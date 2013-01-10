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
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.gui_auto.base_classes.GUI_automation_base;
import com.gui_auto.beans.UsersListBean;
import com.gui_auto.dao.DBCon;
import com.gui_auto.pages.InformationPage;
import com.gui_auto.pages.RegisterPage;

public class SortingTest extends GUI_automation_base {
	static Logger log = Logger.getLogger(SortingTest.class.getName());
	private StringBuffer verificationErrors = new StringBuffer();

	DBCon dbConnection = new DBCon();

	private RegisterPage _register;

	ArrayList<UsersListBean> usersList;

	private final static String TABLE_ROWS_XPATH = "//div[1]/div[3]/div[2]/table/tbody/tr";

	private final static String TABLE_COLUMNS_XPATH = "//div[1]/div[3]/div[2]/table/thead/tr/td";

	@Before
	public void setUp() throws Exception {
		super.setUp(getBrowser());
		PropertyConfigurator.configure("config/log4j.properties");
		_register = PageFactory.initElements(_driver, RegisterPage.class);
	}

	@Test
	public void testSorting() throws Exception {

		try{

			// Open URL in browser
			_register.navigateToPageAndWait();

			//Go to information page
			InformationPage informationPage = new InformationPage(_driver);
			informationPage._tabs.clickElement("Information");	    

			//Information page opens in a new tab, therefore handling a new window	
			RegisterPage registerPage = new RegisterPage(_driver);
			informationPage = registerPage.handleNewTabWindow();

			//Fetch the number of rows in the table to sort
			List<WebElement> rows = _driver.findElements(By.xpath(TABLE_ROWS_XPATH));

			//Fetch the number of columns in the table to sort
			List<WebElement> columns = _driver.findElements(By.xpath(TABLE_COLUMNS_XPATH));

			//Sorting for 3 columns 
			for(int column=1;column <=columns.size();column++){

				//Click on the column header to sort the column values
				String COLUMNS_XPATH = "//div[1]/div[3]/div[2]/table/thead/tr/td["+column+"]/a";
				_driver.findElement(By.xpath(COLUMNS_XPATH)).click();                        

				// Add the sorted order of values in a list
				List<String> actualsortedlist = new ArrayList(); 	

				for(int row=1;row <= rows.size();row++){
					String ROWS_XPATH = "//div[1]/div[3]/div[2]/table/tbody/tr["+row+"]/td["+column+"]";
					actualsortedlist.add(_driver.findElement(By.xpath(ROWS_XPATH)).getText().toLowerCase());
				}

				//Get the expected sort order
				List<String> expectedsortedlist = new ArrayList<String>(actualsortedlist);	
				Collections.copy(expectedsortedlist, actualsortedlist);
				Collections.sort(expectedsortedlist);

				//Compare the actual and expected sort values
				for(int compare=0;compare < rows.size();compare++)
					assertEquals(actualsortedlist.get(compare),expectedsortedlist.get(compare));	

				log.info("Values sorted correctly for "+ column +" column");		
			}
		}
		catch  (Exception e)
		{
			log.error("Values not sorted", e);
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


