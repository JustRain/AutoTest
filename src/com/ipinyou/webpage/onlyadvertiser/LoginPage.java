package com.ipinyou.webpage.onlyadvertiser;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;

public class LoginPage {
	public static void login(WebDriver driver,LoginInfo login){
		 driver.findElement(By.name("username")).sendKeys(login.loginname);
		 driver.findElement(By.name("password")).sendKeys(login.password);
		 driver.findElement(By.cssSelector("input.btn")).click();
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 PubHandle.usualcheck(driver, login.loginname+"登录失败", "统计报表-投放效果分析报表");
	}
	public static void check(WebDriver driver){
		
	}

}
