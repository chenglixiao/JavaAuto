package com.web.auto.util;

import com.web.auto.pojo.BaseData;
import com.web.auto.pojo.Register;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ Author：Pada_xiao
 * @ Date：20:03 2019/3/8
 * @ Description：取出list集合中的对象及其特定的属性并存入二维数组
 * @ Modified By：
 * @Version:
 */
public class GetDatasUtil {
    /**
     * @Author：Pada_xiao
     * @Description：根据传入的flag及获取对应的用例数据
     * @Date：16:23 2019/4/9
     * @Param：[flag, cellNames, datas]
     * @return：java.lang.Object[][]
     **/
    public static Object[][] getDatasByFlag(String flag,String [] cellNames, List<?extends BaseData>datas) {
        List<Object> satisfied = datas.stream().filter(data -> data.getIsNegative().equals(flag)).collect(Collectors.toList());
       /* List<Object> satisfied = new ArrayList<Object>();
        for (BaseData baseData : datas) {
            if(flag.equals(baseData.getIsNegative())){
                satisfied.add(registerData);
            }
        }*/
        Class clazz = satisfied.get(0).getClass();
        return GetDatasUtil.getDatas( satisfied,cellNames, clazz);
    }
    /**
     * @Author：Pada_xiao
     * @Description：根据传入的列名利用反射将list集合的对象取出来并存入到二维数组
     * @Date：16:22 2019/4/9
     * @Param：[satisfied, cellNames, clazz]
     * @return：java.lang.Object[][]
     **/
    public static Object[][] getDatas(List<Object> satisfied, String[] cellNames,Class clazz) {
        Object [][] datas = new Object[satisfied.size()][cellNames.length];
        for (int i = 0; i < satisfied.size(); i++) {
            Object caseData = satisfied.get(i);
            for (int j = 0; j < cellNames.length; j++) {
                //要反射的方法名
                String methodName = "get"+cellNames[j];
                //获取到反射的方法对象
                Method method;
                try {
                    method = clazz.getMethod(methodName);
                    String value = (String) method.invoke(caseData);
                    datas[i][j] = value;
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return datas;
    }

}
