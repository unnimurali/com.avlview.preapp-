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
import com.avlview.app.utilities.Constant;
import com.relevantcodes.extentreports.LogStatus;

public class SettingsPageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;
	SettingsPage sp;

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

	@Test(priority = 1, enabled = false)
	public void validatesettingspageExistTest() {
		extentTest = extent.startTest("validatesettingspageExistTest");
		String validatesettingspage = sp.validateSettingsPage();
		Assert.assertEquals(validatesettingspage, "Settings");
	}

	@Test(priority = 2, enabled = true)
	public void validatesettingspageTabsExistTest() {

		boolean device;
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

		featuers = sp.findItemInTheList(Constant.features);
		softAssertion.assertTrue(featuers, "Feature section not found");

		site = sp.findItemInTheList(Constant.site);
		softAssertion.assertTrue(site, "Site section not found");

		/*
		 * for (String ls : person) { // iteration on the x list // iteration on each
		 * intern list
		 * 
		 * if (ls.contains("DEVICE")) { // System.out.println("in device");
		 * //softAssertion.assertEquals(ls., expected); device = true;
		 * softAssertion.assertTrue(device); continue; // break; } else if
		 * (ls.contains("FEATURES")) { // System.out.println("in FEATURES"); featuers =
		 * true; softAssertion.assertTrue(featuers); continue; // break; } else if
		 * (ls.contains("SITE")) { // System.out.println("in SITE"); site = true;
		 * softAssertion.assertTrue(site); continue; // break; } else { if
		 * (ls.contains("DEVICE") == false) { softAssertion.fail("Dev Not found");
		 * continue; } if (ls.contains("FEATURES") == false) {
		 * softAssertion.fail("feat Not found"); continue; } if (ls.contains("SITE") ==
		 * false) { softAssertion.fail("site Not found"); continue; } }
		 */
		// }

		softAssertion.assertAll();

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
