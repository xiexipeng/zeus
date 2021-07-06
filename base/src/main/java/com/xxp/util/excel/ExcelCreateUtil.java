package com.xxp.util.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @author: xiexipeng
 * @create: 2019/09/22 19:04:14
 * @description: Excel创建工具类
 * @Version V1.0
 **/
public class ExcelCreateUtil {

    private static final int count = 200000;

    private static final String[] names = new String[]{"大娃", "二娃", "三娃", "四娃", "五娃", "六娃", "七娃"};

    private static final String[] sex = new String[]{"男", "女"};

    public static void createExcel(String filePath, ExcelDataAnalyzUtil.FileTemplateTypeEnum fileTemplateTypeEnum) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("第一个sheet");
        String[] heade = fileTemplateTypeEnum.getFileTemplateHeader();
        createExcelTop(sheet.createRow(0));
//        createExcelTop(sheet.createRow(0), heade);
        Random random = new Random();
        for (int i = 0; i <  count; i++) {
            Row row = sheet.createRow(i);
            setValue(row, random);
//            setValue(row, heade, random);
            if (i % 1000 == 0) {
                System.out.println("已经生成行数：" + i);
            }
        }
        FileOutputStream outputStream = new FileOutputStream(filePath);
        workbook.write(outputStream);
        outputStream.flush();
    }

    private static void createExcelTop(Row row) {
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("年龄");
        row.createCell(2).setCellValue("性别");
        row.createCell(3).setCellValue("地址");
    }

    private static void setValue(Row row, Random random) {
        row.createCell(0).setCellValue(names[random.nextInt(names.length - 1)]);
        row.createCell(1).setCellValue(random.nextInt(30));
        row.createCell(2).setCellValue("男");
        row.createCell(3).setCellValue("地址");
    }


    private static void createExcelTop(Row row, String[] header) {
        for (int i = 0; i < header.length; i++) {
            row.createCell(i).setCellValue(header[i]);
        }
    }

    private static void setValue(Row row, String[] header, Random random) {
        for (int i = 0; i < header.length; i++) {
            if (i == 1) {
                row.createCell(i).setCellValue("test_code");
                continue;
            }
            row.createCell(i).setCellValue(random.nextInt(10));
        }
    }
}
