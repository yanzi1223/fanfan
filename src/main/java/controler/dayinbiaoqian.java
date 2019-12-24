package controler;

import basic.Admin;
import basic.setUp;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.Map;

public class dayinbiaoqian {
    public static final Logger log = Logger.getLogger(setUp.class);

    @Test
    public void dybq() throws Exception {

        setUp driver1 = new setUp();
        WebDriver driver = driver1.setup();

        //admin登录
        log.info("admin登录");
        Admin ad = new Admin();
        ad.Login(driver, "http://192.168.1.26", "testsgq", "fan1080");

        boolean isfalse = true;
        String recorder = "没有匹配的记录";

        //待备货打印标签
        while (isfalse) {
            Map<Integer, String> rd = ad.DingDanChaKan(driver, 2);
            //打印标签
            if (rd.get(2).equals(recorder)) {
                break;
            } else {
                ad.DaYinBiaoQian(driver, rd.get(2));
            }
        }

        //退出firefox
        driver.quit();
    }
}
