package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	//constructor for reading Excel path
	public ExcelFileUtil(String excelpath) throws Throwable {
		FileInputStream fi = new FileInputStream(excelpath);
		wb= new XSSFWorkbook(fi);
	}
	//count no.of rows in a sheet
	public int rowCount(String sheetname) {
		return wb.getSheet(sheetname).getLastRowNum();
	}
	//count no.of coloumns in a row
	public int cellCount(String sheetname) {
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}
	//get cell data
	public String getCellData(String sheetname, int row, int column) {
		String data = " ";
		if (wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata = (int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else {
			data= wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
			
		}
		return data;
	}
	//write set cell data
	public void setCelldata(String sheetname, int row,int column, String status, String writeExcelpath) throws Throwable {
		//get sheet from wb
		XSSFSheet ws = wb.getSheet(sheetname);
		//get row from sheet
		XSSFRow rowNum = ws.getRow(row);
		//create cell
		XSSFCell cell = rowNum.createCell(column);
		//write status
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if (status.equalsIgnoreCase("Fail"))
			{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
			}
		else if (status.equalsIgnoreCase("Blocked"))
		{
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writeExcelpath);
		wb.write(fo);
		
		}
	public static void main(String[] args) throws Throwable
	{
		ExcelFileUtil xl = new ExcelFileUtil("D:/documents/Results.xlsx");
		//count no.of row
		int rc = xl.rowCount("Login");
		int cc = xl.cellCount("Login");
		System.out.println(rc+"    "+cc);
		for(int i=1; i<=rc;i++) {
			String user =xl.getCellData("Login",i, 0);
			String pass = xl.getCellData("Login", i, 1);
			System.out.println(user+"   "+pass);
			//xl.setCelldata("Login", i, 3, "Pass","D:/documents/Results.xlsx");
			//xl.setCelldata("Login", i, 3, "Fail","D:/documents/Results.xlsx");
			xl.setCelldata("Login", i, 3, "Blocked","D:/documents/Results.xlsx");
		}
	

	}
}
