/**
* Framework - Grazitti Automation Selenium Testing
* Version - 2.0
* Creation Date - Oct, 2012
* Author - Grazitti Interactive
* Copyright © 2012 Grazitti Interactive. All right reserved.
**/
package com.gui_auto.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.gui_auto.base_classes.GUI_automation_base;
import com.gui_auto.base_classes.GUI_automation_properties;
import com.gui_auto.beans.UsersListBean;

public class DBCon extends GUI_automation_base {

	static Logger log = Logger.getLogger(DBCon.class.getName());

	protected static GUI_automation_properties _properties = new GUI_automation_properties();

	private final static int FIRST_NAME = 0;

	private final static int LAST_NAME = 1;
	
	private final static int PHONE_NUMBER = 2;
	
	private final static int GENDER =3;
	
	private final static int INDUSTRY = 4;
	
	private final static int COUNTRY = 5;
	
	private final static int EDUCATION = 6;
	
	private final static int COURSE = 7;
	
	private final static int HOBBY = 8;
	
	private final static int FILE = 9;
	
	private final static int ABOUT_ME = 10;
	
	public final static int NO_OF_COLUMNS = 11;

	protected static HashMap<String, String> _users = new HashMap<String, String>();

	public ArrayList<UsersListBean> loadDataFromExcel() throws Exception {

		PropertyConfigurator.configure("config/log4j.properties");
		ArrayList<UsersListBean> usersList = new ArrayList<UsersListBean>();

		try {

			Workbook workbook = WorkbookFactory
					.create(new FileInputStream(_properties
							.getProperty(GUI_automation_properties.XLS_DATA)));
			Sheet sheet = workbook.getSheetAt(Integer.parseInt(_properties
					.getProperty(GUI_automation_properties.USERS_LIST)));
			int row = sheet.getLastRowNum();
			
			for (int i = 1; i <= row; i++) {
				try {
					
					Row rows = sheet.getRow(i);
					UsersListBean users = new UsersListBean();
					
					
					users.setFirstName(rows.getCell(FIRST_NAME).getStringCellValue());
					users.setLastName(rows.getCell(LAST_NAME).getStringCellValue());
					users.setPhoneNumber(rows.getCell(PHONE_NUMBER).getStringCellValue());
					users.setGender(rows.getCell(GENDER).getStringCellValue());
					users.setIndustry(rows.getCell(INDUSTRY).getStringCellValue());
					users.setCountry(rows.getCell(COUNTRY).getStringCellValue());
					users.setEducation(rows.getCell(EDUCATION).getStringCellValue());
					users.setCourse(rows.getCell(COURSE).getStringCellValue());
					users.setHobby(rows.getCell(HOBBY).getStringCellValue());
					users.setFile(rows.getCell(FILE).getStringCellValue());
					users.setAboutMe(rows.getCell(ABOUT_ME).getStringCellValue());
				
					
					
					usersList.add(users);
					
				} catch (ArrayIndexOutOfBoundsException ex) {
					
					break;
				} catch (Exception ex) {
					
				}
			}
		} catch (FileNotFoundException ex) {
			log.error("File Could not be Found", ex);
		} catch (Exception ex) {
			
		}

		return usersList;
	}

}
