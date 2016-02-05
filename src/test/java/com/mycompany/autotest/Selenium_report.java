package com.mycompany.autotest;

import static com.relevantcodes.extentreports.DisplayOrder.NEWEST_FIRST;
import com.relevantcodes.extentreports.ExtentReports;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Nguyen Duc Thien
 */
public class Selenium_report {

    public static ExtentReports Instance() {
        ExtentReports extent;
//init report.html
        String Path = "./test_result/ExtentReport.html";
        extent = new ExtentReports(Path, false, NEWEST_FIRST);
        extent.config().documentTitle("Automation Report").reportName("Regression");

        return extent;
    }
//Highlight element and capture ScreenShot 

    public static String CaptureElementScreen(WebDriver driver, WebElement ele, String ImagesName) throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", ele, "color: red; border: 2px solid black;");
        return CaptureScreen(driver, ImagesName);
    }
// Capture Full ScreenShot 

    public static String CaptureScreen(WebDriver driver, String ImagesName) {
        TakesScreenshot oScn = (TakesScreenshot) driver;
        File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
        File oDest = new File("./test_result/snapshot/" + ImagesName + ".png");
        try {
            FileUtils.copyFile(oScnShot, oDest);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "./snapshot/" + ImagesName + ".png";
    }

}
