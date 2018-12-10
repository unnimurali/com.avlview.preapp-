package com.avlview.app.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avlview.app.base.TestBase;

public class SettingsPage extends TestBase {

	String items;
	String name;
	String subitems;

	public SettingsPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	ArrayList<String> ar = new ArrayList<String>();

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	WebElement settings;

	@FindBy(xpath = "//mat-grid-tile/div[1]/div/span")
	WebElement sections;

	@FindBy(xpath = "//span[contains(text(),'DEVICE')]")
	WebElement device;

	@FindBy(xpath = "//span[contains(text(),'Features')]")
	WebElement features;

	@FindBy(xpath = "//span[contains(text(),'Site')]")
	WebElement site;

	@FindBy(xpath = "//a[contains(text(),'Taxes')]")
	WebElement tax;

	// @FindBy(xpath = Constant.device)
	// WebElement device;

	public String validateSettingsPage() {

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
		// ar.add(devicetxt);
		// ar.add(featurestxt);
		// ar.add(sitetxt);

		List<WebElement> rows_table = driver.findElements(By.xpath("//mat-grid-tile/div[1]/div/span"));
		int rows_count = rows_table.size();
		System.out.println("Total rows in the grid is" + rows_count);

		String before_xpath = "//mat-grid-tile[";
		String after_xpath = "]/div/div/span";

		for (int i = 1; i <= rows_count; i++) {
			items = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
			System.out.println("Irems are" + items);
			ar.add(items);
		}

		return ar;

	}

	public boolean findItemInTheList(String itemToFind) {
		// String name = null;
		// if (ar.contains(itemToFind)) {
		// if (itemToFind.equals("FEATURES") || itemToFind.equals("SITE")) {
		// name = itemToFind;
		// name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		// System.out.println(name);
		// } else {
		// name = itemToFind;
		// }
		// name = driver.findElement(By.xpath("//span[contains(text(),'" + name +
		// "')]")).getText();
		// System.out.println(name + " was found in the list");
		// return true;
		// } else {
		// System.out.println(itemToFind + " was not found in the list");
		// return false;
		// }

		if (ar.contains(itemToFind)) {
			name = driver.findElement(By.xpath("//span[contains(text(),'" + itemToFind + "')]")).getText();
			System.out.println(name + " was found in the list");
			return true;
		} else {
			System.out.println(itemToFind + " was not found in the list");
			return false;
		}

	}

	public ArrayList<String> validateSubTabs(String main) {

		List<WebElement> sections_page = driver.findElements(By.xpath("//mat-grid-tile/div[1]/div/span"));
		int section_count = sections_page.size();
		System.out.println("Total Sections in the page is" + section_count);

		String before_xpath_main_text = "//mat-grid-tile[";
		String after_xpath_main_text = "]/div/div/span";

		String before_xpath_sub_txt = "//mat-grid-tile[";
		String after_xpath_sub_txt = "]/div/div/ul/li/div/a[2]";

		String before_xpath_device = "//mat-grid-tile[1]/div/div/ul/li[";
		String after_xpath_device = "]/div/a[2]";

		String before_xpath_FEATURES = "//mat-grid-tile[2]/div/div/ul/li[";
		String after_xpath_FEATURES = "]/div/a[2]";

		String before_xpath_SITE = "//mat-grid-tile[3]/div/div/ul/li[";
		String after_xpath_SITE = "]/div/a[2]";

		for (int i = 1; i <= section_count; i++) {

			items = driver.findElement(By.xpath(before_xpath_main_text + i + after_xpath_main_text)).getText();
			System.out.println(items);

			if (items.equals(main)) {

				if (items.equals("DEVICE")) {
					List<WebElement> sub_tabs = driver
							.findElements(By.xpath(before_xpath_sub_txt + i + after_xpath_sub_txt));
					int sub_tabs_count = sub_tabs.size();
					System.out.println(sub_tabs_count);

					for (int j = 1; j <= sub_tabs_count; j++) {
						subitems = driver.findElement(By.xpath(before_xpath_device + j + after_xpath_device)).getText();
						System.out.println(subitems);
						ar.add(subitems);

					}

				} else if (items.equals("FEATURES")) {
					List<WebElement> sub_tabs = driver
							.findElements(By.xpath(before_xpath_sub_txt + i + after_xpath_sub_txt));
					int sub_tabs_count = sub_tabs.size();
					System.out.println(sub_tabs_count);

					for (int j = 1; j <= sub_tabs_count; j++) {
						subitems = driver.findElement(By.xpath(before_xpath_FEATURES + j + after_xpath_FEATURES))
								.getText();
						System.out.println(subitems);
						ar.add(subitems);

					}

				} else if (items.equals("SITE")) {
					List<WebElement> sub_tabs = driver
							.findElements(By.xpath(before_xpath_sub_txt + i + after_xpath_sub_txt));
					int sub_tabs_count = sub_tabs.size();
					System.out.println(sub_tabs_count);

					for (int j = 1; j <= sub_tabs_count; j++) {
						subitems = driver.findElement(By.xpath(before_xpath_SITE + j + after_xpath_SITE)).getText();
						System.out.println(subitems);
						ar.add(subitems);

					}

				}
			} else {
				System.out.println("Not found");

			}
		}
		return ar;

	}

	public boolean findSubtabItems(String itemToFind) {
		if (ar.contains(itemToFind)) {
			name = driver.findElement(By.xpath("//a[contains(text(),'" + itemToFind + "')]")).getText();
			System.out.println(name + " was found in the list");
			return true;
		} else {
			System.out.println(itemToFind + " was not found in the list");
			return false;
		}

	}

	public TaxListPage taxClick() throws IOException {
		tax.click();
		return new TaxListPage();
	}

}
