package com.web.auto.util;

import com.web.auto.pojo.Register;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author：Pada_xiao
 * @ Date：21:15 2018/11/27
 * @ Description：解析excle文件，并将其封装成对象存入集合
 * @ Modified By：
 * @Version:
 */
public class ExcelUtil {
    /**
     * @Author：Pada_xiao
     * @Description：解析excle文件，并将其封装成对象存入集合
     * @Date：21:29 2018/11/27
     * @Param：[exclePath, sheetName, class1]
     * @return：java.util.List<java.lang.Object>
     **/
    public static <T> List<T> load(String exclePath, String sheetName, Class<T> clazz) {
        // 封装case对象
        List<T> datas = new ArrayList<T>();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(exclePath));
            // 创建workbook对象
            Workbook workbook = WorkbookFactory.create(inputStream);
            // 获取表单对象
            Sheet sheet = workbook.getSheet(sheetName);
            // 获取第一行行对象
            Row titleRow = sheet.getRow(0);
            // 取出表单最后一列的列号
            int lastCellNum = titleRow.getLastCellNum();
            // 存储取出的列名
            String[] fields = new String[lastCellNum];
            // 根据最后一列的列号从第一列开始遍历所有的列
            for (int i = 0; i < lastCellNum; i++) {
                // 根据列索引遍历获取对应的列
                Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                // 设置列的类型为字符串
                cell.setCellType(CellType.STRING);
                // 获取列的值
                String title = cell.getStringCellValue();
                fields[i] = title.substring(0, title.indexOf("("));
            }
            // 获取表单最后一行数据的行索引
            int lastRowIndex = sheet.getLastRowNum();
            // 循环处理每一个数据行
            for (int i = 1; i <= lastRowIndex; i++) {
                // 为每一行准备Case对象封装取出的列的值
                T obj = clazz.newInstance();
                // 获取行对象
                Row dataRow = sheet.getRow(i);
                if (dataRow != null && isNotEmpty(dataRow)) {
                    // 根据取出的最后一行数据的列号循环取出列
                    for (int j = 0; j < lastCellNum; j++) {
                        // 获取列对象
                        Cell cell = dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        // 设置列为字符串类型
                        cell.setCellType(CellType.STRING);
                        // 获取列的值
                        String value = cell.getStringCellValue();
                        // 获取要反射的方法名
                        String methodName = ("set" + fields[j]);
                        // 获取需要反射的方法对象
                        Method method = clazz.getMethod(methodName, String.class);
                        // 完成反射调用
                        method.invoke(obj, value);
                    }
                    // 将封装完数据之后的case对象封装list集合
                    datas.add(obj);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return datas;
    }
    /**
     * @Author：Pada_xiao
     * @Description：判断传入行对象是否为空
     * @Date：21:30 2018/11/27
     * @Param：[dataRow]
     * @return：boolean
     **/
    public static boolean isNotEmpty(Row dataRow) {
        // 传进来的行对象，判断每一列的值是否为空
        int cellNumber = dataRow.getLastCellNum();
        for (int i = 0; i < cellNumber; i++) {
            Cell cell = dataRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            String value = cell.getStringCellValue();
            if (value != null & value.trim().length() > 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
       List<Register>list = load(PropertiesUtil.getValueByKey("register.path"), "register",Register.class);
        for (Register register : list) {
            System.out.println(register);
        }
    }
}
