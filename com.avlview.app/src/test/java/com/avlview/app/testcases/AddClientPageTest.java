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

public class AddClientPageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;
	AddClientPage ac;

	public AddClientPageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeMethod()
	public void setup() throws IOException {
		Initialization();
		lp = new LoginPage();
		cp = lp.login(prop.getProperty("uname"), prop.getProperty("pwd"));
		// cp=lp.login("mapview", "mapview");

		if (cp == null) {

			// driver.quit();
			// throw new SkipException("Skipping tests because login not available.");
			Assert.fail("Login failed");

		}

		ac = new AddClientPage();

	}

	@Test(priority = 1, enabled = false)
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

		extentTest = extent.startTest("AddclientTest");

		ac = cp.addclientclick();
		// System.out.println(data.get("isd").substring(0, 2));

		ac.addclient(data.get("logo"), data.get("firstname"), data.get("lastname"), data.get("email"), data.get("isd"),
				data.get("mobile"), data.get("company"), data.get("timezone"), data.get("paymentplan"),
				data.get("validation"));

		String validatefnmsg = ac.validatemsg();
		System.out.println(validatefnmsg);

		// System.out.println(data.get("validation"));
		// Assert.assertEquals(validatefnmsg, data.get("validation"));

	}

	@Test(priority = 4, enabled = false)
	public void SearchAddedClientTest() throws IOException, InterruptedException, AWTException {
		extentTest = extent.startTest("SearchAddedClientTest");
		// ac=cp.addclientclick();
		String validateSearchResult = ac.validatesClientsearchresult();
		Assert.assertEquals(validateSearchResult, "1");
	}

	@Test(priority = 5, enabled = false)
	public void deActivateClientTest() throws IOException, InterruptedException, AWTException {
		extentTest = extent.startTest("deActivateClientTest");
		// ac=cp.addclientclick();
		String validateSearchResult = ac.deActivateClient();
		Assert.assertEquals(validateSearchResult, "0");
	}

	@Test(priority = 6, enabled = false)
	public void deactivateSearchTest() throws InterruptedException {

		extentTest = extent.startTest("deactivateSearchTest");

		String validateSearchResult = ac.deActivateClientSearch();
		Assert.assertEquals(validateSearchResult, "1");
	}

	@Test(priority = 7, enabled = false)
	public void items25Test() throws InterruptedException {
		extentTest = extent.startTest("items25Test");

		// boolean cnt = ac.itemsperpage("25");
		// Assert.assertTrue(cnt);

		ac.itemsperpage("25");
		boolean cnt = ac.items25();
		Assert.assertTrue(cnt);

		/*
		 * String result = ac.itemsperpage("25"); Assert.assertEquals(result, "1 - 25");
		 */

	}

	@Test(priority = 8, enabled = false)
	public void items50Test() throws InterruptedException {
		extentTest = extent.startTest("items50Test");

		// boolean cnt = ac.itemsperpage("50");
		// Assert.assertTrue(cnt);

		ac.itemsperpage("50");
		boolean cnt = ac.items50();
		Assert.assertTrue(cnt);

		/*
		 * String result = ac.itemsperpage("50"); Assert.assertEquals(result, "1 - 50");
		 */
	}

	@Test(priority = 9, enabled = false)
	public void items100Test() throws InterruptedException {
		extentTest = extent.startTest("items100Test");

		ac.itemsperpage("100");
		boolean cnt = ac.items100();
		Assert.assertTrue(cnt);

	}

	@Test(priority = 10, enabled = false)
	public void PaginationTestfor25Items() throws InterruptedException {
		extentTest = extent.startTest("PaginationTest");
		ac.itemsperpage("25");
		String paginationtxt = ac.pagination();
		Assert.assertEquals(paginationtxt, "26 - 50");
	}

	@Test(priority = 11, enabled = false)
	public void PaginationTestfor50Items() throws InterruptedException {
		extentTest = extent.startTest("PaginationTest");
		ac.itemsperpage("50");
		String paginationtxt = ac.pagination();
		Assert.assertEquals(paginationtxt, "51 - 100");
	}

	@Test(priority = 12, enabled = false)
	public void PaginationTestfor100Items() throws InterruptedException {
		extentTest = extent.startTest("PaginationTest");
		ac.itemsperpage("100");
		String paginationtxt = ac.pagination();
		Assert.assertEquals(paginationtxt, "101 - 200");
	}

	@DataProvider
	public static Object[][] getData() {

		return TestUtil.getData("Addclient");
	}

	@AfterMethod(alwaysRun = true)
	public void teardown(ITestResult result) throws IOException {

		System.out.println(result.getStatus());

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test case failed is" + result.getName());
			extentTest.log(LogStatus.FAIL, "Test case failed is" + result.getThrowable());

			String screenshotPath = TestBase.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));

		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());

			String screenshotPath = TestBase.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.SKIP, extentTest.addScreenCapture(screenshotPath));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}

		extent.endTest(extentTest);

		driver.quit();

	}

}
