package com.avlview.app.pages;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import com.avlview.app.base.TestBase;
import com.avlview.app.utilities.JavaScriptUtil;

public class ProductListPage extends TestBase {

	WebDriverWait wait;

	public ProductListPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Product')]")
	WebElement product;

	@FindBy(xpath = "//a[@class='green_link margin_right_17 margin_left_14 margin_top_1 fl']")
	WebElement addproduct;

	@FindBy(xpath = "//h3[contains(text(),'You have')]")
	WebElement addproductemplate;

	@FindBy(xpath = "//span[@class='fnt-16 fl']")
	WebElement productcount;

	@FindBy(xpath = "//span/mat-icon")
	WebElement back;

	@FindBy(xpath = "//input[@placeholder='Type in product and press Enter key..']")
	WebElement search;

	@FindBy(xpath = "//a[@class='settings_drop_icon']")
	WebElement icon;

	@FindBy(xpath = "//span[contains(text(),'Edit')]")
	WebElement edit;

	@FindBy(xpath = "//span[contains(text(),'Delete')]")
	WebElement delete;

	@FindBy(xpath = "//span[@class='md-subhead ng-star-inserted']")
	WebElement updatetext;

	@FindBy(xpath = "//div[@class='header_notification ng-star-inserted']/span")
	WebElement validationmsg;

	@FindBy(xpath = "//span[@class='mat-button-wrapper'] [contains(text(),'Update')]")
	WebElement updatebtn;

	@FindBy(xpath = "//span[contains(text(),'Add')]")
	WebElement newproduct;

	@FindBy(xpath = "//span[contains(text(),'Yes')]")
	WebElement confirmdeletion;

	public String validateProductPage() {
		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(product));
		System.out.println(product.getText());
		return product.getText();

	}

	public String validateAddProduct() {
		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(addproduct));
		System.out.println(addproduct.getText());
		return addproduct.getText();

	}

	public String validateProductTemplate() throws InterruptedException {

		// wait = new WebDriverWait(driver, 90); // wait for 5 seconds
		// wait.until(ExpectedConditions.visibilityOf(taxcount));
		Thread.sleep(2000);
		String a = productcount.getText();
		System.out.println(a);
		int b = Integer.parseInt(a);
		System.out.println(b);

		if (b == 0) {
			WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
			wait.until(ExpectedConditions.visibilityOf(addproductemplate));
			System.out.println(addproductemplate.getText());
			return addproductemplate.getText();
		} else {
			throw new SkipException("Product data's are avaialble so skipping the testcase.");
		}

	}

	public String validateBackbutton() throws IOException {

		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(back));
		back.click();

		SettingsPage sp = new SettingsPage();
		return sp.validateSettingsPage();

	}

	public String validateAddNewProduct() throws IOException, InterruptedException {

		addproduct.click();
		System.out.println(newproduct.getText());
		return newproduct.getText();

	}

	public boolean validateSearch() {
		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(search));
		System.out.println(search.isDisplayed());
		return search.isDisplayed();
	}

	public String validatesProductSearchresult() throws InterruptedException {
		search();
		Thread.sleep(6000);
		System.out.println(productcount.getText());
		return productcount.getText();
	}

	public void search() throws InterruptedException {
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(search));
		search.click();
		search.sendKeys(prop.getProperty("addedproduct"));
		search.sendKeys(Keys.ENTER);
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

	public String validateDeleteTax() throws IOException, InterruptedException {

		icon.click();
		delete.click();
		confirmdeletion.click();

		JavaScriptUtil.scrollIntoView(validationmsg, driver);
		JavaScriptUtil.drawBorder(validationmsg, driver);

		return validationmsg.getText();
		// // System.out.println(validationmsg.getText());

	}

	public AddProductPage addProductclick() throws IOException {
		addproduct.click();
		return new AddProductPage();
	}

	public UpdateProductPage updateProductlick() throws IOException, InterruptedException {
		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(icon));
		icon.click();

		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(edit));
		edit.click();

		// Thread.sleep(6000);
		// icon.click();
		// Thread.sleep(6000);
		// edit.click();
		return new UpdateProductPage();
	}

}
