package wiesel.wechat.entity;

import java.util.List;
/** 
*
* @ClassName   类名：VoteExt 
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
public class VoteExt extends Vote {
	public List<VoteItems> voteItem ;

	public List<VoteItems> getVoteItemsList() {
		return voteItem;
	}

	public void setVoteItemsList(List<VoteItems> voteItem) {
		this.voteItem = voteItem;
	}
	
	
}
