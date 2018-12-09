package com.avlview.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avlview.app.base.TestBase;

public class AddTaxPage extends TestBase {

	public AddTaxPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Taxes')]")
	WebElement taxes;
	
	@FindBy(xpath="//a[@class='green_link margin_right_17 margin_left_14 margin_top_1 fl']")
	WebElement addtax;
	
	@FindBy(xpath="//h3[contains(text(),'You')]")
	WebElement addtaxtemplate;

	public String validateTaxPage() {

		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(taxes));
		System.out.println(taxes.getText());

		return taxes.getText();

	}
	
	public String validateAddTax() {

		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(addtax));
		System.out.println(addtax.getText());

		return addtax.getText();

	}
	
	public String validateAddTaxTemplate() {

		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(addtaxtemplate));
		System.out.println(addtaxtemplate.getText());

		return addtaxtemplate.getText();

	}
	
	

}
