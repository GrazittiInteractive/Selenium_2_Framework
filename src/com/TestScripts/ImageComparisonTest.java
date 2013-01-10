/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.TestScripts;

import static junit.framework.Assert.fail;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;

import com.gui_auto.base_classes.GUI_automation_base;
import com.gui_auto.beans.UsersListBean;
import com.gui_auto.dao.DBCon;
import com.gui_auto.pages.ImageGalleryPage;
import com.gui_auto.pages.RegisterPage;
import com.gui_auto.utilities.CommonFunctions;


public class ImageComparisonTest extends GUI_automation_base{

	static Logger log = Logger.getLogger(ImageComparisonTest.class.getName());
	private StringBuffer verificationErrors = new StringBuffer();

	DBCon dbConnection = new DBCon();

	private RegisterPage _register;

	ArrayList<UsersListBean> usersList;


	@Before
	public void setUp() throws Exception {
		super.setUp(getBrowser());
		PropertyConfigurator.configure("config/log4j.properties");
		_register = PageFactory.initElements(_driver, RegisterPage.class);
	}

	@Test
	public void testImageComparison() throws Exception {
		try{
			// Open URL in browser
			_register.navigateToPageAndWait();

			//Go to image gallery page
			ImageGalleryPage imageGalleryPage = new ImageGalleryPage(_driver);
			imageGalleryPage._tabs.clickElement("Image Gallery");

			//Take Screen shot. Here we are taking screen shot of 3 pages of image gallery
			for(int i=1;i<4;i++){
				_driver.findElement(By.linkText(""+i+"")).click();
				File screenshot = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE);

				//You can give the location where you want to keep the screen shot
				String fileName = "Screenshot "+i +" "+ CommonFunctions.getRandomString(10);
				FileUtils.copyFile(screenshot, new File(".//OutputData//"+fileName+".jpg")); 

				//Compare output screen shot images with sample images in InputTestData folder
				File fileA = new File(".//InputTestData//page"+i+".jpg");
				File fileB = new File(".//OutputData//"+fileName+".jpg");

				BufferedImage biA = ImageIO.read(fileA);
				DataBuffer dbA = biA.getData().getDataBuffer();
				int sizeA = dbA.getSize();                      
				BufferedImage biB = ImageIO.read(fileB);
				DataBuffer dbB = biB.getData().getDataBuffer();
				int sizeB = dbB.getSize();
				Boolean matchFlag = true;
				if(sizeA == sizeB) {
					for(int j=0; j<sizeA; j++) { 
						if(dbA.getElem(j) != dbB.getElem(j)) {
							matchFlag = false;
							break;
						}
					}
				}
				else 					
					matchFlag = false;

				if (matchFlag) 
					log.info("image matched for "+i+" image");
				else 
					log.error("image not matched for "+i+" image");	
			} 
		} catch (Exception e) { 
			log.error("Failed to compare image files ...", e);
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
