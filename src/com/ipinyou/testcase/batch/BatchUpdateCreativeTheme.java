package com.ipinyou.testcase.batch;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchElementException;
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
import com.ipinyou.entity.batch.creative.BatchUpdateCreativeThemeInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.creative.BatchUpdateCreativeThemePage;


public class BatchUpdateCreativeTheme {
	WebDriver driver;
    ScreenShot s = new ScreenShot();
    PubHandle p  = new PubHandle();
    BatchUpdateCreativeThemeInfo btinfo =  new BatchUpdateCreativeThemeInfo();
    @Parameters({"browser"})
  @BeforeTest
  public void beforeTest(String browser) {
    	 driver = p.before(browser, driver);
	  }
  @Parameters({"loginname","password"})
	@Test
  public void login(String loginname,String password) throws NoSuchElementException{
	  LoginInfo login = new LoginInfo(loginname,password);
      p.loginswitch(login, driver);
	  }
	
	@Parameters({"adname","orname","plname","strname"})
	@Test
	public void search(String adname,String orname,String plname,String strname) throws NoSuchElementException, InterruptedException{
		btinfo.setAdname(adname);
		btinfo.setOrname(orname);
		btinfo.setPlname(plname);
		btinfo.setStrname(strname);
		BatchUpdateCreativeThemePage.search(driver, btinfo);
	}
	
	@Parameters({"creativetheme"})
	@Test
	public void batchupdatecreativetheme(String creativetheme) throws InterruptedException{
		
		btinfo.setCreativetheme(creativetheme);
		BatchUpdateCreativeThemePage.batchupdatecreativetheme(driver, btinfo);
	}
	
	@Parameters({"creativetheme"})
	@Test
	public void check(String creativetheme){
		btinfo.setCreativetheme(creativetheme);
	}
	
	@Parameters({"creativethemerecover"})
	@Test
	public void recover(String creativethemerecover) throws InterruptedException{
		
		btinfo.setCreativethemerecover(creativethemerecover);
		
		WebElement batch = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[4]/div[2]/a"));
		batch.click();
		Thread.sleep(1000);
		WebElement batchupdate = driver.findElement(By.linkText("批量修改主题"));
		batchupdate.click();
		WebElement theme = driver.findElement(By.className("js-input"));
		theme.sendKeys(creativethemerecover);
		WebElement confirm = driver.findElement(By.linkText("确  定"));
		confirm.click();
		Thread.sleep(1000);
		WebElement updatetheme = driver.findElement(By.xpath("//*[@id='creative_list']/tr/td[4]/span"));
		System.out.println(updatetheme.getText());
		if(!updatetheme.getText().equals(creativethemerecover)){
			s.taskScreenShot(driver);
			assertEquals("主题恢复失败",updatetheme.getText(),creativethemerecover);
		}
		
	}

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }
}
