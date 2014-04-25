package com.ipinyou.testcase.mediamannage;

import static org.testng.AssertJUnit.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
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

public class BatchDownScript {
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
	public void batchdown() throws InterruptedException, AWTException{
		WebElement selall = driver.findElement(By.id("select_all"));
		selall.click();
		WebElement batchdown = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/div[2]/a"));
		batchdown.click();
		Thread.sleep(1000);
		WebElement downscript = driver.findElement(By.linkText("批量下载代码"));
		downscript.click();
		Thread.sleep(1000);
		keyBoardDemo();
		Thread.sleep(3000);
	}
	@Parameters({"filepath"})
	@Test
	public void check(String filepath) throws AWTException{
		File file = new File(filepath);
		assertEquals(true,file.exists());
		if(file.exists()){
			keyBoardDemo1();
			return;
		}else{
			return;
		}
	}
	@Parameters({"filepath"})
	@Test
	public void recover(String filepath){
	    File file = new File(filepath);
	    if(file.exists()){
	    	file.delete();
	    	return;
	    }else{
	    	return;
	    }
	}
	
	
	public void keyBoardDemo() throws AWTException { 
		 Robot robot = new Robot();	
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.keyRelease(KeyEvent.VK_ENTER);

	    }
	
	public void keyBoardDemo1() throws AWTException { 
		 Robot robot = new Robot();	
	     robot.keyPress(KeyEvent.VK_ALT);
	     robot.keyPress(KeyEvent.VK_F4);
	     robot.keyRelease(KeyEvent.VK_ALT);
	     robot.keyRelease(KeyEvent.VK_F4);
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
