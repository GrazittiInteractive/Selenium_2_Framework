/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.TestScripts;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.gui_auto.base_classes.GUI_automation_base;
import com.gui_auto.beans.UsersListBean;
import com.gui_auto.dao.DBCon;
import com.gui_auto.pages.HomePage;
import com.gui_auto.pages.ImageGalleryPage;
import com.gui_auto.pages.RegisterPage;
import com.gui_auto.pages.ThankYouPage;



public class CheckDeleteCookieTest extends GUI_automation_base{

	static Logger log = Logger.getLogger(CheckDeleteCookieTest.class.getName());
	private StringBuffer verificationErrors = new StringBuffer();

	DBCon dbConnection = new DBCon();

    private RegisterPage _register;

	ArrayList<UsersListBean> usersList;

	@Before
	public void setUp() throws Exception {
		super.setUp(getBrowser());
		PropertyConfigurator.configure("config/log4j.properties");
		usersList = dbConnection.loadDataFromExcel();
		_register = PageFactory.initElements(_driver, RegisterPage.class);
	}

	@Test
	public void testDeleteCookies() throws Exception {

		try{

			// Open URL in browser
			_register.navigateToPageAndWait();

			//Fill the registration form for only 1 user
			ThankYouPage thankYouPage = new ThankYouPage(_driver);
			thankYouPage = _register.validRegistration(usersList, 0);	

			// Verify that welcome message appears correctly on thank you page
			assertEquals("Welcome "+usersList.get(0).getFirstName(),thankYouPage.assertUserGreeting());

			//Verify that logout link is displayed on thank you page
			assertTrue(thankYouPage.checkLogoutLink());

			//Delete all cookies
			_driver.manage().deleteAllCookies();

			//Go to home page
			HomePage homePage = new HomePage(_driver);
			homePage._tabs.clickElement("Home");

			// Verify that welcome guest is displayed on home page after cookies deletion
			assertEquals("Welcome Guest",thankYouPage.assertUserGreeting());

			//Go to image gallery page
			ImageGalleryPage galleryPage = new ImageGalleryPage(_driver);
			galleryPage._tabs.clickElement("Image Gallery");

			// Verify that welcome guest is displayed on gallery page after cookies deletion
			assertEquals("Welcome Guest",thankYouPage.assertUserGreeting());

			log.info("Cookies deleted successfully");
		} catch (AssertionError ex) {
			log.error("Exception", ex);
		}

	}

	@After
	public void tearDown() throws Exception {
		_driver.quit();

		String verificationErrorString = verificationErrors.toString();

		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}

