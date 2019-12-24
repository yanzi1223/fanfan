package controler;

import basic.Mai;
import basic.ReadExcel;
import basic.setUp;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.Map;

public class goumai {

    public static final Logger log = Logger.getLogger(setUp.class);

    @Test
    public static void mai() throws Exception {
        setUp driver1 = new setUp();
        WebDriver driver = driver1.setup();
        try {
            String filepath = "testcase\\testcase.xlsx";
            ReadExcel excelReader = new ReadExcel(filepath);
            // 读取Excel表格内容
            log.info("读取excel");
            Map<Integer, Map<Integer, String>> map = excelReader.readExcelContent();
            for (int i = 1; i <= map.size(); i++) {
                Map<Integer, String> map1 = map.get(i);
                log.info("测试用例内容:"+map1);

                //购买
                new Mai(driver,map1.get(0), map1.get(1), map1.get(2), map1.get(3), map1.get(4), map1.get(5), "http://192.168.1.26", "testsgq", "fan1080");
            }
            driver.quit();
            log.info("关闭浏览器");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("未找到测试用例文件!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
