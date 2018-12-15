package com.avlview.app.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avlview.app.base.TestBase;
import com.avlview.app.utilities.JavaScriptUtil;

public class AddDevicePage extends TestBase {

	public AddDevicePage() throws IOException {
		super();
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'New')]")
	WebElement newdevice;

	@FindBy(xpath = "//input[@name='imei']")
	WebElement imei;

	@FindBy(xpath = "//input[@role='combobox']")
	WebElement devicemodel;

	@FindBy(xpath = "//span[contains(text(),'Save')]")
	WebElement savebtn;

	@FindBy(xpath = "//div[@class='header_notification ng-star-inserted']/span")
	WebElement validationmsg;

	@FindBy(xpath = "//p[@class='font_13 grey_txt2']")
	WebElement duplicatevalidationmsg;

	@FindBy(xpath = "//span[contains(text(),'Yes')]")
	WebElement duplicateconfirmation;

	public String validateAddNewDevice() throws IOException, InterruptedException {

		System.out.println(newdevice.getText());
		return newdevice.getText();

	}

	public String validatemsg() throws InterruptedException {

		String Valmsg = null;

		Valmsg = validationmsg.getText();
		JavaScriptUtil.drawBorder(validationmsg, driver);
		System.out.println(Valmsg);

		return Valmsg;

	}

	public String validatemsgduplicateIMEI() throws InterruptedException {

		String Valmsg = null;

		Valmsg = duplicatevalidationmsg.getText();

		if (Valmsg.contains(",")) {
			Valmsg = Valmsg.substring(0, Valmsg.indexOf('b')).trim();
		}

		JavaScriptUtil.drawBorder(duplicatevalidationmsg, driver);
		System.out.println(Valmsg);
		duplicateconfirmation.click();
		return Valmsg;

	}

	public void addDevice(String IMEI, String Device, String validation) throws InterruptedException {

		imei.sendKeys(IMEI);
		if (Device != "") {
			devicemodel.click();
			List<WebElement> devicemodel = driver
					.findElements(By.xpath("//span[@class='mat-option-text'][contains(text(),'" + Device + "')]"));
			System.out.println(devicemodel.size());
			for (WebElement ele : devicemodel) {
				System.out.println(ele.getText());
				System.out.println(Device);

				if (ele.getText().equals(Device)) {
					ele.click();
					break;
				}
			}
		}
		savebtn.click();

	}

}
