package com.wise.dataprovider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {

    public static Map<String, String> loadData(String filePath, String sheetName, String uniqueId) throws IOException {
        Map<String, String> dataMap = new LinkedHashMap<>();
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);

        CellAddress address = findCellAddress(sheet, uniqueId);
        if (address == null) {
            throw new IllegalArgumentException("Unique ID not found in the sheet");
        }

        int rowNumber = address.getRow();
        int columnCount = sheet.getRow(rowNumber).getLastCellNum();

        for (int i = 0; i < columnCount; i++) {
            Row headerRow = sheet.getRow(rowNumber - 1); // Assuming headers are in the previous row
            Row dataRow = sheet.getRow(rowNumber);
            if (headerRow != null && dataRow != null) {
                Cell headerCell = headerRow.getCell(i);
                Cell dataCell = dataRow.getCell(i);
                if (headerCell != null && dataCell != null) {
                    String key = getCellValueAsString(headerCell);
                    String value = getCellValueAsString(dataCell);
                    dataMap.put(key, value);
                    System.out.println("Loaded key: " + key + ", value: " + value); // Logging each key-value pair
                }
            }
        }

        workbook.close();
        file.close();

        return dataMap;
    }

    public static void writeData(String filePath, String sheetName, String uniqueId, String header, String data, boolean vertical) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);

        CellAddress address = findCellAddress(sheet, uniqueId);
        if (address == null) {
            throw new IllegalArgumentException("Unique ID not found in the sheet");
        }

        int rowNumber = address.getRow();
        int headerColumnIndex = findHeaderColumnIndex(sheet, header, rowNumber);

        if (headerColumnIndex == -1) {
            throw new IllegalArgumentException("Header not found in the sheet");
        }

        if (vertical) {
            writeVertical(sheet, rowNumber, headerColumnIndex, data);
        } else {
            writeHorizontal(sheet, rowNumber, headerColumnIndex, data);
        }

        file.close();

        // Write changes to the file
        FileOutputStream outFile = new FileOutputStream(filePath);
        workbook.write(outFile);
        outFile.close();
        workbook.close();
    }

    public static void writeData(String filePath, String sheetName, String uniqueId, String header, String data, boolean vertical, Map<String, String> dataMap) throws IOException {
        if (!dataMap.containsKey(header)) {
            throw new IllegalArgumentException("Header not found in the provided data map");
        }

        writeData(filePath, sheetName, uniqueId, header, data, vertical);
    }

    private static int findHeaderColumnIndex(Sheet sheet, String header, int rowNumber) {
        Row headerRow = sheet.getRow(rowNumber - 1); // Assuming headers are in the previous row
        if (headerRow != null) {
            for (Cell cell : headerRow) {
                if (getCellValueAsString(cell).equals(header)) {
                    return cell.getColumnIndex();
                }
            }
        }
        return -1; // Header not found
    }

    private static void writeVertical(Sheet sheet, int rowNumber, int headerColumnIndex, String data) {
        Row dataRow = sheet.getRow(rowNumber);
        if (dataRow == null) {
            dataRow = sheet.createRow(rowNumber);
        }
        Cell dataCell = dataRow.getCell(headerColumnIndex);
        if (dataCell == null) {
            dataCell = dataRow.createCell(headerColumnIndex);
        }
        dataCell.setCellValue(data);
    }

    private static void writeHorizontal(Sheet sheet, int rowNumber, int headerColumnIndex, String data) {
        Row dataRow = sheet.getRow(rowNumber);
        if (dataRow == null) {
            dataRow = sheet.createRow(rowNumber);
        }
        Cell dataCell = dataRow.getCell(headerColumnIndex);
        if (dataCell == null) {
            dataCell = dataRow.createCell(headerColumnIndex);
        }
        dataCell.setCellValue(data);
    }

    private static CellAddress findCellAddress(Sheet sheet, String uniqueValue) {
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(uniqueValue)) {
                    return cell.getAddress();
                }
            }
        }
        return null;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((int) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "Unknown Cell Type";
        }
    }

    public static String getValueByKey(Map<String, String> dataMap, String key) {
        if (dataMap.isEmpty()) {
            throw new IllegalStateException("Data not loaded. Call loadData() first.");
        }

        // Retrieve value based on key
        String value = dataMap.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Key '" + key + "' not found.");
        }

        return value;
    }
}
