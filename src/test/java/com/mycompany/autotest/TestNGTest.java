package com.mycompany.autotest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.safari.SafariDriver;

/**
 *
 *
 * @author Nguyen Duc Thien
 */
public class TestNGTest {

    private String baseUrl;
    private boolean acceptNextAlert = true;
    private final StringBuffer verificationErrors = new StringBuffer();
    private WebDriver driver;
    private ExtentReports extend;
    private ExtentTest test;
    private String error;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(String browser) throws Exception {
        extend = Selenium_report.Instance();
        if (browser.equalsIgnoreCase("firefox")) {
            test = extend.startTest("test " + browser);
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("chrome")) {
            test = extend.startTest("test " + browser);
            System.setProperty("webdriver.chrome.driver", "/chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else {
            test = extend.startTest("test " + browser);
            driver = new SafariDriver();
            driver.manage().window().maximize();
        }
        baseUrl = "http://google.com.vn/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testAuto() throws Exception {
        driver.get(baseUrl + "/?gws_rd=ssl");
        try {
            assertEquals(driver.getTitle(), "Google");
            test.log(LogStatus.PASS, "Check title google", "Title is " + driver.getTitle());
        } catch (Exception e) {
            test.log(LogStatus.ERROR, "Check title google", e.getMessage());
        } catch (Error e) {
            verificationErrors.append(e.toString());
            test.log(LogStatus.FAIL, "Check title google", e.toString() + "like below" + test.addScreenCapture(Selenium_report.CaptureScreen(driver, String.valueOf(System.currentTimeMillis()))));
        }
        driver.findElement(By.id("lst-ib")).clear();
        driver.findElement(By.id("lst-ib")).sendKeys("selenium tutorial");
        driver.findElement(By.name("btnG")).click();
        for (int second = 0;; second++) {
            if (second >= 5) {
                if (error == null) {
                    test.log(LogStatus.FAIL, "Type 'selenium tutorial' and search", "FAIL" + "like below" + test.addScreenCapture(Selenium_report.CaptureScreen(driver, String.valueOf(System.currentTimeMillis()))));
                    break;
                } else {
                    test.log(LogStatus.ERROR, "Type 'selenium tutorial' and search", error);
                    error = null;
                    break;
                }
            }
            try {
                if ("selenium tutorial - Tìm với Google".equals(driver.getTitle())) {
                    test.log(LogStatus.PASS, "Type 'selenium tutorial' and search", "Search selenium tutorial success");
                    break;
                }
            } catch (Exception e) {
                error = e.getMessage();
            }
            Thread.sleep(1000);
        }

        try {
            assertTrue(isElementPresent(By.linkText("Selenium Tutorial - TutorialsPoint")));
            test.log(LogStatus.PASS, "Verify search result", "Found Selenium Tutorial - TutorialsPoint");
        } catch (Error e) {
            verificationErrors.append(e.toString());
            test.log(LogStatus.FAIL, "Verify search result", "Not found link Selenium Tutorial - TutorialsPoints likes below" + test.addScreenCapture(Selenium_report.CaptureElementScreen(driver, driver.findElement(By.linkText("Selenium Tutorial - TutorialsPoint")), String.valueOf(System.currentTimeMillis()))));
        }
        driver.findElement(By.linkText("Selenium Tutorial - TutorialsPoint")).click();
        Thread.sleep(3000);

        try {
            assertEquals(driver.getTitle(), "Selenium Tutorial");
            test.log(LogStatus.PASS, "Go to result website", "Success");
        } catch (Exception e) {
            test.log(LogStatus.ERROR, "Go to result website", e.getMessage());
        } catch (Error e) {
            verificationErrors.append(e.toString());
            test.log(LogStatus.FAIL, "Go to result website", e.toString() + " like below" + test.addScreenCapture(Selenium_report.CaptureScreen(driver, String.valueOf(System.currentTimeMillis()))));
        }

        try {
            assertEquals(driver.findElement(By.linkText("Selenium - Home")).getText(), "Selenium - Home");
            test.log(LogStatus.PASS, "Check Selenium - Home menu", "OK");
        } catch (Exception e) {
            verificationErrors.append(e.getMessage());
            test.log(LogStatus.ERROR, "Check Selenium - Home menu", e.getMessage());
        } catch (Error e) {
            verificationErrors.append(e.toString());
            test.log(LogStatus.FAIL, "Check Selenium - Home menu", e.toString() + " like below" + test.addScreenCapture(Selenium_report.CaptureScreen(driver, String.valueOf(System.currentTimeMillis()))));
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {

//end test
        extend.endTest(test);
//Write result to .html
        extend.flush();
//Close file .html
        extend.close();
//Quit driver, close browse
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

//    private String baseUrl;
//    private boolean acceptNextAlert = true;
//    private final StringBuffer verificationErrors = new StringBuffer();
//    private WebDriver driver;
//    private ExtentReports extend;
//    private ExtentTest test;
//    private String error;
//
//    @Parameters({"browser"})
//    @BeforeClass(alwaysRun = true)
//    public void setUp(String browser) throws Exception {
//        extend = Selenium_report.Instance();
//        if (browser.equalsIgnoreCase("firefox")) {
//            test = extend.startTest("test " + browser);
//            System.out.println("firefox");
//            driver = new FirefoxDriver();
//            driver.manage().window().maximize();
//        } else if (browser.equalsIgnoreCase("chrome")) {
//            test = extend.startTest("test " + browser);
//            System.out.println("chrome");
//            System.setProperty("webdriver.chrome.driver", "/chromedriver");
//            driver = new ChromeDriver();
//            driver.manage().window().maximize();
//        } else {
//            test = extend.startTest("test " + browser);
//            System.out.println("Safari");
//            driver = new SafariDriver();           
//            driver.manage().window().maximize();
//        }
//        baseUrl = "http://localhost:8080/";
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//    }
//
//    @Test
//    public void testAuto() throws Exception {
//        driver.get(baseUrl + "faber-advertiser/");
//        System.out.println("Login");
//        driver.findElement(By.id("txt_password")).clear();
//        driver.findElement(By.id("txt_password")).sendKeys("a");
//        driver.findElement(By.id("txt_email_login")).clear();
//        driver.findElement(By.id("txt_email_login")).sendKeys("thien.advertiser@gmail.com");
//        driver.findElement(By.xpath("//button[@type='submit']")).click();
//        Thread.sleep(4000);
//        System.out.println("scan dashboard");
//        try {
//            assertEquals(driver.findElement(By.xpath("//div[2]/div/div/span")).getText(), "KẾT QUẢ CHIẾN DỊCH123");
//            test.log(LogStatus.PASS, "Check KẾT QUẢ CHIẾN DỊCH");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//            test.log(LogStatus.FAIL, "check KẾT QUẢ CHIẾN DỊCH", e.toString() + "like below" + test.addScreenCapture(Selenium_report.CaptureScreen(driver, String.valueOf(System.currentTimeMillis()))));
//        }
//        try {
//            assertEquals(driver.findElement(By.xpath("//div[3]/div/div/span")).getText(), "GIÁ");
//            test.log(LogStatus.PASS, "Check GIÁ");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//            test.log(LogStatus.FAIL, e.toString());
//        }
//        driver.findElement(By.linkText("Chiến dịch")).click();
//        System.out.println("scan campaign");
//
//        Thread.sleep(2000);
//        for (int second = 0;; second++) {
//            if (second >= 1) {
//                if (error != null) {
//                    test.log(LogStatus.ERROR, "Can't find CP-645390271", error);
//                    error = null;
//                } else {
//                    test.log(LogStatus.FAIL, driver.findElement(By.linkText("CP-645390271")).getText() + " not equal CP-645390271");
//                }
//                break;
//
//            }
//            try {
//                if ("CP-64539027".equals(driver.findElement(By.linkText("CP-64539027")).getText())) {
//                    test.log(LogStatus.PASS, "Find CP-645390271");
//                    break;
//                }
//            } catch (Exception e) {
//                error = e.getMessage();
//            }
//            Thread.sleep(1000);
//        }
//
//        try {
//            assertEquals(driver.findElement(By.linkText("CP-786913367")).getText(), "CP-786913367");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
//        driver.findElement(By.linkText("Kiểm tra mua hàng")).click();
//        System.out.println("Check aquisition");
//        Thread.sleep(2000);
//        for (int second = 0;; second++) {
//            if (second >= 10) {
//                fail("timeout");
//            }
//            try {
//                if ("Không tìm thấy dòng nào phù hợp".equals(driver.findElement(By.cssSelector("td.dataTables_empty")).getText())) {
//                    break;
//                }
//            } catch (Exception e) {
//            }
//            Thread.sleep(1000);
//        }
//
//        driver.findElement(By.xpath("//li[6]/a/span")).click();
//        System.out.println("check report");
//        Thread.sleep(2000);
//        driver.findElement(By.id("drp_autogen0")).click();
//        driver.findElement(By.xpath("(/html/body/div[8]/div[1]/div[1]/div/div[2]/table/tbody/tr[1]/td[6]/a)")).click();
//        driver.findElement(By.xpath("(/html/body/div[8]/div[1]/div[1]/div/div[2]/table/tbody/tr[6]/td[1]/a)")).click();
//        driver.findElement(By.xpath("(/html/body/div[8]/div[2]/div/button[1])")).click();
//        new Select(driver.findElement(By.xpath("//*[@id=\"cbo_campaign_type\"]"))).selectByVisibleText("All");
//        try {
//            assertEquals(driver.findElement(By.linkText("CP-435846415")).getText(), "CP-435846415");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
//        for (int second = 0;; second++) {
//            if (second >= 10) {
//                fail("timeout");
//            }
//            try {
//                if ("CP-200605663".equals(driver.findElement(By.linkText("CP-200605663")).getText())) {
//                    break;
//                }
//            } catch (Exception e) {
//            }
//            Thread.sleep(1000);
//        }
//
//        try {
//            assertEquals(driver.findElement(By.linkText("CP-948952808")).getText(), "CP-948952808");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
//        new Select(driver.findElement(By.xpath("//*[@id=\"cbo_campaign_type\"]"))).selectByVisibleText("CPA");
//        System.out.println("check CPA");
//        Thread.sleep(1000);
//        for (int second = 0;; second++) {
//            if (second >= 10) {
//                fail("timeout");
//            }
//            try {
//                if ("Lượt tương tác".equals(driver.findElement(By.cssSelector("tspan")).getText())) {
//                    break;
//                }
//            } catch (Exception e) {
//            }
//            Thread.sleep(1000);
//        }
//
//        try {
//            assertEquals(driver.findElement(By.linkText("CP-645390271")).getText(), "CP-645390271");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
//        try {
//            assertEquals(driver.findElement(By.linkText("CP-786913367")).getText(), "CP-786913367");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
//        new Select(driver.findElement(By.xpath("//*[@id=\"cbo_campaign_type\"]"))).selectByVisibleText("CPI");
//        System.out.println("check CPI");
//        Thread.sleep(1000);
//        for (int second = 0;; second++) {
//            if (second >= 10) {
//                fail("timeout");
//            }
//            try {
//                if ("Lượt xem".equals(driver.findElement(By.cssSelector("tspan")).getText())) {
//                    break;
//                }
//            } catch (Exception e) {
//            }
//            Thread.sleep(1000);
//        }
//
//        try {
//            assertEquals(driver.findElement(By.linkText("CP-200605663")).getText(), "CP-200605663");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
//        try {
//            assertEquals(driver.findElement(By.linkText("CP-948952808")).getText(), "CP-948952808");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
//        new Select(driver.findElement(By.xpath("//*[@id=\"cbo_campaign_type\"]"))).selectByVisibleText("CPC");
//        System.out.println("check CPC");
//        Thread.sleep(1000);
//        for (int second = 0;; second++) {
//            if (second >= 10) {
//                fail("timeout");
//            }
//            try {
//                if ("Lượt nhấp chuột".equals(driver.findElement(By.cssSelector("tspan")).getText())) {
//                    break;
//                }
//            } catch (Exception e) {
//            }
//            Thread.sleep(1000);
//        }
//
//        try {
//            assertEquals(driver.findElement(By.linkText("CP-435846415")).getText(), "CP-435846415");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
//        try {
//            assertEquals(driver.findElement(By.linkText("CP-925247015")).getText(), "CP-925247015");
//        } catch (Error e) {
//            verificationErrors.append(e.toString());
//        }
//        driver.findElement(By.xpath("//li[7]/a/span")).click();
//        Thread.sleep(1000);
//        for (int second = 0;; second++) {
//            if (second >= 10) {
//                fail("timeout");
//            }
//            try {
//                if ("Lượt xem & Nhấp chuột".equals(driver.findElement(By.cssSelector("tspan")).getText())) {
//                    break;
//                }
//            } catch (Exception e) {
//            }
//            Thread.sleep(1000);
//        }
//        System.out.println("Sign out");
//        driver.findElement(By.cssSelector("i.icon-login")).click();
//
//    }
//
//    @AfterClass(alwaysRun = true)
//    public void tearDown() throws Exception {
//
//        extend.endTest(test);
//        extend.flush();
//        extend.close();
//        driver.quit();
//        String verificationErrorString = verificationErrors.toString();
//        if (!"".equals(verificationErrorString)) {
//            fail(verificationErrorString);
//        }
//    }
//
//    private boolean isElementPresent(By by) {
//        try {
//            driver.findElement(by);
//            return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }
//
//    private boolean isAlertPresent() {
//        try {
//            driver.switchTo().alert();
//            return true;
//        } catch (NoAlertPresentException e) {
//            return false;
//        }
//    }
//
//    private String closeAlertAndGetItsText() {
//        try {
//            Alert alert = driver.switchTo().alert();
//            String alertText = alert.getText();
//            if (acceptNextAlert) {
//                alert.accept();
//            } else {
//                alert.dismiss();
//            }
//            return alertText;
//        } finally {
//            acceptNextAlert = true;
//        }
//    }
}
