package com.avlview.app.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avlview.app.base.TestBase;

public class UpdateDevicePage extends TestBase {

	WebDriverWait wait;

	public UpdateDevicePage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='md-subhead ng-star-inserted']")
	WebElement updatetext;

	@FindBy(xpath = "//span[1][@class='fnt-16 fl']")
	WebElement searchcount;

	@FindBy(xpath = "//input[@placeholder='Search IMEI..']")
	WebElement search;

	@FindBy(xpath = "//input[@role='combobox']")
	WebElement devicemodel;

	@FindBy(xpath = "//span[@class='mat-button-wrapper'] [contains(text(),'Update')]")
	WebElement updatebtn;

	public String validateEditscreen() throws IOException {
		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(updatetext));
		return updatetext.getText();
	}

	public boolean validateIMEIstatus() {
		System.out.println(driver.findElement(By.xpath("//input[@placeholder='Search IMEI..']")).isEnabled());
		return driver.findElement(By.xpath("//input[@placeholder='Search IMEI..']")).isEnabled();
	}

	public String validateUpdate() {

		devicemodel.click();
		driver.findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'Zenda')]")).click();
		updatebtn.click();
		return driver.findElement(By.xpath("//div[@class='header_notification ng-star-inserted']/span")).getText();

	}

	public String validateUpdatedDevice() throws InterruptedException {

		String deviceName = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;",
				driver.findElement(By.xpath("//input[@role='combobox']")));
		System.out.println(deviceName);

		return deviceName;

	}

}
