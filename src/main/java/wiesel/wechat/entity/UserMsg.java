package wiesel.wechat.entity;

public class UserMsg {

	public final static Integer BARRAGE = 2;

	public final static Integer ORDER = 4;

	public final static Integer LEAVE_MSG = 3;

	public final static Integer VOTE = 1;

	public final static String BARRAGE_NAME = "弹幕";

	public final static String ORDER_NAME = "点播";

	public final static String LEAVE_MSG_NAME = "留言";

	public final static String VOTE_NAME = "投票";

	/**
	 * 内容ID
	 */
	private String msgId;

	/**
	 * 用户ID
	 */
	private String openid;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 用户类型
	 */
	private Integer type;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}