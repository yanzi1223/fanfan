package controler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class test {

    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.gecko.driver", "D:\\code\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.quit();
    }
}
