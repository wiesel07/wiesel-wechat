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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
import wiesel.wechat.entity.UserMsg;
import wiesel.wechat.entity.Vote;
import wiesel.wechat.entity.VoteAccount;
import wiesel.wechat.entity.VoteItems;
import wiesel.wechat.entity.WechatUser;
import wiesel.wechat.service.UserMsgService;
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
	public void vote(HttpServletRequest request, HttpServletResponse response, String openId)
			throws ServletException, IOException {

		// response.setStatus(302);
		// response.setHeader("Location", "../resources/Links/html/vote.html");

		HttpSession httpSession = request.getSession();
		openId = "18120825406";

		httpSession.setAttribute("openId", openId);
		// request.getRequestDispatcher("../resources/Links/html/vote.html").forward(request,
		// response);
		response.sendRedirect("../resources/Links/html/vote.html");

	}

	@ResponseBody
	@RequestMapping("getVoteMsg")
	public Map<String, Object> getVoteMsg(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();

		Map<String, Object> results = new HashMap<String, Object>();
		List<Vote> votes = voteService.getVoteList();
		List<VoteItems> voteItems = voteService.getVoteItemsList();

		results.put("votes", votes);
		results.put("voteItems", voteItems);
		results.put("openId", httpSession.getAttribute("openId"));
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
		vote.setVoteAccount("1");

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
				voteAccount.setAccountId(UUIDTool.getUUID());
				voteAccountList.add(voteAccount);
			}
			voteService.doInsertVoteCount(voteAccountList);
			
			for (String itemId : voteItems) {
				int count = voteService.getVoteAccountByItemId(itemId).size();
				results.put(itemId, count);
			}
		
			results.put("msg", "投票成功！");
			

		} else {
			results.put("msg", "每个用户只允许投一次票！");
			results.put("flag", false);
			return results;
		}

		results.put("flag", true);
		return results;
	}
}
