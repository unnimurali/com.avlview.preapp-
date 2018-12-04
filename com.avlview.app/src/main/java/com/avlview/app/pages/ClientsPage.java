package com.avlview.app.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avlview.app.base.TestBase;

public class ClientsPage extends TestBase {

	public ClientsPage() throws IOException {
		// super();
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//span[2][contains(text(),'Clients')]")
	WebElement clients;

	@FindBy(xpath = "//button[starts-with(@class,'mat-button ')]")
	WebElement Totaltabs;

	@FindBy(xpath = "//img[@class='margin_top_7']")
	WebElement logo;

	@FindBy(xpath = "//a[contains(text(),'Add client')]")
	WebElement addclient;

	@FindBy(xpath = "//*[@id='mat-input-0']")
	WebElement search;

	@FindBy(xpath = "//span[1][@class='fnt-16 fl']")
	WebElement searchcount;

	@FindBy(xpath = "//i[@class='material-icons more_btn']")
	WebElement topright;

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	WebElement settings;

	public String validateClientspage() {
		System.out.println(clients.getText());
		return clients.getText();
	}

	public boolean validatelogo() {
		WebDriverWait wait = new WebDriverWait(driver, 30); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(logo));

		return logo.isDisplayed();
	}

	public String validateAddclient() {
		System.out.println(addclient.getText());
		return addclient.getText();
	}

	public int validatetotaltabs() {

		int tabcount = driver.findElements(By.xpath("//button[starts-with(@class,'mat-button ')]")).size();
		// System.out.println(tabcount);

		return tabcount;
	}

	public boolean validateSearch() {
		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(search));
		System.out.println(search.isDisplayed());
		return search.isDisplayed();
	}

	public String validatesExistingclientearchresult1() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(search));

		/*
		 * Actions builder = new Actions(driver); Action seriesofaction =
		 * builder.moveToElement(search).click().sendKeys(search,
		 * prop.getProperty("searchtxt")) .sendKeys(Keys.ENTER).build();
		 * seriesofaction.perform();
		 */

		search.click();
		search.sendKeys(prop.getProperty("searchtxt"));
		search.sendKeys(Keys.ENTER);

		Thread.sleep(6000);

		System.out.println(searchcount.getText());
		return searchcount.getText();
	}

	public AddClientPage addclientclick() throws IOException {
		// TODO Auto-generated method stub

		/*
		 * Actions actions = new Actions(driver); actions.moveToElement(addclient);
		 * actions.click(); actions.build().perform();
		 */

		addclient.click();

		return new AddClientPage();
	}

	public SettingsPage settingsclick() throws IOException {

		topright.click();

		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(settings));
		settings.click();

		return new SettingsPage();
	}

	public String validatesExistingclientearchresult() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(search));

		/*
		 * Actions builder = new Actions(driver); Action seriesofaction =
		 * builder.moveToElement(search).click().sendKeys(search,
		 * prop.getProperty("searchtxt")) .sendKeys(Keys.ENTER).build();
		 * seriesofaction.perform();
		 */

		search.click();
		search.sendKeys(prop.getProperty("searchtxt"));
		search.sendKeys(Keys.ENTER);

		Thread.sleep(6000);

		// WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		// wait.until(ExpectedConditions.visibilityOf(searchcount));
		// wait.until(ExpectedConditions.textToBePresentInElementValue(searchcount,prop.getProperty("searchtxt")));
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[1][@class='fnt-16
		// fl']")));

		System.out.println(searchcount.getText());
		return searchcount.getText();
	}

}
