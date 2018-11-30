package com.avlview.app.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.avlview.app.base.TestBase;
import com.avlview.app.pages.ForgotPasswordPage;
import com.avlview.app.pages.LoginPage;
import com.avlview.app.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class ForgotPasswordPageTest extends TestBase {

	LoginPage lp;
	ForgotPasswordPage fp;

	public ForgotPasswordPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setup() throws IOException {
		Initialization();
		// fp=new ForgotPasswordPage();
		lp = new LoginPage();

	}

	@Test(priority = 1, enabled = true)
	public void validateForgotPasswordPageExistTest() throws IOException {
		extentTest = extent.startTest("validateForgotPasswordPageExistTest");
		fp = lp.ForgotPassword();
		String validateForgotpage = fp.ValidateForgotpasswordpage();
		Assert.assertEquals(validateForgotpage, "Forgot your password?");
	}

	@Test(priority = 2, enabled = true)
	public void validateClientLogoExistTest() throws InterruptedException, IOException {
		extentTest = extent.startTest("validateClientLogoExistTest");
		fp = lp.ForgotPassword();
		boolean validateClientLogo = fp.validateClientLogo();
		Assert.assertTrue(validateClientLogo);
	}

	@Test(priority = 3, enabled = true)
	public void validateApplogoExistTest() throws IOException {
		extentTest = extent.startTest("validateApplogoExistTest");
		fp = lp.ForgotPassword();
		boolean ApplogoExist = fp.validateApplogo();
		Assert.assertTrue(ApplogoExist);
	}

	@Test(priority = 4, enabled = true)
	public void validateBacktoLoginExistTest() throws IOException {
		extentTest = extent.startTest("validateBacktoLoginExistTest");
		fp = lp.ForgotPassword();
		String Backtl = fp.ValidateBackTologin();
		Assert.assertEquals(Backtl, "Back to Login");
	}

	@Test(priority = 5, enabled = true)
	public void validateBacktoLoginClickTest() throws IOException {
		extentTest = extent.startTest("validateBacktoLoginClickTest");
		fp = lp.ForgotPassword();
		fp.BacktoLoginClick();
		String validateloginpage = lp.validateLoginPage();
		Assert.assertEquals(validateloginpage, "Sign in");
	}

	@Test(priority = 6, enabled = false)
	public void validateNouserClickTest() throws IOException {
		extentTest = extent.startTest("validateNouserClickTest");
		fp = lp.ForgotPassword();
		String validateNousermessage = fp.ValidateNousermessage();
		Assert.assertEquals(validateNousermessage, "Please enter the username.");
	}

	@Test(priority = 7, enabled = TestUtil.is_execute)
	public void validateInvaliduserClickTest() throws IOException {
		extentTest = extent.startTest("validateInvaliduserClickTest");
		fp = lp.ForgotPassword();

		String validateInvalidusermessage = fp.ValidateInvalidusermessage();
		Assert.assertEquals(validateInvalidusermessage, "Wrong! Please enter a valid username");

	}

	@Test(priority = 8, enabled = true)
	public void validateValiduserClickTest() throws IOException {
		extentTest = extent.startTest("validateValiduserClickTest");
		fp = lp.ForgotPassword();
		String validateValidusermessage = fp.validateCorrectusermessage();
		Assert.assertEquals(validateValidusermessage, "A verification email has been sent to");

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
