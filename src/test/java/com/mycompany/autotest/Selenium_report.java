package com.mycompany.autotest;

import static com.relevantcodes.extentreports.DisplayOrder.NEWEST_FIRST;
import com.relevantcodes.extentreports.ExtentReports;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author ORO-MAC032
 */
public class Selenium_report {

    public static ExtentReports Instance() {
        ExtentReports extent;
        String Path = "./ExtentReport.html";
        extent = new ExtentReports(Path, true, NEWEST_FIRST);
        extent.config().documentTitle("Automation Report").reportName("Regression");

        return extent;
    }

    public static String CaptureScreen(WebDriver driver, String ImagesPath) {
        TakesScreenshot oScn = (TakesScreenshot) driver;
        File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
        File oDest = new File(ImagesPath + ".jpg");
        try {
            FileUtils.copyFile(oScnShot, oDest);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath + ".jpg";
    }
}
