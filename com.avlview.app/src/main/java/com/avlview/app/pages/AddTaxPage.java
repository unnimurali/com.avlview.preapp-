package com.avlview.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avlview.app.base.TestBase;
import com.avlview.app.utilities.JavaScriptUtil;

public class AddTaxPage extends TestBase {

	WebDriverWait wait;
	TaxListPage tlp;

	public AddTaxPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Add Tax')]")
	WebElement addtax;

	@FindBy(xpath = "//input[@name='taxDescription']")
	WebElement taxdesc;

	@FindBy(xpath = "//input[@placeholder='14']")
	WebElement taxamt;

	@FindBy(xpath = "//div[@class='header_notification ng-star-inserted']/span")
	WebElement validationmsg;

	@FindBy(xpath = "//span[contains(text(),'Save')]")
	WebElement addtaxbtn;

	public String validateAddNewTax() throws IOException, InterruptedException {

		System.out.println(addtax.getText());
		return addtax.getText();

	}

	public String validateEmptyTaxDesc() throws IOException, InterruptedException {

		taxdesc.clear();
		taxdesc.click();
		addtaxbtn.click();
		Thread.sleep(2000);

		return validationmsg.getText();
		// System.out.println(validationmsg.getText());
	}

	public String validateEmptyTaxamount() throws IOException, InterruptedException {

		taxamt.clear();
		taxamt.click();
		addtaxbtn.click();
		Thread.sleep(2000);

		JavaScriptUtil.scrollIntoView(validationmsg, driver);
		JavaScriptUtil.drawBorder(validationmsg, driver);

		return validationmsg.getText();
		// System.out.println(validationmsg.getText());

	}

	public String validateAddNewTax(String taxdes, String percentage) throws InterruptedException {

		taxdesc.clear();
		taxamt.clear();

		taxdesc.sendKeys(taxdes);
		taxamt.sendKeys(percentage);

		addtaxbtn.click();
		// Thread.sleep(2000);

		JavaScriptUtil.scrollIntoView(validationmsg, driver);
		JavaScriptUtil.drawBorder(validationmsg, driver);

		return validationmsg.getText();
	}

}
