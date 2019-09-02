package com.web.auto.report;

import com.web.auto.util.ScreenshotUtil;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @ Author：Pada_xiao
 * @ Date：10:38 2019/3/10
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class CustomListener extends TestListenerAdapter {
    @Override
    /**
     * @Author：Pada_xiao
     * @Description：复写onTestFailure方法，将用例失败图片地址添加到reportng log中
     * @Date：13:05 2019/3/11
     * @Param：[tr]
     * @return：void
     **/
    public void onTestFailure(ITestResult tr) {
        String baseDir = "target\\surefire-reports";
        String screenshotDir = "screenshot";
        //获取测试的上下文--》获取当前正在执行的test测试集--》获取测试集的name值
        String testNameDir = tr.getTestContext().getCurrentXmlTest().getName();
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String dateDir = sdf.format(nowDate);
        String fileName = nowDate.getTime()+".png";
        String filePath = baseDir+ File.separator+screenshotDir+File.separator+testNameDir
                +File.separator+dateDir+File.separator+fileName;
        File screenshot = ScreenshotUtil.svaeScreenshot(filePath);
        String toBeReplace = tr.getTestContext().getCurrentXmlTest().getParameter("DocumentRoot");
        String replacement = tr.getTestContext().getCurrentXmlTest().getParameter("host");
        String imgString = getImgString(toBeReplace,replacement,screenshot);
        //将图片地址添加到log中
        Reporter.log(imgString);
    }
    /**
     * @Author：Pada_xiao
     * @Description：编写html代码，展示图片
     * @Date：13:11 2019/3/11
     * @Param：[toBeReplace, replacement, file]
     * @return：java.lang.String
     **/
    private static String getImgString(String toBeReplace,String replacement,File file){
        //获取文件的绝对地址
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath+toBeReplace+replacement);
        String accessPath = absolutePath.replaceAll(toBeReplace,replacement);
        System.out.println(accessPath);
        String img = "<img src='"+accessPath+"' height='100' width='100'><a href='"+accessPath+"' target='_blank'>点击查看大图</a></img>";
        return img;
    }
}
