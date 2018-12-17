package com.avlview.app.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.avlview.app.base.TestBase;
import com.avlview.app.pages.ClientsPage;
import com.avlview.app.pages.LoginPage;
import com.avlview.app.pages.SettingsPage;
import com.avlview.app.pages.TaxListPage;
import com.relevantcodes.extentreports.LogStatus;

public class TaxListPageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;
	SettingsPage sp;
	TaxListPage tlp;

	public TaxListPageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeMethod
	public void setup() throws IOException {
		Initialization();
		lp = new LoginPage();
		cp = lp.login(prop.getProperty("uname"), prop.getProperty("pwd"));
		if (cp == null) {
			Assert.fail("Login failed");
		}

		sp = cp.settingsclick();
		tlp = sp.taxClick();

	}

	@Test(priority = 1, enabled = true)
	public void validateTaxpageExistTest() {
		extentTest = extent.startTest("validateTaxpageExistTest");
		String validatetaxspage = tlp.validateTaxPage();
		Assert.assertEquals(validatetaxspage, "Taxes");
	}

	@Test(priority = 2, enabled = true)
	public void validateAddTaxExistTest() {
		extentTest = extent.startTest("validateAddTaxExistTest");
		String validateaddtaxspage = tlp.validateAddTax();
		Assert.assertEquals(validateaddtaxspage, "ADD TAX");
	}

	@Test(priority = 3, enabled = true)
	public void validateAddTaxTemplateExistTest() throws InterruptedException {
		extentTest = extent.startTest("validateAddTaxTemplateExistTest");
		String validatetaxstemplatepage = tlp.validateAddTaxTemplate();
		Assert.assertEquals(validatetaxstemplatepage, "You are yet to add tax information here!");
	}

	@Test(priority = 4, enabled = true)
	public void validateBackButtonTest() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateBackButtonTest");
		String validatebackbutton = tlp.validateBackbutton();
		Assert.assertEquals(validatebackbutton, "Settings");
	}

	@Test(priority = 5, enabled = true)
	public void validateEditTest() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateEditTest");
		String validateupdatescreen = tlp.validateEditscreen();
		Assert.assertEquals(validateupdatescreen, "Update Tax");
	}

	// @Test(priority = 6, enabled = false)
	// public void validateEmptyTaxdesc() throws InterruptedException, IOException {
	// extentTest = extent.startTest("validateEmptyTaxdesc");
	// // tlp.validateEmptyTaxDesc();
	// String validateemptytaxdesc = tlp.validateEmptyTaxDesc();
	// Assert.assertEquals(validateemptytaxdesc, "You cannot leave tax description
	// field empty.");
	// }
	//
	// @Test(priority = 7, enabled = false)
	// public void validateEmptyTaxamount() throws InterruptedException, IOException
	// {
	// extentTest = extent.startTest("validateEmptyTaxamount");
	// // tlp.validateEmptyTaxDesc();
	// String validateemptytaxamt = tlp.validateEmptyTaxamount();
	// Assert.assertEquals(validateemptytaxamt, "You missed to enter tax percentage
	// value.");
	// }

	@AfterMethod
	public void teardown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test case failed is" + result.getName());
			extentTest.log(LogStatus.FAIL, "Test case failed is" + result.getThrowable());

			String screenshotPath = TestBase.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}

		extent.endTest(extentTest);

		driver.quit();

	}

}
