package com.ipinyou.pub;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;

public class ScreenShot {
    
    public static void taskScreenShot(WebDriver driver){
    	String dir_name = "E://WebDriverScreen";
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MMdd-HHmmss");
    	String time = sd.format(new Date());
    	SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MMdd");
    	String data = sd1.format(new Date());
    	String dir = dir_name+"//"+data;
    	File file = new File(dir);
    	if(!file.exists()){
    		file.mkdirs();
    	}
		File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	try{
    		FileUtils.copyFile(screenShotFile, new File(dir+File.separator+time+".png"));
            
    	}catch(IOException  e){
    		e.printStackTrace();
    	}
    }
}
