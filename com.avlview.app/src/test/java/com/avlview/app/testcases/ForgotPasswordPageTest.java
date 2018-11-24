package com.avlview.app.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.avlview.app.base.TestBase;
import com.avlview.app.pages.ForgotPasswordPage;
import com.avlview.app.pages.LoginPage;
import com.avlview.app.utilities.TestUtil;

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

	@Test(priority = 1, enabled = false)
	public void validateForgotPasswordPageExistTest() throws IOException {
		fp = lp.ForgotPassword();
		String validateForgotpage = fp.ValidateForgotpasswordpage();
		Assert.assertEquals(validateForgotpage, "Forgot your password?");
	}

	@Test(priority = 2, enabled = false)
	public void validateClientLogoExistTest() throws InterruptedException, IOException {
		fp = lp.ForgotPassword();
		boolean validateClientLogo = fp.validateClientLogo();
		Assert.assertTrue(validateClientLogo);
	}

	@Test(priority = 3, enabled = false)
	public void validateApplogoExistTest() throws IOException {
		fp = lp.ForgotPassword();
		boolean ApplogoExist = fp.validateApplogo();
		Assert.assertTrue(ApplogoExist);
	}

	@Test(priority = 4, enabled = false)
	public void validateBacktoLoginExistTest() throws IOException {
		fp = lp.ForgotPassword();
		String Backtl = fp.ValidateBackTologin();
		Assert.assertEquals(Backtl, "Back to Login");
	}

	@Test(priority = 5, enabled = false)
	public void validateBacktoLoginClickTest() throws IOException {
		fp = lp.ForgotPassword();
		fp.BacktoLoginClick();
		String validateloginpage = lp.validateLoginPage();
		Assert.assertEquals(validateloginpage, "Sign in");
	}

	@Test(priority = 6, enabled = false)
	public void validateNouserClickTest() throws IOException {
		fp = lp.ForgotPassword();
		String validateNousermessage = fp.ValidateNousermessage();
		Assert.assertEquals(validateNousermessage, "Please enter the username.");
	}

	@Test(priority = 7, enabled = TestUtil.is_execute)
	public void validateInvaliduserClickTest() throws IOException {
		fp = lp.ForgotPassword();

		String validateInvalidusermessage = fp.ValidateInvalidusermessage();
		Assert.assertEquals(validateInvalidusermessage, "Wrong! Please enter a valid username");

	}

	@Test(priority = 8, enabled = true)
	public void validateValiduserClickTest() throws IOException {
		fp = lp.ForgotPassword();
		String validateValidusermessage = fp.validateCorrectusermessage();
		Assert.assertEquals(validateValidusermessage, "A verification email has been sent to");

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
