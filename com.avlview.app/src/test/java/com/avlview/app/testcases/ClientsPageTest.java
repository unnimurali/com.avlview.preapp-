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
import com.relevantcodes.extentreports.LogStatus;

public class ClientsPageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;

	public ClientsPageTest() throws IOException {
		super();

	}

	@BeforeMethod
	public void setup() throws IOException {
		Initialization();
		lp = new LoginPage();
		cp = lp.login(prop.getProperty("uname"), prop.getProperty("pwd"));
	}

	@Test(priority = 1, enabled = true)
	public void validateclientpageExistTest() {
		extentTest = extent.startTest("validateclientpageExistTest");
		String validateclientpage = cp.validateClientspage();
		Assert.assertEquals(validateclientpage, "Clients");
	}

	@Test(priority = 2, enabled = true)
	public void validatePartnerLogoExistTest() throws InterruptedException {
		extentTest = extent.startTest("validatePartnerLogoExistTest");
		boolean validatePartnerLogo = cp.validatelogo();
		Assert.assertTrue(validatePartnerLogo);
	}

	@Test(priority = 3, enabled = true)
	public void validateAddclientExistTest() {
		extentTest = extent.startTest("validateAddclientExistTest");
		String validateaddclient = cp.validateAddclient();
		Assert.assertEquals(validateaddclient, "ADD CLIENT");
	}

	@Test(priority = 4, enabled = true)
	public void validateSearchExistTest() {
		extentTest = extent.startTest("validateSearchExistTest");
		boolean validateSearch = cp.validateSearch();
		Assert.assertTrue(validateSearch);
	}

	@Test(priority = 5, enabled = true)
	public void validateSearchResultTest() throws InterruptedException {
		extentTest = extent.startTest("validateSearchResultTest");
		String validateSearchResult = cp.validatesExistingclientearchresult();
		Assert.assertEquals(validateSearchResult, "1");
	}

	@Test(priority = 6, enabled = true)
	public void validateTotalTabsTest() throws InterruptedException {
		extentTest = extent.startTest("validateTotalTabsTest");
		int validateTabcount = cp.validatetotaltabs();
		Assert.assertEquals(validateTabcount, 2);

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
