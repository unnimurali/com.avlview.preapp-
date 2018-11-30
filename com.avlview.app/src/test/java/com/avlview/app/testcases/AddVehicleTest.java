package com.avlview.app.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.avlview.app.base.TestBase;
import com.avlview.app.pages.AddClientPage;
import com.avlview.app.pages.AddVehiclePage;
import com.avlview.app.pages.ClientsPage;
import com.avlview.app.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

public class AddVehicleTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;
	AddClientPage ac;
	AddVehiclePage av;

	public AddVehicleTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeMethod()
	public void setup() throws IOException {
		Initialization();
		lp = new LoginPage();
		cp = lp.login(prop.getProperty("uname"), prop.getProperty("pwd"));

		if (cp == null) {
			Assert.fail("Login failed");
		}

		ac = new AddClientPage();
		// av=ac.clientclick();

	}

	@Test(priority = 1, enabled = true)
	public void TemplateTest() throws IOException, InterruptedException {

		extentTest = extent.startTest("TemplateTest");

		String validateSearchResult = ac.validatesClientsearchresult();
		Assert.assertEquals(validateSearchResult, "1");

		av = ac.clientclick();

		String template = av.validatevehiclepage();
		Assert.assertEquals(template, "No vehicle(s) found");

		String addveh = av.addvehicle();
		Assert.assertEquals(addveh, "Add Vehicle");

	}

	@AfterMethod(alwaysRun = true)
	public void teardown(ITestResult result) throws IOException {

		System.out.println(result.getStatus());

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test case failed is" + result.getName());
			extentTest.log(LogStatus.FAIL, "Test case failed is" + result.getThrowable());

			String screenshotPath = TestBase.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());

			String screenshotPath = TestBase.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.SKIP, extentTest.addScreenCapture(screenshotPath));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}

		extent.endTest(extentTest);

		driver.quit();

	}

}
