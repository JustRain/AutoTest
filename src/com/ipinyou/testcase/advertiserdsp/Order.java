package com.ipinyou.testcase.advertiserdsp;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Order {
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
	
	@Parameters({"ordername","totalbudget","contractNo"})
	@Test
	public void  createorder(String ordername,String totalbudget,String contractNo){
		WebElement createorder = driver.findElement(By.className("ico-add"));
		createorder.click();
		assertEquals("����������תʧ��",driver.getTitle(),"��������");
		WebElement name = driver.findElement(By.id("name"));
		name.sendKeys(ordername);
		WebElement budget = driver.findElement(By.id("totalBudget"));
		budget.sendKeys(totalbudget);
		WebElement contractno = driver.findElement(By.id("contractNo"));
		contractno.sendKeys(contractNo);
		WebElement submit = driver.findElement(By.name("submitForm"));		
		submit.click();
		assertEquals("��������ʧ��",driver.getTitle(),"�������ƻ�");
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
