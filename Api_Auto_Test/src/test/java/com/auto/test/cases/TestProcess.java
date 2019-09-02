package com.auto.test.cases;

import com.alibaba.fastjson.JSONObject;
import com.auto.test.pojo.BackWriteData;
import com.auto.test.util.*;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ Author：Pada_xiao
 * @ Date：9:19 2019/1/17
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class TestProcess {
    final  static Logger LOGGER = Logger.getLogger(TestProcess.class);
    String[] cellNames = new String[]{"ApiId","CaseId","Params","PreValidataSql","AfterValidataSql"};
    @Test(dataProvider = "caseDatas")
    public void testProcess(String apiId,String caseId, String params,String preValidataSql,String afterValidataSql ){
        if (preValidataSql!=null&&preValidataSql.trim().length()>0) {
            LOGGER.info("替换sql语句中的形参");
            preValidataSql=VariableUtil.replaceVarible(preValidataSql);
            LOGGER.info("用例执行前数据查询，sql【" + preValidataSql + "】");
            String preVlidata = DBChekUtil.dbCheck(preValidataSql);
            LOGGER.info("用例执行前数据查询结果【"+preVlidata+"】");
            ExcelUtil.backWriteDataList.add(new BackWriteData(caseId,"PreValidataResult",preVlidata));
        }
        String [] apiInfo = {"ApiName","Type","Url"};
        Map<String,String> apiInfoMap = CaseUtil.getApiInfo(apiId,apiInfo);
        String requestType = apiInfoMap.get("Type");
        String url = apiInfoMap.get("Url");
        LOGGER.info("替换参数中的形参");
        params = VariableUtil.replaceVarible(params);
        Map<String,String> paramsMap = (Map<String, String>) JSONObject.parse(params);
        if (paramsMap.containsKey("access_token")){
            LOGGER.info("参数中的token变量替换成Map中的token value");
            HttpUtil.replaceToken(paramsMap);
        }
        LOGGER.info("调用接口：接口名称【"+ apiInfoMap.get("ApiName")+"】,类型【"+requestType+"】，Url【"+url+"】，params【"+params+"】");
        String result = HttpUtil.doServer(requestType,url,paramsMap);
        LOGGER.info("接口返回结果:"+result);
        if (afterValidataSql!=null&&afterValidataSql.trim().length()>0) {
            LOGGER.info("替换sql语句中的形参");
            afterValidataSql=VariableUtil.replaceVarible(afterValidataSql);
            LOGGER.info("用例执行后数据查询，sql【" + afterValidataSql + "】");
            String afterVlidata = DBChekUtil.dbCheck(afterValidataSql);
            LOGGER.info("用例执行后数据查询结果【"+afterVlidata+"】");
            ExcelUtil.backWriteDataList.add(new BackWriteData(caseId,"AfterValidataResult",afterVlidata));
        }
        ExcelUtil.backWriteDataList.add(new BackWriteData(caseId,"ActualResponseData",result));
}

    @AfterSuite
    /**
     * @Author：Pada_xiao
     * @Description：整个套件执行完成之后，将封装的result一次性写入文件
     * @Date：13:41 2019/1/20
     * @Param：[]
     * @return：void
     **/
    public static void writeDataToEecel(){
        LOGGER.info("向【"+PropertiesUtil.getProperty("CaseFile.Plus")+"】文件，【"+PropertiesUtil.getProperty("CaseSheetName")+"】表单批量写入数据");
        ExcelUtil.batchWriteBackDatas(PropertiesUtil.getProperty("CaseFile.Plus"),PropertiesUtil.getProperty("CaseSheetName"));
    }
}
