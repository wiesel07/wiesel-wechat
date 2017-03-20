package wiesel.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiesel.wechat.entity.WechatUser;
import wiesel.wechat.mapper.WechatUserMapper;
import wiesel.wechat.service.WechatUserService;

/** 
*
* @ClassName   类名：WechatUserServiceImpl 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2017年3月13日
* @author      创建人：Wiesel
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2017年3月13日   wujian   创建该类功能。
*
***********************************************************************
*</p>
*/
@Service
public class WechatUserServiceImpl implements WechatUserService {

	@Autowired
	private WechatUserMapper wechatUserMapper;
	
	@Override
	public void doInsert(WechatUser wechatUser) {
		wechatUserMapper.insert(wechatUser);
	}

	@Override
	public void doDel(String openid) {
		wechatUserMapper.deleteByPrimaryKey(openid);
	}
	
	@Override
	public List<WechatUser> getWechatUserList() {
		// TODO Auto-generated method stub
		return wechatUserMapper.selectAll();
	}
	

}
