package wiesel.wechat.mapper;

import java.util.List;
import wiesel.wechat.entity.VoteItems;

public interface VoteItemsMapper {
    int deleteByPrimaryKey(String itemId);

    int insert(VoteItems record);

    VoteItems selectByPrimaryKey(String itemId);

    List<VoteItems> selectAll();

    int updateByPrimaryKey(VoteItems record);
}