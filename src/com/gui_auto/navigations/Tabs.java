/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.gui_auto.navigations;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gui_auto.base_classes.BaseElement;
import com.gui_auto.base_classes.BaseNavigation;
import com.gui_auto.tabs.HomeTab;
import com.gui_auto.tabs.ImageGalleryTab;
import com.gui_auto.tabs.InformationTab;
import com.gui_auto.utilities.CommonFunctions;

public class Tabs implements BaseNavigation{

	public enum Tab {
		HOME			("Home"), 
		INFORMATION		("Information"), 
		IMAGE_GALLERY			("Image Gallery");

		private String _label;

		Tab(String label) {
			_label = label;
		}

		public String toString() {
			return _label;
		}
	}

	private static final String TABS_NAV_PANEL_CLASSNAME 	= "header";

	private WebDriver _driver;

	public Tabs(final WebDriver driver) {
		_driver = driver;
	}

	@Override
	public List <WebElement> getAllElements() {
		WebElement tabNav = _driver.findElement(By.className(TABS_NAV_PANEL_CLASSNAME ));
		return tabNav.findElements(By.tagName("a"));
	}

	@Override
	public BaseElement clickElement(String elementName) {

		BaseElement page = null;
		WebElement element = CommonFunctions.findElementByText(getAllElements(), elementName);

		if (elementName.equalsIgnoreCase(Tab.HOME.toString()))
			page = new HomeTab(_driver);
		else if (elementName.equalsIgnoreCase(Tab.INFORMATION.toString()))
			page = new InformationTab(_driver);
		else if (elementName.equalsIgnoreCase(Tab.IMAGE_GALLERY.toString()))
			page = new ImageGalleryTab(_driver);
		else return page; // returns null if the element name does not correspond to a known, implemented page object

		element.click();
		page.waitForPage();
		return page;
	}

	@Override
	public WebElement getSelectedElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNavPresent() {
		// TODO Auto-generated method stub
		return false;
	}

}
