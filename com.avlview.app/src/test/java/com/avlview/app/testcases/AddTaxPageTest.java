package com.avlview.app.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.avlview.app.base.TestBase;
import com.avlview.app.pages.AddTaxPage;
import com.avlview.app.pages.ClientsPage;
import com.avlview.app.pages.LoginPage;
import com.avlview.app.pages.SettingsPage;
import com.avlview.app.pages.TaxListPage;
import com.avlview.app.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class AddTaxPageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;
	SettingsPage sp;
	TaxListPage tlp;
	AddTaxPage atp;

	public AddTaxPageTest() throws IOException {
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
	public void validateAddTax() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateAddTax");
		atp = tlp.addTaxClick();
		String validateadddevice = atp.validateAddNewTax();
		Assert.assertEquals(validateadddevice, "Add Tax");
	}

	@Test(priority = 2, enabled = true)
	public void validateEmptyTaxdesc() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateEmptyTaxdesc");
		atp = tlp.addTaxClick();
		String validateemptytaxdesc = atp.validateEmptyTaxDesc();
		Assert.assertEquals(validateemptytaxdesc, "You cannot leave tax description field empty.");
	}

	@Test(priority = 3, enabled = true)
	public void validateEmptyTaxamount() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateEmptyTaxamount");
		atp = tlp.addTaxClick();
		String validateemptytaxamt = atp.validateEmptyTaxamount();
		Assert.assertEquals(validateemptytaxamt, "You missed to enter tax percentage value.");
	}

	@Test(priority = 4, enabled = true, dataProvider = "getData")
	public void validateAddTax(Hashtable<String, String> data) throws IOException, InterruptedException {

		extentTest = extent.startTest("validateAddTax");
		atp = tlp.addTaxClick();
		String validateemptytaxamt = atp.validateAddNewTax(data.get("Taxdesc"), data.get("Taxamt"));
		Assert.assertEquals(validateemptytaxamt, "You just added a new tax type.");

	}

	@Test(priority = 5, enabled = true)
	public void validateUpdatepopupTest() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateUpdatepopupTest");

		String validateupdatescreen = tlp.validateEditscreen();
		Assert.assertEquals(validateupdatescreen, "Update Tax");
	}

	@Test(priority = 6, enabled = true)
	public void validateEmptyTaxdescEdit() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateEmptyTaxdescEdit");
		// tlp.validateEmptyTaxDesc();
		String validateemptytaxdesc = tlp.validateEmptyTaxDescEdit();
		Assert.assertEquals(validateemptytaxdesc, "You cannot leave tax description field empty.");
	}

	@Test(priority = 7, enabled = true)
	public void validateEmptyTaxamountEdit() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateEmptyTaxamountEdit");
		// tlp.validateEmptyTaxDesc();
		String validateemptytaxamt = tlp.validateEmptyTaxamountEdit();
		Assert.assertEquals(validateemptytaxamt, "You missed to enter tax percentage value.");
	}

	@Test(priority = 8, enabled = true)
	public void validateEditTax() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateEditTax");
		// tlp.validateEmptyTaxDesc();
		String validateedttxt = tlp.validateEditTax();
		Assert.assertEquals(validateedttxt, "Changes made to the product have been saved.");
	}

	@Test(priority = 9, enabled = true)
	public void validateDeleteTax() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateDeleteTax");
		// tlp.validateEmptyTaxDesc();
		String validatedlttxt = tlp.validateDeleteTax();
		Assert.assertEquals(validatedlttxt, "You just deleted a tax structure from records.");
	}

	@DataProvider
	public static Object[][] getData() {

		return TestUtil.getData("Addtax");
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
