package com.ipinyou.testcase.advertiserdsp;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Strategy {
	WebDriver driver;
	
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
  	  assertEquals("��¼ʧ��",driver.getTitle(),"�����б�");

    }
	
@Parameters({"ordername","planname"})
  @Test
  public void search(String ordername,String planname) {
	WebElement searchorder = driver.findElement(By.id("appendedInputButton"));
	searchorder.sendKeys(ordername);
	WebElement dosearchor  = driver.findElement(By.xpath("//*[@id='queryForm']/button"));
	dosearchor.click();
	WebElement orderlink = driver.findElement(By.linkText(ordername));
	orderlink.click(); 
	assertEquals("��תʧ��",driver.getTitle(),"�ƻ��б�");
	WebElement searchplan = driver.findElement(By.id("appendedInputButton"));
	searchplan.sendKeys(planname);
	WebElement dosearchpl = driver.findElement(By.xpath("//*[@id='queryForm']/button"));
	dosearchpl.click();
	assertEquals("��תʧ��",driver.getTitle(),"����Ͷ�Ų���");
  }
@Test
    public void createstrategy(){
	
}
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
	  driver = new FirefoxDriver();
	  String url = "http://console.ipinyou.com/login/";
	  driver.get(url);
	  driver.manage().window().maximize();
	  assertEquals("ҳ��δ������",driver.getTitle(), "Ʒ���ųۣ�רΪ�߶��û������DSPƽ̨");
  }

  @AfterTest
  public void afterTest() {
	  WebElement logout = driver.findElement(By.linkText("�˳�"));
	  logout.click();
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	  assertEquals("�˳�ʧ��",driver.getTitle(),"Ʒ���ųۣ�רΪ�߶��û������DSPƽ̨");
	  driver.quit();
  }

}
