package com.ipinyou.webpage.selfadvertiserpage;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.ipinyou.entity.selfadvertiser.CreativeInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.testcase.dsp.Creative;

public class CreativePage {
	
	public static PubHandle p  = new PubHandle();
	public static void search(WebDriver driver,CreativeInfo cinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(cinfo.getOrname());
		list.add(cinfo.getPlname());
		list.add(cinfo.getStrategyname());
		PubHandle.search(driver, list, PubHandle.titlelists());
	}
	
	public static boolean increative(WebDriver driver,String title){
		 driver.findElement(By.className("ico-add")).click();
		 boolean flag = Check.usualexist(driver, title, 2);
		 if(flag){
			 return true;
		 }else{
			 return false;
		 }
	}
	public static void selcreative(WebDriver driver,String reminder,String element) throws InterruptedException, AWTException{
		Actions action = new Actions(driver);
	    action.click(driver.findElement(By.id("SWFUpload_0"))).perform();
		Thread.sleep(2000);
		Creative.keyBoardDemo();
	    boolean flag = Check.elementexist(driver, By.className("upload-result"), 15, element);
	    if(flag){
	    	driver.findElement(By.linkText("ȷ��")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "�ϴ�����ʧ��", element, By.className("upload-result"));
	    }
	}
	public static void completecreative(WebDriver driver,CreativeInfo cinfo) throws InterruptedException{
		boolean comflag = Check.elementexist(driver, By.className("js-add-new-click"),5 , "��ӵ������ַ");
		if(comflag){
			Select first = new Select(driver.findElement(By.cssSelector(".js-creative-tag-parent")));
			first.selectByVisibleText("�ݳ�Ʒ");
			Select second = new Select(driver.findElement(By.cssSelector(".js-creative-tag-child")));
			second.selectByVisibleText("�ݳ�Ʒ");
			Thread.sleep(1000);
			boolean urlflag = Check.elementexist(driver,By.className("js-add-new-click"), 4, "��ӵ������ַ");
			if(urlflag){
				WebElement arraive =driver.findElement(By.cssSelector(".js-targetUrl-input"));
				arraive.clear();
				arraive.sendKeys(cinfo.getArriveadress());
				driver.findElement(By.className("js-add-new-click")).click();
				driver.findElement(By.className("js-url-input")).sendKeys(cinfo.getMonitoraddress());
				driver.findElement(By.xpath("/html/body/div[10]/div[2]/div/div[2]/a[1]")).click();
				driver.findElement(By.className("js-add-new-tracking")).click();
				driver.findElement(By.className("js-url-input")).sendKeys(cinfo.getExposureaddress());
				driver.findElement(By.xpath("/html/body/div[10]/div[2]/div/div[3]/a[1]")).click();
				driver.findElement(By.name("strtgyCrtvs[0].creativeTheme")).sendKeys(cinfo.getTheme());
				driver.findElement(By.name("submitForm")).click();
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "������д��ַ", "��ӵ������ַ", By.className("js-add-new-click"));
			}
					
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "�������ƴ�����Ϣ", "��ӵ������ַ", By.className("js-add-new-click"));
		}
		
		
	}
	public static void checkcreative(WebDriver driver,CreativeInfo cinfo) throws InterruptedException{
		boolean flag = Check.exist(driver, By.cssSelector(".js-update-active"), 4);
		if(flag){
			boolean flag1 = Check.elementexist(driver, By.className("creative-name"), 2, cinfo.getTheme());
			if(!flag1){
				ScreenshotandAssert.screenandasserttext(driver, "���ⲻ��", cinfo.getTheme(), By.className("creative-name"));
			}
			boolean flag2 = Check.elementexist(driver, By.cssSelector(".js-targetUrl"), 2, cinfo.getArriveadress());
			if(!flag2){
				ScreenshotandAssert.screenandasserttext(driver, "�����ַ����", cinfo.getMonitoraddress(), By.cssSelector(".js-targetUrl"));
			}
			boolean flag3 = Check.elementexist(driver, By.xpath("//*[@id='creative_list']/tr/td[9]/div[2]/span[2]"), 2, cinfo.getMonitoraddress());
			if(!flag3){
				ScreenshotandAssert.screenandasserttext(driver, "�����ַ����", cinfo.getExposureaddress(),By.xpath("//*[@id='creative_list']/tr/td[9]/div[2]/span[2]"));
			}
			boolean flag4 = Check.elementexist(driver, By.cssSelector(".tracking"), 2, cinfo.getExposureaddress());
			if(!flag4){
				ScreenshotandAssert.screenandasserttext(driver, "�ع��ַ����",cinfo.getExposureaddress(),By.cssSelector(".tracking"));
			}
			
		}else{
			ScreenshotandAssert.screenandassertvalue(driver, "��������û������", "�ѿ���", By.cssSelector(".js-update-active"), "title");
		}
	}
}
