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
 * Created on 2016年12月17日
 *******************************************************************************/

package wiesel.common.util;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * TODO 此处填写 class 信息
 *
 * @author wuj (wuj:wiesel@163.com)
 */
public class WechatInfo {
	private final static String APP_ID = "wx1e365de7b505ce44";

	private final static String APPSECRET = "6f1775238822ca8bec449407a88fe670";

	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APP_ID+"&secret="+APPSECRET;

	public static final String CUR_ACCESS_TOKEN="2pGEi0GJoLNJBL1glDM376tbpNzJH96bfkeZ0URO-okivohL3fobln_MooGjjPUOGxbLk43KTzIf1W0gXR026OeWq1sji4jL1npahzlsDU23vSwCmAI6c645QaSoRIEjERMaAEATGB";
	
	public static final String USER_INFO_URL ="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+CUR_ACCESS_TOKEN+"&openid=";
	// 错误编码 {"errcode":40013,"errmsg":"invalid appid"}
	public static int ERRCODE = 40013;
}

/*
 * 修改历史 $Log$
 */