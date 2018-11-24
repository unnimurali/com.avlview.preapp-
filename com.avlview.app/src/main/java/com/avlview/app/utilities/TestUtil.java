package com.avlview.app.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.avlview.app.base.TestBase;

public class TestUtil extends TestBase {

	public TestUtil() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static long Page_timeout = 50;
	public static long Imp_wait = 30;

	public static final boolean is_execute_no = false;
	public static final boolean is_execute = true;

	public static Object[][] getData(String sheetname) {

		int Totalrows = excel.getRowCount(sheetname);
		int Totalcols = excel.getColumnCount(sheetname);

		System.out.println("total rows are : " + Totalrows);
		System.out.println("total cols are : " + Totalcols);

		Object[][] data = new Object[Totalrows - 1][1];
		Hashtable<String, String> table = null;

		for (int rows = 2; rows <= Totalrows; rows++) {

			table = new Hashtable<String, String>();

			for (int cols = 0; cols < Totalcols; cols++) {
				// Value = excel.getCellData(sheetname, cols, rows);
				table.put(excel.getCellData(sheetname, cols, 1), excel.getCellData(sheetname, cols, rows));
				data[rows - 2][0] = table;
			}
		}
		return data;

	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

}
