package com.avlview.app.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avlview.app.base.TestBase;
import com.avlview.app.utilities.JavaScriptUtil;

public class AddProductPage extends TestBase {

	public AddProductPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//span[contains(text(),'Add')]")
	WebElement addproduct;

	@FindBy(xpath = "//input[@name='productName']")
	WebElement productname;

	@FindBy(xpath = "//textarea[@placeholder='Pro Plan on 12 months payment plan.']")
	WebElement productdescription;

	@FindBy(xpath = "//span[contains(text(),'Choose')]")
	WebElement plan;

	@FindBy(xpath = "//input[@name='rate']")
	WebElement rate;

	@FindBy(xpath = "//span[contains(text(),'15%')]")
	WebElement tax;

	@FindBy(xpath = "//span[contains(text(),'Save')]")
	WebElement savebtn;

	@FindBy(xpath = "//div[@class='header_notification ng-star-inserted']/span")
	WebElement validationmsg;

	@FindBy(xpath = "//p[@class='font_13 grey_txt2']")
	WebElement duplicatevalidationmsg;

	@FindBy(xpath = "//span[contains(text(),'Yes')]")
	WebElement duplicateconfirmation;

	public String validateAddNewProduct() throws IOException, InterruptedException {

		System.out.println(addproduct.getText());
		return addproduct.getText();

	}

	public String validatemsg() throws InterruptedException {

		String Valmsg = null;

		Valmsg = validationmsg.getText();
		JavaScriptUtil.drawBorder(validationmsg, driver);
		System.out.println(Valmsg);

		return Valmsg;

	}

	public void addProduct(String ProductName, String ProductDescription, String Plan, String Rate, String Tax,
			String Validation) throws InterruptedException {

		productname.clear();
		productname.sendKeys(ProductName);
		productdescription.clear();
		productdescription.sendKeys(ProductDescription);

		if (Plan != "") {
			plan.click();
			WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'" + Plan + "')]"));
			ele.click();
		}

		rate.clear();
		rate.sendKeys(Rate);

		if (Tax != "") {

			tax.click();
			WebElement taxvalue = driver.findElement(By.xpath("//span[contains(text(),'" + Tax + "')]"));
			taxvalue.click();

		}

		savebtn.click();

	}

}
