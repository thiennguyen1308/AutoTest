package com.mycompany.autotest;

import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

/**
 * s
 *
 * @author Nguyen Duc Thien
 */
public class TestNGTest {

    private String baseUrl;
    private boolean acceptNextAlert = true;
    private final StringBuffer verificationErrors = new StringBuffer();
    private final Assertion hardAssert = new Assertion();
    private final SoftAssert softAssert = new SoftAssert();
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(String browser) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {
            System.out.println("firefox");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.out.println("chrome");
            System.setProperty("webdriver.chrome.driver", "/chromedriver");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testAuto() throws Exception {
        driver.get(baseUrl + "faber-advertiser/");
        driver.findElement(By.id("txt_password")).clear();
        driver.findElement(By.id("txt_password")).sendKeys("a");
        driver.findElement(By.id("txt_email_login")).clear();
        driver.findElement(By.id("txt_email_login")).sendKeys("thien.advertiser@gmail.com");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(4000);
        try {
            assertEquals(driver.findElement(By.xpath("//div[2]/div/div/span")).getText(), "KẾT QUẢ CHIẾN DỊCH");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals(driver.findElement(By.xpath("//div[3]/div/div/span")).getText(), "GIÁ");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.linkText("Chiến dịch")).click();
        Thread.sleep(4000);
        for (int second = 0;; second++) {
            if (second >= 10) {
                fail("timeout");
            }
            try {
                if ("CP-645390271".equals(driver.findElement(By.linkText("CP-645390271")).getText())) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(4000);
        }

        try {
            assertEquals(driver.findElement(By.linkText("CP-786913367")).getText(), "CP-786913367");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.linkText("Kiểm tra mua hàng")).click();
        Thread.sleep(4000);
        for (int second = 0;; second++) {
            if (second >= 10) {
                fail("timeout");
            }
            try {
                if ("Không tìm thấy dòng nào phù hợp".equals(driver.findElement(By.cssSelector("td.dataTables_empty")).getText())) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(4000);
        }

        driver.findElement(By.xpath("//li[6]/a/span")).click();
        Thread.sleep(4000);
        driver.findElement(By.id("drp_autogen0")).click();
        driver.findElement(By.xpath("(/html/body/div[8]/div[1]/div[1]/div/div[2]/table/tbody/tr[1]/td[6]/a)")).click();
        driver.findElement(By.xpath("(/html/body/div[8]/div[1]/div[1]/div/div[2]/table/tbody/tr[6]/td[1]/a)")).click();
        driver.findElement(By.xpath("(/html/body/div[8]/div[2]/div/button[1])")).click();
        new Select(driver.findElement(By.xpath("//*[@id=\"cbo_campaign_type\"]"))).selectByVisibleText("All");
        try {
            assertEquals(driver.findElement(By.linkText("CP-435846415")).getText(), "CP-435846415");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        for (int second = 0;; second++) {
            if (second >= 10) {
                fail("timeout");
            }
            try {
                if ("CP-200605663".equals(driver.findElement(By.linkText("CP-200605663")).getText())) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(4000);
        }

        try {
            assertEquals(driver.findElement(By.linkText("CP-948952808")).getText(), "CP-948952808");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        new Select(driver.findElement(By.xpath("//*[@id=\"cbo_campaign_type\"]"))).selectByVisibleText("CPA");
        Thread.sleep(1000);
        for (int second = 0;; second++) {
            if (second >= 10) {
                fail("timeout");
            }
            try {
                if ("Lượt tương tác".equals(driver.findElement(By.cssSelector("tspan")).getText())) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(4000);
        }

        try {
            assertEquals(driver.findElement(By.linkText("CP-645390271")).getText(), "CP-645390271");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals(driver.findElement(By.linkText("CP-786913367")).getText(), "CP-786913367");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        new Select(driver.findElement(By.xpath("//*[@id=\"cbo_campaign_type\"]"))).selectByVisibleText("CPI");
        Thread.sleep(1000);
        for (int second = 0;; second++) {
            if (second >= 10) {
                fail("timeout");
            }
            try {
                if ("Lượt xem".equals(driver.findElement(By.cssSelector("tspan")).getText())) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(4000);
        }

        try {
            assertEquals(driver.findElement(By.linkText("CP-200605663")).getText(), "CP-200605663");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals(driver.findElement(By.linkText("CP-948952808")).getText(), "CP-948952808");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        new Select(driver.findElement(By.xpath("//*[@id=\"cbo_campaign_type\"]"))).selectByVisibleText("CPC");
        Thread.sleep(1000);
        for (int second = 0;; second++) {
            if (second >= 10) {
                fail("timeout");
            }
            try {
                if ("Lượt nhấp chuột".equals(driver.findElement(By.cssSelector("tspan")).getText())) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(4000);
        }

        try {
            assertEquals(driver.findElement(By.linkText("CP-435846415")).getText(), "CP-435846415");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        try {
            assertEquals(driver.findElement(By.linkText("CP-925247015")).getText(), "CP-925247015");
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
        driver.findElement(By.xpath("//li[7]/a/span")).click();
        Thread.sleep(1000);
        for (int second = 0;; second++) {
            if (second >= 10) {
                fail("timeout");
            }
            try {
                if ("Lượt xem & Nhấp chuột".equals(driver.findElement(By.cssSelector("tspan")).getText())) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(4000);
        }

        driver.findElement(By.cssSelector("i.icon-login")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
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

}
