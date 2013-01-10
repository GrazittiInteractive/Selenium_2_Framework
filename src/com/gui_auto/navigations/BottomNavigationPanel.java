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

import com.gui_auto.pages.GrazittiPage;
import com.gui_auto.pages.PrivacyPolicy;
import com.gui_auto.utilities.CommonFunctions;

public class BottomNavigationPanel implements BaseNavigation{

	protected static WebDriver 	_driver;
	protected static Tabs 		_tabs;

	private static final String BOTTOM_NAVIGATION_PANEL_ID = "footer";

	public BottomNavigationPanel(final WebDriver driver) {
		_driver = driver;
	}

	public enum BottomNavigationLink {
		PRIVACY_POLICY		("Privacy Policy"), 
		GRAZITTI_LINK		("Grazitti.com");

		private String _label;

		BottomNavigationLink(String label) {
			_label = label;
		}

		public String toString() {
			return _label;
		}
	}


	@Override
	public List<WebElement> getAllElements() {
		WebElement tabNav = _driver.findElement(By.id(BOTTOM_NAVIGATION_PANEL_ID));
		return tabNav.findElements(By.tagName("a"));
	}

	@Override
	public boolean isNavPresent() {
		if (CommonFunctions.isElementPresent(_driver, By.id(BOTTOM_NAVIGATION_PANEL_ID)) && getAllElements().size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public WebElement getSelectedElement() {
		return null; 
	}

	public BaseElement clickElement(String elementName) {
		BaseElement page = null;
		WebElement element = CommonFunctions.findElementByText(getAllElements(), elementName);

		if (elementName.equalsIgnoreCase(BottomNavigationLink.PRIVACY_POLICY.toString()))
			page = new PrivacyPolicy(_driver);
		else if (elementName.equalsIgnoreCase(BottomNavigationLink.GRAZITTI_LINK.toString()))
			page = new GrazittiPage(_driver);
		else return page; // returns null if the element name does not correspond to a known, implemented page object

		element.click();
		page.waitForPage();
		return page;
	}

}
