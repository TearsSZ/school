package com.dlb.utils;

//import com.dlb.pojo.Tab_car;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

public class ExcelPoi {
    /**
     * HSSF : 读写 Microsoft Excel XLS 格式文档
     *
     * XSSF : 读写 Microsoft Excel OOXML XLSX 格式文档
     *
     * SXSSF : 读写 Microsoft Excel OOXML XLSX 格式文档
     *
     * HWPF : 读写 Microsoft Word DOC 格式文档
     *
     * HSLF : 读写 Microsoft PowerPoint 格式文档
     *
     * HDGF : 读 Microsoft Visio 格式文档
     *
     * HPBF : 读 Microsoft Publisher 格式文档
     *
     * HSMF : 读 Microsoft Outlook 格式文档
     * @param file  上传文件源
     * @param filePath 存放路径
     */
    public void readExcel(MultipartFile file, String filePath) {
        FileOutputStream fos =null;
        InputStream is = null;
        Workbook workbook = null;
        String filename = file.getOriginalFilename();//文件名
        try {
            is = file.getInputStream();//获取流
            //根据文件后缀名判断创建什么工作簿
            assert filename != null;
            String substring = filename.substring(filename.lastIndexOf(".") + 1);
            System.out.println("substring = " + substring);
            if ("xls".equals(substring)){
                workbook = new HSSFWorkbook(is);
            }else if ("xlsx".equals(substring)){
                workbook = new XSSFWorkbook(is);
            }
            assert workbook != null;
            boolean res = readExcel(is,workbook);//调用读方法
            if (res){
                fos = new FileOutputStream(new File(filePath+filename));
                workbook.write(fos);//生成文件，放入指定路径
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (fos != null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //读并打印
    public boolean readExcel(InputStream is,Workbook workbook){
        Sheet sheet =null;
        Row row =null;
        Cell cell =null;
        int a = 0;//遍历完一个表就＋1，如果和表数量一致就是遍历完成
        int sheets = workbook.getNumberOfSheets();//一共有几个表
        for (int i = 0; i < sheets; i++) {
            sheet = workbook.getSheetAt(i);
            row = sheet.getRow(i);
            if (row!=null){
                //第一行一共有几个单元格
                int title = row.getPhysicalNumberOfCells();
                for (int j = 0; j < title; j++) {
                    cell = row.getCell(j);
                    if(cell!=null){
                        CellType cellType = cell.getCellType();
                        //System.out.println(cellType);
                        String stringCellValue = cell.getStringCellValue();
                        System.out.print(stringCellValue+"  |  ");
                    }
                }
            }
            System.out.println();
            //获取多少行
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            for (int k = 1; k < numberOfRows; k++) {
                Row row1 = sheet.getRow(k);
                if (row1!=null){
                    int cells = row1.getPhysicalNumberOfCells();
                    for (int m = 0; m < cells; m++) {
                        cell = row1.getCell(m);
                        if (cell!=null){
                            CellType cellType = cell.getCellType();
                            String cv="";
                            /**
                             *  _NONE(-1),
                             *     NUMERIC(0),
                             *     STRING(1),
                             *     FORMULA(2),
                             *     BLANK(3),
                             *     BOOLEAN(4),
                             *     ERROR(5);
                             */
                            switch (cellType){
                                case _NONE://空
                                    break;
                                case NUMERIC://日期 普通数字
                                    if (DateUtil.isCellDateFormatted(cell)){
                                        Date date = cell.getDateCellValue();
                                        cv = new DateTime(date).toString();
                                        System.out.println("[日期]");
                                    }else {//转换为字符串输出
                                        // 先获取数据
                                        double cellValue = cell.getNumericCellValue();
                                        //直接强转String类型会出现数字编程科学计数法问题
                                        cv=new BigDecimal(String.valueOf(cellValue))
                                                .stripTrailingZeros()//去除末尾的0
                                                .toPlainString();//输出时不用科学计数法
                                        System.out.print("[数字]");
                                    }

                                    break;
                                case STRING://字符串
                                    cv=cell.getStringCellValue();
                                    break;
                                case BLANK:
                                    break;
                                case ERROR://数据类型错误
                                    System.out.println("数据错误");
                                    break;
                                case BOOLEAN://布尔
                                    cv=String.valueOf(cell.getBooleanCellValue());
                                    break;
                                case FORMULA://公式
                                    //拿到计算公式
                                    FormulaEvaluator fe = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);

                                    String cellFormula = cell.getCellFormula();
                                    //内容值:SUM(D2:D3)
                                    System.out.println("内容值:"+cellFormula);
                                    //计算
                                    CellValue evaluate = fe.evaluate(cell);
                                    String s = evaluate.toString();
                                    //org.apache.poi.ss.usermodel.CellValue [25.0]
                                    System.out.println(s);
                                    break;
                            }
                            System.out.print(cv+"  |  ");
                        }
                    }
                    System.out.println();
                }
            }
            System.err.println("表"+i+"完:"+sheet.getSheetName());
            a++;
        }
        return a == sheets;
    }
    /*//网页上面下载Excel文件
    public void writeExcel(String sheetName,//sheet名字
                           List<Tab_car> bodyList,//要输出的对象集合
                           HttpServletResponse response){//服务响应
        //创建工作簿
        Workbook workbook = new SXSSFWorkbook();
        //创建一个sheet表
        Sheet sheet = workbook.createSheet(sheetName);
        //写数据
        // 创建一行
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("品牌");
        row.createCell(1).setCellValue("车牌号");
        row.createCell(2).setCellValue("容量座位");
        row.createCell(3).setCellValue("颜色");
        row.createCell(4).setCellValue("价格");
        row.createCell(5).setCellValue("时间");
        row.createCell(6).setCellValue("状态");
        // 定义样式
        CellStyle cellStyle = workbook.createCellStyle();
        //内容
        for (int i = 0; i < bodyList.size(); i++) {
            Row row1 = sheet.createRow(i+1);
            Tab_car tab_car = bodyList.get(i);
            row1.createCell(0).setCellValue(tab_car.getCarLogo());
            row1.createCell(1).setCellValue(tab_car.getCarNumber());
            row1.createCell(2).setCellValue(tab_car.getCarZuo());
            row1.createCell(3).setCellValue(tab_car.getCarColor());
            row1.createCell(4).setCellValue(tab_car.getCarPrice().toString());
            String time = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(tab_car.getCarTime());
            row1.createCell(5).setCellValue(time);
            row1.createCell(6).setCellValue(tab_car.getCarStatus());
        }
        //模拟文件，myfile.txt为需要下载的文件
        //String path = "E:\\"+fileName+".xlsx";
       // new File(path)
       // FileOutputStream fos = new FileOutputStream(path);
       // workbook.write(fos);
        ServletOutputStream outputStream = null;
        try{
            //响应防止乱码
            response.setContentType("multipart/form-data");
            // 传递中文参数编码
            String codedFileName = java.net.URLEncoder.encode("汽车信息","UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xlsx");
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
