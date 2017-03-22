package wiesel.wechat.mapper;

import java.util.List;
import wiesel.wechat.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    User selectByPrimaryKey(String userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}