package com.ipinyou.webpage.batch.plan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.ipinyou.entity.strategy.AlgorithmPlanInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.DBConn;
import com.ipinyou.pub.DBService;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class BatchDelPlanPage {
	public static void search(WebDriver driver,AlgorithmPlanInfo pinfo) throws InterruptedException{
		 List<String> list = new ArrayList<String>();
		 list.add(pinfo.getAdname());
		 list.add(pinfo.getOrname());
		 PubHandle.search(driver, list,PubHandle.titlelist());
	}
	public static void inplan(WebDriver driver) throws InterruptedException{
			driver.findElement(By.className("ico-add")).click();
			boolean flag = Check.usualexist(driver, "创建广告计划", 2);
			if(!flag){
				ScreenshotandAssert.screenandasserttitle(driver, "没有进入创建计划页", "创建广告计划");
			
		}
	}
	public static void createplan(WebDriver driver,AlgorithmPlanInfo pinfo){
		driver.findElement(By.id("name")).sendKeys(pinfo.getPlname());
		driver.findElement(By.id("beginDate-endDate")).click();
		driver.findElement(By.xpath("//*[@class='calendar first']//*[@title='"+pinfo.getBegindate()+"']")).click();
		driver.findElement(By.xpath("//*[@class='calendar']//*[@title='"+pinfo.getEnddate()+"']")).click();
		driver.findElement(By.xpath("/html/body/div[7]/div[2]/input[1]")).click();
		driver.findElement(By.id("limit.totalBudget")).sendKeys(pinfo.getTotalbudget());
		driver.findElement(By.name("submitForm")).click();
	}
	public static void check(WebDriver driver){
		boolean flag = Check.usualexist(driver, "创建投放策略", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "计划创建失败", "创建投放策略");
		}
	}
	public static void delPlan(WebDriver driver,AlgorithmPlanInfo pinfo){
		driver.findElement(By.linkText(pinfo.getOrname())).click();
		driver.findElement(By.id("appendedInputButton")).sendKeys(pinfo.getPlname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/div[2]/a/span[1]")).click();
        driver.findElement(By.linkText("批量删除")).click();
        driver.findElement(By.className("confirm")).click();
        driver.findElement(By.id("appendedInputButton")).clear();
        driver.findElement(By.id("appendedInputButton")).sendKeys(pinfo.getPlname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
	    boolean flag = Check.exist(driver, By.linkText(pinfo.getPlname()), 5);
	    if(flag){
	    	ScreenshotandAssert.screenandassert(driver, "删除计划失败", flag,false);
	    }else{
	    	System.out.println("删除成功！！！！");
	    }
	}
	
	public static void datacheck(AlgorithmPlanInfo pinfo) throws SQLException{
		String sql = "select removed from campaign where name = '"+pinfo.getPlname()+"'";
		DBService.databasecheck(sql, "计划删除失败");
//		Connection conn = (Connection) DBConn.getconnection();
//		Statement stmt = (Statement) conn.createStatement();
//		int result = 0;
//		String sql = "select removed from campaign where name = '"+pinfo.getPlname()+"'";
//		ResultSet rs = stmt.executeQuery(sql);
//		while(rs.next()){
//			result = rs.getInt(1);
//			System.out.println(result);
//			
//		}
//		
//		if(result!= 1){
//			Assert.fail("计划删除失败");
//		}
	}

}
