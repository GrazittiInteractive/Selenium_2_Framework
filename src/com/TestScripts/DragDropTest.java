/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.TestScripts;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.gui_auto.base_classes.GUI_automation_base;
import com.gui_auto.beans.UsersListBean;
import com.gui_auto.dao.DBCon;
import com.gui_auto.pages.InformationPage;
import com.gui_auto.pages.RegisterPage;


public class DragDropTest extends GUI_automation_base {
	static Logger log = Logger.getLogger(DragDropTest.class.getName());
	private StringBuffer verificationErrors = new StringBuffer();

	DBCon dbConnection = new DBCon();

	private RegisterPage _register;

	ArrayList<UsersListBean> usersList;

	private final static String DRAG_ELEMENT_XPATH = "//div[1]/div[3]/div[1]/table/tbody/tr[2]/td[1]/ul/li[1]";

	private final static String DROP_ELEMENT_XPATH = "//div[1]/div[3]/div[1]/table/tbody/tr[2]/td[2]/ul/li[3]";

	private final static String TABLE_1_ID = "sortable1";

	private final static String TABLE_2_ID = "sortable2";


	@Before
	public void setUp() throws Exception {
		super.setUp(getBrowser());
		PropertyConfigurator.configure("config/log4j.properties");
		_register = PageFactory.initElements(_driver, RegisterPage.class);
	}

	@Test
	public void testDragDrop() throws Exception {

		try{

			// Open URL in browser
			_register.navigateToPageAndWait();

			//Go to information page
			InformationPage informationPage = new InformationPage(_driver);
			informationPage._tabs.clickElement("Information");

			//Information page opens in a new tab, therefore handling a new window	
			RegisterPage registerPage = new RegisterPage(_driver);
			informationPage = registerPage.handleNewTabWindow();

			//Drag and drop element from table 1 to table 2
			WebElement dragElement=_driver.findElement(By.xpath(DRAG_ELEMENT_XPATH));  
			WebElement dropElement=_driver.findElement(By.xpath(DROP_ELEMENT_XPATH));  
			(new Actions(_driver)).dragAndDrop(dragElement, dropElement).perform();

			//Get the drag element's text
			String dragElementText = dragElement.getText();

			//Verify that drag element is not present in table 1
			assertFalse(_driver.findElement(By.id(TABLE_1_ID)).getText().contains(dragElementText));

			//Verify that drag element is present in table 2
			assertTrue(_driver.findElement(By.id(TABLE_2_ID)).getText().contains(dragElementText));

			log.info("Drag successful");

		}catch  (Exception e)
		{
			log.error("Drag not successful", e);
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


