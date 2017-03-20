package wiesel.wechat.mapper;

import java.util.List;
import wiesel.wechat.entity.AccessToken;

public interface AccessTokenMapper {
    int insert(AccessToken record);

    List<AccessToken> selectAll();
    
    /**
     * 
     * @date   创建时间：2016年12月18日
     * @Description：获取单个AccessToken
     * @param accessToken
     */
    public void selectOne(AccessToken accessToken);
    
    /**
     * 
     * @date   创建时间：2016年12月18日
     * @Description：删除AccessToken
     * @param accessToken
     */
    public void deleteByAccessToken(AccessToken accessToken);
}