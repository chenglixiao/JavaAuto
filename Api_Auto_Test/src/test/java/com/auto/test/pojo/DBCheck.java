package com.auto.test.pojo;

/**
 * @ Author：Pada_xiao
 * @ Date：15:09 2019/1/24
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class DBCheck {
    private String no;
    private String sql;

    public DBCheck() {
    }
    public DBCheck(String no, String sql) {
        this.no = no;
        this.sql = sql;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return ("no【"+no+"】,sql【"+sql+"】");
    }
}
