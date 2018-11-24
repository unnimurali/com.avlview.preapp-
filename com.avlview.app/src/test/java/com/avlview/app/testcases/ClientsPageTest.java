package com.avlview.app.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.avlview.app.base.TestBase;
import com.avlview.app.pages.ClientsPage;
import com.avlview.app.pages.LoginPage;

public class ClientsPageTest extends TestBase {

	LoginPage lp;
	ClientsPage cp;

	public ClientsPageTest() throws IOException {
		super();

	}

	@BeforeMethod
	public void setup() throws IOException {
		Initialization();
		lp = new LoginPage();
		cp = lp.login(prop.getProperty("uname"), prop.getProperty("pwd"));
	}

	@Test(priority = 1, enabled = false)
	public void validateclientpageExistTest() {
		String validateclientpage = cp.validateClientspage();
		Assert.assertEquals(validateclientpage, "Clients");
	}

	@Test(priority = 2, enabled = false)
	public void validatePartnerLogoExistTest() throws InterruptedException {
		boolean validatePartnerLogo = cp.validatelogo();
		Assert.assertTrue(validatePartnerLogo);
	}

	@Test(priority = 3, enabled = false)
	public void validateAddclientExistTest() {
		String validateaddclient = cp.validateAddclient();
		Assert.assertEquals(validateaddclient, "ADD CLIENT");
	}

	@Test(priority = 4, enabled = false)
	public void validateSearchExistTest() {
		boolean validateSearch = cp.validateSearch();
		Assert.assertTrue(validateSearch);
	}

	@Test(priority = 5, enabled = false)
	public void validateSearchResultTest() throws InterruptedException {
		String validateSearchResult = cp.validatesExistingclientearchresult();
		Assert.assertEquals(validateSearchResult, "1");
	}

	@Test(priority = 6, enabled = false)
	public void validateTotalTabsTest() throws InterruptedException {
		int validateTabcount = cp.validatetotaltabs();
		Assert.assertEquals(validateTabcount, 3);

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
