package com.avlview.app.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.avlview.app.base.TestBase;
import com.avlview.app.pages.ClientsPage;
import com.avlview.app.pages.LoginPage;
import com.avlview.app.pages.SettingsPage;
import com.avlview.app.pages.TaxListPage;
import com.avlview.app.utilities.Constant;
import com.relevantcodes.extentreports.LogStatus;

public class SettingsPageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;
	SettingsPage sp;
	TaxListPage atp;

	public SettingsPageTest() throws IOException {
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
		// av=ac.clientclick();
	}

	@Test(priority = 1, enabled = true)
	public void validatesettingspageExistTest() {
		extentTest = extent.startTest("validatesettingspageExistTest");
		String validatesettingspage = sp.validateSettingsPage();
		Assert.assertEquals(validatesettingspage, "Settings");
	}

	@Test(priority = 2, enabled = true)
	public void validatesettingspageTabsExistTest() {

		boolean device = false;
		boolean featuers = false;
		boolean site = false;

		SoftAssert softAssertion = new SoftAssert();

		extentTest = extent.startTest("validatesettingspageTabExistTest");

		// ArrayList<String> person = sp.validateTabs();
		// System.out.println(person);
		// String dev = person.get(0);
		// System.out.println(dev);
		// softAssertion.assertEquals(dev, Constant.device, "Device section not found");

		sp.validateTabs();
		device = sp.findItemInTheList(Constant.device);
		softAssertion.assertTrue(device, "Device section not found");

		sp.validateTabs();
		featuers = sp.findItemInTheList(Constant.features);
		softAssertion.assertTrue(featuers, "Feature section not found");

		sp.validateTabs();
		site = sp.findItemInTheList(Constant.site);
		softAssertion.assertTrue(site, "Site section not found");

		softAssertion.assertAll();

	}

	@Test(priority = 3, enabled = true)
	public void validatesettingspageSubTabsExistTest() {

		boolean devices = false;
		boolean product = false;
		boolean taxes = false;
		boolean logo = false;

		SoftAssert softAssertion = new SoftAssert();

		extentTest = extent.startTest("validatesettingspageSubTabsExistTest");

		// ArrayList<String> person = sp.validateSubTabs(Constant.device);
		// System.out.println(person);
		// String dev = person.get(0);
		// System.out.println(dev);
		// String dev1 = person.get(0);
		// System.out.println(dev1);
		// softAssertion.assertEquals(dev, Constant.device, "Device section not found");

		sp.validateSubTabs(Constant.device);
		devices = sp.findSubtabItems(Constant.devices);
		System.out.println(devices);
		softAssertion.assertTrue(devices, "Devices section not found");

		sp.validateSubTabs(Constant.features);
		product = sp.findSubtabItems(Constant.product);
		System.out.println(product);
		softAssertion.assertTrue(product, "Product section not found");
		taxes = sp.findSubtabItems(Constant.taxes);
		System.out.println(taxes);
		softAssertion.assertTrue(taxes, "Taxes section not found");

		sp.validateSubTabs(Constant.site);
		logo = sp.findSubtabItems(Constant.logo);
		System.out.println(logo);
		softAssertion.assertTrue(logo, "Logo section not found");

		softAssertion.assertAll();

	}

	public void taxclick() throws IOException {
		extentTest = extent.startTest("taxclick");
		atp = sp.taxClick();
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
