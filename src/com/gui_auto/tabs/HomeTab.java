/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.gui_auto.tabs;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.navigations.Tabs;

public class HomeTab implements BaseElement{

	private WebDriver _driver;

	private Tabs _tabs;

	public HomeTab(final WebDriver driver) {
		_driver = driver;
		_tabs = new Tabs(driver);
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
