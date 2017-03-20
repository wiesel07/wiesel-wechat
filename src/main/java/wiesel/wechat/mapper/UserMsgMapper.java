package wiesel.wechat.mapper;

import java.util.List;
import wiesel.wechat.entity.UserMsg;

public interface UserMsgMapper {
    int deleteByPrimaryKey(String msgId);

    int insert(UserMsg record);

    UserMsg selectByPrimaryKey(String msgId);

    List<UserMsg> selectAll();

    int updateByPrimaryKey(UserMsg record);
    
    List<UserMsg>  findBarrage(UserMsg userMsg);
    
    List<UserMsg> findByType(Integer tupe);
}