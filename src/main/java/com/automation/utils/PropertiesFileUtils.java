package com.automation.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {
	
	// DUONG DAN DEN PROPERTIES FILES TRONG FOLDER CONFIGURATION
	private static String CONFIG_PATH = "./configuration/configs.properties";
	
	// LAY RA GIA TRI PROPERTY BAT KY THEO KEY
	public static String getProperty(String key) {
		Properties properties = new Properties();
		FileInputStream  fileInputStream = null;
		String value = null;
			
			try {
				fileInputStream = new FileInputStream(CONFIG_PATH);
				properties.load(fileInputStream);
				value = properties.getProperty(key);
				return value;
			}catch(Exception ex) {
				System.out.println("Xảy ra lỗi khi đọc giá trị của " + key);
				ex.printStackTrace();
			}finally {
				if(fileInputStream != null) {
					try {
						fileInputStream.close();
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
			return value;
	}
			
		// GHI PROPERTY VAO FILE
		public static void setProperty(String key, String value) {
			Properties prop = new Properties();
			FileOutputStream fileOutputStream = null;
			
			try {
				fileOutputStream = new FileOutputStream(CONFIG_PATH);
				prop.setProperty(key, value);
				prop.store(fileOutputStream, "Set new value in properties");
				System.out.println("Set new value in file properties success.");
			}catch(IOException ex) {
				ex.printStackTrace();
			}finally {
				if(fileOutputStream != null) {
					try {
						fileOutputStream.close();
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
}
