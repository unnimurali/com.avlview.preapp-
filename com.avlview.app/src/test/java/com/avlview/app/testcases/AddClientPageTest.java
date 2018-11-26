package com.avlview.app.testcases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.avlview.app.base.TestBase;
import com.avlview.app.pages.AddClientPage;
import com.avlview.app.pages.ClientsPage;
import com.avlview.app.pages.LoginPage;
import com.avlview.app.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

import rough.NewTest1;

public class AddClientPageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;
	AddClientPage ac;

	public AddClientPageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeMethod
	public void setup() throws IOException {
		Initialization();
		lp = new LoginPage();
		cp = lp.login(prop.getProperty("uname"), prop.getProperty("pwd"));
		// cp=lp.login("mapview", "mapview");
		ac = new AddClientPage();
	}

	@Test(priority = 1, enabled = true)
	public void TemplateTest() throws IOException, InterruptedException {

		extentTest = extent.startTest("TemplateTest");

		String validateSearchResult = ac.validatesClientsearchresult();
		String noclienttemplatetxt = AddClientPage.txt;
		System.out.println(noclienttemplatetxt);
		Assert.assertEquals(noclienttemplatetxt, "CREATE YOUR FIRST CLIENT");
		Assert.assertEquals(validateSearchResult, "0");
	}

	@Test(priority = 2, enabled = true)
	public void ClickAddClientTest() throws IOException {

		extentTest = extent.startTest("ClickAddClientTest");

		ac = cp.addclientclick();
		String validateaddclient = ac.validateAddclient();
		Assert.assertEquals(validateaddclient, "Add Client");
	}

	/*
	 * @Test(priority = 3, enabled = true) public void AddclientTest() throws
	 * IOException, InterruptedException, AWTException { ac = cp.addclientclick();
	 * ac.addclient(); }
	 */

	@Test(priority = 3, enabled = false, dataProvider = "getData")
	public void AddclientTest(Hashtable<String, String> data) throws IOException, InterruptedException, AWTException {
		ac = cp.addclientclick();
		// System.out.println(data.get("isd").substring(0, 2));

		ac.addclient(data.get("logo"), data.get("firstname"), data.get("lastname"), data.get("email"), data.get("isd"),
				data.get("mobile"), data.get("company"), data.get("timezone"), data.get("paymentplan"),
				data.get("validation"));

		String validatefnmsg = ac.validatemsg();
		// System.out.println(validatefnmsg);
		// System.out.println(data.get("validation"));
		Assert.assertEquals(validatefnmsg, data.get("validation"));

	}

	@Test(priority = 4, enabled = false)
	public void SearchAddedClientTest() throws IOException, InterruptedException, AWTException {
		// ac=cp.addclientclick();
		String validateSearchResult = ac.validatesClientsearchresult();
		Assert.assertEquals(validateSearchResult, "1");
	}

	@Test(priority = 5, enabled = false)
	public void deActivateClientTest() throws IOException, InterruptedException, AWTException {
		// ac=cp.addclientclick();
		String validateSearchResult = ac.deActivateClient();
		Assert.assertEquals(validateSearchResult, "0");
	}

	@Test(priority = 6, enabled = true)
	public void deactivateSearchTest() throws InterruptedException {

		extentTest = extent.startTest("deactivateSearchTest");

		String validateSearchResult = ac.deActivateClientSearch();
		Assert.assertEquals(validateSearchResult, "1");
	}

	@Test(priority = 7, enabled = false)
	public void items25Test() throws InterruptedException {
		boolean cnt = ac.itemsperpage("25");
		Assert.assertTrue(cnt);
	}

	@Test(priority = 8, enabled = false)
	public void items50Test() throws InterruptedException {
		boolean cnt = ac.itemsperpage("50");
		Assert.assertTrue(cnt);
	}

	@Test(priority = 9, enabled = false)
	public void items100Test() throws InterruptedException {
		boolean cnt = ac.itemsperpage("100");
		Assert.assertTrue(cnt);
	}

	@Test(priority = 10, enabled = false)
	public void PaginationTest() throws InterruptedException {
		String paginationtxt = ac.pagination();
		Assert.assertEquals(paginationtxt, "101 - 200");
	}

	@DataProvider
	public static Object[][] getData() {

		return TestUtil.getData("Addclient");
	}

	@AfterMethod
	public void teardown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test case failed is" + result.getName());
			extentTest.log(LogStatus.FAIL, "Test case failed is" + result.getThrowable());

			String screenshotPath = NewTest1.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}

		extent.endTest(extentTest);

		driver.quit();

	}

}
