package com.avlview.app.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.avlview.app.base.TestBase;
import com.avlview.app.pages.ClientsPage;
import com.avlview.app.pages.ForgotPasswordPage;
import com.avlview.app.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

import rough.NewTest1;

public class LoginPageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;
	ForgotPasswordPage fp;

	public LoginPageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeMethod
	public void setup() throws IOException {
		Initialization();
		lp = new LoginPage();
		cp = new ClientsPage();
		fp = new ForgotPasswordPage();
	}

	@Test(priority = 1, enabled = false)
	public void validateLoginPageExistTest() {

		extentTest = extent.startTest("validateLoginPageExistTest");

		String validateloginpage = lp.validateLoginPage();
		Assert.assertEquals(validateloginpage, "Sign in");
	}

	@Test(priority = 2, enabled = false)
	public void validateClientLogoExistTest() throws InterruptedException {
		boolean validateClientLogo = lp.validateClientLogo();
		Assert.assertTrue(validateClientLogo);
	}

	@Test(priority = 3, enabled = false)
	public void validateApplogoExistTest() {
		boolean ApplogoExist = lp.validateApplogo();
		Assert.assertTrue(ApplogoExist);
	}

	@Test(priority = 4, enabled = false)
	public void validateRemembermeExistTest() {
		String Rememberme = lp.validateRememberme();
		Assert.assertEquals(Rememberme, "Remember me");
	}

	@Test(priority = 5, enabled = false)
	public void validateLanguageExistTest() {
		String Language = lp.validateLanguage();
		Assert.assertEquals(Language, "English");
	}

	@Test(priority = 6, enabled = false)
	public void forgotpasswordclick() throws IOException {
		fp = lp.ForgotPassword();

	}

	@Test(priority = 7, enabled = false)
	public void loginclickWithInvaliduser() throws InterruptedException {
		String valmsg = lp.validationMessage(prop.getProperty("invaliduname"), prop.getProperty("invalidpwd"));
		Assert.assertEquals(valmsg, "Invalid username/password, please try again!");

	}

	@Test(priority = 8, enabled = true)
	public void loginclick() throws IOException {
		cp = lp.login(prop.getProperty("uname"), prop.getProperty("pwd"));

		System.out.println(cp.toString());

		Assert.assertTrue(cp != null, "Login failed");
		System.out.println("Login done sucessfully");

	}

	@AfterMethod
	public void teardown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test case failed is" + result.getName());
			extentTest.log(LogStatus.FAIL, "Test case failed is" + result.getThrowable());

			String screenshotPath = NewTest1.getScreenshot(driver, result.getName());
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
