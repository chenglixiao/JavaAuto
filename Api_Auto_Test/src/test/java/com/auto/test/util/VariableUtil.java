package com.auto.test.util;

import com.auto.test.pojo.Variable;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ Author：Pada_xiao
 * @ Date：14:12 2019/1/26
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class VariableUtil {
    final  static Logger LOGGER = Logger.getLogger(VariableUtil.class);
    static List<Variable> variableList = new ArrayList<Variable>();
    static {
        variableList = ExcelUtil.packegingExcelDataToObject(PropertiesUtil.getProperty("CaseFile.Plus"),
                "Variable", Variable.class);
    }
    public static String replaceVarible(String string) {
        for (Variable variable : variableList) {
            if (string.contains(variable.getName())) {
                if(variable.getStaticValue()==null||variable.getStaticValue().trim().length()==0){
                    List<Map> mapList = JDBCUtil.selectDatabase(variable.getDynamicValue());
                    for (Map map : mapList) {
                        Set<String> keyNames = map.keySet();
                        for (String keyName : keyNames) {
                            String keyValue = (String)map.get(keyName);
                            string = string.replace(variable.getName(),keyValue);
                            variable.setStaticValue(keyValue);
                        }
                    }
                }   
                else {
                   string = string.replace(variable.getName(),variable.getStaticValue());
                }
            }
        }
        return string;
    }

    public static void main(String[] args) {
        System.out.println(replaceVarible("{\n" +
                " \"phoneNum\": \"${IncorrectMobilephone}\",\n" +
                " \"appKey\": \"5327e39ea24f-549a-4a8d-a2d1-4ee2d78e2a24\",\n" +
                " \"headImgUrl\": \"\",\n" +
                " \"nickname\": \"\",\n" +
                " \"sex\": \"1\",\n" +
                " \"openId\": \"\",\n" +
                " \"access_token\": \" ${token}\"\n" +
                "}"));
    }
}
