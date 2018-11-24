package com.avlview.app.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.avlview.app.utilities.ExcelReader;
import com.avlview.app.utilities.TestUtil;
import com.avlview.app.utilities.WebEventListener;

public class TestBase {

	public static WebDriver driver;

	public static Properties OR = new Properties();
	public static Properties prop = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	// public static Logger log = Logger.getLogger("devpinoyLogger");

	public TestBase() throws IOException {

		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\avlview\\app\\config\\config.properties");
		prop.load(fis);

		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\avlview\\app\\config\\OR.properties");
		OR.load(fis);

		excel = new ExcelReader(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\avlview\\app\\testdata\\testdata.xlsx");

	}

	public static void Initialization() throws IOException {
		String Browsername = prop.getProperty("browser");

		if (Browsername.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (Browsername.equals("FF")) {
			System.setProperty("webdriver.geko.driver",
					System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.Imp_wait, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));

	}

}
