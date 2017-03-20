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
 * Created on 2016年10月29日
 *******************************************************************************/

package wiesel.message.req.entity;

/**
 * TODO 消息基类
 *
 * @author wujian (mailto:wujian@bosssoft.com.cn)
 */
public class BaseMessage {
	// 接收方帐号（收到的OpenID）
	private String ToUserName;

	// 开发者微信号
	private String FromUserName;

	// 消息创建时间 （整型）
	private long CreateTime;

	// 消息类型（text/music/news）
	private String MsgType;

	// 位0x0001被标志时，星标刚收到的消息
	private int FuncFlag;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public int getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(int funcFlag) {
		FuncFlag = funcFlag;
	}
}

/*
 * 修改历史 $Log$
 */