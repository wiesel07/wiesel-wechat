package wiesel.wechat.mapper;

import java.util.List;

import wiesel.wechat.entity.Vote;
import wiesel.wechat.entity.VoteAccount;

public interface VoteAccountMapper {
    int deleteByPrimaryKey(String accountId);

    int insert(VoteAccount record);

    VoteAccount selectByPrimaryKey(String accountId);

    List<VoteAccount> selectAll();

    int updateByPrimaryKey(VoteAccount record);
    
    /**
	 * 
	 * <p>
	 * 函数名称：
	 * </p>
	 * <p>
	 * 功能说明：根据用户ID获取统计实体
	 *
	 * </p>
	 * <p>
	 * 参数说明：
	 * </p>
	 * 
	 * @param voteId
	 * @return
	 *
	 * @date 创建时间：2017年3月20日
	 * @author 作者：wujian
	 */
	VoteAccount selectByOpenId(String openid);
	
	List<VoteAccount> selectByItemId(String openid);	
}