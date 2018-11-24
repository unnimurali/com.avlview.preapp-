package com.avlview.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avlview.app.base.TestBase;

public class ForgotPasswordPage extends TestBase {

	public ForgotPasswordPage() throws IOException {
		// super();
		PageFactory.initElements(driver, this);
	}

	// Pagefactory

	@FindBy(xpath = "//span[contains(text(),'Forgot')]")
	WebElement FPwdpage;

	@FindBy(xpath = "//div[3]/div/div[1]/input")
	WebElement FPuname;

	@FindBy(xpath = "//input[@type='button']")
	WebElement FPRecpwd;

	@FindBy(xpath = "//*[@id='clientimglogo']")
	WebElement Clientlogo;

	@FindBy(xpath = "//div[2] [@class='sign_slider fl margin_botm_5']")
	WebElement Applogo;

	@FindBy(xpath = "//span[contains(text(),'Please')]")
	WebElement ValmsgNouser;

	@FindBy(xpath = "//span[contains(text(),'Wrong!')]")
	WebElement ValmsgWrnguser;

	@FindBy(xpath = "//a[contains(text(),'Back to Login']")
	WebElement Sucesscontent;

	@FindBy(xpath = "//span[contains(text(),'A verification')]")
	WebElement Sucessmgs;

	@FindBy(xpath = "//a[contains(text(),'Back to Login')]")
	WebElement Backtologin;

	public String ValidateForgotpasswordpage() {
		System.out.println(FPwdpage.getText());
		return FPwdpage.getText();
	}

	public boolean validateClientLogo() throws InterruptedException {
		// System.out.println(Clientlogo);
		WebDriverWait wait = new WebDriverWait(driver, 30); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(Clientlogo));

		System.out.println(Clientlogo.isDisplayed());
		return Clientlogo.isDisplayed();
	}

	public boolean validateApplogo() {
		WebDriverWait wait = new WebDriverWait(driver, 30); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(Applogo));

		System.out.println(Applogo.isDisplayed());
		return true;
	}

	public String ValidateBackTologin() {
		System.out.println(Backtologin.getText());
		return Backtologin.getText();
	}

	public LoginPage BacktoLoginClick() throws IOException {
		Backtologin.click();
		return new LoginPage();

	}

	public String ValidateNousermessage() {
		FPRecpwd.click();
		System.out.println(ValmsgNouser.getText());
		return ValmsgNouser.getText();
	}

	public String ValidateInvalidusermessage() {
		FPuname.sendKeys(prop.getProperty("invaliduname"));

		WebDriverWait wait = new WebDriverWait(driver, 30); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(FPRecpwd));

		FPRecpwd.click();
		System.out.println(ValmsgWrnguser.getText());

		return ValmsgWrnguser.getText();
	}

	public String validateCorrectusermessage() {
		FPuname.sendKeys(prop.getProperty("uname"));
		FPRecpwd.click();
		System.out.println(Sucessmgs.getText());

		return Sucessmgs.getText();
	}

}
