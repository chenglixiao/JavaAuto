package com.auto.test.util;

import com.auto.test.pojo.ApiInfo;
import com.auto.test.pojo.CaseInfo;
import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author：Pada_xiao
 * @ Date：15:11 2019/1/15
 * @ Description：从已封装好的list集合读取特定的文件信息
 * @ Modified By：
 * @Version:
 */
public class CaseUtil {
    final  static Logger LOGGER = Logger.getLogger(CaseUtil.class);
    static List<ApiInfo> apiInfoList;
    static List<CaseInfo>caseInfoList;
    static {
        apiInfoList = ExcelUtil.packegingExcelDataToObject(PropertiesUtil.getProperty("CaseFile.Plus"),PropertiesUtil.getProperty("ApiSheetName"),ApiInfo.class);
        caseInfoList = ExcelUtil.packegingExcelDataToObject(PropertiesUtil.getProperty("CaseFile.Plus"),PropertiesUtil.getProperty("CaseSheetName"),CaseInfo.class);
    }
    /**
     * create by:Pada_xiao
     * description:根据指定的key,获取文件指定的字段值，返回Map
     * create time:14:48 2019/1/18
     * @params[key, values]
     * @returnjava.lang.String
     */
    public static Map<String,String> getApiInfo(String key , String[] cellNames){
        Class clazz = ApiInfo.class;
        Map<String,String> cellData = new HashMap<String, String>();
        List<ApiInfo> apiInforByKeyList=new ArrayList<ApiInfo>();
        for (ApiInfo apiInfo : apiInfoList) {
            if (apiInfo.getApiId().equalsIgnoreCase(key)){
                apiInforByKeyList.add(apiInfo);
            }
        }
        for (ApiInfo apiInfo : apiInforByKeyList) {
            try {
                for (String cellName : cellNames) {
                    String methodName = "get"+ cellName;
                    Method method = clazz.getMethod(methodName);
                    String value = (String) method.invoke(apiInfo);
                    cellData.put(cellName,value);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        return  cellData;
    }
    /**
     * create by:Pada_xiao
     * description:根据指定的key,获取
     * create time:14:48 2019/1/18
     * @params[key, values]
     * @returnjava.lang.Object[][]
     */
    public  static Object[][] getCaseInfo(String key,String[] cellNames){
        Class clazz = CaseInfo.class;
        List<CaseInfo> caseInfoByKeyList = new ArrayList<CaseInfo>();
        for (CaseInfo caseInfo : caseInfoList) {
            if (caseInfo.getApiId().equalsIgnoreCase(key)){
                caseInfoByKeyList.add(caseInfo);
            }
        }
        Object [][] caseInfoAr = new Object[caseInfoByKeyList.size()][cellNames.length];
        for (int i =0;i<caseInfoByKeyList.size();i++) {
            try {
                Object object =caseInfoByKeyList.get(i) ;
                for (int j=0;j<cellNames.length;j++) {
                    String methodName = "get"+ cellNames[j];
                    Method method = clazz.getMethod(methodName);
                    String value = (String) method.invoke(object);
                    caseInfoAr[i][j]=value;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  caseInfoAr;
    }
}
