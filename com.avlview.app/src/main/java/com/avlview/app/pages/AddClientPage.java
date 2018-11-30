package com.avlview.app.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import com.avlview.app.base.TestBase;
import com.avlview.app.utilities.JavaScriptUtil;

public class AddClientPage extends TestBase {

	String logo;
	public static String txt;

	boolean count = false;

	public AddClientPage() throws IOException {
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//span[contains(text(),'Add')]")
	WebElement addclient;

	@FindBy(xpath = "//a[contains(text(),'CREATE YOUR FIRST CLIENT')]")
	WebElement validatenoclient;

	@FindBy(xpath = "//div[@class='header_notification ng-star-inserted']/span")
	WebElement validationmsg;

	@FindBy(xpath = "//span[contains(text(),'Save')]")
	WebElement savebtn;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement fname;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lname;

	@FindBy(xpath = "//input[@placeholder='johnsmith@email.com']")
	WebElement email;

	@FindBy(xpath = "//input[@name='orgUnitName']")
	WebElement company;

	@FindBy(xpath = "//input[@placeholder='+65']")
	WebElement isdcode;

	@FindBy(xpath = "//input[@placeholder='Eg. 1234567890']")
	WebElement mobile;

	@FindBy(xpath = "//input[@placeholder='1 Year']")
	WebElement paymentplan;

	@FindBy(xpath = "//input[@placeholder='(GMT+08:00) Kuala Lumpur, Singapore']")
	WebElement TimeZone;

	@FindBy(xpath = "//img[@alt='Upload your logo (200x 55)']")
	WebElement Logo;

	@FindBy(xpath = "//*[@id='mat-input-0']")
	WebElement search;

	@FindBy(xpath = "//span[1][@class='fnt-16 fl']")
	WebElement searchcount;

	@FindBy(xpath = "//span[contains(text(),'Deactivate')]")
	WebElement deactivate;

	@FindBy(xpath = "//a[@class='settings_drop_icon']")
	WebElement settingsicon;

	@FindBy(xpath = "//span[contains(text(),'Active')]")
	WebElement active;

	@FindBy(xpath = "//span[contains(text(),'Inactive')]")
	WebElement inactive;

	@FindBy(xpath = "//*[@id='mat-select-1']/div/div[1]")
	WebElement itemcount;

	@FindBy(xpath = "//*[@id='mat-option-3']/span")
	WebElement itemsperpage25;

	@FindBy(xpath = "//*[@id='mat-option-4']/span")
	WebElement itemsperpage50;

	@FindBy(xpath = "//*[@id='mat-option-5']/span")
	WebElement itemsperpage100;

	@FindBy(xpath = "//div[@class='mat-paginator-range-label']")
	WebElement rangelabel;

	@FindBy(xpath = "//button[2][@type='button']")
	WebElement paginationbtn;

	@FindBy(xpath = "//a[contains(text(),'newclint')]")
	WebElement newclient;

	public String validateAddclient() {
		System.out.println(addclient.getText());
		return addclient.getText();
	}

	public String validatemsg() throws InterruptedException {

		String Valmsg = null;

		Valmsg = validationmsg.getText();
		JavaScriptUtil.drawBorder(validationmsg, driver);
		System.out.println(Valmsg);

		return Valmsg;

	}

	public void addclient(String logoname, String ftName, String ltName, String mail, String isd, String mobileno,
			String cmpany, String timezone, String paymntplan, String validation)
			throws InterruptedException, AWTException {

		String requiredCity = null;
		String logo;

		logo = System.getProperty("user.dir") + "\\src\\main\\resources\\Logo\\" + logoname;

		if (logoname != "") {

			JavaScriptUtil.drawBorder(Logo, driver);
			setClipboardData(logo);

			/*
			 * Actions builder = new Actions(driver); Action myAction =
			 * builder.click(Logo).release().build(); myAction.perform();
			 */

			Logo.click();

			Robot rbt = new Robot();
			rbt.delay(2000);
			rbt.keyPress(KeyEvent.VK_CONTROL);
			rbt.keyPress(KeyEvent.VK_V);
			rbt.keyRelease(KeyEvent.VK_V);
			rbt.keyRelease(KeyEvent.VK_CONTROL);
			rbt.keyPress(KeyEvent.VK_ENTER);
			rbt.keyRelease(KeyEvent.VK_ENTER);
			rbt.delay(2000);
		}

		fname.sendKeys(ftName);
		lname.sendKeys(ltName);
		email.sendKeys(mail);

		isdcode.click();
		if (isd.contains(".")) {
			isd = isd.substring(0, isd.indexOf('.'));
			requiredCity = isd.trim();

			// WebElement ISD = driver
			// .findElement(By.xpath("//span[@class='mat-option-text'][contains(text(),'" +
			// requiredCity + "')]"));
			// ISD.click();

			// List<WebElement> ISD =
			// driver.findElements(By.xpath("//span[@class='mat-option-text'][contains(text(),'"
			// + requiredCity + "')]"));
			// for (int i = 0; i < ISD.size(); i++) {
			// if (ISD.get(i).getText().equals(requiredCity)) {
			// ISD.get(i).click();
			// break;
			// }
			// }

			List<WebElement> ISD = driver.findElements(
					By.xpath("//span[@class='mat-option-text'][contains(text(),'" + requiredCity + "')]"));
			System.out.println(ISD.size());

			for (WebElement ele : ISD) {
				System.out.println(ele.getText());
				System.out.println(requiredCity);

				if (ele.getText().equals(requiredCity)) {
					ele.click();
					break;
				}
			}

		}

		mobile.sendKeys(mobileno);
		company.sendKeys(cmpany);

		TimeZone.click();
		WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'" + timezone + "')]"));
		ele.click();

