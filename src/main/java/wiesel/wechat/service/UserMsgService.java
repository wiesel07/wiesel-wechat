package wiesel.wechat.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import wiesel.wechat.entity.UserMsg;

/**
 *
 * @ClassName 类名：UserMsgService
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
public interface UserMsgService {
	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：新增
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param userMsg
	 *
	 * @date 创建时间：2017年3月13日
	 * @author 作者：wujian
	 */
	public void doInsert(UserMsg userMsg);

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：删除
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param id
	 *
	 * @date 创建时间：2017年3月13日
	 * @author 作者：wujian
	 */
	public void doDel(String id);

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：通过类型获取相应消息的集合
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param type
	 * @return
	 *
	 * @date 创建时间：2017年3月13日
	 * @author 作者：wujian
	 */
	public List<UserMsg> getUserMsgListByType(Integer type);

	/**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：获取未处理的弹幕消息
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param type
	 * @param status
	 * @return
	 *
	 * @date 创建时间：2017年3月14日
	 * @author 作者：wujian
	 */
	public List<UserMsg> getBarrageMsgList(UserMsg userMsg);

	public void updateBarrageMsg(List<UserMsg> userMsgList);
}
