package com.auto.test.pojo;

/**
 * @ Author：Pada_xiao
 * @ Date：10:48 2019/1/18
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class ResponseData {
    private String caseId;
    private String cellName;
    private String result;
    public ResponseData() {
    }
    public ResponseData(String caseId, String cellName,String result) {
        super();
        this.caseId = caseId;
        this.cellName = cellName;
        this.result = result;
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
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return("caseId="+caseId+",cellName="+cellName+",result="+result);
    }
}