		System.out.println(paymntplan.isEmpty());
		if (paymntplan.isEmpty()) {
			savebtn.click();

		} else {
			paymentplan.click();
			// driver.findElement(By.xpath("//span[contains(text(),'" + paymntplan +
			// "')]")).click();

			WebElement PP = driver.findElement(By.xpath("//span[contains(text(),'" + paymntplan + "')]"));
			PP.click();

		}

		savebtn.click();

	}

	public static void setClipboardData(String string) {

		StringSelection stringSelection = new StringSelection(string);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

	}

	public String deActivateClient() throws InterruptedException {

		search();
		Thread.sleep(2000);

		settingsicon.click();
		Thread.sleep(2000);
		deactivate.click();
		// System.out.println(searchcount.getText());
		Thread.sleep(4000);
		System.out.println(searchcount.getText());
		return searchcount.getText();
	}

	public String deActivateClientSearch() throws InterruptedException {
		active.click();
		inactive.click();
		search();
		Thread.sleep(2000);
		System.out.println(searchcount.getText());
		return searchcount.getText();
	}

	public void search() throws InterruptedException {
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, 60); // wait for 5 seconds
		wait.until(ExpectedConditions.visibilityOf(search));

		search.click();
		search.sendKeys(prop.getProperty("addedclient"));
		search.sendKeys(Keys.ENTER);

		/*
		 * actions.sendKeys(Keys.ENTER); actions.moveToElement(addclient);
		 * actions.click(); actions.build().perform();
		 */

		/*
		 * Actions builder = new Actions(driver); Action seriesofaction =
		 * builder.moveToElement(search).click().sendKeys(search,
		 * prop.getProperty("addedclient")) .sendKeys(Keys.ENTER).build();
		 * seriesofaction.perform();
		 */

	}

	public void itemsperpage(String cnt) throws InterruptedException, UnsupportedCommandException {

		itemcount.click();

		if (cnt.equals("25")) {
			System.out.println("25");
			itemsperpage25.click();
			Thread.sleep(2000);
		} else if (cnt.equals("50")) {
			System.out.println("50");
			itemsperpage50.click();
			Thread.sleep(2000);
		} else {
			System.out.println("Clicking 100");
			itemsperpage100.click();
			Thread.sleep(2000);
		}

	}

	public String validatesClientsearchresult() {
		try {
			search();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(searchcount.getText());

		if (searchcount.getText().equals("0")) {
			System.out.println(validatenoclient.getText());
			txt = validatenoclient.getText();
			System.out.println(txt);
		}

		return searchcount.getText();
		// TODO Auto-generated method stub
		// return null;
	}

	public boolean items25() throws InterruptedException {
		int rows_count;
		List<WebElement> rows_table = driver.findElements(By.xpath("//mat-row[@class='mat-row ng-star-inserted']"));

		rows_count = rows_table.size();
		System.out.println("Total rows in the grid is" + rows_count);

		if (rows_count > 0) {

			if (rows_count > 1 && rows_count <= 25) {
				count = true;
			} else {
				throw new SkipException("Skipping Item25 as no data available.");
			}
		} else {
			throw new SkipException("Skipping as no client data available.");
		}

		JavaScriptUtil.scrollIntoView(rangelabel, driver);
		JavaScriptUtil.drawBorder(rangelabel, driver);

		Thread.sleep(3000);

		return count;
	}

	public boolean items50() throws InterruptedException {
		int rows_count;
		List<WebElement> rows_table = driver.findElements(By.xpath("//mat-row[@class='mat-row ng-star-inserted']"));

		rows_count = rows_table.size();
		System.out.println("Total rows in the grid is" + rows_count);

		if (rows_count > 0) {

			if (rows_count > 25 && rows_count <= 50) {
				count = true;
			} else {
				throw new SkipException("Skipping Item50 as no data available.");
			}
		} else {
			throw new SkipException("Skipping as no client data available.");
		}

		JavaScriptUtil.scrollIntoView(rangelabel, driver);
		JavaScriptUtil.drawBorder(rangelabel, driver);

		Thread.sleep(3000);

		return count;
	}

	public boolean items100() throws InterruptedException {
		int rows_count;
		List<WebElement> rows_table = driver.findElements(By.xpath("//mat-row[@class='mat-row ng-star-inserted']"));

		rows_count = rows_table.size();
		System.out.println("Total rows in the grid is" + rows_count);

		if (rows_count > 0) {

			if (rows_count > 50 && rows_count <= 100) {
				count = true;
			} else {
				throw new SkipException("Skipping Item100 as no data available.");
			}
		} else {
			throw new SkipException("Skipping as no client data available.");
		}

		JavaScriptUtil.scrollIntoView(rangelabel, driver);
		JavaScriptUtil.drawBorder(rangelabel, driver);

		Thread.sleep(3000);
		return count;
	}

	public String pagination() throws InterruptedException {
		String pagetxt;
		String substrtxt;

		// Actions actions = new Actions(driver);
		// actions.moveToElement(itemcount).build().perform();

		System.out.println(paginationbtn.isEnabled());

		if (paginationbtn.isEnabled()) {
			paginationbtn.click();

			Thread.sleep(2000);

			pagetxt = rangelabel.getText();
			System.out.println(pagetxt);

			int firstIndex = pagetxt.indexOf('o');
			System.out.println("First occurrence of char 'o'" + " is found at : " + firstIndex);

			substrtxt = pagetxt.substring(0, firstIndex).trim();
			System.out.println(substrtxt);

		} else {
			throw new SkipException("Skipping there is no data available for pagination.");
		}
		JavaScriptUtil.scrollIntoView(rangelabel, driver);
		JavaScriptUtil.drawBorder(rangelabel, driver);
		Thread.sleep(3000);
		return substrtxt;

	}

	public AddVehiclePage clientclick() throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		newclient.click();

		Thread.sleep(1000);

		return new AddVehiclePage();
	}

}
