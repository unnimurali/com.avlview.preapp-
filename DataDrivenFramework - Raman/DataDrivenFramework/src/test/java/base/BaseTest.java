package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.ExcelReader;
import utilities.MonitoringMail;

public class BaseTest {

	/*
	 * WebDriver logs testNg mail properties Excel JDBC -- tomorrow ReportNG -
	 * Listeners Wait - Explicit and Implicit
	 * 
	 * 
	 */

	public static WebDriver driver;
	public static Logger log = Logger.getLogger(BaseTest.class);
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static MonitoringMail mail = new MonitoringMail();
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "//src//test//resources//excel//testdata.xlsx");
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public static WebElement dropdown;

	public static void click(String locator) {

		try {
			if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator))).click();
			} else if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator))).click();
			}

			log.info("Clicking on an Element : " + locator);
		} catch (Throwable t) {

			// capturing screenshot
			log.info("Error while clicking on an Element : " + locator + " Exception message : " + t.getMessage());
		}
	}
	
	
	
	public static void select(String locator,String value) {

		
		
		try {
			if (locator.endsWith("_XPATH")) {
				dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
			} else if (locator.endsWith("_CSS")) {
				dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			} else if (locator.endsWith("_ID")) {
				dropdown = driver.findElement(By.id(OR.getProperty(locator)));
			}

			log.info("selecting an Element : " + locator+" with option value as "+value);
		} catch (Throwable t) {

			// capturing screenshot
			log.info("Error while selecting an Element : " + locator + " Exception message : " + t.getMessage());
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean isElementPresent(String locator) {

		try {
			if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator)));
			} else if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator)));
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator)));
			}

			
			log.info("Finding an Element : " + locator);
			return true;
		} catch (Throwable t) {

			// capturing screenshot
			log.info("Error while finding an Element : " + locator + " Exception message : " + t.getMessage());
			return false;
		}
	}
	
	
	
	

	public static void type(String locator, String value) {

		try {
			if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			} else if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
			}
			log.info("Typing in an Element: " + locator + " entered the value as : " + value);
		} catch (Throwable t) {

			// capture screenshot
			log.info("Error while typing in an Element " + locator + " Exception message : " + t.getMessage());
			// Assert.fail("Error while typing in an Element "+locator,t);

		}
	}

	@BeforeSuite
	public void setUp() {

		if (driver == null) {

			PropertyConfigurator
					.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "//src//test//resources//properties//Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Config.load(fis);
				log.info("Config properties loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "//src//test//resources//properties//OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				OR.load(fis);
				log.info("OR properties loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Config.getProperty("browser").equals("firefox")) {

				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
				driver = new FirefoxDriver();
				log.info("Firefox Launched !!!");
			} else if (Config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
				driver = new ChromeDriver();
				log.info("Chrome Launched !!!");
			} else if (Config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "//src//test//resources//executables//IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				log.info("IE Launched !!!");
			}

			driver.get(Config.getProperty("testsiteurl"));
			log.info("Navigated to : " + Config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));

		}

	}

	@AfterSuite
	public void tearDown() {

		driver.quit();
		log.info("Test Execution Completed");

	}
}
