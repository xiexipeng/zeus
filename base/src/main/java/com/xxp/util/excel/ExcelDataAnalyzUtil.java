package com.xxp.util.excel;

import com.monitorjbl.xlsx.StreamingReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiexipeng@u51.com
 * @create: 2019/08/26 20:37
 * @description: Excel 数据解析工具类
 * @Version V1.0
 **/
@Slf4j
public class ExcelDataAnalyzUtil {

    public static final String EXCEL_ERROR_CHART = "非法字符";

    public static final String EXCEL_UNKNOWN_CHART = "未知类型";

    /**
     * Excel文件解析
     *
     * @param multipartFile
     * @param fileTemplateTypeEnum
     * @return
     * @throws RuntimeException
     * @throws IOException
     */
    public static List<String[]> getExcelDataByFile(MultipartFile multipartFile, FileTemplateTypeEnum fileTemplateTypeEnum, String fileName) throws RuntimeException, IOException {
        log.info("开始执行Excel文件解析, filename:[{}], fileTemplateTypeEnum:[{}]", fileName, fileTemplateTypeEnum);
        checkFile(fileName);
        if (multipartFile.getSize() > 50 * 1024 * 1024) {
            log.warn("Excel文件大于50M，请分批上传, size:[{}]M", multipartFile.getSize() / 1024 * 1024);
            throw new RuntimeException("Excel文件大于50M，请分批上传");
        }
        Workbook workbook = getWorkBook(multipartFile.getInputStream(), fileName);
        return getDateByWorkBook(workbook, fileTemplateTypeEnum);
    }

    /**
     * Excel文件解析
     *
     * @param multipartFile
     * @param fileTemplateTypeEnum
     * @param fileName
     * @return
     * @throws IOException
     */
    public static List<String[]> getExcelDataByStreaming(MultipartFile multipartFile, FileTemplateTypeEnum fileTemplateTypeEnum, String fileName) throws IOException {
        log.info("开始执行Excel文件解析, filename:[{}], fileTemplateTypeEnum:[{}]", fileName, fileTemplateTypeEnum);
        checkFile(fileName);
        if (multipartFile.getSize() > 50 * 1024 * 1024) {
            log.warn("Excel文件大于50M，请分批上传, size:[{}]M", multipartFile.getSize() / 1024 * 1024);
            throw new RuntimeException("Excel文件大于50M，请分批上传");
        }
        Workbook workbook = StreamingReader.builder()
                .rowCacheSize(100)  //缓存到内存中的行数，默认是10
                .bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
                .open(multipartFile.getInputStream());  //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
        return getDateByWorkBook(workbook, fileTemplateTypeEnum);
    }

    /**
     * Excel文件解析
     *
     * @param inputStream
     * @param fileName
     * @param fileTemplateTypeEnum
     * @return
     */
    public static List<String[]> getExcelDateByInputStream(InputStream inputStream, String fileName, FileTemplateTypeEnum fileTemplateTypeEnum) {
        log.info("开始执行Excel文件解析, filename:[{}], fileTemplateTypeEnum:[{}]", fileName, fileTemplateTypeEnum);
        checkFile(fileName);
        Workbook workbook = getWorkBook(inputStream, fileName);
        return getDateByWorkBook(workbook, fileTemplateTypeEnum);
    }

