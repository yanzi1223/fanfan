package basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
    private static final Logger log =  Logger.getLogger(setUp.class);
    private Workbook wb;

    public ReadExcel(String filepath) {
        if(filepath==null){
            return;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(filepath);
            if(".xls".equals(ext)){
                wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(ext)){
                wb = new XSSFWorkbook(is);
            }else{
                wb=null;
            }
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException", e);
        } catch (IOException e) {
            log.error("IOException", e);
        }
    }

    //读取Excel数据内容
    public Map<Integer, Map<Integer,String>> readExcelContent() throws Exception{
        if(wb==null){
            throw new Exception("测试用例文件为空！");
        }
        Map<Integer, Map<Integer, String>> content = new HashMap<Integer, Map<Integer, String>>();

        Sheet sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        Row row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<Integer, String> cellValue = new HashMap<Integer, String>();
            while (j < colNum) {
                String obj = (String) getCellFormatValue(row.getCell(j));
                cellValue.put(j, obj);
                j++;
            }
            content.put(i, cellValue);
        }
        return content;
    }

    //根据Cell类型设置数据
    private Object getCellFormatValue(Cell cell) {
        Object cellvalue;
        if (cell != null) {
            switch (cell.getCellType()) {
                case NUMERIC:// 如果当前Cell的Type为NUMERIC
                    DecimalFormat df = new DecimalFormat("0");
                    cellvalue = String.valueOf(df.format(cell.getNumericCellValue()));
                    break;
                case STRING:// 如果当前Cell的Type为STRING
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }
}