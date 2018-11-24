package rough;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NewTest1 {

	public static WebDriver driver;

	// @Test(retryAnalyzer = com.avlview.Analyzer.RetryAnalyzer.class)
	@Test
	public void launchBrowser() throws InterruptedException {

		// System.out.println("launching firefox browser");

		// System.setProperty("webdriver.gecko.driver", "C:/2018/geckodriver.exe");
		// driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", "C:/2018/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://app.avlview.com");

		// Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@id='blinkLogInText']")).sendKeys("test");
		// driver.findElement(By.xpath("//div[3]/span[2]/a")).click();

		// driver.quit();

	}

}
