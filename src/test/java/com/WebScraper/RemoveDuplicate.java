package com.WebScraper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RemoveDuplicate {

	private static String path = "D:\\Master_Excel\\Master_Report.xlsx";
	private static File file_path = new File(path);
	private static Date date = new Date();
	private static XSSFWorkbook work_book = null;
	private static XSSFSheet sheet = null;
	private static int total_row;
	private static int total_column;
	private static ArrayList<Integer> totalDuplicate;
	private static XSSFWorkbook workbook;
	private static XSSFSheet uniqueSheet;

	public static void main(String[] args) throws IOException {
		RemoveDuplicate rd = new RemoveDuplicate();
		rd.removeDuplicate();
	}

	public void removeDuplicate() throws IOException {
		FileInputStream fis = new FileInputStream(file_path);
		work_book = new XSSFWorkbook(fis);
		sheet = work_book.getSheetAt(0);
		total_row = sheet.getLastRowNum();
		total_column = sheet.getRow(0).getLastCellNum();
		String value = null;
		String actual_title = null;
		String actual_description = null;
		String path = null;
		String expected_title = null;
		String expected_description = null;
		String expected_path = null;
		String first_data = null;
		String second_data = null;
		List<Integer> list = new ArrayList<Integer>();
		int count = 0;
		for (int i = 1; i <= total_row; i++) {
			actual_title = removeCharacter(sheet.getRow(i).getCell(getHeaderIndexNumber("title")).getStringCellValue().trim(),'/');
			actual_description = removeCharacter(sheet.getRow(i).getCell(getHeaderIndexNumber("description")).getStringCellValue()
					.trim(),'/');
			path = removeCharacter(sheet.getRow(i).getCell(getHeaderIndexNumber("path")).getStringCellValue().trim(),'/');
			count = 0;
			for (int j = 1; j <= total_row; j++) {
				expected_title = removeCharacter(sheet.getRow(j).getCell(getHeaderIndexNumber("title")).getStringCellValue().trim(),'/');
				expected_description = removeCharacter(sheet.getRow(j).getCell(getHeaderIndexNumber("description")).getStringCellValue()
						.trim(),'/');
				expected_path = removeCharacter(sheet.getRow(j).getCell(getHeaderIndexNumber("path")).getStringCellValue().trim(),'/');

				first_data = expected_path.length() >= path.length() ? expected_path : path;
				second_data = expected_path.length() <= path.length() ? expected_path : path;

				if ((actual_title.equals(expected_title) || (actual_description.equals(expected_description)))) {

					if (first_data.contains(second_data)) {
//						System.out.println(actual_title+"=="+expected_title);
//						System.out.println(expected_description+"=="+actual_description);
//						System.out.println(first_data+"=="+second_data);
						count++;

						if (count >= 2) {
							System.out.println(actual_title + "==" + expected_title);
							System.out.println(expected_description + "==" + actual_description);
							System.out.println(first_data + "==" + second_data + "=====>" + (j+1));
							System.out.println(count);
							list.add(j);
						}

					}
				}
			}

		}

	  LinkedHashSet<Integer> set= new LinkedHashSet<Integer>(list);
	   totalDuplicate=new ArrayList<Integer>(set);
	   if(totalDuplicate.size()>1)
	   {
		   logUniqueDefect();  
	   }
	}

	/**
	 * this method is used to return the column index number
	 * 
	 * @return
	 */
	public static int getHeaderIndexNumber(String expectedValue) {
		int columnIndex = 0;
		for (int i = 0; i < total_column; i++) {
			String actual_value = sheet.getRow(0).getCell(i).getStringCellValue().trim();
			if (actual_value.equals(expectedValue)) {
				columnIndex = i;
				break;
			}
		}
		return columnIndex;
	}

	/**
	 * this is used to remove the char whatever character you have passed
	 * @param expected
	 * @param c
	 * @return
	 */
	public static String removeCharacter(String expected, char c) {
		String newString = "";
		for (int i = 0; i < expected.length(); i++) {
			char d = expected.charAt(i);

			if (d != c) {
				newString = newString + String.valueOf(d);
			}
		}
		return newString;
	}
	/**
	 * @throws IOException 
	 * 
	 */
	public static void logUniqueDefect() throws IOException
	{
		
		Date date=new Date();
		String path="Unique_Defect_Report"+date.toString().replace(" ","_").replace(":","_");
		FileOutputStream fos=new FileOutputStream(new File("D:\\uniqueDefectReport\\"+path+".xlsx"));
		workbook = new XSSFWorkbook();
		uniqueSheet = workbook.createSheet("Unique_Defect");
		String actual_value=null;
		for(int i=0,k=0;i<total_row;i++,k++) {
		   uniqueSheet.createRow(i);
		   
		   for(int j=0;j<total_column;j++)
		   {
			   
			   if(!(totalDuplicate.contains(i)))
			   {
				  actual_value=sheet.getRow(i).getCell(j).getStringCellValue();  
				  uniqueSheet.getRow(k).createCell(j).setCellValue(actual_value);
			   }
			   if(totalDuplicate.contains(i))
			   {
				   k=k-1;
			   }
			   
		   }
		   
		}
	   workbook.write(fos);
	
	}

}
