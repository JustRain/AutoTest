package com.ipinyou.webpage.del;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.StrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.DBService;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class DelStrategyPage {
	public static void search(WebDriver driver,StrategyInfo sinfo){
		List<String> list = new ArrayList<String>(); 
		list.add(sinfo.getAdname());
		list.add(sinfo.getOrname());
		list.add(sinfo.getPlname());
		try {
			PubHandle.search(driver, list,PubHandle.titlelist());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void instrategy(WebDriver driver){
		driver.findElement(By.className("ico-add")).click();
		boolean flag = Check.usualexist(driver, "创建投放策略", 2);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入策略页",  "创建投放策略");
		}
	}
	public static void createstrategy(WebDriver driver,StrategyInfo sinfo){
		driver.findElement(By.id("name")).sendKeys(sinfo.getStrategyname());
		driver.findElement(By.name("submitForm")).click();
	}
	public static void uploadcreative(WebDriver driver,StrategyInfo sinfo) throws AWTException, InterruptedException{
	    driver.findElement(By.id("SWFUpload_0")).click();
	    PubHandle.setClipboardData(sinfo.getDelpath());
	    keyBord();
	    boolean flag = Check.elementexist(driver, By.className("upload-result"), 15, "本次上传创意，成功1，失败0");
	    if(flag){
	    	driver.findElement(By.linkText("确定")).click();
	    	driver.findElement(By.className("js-name-input")).sendKeys(sinfo.getDelcreativetheme());
	    	driver.findElement(By.cssSelector(".js-targetUrl-input")).clear();
	    	driver.findElement(By.cssSelector(".js-targetUrl-input")).sendKeys(sinfo.getDelurl());
	    	PubHandle.select(driver, By.cssSelector(".js-creative-tag-parent"), "公共服务");
	    	PubHandle.select(driver, By.cssSelector(".js-creative-tag-child"), "社会福利保障");
	    	driver.findElement(By.name("submitForm")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "上传创意失败", "本次上传创意，成功1，失败0", By.className("upload-result"));
	    }
	}
	
	public static void delcreative(WebDriver driver,StrategyInfo sinfo){
		driver.findElement(By.id("select_all")).click();
		driver.findElements(By.className("caret")).get(0).click();
		driver.findElement(By.linkText("批量删除")).click();
		driver.findElement(By.className("confirm")).click();
	}
	
	public static void delStr(WebDriver driver,StrategyInfo sinfo){
		driver.findElement(By.linkText(sinfo.getPlname())).click();
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(sinfo.getStrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.id("select_all")).click();
		driver.findElements(By.className("caret")).get(0).click();
		driver.findElement(By.linkText("批量删除")).click();
		driver.findElement(By.className("confirm")).click();
	}
	
	public static void strdatacheck(StrategyInfo sinfo) throws SQLException{
		System.out.println(sinfo.getStrategyname());
		String sql = "select removed from strategy where name = '"+sinfo.getStrategyname()+"'";
		DBService.databasecheck(sql,sinfo.getStrategyname()+"删除失败" );
	}
	
	public static void creadatacheck(StrategyInfo sinfo) throws SQLException{
		String sql = "select removed from strategy_creative_rel where strategy_id IN (SELECT id FROM strategy WHERE NAME = '"+sinfo.getStrategyname()+"')";
		DBService.databasecheck(sql, sinfo.getDelcreativetheme()+"删除失败");
	}
	
	public static void keyBord() throws AWTException{
		 Robot robot = new Robot();	
	     robot.keyPress(KeyEvent.VK_CONTROL); 
	     robot.keyPress(KeyEvent.VK_V); 
	     robot.keyRelease(KeyEvent.VK_CONTROL); 
	     robot.keyRelease(KeyEvent.VK_V); 
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.keyRelease(KeyEvent.VK_ENTER);
	}
}
