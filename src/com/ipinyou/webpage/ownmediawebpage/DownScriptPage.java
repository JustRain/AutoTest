package com.ipinyou.webpage.ownmediawebpage;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.ipinyou.entity.ownmedia.DownScriptInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ReplaceScript;
import com.ipinyou.pub.ScreenshotandAssert;

public class DownScriptPage {
	public static void downscript(WebDriver driver,DownScriptInfo dsinfo) throws InterruptedException, AWTException{
		driver.findElement(By.id("mediaMenu")).click();
		boolean flag = Check.elementexist(driver, By.xpath("/html/body/div[3]/div/div[3]/strong"), 10, "��������");
		if(flag){
			driver.findElement(By.linkText(dsinfo.getChannel())).click();
			fifth(driver, dsinfo);
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "û�н��뵽ý�������", "��������", By.xpath("/html/body/div[3]/div/div[2]/strong"));
		}
	}
	
	public static void first(WebDriver driver,DownScriptInfo dsinfo) throws InterruptedException{
		
		boolean frameflag = Check.elementexist(driver, By.xpath("//*[@id='main']/div/div/div[1]/div[1]/strong"), 10, "��ȡ����");
		if(frameflag){
			WebElement testarea = driver.findElement(By.id("retargeting_js"));
			System.out.println(testarea.getText());
			testarea.click();
			ReplaceScript r = new ReplaceScript();
		    boolean checkflag = r.replace(dsinfo.getFilepath(), testarea.getText());
		    if(checkflag){
		    	driver.switchTo().defaultContent();
		    	driver.findElement(By.className("close-tag")).click();
		    }else{
		    	ScreenshotandAssert.screenandassert(driver, "��������ʧ��", checkflag, true);
		    }
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "û����ת�����ش���frame", "��ȡ����", By.xpath("//*[@id='main']/div/div/div[1]/div[1]/strong"));
		}
	}
	public static void second(WebDriver driver,DownScriptInfo dsinfo) throws InterruptedException{
		boolean scrflag = Check.elementexist(driver, By.linkText("���ش���"), 10, "���ش���");
		if(scrflag){
			driver.findElement(By.linkText("���ش���")).click();
			driver.switchTo().frame(driver.findElement(By.className("myiframe")));
//			List<WebElement> frame = driver.findElements(By.className("myiframe"));
//			driver.switchTo().frame(frame.get(2));
			//PubHandle.switchframe(driver, By.className("myiframe"), "src", "./code");
			Thread.sleep(1000);
			first(driver, dsinfo);
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "û�д����ش���������", "���ش���", By.linkText("���ش���"));
		}
	}
	public static void third(WebDriver driver,DownScriptInfo dsinfo) throws InterruptedException{
		boolean posflag = Check.elementexist(driver, By.xpath("/html/body/div[3]/div/div/div[2]/div[2]/div[1]/strong"), 10, dsinfo.getPart());
		if(posflag){
			driver.findElement(By.id("appendedInputButton")).sendKeys(dsinfo.getPosition());
			driver.findElement(By.id("searchInputButton")).click();
			boolean seaflag = Check.elementexist(driver, By.xpath("/html/body/div[3]/div[1]/div[1]/div[4]/table/tbody/tr/td[3]"), 10, "autoadposition");
			if(seaflag){
				Actions action = new Actions(driver);
				action.moveToElement(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[4]/table/tbody/tr/td[2]/div/span[1]"))).perform();
				//driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[4]/table/tbody/tr/td[2]/div/span[1]")).click();
				Thread.sleep(2000);
				second(driver, dsinfo);
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "û�в鵽���λ", "autoadposition", By.cssSelector(".ellipsis"));
			}
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "û�н�����λҳ", dsinfo.getPart(), By.xpath("/html/body/div[3]/div/div/div[2]/div[2]/div[1]/strong"));
		}
	}
	public static void fourth(WebDriver driver,DownScriptInfo dsinfo) throws InterruptedException{
		boolean partflag = Check.elementexist(driver, By.xpath("/html/body/div[3]/div/div/div[2]/div[2]/div[1]/strong"), 10, dsinfo.getWebsuit());
		if(partflag){
			driver.findElement(By.linkText(dsinfo.getPart())).click();
			third(driver, dsinfo);
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "û�н�����Ŀҳ", dsinfo.getWebsuit(), By.xpath("/html/body/div[3]/div/div/div[2]/div[2]/div[1]/strong"));
		}
	}
	public static void fifth(WebDriver driver,DownScriptInfo dsinfo) throws InterruptedException{
		boolean webflag = Check.elementexist(driver, By.xpath("/html/body/div[3]/div/div/div[3]/div[2]/div[1]/strong"), 10, dsinfo.getChannel());
		if(webflag){
			driver.findElement(By.linkText(dsinfo.getWebsuit())).click();
			fourth(driver, dsinfo);
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "û�н�����վҳ", dsinfo.getChannel(), By.xpath("/html/body/div[3]/div/div/div[3]/div[2]/div[1]/strong"));
		}
	}
		
}
