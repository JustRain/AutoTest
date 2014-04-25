package com.ipinyou.testcase.advertiserdsp;

import static org.testng.AssertJUnit.assertEquals;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Plan {
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
	
	@Parameters({"ordername"})
	@Test
	public void search(String ordername){
		WebElement searchorder = driver.findElement(By.id("appendedInputButton"));
		searchorder.sendKeys(ordername);
		WebElement dosearch  = driver.findElement(By.xpath("//*[@id='queryForm']/button"));
		dosearch.click();
		WebElement orderlink = driver.findElement(By.linkText(ordername));
		orderlink.click();
		assertEquals("��תʧ��",driver.getTitle(),"�ƻ��б�");
	}
	
	@Parameters({"planname","totalbudget","dailybudget","totallimitexposure","totallimitclick","dailylimitexposure","dailylimitclick","planlimitexposure"
		,"planlimitclick","creativelimitexposure","creativdelimitclick"})
	@Test
	public void createplan (String planname,String totalbudget,String dailybudget,String totallimitexposure,String totallimitclick,
     String dailylimitexposure,String dailylimitclick,String planlimitexposure,String planlimitclick,String creativelimitexposure,String creativdelimitclick)throws NoSuchElementException{
		WebElement create = driver.findElement(By.className("ico-add"));
		create.click();
		assertEquals("�½�ҳδ��",driver.getTitle(),"�������ƻ�");
		WebElement plname = driver.findElement(By.id("name"));
		plname.sendKeys(planname);
		WebElement datepick = driver.findElement(By.className("date-picker"));
		datepick.click();
		WebElement begindate = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/table/tbody/tr[4]/td[2]"));
		begindate.click();
		WebElement enddate = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/table/tbody/tr[4]/td[7]"));
		enddate.click();
		WebElement submit = driver.findElement(By.xpath("/html/body/div[6]/div[2]/input[1]"));
		submit.click();
		WebElement tobudget = driver.findElement(By.id("limit.totalBudget"));
		tobudget.sendKeys(totalbudget);
		WebElement dabudget = driver.findElement(By.id("limit.dailyBudget"));
		dabudget.sendKeys(dailybudget);
		WebElement totalexposure = driver.findElement(By.id("limit.impTotalLimit"));
		totalexposure.sendKeys(totallimitexposure);
		WebElement totalclick = driver.findElement(By.id("limit.clickTotalLimit"));
		totalclick.sendKeys(totallimitclick);
		WebElement dailyexposure = driver.findElement(By.id("limit.impDailyLimit"));
		dailyexposure.sendKeys(dailylimitexposure);
		WebElement dailyclick = driver.findElement(By.id("limit.clickDailyLimit"));
		dailyclick.sendKeys(dailylimitclick);
		WebElement exposureplanhz = driver.findElement(By.id("campaignIndvdLimitimpLimit"));
		exposureplanhz.sendKeys(planlimitexposure);
		WebElement clickplanhz = driver.findElement(By.id("campaignIndvdLimitclickLimit"));
		clickplanhz.sendKeys(planlimitclick);
		
        WebElement creativeexposure = driver.findElement(By.id("creativeIndvdLimitimpLimit"));
        creativeexposure.sendKeys(creativelimitexposure);
        WebElement creativeclick = driver.findElement(By.id("creativeIndvdLimitclickLimit"));
        creativeclick.sendKeys(creativdelimitclick);
        
        WebElement confirm = driver.findElement(By.name("submitForm"));
        confirm.click();
        assertEquals("�����ƻ�ʧ��",driver.getTitle(),"����Ͷ�Ų���");
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
