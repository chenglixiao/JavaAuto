package com.auto.test.pojo;

import java.util.List;
import java.util.Map;

/**
 * @ Author：Pada_xiao
 * @ Date：15:10 2019/1/24
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class DBCheckResult {
    private String no;
    private List<Map> columnLableAndValue;

    public DBCheckResult() {
    }
    public DBCheckResult(String no, List<Map> columnLableAndValue) {
        this.no = no;
        this.columnLableAndValue = columnLableAndValue;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public List<Map> getColumnLableAndValue() {
        return columnLableAndValue;
    }

    public void setColumnLableAndValue(List<Map> columnLableAndValue) {
        this.columnLableAndValue = columnLableAndValue;
    }

    @Override
    public String toString() {
        return ("no【"+no+"】，columnLableAndValue【"+columnLableAndValue+"】");
    }
}
