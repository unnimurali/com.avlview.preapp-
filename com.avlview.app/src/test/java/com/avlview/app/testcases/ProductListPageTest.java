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
import com.avlview.app.pages.ProductListPage;
import com.avlview.app.pages.SettingsPage;
import com.relevantcodes.extentreports.LogStatus;

public class ProductListPageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;
	SettingsPage sp;
	ProductListPage plp;

	public ProductListPageTest() throws IOException {
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
	public void validateProductpageExistTest() {
		extentTest = extent.startTest("validateProductpageExistTest");
		String validatetaxspage = plp.validateProductPage();
		Assert.assertEquals(validatetaxspage, "Product");
	}

	@Test(priority = 2, enabled = true)
	public void validateAddProductExistTest() {
		extentTest = extent.startTest("validateAddProductExistTest");
		String validateaddtaxspage = plp.validateAddProduct();
		Assert.assertEquals(validateaddtaxspage, "ADD PRODUCT");
	}

	@Test(priority = 3, enabled = true)
	public void validateAddProductTemplateExistTest() throws InterruptedException {
		extentTest = extent.startTest("validateAddProductTemplateExistTest");
		String validatedevicetemplatepage = plp.validateProductTemplate();
		Assert.assertEquals(validatedevicetemplatepage, "You have no products!");
	}

	@Test(priority = 4, enabled = true)
	public void validateBackButtonTest() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateBackButtonTest");
		String validatebackbutton = plp.validateBackbutton();
		Assert.assertEquals(validatebackbutton, "Settings");
	}

	@Test(priority = 5, enabled = true)
	public void validateAddProduct() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateAddProduct");
		String validateadddevice = plp.validateAddNewProduct();
		Assert.assertEquals(validateadddevice, "Add Product");
	}

	@Test(priority = 6, enabled = true)
	public void validateSearchExistTest() {
		extentTest = extent.startTest("validateSearchExistTest");
		boolean validateSearch = plp.validateSearch();
		Assert.assertTrue(validateSearch);
	}

	@Test(priority = 7, enabled = true)
	public void validateDeleteProduct() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateDeleteProduct");
		// tlp.validateEmptyTaxDesc();
		String validatedlttxt = plp.validateDeleteTax();
		Assert.assertEquals(validatedlttxt, "You just deleted a product from records.");
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
