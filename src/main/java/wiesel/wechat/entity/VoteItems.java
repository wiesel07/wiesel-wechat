package wiesel.wechat.entity;

public class VoteItems {
    /**
     * 元素ID
     */
    private String itemId;

    /**
     * 元素说明
     */
    private String itemAccount;

    /**
     * 投票ID
     */
    private String voteId;

    /**
     * 选项
     */
    private String voteItem;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemAccount() {
        return itemAccount;
    }

    public void setItemAccount(String itemAccount) {
        this.itemAccount = itemAccount;
    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getVoteItem() {
        return voteItem;
    }

    public void setVoteItem(String voteItem) {
        this.voteItem = voteItem;
    }
}