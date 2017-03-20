package wiesel.wechat.entity;

import java.util.Date;

public class AccessToken {
    /**
     * 接口访问凭证
     */
    private String accessToken;

    /**
     * 凭证有效期，单位：秒
     */
    private Integer expiresIn;

    /**
     * 创建日期
     */
    private Date creadateDate;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getCreadateDate() {
        return creadateDate;
    }

    public void setCreadateDate(Date creadateDate) {
        this.creadateDate = creadateDate;
    }
}