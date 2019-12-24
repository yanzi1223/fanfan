package basic;
import basic.setUp.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Admin {
    private static final Logger log =  Logger.getLogger(setUp.class);
    public void Login(WebDriver driver, String url, String usrname, String password) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        //打开管理中心
        driver.get(url);

        //判断是否登录
        String name = driver.getTitle();
        if (name.equals("主页")) {
        } else {
            //输入用户名和密码
            driver.findElement(By.id("userCode")).sendKeys(usrname);
            driver.findElement(By.id("usrpwd")).sendKeys(password);
            log.info("输入用户名密码");
            //登录
            driver.findElement(By.id("btnSubmit")).click();
            log.info("点击登录");
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("welcome"))));
        }
    }

    public void DaYinBiaoQian(WebDriver driver, String storename) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        //仓库管理
        driver.findElement(By.cssSelector("li.nav-parent:nth-child(6) > a:nth-child(1)")).click();
        log.info("仓库管理");

        //标签打印
        driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/ul/li[6]/ul/li[1]/a")).click();
        log.info("打印标签");

        //输入店铺名称
        driver.findElement(By.id("storeName")).sendKeys(storename);
        log.info("输入店铺名称");

        //点击查找
        Thread.sleep(1000);
        driver.findElement(By.id("searchBtn")).click();

        //选择店铺
        driver.findElement(By.cssSelector("td.center:nth-child(2)")).click();
        log.info("选择店铺");

        //向下滚动
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.id("rows"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);


        //选择每页显示行数
        Select sl = new Select(driver.findElement(By.name("datatable_length")));
        sl.selectByIndex(3);
        log.info("每页显示100行");

        //全选
        driver.findElement(By.id("selectAll")).click();
        log.info("全选");

        //打印
        driver.findElement(By.id("btnPrintSelected")).click();
        log.info("打印");

        //确定
        driver.findElement(By.cssSelector("a.btn-sm:nth-child(1)")).click();
        Thread.sleep(500);
        log.info("确定");

        //关闭当前窗口
        String parentHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for(String handle:handles){
            if(!handle.equals(parentHandle)){
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(parentHandle);
        log.info("关闭打印窗口");

        //仓库管理
        Thread.sleep(500);
        driver.findElement(By.cssSelector("li.nav-parent:nth-child(6) > a:nth-child(1)")).click();
        log.info("仓库管理");
    }

    void ZhiFuDanYiChangWeiXin(WebDriver driver, String text) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        //系统
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement xt = driver.findElement(By.cssSelector(".fa.fa-gears"));
        js.executeScript("arguments[0].scrollIntoView(true);", xt);
        Thread.sleep(1000);
        xt.click();
        log.info("系统");

        //向下滚动
        WebElement element = driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/ul/li[20]/ul/li[16]/a"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        log.info("向下滚动");

        //订单支付异常处理
        driver.findElement(By.xpath("/html/body/section/div[1]/div[1]/ul/li[20]/ul/li[16]/a/i")).click();
        log.info("订单支付异常处理");

        //采购单号
        driver.findElement(By.id("totalOrderId")).sendKeys(text);
        log.info("输入采购单号");

        //查找
        Thread.sleep(1000);
        driver.findElement(By.id("searchBtn")).click();
        log.info("点击查找");

        //完成支付
        driver.findElement(By.id("completeOrderBtn")).click();
        log.info("点击完成支付");

        //确定
        driver.findElement(By.cssSelector("div.pop_up:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > a:nth-child(1)")).click();
        log.info("点击确定");

        //确定
        driver.findElement(By.cssSelector("a.btn-primary:nth-child(1)")).click();
        log.info("确定");

        //确定
        driver.findElement(By.cssSelector("div.pop_up:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > a:nth-child(1)")).click();
        log.info("确定");
    }

    public Map<Integer,String> DingDanChaKan(WebDriver driver, int i) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        //交易
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("preloader"))));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".fa.fa-money"))));
        driver.findElement(By.cssSelector(".fa.fa-money")).click();
        log.info("交易");

        //订单查看
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'订单查看')]")).click();
        log.info("订单查看");

        //获取当前日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        Date start = c.getTime();
        String jt = format.format(start);

        c.add(Calendar.DATE, -1);
        Date start1 = c.getTime();
        String qlt = format.format(start1);

        //选择订单状态
        Thread.sleep(1000);
        Select sl = new Select(driver.findElement(By.id("orderState")));
        sl.selectByIndex(i);
        log.info("选择订单状态");

        //输入开始时间
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("start"))));
        driver.findElement(By.id("start")).sendKeys(qlt);
        log.info("输入支付开始时间");

        //输入结束时间
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("end"))));
        driver.findElement(By.id("end")).sendKeys(jt);
        log.info("输入支付结束时间");

        //查找
        driver.findElement(By.id("storeName")).click();
        driver.findElement(By.id("searchButton")).click();
        log.info("点击查找");

        //获取采购单号
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(1)"))));
        String recorder = driver.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(1)")).getText();
        log.info("获取采购单号：" + recorder);

        //获取店铺名称
        String dianpuName;
        if (recorder.equals("没有匹配的记录")) {
            dianpuName = recorder;
        } else {
            dianpuName = driver.findElement(By.cssSelector("tr.odd:nth-child(1) > td:nth-child(3)")).getText();
        }
        log.info("店铺名称：" + dianpuName);

        //点击交易
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("preloader"))));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".fa.fa-money"))));
        driver.findElement(By.cssSelector(".fa.fa-money")).click();
        log.info("点击交易");

        //返回查询结果
        Map mp = new HashMap<Integer, String>();
        mp.put(1, recorder);
        mp.put(2, dianpuName);
        return mp;
    }

    public void DingDanWanChengChuLi(WebDriver driver, String recorder) throws Exception {

        //点击交易
        driver.findElement(By.cssSelector("li.nav-parent:nth-child(4) > a:nth-child(1)")).click();
        log.info("交易");

        //订单完成
        driver.findElement(By.cssSelector("li.nav-parent:nth-child(4) > ul:nth-child(2) > li:nth-child(9) > a:nth-child(1)")).click();
        log.info("订单完成");

        //输入订单号
        driver.findElement(By.id("cgdNo")).sendKeys(recorder);
        log.info("输入订单号");

        //查找
        Thread.sleep(1000);
        driver.findElement(By.id("searchButton")).click();
        log.info("点击查找");

        //向下滚动
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.id("completeButton"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);

        //点击完成
        driver.findElement(By.id("completeButton")).click();
        log.info("点击完成");

        //确定
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("a.btn:nth-child(1)")).click();
        log.info("点击确定");

        //交易
        driver.findElement(By.cssSelector("li.nav-parent:nth-child(4) > a:nth-child(1)")).click();
        log.info("点击确定");
    }
}

