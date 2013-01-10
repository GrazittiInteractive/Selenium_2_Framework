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

import java.io.BufferedInputStream;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.gui_auto.base_classes.GUI_automation_base;
import com.gui_auto.beans.UsersListBean;
import com.gui_auto.pages.InformationPage;
import com.gui_auto.pages.RegisterPage;

public class PdfTextComparisonTest extends GUI_automation_base {

	static Logger log = Logger.getLogger(PdfTextComparisonTest.class.getName());
	private StringBuffer verificationErrors = new StringBuffer();


	private RegisterPage _register;

	ArrayList<UsersListBean> usersList;

	@Before
	public void setUp() throws Exception {
		super.setUp(getBrowser());
		PropertyConfigurator.configure("config/log4j.properties");
		_register = PageFactory.initElements(_driver, RegisterPage.class);
	}

	@Test
	public void testPdfTextComparison() throws Exception {
		try{

			// Open URL in browser
			_register.navigateToPageAndWait();

			//Go to information page
			InformationPage informationPage = new InformationPage(_driver);
			informationPage._tabs.clickElement("Information");

			//Information page opens in a new tab, therefore handling a new window	
			RegisterPage registerPage = new RegisterPage(_driver);
			informationPage = registerPage.handleNewTabWindow();

			//Click on 'Click here' link
			_driver.findElement(By.linkText("Click Here")).click();

			// This will parse the stream and populate the COSDocument object (in-memory representation of the
			// PDF document)
			URL url = new URL(_driver.getCurrentUrl());
			BufferedInputStream fileToParse = new BufferedInputStream(url.openStream());
			PDFParser parser = new PDFParser(fileToParse);
			parser.parse();

			// Store the pdf text into string
			String output = new PDFTextStripper().getText(parser
					.getPDDocument());

			// Remove all spaces, tab, new lines		
			output = output.replaceAll("\\s", "");
			parser.getPDDocument().close();

			// Convert the sample file to string for comparison. Also Remove all spaces, tab, new lines
			File file = new File(".//InputTestData//Sample_File.txt");
			String fileContent = FileUtils.readFileToString(file).replaceAll("\\s", "");

			// Compare the sample and resultant file
			assertEquals((output.compareTo(fileContent)), 0);

			log.info("Content Matched");
		} catch (AssertionError e) {
			log.error("Content Not Matched", e);
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
