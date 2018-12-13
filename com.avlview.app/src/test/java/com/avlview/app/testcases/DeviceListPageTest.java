package com.avlview.app.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.avlview.app.base.TestBase;
import com.avlview.app.pages.ClientsPage;
import com.avlview.app.pages.DeviceListPage;
import com.avlview.app.pages.LoginPage;
import com.avlview.app.pages.SettingsPage;
import com.relevantcodes.extentreports.LogStatus;

public class DeviceListPageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;
	SettingsPage sp;
	DeviceListPage dlp;

	public DeviceListPageTest() throws IOException {
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
		dlp = sp.deviceClick();

	}

	@Test(priority = 1, enabled = true)
	public void validateDevicepageExistTest() {
		extentTest = extent.startTest("validateDevicepageExistTest");
		String validatetaxspage = dlp.validateDevicePage();
		Assert.assertEquals(validatetaxspage, "Devices");
	}

	@Test(priority = 2, enabled = true)
	public void validateAddDeviceExistTest() {
		extentTest = extent.startTest("validateAddDeviceExistTest");
		String validateaddtaxspage = dlp.validateAddDevice();
		Assert.assertEquals(validateaddtaxspage, "ADD DEVICE");
	}

	@Test(priority = 3, enabled = true)
	public void validateAddDeviceTemplateExistTest() throws InterruptedException {
		extentTest = extent.startTest("validateAddDeviceTemplateExistTest");
		String validatedevicetemplatepage = dlp.validateDeviceTemplate();
		Assert.assertEquals(validatedevicetemplatepage, "Found none!");
	}

	@Test(priority = 4, enabled = true)
	public void validateBackButtonTest() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateBackButtonTest");
		String validatebackbutton = dlp.validateBackbutton();
		Assert.assertEquals(validatebackbutton, "Settings");
	}

	@Test(priority = 5, enabled = true)
	public void validateAddDevice() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateAddDevice");
		String validateadddevice = dlp.validateAddNewDevice();
		Assert.assertEquals(validateadddevice, "New Device");
	}

	@Test(priority = 6, enabled = true)
	public void validateSearchExistTest() {
		extentTest = extent.startTest("validateSearchExistTest");
		boolean validateSearch = dlp.validateSearch();
		Assert.assertTrue(validateSearch);
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
