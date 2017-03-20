package wiesel.wechat.entity;

public class Vote {
    /**
     * 投票ID(唯一标识)
     */
    private String voteId;

    /**
     * 投票标题
     */
    private String voteTitle;

    /**
     * 开始时间（目前暂定页面不显示）
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 投票说明
     */
    private String voteAccount;

    /**
     * 图片
     */
    private String voteImage;

    /**
     * 音乐
     */
    private String voteMusic;

    /**
     * 单选，多选（最多两项），多选（无限制）
     */
    private String voteType;

    /**
     * 匿名状态（1-匿名 ，0-不匿名）
     */
    private Integer anonymityFlag;

    /**
     * 有效天数
     */
    private String validDay;

    /**
     * 图片路径
     */
    private String filePath;

    /**
     * 图片名称
     */
    private String fileName;

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getVoteTitle() {
        return voteTitle;
    }

    public void setVoteTitle(String voteTitle) {
        this.voteTitle = voteTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getVoteAccount() {
        return voteAccount;
    }

    public void setVoteAccount(String voteAccount) {
        this.voteAccount = voteAccount;
    }

    public String getVoteImage() {
        return voteImage;
    }

    public void setVoteImage(String voteImage) {
        this.voteImage = voteImage;
    }

    public String getVoteMusic() {
        return voteMusic;
    }

    public void setVoteMusic(String voteMusic) {
        this.voteMusic = voteMusic;
    }

    public String getVoteType() {
        return voteType;
    }

    public void setVoteType(String voteType) {
        this.voteType = voteType;
    }

    public Integer getAnonymityFlag() {
        return anonymityFlag;
    }

    public void setAnonymityFlag(Integer anonymityFlag) {
        this.anonymityFlag = anonymityFlag;
    }

    public String getValidDay() {
        return validDay;
    }

    public void setValidDay(String validDay) {
        this.validDay = validDay;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}