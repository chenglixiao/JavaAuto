package com.auto.test.util;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JDBCUtil {
    final static Logger LOGGER = Logger.getLogger(JDBCUtil.class);
    private static Connection connection;

    static {
        String url = PropertiesUtil.getProperty("src/test/resources/JDBC.properties", "member.jdbc.url");
        String user = PropertiesUtil.getProperty("src/test/resources/JDBC.properties", "member.jdbc.username");
        String password = PropertiesUtil.getProperty("src/test/resources/JDBC.properties", "member.jdbc.password");
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * create by:Pada_xiao
     * description:查询数据库
     * create time:15:09 2019/1/25
     *
     * @params[sql, values]
     * @returnjava.util.List<java.util.Map>
     */
    public static List<Map> selectDatabase(String sql, Object... values) {

        //LOGGER.info(connection);
        // LOGGER.info(sql+values.toString());
        List<Map> selectResultList = new ArrayList<Map>();
        try {
            // LOGGER.info("准备数据库操作对象");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // LOGGER.info("给sql设置条件字段的值");
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            //LOGGER.info("执行sql语句");
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            if (!resultSet.next()) {
                Map selectResult = new HashMap<String, String>();
                selectResult.put("selectResult", "未查询到数据");
                selectResultList.add(selectResult);
            } else {
                //指针上移一位，从新读取数据
                resultSet.previous();
                while (resultSet.next()) {
                    Map<String, String> selectResult = new HashMap<String, String>();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnLable = resultSetMetaData.getColumnLabel(i);
                        String columnValue = null;
                        try {
                            columnValue = resultSet.getObject(columnLable).toString();
                        } catch (Exception e) {
                            columnValue = "null";
                        }
                        selectResult.put(columnLable, columnValue);
                    }
                    selectResultList.add(selectResult);
                    connection.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectResultList;

    }

    public static void main(String[] args) {
        String sql = "SELECT * FROM general_member_info WHERE nickname = '风吹不走的我的心'";
        //String value = "%风吹不走的33%";
        List<Map> selectResult = JDBCUtil.selectDatabase(sql);
        for (Map map : selectResult) {
            System.out.println(map);
        }
    }
}
