package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class BankManagerLoginTest extends BaseTest{
	
	
	@Test
	public void loginAsBankManager() {
		
		
		click("bmlBtn_CSS");
		Assert.assertTrue(isElementPresent("addCustomerBtn_CSS"),"Add Customer Button not found");
		//Assert.fail("Bank Manager Login Test failed");
		
		
	}

}
