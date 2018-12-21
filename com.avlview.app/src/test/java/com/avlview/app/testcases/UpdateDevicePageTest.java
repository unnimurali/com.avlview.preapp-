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
import com.avlview.app.pages.UpdateDevicePage;
import com.relevantcodes.extentreports.LogStatus;

public class UpdateDevicePageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;
	SettingsPage sp;
	DeviceListPage dlp;
	UpdateDevicePage udp;

	public UpdateDevicePageTest() throws IOException {
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
	public void validateUpdatepopupTest() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateUpdatepopupTest");
		dlp.search();
		udp = dlp.updateDeviceclick();

		String validateupdatescreen = udp.validateEditscreen();
		Assert.assertEquals(validateupdatescreen, "Update Device");
	}

	@Test(priority = 2, enabled = true)
	public void validateIMEIEnableTest() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateIMEIEnableTest");
		dlp.search();
		udp = dlp.updateDeviceclick();

		boolean validateemeistatus = udp.validateIMEIstatus();
		Assert.assertTrue(validateemeistatus);
	}

	@Test(priority = 3, enabled = true)
	public void validateUpdateDeviceTest() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateUpdateDeviceTest");
		dlp.search();
		udp = dlp.updateDeviceclick();

		String validateupdate = udp.validateUpdate();
		Assert.assertEquals(validateupdate, "You just updated a device IMEI.");
	}

	@Test(priority = 4, enabled = true)
	public void validateUpdatedDeviceTest() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateUpdatedDeviceTest");
		dlp.search();
		udp = dlp.updateDeviceclick();

		String validateupdateddevice = udp.validateUpdatedDevice();
		Assert.assertEquals(validateupdateddevice, "Zenda");
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
