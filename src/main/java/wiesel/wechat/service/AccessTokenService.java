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

package wiesel.wechat.service;

import wiesel.wechat.entity.AccessToken;

/**
 * TODO 此处填写 class 信息
 *
 * @author wuj (wuj:wiesel@163.com)
 */
public interface AccessTokenService {
	
	public void insertAccessToken(AccessToken accessToken);

	public void delAccessToken(AccessToken accessToken);

	public void getAccessToken(AccessToken accessToken);
}

/*
 * 修改历史 $Log$
 */