/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.gui_auto.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.GUI_automation_base;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.beans.UsersListBean;
import com.gui_auto.utilities.CommonFunctions;

public class RegisterPage implements BaseElement{

	private final static String FIRSTNAME_XPATH = "//div[1]/div[3]/div/div/form/input[1]";
	private final static String LASTNAME_XPATH= "//div[1]/div[3]/div/div/form/input[2]";
	private final static String PHONENUMBER_XPATH= "//div[1]/div[3]/div/div/form/input[3]";
	private final static String GENDER_XPATH= "//div[1]/div[3]/div/div/form/div[1]/div[2]/input";
	private final static String INDUSTRY_XPATH = "//div[1]/div[3]/div/div/form/select[1]";
	private final static String COUNTRY_ID ="tags";
	private final static String EDUCATION_XPATH="//div[1]/div[3]/div/div/form/select[2]";
	private final static String COURSE_XPATH ="//div[1]/div[3]/div/div/form/select[3]";
	private final static String ABOUTME_XPATH ="//div[1]/div[3]/div/div/form/textarea";
	private final static String HOBBY_XPATH ="//div[1]/div[3]/div/div/form/div[3]/div[2]/input";
	private final static String BROWSE_BUTTON_XPATH="//div[1]/div[3]/div/div/form/input[4]";  
	private final static String LOGOUT_XPATH = "//div[1]/div[3]/div/table/tbody/tr/td[2]/div/a";
	private final static String SUBMIT_BUTTON_XPATH = "//div[1]/div[3]/div/div/form/div[4]/input[1]";

	private String _pageURL;

	protected WebDriver _driver;

	protected static GUI_automation_properties _properties = new GUI_automation_properties();

	public RegisterPage(final WebDriver driver) {
		_driver = driver;
		_pageURL = GUI_automation_base.getUrl();
	}

	/**
	 * Action: Register to website
	 * 
	 */
	public ThankYouPage validRegistration(ArrayList<UsersListBean> users, int i) {
		navigateToPageAndWait();
		populateFields(users, i);
		_driver.findElement(By.xpath(SUBMIT_BUTTON_XPATH)).click();
		ThankYouPage thankyouPage = new ThankYouPage(_driver);
		return thankyouPage;
	}

	public void navigateToPageAndWait() {
		_driver.get(_pageURL);
	}

	/**
	 * Action:  Logout of site
	 * @throws InterruptedException 
	 * 
	 */
	public void logout() {
		_driver.findElement(By.xpath(LOGOUT_XPATH)).click();
		waitForPage();
	}

	/**
	 * Handle a new tab
	 * 
	 */
	public InformationPage handleNewTabWindow()

	{
		CommonFunctions.handleNewTab(_driver);	
		InformationPage informationPage = new InformationPage(_driver);
		return informationPage;
	}	

	/**
	 * Fill in registration form fields - firstname, lastname, phone number, ...etc
	 * 
	 */
	private void populateFields(ArrayList<UsersListBean> users, int i) {
		
		//populate text box/area
		CommonFunctions.populateField(_driver, By.xpath(FIRSTNAME_XPATH), users.get(i).getFirstName());
		CommonFunctions.populateField(_driver, By.xpath(LASTNAME_XPATH), users.get(i).getLastName());
		CommonFunctions.populateField(_driver, By.xpath(PHONENUMBER_XPATH), users.get(i).getPhoneNumber());
		
		//select radio button
		CommonFunctions.selectRadioButton(_driver, By.xpath(GENDER_XPATH), users.get(i).getGender());
		
		//select drop down
		CommonFunctions.selectDropdown(_driver, By.xpath(INDUSTRY_XPATH), users.get(i).getIndustry());
		
		//select auto suggest search drop down
		CommonFunctions.selectSearchDropdown(_driver, By.id(COUNTRY_ID), users.get(i).getCountry());
		
		//select drop down
		CommonFunctions.selectDropdown(_driver, By.xpath(EDUCATION_XPATH), users.get(i).getEducation());
		CommonFunctions.selectDropdown(_driver, By.xpath(COURSE_XPATH), users.get(i).getCourse());
		
		//select multiple check boxes
		CommonFunctions.selectCheckboxes(_driver, By.xpath(HOBBY_XPATH), users.get(i).getHobby());
		
		//uploads file
		CommonFunctions.uploadFile(_driver, By.xpath(BROWSE_BUTTON_XPATH), users.get(i).getFile());
		
		//populate text box/area
		CommonFunctions.populateField(_driver, By.xpath(ABOUTME_XPATH), users.get(i).getAboutMe());

	}

	@Override
	public boolean waitForPage() {
		return false;
	}

	@Override
	public boolean onPage() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return false;
	}

}
