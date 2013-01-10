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

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.gui_auto.beans.UsersListBean;
import com.gui_auto.dao.DBCon;
import com.gui_auto.pages.RegisterPage;
import com.gui_auto.pages.ThankYouPage;
import com.gui_auto.utilities.CommonFunctions;



public class WriteAndDownloadTest extends DBCon{

	static Logger log = Logger.getLogger(WriteAndDownloadTest.class.getName());
	private StringBuffer verificationErrors = new StringBuffer();

	DBCon dbConnection = new DBCon();

	private RegisterPage _register;
	private final static String TOOL_TIP_FIRSTNAME_XPATH = "//div[1]/div[3]/div/div/form/img[1]";
	private final static String TOOL_TIP_LASTNAME_XPATH = "//div[1]/div[3]/div/div/form/img[2]";

	ArrayList<UsersListBean> usersList;

	@Before
	public void setUp() throws Exception {
		super.setUp(getBrowser());
		PropertyConfigurator.configure("config/log4j.properties");
		usersList = dbConnection.loadDataFromExcel();
		_register = PageFactory.initElements(_driver, RegisterPage.class);
	}

	@Test
	public void testWriteAndDownloadFile() throws Exception {
		try{
			// Open URL in browser
			_register.navigateToPageAndWait();

			//Check hover message for first name field on question mark icon
			String firstNameHover = "Enter Your First Name"; 
			assertEquals(CommonFunctions.checkHoverMessage(_driver, By.xpath(TOOL_TIP_FIRSTNAME_XPATH)),firstNameHover);

			//Check hover message for last name field on question mark icon
			String lastNameHover = "Enter Your Last Name"; 
			assertEquals(CommonFunctions.checkHoverMessage(_driver, By.xpath(TOOL_TIP_LASTNAME_XPATH)), lastNameHover); 

			//Fill the registration form for only 1 user
			ThankYouPage thankYouPage = new ThankYouPage(_driver);
			thankYouPage = _register.validRegistration(usersList, 0);	

			//Write first name in excel file and verify it
			String writeFileName = "WriteExcelFile" + CommonFunctions.getRandomString(10);
			thankYouPage.writeExcelData(thankYouPage.assertFirstName(), writeFileName);

			// After registration we are downloading the uploaded file
			// Verify the xls file to download exists
			thankYouPage.verifyDownloadFileExists();

			// Download the file
			String downloadedFileName = "Downloadedfile"+ CommonFunctions.getRandomString(10);			
			String fileName = thankYouPage.fetchFileName();			
			String fileURL = "http://www.grazitti.com/qasite/thanks.php?task=download&file_name="+fileName;
			CommonFunctions.downloadFile(fileURL, downloadedFileName+".xls");
			
			log.info("Text written and file downloaded successfully");
		} 
		catch (Exception e) {
			log.error("Problem encountered", e);
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
