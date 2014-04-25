package com.ipinyou.pub;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Check {
	
	
	public static boolean exist(WebDriver dr,By by,int findtime){
		
		try{
			for(int i=0;i<findtime;i++){
				if(dr.findElement(by).isDisplayed()){
					return true;
				}else{
					Thread.sleep(500);
				}
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean isenbled(WebDriver dr,By by,int findtime){
		try{
			for(int i=0;i<findtime;i++){
				if(dr.findElement(by).isSelected()){
					return true;
				}else{
					Thread.sleep(500);
				}
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	public static boolean elementexist(WebDriver driver,By by,int findtime,String element) throws InterruptedException{
		try{
			for(int i=0;i<findtime;i++){
				if(driver.findElement(by).getText().equals(element)){
					return true;
				}else{
					Thread.sleep(500);
				}
			}			
			return false;
		}catch(org.openqa.selenium.NoSuchElementException e){
			return false;
		}
	}
	
	
	
	public static boolean elementvalue(WebDriver driver,By by,int findtime,String element,String value) throws InterruptedException{
		try{
			for(int i=0;i<findtime;i++){
				if(driver.findElement(by).getAttribute(value).equals(element)){
					return true;
				}else{
					Thread.sleep(500);
				}
			}			
			return false;
		}catch(org.openqa.selenium.NoSuchElementException e){
			return false;
		}
	}
	
	
	
	public static boolean usualexist(WebDriver driver,String title,int findtime){
		try{
			for(int i=0;i<findtime;i++){
				if(driver.getTitle().equals(title)){
					return true;
				}else{
					Thread.sleep(500);
				}
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	public static boolean indexexist(WebDriver driver,String element,By by,int findtime){
		try{
			for(int i=0;i<findtime;i++){
				if(driver.findElement(by).getText().indexOf(element)!=-1){
					return true;
				}else{
					Thread.sleep(500);
				}
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}

}
