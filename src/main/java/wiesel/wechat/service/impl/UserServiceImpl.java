package wiesel.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wiesel.wechat.entity.User;
import wiesel.wechat.mapper.UserMapper;
import wiesel.wechat.service.UserService;

/**
 *
 * @ClassName 类名：UserServiceImpl
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2017年3月22日
 * @author 创建人：Wiesel
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2017年3月22日 wujian 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public void doDel(User user) {
		userMapper.deleteByPrimaryKey(user.getUserId());

	}

	@Override
	public void doUpdate(User user) {
		userMapper.updateByPrimaryKey(user);

	}

	@Override
	public void doInsert(User user) {
		userMapper.insert(user);
	}

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return userMapper.selectAll();
	}

}
