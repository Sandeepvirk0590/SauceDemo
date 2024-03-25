package com.saucedemo.Utils;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.saucedemo.base.SauceDemoTestBase;

public class ExcelUtils extends SauceDemoTestBase {

	public static FileInputStream fi;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static int getRowCount(String fileName, String sheet) throws Exception {
		int rowCount;
		fi = new FileInputStream(fileName);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheet);
		rowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;

	}

	public static int getCellCount(String fileName, String sheet, int rowCount) throws Exception {
		int cellCount;
		fi = new FileInputStream(fileName);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheet);
		row = ws.getRow(rowCount);
		cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}

	public static String getCellData(String fileName, String sheet, int rowCount, int cellCount) throws Exception {
		String data = null;

		fi = new FileInputStream(fileName);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheet);
		row = ws.getRow(rowCount);
		cell = row.getCell(cellCount - 1);
		data = cell.getStringCellValue();
//		fi.close();
//		wb.close();				
		return data;

	}
}
