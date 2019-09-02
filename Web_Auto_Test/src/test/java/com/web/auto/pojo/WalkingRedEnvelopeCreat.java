package com.web.auto.pojo;

import com.web.auto.util.ExcelUtil;

import java.util.List;

/**
 * @ Author：Pada_xiao
 * @ Date：14:19 2019/5/6
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class WalkingRedEnvelopeCreat extends BaseData {
    private String activityTheme;
    private String startTime;
    private String endTime;
    private String isRegister;
    private String redEnvelope;


    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getActivityTheme() {
        return activityTheme;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setActivityTheme(String activityTheme) {
        this.activityTheme = activityTheme;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(String isRegister) {
        this.isRegister = isRegister;
    }

    public String getRedEnvelope() {
        return redEnvelope;
    }

    public void setRedEnvelope(String redEnvelope) {
        this.redEnvelope = redEnvelope;
    }

    @Override
    public String toString() {
        return ("isNegative:【"+isNegative+"】,desc:【" + desc + "】,ActivityTheme:【" + activityTheme + ",】startTime:【" + startTime + "】,endTime:【" + endTime + "】,isRegister:【" + isRegister + "】,redEnvelope:【" + redEnvelope+"】,:expectMsg【"+expectMsg+"】");
    }

    public static void main(String[] args) {
        List<WalkingRedEnvelopeCreat> list = ExcelUtil.load("src/test/resources/随行红包.xlsx","随行红包创建",WalkingRedEnvelopeCreat.class);
        for (WalkingRedEnvelopeCreat walkingRedEnvelopeCreat : list) {
           System.out.println(walkingRedEnvelopeCreat);
        }
    }
}
