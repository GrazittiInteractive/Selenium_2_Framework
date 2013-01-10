/**
 * Framework - Grazitti Automation Selenium Testing
 * Version - 3.0
 * Creation Date - Nov, 2012
 * Author - Grazitti Interactive
 * Copyright © 2012 Grazitti Interactive. All right reserved.
 **/
package com.gui_auto.base_classes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GUI_automation_properties {

	public final static String BASEURL = "BASEURL";

	public final static String PROPERTY_FILENAME = "config/gui_automation.properties";

	private Properties _gui_automation_properties = new Properties();

	public final static String XLS_DATA = "XLS_DATA";

	public final static String USERS_LIST = "USERS_LIST";

	public final static String BROWSER = "BROWSER";

	/**
	 * Loads the properties file
	 */
	public GUI_automation_properties() {
		try {
			_gui_automation_properties.load(new FileInputStream(PROPERTY_FILENAME));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		assert !_gui_automation_properties.isEmpty();
	}

	/**
	 * returns the value of the property denoted by key
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(final String key) {
		String property = _gui_automation_properties.getProperty(key);
		return property != null ? property.trim() : property;
	}

}
