package com.test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class getvalue {
	WebDriver driver;
  @Test
  public void f() throws InterruptedException {
	  
	   driver = new FirefoxDriver();
	   driver.navigate().to("http://console.ipinyou.com");
	   driver.findElement(By.name("username")).sendKeys("xuchong@ipinyou.com");
	   driver.findElement(By.name("password")).sendKeys("aaaaaa");
       driver.findElement(By.cssSelector("input.btn")).click();
	   Thread.sleep(2000);
	   driver.findElement(By.linkText("autoadvertiser")).click();
	   driver.findElement(By.className("alter-icon")).click();
	   System.out.println("The value is " +driver.findElement(By.id("name")).getAttribute("value"));
  }
}
