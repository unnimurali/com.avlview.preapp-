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

public class DeviceListPage extends TestBase {

	WebDriverWait wait;

	public DeviceListPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Devices')]")
	WebElement devices;

	@FindBy(xpath = "//a[@class='green_link margin_right_17 margin_left_14 margin_top_1 fl']")
	WebElement adddevice;

	@FindBy(xpath = "//h3[contains(text(),'Found')]")
	WebElement adddevicetemplate;

	@FindBy(xpath = "//span[@class='fnt-16 fl']")
	WebElement devicecount;

	@FindBy(xpath = "//span/mat-icon")
	WebElement back;

	@FindBy(xpath = "//input[@placeholder='Search IMEI..']")
	WebElement search;

	@FindBy(xpath = "//a[@class='settings_drop_icon']")
	WebElement icon;

	@FindBy(xpath = "//span[contains(text(),'Edit')]")
	WebElement edit;

	@FindBy(xpath = "//span[@class='md-subhead ng-star-inserted']")
	WebElement updatetext;

	@FindBy(xpath = "//div[@class='header_notification ng-star-inserted']/span")
	WebElement validationmsg;

	@FindBy(xpath = "//span[@class='mat-button-wrapper'] [contains(text(),'Update')]")
	WebElement updatebtn;

	@FindBy(xpath = "//span[contains(text(),'New')]")
	WebElement newdevice;

	@FindBy(xpath = "//span[1][@class='fnt-16 fl']")
	WebElement searchcount;

	public String validateDevicePage() {
		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(devices));
		System.out.println(devices.getText());
		return devices.getText();

	}

	public String validateAddDevice() {
		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(adddevice));
		System.out.println(adddevice.getText());
		return adddevice.getText();

	}

	public String validateDeviceTemplate() throws InterruptedException {

		// wait = new WebDriverWait(driver, 90); // wait for 5 seconds
		// wait.until(ExpectedConditions.visibilityOf(taxcount));
		Thread.sleep(2000);
		String a = devicecount.getText();
		System.out.println(a);
		int b = Integer.parseInt(a);
		System.out.println(b);

		if (b == 0) {
			WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
			wait.until(ExpectedConditions.visibilityOf(adddevicetemplate));
			System.out.println(adddevicetemplate.getText());
			return adddevicetemplate.getText();
		} else {
			throw new SkipException("Device data's are avaialble so skipping the testcase.");
		}

	}

	public String validateBackbutton() throws IOException {

		wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(back));
		back.click();

		SettingsPage sp = new SettingsPage();
		return sp.validateSettingsPage();

	}

	public String validateAddNewDevice() throws IOException, InterruptedException {

		adddevice.click();
		System.out.println(newdevice.getText());
		return newdevice.getText();

	}

	public boolean validateSearch() {
		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(search));
		System.out.println(search.isDisplayed());
		return search.isDisplayed();
	}

	public String validatesClientsearchresult() throws InterruptedException {
		search();
		Thread.sleep(6000);
		System.out.println(searchcount.getText());
		return searchcount.getText();
	}

	public void search() throws InterruptedException {
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(search));
		search.click();
		search.sendKeys(prop.getProperty("addedimei"));
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

	public AddDevicePage addDeviceclick() throws IOException {
		adddevice.click();
		return new AddDevicePage();
	}

	public UpdateDevicePage updateDeviceclick() throws IOException, InterruptedException {
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
		return new UpdateDevicePage();
	}

}
