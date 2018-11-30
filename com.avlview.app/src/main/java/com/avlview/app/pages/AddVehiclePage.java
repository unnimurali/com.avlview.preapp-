package com.avlview.app.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avlview.app.base.TestBase;

public class AddVehiclePage extends TestBase {

	public AddVehiclePage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='ng-star-inserted'][contains(text(),'No')]")
	WebElement novehicle;

	@FindBy(xpath = "//div[@class='ng-star-inserted'][contains(text(),'No')]")
	WebElement addvehicle;

	public String validatevehiclepage() {
		System.out.println(novehicle.getText());
		return novehicle.getText();

	}

	public String addvehicle() {
		System.out.println(addvehicle.getText());
		return addvehicle.getText();

	}

}
