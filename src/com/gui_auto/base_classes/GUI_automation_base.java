/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.gui_auto.base_classes;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class GUI_automation_base {

	public static WebDriver _driver;

	private static String URL = null;

	private static String BROWSER = null;

	protected static GUI_automation_properties _properties = new GUI_automation_properties();

	DesiredCapabilities capabilities = new DesiredCapabilities();

	/**
	 * JUnit setup should call this via
	 * 
	 * super.setUp()
	 * 
	 * By doing so, the mentioned Driver for WebDriver will be instantiated.
	 * 
	 * @throws Exception
	 */
	public void setUp(final String BROWSER) throws Exception {

		if (BROWSER.contentEquals("firefox")){
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
			_driver = new FirefoxDriver(firefoxProfile);
		}else if (BROWSER.contentEquals("internetExplorer")){
			_driver = new InternetExplorerDriver();
		}else if (BROWSER.contentEquals("chrome")){
			System.setProperty("webdriver.chrome.driver", "InputTestData/chromedriver.exe");
			_driver = new ChromeDriver(capabilities);
		}


		// --- choose one ------------------

		// _driver = new HtmlUnitDriver(true);

		_driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	/**
	 * Build up the URL via the properties file.
	 * 
	 * @return
	 */
	public static String getUrl() {
		// TODO: EY: Should this be done in the constructor instead?
		if (URL == null) {
			try {
				URL = _properties
						.getProperty(GUI_automation_properties.BASEURL);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			assert URL.contains("http"); // should look like a URL
		}
		return URL;
	}

	/**
	 * Build up the BROWSER via the properties file.
	 * 
	 * @return
	 */
	public static String getBrowser() {
		// TODO: EY: Should this be done in the constructor instead?
		if (BROWSER == null) {
			try {
				BROWSER = _properties
						.getProperty(GUI_automation_properties.BROWSER);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return BROWSER;
	}

}
