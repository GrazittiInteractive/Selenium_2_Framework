/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.gui_auto.pages;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.gui_auto.utilities.CommonFunctions;

public class ThankYouPage extends BasePage{

	static Logger log = Logger.getLogger(ThankYouPage.class.getName());

	private final static String LOGOUT_ID = "log_out";
	private final static String FIRSTNAME_XPATH = "//div[1]/div[3]/div/form/span[1]";
	private final static String LASTNAME_XPATH = "//div[1]/div[3]/div/form/span[2]";
	private final static String PHONENUMBER_XPATH = "//div[1]/div[3]/div/form/span[3]";
	private final static String GENDER_XPATH = "//div[1]/div[3]/div/form/span[4]";
	private final static String INDUSTRY_XPATH = "//div[1]/div[3]/div/form/span[5]";
	private final static String COUNTRY_XPATH = "//div[1]/div[3]/div/form/span[6]";
	private final static String FILENAME_XPATH  = "//div[1]/div[3]/div/form/span[10]";
	private final static String WELCOME_MSG_ID = "P";

	public ThankYouPage(WebDriver driver) {  
		super();  
		PropertyConfigurator.configure("config/log4j.properties");
		this._driver = driver;  
	}  

	/**
	 * Checks the user greeting
	 * 
	 */
	public String assertUserGreeting() throws Exception {  
		String welcome_msg = _driver.findElement(By.id(WELCOME_MSG_ID)).getText();  
		return welcome_msg;
	} 

	/**
	 * Writes first name to the excel sheet
	 * 
	 */
	public void writeExcelData (String text, String filename) throws Exception {
		CommonFunctions.writeExcel(text,filename);
	}

	/**
	 * Fetches downloaded file name
	 * 
	 */
	public String fetchFileName() throws Exception {
		String text = _driver.findElement(By.xpath(FILENAME_XPATH)).getText();
		return text;	
	}


	/**
	 * Checks login link is present
	 * 
	 */
	public boolean checkLogoutLink() throws Exception {  
		if(CommonFunctions.isElementPresent(_driver, By.id(LOGOUT_ID)))
			return true;
		else
			return false;
	} 

	/**
	 * Verify file to downlaod exists
	 * 
	 */
	public void verifyDownloadFileExists() {
		_driver.findElement(By.xpath("//div[1]/div[3]/div/form/a")).click();

	}

	/**
	 * Assert the firstname, lastname, phone number etc..
	 * 
	 */
	public String assertFirstName() throws Exception {  
		String firstName = _driver.findElement(By.xpath(FIRSTNAME_XPATH)).getText();  
		return firstName;
	} 

	public String assertLastName() throws Exception {  
		String lastName = _driver.findElement(By.xpath(LASTNAME_XPATH)).getText();  
		return lastName;
	} 

	public String assertPhoneNumber() throws Exception {  
		String phoneNumber = _driver.findElement(By.xpath(PHONENUMBER_XPATH)).getText();  
		return phoneNumber;
	} 

	public String assertGender() throws Exception {  
		String gender = _driver.findElement(By.xpath(GENDER_XPATH)).getText();  
		return gender;
	} 

	public String assertIndustry() throws Exception {  
		String industry = _driver.findElement(By.xpath(INDUSTRY_XPATH)).getText();  
		return industry;
	} 

	public String assertCountry() throws Exception {  
		String country = _driver.findElement(By.xpath(COUNTRY_XPATH)).getText();  
		return country;
	} 

}
