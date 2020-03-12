package io.wjh.wcartstoreback.dto.in;

public class CustomerChangePwdInDTO {
    private String originPwd;
    private String newPwd;
    private String reNewPwd;

    public String getReNewPwd() {
        return reNewPwd;
    }

    public void setReNewPwd(String reNewPwd) {
        this.reNewPwd = reNewPwd;
    }

    public String getOriginPwd() {
        return originPwd;
    }

    public void setOriginPwd(String originPwd) {
        this.originPwd = originPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
