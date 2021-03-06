package io.wjh.wcartadministrationback.dto.out;

public class AdministratorGetProfileOutDTO {
    private Integer administartorId;
    private String username;
    private String realName;
    private String email;
    private String avatarUrl;
    private Long createTimestamp;

    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Integer getAdministartorId() {
        return administartorId;
    }

    public void setAdministartorId(Integer administartorId) {
        this.administartorId = administartorId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
