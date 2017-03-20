package wiesel.wechat.service;

import java.util.List;

import wiesel.wechat.entity.WechatUser;

/**
 *
 * @ClassName 类名：WechatUserService
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
public interface WechatUserService {
	
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：新增用户
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param wechatUser
	 *
	 * @date   创建时间：2017年3月13日
	 * @author 作者：wujian
	 */
	public void doInsert(WechatUser wechatUser);
	
	/**
	 * 
	 * <p>函数名称：        </p>
	 * <p>功能说明：删除用户
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param openid
	 *
	 * @date   创建时间：2017年3月13日
	 * @author 作者：wujian
	 */
	public void doDel(String openid);
	
	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：获取微信公众号所有关注的用户
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @return
	 *
	 * @date 创建时间：2017年3月13日
	 * @author 作者：wujian
	 */
	public List<WechatUser> getWechatUserList();

}
