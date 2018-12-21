package com.avlview.app.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.avlview.app.base.TestBase;
import com.avlview.app.pages.AddProductPage;
import com.avlview.app.pages.ClientsPage;
import com.avlview.app.pages.LoginPage;
import com.avlview.app.pages.ProductListPage;
import com.avlview.app.pages.SettingsPage;
import com.avlview.app.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class AddProductPageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;
	SettingsPage sp;
	ProductListPage plp;
	AddProductPage app;

	public AddProductPageTest() throws IOException {
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
		plp = sp.ProductClick();

	}

	@Test(priority = 1, enabled = true)
	public void validateAddProduct() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateAddProduct");
		app = plp.addProductclick();
		String validateaddproduct = app.validateAddNewProduct();
		Assert.assertEquals(validateaddproduct, "Add Product");
	}

	@Test(priority = 2, enabled = true, dataProvider = "getData")
	public void AddProductTest(Hashtable<String, String> data) throws IOException, InterruptedException, AWTException {

		extentTest = extent.startTest("AddProductTest");

		app = plp.addProductclick();
		// System.out.println(data.get("isd").substring(0, 2));

		app.addProduct(data.get("Productname"), data.get("Productdescription"), data.get("Plan"), data.get("Rate"),
				data.get("Tax"), data.get("Validation"));

		String validatefnmsg = app.validatemsg();
		System.out.println(validatefnmsg);

		System.out.println(data.get("Validation"));
		Assert.assertEquals(validatefnmsg, data.get("Validation"));

	}

	@Test(priority = 3, enabled = true)
	public void SearchAddedProductTest() throws IOException, InterruptedException, AWTException {
		extentTest = extent.startTest("SearchAddedProductTest");
		// ac=cp.addclientclick();
		String validateSearchResult = plp.validatesProductSearchresult();
		Assert.assertEquals(validateSearchResult, "1");
	}

	@DataProvider
	public static Object[][] getData() {

		return TestUtil.getData("Addproduct");
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
