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
import org.openqa.selenium.WebDriver;

import com.gui_auto.pages.BasePage;

public class HomePage extends BasePage{
	
	 static Logger log = Logger.getLogger(HomePage.class.getName());	 
	  
	    public HomePage(WebDriver driver) {  
	        super();  
	        PropertyConfigurator.configure("config/log4j.properties");
	        this._driver = driver;  
	    }  

}
