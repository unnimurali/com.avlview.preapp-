package com.avlview.app.pages;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import com.avlview.app.base.TestBase;

public class AddTaxPage extends TestBase {

	public AddTaxPage() throws IOException {
		super();
		// TODO Auto-generated constructor stub

		PageFactory.initElements(driver, this);
	}

}
