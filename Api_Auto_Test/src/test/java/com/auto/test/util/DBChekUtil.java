package com.auto.test.util;

import com.alibaba.fastjson.JSONObject;
import com.auto.test.pojo.DBCheck;
import com.auto.test.pojo.DBCheckResult;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @ Author：Pada_xiao
 * @ Date：15:46 2019/1/24
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class DBChekUtil {
    final  static Logger LOGGER = Logger.getLogger(DBChekUtil.class);
    public static String dbCheck(String string){
        //将json字符串数组转换为list集合
        List<DBCheck>  dbChecks = JSONObject.parseArray(string,DBCheck.class);
        //保存查询结果
        List<DBCheckResult> dbCheckResultLists = new ArrayList<DBCheckResult>();
        if(dbChecks!=null) {
            for (DBCheck dbCheck : dbChecks) {
                String no = dbCheck.getNo();
                String sql = dbCheck.getSql();
                List<Map> selectReslutList = JDBCUtil.selectDatabase(sql);
                //将查询出的结果封装成对象
                DBCheckResult dbCheckResult = new DBCheckResult(no, selectReslutList);
                dbCheckResultLists.add(dbCheckResult);
            }
        }
        //将list集合转换为json格式的数组并返回
        return JSONObject.toJSONString(dbCheckResultLists);
    }

    public static void main(String[] args) {
        dbCheck("[{\"no\":\"1\",\"sql\":\"SELECT nickname,phone_num FROM `general_member_info` WHERE phone_num='13508634523'\"},{\"no\":\"2\"," +
                "\"sql\":\"SELECT count(nickname) FROM `general_member_info` WHERE phone_num='13508634523'\"}]");
    }
}
