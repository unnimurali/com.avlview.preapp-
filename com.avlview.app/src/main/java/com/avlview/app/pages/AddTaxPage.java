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

	public String validateTaxPage() {

		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(taxes));
		System.out.println(taxes.getText());

		return taxes.getText();

	}

}
