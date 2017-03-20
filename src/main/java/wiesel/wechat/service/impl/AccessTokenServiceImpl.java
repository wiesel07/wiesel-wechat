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

package wiesel.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import wiesel.wechat.entity.AccessToken;
import wiesel.wechat.mapper.AccessTokenMapper;
import wiesel.wechat.service.AccessTokenService;

/**
 * TODO 此处填写 class 信息
 *
 * @author wuj (wuj:wiesel@163.com)
 */
public class AccessTokenServiceImpl implements AccessTokenService {

	@Autowired
	private AccessTokenMapper accessTokenMapper;

	@Override
	public void insertAccessToken(AccessToken accessToken) {
		accessTokenMapper.insert(accessToken);
	}

	@Override
	public void delAccessToken(AccessToken accessToken) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAccessToken(AccessToken accessToken) {
		// TODO Auto-generated method stub

	}

}

/*
 * 修改历史 $Log$
 */