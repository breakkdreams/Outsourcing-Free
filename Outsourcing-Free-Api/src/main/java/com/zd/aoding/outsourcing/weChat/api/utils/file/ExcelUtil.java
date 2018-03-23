package com.zd.aoding.outsourcing.weChat.api.utils.file;
//package com.zd.aoding.outsourcing.weChat.provider.utils.file;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class ExcelUtil {
//	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
//	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
//	
//	/**
//	 * 
//	 * @param file
//	 * @param sheetNum
//	 * @param colNames 列元素名   依次对应
//	 * @return
//	 * @throws IOException
//	 */
//	public static List<Map<String, Object>> readExcel(File file,Integer sheetNum,String [] colNames,boolean needFirstRow) throws IOException {
//		if(file==null){
//			return null;
//		}
//		if(file.getName().endsWith(OFFICE_EXCEL_2003_POSTFIX)){
//			return readXls(file, sheetNum, colNames,needFirstRow);
//		}else if(file.getName().endsWith(OFFICE_EXCEL_2010_POSTFIX)){
//			return readXlsx(file, sheetNum, colNames,needFirstRow);
//		}
//		return null;
//	}
//	
//	/**
//	 * 
//	 * @param file
//	 * @param sheetNum
//	 * @param colNames 列元素名   依次对应
//	 * @return
//	 * @throws IOException
//	 */
//	public static List<Map<String, Object>> readXlsx(File file,Integer sheetNum,String [] colNames,boolean needFirstRow) throws IOException {
//		InputStream is = new FileInputStream(file);
//		@SuppressWarnings("resource")
//		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheetNum==null?0:sheetNum);
//		for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
//			if(!needFirstRow&&rowNum==0){
//				continue;
//			}
//			XSSFRow xssfRow = xssfSheet.getRow(rowNum);
//			Map<String, Object> map = new HashMap<String, Object>();
//			if (xssfRow != null) {
//				for (int colNum = 0; colNum < xssfRow.getLastCellNum(); colNum++) {
//					XSSFCell xssfc = xssfRow.getCell(colNum);
//					map.put(colNames[colNum], getValue(xssfc));
//				}
//				map.put("index", rowNum+1);
//				list.add(map);
//			}
//		}
//		return list;
//	}
//	
//	/**
//	 * 
//	 * @param file
//	 * @param sheetNum
//	 * @param colNames 列元素名   依次对应
//	 * @return
//	 * @throws IOException
//	 */
//	public static List<Map<String, Object>> readXls(File file,Integer sheetNum,String [] colNames,boolean needFirstRow) throws IOException {
//		InputStream is = new FileInputStream(file);
//		@SuppressWarnings("resource")
//		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(sheetNum==null?0:sheetNum);
//		for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
//			if(!needFirstRow&&rowNum==0){
//				continue;
//			}
//			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
//			Map<String, Object> map = new HashMap<String, Object>();
//			if (hssfRow != null) {
//				for (int colNum = 0; colNum < hssfRow.getLastCellNum()&&colNum<colNames.length; colNum++) {
//					HSSFCell hssfc = hssfRow.getCell(colNum);
//					map.put(colNames[colNum], getValue(hssfc));
//				}
//				map.put("index", rowNum+1);
//				list.add(map);
//			}
//		}
//		return list;
//	}
//	
//	private static String getValue(XSSFCell xssFCell) {
//		if(xssFCell==null){
//			return null;
//		}
//		if (xssFCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
//			return String.valueOf(xssFCell.getBooleanCellValue());
//		} else if (xssFCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
//			return String.valueOf(xssFCell.getNumericCellValue());
//		} else {
//			return String.valueOf(xssFCell.getStringCellValue());
//		}
//	}
//	private static String getValue(HSSFCell hssfCell) {
//		if(hssfCell==null){
//			return null;
//		}
//		if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
//			return String.valueOf(hssfCell.getBooleanCellValue());
//		} else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
//			return String.valueOf(hssfCell.getNumericCellValue());
//		} else {
//			return String.valueOf(hssfCell.getStringCellValue());
//		}
//	}
//}
