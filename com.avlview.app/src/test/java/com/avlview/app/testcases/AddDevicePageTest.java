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
import com.avlview.app.pages.AddDevicePage;
import com.avlview.app.pages.ClientsPage;
import com.avlview.app.pages.DeviceListPage;
import com.avlview.app.pages.LoginPage;
import com.avlview.app.pages.SettingsPage;
import com.avlview.app.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class AddDevicePageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;
	SettingsPage sp;
	DeviceListPage dlp;
	AddDevicePage adp;

	public AddDevicePageTest() throws IOException {
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
	public void validateAddDevice() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateAddDevice");
		adp = dlp.addDeviceclick();
		String validateadddevice = adp.validateAddNewDevice();
		Assert.assertEquals(validateadddevice, "New Device");
	}

	@Test(priority = 2, enabled = true, dataProvider = "getData")
	public void AddDeviceTest(Hashtable<String, String> data) throws IOException, InterruptedException, AWTException {

		extentTest = extent.startTest("AddDeviceTest");
		adp = dlp.addDeviceclick();
		String validatefnmsg = null;

		// ac = cp.addclientclick();
		// System.out.println(data.get("isd").substring(0, 2));

		adp.addDevice(data.get("IMEI"), data.get("Device"), data.get("Validation"));

		if (data.get("Validation").contains("This IMEI already exists in records,")) {
			validatefnmsg = adp.validatemsgduplicateIMEI();
			System.out.println(validatefnmsg);

		} else {

			validatefnmsg = adp.validatemsg();
			System.out.println(validatefnmsg);
		}

		System.out.println(data.get("Validation"));
		Assert.assertEquals(validatefnmsg, data.get("Validation"));

	}

	@Test(priority = 3, enabled = true)
	public void SearchAddedIMEITest() throws IOException, InterruptedException, AWTException {
		extentTest = extent.startTest("SearchAddedIMEITest");
		// ac=cp.addclientclick();
		String validateSearchResult = dlp.validatesClientsearchresult();
		Assert.assertEquals(validateSearchResult, "1");
	}

	@DataProvider
	public static Object[][] getData() {

		return TestUtil.getData("Adddevice");
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
