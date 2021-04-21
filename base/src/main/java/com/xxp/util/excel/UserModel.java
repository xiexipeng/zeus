package com.xxp.util.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: xiexipeng@u51.com
 * @create: 2020/05/22 17:36:58
 * @description: 效率较低，内存消耗较大
 * @Version
 **/
public class UserModel {
    public static void main(String[] args) throws InterruptedException {
        try {
            Thread.sleep(5000);
            System.out.println("start read");
            for (int i = 0; i < 100; i++) {
                try {
                    Workbook wb = null;
                    File file = new File("/Users/xiexipeng/learning/excel/excel2.xlsx");
                    InputStream fis = new FileInputStream(file);
                    wb = new XSSFWorkbook(fis);
                    Sheet sheet = wb.getSheetAt(0);
                    for (Row row : sheet) {
                        for (Cell cell : row) {
                            System.out.println("row:" + row.getRowNum() + ",cell:" + cell.toString());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
