package com.ipinyou.testcase.ownmedia;

import static org.testng.AssertJUnit.assertEquals;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.beust.jcommander.Parameter;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;

public class UploadHisttoryCreative {
	WebDriver driver;
	PubHandle p  = new PubHandle();
	ScreenShot s = new ScreenShot();
	@Parameters({"loginname","password"})
	@Test
  public void login(String loginname,String password) {
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitch(login, driver);
  }
	@Parameters({"adname","orname","plname","strategyname"})
	@Test
	public void search(String adname,String orname,String plname,String strategyname) throws NoSuchElementException{
		WebElement searchad = driver.findElement(By.id("appendedInputButton"));
		searchad.sendKeys(adname);
		WebElement searcha = driver.findElement(By.xpath("//*[@id='queryForm']/button"));
		searcha.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement adlink = driver.findElement(By.linkText(adname));
		adlink.click();
		
		WebElement searchor = driver.findElement(By.id("appendedInputButton"));
		searchor.sendKeys(orname);
		WebElement searcho = driver.findElement(By.xpath("//*[@id='queryForm']/button"));
		searcho.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement  orderlink =  driver.findElement(By.linkText(orname));
		orderlink.click();
		
		WebElement searchpl = driver.findElement(By.id("appendedInputButton"));
		searchpl.sendKeys(plname);
		WebElement searchp  = driver.findElement(By.xpath("//*[@id='queryForm']/button"));
		searchp.click();
		
		WebElement planlink = driver.findElement(By.linkText(plname));
		planlink.click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		WebElement searchstra = driver.findElement(By.id("appendedInputButton"));
		searchstra.sendKeys(strategyname);
		WebElement searchst = driver.findElement(By.xpath("//*[@id='queryForm']/button"));
		searchst.click();
		
		WebElement strlink = driver.findElement(By.linkText(strategyname));
		strlink.click();
	}
	@Test
	public void increative() throws NoSuchElementException{
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		  WebElement addcreative = driver.findElement(By.className("ico-add"));
		  
		  addcreative.click();
	}
	@Parameters({"searchname"})
	@Test
	public void selecthistorycreative(String searchname) throws NoSuchElementException{
		WebElement uploadhistory = driver.findElement(By.id("selectFromHistory"));
		uploadhistory.click();
		WebElement upiframe = driver.findElement(By.className("myiframe"));
		driver.switchTo().frame(upiframe);
		WebElement inputname = driver.findElement(By.name("name"));
		inputname.sendKeys(searchname);
		WebElement dosearch = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/form/div/button"));
		dosearch.click();
		WebElement selcreative = driver.findElement(By.name("creativeIds"));
		selcreative.click();
		WebElement confirm = driver.findElement(By.cssSelector(".btn-success"));
		confirm.click();
		driver.switchTo().defaultContent();
	}
	@Parameters({"text1","text2","arriveadress"})
	@Test
	public void  choosecreative(String text1,String text2,String arriveadress){
		
		Select choosefirst = new Select(driver.findElement(By.cssSelector(".js-creative-tag-parent")));
		choosefirst.selectByVisibleText(text1);
		Select choosesecond  = new Select(driver.findElement(By.cssSelector(".js-creative-tag-child")));
		choosesecond.selectByVisibleText(text2);
		WebElement arriveurl = driver.findElement(By.cssSelector(".js-targetUrl-input"));
		arriveurl.clear();
		arriveurl.sendKeys(arriveadress);
		WebElement confirm = driver.findElement(By.name("submitForm"));
		confirm.click();	
	}
	@Parameters({"browser"})
  @BeforeTest
  public void beforeTest(String browser) {
		driver = p.before(browser, driver);
  }

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }
}
