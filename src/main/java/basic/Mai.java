package basic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class Mai {

    private static final Log log = LogFactory.getLog(Mai.class);
    public Mai(WebDriver driver,String LoginUrl, String LoginUserName, String LoginPassWord, String GoodName, String ifpc, String ifwx, String AdminLoginUrl, String AdminLoginUserName, String AdminLoginPassWord) throws Exception {

        boolean ispc ;
        boolean iswx ;
        String text;

        //判断是否PC
        if(ifpc.equals("yes")) {
            ispc = true;
            log.info("PC端购买");
        }else{
            ispc = false;
            log.info("移动端购买");
        }

        //判断是否微信支付
        if(ifwx.equals("yes")) {
            iswx = true;
            log.info("微信支付");
        }else{
            iswx = false;
            log.info("余额支付");
        }

        if (ispc) {
            if(iswx) {
                //PC登录&购买
                Pc pc = new Pc();
                log.info("PC登录");
                pc.Login(driver, LoginUrl, LoginUserName, LoginPassWord);

                Map mp = pc.GouMaiWeiXin(driver, GoodName);
                text = (String) mp.get("recorder");

                //admin登录
                Admin ad = new Admin();
                log.info("admin登录");
                ad.Login(driver, AdminLoginUrl, AdminLoginUserName, AdminLoginPassWord);

                //订单微信支付
                ad.ZhiFuDanYiChangWeiXin(driver, text);
            }else{
                //PC登录&购买
                Pc pc = new Pc();
                log.info("PC登录");
                pc.Login(driver, LoginUrl, LoginUserName, LoginPassWord);
                pc.GouMaiYuE(driver, GoodName);
            }
        } else {
            if(iswx) {
                //移动登录&购买
                Mobile mb = new Mobile();
                log.info("mobile登录");
                mb.Login(driver, LoginUrl, LoginUserName, LoginPassWord);
                Map mp = mb.GouMaiWeiXin(driver, GoodName);
                text = (String) mp.get("recorder");

                //admin登录
                Admin ad = new Admin();
                log.info("admin登录");
                ad.Login(driver, AdminLoginUrl, AdminLoginUserName, AdminLoginPassWord);

                //订单微信支付
                ad.ZhiFuDanYiChangWeiXin(driver, text);
            }else{
                Mobile mb = new Mobile();
                log.info("mobile登录");
                mb.Login(driver, LoginUrl, LoginUserName, LoginPassWord);
                mb.GouMaiYuE(driver, GoodName);
            }
        }
    }
}
