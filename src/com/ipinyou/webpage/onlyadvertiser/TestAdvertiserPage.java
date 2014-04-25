package com.ipinyou.webpage.onlyadvertiser;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.onlyadvertiser.TestAdvertiserInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class TestAdvertiserPage {
	
	public static void switchurl(TestAdvertiserInfo tainfo) throws AWTException{
		keyBoardDemoF();
		PubHandle.setClipboardData(tainfo.getAdvertiserurl());
		keyBoardDemoE();
	}
	
	public static void check(WebDriver driver){
		boolean flag = Check.usualexist(driver, "û�з��ʸ�ҳ���Ȩ��", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "�����Ȩ�޲���", "û�з��ʸ�ҳ���Ȩ��");
		}
	}
	public static void keyBoardDemoF() throws AWTException{
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_F6);
		robot.keyRelease(KeyEvent.VK_F6);
	}
	public static void keyBoardDemoE()throws AWTException{
		 Robot robot = new Robot();	
	     robot.keyPress(KeyEvent.VK_CONTROL);     
	     robot.keyPress(KeyEvent.VK_V); 
	     robot.keyRelease(KeyEvent.VK_CONTROL); 	     
	     robot.keyRelease(KeyEvent.VK_V); 
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public static void aftertest(WebDriver driver){
		driver.findElement(By.linkText("������ҳ")).click();
		boolean flag = Check.usualexist(driver, "ͳ�Ʊ���-Ͷ��Ч����������", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "������ҳʧ��", "ͳ�Ʊ���-Ͷ��Ч����������");
		}
	}

}
