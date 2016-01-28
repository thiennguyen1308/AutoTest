package com.mycompany.autotest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.testng.ITestResult;

/**
 *
 * @author Nguyen Duc Thien
 */
public class TestNGTest {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private int pass;
    private int fail;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\vagrant\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
    }

    @Test(invocationCount = 5)
    public void test() throws Exception {

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/?gws_rd=ssl");
        driver.findElement(By.id("lst-ib")).clear();
        driver.findElement(By.id("lst-ib")).sendKeys("genk.vn");
        driver.findElement(By.linkText("Trang thông tin dành cho tín đồ công nghệ | genK.vn")).click();
        driver.findElement(By.cssSelector("a[title=\"iPhone 6 nhái iPhone 6s chưa là gì, iPhone 5s hô biến thành iPhone 6s mới đáng sợ\"]")).click();
        assertEquals(driver.getTitle(), "iPhone 6 nhái iPhone 6s chưa là gì, iPhone 5s hô biến thành iPhone 6s mới đáng sợ | genK.vn");
        driver.findElement(By.linkText("Công bố kết quả quay thưởng Bàn phím cơ RK-61 trị giá...")).click();
        assertEquals(driver.getTitle(), "Công bố kết quả quay thưởng Bàn phím cơ RK-61 trị giá 1.500.000 đồng | genK.vn");
    }

    @AfterMethod
    public void writeResult(ITestResult result) {
        if (result.getStatus() == 1) {
            pass += 1;
        } else {
            fail += 1;
        }

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println(pass);
        System.out.println(fail);
        driver.quit();
    }

}
