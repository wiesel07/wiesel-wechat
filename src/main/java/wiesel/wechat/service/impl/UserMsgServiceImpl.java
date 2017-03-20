package wiesel.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiesel.wechat.entity.UserMsg;
import wiesel.wechat.mapper.UserMsgMapper;
import wiesel.wechat.service.UserMsgService;

/**
 *
 * @ClassName 类名：UserMsgServiceImpl
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2017年3月13日
 * @author 创建人：Wiesel
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2017年3月13日 wujian 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Service
public class UserMsgServiceImpl implements UserMsgService {

	@Autowired
	private UserMsgMapper userMsgMapper;

	@Override
	public void doInsert(UserMsg userMsg) {
		userMsgMapper.insert(userMsg);

	}

	@Override
	public void doDel(String id) {
		userMsgMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<UserMsg> getUserMsgListByType(Integer type) {

		return userMsgMapper.findByType(type);
	}

	@Override
	public List<UserMsg> getBarrageMsgList(UserMsg userMsg) {

		return userMsgMapper.findBarrage(userMsg);
	}

	@Override
	public void updateBarrageMsg(List<UserMsg> userMsgList) {
		for (UserMsg userMsg : userMsgList) {
			userMsg.setStatus(2);
			userMsgMapper.updateByPrimaryKey(userMsg);
		}

	}

}
