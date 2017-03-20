/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2016 Bosssoft Co, Ltd.
 * All rights reserved.
 * 
 * Created on 2016年11月4日
 *******************************************************************************/

package wiesel.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.fabric.xmlrpc.base.Data;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import wiesel.common.util.CheckUtil;
import wiesel.common.util.CommonInterfacesUtil;
import wiesel.common.util.DateUtilsExt;
import wiesel.common.util.MessageUtil;
import wiesel.common.util.UUIDTool;
import wiesel.common.util.WechatInfo;
import wiesel.message.resp.entity.TextMessage;
import wiesel.wechat.entity.AccessToken;
import wiesel.wechat.entity.UserMsg;
import wiesel.wechat.entity.WechatUser;
import wiesel.wechat.service.UserMsgService;
import wiesel.wechat.service.WechatUserService;

/**
 * TODO 微信核心请求类
 *
 * @author wujian (mailto:wujian@bosssoft.com.cn)
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController {
	private final static Logger logger = LogManager.getLogger(WeChatController.class.getName());

	@Autowired
	private WechatUserService weChatUserService;

	@Autowired
	private UserMsgService userMsgService;
	
	
	

	// 发送方帐号（open_id）
	private String FROM_USER_NAME = "";

	// 公众帐号
	private String TO_USER_NAME = "";

	// 消息类型
	private String MSG_TYPE = "";

	/**
	 * 
	 * @date 创建时间：2016年12月17日
	 * @Description：校验信息是否从微信服务器发过来的
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public void valid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Execute action's WeiXin:execute() method. Start:{" + System.currentTimeMillis() + "}");
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");// 随机字符串

		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (CheckUtil.checkSingnature(signature, timestamp, nonce)) {
			out.write(echostr);
		} else {
			logger.info(new Data() + "---不是从微信服务器发过来的消息！请小心！");
		}
		out.close();
		out = null;

		logger.info("Execute action's WeiXin:dispatchWeiXinRequest() method. GET");
	}

	/**
	 * 
	 * @date 创建时间：2016年12月17日
	 * @Description：处理从微信服务器发过来的消息
	 * @param request
	 * @param response
	 * @throws IOException
	 */

	@RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {

		logger.info("Execute action's WeiXin:execute() method. Start:{" + System.currentTimeMillis() + "}");
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 调用核心业务类接收消息、处理消息
		String respMessage = processRequest(request);

		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
		logger.info("Execute action's WeiXin:dispatchWeiXinRequest() method. POST");
	}

	@ResponseBody
	@RequestMapping(value = "initMenu", produces = "application/json;charset=UTF-8")
	public String initMenu() {
		return "test";
	}

	@ResponseBody
	@RequestMapping(value = "getAcessToken", produces = "application/json;charset=UTF-8")
	public AccessToken getAcessToken() {

		AccessToken accessToken = null;
		String requestUrl = WechatInfo.ACCESS_TOKEN_URL;

		JSONObject jsonObject = CommonInterfacesUtil.httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				accessToken = new AccessToken();
				accessToken.setAccessToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				logger.error("获取token失败 errcode:{" + jsonObject.getInt("errcode") + "} errmsg:{"
						+ jsonObject.getString("errmsg") + "}");
			}
		}

		return accessToken;

	}

	private String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			Map<String,String> results = new HashMap<String,String>();
			// 发送方帐号（open_id）
			FROM_USER_NAME = requestMap.get("FromUserName");
			// 公众帐号
			TO_USER_NAME = requestMap.get("ToUserName");
			// 消息类型
			MSG_TYPE = requestMap.get("MsgType");
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(FROM_USER_NAME);
			textMessage.setFromUserName(TO_USER_NAME);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			// 文本消息
			if (MSG_TYPE.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

				results = handlText(requestMap);
				System.out.println(results.get("flag"));
				if ("1".equalsIgnoreCase(results.get("flag").trim())) {
					respContent ="成功！";
					logger.info("1");
				}else {
					//respContent = "您发送的是文本消息！";
					respContent = results.get("msg".trim());
					
					logger.info("2");
				}
			}
			// 图片消息
			else if (MSG_TYPE.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				 respContent = "您发送的是图片消息！";
				/* textMessage.setContent("[难过] /难过 /::("); */
				//respContent = "[难过] /难过 /::(";
			}
			// 地理位置消息
			else if (MSG_TYPE.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (MSG_TYPE.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (MSG_TYPE.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (MSG_TYPE.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {

				results = handleEvent(requestMap);
				respContent = (String) results.get("msg");
			}

			textMessage.setContent(respContent);
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

	private Map<String,String> handlText(Map<String, String> requestMap) {
		Map<String,String> results = new HashMap<String,String>();
		results.put("flag", "1");
		String content = requestMap.get("Content");

		
		if (content.contains("+")) {
			String[] arrs = content.split("[+]");
			UserMsg userMsg = new UserMsg();
			userMsg.setOpenid(FROM_USER_NAME);
			userMsg.setMsgId(UUIDTool.getUUID());
			userMsg.setStatus(1);
			
			if ("投票".equalsIgnoreCase(arrs[0].trim())) {
				if (arrs.length == 1) {
				
					userMsg.setTitle(UserMsg.VOTE_NAME);
					userMsg.setType(UserMsg.VOTE);
					userMsg.setCreateTime(DateUtilsExt.getNowDateTime());
					userMsgService.doInsert(userMsg);// 新增
					logger.info("3");
					return results;

				} else {
					results.put("flag", "0");
					results.put("msg", "发送投票请按如下格式：投票+");
					logger.info("4");
					return results;
				}
			}
			logger.info("5");
			if ("留言".equalsIgnoreCase(arrs[0].trim())) {
				userMsg.setType(UserMsg.LEAVE_MSG);
				if (arrs.length == 2) {
					userMsg.setTitle(UserMsg.LEAVE_MSG_NAME);
					userMsg.setType(UserMsg.LEAVE_MSG);
					userMsg.setCreateTime(DateUtilsExt.getNowDateTime());
					userMsg.setContent(arrs[1]);
					userMsgService.doInsert(userMsg);// 新增

					return results;
				} else {
					results.put("flag","0");
					results.put("msg", "发送留言请按如下格式：留言+xxx(想要说的话)");
					return results;
				}
			}

			if ("弹幕".equalsIgnoreCase(arrs[0].trim())) {
				if (arrs.length == 2) {
					userMsg.setTitle(UserMsg.BARRAGE_NAME);
					userMsg.setType(UserMsg.BARRAGE);
					userMsg.setCreateTime(DateUtilsExt.getNowDateTime());
					userMsg.setContent(arrs[1]);
					userMsgService.doInsert(userMsg);// 新增
					return results;
				} else {
					results.put("flag","0");
					results.put("msg", "发送弹幕请按如下格式：弹幕+xxx(想要说的话)");
					return results;
				}
			}

			if ("点播".equalsIgnoreCase(arrs[0].trim())) {
				userMsg.setType(UserMsg.ORDER);
				if (arrs.length == 3) {
					userMsg.setTitle(UserMsg.ORDER_NAME);
					userMsg.setType(UserMsg.ORDER);
					userMsg.setCreateTime(DateUtilsExt.getNowDateTime());

					StringBuffer buffer = new StringBuffer();
					buffer.append("歌曲-" + arrs[1]);
					buffer.append("===" + arrs[2]);
					userMsg.setContent(buffer.toString());

					userMsgService.doInsert(userMsg);// 新增

					return results;
				} else {
					results.put("flag", "0");
					results.put("msg", "发送点播请按如下格式：弹幕+xxx(歌曲)+xxx(想要说的话)");
					return results;
				}
			}
			if ("抽奖".equalsIgnoreCase(arrs[0].trim())) {

			}

			results.put("flag", "0");
			return results;
		}
		
		results.put("flag", "0");
		return results;
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：处理事件消息
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 *
	 * @date 创建时间：2017年3月13日
	 * @author 作者：wujian
	 */
	private Map<String,String> handleEvent(Map<String, String> requestMap) {

		Map<String,String> results = new HashMap<String,String>();
		// 事件类型
		String eventType = requestMap.get("Event");

		StringBuffer buffer = new StringBuffer();

		// 订阅
		if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
			System.out.println("关注======" + requestMap.get("FromUserName"));
			WechatUser wechatUser = new WechatUser();
			wechatUser.setOpenid(FROM_USER_NAME);

			buffer.append(WechatInfo.USER_INFO_URL);
			buffer.append(FROM_USER_NAME);

			JSONObject jsonObject = getJsonObject(buffer.toString(), "GET");

			String nickname = jsonObject.getString("nickname");
			String city = jsonObject.getString("city");
			String country = jsonObject.getString("country");
			String province = jsonObject.getString("province");
			Integer sex = jsonObject.getInt("sex");
			// Long subscribeTime = jsonObject.getInt("subscribeTime");

			wechatUser.setNickname(nickname);
			wechatUser.setCity(city);
			wechatUser.setCountry(country);
			wechatUser.setProvince(province);
			wechatUser.setSex(sex);
			// wechatUser.setSubscribeTime(subscribeTime);
			wechatUser.setCreateTime(DateUtilsExt.getNowDateTime());

			weChatUserService.doInsert(wechatUser);
			results.put("msg", "谢谢您的关注！");
		}
		// 取消订阅
		else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {

			weChatUserService.doDel(FROM_USER_NAME);// 删除用户
			System.out.println("取消关注======" + requestMap.get("FromUserName"));
		}
		// 自定义菜单点击事件
		else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
			// TODO 自定义菜单权没有开放，暂不处理该类消息
		}

		return results;
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：获取调用微信相应接口返回的数据
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param requestUrl
	 * @return
	 *
	 * @date 创建时间：2017年3月13日
	 * @author 作者：wujian
	 */
	private JSONObject getJsonObject(String requestUrl, String method) {
		JSONObject jsonObject = CommonInterfacesUtil.httpRequest(requestUrl, method, null);
		return jsonObject;
	}
}

/*
 * 修改历史 $Log$
 */