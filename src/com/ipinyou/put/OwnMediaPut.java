package com.ipinyou.put;

import static org.testng.AssertJUnit.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.pub.WindowOperation;

public class OwnMediaPut {
	WebDriver driver;
  @Test
  public void ownmediaput() {

	  WebElement frame = driver.findElement(By.xpath("/html/body/iframe"));
	  driver.switchTo().frame(frame);
	  WebElement link = driver.findElement(By.xpath("//*[@id='ipy_cover']/img"));
	  link.click();
	  WindowOperation wp = new WindowOperation();
	  wp.switchToWindow(driver, "百度一下，你就知道");
	  assertEquals("跳转正确，投放成功",driver.getTitle(), "百度一下，你就知道");
	  
  }
  @BeforeTest
  public void beforeTest() throws InterruptedException {
	  driver = new FirefoxDriver();
	  driver.get("http://localhost/web1.html");
	  driver.manage().window().maximize();
  }

  @AfterTest
  public void afterTest() {
	//  driver.quit();
  }

}
