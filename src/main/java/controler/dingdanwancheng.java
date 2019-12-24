package controler;

import basic.Admin;
import basic.setUp;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.Map;

public class dingdanwancheng {
    public static final Logger log =  Logger.getLogger(setUp.class);
    @Test
    public void ddwc() throws Exception {

        setUp driver1 = new setUp();
        WebDriver driver = driver1.setup();

        //admin登录
        log.info("admin登录");
        Admin ad = new Admin();
        ad.Login(driver, "http://192.168.1.26", "testsgq", "fan1080");

        boolean isfalse = true;
        String recorder = "没有匹配的记录";

        //待验收订单完成
        while (isfalse) {
            Map<Integer,String> rd = ad.DingDanChaKan(driver,3);
            //打印标签
            if (rd.get(1).equals(recorder)) {
                break;
            } else {
                ad.DingDanWanChengChuLi(driver, rd.get(1));
            }
        }

        //退出firefox
        driver.quit();
    }
}
