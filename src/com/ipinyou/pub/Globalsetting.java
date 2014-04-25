package com.ipinyou.pub;

import java.io.FileInputStream;
import java.util.Properties;

public class Globalsetting {

	public static Properties prop = getproperties();
	public static String chromedriverpath = prop.getProperty("chromedriverpath", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
	public static String firefoxdriverpath = prop.getProperty("firefoxdriverpath", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	public static String openurl = prop.getProperty("openurl", "");
	
	public static String getProperties(String property){
		return prop.getProperty(property);
	}
			
	public static Properties getproperties(){
		Properties prop = new Properties();
		try{
			FileInputStream file = new FileInputStream("prop.properties");
			prop.load(file);
			file.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return prop;
	}		

}
