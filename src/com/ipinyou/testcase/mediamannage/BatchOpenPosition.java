package com.ipinyou.testcase.mediamannage;

import static org.testng.AssertJUnit.assertEquals;

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

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;

public class BatchOpenPosition {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	@Parameters({"loginname","password"})
	@Test
  public void login(String loginname,String password) {
		LoginInfo login = new LoginInfo(loginname,password);
		p.login(login, driver); 	
  }
	@Parameters({"channelname","websuit","partname"})
    @Test
    public void switchmedia(String channelname,String websuit,String partname){
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	WebElement media = driver.findElement(By.id("mediaMenu"));
    	media.click();
    	WebElement channellink = driver.findElement(By.linkText(channelname));
    	channellink.click();
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	WebElement weblink = driver.findElement(By.linkText(websuit));
    	weblink.click();
    	WebElement partlink = driver.findElement(By.linkText(partname));
    	partlink.click();
    }
	@Test
	public void batchopen() throws InterruptedException{
		WebElement selall = driver.findElement(By.id("select_all"));
		selall.click();
		WebElement batchopen = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/div[2]/a"));
		batchopen.click();
		Thread.sleep(1000);
		WebElement open = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/div[2]/ul/li[1]/a"));
		open.click();
		Thread.sleep(1000);
		WebElement confirm = driver.findElement(By.className("confirm"));
		confirm.click();
		Thread.sleep(1000);
	}
	
	@Test
	public void check(){
		WebElement status1 = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[4]/table/tbody/tr[1]/td[11]"));
		System.out.println(status1.getText());
		if(!status1.getText().equals("开启")){
			s.taskScreenShot(driver);
			assertEquals("批量开启失败",status1.getText(),"开启");	
		}
		
		WebElement status2 = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[4]/table/tbody/tr[2]/td[11]"));
		System.out.println(status2.getText());
		if(!status2.getText().equals("开启")){
			s.taskScreenShot(driver);
			assertEquals("批量开启失败",status2.getText(),"开启");	
		}
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
