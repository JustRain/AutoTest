package com.ipinyou.webpage;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.ipinyou.entity.CreativeInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.testcase.dsp.Creative;

public class CreativePage {
	public static  List<String> pathlist = new ArrayList<String>();
	public static PubHandle p  = new PubHandle();
	public static void search(WebDriver driver,CreativeInfo cinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(cinfo.getAdname());
		list.add(cinfo.getOrname());
		list.add(cinfo.getPlname());
		list.add(cinfo.getStrategyname());
		PubHandle.search(driver, list, PubHandle.titlelist());
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
	//�ϴ�JPG
	public static void selcreativejpg(WebDriver driver, CreativeInfo cinfo) throws InterruptedException, AWTException{
		//CreativePage.addpath(cinfo);
        PubHandle.setClipboardData(cinfo.getDspjpgpath());
        pubselcreative(driver, cinfo.getReminder());
	}
	public static void completecreativejpg(WebDriver driver,CreativeInfo cinfo) throws InterruptedException{
		pubcompletecreative(driver, cinfo, cinfo.getJpgtheme());
	}
	//�ϴ�FLV
	public static void selcreativeflv(WebDriver driver, CreativeInfo cinfo) throws InterruptedException, AWTException{
		//CreativePage.addpath(cinfo);
		increative(driver, "�ϴ�����");
        PubHandle.setClipboardData(cinfo.getDspflvpath());
        pubselcreative(driver, cinfo.getReminder());
	}
	public static void completecreativeflv(WebDriver driver,CreativeInfo cinfo) throws InterruptedException{
		pubcompletecreative(driver, cinfo, cinfo.getFlvtheme());
	}
	//�ϴ�SWF
	public static void selcreativswf(WebDriver driver, CreativeInfo cinfo) throws InterruptedException, AWTException{
		//CreativePage.addpath(cinfo);
		increative(driver, "�ϴ�����");
        PubHandle.setClipboardData(cinfo.getDspswfpath());
        pubselcreative(driver, cinfo.getReminder());
	}
	public static void completecreativeswf(WebDriver driver,CreativeInfo cinfo) throws InterruptedException{
		pubcompletecreative(driver, cinfo, cinfo.getSwftheme());
	}
	//�ϴ�PNG
	public static void selcreativepng(WebDriver driver, CreativeInfo cinfo) throws InterruptedException, AWTException{
		//CreativePage.addpath(cinfo);
		increative(driver, "�ϴ�����");
        PubHandle.setClipboardData(cinfo.getDsppngpath());
        pubselcreative(driver, cinfo.getReminder());
	}
	public static void completecreativepng(WebDriver driver,CreativeInfo cinfo) throws InterruptedException{
		pubcompletecreative(driver, cinfo, cinfo.getPngtheme());
	}
	//�ϴ�GIF
	public static void selcreativegif(WebDriver driver, CreativeInfo cinfo) throws InterruptedException, AWTException{
		//CreativePage.addpath(cinfo);
		increative(driver, "�ϴ�����");
        PubHandle.setClipboardData(cinfo.getDspgifpath());
        pubselcreative(driver, cinfo.getReminder());
	}
	public static void completecreativegif(WebDriver driver,CreativeInfo cinfo) throws InterruptedException{
		pubcompletecreative(driver, cinfo, cinfo.getGiftheme());
	}
	//�ϴ�JPEG
	public static void selcreativejpeg(WebDriver driver, CreativeInfo cinfo) throws InterruptedException, AWTException{
		//CreativePage.addpath(cinfo);
		increative(driver, "�ϴ�����");
        PubHandle.setClipboardData(cinfo.getDspjpegpath());
        pubselcreative(driver, cinfo.getReminder());
	}
	
	public static void checkcreative(WebDriver driver,CreativeInfo cinfo) throws InterruptedException{
		boolean flag = Check.exist(driver, By.cssSelector(".js-update-active"), 4);
		if(flag){
			boolean flag1 = Check.elementexist(driver, By.className("creative-name"), 2, cinfo.getJpgtheme());
			if(!flag1){
				ScreenshotandAssert.screenandasserttext(driver, "���ⲻ��", cinfo.getJpgtheme(), By.className("creative-name"));
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
	
	public static void pubselcreative(WebDriver driver,String element) throws InterruptedException, AWTException{
		Actions action = new Actions(driver);
	    action.click(driver.findElement(By.id("SWFUpload_0"))).perform();
		Thread.sleep(2000);
		Creative.keyBoardDemo();
		System.out.println("reminder=============="+element);
	    boolean flag = Check.elementexist(driver, By.className("upload-result"), 15, element);
	    if(flag){
	    	driver.findElement(By.linkText("ȷ��")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "�ϴ�����ʧ��", element, By.className("upload-result"));
	    }
	}
	
	public static void pubcompletecreative(WebDriver driver,CreativeInfo cinfo,String theme) throws InterruptedException{
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
				driver.findElement(By.name("strtgyCrtvs[0].creativeTheme")).sendKeys(theme);
				driver.findElement(By.name("submitForm")).click();
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "������д��ַ", "��ӵ������ַ", By.className("js-add-new-click"));
			}
					
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "�������ƴ�����Ϣ", "��ӵ������ַ", By.className("js-add-new-click"));
		}
		
		
	}
	
	
	public static  ArrayList<String> addpath(CreativeInfo cinfo){
		pathlist.add(cinfo.getDspjpgpath());
		pathlist.add(cinfo.getDspflvpath());
		pathlist.add(cinfo.getDspswfpath());
		pathlist.add(cinfo.getDsppngpath());
		pathlist.add(cinfo.getDspgifpath());
		pathlist.add(cinfo.getDspjpegpath());
		
//		pathlist.add(jpjpath);
//		pathlist.add(flvpath);
//		pathlist.add(swfpath);
//	    System.out.println(pathlist.get(0)+" "+pathlist.get(1)+" "+pathlist.get(2));
		for(String list : pathlist){
			System.out.println(list);
		}
		return (ArrayList<String>) pathlist;
	}
	
	public static void main(String args[]){
		CreativePage c = new CreativePage();
		String jpjpath = "E:\\360Downloads\\UploadCreative.jpg";
		String flvpath = "E:\\360Downloads\\classify\\Uploadflv.flv";
		String swfpath = "E:\\360Downloads\\classify\\Uploadswf.swf"; 
		//c.addpath(jpjpath,flvpath,swfpath);
	}
}
