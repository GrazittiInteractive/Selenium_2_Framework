/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.gui_auto.base_classes;

import org.openqa.selenium.NoSuchElementException;

public interface BaseElement {

	abstract public boolean onPage() throws NoSuchElementException; // webdriver calls to determine if this is the page.

	/**
	 * Should implement only this line
	 * 
	 * GUI_automation_base.waitToLoad(10, this);
	 * 
	 * @return
	 */
	abstract public boolean waitForPage();

}
