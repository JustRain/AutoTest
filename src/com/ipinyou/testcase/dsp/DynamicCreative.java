package com.ipinyou.testcase.dsp;

import static org.testng.AssertJUnit.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.pub.*;

public class DynamicCreative {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p = new PubHandle();
	@Parameters({"loginname","password"})
	@Test
	public void login(String loginname,String password){
		
		  WebElement name = driver.findElement(By.name("username"));
	  	  name.sendKeys(loginname);
	  	  WebElement pass = driver.findElement(By.name("password"));
	  	  pass.sendKeys(password);
	  	  WebElement submit = driver.findElement(By.cssSelector("input.btn"));
	  	  submit.click();	
	  	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  	  assertEquals("��¼ʧ��",driver.getTitle(),"��ҳ");
	  	  WebElement backlink = driver.findElement(By.linkText("��̨����"));
	  	  backlink.click();
	      assertEquals("��תʧ��",driver.getTitle(),"�����̹���");
	      WebElement modlelink = driver.findElement(By.linkText("��̬����ģ��"));
	      modlelink.click();
	      assertEquals("��תʧ��",driver.getTitle(),"��̬����ģ��");
	}
	
  @Parameters({"name","symbol","width","height","filrpath","picpath","minframe","maxframe"})	
  @Test
  public void dynamiccreative(String name,String symbol,String width,String height,String filrpath,String picpath,String minframe,String maxframe) throws InterruptedException, UnsupportedEncodingException, AWTException {
	     WebElement addmodle = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div[1]/a[2]/span[2]"));
	     addmodle.click();
	     assertEquals("��תʧ��",driver.getTitle(),"��̬����(������Ʒ)ģ��");
	     driver.findElement(By.id("name")).sendKeys(name);
	     driver.findElement(By.id("symbol")).sendKeys(symbol);
	     driver.findElement(By.id("universal2")).click();
	     driver.findElement(By.id("width")).clear();
	     driver.findElement(By.id("width")).sendKeys(width);
	     driver.findElement(By.id("height")).clear();
	     driver.findElement(By.id("height")).sendKeys(height);
	     String m = URLDecoder.decode(readtxt.script(filrpath),"UTF-8");
	     PubHandle.setClipboardData(m);
	     driver.findElement(By.id("codeTemplate")).click();
	     keyBoardDemo();
	     driver.findElement(By.id("multiUnit1")).click();
	     driver.findElement(By.id("minUnit")).sendKeys(minframe);
	     driver.findElement(By.id("maxUnit")).sendKeys(maxframe);
	     driver.findElement(By.id("priviewPic")).sendKeys(picpath);
	     driver.findElement(By.name("submitForm")).click();
	     assertEquals("����ģ��ʧ��",driver.getTitle(), "��̬����ģ��");
	     
	     
  }
  @Parameters({"browser"})
  @BeforeTest
  public void beforeTest(String browser) {
	  if(browser.equals("firefox")){
		  System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		  driver = new FirefoxDriver();  
	  }else if(browser.equals("chrome")){
		  System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
		  driver = new ChromeDriver();
	  }
	  String url = "http://console.ipinyou.com/login/";
	  driver.get(url);
	  driver.manage().window().maximize();
	  if(!driver.getTitle().equals("Ʒ���ųۣ�רΪ�߶��û������DSPƽ̨")){
		  ScreenShot.taskScreenShot(driver);
		  assertEquals("ҳ��δ������",driver.getTitle(), "Ʒ���ųۣ�רΪ�߶��û������DSPƽ̨");  
	  }
	  
  }
  public static void keyBoardDemo() throws AWTException { 
		 Robot robot = new Robot();	
	     robot.keyPress(KeyEvent.VK_CONTROL); 
	     robot.keyPress(KeyEvent.VK_V); 
	     robot.keyRelease(KeyEvent.VK_CONTROL); 
	     robot.keyRelease(KeyEvent.VK_V); 
	    }
  @AfterTest
  public void afterTest() {
	  WebElement logout = driver.findElement(By.linkText("�˳�"));
	  logout.click();
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  if(!driver.getTitle().equals("Ʒ���ųۣ�רΪ�߶��û������DSPƽ̨")){
		  ScreenShot.taskScreenShot(driver);
		  assertEquals("�˳�ʧ��",driver.getTitle(),"Ʒ���ųۣ�רΪ�߶��û������DSPƽ̨");

	  }
  }

}
