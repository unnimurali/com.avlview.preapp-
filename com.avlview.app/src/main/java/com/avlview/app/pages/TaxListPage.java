package com.avlview.app.pages;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import com.avlview.app.base.TestBase;
import com.avlview.app.utilities.JavaScriptUtil;

public class TaxListPage extends TestBase {

	WebDriverWait wait;
	ArrayList<String> ar = new ArrayList<String>();

	public TaxListPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Taxes')]")
	WebElement taxes;

	@FindBy(xpath = "//a[@class='green_link margin_right_17 margin_left_14 margin_top_1 fl']")
	WebElement addtax;

	@FindBy(xpath = "//h3[contains(text(),'You')]")
	WebElement addtaxtemplate;

	@FindBy(xpath = "//span[@class='fnt-16 fl']")
	WebElement taxcount;

	@FindBy(xpath = "//span/mat-icon")
	WebElement back;

	@FindBy(xpath = "//a[@class='settings_drop_icon']")
	WebElement icon;

	@FindBy(xpath = "//span[contains(text(),'Edit')]")
	WebElement edit;

	@FindBy(xpath = "//span[contains(text(),'Delete')]")
	WebElement delete;

	@FindBy(xpath = "//span[@class='md-subhead ng-star-inserted']")
	WebElement updatetext;

	@FindBy(xpath = "//input[@name='taxDescription']")
	WebElement taxdesc;

	@FindBy(xpath = "//input[@placeholder='14']")
	WebElement taxamt;

	@FindBy(xpath = "//div[@class='header_notification ng-star-inserted']/span")
	WebElement validationmsg;

	@FindBy(xpath = "//span[@class='mat-button-wrapper'] [contains(text(),'Update')]")
	WebElement updatebtn;

	@FindBy(xpath = "//span[contains(text(),'Yes')]")
	WebElement confirmdeletion;

	@FindBy(xpath = "//span[contains(text(),'Save')]")
	WebElement addtaxbtn;

	public String validateTaxPage() {

		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(taxes));
		System.out.println(taxes.getText());

		return taxes.getText();

	}

	public String validateAddTax() {

		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(addtax));
		System.out.println(addtax.getText());

		return addtax.getText();

	}

	public String validateAddTaxTemplate() throws InterruptedException {

		// wait = new WebDriverWait(driver, 90); // wait for 5 seconds
		// wait.until(ExpectedConditions.visibilityOf(taxcount));
		Thread.sleep(2000);
		String a = taxcount.getText();
		System.out.println(a);
		int b = Integer.parseInt(a);
		System.out.println(b);

		if (b == 0) {
			WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
			wait.until(ExpectedConditions.visibilityOf(addtaxtemplate));
			System.out.println(addtaxtemplate.getText());
			return addtaxtemplate.getText();
		} else {
			throw new SkipException("Tax data's are avaialble so skipping the testcase.");
		}

	}

	public String validateBackbutton() throws IOException {

		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(back));
		back.click();

		SettingsPage sp = new SettingsPage();
		return sp.validateSettingsPage();

	}

	public String validateEditscreen() throws IOException {

		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(icon));
		icon.click();

		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(edit));
		edit.click();

		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(updatetext));

		return updatetext.getText();

	}

	public String validateEmptyTaxDesc() throws IOException, InterruptedException {

		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(icon));
		icon.click();

		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(edit));
		edit.click();

		taxdesc.clear();
		taxdesc.click();
		updatebtn.click();
		Thread.sleep(2000);

		return validationmsg.getText();
		// System.out.println(validationmsg.getText());
	}

	public String validateEmptyTaxamount() throws IOException, InterruptedException {

		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(icon));
		icon.click();

		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(edit));
		edit.click();

		taxamt.clear();
		taxamt.click();
		updatebtn.click();
		Thread.sleep(2000);

		JavaScriptUtil.scrollIntoView(validationmsg, driver);
		JavaScriptUtil.drawBorder(validationmsg, driver);

		return validationmsg.getText();
		// System.out.println(validationmsg.getText());

	}

	public String validateAddNewTax() throws IOException, InterruptedException {

		// wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		// wait.until(ExpectedConditions.visibilityOf(addtax));
		addtax.click();

		taxdesc.sendKeys("GST");
		taxamt.sendKeys("15");

		addtaxbtn.click();
		Thread.sleep(2000);

		JavaScriptUtil.scrollIntoView(validationmsg, driver);
		JavaScriptUtil.drawBorder(validationmsg, driver);

		return validationmsg.getText();
		// System.out.println(validationmsg.getText());

	}

	public String validateDeleteTax() throws IOException, InterruptedException {

		// wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		// wait.until(ExpectedConditions.visibilityOf(addtax));
		icon.click();
		delete.click();
		confirmdeletion.click();

		JavaScriptUtil.scrollIntoView(validationmsg, driver);
		JavaScriptUtil.drawBorder(validationmsg, driver);

		return validationmsg.getText();
		// System.out.println(validationmsg.getText());

	}

}
