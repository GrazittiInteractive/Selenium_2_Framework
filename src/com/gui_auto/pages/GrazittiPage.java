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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.gui_auto.base_classes.BaseElement;

public class GrazittiPage extends BasePage implements BaseElement {
	
	 static Logger log = Logger.getLogger(GrazittiPage.class.getName());
	 
	  
	    public GrazittiPage(WebDriver driver) {  
	        super();  
	        PropertyConfigurator.configure("config/log4j.properties");
	        this._driver = driver;  
	    }


		@Override
		public boolean onPage() throws NoSuchElementException {
			// TODO Auto-generated method stub
			return false;
		}


		@Override
		public boolean waitForPage() {
			// TODO Auto-generated method stub
			return false;
		}  
	  
}
