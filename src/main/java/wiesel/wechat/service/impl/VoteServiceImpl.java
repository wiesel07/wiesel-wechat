package wiesel.wechat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wiesel.wechat.entity.Vote;
import wiesel.wechat.entity.VoteAccount;
import wiesel.wechat.entity.VoteItems;
import wiesel.wechat.mapper.VoteAccountMapper;
import wiesel.wechat.mapper.VoteItemsMapper;
import wiesel.wechat.mapper.VoteMapper;
import wiesel.wechat.service.VoteService;

/**
 *
 * @ClassName 类名：VoteServiceImpl
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2017年3月14日
 * @author 创建人：Wiesel
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2017年3月14日 wujian 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */

@Service
@Scope("prototype")
public class VoteServiceImpl implements VoteService {
	@Autowired
	private VoteMapper voteMapper;

	@Autowired
	private VoteItemsMapper voteItemsMapper;

	@Autowired
	private VoteAccountMapper voteAccountMapper;
	
	@Override
	@Transactional
	public void doInsert(Vote vote, List<VoteItems> voteItems){
		voteMapper.insert(vote);
		for (VoteItems voteItems2 : voteItems) {
			voteItemsMapper.insert(voteItems2);
		}

	}

	@Override
	public List<Vote> getVoteList() {
		
		return voteMapper.selectAll();
	}

	@Override
	public List<VoteItems> getVoteItemsList() {
		
		return voteItemsMapper.selectAll();
	}

	@Override
	@Transactional
	public int doInsertVoteCount(List<VoteAccount> voteAccountList) {
		
		int i=0;
		for (VoteAccount voteAccount : voteAccountList) {
			voteAccountMapper.insert(voteAccount);
			i++;
		}
		return i;
	}

	/**
	 * 根据主键获取统计表实体
	 */
	@Override
	public VoteAccount getVoteAccount(String accountId) {
		
		return voteAccountMapper.selectByPrimaryKey(accountId);
	}

	@Override
	public VoteAccount getVoteAccountByOpenid(String openid) {
		return voteAccountMapper.selectByOpenId(openid);
	}

	@Override
	public List<VoteAccount> getVoteAccountByItemId(String itemId) {
		
		return voteAccountMapper.selectByItemId(itemId);
	}

}
