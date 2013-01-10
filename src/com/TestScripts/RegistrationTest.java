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

import com.gui_auto.beans.UsersListBean;
import com.gui_auto.dao.DBCon;
import com.gui_auto.navigations.BottomNavigationPanel;
import com.gui_auto.pages.RegisterPage;
import com.gui_auto.pages.ThankYouPage;

public class RegistrationTest extends DBCon{

	static Logger log = Logger.getLogger(RegistrationTest.class.getName());
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
	public void testValidRegister() throws Exception {

		assert !_users.isEmpty();

		// Open URL in browser
		_register.navigateToPageAndWait();

		// Verify that bottom navigation is present on this page
		BottomNavigationPanel bottomNav = new BottomNavigationPanel(_driver);
        assertTrue(bottomNav.isNavPresent());


		for (int i = 0; i < usersList.size(); i++) {

			try {
				ThankYouPage thankYouPage = new ThankYouPage(_driver);
				
				// Register into the site
				// Parameterization of different input fields - text boxes, radio button, check boxes, drop downs, file upload, etc
				// Also parameterization of auto-suggest search drop downs
				// Please mention the location of the file to download in the input excel sheet
				thankYouPage = _register.validRegistration(usersList, i);

				// Verify that welcome message appears correctly
				assertEquals("Welcome "+usersList.get(i).getFirstName(),thankYouPage.assertUserGreeting());

				//Verify that logout link is displayed
				assertTrue(thankYouPage.checkLogoutLink());

				// Verify that correct first name is displayed
				assertEquals(usersList.get(i).getFirstName(),thankYouPage.assertFirstName());

				// Verify that correct last name is displayed
				assertEquals(usersList.get(i).getLastName(),thankYouPage.assertLastName());

				// Verify that correct phone number is displayed
				assertEquals(usersList.get(i).getPhoneNumber(),thankYouPage.assertPhoneNumber());

				// Verify that correct gender is displayed
				assertEquals(usersList.get(i).getGender(),thankYouPage.assertGender());

				// Verify that correct industry is displayed
				assertEquals(usersList.get(i).getIndustry(),thankYouPage.assertIndustry());

				// Verify that correct country is displayed
				assertEquals(usersList.get(i).getCountry(),thankYouPage.assertCountry());

				log.info("User "+usersList.get(i).getFirstName()+" has registered successfully");
			} catch (AssertionError ex) {
				log.error("Value does not match", ex);
			}
			// Logout from site
			_register.logout();
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
