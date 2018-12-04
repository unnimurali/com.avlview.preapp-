package com.avlview.app.pages;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avlview.app.base.TestBase;

public class SettingsPage extends TestBase {

	public SettingsPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	ArrayList<String> ar = new ArrayList<String>();

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	WebElement settings;

	@FindBy(xpath = "//span[contains(text(),'DEVICE')]")
	WebElement device;

	// @FindBy(xpath = Constant.device)
	// WebElement device;

	@FindBy(xpath = "//span[contains(text(),'Features')]")
	WebElement features;

	@FindBy(xpath = "//span[contains(text(),'Site')]")
	WebElement site;

	public String validateSettingsPage() {
		// String settingspage = settings.getText();

		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(settings));
		System.out.println(settings.getText());

		return settings.getText();

	}

	// public List<String> validateTabs() {
	// // String settingspage = settings.getText();
	//
	// WebDriverWait wait = new WebDriverWait(driver, 10); // wait for 5 seconds
	// wait.until(ExpectedConditions.visibilityOf(device));
	// System.out.println(device.getText());
	// String devicetxt = device.getText();
	//
	// WebDriverWait wait1 = new WebDriverWait(driver, 10); // wait for 5 seconds
	// wait1.until(ExpectedConditions.visibilityOf(features));
	//
	// System.out.println(features.getText());
	// String featurestxt = features.getText();
	//
	// WebDriverWait wait2 = new WebDriverWait(driver, 10); // wait for 5 seconds
	// wait2.until(ExpectedConditions.visibilityOf(site));
	// System.out.println(site.getText());
	// String sitetxt = site.getText();
	//
	// return Arrays.asList(devicetxt, featurestxt, sitetxt);
	//
	// }

	public ArrayList<String> validateTabs() {
		// String settingspage = settings.getText();

		WebDriverWait wait = new WebDriverWait(driver, 10); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(device));
		System.out.println(device.getText());
		String devicetxt = device.getText();

		WebDriverWait wait1 = new WebDriverWait(driver, 10); // wait for 5 seconds
		wait1.until(ExpectedConditions.visibilityOf(features));

		System.out.println(features.getText());
		String featurestxt = features.getText();

		WebDriverWait wait2 = new WebDriverWait(driver, 10); // wait for 5 seconds
		wait2.until(ExpectedConditions.visibilityOf(site));
		System.out.println(site.getText());
		String sitetxt = site.getText();

		ar.add(devicetxt);
		ar.add(featurestxt);
		ar.add(sitetxt);

		return ar;

	}

	public boolean findItemInTheList(String itemToFind) {
		if (ar.contains(itemToFind)) {
			System.out.println(itemToFind + " was found in the list");
			return true;
		} else {
			System.out.println(itemToFind + " was not found in the list");
			return false;
		}

	}

}
