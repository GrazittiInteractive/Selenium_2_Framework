/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.gui_auto.utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonFunctions {	

	static Logger log = Logger.getLogger(CommonFunctions.class.getName());

	static char specialCharacters[] = { '!', '@', '#', '$', '%', '^', '&', '*',
		'(', ')', '?', '/', '"', '|', '{', '[', '<', '>', ';', '`', ',',
		'_', '-' };

	/**
	 * Retrieve popup text message.
	 * 
	 * @param WebDriver
	 *            driver
	 * @return
	 */
	public static String getPopupMessage(final WebDriver driver) {
		String message = null;
		try {
			Alert alert = driver.switchTo().alert();

			message = alert.getText();
			alert.accept();
		} catch (Exception e) {
			// Sometimes the text exist, but not the accept button.
			// this means the popup wasn't displayed and therefore
			// really never existed.
			//
			message = null;
		}
		System.out.println("message"+message);
		return message;
	}

	public static String cancelPopupMessageBox(final WebDriver driver) {
		String message = null;
		try {
			Alert alert = driver.switchTo().alert();

			message = alert.getText();
			alert.dismiss();
		} catch (Exception e) {
			// Sometimes the text exist, but not the accept button.
			// this means the popup wasn't displayed and therefore
			// really never existed.
			//
			message = null;
		}

		return message;
	}

	private static SecureRandom random = new SecureRandom();

	/**
	 * Generate random string of special characters of length x
	 * 
	 * @return
	 */
	public String getRandomSpecialString(int length) {
		int len = specialCharacters.length;
		String str = "";
		Random randomGenerator = new Random();
		int index;

		for (int i = 0; i < length; i++) {
			index = randomGenerator.nextInt(len - 1);
			str = str + specialCharacters[index];
		}
		return str;
	}

	/**
	 * Generate random string of length x
	 * 
	 * @return
	 */
	public static String getRandomString(int length) {
		String result = new BigInteger(Long.SIZE * length, random).toString(32);
		return result.substring(0, length);
	}

	/**
	 * Generate random string of length x
	 * 
	 * @return
	 */
	public static void populateField(WebDriver driver, By locator, String value) {
		WebElement field = driver.findElement(locator);
		field.clear();
		field.sendKeys(value);

	}

	/**
	 * Check hover message text
	 * 
	 * @param driver
	 * @param by
	 * 
	 * @return string
	 */
	public static String checkHoverMessage(WebDriver driver, By locator){
		String tooltip = driver.findElement(locator).getAttribute("title");
		return tooltip;
	}

	/**
	 * Select radio button
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * 
	 */
	public static void selectRadioButton(WebDriver driver, By locator, String value){
		List<WebElement> select = driver.findElements(locator);

		for (WebElement radio : select){
			if (radio.getAttribute("value").equalsIgnoreCase(value)){
				radio.click(); 	            	

			}}}

	/**
	 * Select multiple check boxes
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * 
	 */
	public static void selectCheckboxes(WebDriver driver, By locator, String value){

		List<WebElement> abc = driver.findElements(locator);
		List<String> list = new ArrayList<String>(Arrays.asList(value.split(",")));

		for (String check : list){
			for (WebElement chk : abc){        	
				if(chk.getAttribute("value").equalsIgnoreCase(check)){	        	
					chk.click();	    	            	
				}      		        		            	
			}
		}
	}


	/**
	 * Select drop down
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * 
	 */
	public static void selectDropdown(WebDriver driver, By locator, String value){
		new Select (driver.findElement(locator)).selectByVisibleText(value); }


	/**
	 * Select auto-suggest search drop down
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * 
	 */
	public static void selectSearchDropdown(WebDriver driver, By locator, String value){
		driver.findElement(locator).click();
		driver.findElement(locator).sendKeys(value);
		driver.findElement(locator).sendKeys(Keys.TAB);	 
	}


	/**
	 * Upload file
	 * 
	 * @param driver
	 * @param by
	 * @param value
	 * 
	 */
	public static void uploadFile(WebDriver driver, By locator, String value){
		driver.findElement(locator).sendKeys(value);		  
	}


	/**
	 * Takes controls on new tab
	 * 
	 * @param driver
	 * 
	 */
	public static void handleNewTab(WebDriver driver)
	{
		Set<String> allWindowHandles = driver.getWindowHandles();
		String window0 = (String) allWindowHandles.toArray()[1];
		driver.switchTo().window(window0);
	}


	/**
	 * Helper method: looks through a list of WebElements, to find the first WebElement with matching text
	 * 
	 * @param elements
	 * @param text
	 * 
	 * @return WebElement or null
	 */
	public static WebElement findElementByText(List<WebElement> elements, String text) {
		WebElement result = null;
		for (WebElement element : elements) {
			element.getText().trim();
			if (text.equalsIgnoreCase(element.getText().trim())) {
				result = element;
				break;
			}
		}
		return result;
	}


	/**
	 * Compact way to verify if an element is on the page
	 * 
	 * @param driver
	 * @param by
	 * @return
	 */
	public static boolean isElementPresent(final WebDriver driver, By by) {


		try {
			driver.findElement(by);

			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}


	/**
	 * Downloads a file from the defined url, and saves it into the
	 * OutputDatafolder, using the filename defined
	 * 
	 * @param href
	 * @param fileName
	 */
	public static void downloadFile(String href, String fileName) throws Exception{

		PropertyConfigurator.configure("config/log4j.properties");

		URL url = null;
		URLConnection con = null;
		int i;

		url = new URL(href);

		con = url.openConnection();
		File file = new File(".//OutputData//" + fileName);
		BufferedInputStream bis = new BufferedInputStream(con.getInputStream());

		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(file));
		while ((i = bis.read()) != -1) {
			bos.write(i);
		}
		bos.flush();
		bis.close();

	}


	/**
	 * Writes content to the excel sheet
	 * 
	 * @param text
	 * @param fileName
	 */
	public static void writeExcel(String text,String fileName) throws Exception 
	{ 
		FileOutputStream file = new FileOutputStream(".//OutputData//" + fileName+".csv",true);
		WritableWorkbook book = Workbook.createWorkbook(file); 
		WritableSheet sheet = book.createSheet("output", 0);
		Label l = new Label(0, 0, text);
		sheet.addCell(l);
		book.write(); 
		book.close(); 
	}

}