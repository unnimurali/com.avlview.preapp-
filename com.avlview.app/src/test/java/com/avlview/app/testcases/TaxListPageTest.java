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
	TaxListPage atp;

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
		atp = sp.taxClick();

	}

	@Test(priority = 1, enabled = false)
	public void validateTaxpageExistTest() {
		extentTest = extent.startTest("validateTaxpageExistTest");
		String validatetaxspage = atp.validateTaxPage();
		Assert.assertEquals(validatetaxspage, "Taxes");
	}

	@Test(priority = 2, enabled = false)
	public void validateAddTaxExistTest() {
		extentTest = extent.startTest("validateAddTaxExistTest");
		String validateaddtaxspage = atp.validateAddTax();
		Assert.assertEquals(validateaddtaxspage, "ADD TAX");
	}

	@Test(priority = 3, enabled = false)
	public void validateAddTaxTemplateExistTest() throws InterruptedException {
		extentTest = extent.startTest("validateAddTaxTemplateExistTest");
		String validatetaxstemplatepage = atp.validateAddTaxTemplate();
		Assert.assertEquals(validatetaxstemplatepage, "You are yet to add tax information here!");
	}

	@Test(priority = 4, enabled = false)
	public void validateBackButtonTest() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateBackButtonTest");
		String validatebackbutton = atp.validateBackbutton();
		Assert.assertEquals(validatebackbutton, "Settings");
	}

	@Test(priority = 5, enabled = false)
	public void validateEditTest() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateEditTest");
		String validateupdatescreen = atp.validateEditscreen();
		Assert.assertEquals(validateupdatescreen, "Update Tax");
	}

	@Test(priority = 6, enabled = false)
	public void validateEmptyTaxdesc() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateEmptyTaxdesc");
		// atp.validateEmptyTaxDesc();
		String validateemptytaxdesc = atp.validateEmptyTaxDesc();
		Assert.assertEquals(validateemptytaxdesc, "You cannot leave tax description field empty.");
	}

	@Test(priority = 7, enabled = false)
	public void validateEmptyTaxamount() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateEmptyTaxamount");
		// atp.validateEmptyTaxDesc();
		String validateemptytaxamt = atp.validateEmptyTaxamount();
		Assert.assertEquals(validateemptytaxamt, "You missed to enter tax percentage value.");
	}

	@Test(priority = 8, enabled = true)
	public void validateAddTax() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateAddTax");
		// atp.validateEmptyTaxDesc();
		String validateemptytaxamt = atp.validateAddNewTax();
		Assert.assertEquals(validateemptytaxamt, "You just added a new tax type.");
	}

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
