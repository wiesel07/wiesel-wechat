package wiesel.wechat.mapper;

import java.util.List;
import wiesel.wechat.entity.Vote;

public interface VoteMapper {
	int deleteByPrimaryKey(String voteId);

	int insert(Vote record);

	Vote selectByPrimaryKey(String voteId);

	List<Vote> selectAll();

	int updateByPrimaryKey(Vote record);


}