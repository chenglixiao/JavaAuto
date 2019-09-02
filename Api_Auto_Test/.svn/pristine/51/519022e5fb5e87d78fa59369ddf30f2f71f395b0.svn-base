package com.auto.test.util;
import com.auto.test.cases.TestProcess;
import com.auto.test.pojo.ApiInfo;
import com.auto.test.pojo.BackWriteData;
import com.auto.test.pojo.CaseInfo;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @ Author：Pada_xiao
 * @ Date：15:08 2019/1/15
 * @ Description：解析excel文件，封装指定对象存入list集合
 * @ Modified By：
 * @Version:
 */
public class ExcelUtil {
    final  static Logger LOGGER = Logger.getLogger(ExcelUtil.class);
    private static Map<String,Integer>  rowDataAndRowNumMapping = new HashMap<String, Integer>();
    private static Map<String,Integer>  cellNameAndCellNumMapping = new HashMap<String, Integer>();
    public static List<BackWriteData> backWriteDataList = new ArrayList<BackWriteData>();
    public static List<T> getDatasFromColumnAndCell(String filePath,int[]columns,int[]cells){
        return null;
    }
    /**
     * create by:Pada_xiao
     * description:解析excle文件数据，并将其封装成的对象，返回list
     * create time:14:37 2019/1/18
     * @params[filePath, sheetName, clazz]
     * @returnjava.util.List<T>
     */
    public static <T> List<T> packegingExcelDataToObject(String filePath,String sheetName,Class<T> clazz){
        List<T> objectList = new ArrayList<T>();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            Row titleRow = sheet.getRow(0);
            int cellNumber = titleRow.getLastCellNum();
            String [] fields = new String[cellNumber];
            for (int i = 0; i <cellNumber ; i++) {
               Cell cell =  titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
               cell.setCellType(CellType.STRING);
               String cellValue = cell.getStringCellValue();
               cellValue=cellValue.substring(0,cellValue.indexOf("("));
               fields[i]=cellValue;
            }
            int dataRowNumber = sheet.getLastRowNum();
            for (int i = 1; i <=dataRowNumber; i++) {
                Row dataRow = sheet.getRow(i);
               if (isNotEmpty(dataRow)) {
                   T object = clazz.newInstance();
                   for (int j = 0; j < cellNumber; j++) {
                       Cell dataCell = dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                       dataCell.setCellType(CellType.STRING);
                       String cellValue =  dataCell.getStringCellValue();
                       String methodName = "set"+fields[j];
                       Method method = clazz.getMethod(methodName,String.class);
                       method.invoke(object,cellValue);
                   }
                   objectList.add(object);
               }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if (inputStream!=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  objectList;
    }
    /**
     * create by:Pada_xiao
     * description:解析excel文件，根据指定的列号，确定行列映射关系
     * create time:9:50 2019/1/18
     * @params[filePath, SheetName, cellName]
     * @returnvoid
     */
    public static void loadRowAndCellMapping(String filePath,String sheetName,String cellName){
        InputStream inputStream =null;
        try {
            inputStream = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            Row titleRow = sheet.getRow(0);
            int cellNumber = titleRow.getLastCellNum();
            if(isNotEmpty(titleRow)) {
                for (int i = 0; i < cellNumber; i++) {
                    Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String cellValue = cell.getStringCellValue().substring(0,cell.getStringCellValue().indexOf("("));
                    int cellnum = cell.getAddress().getColumn();
                    cellNameAndCellNumMapping.put(cellValue,i);
                }
            }
            int rowNumber = sheet.getLastRowNum();
            for (int i = 1; i <=rowNumber ; i++) {
                Row dataRow = sheet.getRow(i);
                if (isNotEmpty(dataRow)){
                    int cellNameNumber = cellNameAndCellNumMapping.get(cellName);
                    Cell cell = dataRow.getCell(cellNameNumber, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String cellValue = cell.getStringCellValue();
                    int rowNum = dataRow.getRowNum();
                    rowDataAndRowNumMapping.put(cellValue,i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * create by:Pada_xiao
     * description:判断行对象是否为空，只要有一列不为null或者不为空，则返回true
     * create time:10:13 2019/1/18
     * @params[row]
     * @returnboolean
     */
    private static boolean isNotEmpty(Row row){

        int cellNum = row.getLastCellNum();
        for (int i = 0; i <cellNum ; i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            if(cell!=null&&cell.getStringCellValue().trim().length()>0){
                return true;
            }
        }
        return false;
    }
    /**
     * create by:Pada_xiao
     * description:向指定文件、指定表单批量写入数据
     * create time:14:36 2019/1/18
     * @params[filePath, sheetName]
     * @returnvoid
     */
    public static  void  batchWriteBackDatas(String filePath,String sheetName){
        loadRowAndCellMapping(filePath,sheetName,"CaseId");
        InputStream inputStream= null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);
            for (BackWriteData backWriteData : backWriteDataList) {
                int rowNumber= rowDataAndRowNumMapping.get(backWriteData.getCaseId());
                int cellNumber = cellNameAndCellNumMapping.get(backWriteData.getCellName());
                Row row = sheet.getRow(rowNumber);
                Cell cell = row.getCell(cellNumber, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(backWriteData.getWriteValue());
            }
            outputStream = new FileOutputStream(new File(filePath));
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
                try {
                    if(inputStream!=null) {
                        inputStream.close();
                    }
                    if (outputStream!=null){
                            outputStream.close();
                        }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    public static void main(String[] args) {
        List<ApiInfo> apiInfos = new ArrayList<ApiInfo>();
       apiInfos = packegingExcelDataToObject("src/test/resources/Api_Test_Cases_Plus.xlsx","ApiInfo", ApiInfo.class);
        for (ApiInfo apiInfo : apiInfos) {
            System.out.println(apiInfo);
        }
      /* loadRowAndCellMapping("src/test/resources/Api_Test_Cases.xlsx","CaseInfo","CaseId");
       Set<String> keyValues = rowDataAndRowNumMapping.keySet();
        for (String keyValue : keyValues) {
            System.out.println(keyValue);
           System.out.println( rowDataAndRowNumMapping.get(keyValue));
        }
        Set<String> keyValues1=cellNameAndCellNumMapping.keySet();
        for (String s : keyValues1) {
            System.out.println(s);
            System.out.println(cellNameAndCellNumMapping.get(s));
        }*/
    }
}
