package com.web.auto.pojo;

/**
 * @ Author：Pada_xiao
 * @ Date：20:56 2018/11/27
 * @ Description：封装注册用例表单解析出的数据
 * @ Modified By：
 * @Version:
 */
public class Register extends BaseData {
    private String mobilePhone;
    private String password;
    private String passwordConfirm;
    private String verifyCode;


    //有参构造方法
    public Register(String isNegative, String desc, String mobilePhone, String password, String passwordConfirm, String verifyCode, String expectMsg) {
        this.isNegative = isNegative;
        this.desc = desc;
        this.mobilePhone = mobilePhone;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.verifyCode = verifyCode;

    }

    //无参构造方法
    public Register() {
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }



    @Override
    public String toString() {
        return ("isNegative:【"+isNegative+"】,desc:【" + desc + "】,mobilePhone:【" + mobilePhone + ",】password:【" + password + "】,passwordConfirm:【" + passwordConfirm + "】,verifyCode:【" + verifyCode + "】,expectMsg:【" + expectMsg+"】");
    }
}
