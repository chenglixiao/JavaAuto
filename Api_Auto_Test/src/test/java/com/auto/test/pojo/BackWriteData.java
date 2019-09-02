package com.auto.test.pojo;

/**
 * @ Author：Pada_xiao
 * @ Date：10:48 2019/1/18
 * @ Description：封装回写的数据
 * @ Modified By：
 * @Version:
 */
public class BackWriteData {
    private String caseId;
    private String cellName;
    private String writeValue;
    public BackWriteData() {
    }

    public BackWriteData(String caseId, String cellName, String writeValue) {
        this.caseId = caseId;
        this.cellName = cellName;
        this.writeValue = writeValue;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getWriteValue() {
        return writeValue;
    }

    public void setWriteValue(String writeValue) {
        this.writeValue = writeValue;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return("caseId="+caseId+",cellName="+cellName+",result="+writeValue);
    }
}
