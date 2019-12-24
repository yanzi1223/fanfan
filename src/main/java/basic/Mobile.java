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

class Mobile {
    private static final Log log = LogFactory.getLog(setUp.class);
    void Login(WebDriver driver, String url, String usrname, String usrpass) throws Exception {

        //打开移动端
        driver.get(url);
        log.info("打开移动端");

        //点击“采购商登录”
        driver.findElement(By.cssSelector(".toLogin > img:nth-child(1)")).click();
        log.info("点击采购商登陆");

        //判断是否登录
        String title = driver.getTitle();
        if (title.equals("采购商登录")) {
        } else {
            driver.findElement(By.cssSelector("div.pop_up_A:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)")).click();
            log.info("已登录，确定");
            driver.findElement(By.cssSelector("a.footer-tab-item:nth-child(5)")).click();
            log.info("点击我的饭饭");
            //向下滚动
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(By.cssSelector(".bt_logout > a:nth-child(1)"));

            js.executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
            log.info("向下滚动");
            //退出登录
            element.click();
            log.info("点击退出登录");
            //点击“采购商登录”
            driver.findElement(By.cssSelector(".toLogin > img:nth-child(1)")).click();
            log.info("点击采购商登陆");
        }
        //输入用户名和密码
        driver.findElement(By.id("userName")).sendKeys(usrname);
        driver.findElement(By.id("password1")).sendKeys(usrpass);
        log.info("输入用户名密码");

        //点击登录
        driver.findElement(By.id("btnSubmit")).click();
        log.info("登录");
    }

    Map GouMaiWeiXin(WebDriver driver, String good) throws Exception {

        //查找商品
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".searchContent")).sendKeys(good);
        log.info("输入商品名称");

        driver.findElement(By.cssSelector(".btn > span:nth-child(1)")).click();
        log.info("点击查找");

        //点击商品
        driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div/div/div/a/img")).click();
        log.info("点击商品");

        //向下滚动
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.cssSelector(".sendTo > span:nth-child(1)"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        log.info("向下滚动");

        //向上滚动一点点
        js.executeScript("window.scrollBy(0,-190);");
        Thread.sleep(500);

        //点击数量
        driver.findElement(By.id("toChoose")).click();
        log.info("点击数量");

        //输入随机数
        String count = String.valueOf((int)(Math.random()*10)+1);
        driver.findElement(By.id("j_ipt1")).sendKeys(count);
        log.info("购买数量:"+count);

        //获取店铺名称
        WebElement element1 =driver.findElement(By.cssSelector(".shopName"));
        js.executeScript("arguments[0].scrollIntoView(true);",element1);
        String storename = driver.findElement(By.cssSelector(".shopName")).getText();
        log.info("店铺名称:"+storename);

        //点击立即购买
        driver.findElement(By.cssSelector("div.fixedBottom:nth-child(3) > div:nth-child(1) > span:nth-child(2)")).click();
        log.info("立即购买");

        //点击提交订单
        driver.findElement(By.cssSelector(".big-button")).click();
        log.info("提交订单");

        //点击确定
        driver.findElement(By.cssSelector(".aLeft")).click();
        log.info("确定");

        //获取采购单号
        String recorder = driver.findElement(By.cssSelector(".caigou-title > span:nth-child(1)")).getText();
        log.info("采购单号:"+recorder);

        //返回map
        Map gm = new HashMap<String,String>();
        gm.put("storename",storename);
        gm.put("recorder",recorder);
        return gm;
    }

    Map GouMaiYuE(WebDriver driver, String good) throws Exception {

        //查找商品
        driver.findElement(By.cssSelector(".searchContent")).sendKeys(good);
        log.info("输入商品名称");

        driver.findElement(By.cssSelector(".btn > span:nth-child(1)")).click();
        log.info("点击查找");

        //点击商品
        driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div/div/div/a/img")).click();
        log.info("点击商品");

        //向下滚动
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.cssSelector(".sendTo > span:nth-child(1)"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        log.info("向下滚动");

        //向上滚动一点点
        js.executeScript("window.scrollBy(0,-190);");
        Thread.sleep(500);

        //点击数量
        driver.findElement(By.id("toChoose")).click();
        log.info("点击数量");

        //输入随机数
        String count = String.valueOf((int)(Math.random()*10)+1);
        driver.findElement(By.id("j_ipt1")).sendKeys(count);
        log.info("购买数量:"+count);


        //获取店铺名称
        WebElement element1 =driver.findElement(By.cssSelector(".shopName"));
        js.executeScript("arguments[0].scrollIntoView(true);",element1);
        String storename = driver.findElement(By.cssSelector(".shopName")).getText();
        log.info("店铺名称"+storename);

        //点击立即购买
        driver.findElement(By.cssSelector("div.fixedBottom:nth-child(3) > div:nth-child(1) > span:nth-child(2)")).click();
        log.info("立即购买");

        //点击提交订单
        driver.findElement(By.cssSelector(".big-button")).click();
        log.info("提交订单");

        //点击确定
        driver.findElement(By.cssSelector(".aLeft")).click();
        log.info("确定");

        //获取采购单号
        String recorder = driver.findElement(By.cssSelector(".caigou-title > span:nth-child(1)")).getText();
        log.info("采购单号:"+recorder);

        //选择余额支付
        driver.findElement(By.cssSelector("#pay_balance > i:nth-child(3)")).click();
        log.info("选择余额支付");

        //点击立即支付
        driver.findElement(By.cssSelector(".big-button")).click();
        log.info("立即支付");

        //判断支付是否成功
        String title = driver.findElement(By.cssSelector(".success_h1")).getText();
        log.info(title);
        Assert.assertEquals(title, "支付成功");

        //返回map
        Map gm = new HashMap<String,String>();
        gm.put("storename",storename);
        gm.put("recorder",recorder);
        return gm;
    }
}
