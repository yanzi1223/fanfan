package basic;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public  class setUp {
    private static final Logger log =  Logger.getLogger(setUp.class);
    public WebDriver setup() {
        //指定firefox驱动的地址
        System.setProperty("webdriver.gecko.driver","geckodriver.exe");

        //实例化一个WebDriver的对象
        log.info("打开浏览器");
        FirefoxOptions option = new FirefoxOptions();
        option.addArguments("--headless");
        WebDriver driver = new FirefoxDriver();

        //最大化浏览器窗口
        log.info("窗口最大化");
        driver.manage().window().maximize();

        //设置隐形等待时间
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //返回dirver
        return driver;
    }
}
