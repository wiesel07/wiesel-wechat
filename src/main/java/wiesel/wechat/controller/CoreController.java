package wiesel.wechat.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ObjectUtils;
import org.apache.ibatis.annotations.Results;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.sql.visitor.functions.If;
import com.mysql.fabric.xmlrpc.base.Array;

import wiesel.common.util.DateUtilsExt;
import wiesel.common.util.UUIDTool;
import wiesel.wechat.entity.User;
import wiesel.wechat.entity.UserMsg;
import wiesel.wechat.entity.Vote;
import wiesel.wechat.entity.VoteAccount;
import wiesel.wechat.entity.VoteItems;
import wiesel.wechat.entity.WechatUser;
import wiesel.wechat.service.UserMsgService;
import wiesel.wechat.service.UserService;
import wiesel.wechat.service.VoteService;
import wiesel.wechat.service.WechatUserService;

/**
 *
 * @ClassName 类名：CoreController
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2017年3月13日
 * @author 创建人：Wiesel
 * @version 版本号：V1.0
 *          <p>
 ****************************          修订记录*************************************
 * 
 *          2017年3月13日 wujian 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Controller
@RequestMapping("/core")
public class CoreController {
	private final static Logger logger = LogManager.getLogger(CoreController.class.getName());

	@Autowired
	private WechatUserService wechatUserService;

	@Autowired
	private UserMsgService userMsgService;

	@Autowired
	private VoteService voteService;

	@Autowired
	private UserService userService;

	// @RequestMapping(value = "/login", name = "主界面", method = {
	// RequestMethod.POST,
	// RequestMethod.GET })
	@RequestMapping("showIndex")
	public void showIndex(Model model, HttpServletResponse response) {

		// return "showIndex.jsp";
		// return "showOrderIndex.jsp";
		// return "index111.jsp";

		response.setStatus(302);
		response.setHeader("Location", "../resources/Links/adminmanage.html");
		// model.addAttribute("userMsg", "vote");
		// return "usermsg";
	}

	@RequestMapping("vote")
	public void vote(HttpServletRequest request, HttpServletResponse response, String openId, String voteId)
			throws ServletException, IOException {

		// response.setStatus(302);
		// response.setHeader("Location", "../resources/Links/html/vote.html");

		logger.info(openId + "------------" + voteId);
		HttpSession httpSession = request.getSession();
		// openId = "oGAklwNgViB1UI__PG8UBQEAmBN4";

		httpSession.setAttribute("voteId", voteId);
		httpSession.setAttribute("openId", openId);

		response.sendRedirect("../resources/Links/html/vote.html");

	}

	@ResponseBody
	@RequestMapping("getVoteMsg")
	public Map<String, Object> getVoteMsg(HttpServletRequest request) {
		// 还有参数投票ID 以及用户信息
		HttpSession httpSession = request.getSession();
		String voteId = (String) httpSession.getAttribute("voteId");// 获取投票ID
		String openId = (String) httpSession.getAttribute("openId");// 获取投票ID

		Map<String, Object> results = new HashMap<String, Object>();
		VoteAccount voteAccount = voteService.getVoteAccountByOpenid(openId);
		if (voteAccount != null) {
			// results.put("url", "voteResult.html");
			results.put("flag", false);
			return results;
		} else {

			Vote votes = voteService.getVote(voteId);
			List<VoteItems> voteItems = voteService.getVoteItemsListByVoteId(voteId);

			results.put("flag", true);
			results.put("votes", votes);
			results.put("voteItems", voteItems);
			results.put("openId", httpSession.getAttribute("openId"));

		}
		return results;
	}

	@ResponseBody
	@RequestMapping("getVoteResultMsg")
	public Map<String, Object> getVoteResultMsg(HttpServletRequest request) {
		// 还有参数投票ID 以及用户信息
		HttpSession httpSession = request.getSession();
		String voteId = (String) httpSession.getAttribute("voteId");// 获取投票ID
		String openid = (String) httpSession.getAttribute("openId");// 获取维信用户ID

		Map<String, Object> results = new HashMap<String, Object>();
		Vote votes = voteService.getVote(voteId);
		List<VoteItems> voteItems = voteService.getVoteItemsListByVoteId(voteId);

		for (VoteItems vItems : voteItems) {
			List<VoteAccount> voteAccountList = voteService.getVoteAccountByItemId(vItems.getItemId());
			if (voteAccountList != null && voteAccountList.size() > 0) {
				int count = voteAccountList.size();
				results.put(vItems.getItemId(), count);
			} else {
				results.put(vItems.getItemId(), 0);
			}
		}

		WechatUser wechatUser = wechatUserService.getWechatUser(openid);
		results.put("nickname", wechatUser.getNickname());

		results.put("votes", votes);
		results.put("voteItems", voteItems);

		return results;
	}


	@RequestMapping(value = "queryWechatUserPage")
	@ResponseBody
	public List<WechatUser> queryWechatUserPage() {
		logger.info("user in");
		return wechatUserService.getWechatUserList();

	}

	@RequestMapping(value = "queryVotePage")
	@ResponseBody
	public List<Vote> queryVotePage() {

		return voteService.getVoteList();

	}

	@RequestMapping(value = "queryVoteItemPage")
	@ResponseBody
	public List<VoteItems> queryVoteItemPage(String voteId) {
		return voteService.getVoteItemsListByVoteId(voteId);

	}

	@RequestMapping(value = "queryVoteMsgPage")
	@ResponseBody
	public List<UserMsg> queryVoteMsgPage() {

		return userMsgService.getUserMsgListByType(UserMsg.VOTE);

	}

	@RequestMapping(value = "queryOrderPage")
	@ResponseBody
	public List<UserMsg> queryOrderPage() {
		logger.info("order in");
		return userMsgService.getUserMsgListByType(UserMsg.ORDER);

	}

	@RequestMapping(value = "queryLeaveMsgPage")
	@ResponseBody
	public List<UserMsg> queryLeaveMsgPage() {
		logger.info("leave in");
		return userMsgService.getUserMsgListByType(UserMsg.LEAVE_MSG);

	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：处理微信弹幕消息
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @return
	 *
	 * @date 创建时间：2017年3月14日
	 * @author 作者：wujian
	 */
	@RequestMapping(value = "HandleBarrageMsg")
	@ResponseBody
	public List<UserMsg> HandleBarrageMsg() {
		UserMsg msg = new UserMsg();
		msg.setType(UserMsg.BARRAGE);
		msg.setStatus(1);
		List<UserMsg> userMsgList = userMsgService.getBarrageMsgList(msg);

		userMsgService.updateBarrageMsg(userMsgList);

		return userMsgList;
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：上传附件
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 *
	 * @date 创建时间：2017年3月21日
	 * @author 作者：wujian
	 */
	@ResponseBody
	@RequestMapping(value = "upload")
	public Map<String, String> upload(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {

		Map<String, String> results = new HashMap<String, String>();
		StringBuffer buffer = new StringBuffer();
		String path = request.getSession().getServletContext().getRealPath("upload");
		// String fileName = file.getOriginalFilename();

		String fileName = buffer.append(UUIDTool.getUUID() + file.getOriginalFilename()).toString();
		// String fileName = new Date().getTime()+".jpg";
		System.out.println(path);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// model.addAttribute("fileUrl",
		// request.getContextPath()+"/upload/"+fileName);
		results.put("path", path);
		results.put("fileName", fileName);

		return results;
	}

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：发布投票
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param vote
	 * @param items
	 * @return
	 *
	 * @date 创建时间：2017年3月15日
	 * @author 作者：wujian
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "doSaveVote")
	public Map<String, Object> doSaveVote(Vote vote, @RequestParam(value = "items") String items) {
		Map<String, Object> results = new HashMap<String, Object>();

		vote.setVoteId(UUIDTool.getUUID());
		vote.setStartDate(DateUtilsExt.getNowDateTime());
		vote.setEndDate(DateUtilsExt.formateNowDateTime(
				DateUtilsExt.addDays(vote.getStartDate(), Integer.parseInt(vote.getValidDay()))));
		vote.setVoteAccount("暂时没有说明");
		vote.setStatus(1);// 投票实体默认状态为1

		String[] arr_item = items.trim().split("---");
		List<VoteItems> voteItems = new ArrayList<>();

		for (String item : arr_item) {
			VoteItems vItems = new VoteItems();
			vItems.setVoteId(vote.getVoteId());
			vItems.setVoteItem(item);
			vItems.setItemId(UUIDTool.getUUID());
			voteItems.add(vItems);
		}

		voteService.doInsert(vote, voteItems);

		results.put("flag", true);
		return results;
	}

	@ResponseBody
	@RequestMapping(value = "doVoteCount")
	public Map<String, Object> doVoteCount(@RequestParam(value = "itemJson") String itemJson,
			HttpSession httpSession) {
		Map<String, Object> results = new HashMap<String, Object>();
		String openid = (String) httpSession.getAttribute("openId");

		String[] voteItems = itemJson.split("---");
		VoteAccount voteAccount = voteService.getVoteAccountByOpenid(openid);
		List<VoteAccount> voteAccountList = new ArrayList<>();

		if (voteAccount == null) {
			voteAccount = new VoteAccount();
			voteAccount.setOpenid(openid);
			for (String itemId : voteItems) {
				voteAccount.setItemId(itemId);
				voteAccount.setAccount(1);
				voteAccount.setVoteTime(DateUtilsExt.getNowDateTime());
				voteAccount.setAccountId(UUIDTool.getUUID());
				voteAccountList.add(voteAccount);
			}
			voteService.doInsertVoteCount(voteAccountList);

			results.put("msg", "投票成功！");

		} else {
			results.put("msg", "每个用户只允许投一次票！");
			results.put("flag", false);
			return results;
		}

		results.put("flag", true);
		return results;
	}

	@ResponseBody
	@RequestMapping(value = "doInsertUser")
	public Map<String, Object> doInsertUser(User user) {
		Map<String, Object> results = new HashMap<String, Object>();
		user.setUserId(UUIDTool.getUUID());
		user.setUserType("SYSTEM");
		user.setCreateTime(DateUtilsExt.getNowDateTime());
		
		userService.doInsert(user);
		results.put("msg", "新增成功！");
		results.put("flag", true);
		return results;
	}

	@ResponseBody
	@RequestMapping(value = "doDelUser")
	public Map<String, Object> doDelUser(User user) {
		Map<String, Object> results = new HashMap<String, Object>();

		userService.doDel(user);
		results.put("msg", "删除成功！");
		results.put("flag", true);
		return results;
	}

	@ResponseBody
	@RequestMapping(value = "doUpdateUser")
	public Map<String, Object> doUpdatetUser(User user) {
		Map<String, Object> results = new HashMap<String, Object>();

		userService.doUpdate(user);
		results.put("msg", "修改成功！");
		results.put("flag", true);
		return results;

	}
	

	@RequestMapping(value = "queryUserPage")
	@ResponseBody
	public List<User> queryUserPage() {
		return userService.getUserList();
	}
}
