package wiesel.wechat.mapper;

import java.util.List;
import wiesel.wechat.entity.WechatUser;

public interface WechatUserMapper {
    int deleteByPrimaryKey(String openid);

    int insert(WechatUser record);

    WechatUser selectByPrimaryKey(String openid);

    List<WechatUser> selectAll();

    int updateByPrimaryKey(WechatUser record);
}