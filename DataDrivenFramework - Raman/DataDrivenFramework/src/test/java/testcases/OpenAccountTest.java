package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class OpenAccountTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void openAccount(String customer, String currency, String alertMsg) {

		click("openAccount_CSS");
		select("customer_CSS", customer);
		select("currency_CSS", currency);
		click("process_CSS");

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertMsg), "Account not created successfully");
		alert.accept();
		
		Assert.fail("Open Account Test failed");
	}

	@DataProvider
	public Object[][] getData() {

		return TestUtil.getData("OpenAccountTest");

	}

}
