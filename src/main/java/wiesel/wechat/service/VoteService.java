package wiesel.wechat.service;

import java.util.List;

import wiesel.wechat.entity.Vote;
import wiesel.wechat.entity.VoteAccount;
import wiesel.wechat.entity.VoteItems;

/** 
*
* @ClassName   类名：VoteService 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2017年3月14日
* @author      创建人：Wiesel
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2017年3月14日   wujian   创建该类功能。
*
***********************************************************************
*</p>
*/
public interface VoteService {
  public void doInsert(Vote vote,List<VoteItems> voteItems);
  
  public List<Vote> getVoteList();
  
  public List<VoteItems> getVoteItemsList();
  
  public int doInsertVoteCount (List<VoteAccount> voteAccountList);
  
  /**
   * 
   * <p>函数名称：        </p>
   * <p>功能说明：根据统计ID获取统计实体
   *
   * </p>
   *<p>参数说明：</p>
   * @param accountId
   * @return
   *
   * @date   创建时间：2017年3月20日
   * @author 作者：wujian
   */
  public VoteAccount getVoteAccount(String accountId);
  
  /**
   * 
   * <p>函数名称：        </p>
   * <p>功能说明：根据用户ID获取统计实体
   *
   * </p>
   *<p>参数说明：</p>
   * @param openid
   * @return
   *
   * @date   创建时间：2017年3月20日
   * @author 作者：wujian
   */
  public VoteAccount getVoteAccountByOpenid(String openid);
  
  public List<VoteAccount> getVoteAccountByItemId(String itemId);
}