    private static List<String[]> getDateByWorkBook(Workbook workbook, FileTemplateTypeEnum fileTemplateTypeEnum) {
        List<String[]> result = new ArrayList<>();
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);
            if (sheet == null) {
                continue;
            }
//            int firstRowNum = sheet.getFirstRowNum();
            int firstRowNum = 0;
//            checkExcelTemplateHeader(sheet.getRow(firstRowNum), fileTemplateTypeEnum);
//            int lastRowNum = sheet.getLastRowNum();
            for (Row row : sheet) {
                if (row == null) {
                    continue;
                }
                String[] cells = null;
                try {
                    cells = analyzExcelByRow(row);
                } catch (RuntimeException e) {
                    log.warn("单元格解析失败, sheetNum:[{}], rowNum:[{}]", sheetNum, row.getRowNum(), e);
                    if (firstRowNum == row.getRowNum()) {
                        throw new RuntimeException("单元格表头解析失败");
                    }
                    continue;
                }
                result.add(cells);
            }
//            for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
//                Row row = sheet.getRow(rowNum);
//                if (row == null) {
//                    continue;
//                }
//                String[] cells = null;
//                try {
//                    cells = analyzExcelByRow(row);
//                } catch (RuntimeException e) {
//                    log.warn("单元格解析失败, sheetNum:[{}], rowNum:[{}]", sheetNum, rowNum, e);
//                    if (firstRowNum == rowNum) {
//                        throw new RuntimeException("单元格表头解析失败");
//                    }
//                    continue;
//                }
//                result.add(cells);
//            }
        }
        return result;
    }

    private static void checkFile(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            log.error("文件名不能为空!");
            throw new RuntimeException("文件名不能为空!");
        }
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            log.error("文件不是一个Excel文件, filename:[{}]", fileName);
            throw new RuntimeException("文件不是一个Excel文件");
        }
    }

    private static Workbook getWorkBook(InputStream inputStream, String fileName) {
        Workbook workbook = null;
        try {
            if (fileName.endsWith("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            log.error("Excel文件解析失败", e);
            throw new RuntimeException("Excel文件解析失败");
        }
        return workbook;
    }

    private static void checkExcelTemplateHeader(Row firstRow, FileTemplateTypeEnum fileTemplateTypeEnum) {
        String[] fileTemplateHeader = fileTemplateTypeEnum.getFileTemplateHeader();
        for (int i = 0; i < fileTemplateHeader.length; i++) {
            String header = fileTemplateHeader[i];
            String cellValue = firstRow.getCell(i).getStringCellValue();
            if (!header.equalsIgnoreCase(cellValue)) {
                log.warn("文件模版格式有误, header:[{}], cellValue:[{}]", header, cellValue);
                throw new RuntimeException("文件模版格式有误!");
            }
        }
    }

    /**
     * 按行解析
     *
     * @param row
     * @return
     */
    private static String[] analyzExcelByRow(Row row) {
        int firstCellNum = row.getFirstCellNum();
        int lastCellNum = row.getLastCellNum();
        String[] cells = new String[lastCellNum];
        for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
            Cell cell = row.getCell(cellNum);
            cells[cellNum] = getCellValue(cell);
        }
        return cells;
    }

    private static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        switch (cell.getCellType()) {
            case STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case NUMERIC:
                cellValue = stringDataProcess(cell);
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK:
                cellValue = "";
                break;
            case ERROR:
                cellValue = EXCEL_ERROR_CHART;
                break;
            default:
                cellValue = EXCEL_UNKNOWN_CHART;
                break;
        }
        return cellValue;
    }

    private static String stringDataProcess(Cell cell) {
        String cellValue = "";
        if (HSSFDateUtil.isCellDateFormatted(cell)) {
            SimpleDateFormat simpleDateFormat = null;
            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                simpleDateFormat = new SimpleDateFormat("HH:mm");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            }
            Date date = cell.getDateCellValue();
            cellValue = simpleDateFormat.format(date);
        } else if (cell.getCellStyle().getDataFormat() == 58) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            double value = cell.getNumericCellValue();
            Date date = org.apache.poi.ss.usermodel.DateUtil
                    .getJavaDate(value);
            cellValue = sdf.format(date);
        } else {
            double value = cell.getNumericCellValue();
            CellStyle style = cell.getCellStyle();
            DecimalFormat format = new DecimalFormat();
            String temp = style.getDataFormatString();
            // 单元格设置成常规
            if (temp.equals("General")) {
                format.applyPattern("#");
            }
            cellValue = format.format(value);
        }
        return cellValue;
    }

    /**
     * 名单导入文件模版头
     *
     * @return
     */
    private static String[] getNameListTemplateheader() {
        String[] header = {"key", "nCode", "value", "source", "status"};
        return header;
    }

    private static String[] testHeader() {
        String[] header = {"姓名", "年龄", "性别", "地址"};
        return header;
    }

    /**
     * 文件模版类型
     */
    public static enum FileTemplateTypeEnum {

        NAMELISTTEMPLATE("名单模版", ExcelDataAnalyzUtil.getNameListTemplateheader()),
        TEST_TEMPLATE("测试模版", ExcelDataAnalyzUtil.testHeader());

        private String FileTemplateType;

        private String[] FileTemplateHeader;

        FileTemplateTypeEnum(String fileTemplateType, String[] fileTemplateHeader) {
            FileTemplateType = fileTemplateType;
            FileTemplateHeader = fileTemplateHeader;
        }

        public String getFileTemplateType() {
            return FileTemplateType;
        }

        public String[] getFileTemplateHeader() {
            return FileTemplateHeader;
        }
    }
}
