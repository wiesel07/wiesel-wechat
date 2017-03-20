package wiesel.wechat.entity;

public class VoteAccount {
    /**
     * 统计ID
     */
    private String accountId;

    /**
     * 元素ID
     */
    private String itemId;

    /**
     * 数量
     */
    private Integer account;

    /**
     * 用户ID
     */
    private String openid;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}