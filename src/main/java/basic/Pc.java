package basic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class Pc {
    private static final Log log = LogFactory.getLog(setUp.class);

    public void Login(WebDriver driver, String url, String usrname, String usrpass) throws Exception {

        //打开直采PC
        driver.get(url);


        //判断是否已经登录
        boolean name = driver.findElement(By.cssSelector(".userName")).isDisplayed();
        if (name) {
            driver.findElement(By.cssSelector(".header_nav_quit > a:nth-child(2)")).click();
        } else {
        }

        //点击“采购商登录” 执行搜索
        driver.findElement(By.className("rg_login")).click();

        //输入用户名和密码
        driver.findElement(By.id("userName")).sendKeys(usrname);
        driver.findElement(By.id("password1")).sendKeys(usrpass);

        //点击登录
        driver.findElement(By.id("btnSubmit")).click();

    }

    Map GouMaiWeiXin(WebDriver driver, String good) throws Exception {

        //查找商品
        driver.findElement(By.cssSelector("div.search_input_box_out div.search_input_box_in.input-txtbox input.search_input.txt-input")).sendKeys(good);
        log.info("输入商品名称");

        driver.findElement(By.cssSelector("input.search_button:nth-child(3)")).click();
        log.info("点击查找");

        //点击商品
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[1]/a/img")).click();
        log.info("点击商品");

        //获取店铺名称
        String storename = driver.findElement(By.cssSelector(".not_weight")).getText();
        log.info("店铺名称:" + storename);

        //输入数量
        String count = String.valueOf((int) (Math.random() * 10) + 1);
        driver.findElement(By.cssSelector("#j_ipt1")).sendKeys(count);
        Thread.sleep(500);
        log.info("购买数量:" + count);

        //点击立即购买
        driver.findElement(By.cssSelector(".jbtn3")).click();
        Thread.sleep(500);
        log.info("点击立即购买");

        //向下滚动
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement xt = driver.findElement(By.cssSelector(".footer_icon_3"));
        js.executeScript("arguments[0].scrollIntoView(false);", xt);
        log.info("向下滚动");

        //点击提交订单
        Thread.sleep(3000);
        driver.findElement(By.className("big-button")).click();
        log.info("提交订单");

        //点击确定
        driver.findElement(By.cssSelector("div.pop_up_button:nth-child(2) > a:nth-child(1)")).click();
        log.info("确定");

        //获取采购单号
        String recorder = driver.findElement(By.cssSelector(".totalOrderId")).getText();
        log.info("采购单号:" + recorder);

        //返回map
        Map gm = new HashMap<String, String>();
        gm.put("storename", storename);
        gm.put("recorder", recorder);
        return gm;
    }

    Map GouMaiYuE(WebDriver driver, String good) throws Exception {

        //查找商品
        driver.findElement(By.cssSelector("div.search_input_box_out div.search_input_box_in.input-txtbox input.search_input.txt-input")).sendKeys(good);
        log.info("输入商品名称");

        driver.findElement(By.cssSelector("input.search_button:nth-child(3)")).click();
        log.info("点击查找");

        //点击商品
        driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div[2]/div/div[2]/div/div/div[1]/a/img")).click();
        log.info("点击商品");

        //获取店铺名称
        String storename = driver.findElement(By.cssSelector(".not_weight")).getText();
        log.info("店铺名称:" + storename);

        //输入数量
        String count = String.valueOf((int) (Math.random() * 10) + 1);
        driver.findElement(By.cssSelector("#j_ipt1")).sendKeys(count);
        Thread.sleep(500);
        log.info("购买数量:" + count);

        //点击立即购买
        driver.findElement(By.cssSelector(".jbtn3")).click();
        Thread.sleep(500);
        log.info("立即购买");

        //向下滚动
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement xt = driver.findElement(By.cssSelector(".footer_icon_3"));
        js.executeScript("arguments[0].scrollIntoView(false);", xt);
        log.info("向下滚动");

        //点击提交订单
        Thread.sleep(3000);
        try {
            driver.findElement(By.className("big-button")).click();
        } catch (Exception e) {
            driver.close();
            throw e;
        }
        log.info("提交订单");

        //点击确定
        driver.findElement(By.cssSelector("div.pop_up_button:nth-child(2) > a:nth-child(1)")).click();
        log.info("确定");

        //获取采购单号
        String recorder = driver.findElement(By.cssSelector(".totalOrderId")).getText();
        log.info("采购单号:" + recorder);

        //选择余额支付
        driver.findElement(By.cssSelector(".textalign-m > div:nth-child(3) > input:nth-child(1)")).click();
        log.info("选择余额支付");

        //点击立即支付
        driver.findElement(By.cssSelector(".big-button")).click();
        log.info("点击立即支付");

        //判断支付是否成功
        String title = driver.findElement(By.cssSelector(".payment-title")).getText();
        log.info(title);
        Assert.assertEquals(title, "支付成功！");

        //返回map
        Map<String, String> gm = new HashMap();
        gm.put("storename", storename);
        gm.put("recorder", recorder);
        return gm;
    }
}
