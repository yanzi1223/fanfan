package controler;
import basic.Admin;
import basic.setUp;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;



public class wancheng {
    public static final Logger log = Logger.getLogger(setUp.class);
    @Test
    public void wc() throws Exception {
        setUp driver1 = new setUp();
        WebDriver driver = driver1.setup();

        //admin登录
        log.info("admin登录");
        Admin wc1 = new Admin();
        wc1.Login(driver, "http://192.168.1.26", "testsgq", "fan1080");

        //点击交易
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement xt = driver.findElement(By.cssSelector("li.nav-parent:nth-child(4) > a:nth-child(1) > span:nth-child(2)"));
        js.executeScript("arguments[0].click();", xt);
        //driver.findElement(By.cssSelector("li.nav-parent:nth-child(4) > a:nth-child(1)")).click();
        log.info("交易");
        Thread.sleep(1000);
        //订单完成
        driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/ul/li[4]/ul/li[9]/a")).click();
        log.info("订单完成");
        Thread.sleep(1000);
        //批量完成
        driver.findElement(By.id("batchButton")).click();
         log.info("批量订单完成");

        driver.quit();
    }
    }
