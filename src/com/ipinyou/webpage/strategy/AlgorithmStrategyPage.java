package com.ipinyou.webpage.strategy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ipinyou.entity.strategy.AlgorithmStrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class AlgorithmStrategyPage {
	public static void search(WebDriver driver,AlgorithmStrategyInfo asinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>(); 
		list.add(asinfo.getAdname());
		list.add(asinfo.getOrname());
		list.add(asinfo.getAlorplname());
		PubHandle.search(driver, list,PubHandle.titlelist());
	}
	
	public static void instrategy(WebDriver driver){
		driver.findElement(By.className("ico-add")).click();
		boolean flag1 = Check.usualexist(driver, "创建投放策略", 2);
		if(!flag1){
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入创建投放策略页", "创建投放策略");
		}
	}
	
	public static void createstrategy(WebDriver driver,AlgorithmStrategyInfo asinfo){
		System.out.println(asinfo.getAlgostrategyname());
		driver.findElement(By.id("name")).sendKeys(asinfo.getAlgostrategyname());
		PubHandle.select(driver, By.id("algorithmCode"),asinfo.getAlgorithm());
		PubHandle.select(driver, By.id("expectedGoal"), asinfo.getPricegoal());
		driver.findElement(By.id("cpc")).sendKeys(asinfo.getUnitprice());
		driver.findElement(By.name("submitForm")).click();
		boolean flag = Check.usualexist(driver, "上传创意", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "创建策略失败", "上传创意");
		}else{
			System.out.println(asinfo.getAlgostrategyname()+"Success");
		}
	}
	public static void firstcheck(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.linkText(asinfo.getAlorplname())).click();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.xpath("//*[@title='修改策略']")).click();
		boolean flag = Check.usualexist(driver, "修改投放策略", 5);
		if(flag){
			PubHandle.selcheck(driver, By.id("algorithmCode"), asinfo.getAlgorithm());
			PubHandle.selcheck(driver, By.id("expectedGoal"), asinfo.getPricegoal());
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入策略修改也", "修改投放策略");
		}
	}
	public static void firsteditstr(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.linkText(asinfo.getAlorplname())).click();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.xpath("//*[@title='修改策略']")).click();
		boolean flag = Check.usualexist(driver, "修改投放策略", 5);
		if(flag){
			PubHandle.select(driver, By.id("algorithmCode"), asinfo.getEditalgorithm());
			driver.findElement(By.name("submitForm")).click();
			boolean editflag = Check.usualexist(driver, "策略列表", 5);
			if(!editflag){
				ScreenshotandAssert.screenandasserttitle(driver, "修改失败", "策略列表");
			}else{
				System.out.println("低价算法修改成功");
			}
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入策略修改也", "修改投放策略");
		}
	}
	public static void firsteditcheck(WebDriver driver,AlgorithmStrategyInfo asinfo) throws InterruptedException{
		    driver.findElement(By.id("appendedInputButton")).clear();
			driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
			driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
			driver.findElement(By.xpath("//*[@title='修改策略']")).click();
			boolean flag = Check.usualexist(driver, "修改投放策略", 5);
			if(flag){
				PubHandle.selcheck(driver,By.id("algorithmCode"),asinfo.getEditalgorithm());
				PubHandle.checkvalue(driver, By.id("expectedParams"), asinfo.getCpcunit(), "value");
			}else{
				ScreenshotandAssert.screenandasserttitle(driver, "没有进入策略修改也", "修改投放策略");
			}
		}
	public static void secondeditstr(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.linkText(asinfo.getAlorplname())).click();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.xpath("//*[@title='修改策略']")).click();
		boolean flag = Check.usualexist(driver, "修改投放策略", 5);
		if(flag){
			PubHandle.select(driver, By.id("algorithmCode"), asinfo.getSecalgorithm());
            driver.findElement(By.id("cpmBidPrice")).sendKeys(asinfo.getSechighprice());
            driver.findElement(By.id("cpc")).sendKeys(asinfo.getSecunitprice());
			driver.findElement(By.name("submitForm")).click();
			boolean editflag = Check.usualexist(driver, "策略列表", 5);
			if(!editflag){
				ScreenshotandAssert.screenandasserttitle(driver, "修改失败", "策略列表");
			}else{
				System.out.println("第二次修改策略通过");
			}
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入策略修改也", "修改投放策略");
		}
	}
	
	public static void seceditcheck(WebDriver driver,AlgorithmStrategyInfo asinfo) throws InterruptedException{
		    driver.findElement(By.id("appendedInputButton")).clear();
			driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
			driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
			driver.findElement(By.xpath("//*[@title='修改策略']")).click();
			boolean flag = Check.usualexist(driver, "修改投放策略", 5);
			if(flag){
				PubHandle.checkvalue(driver, By.id("cpmBidPrice"), asinfo.getSechighprice(), "value");
				PubHandle.selcheck(driver,By.id("algorithmCode"),asinfo.getSecalgorithm());
				PubHandle.checkvalue(driver, By.id("expectedParams"), asinfo.getSecunitpricevalue(), "value");
			}else{
				ScreenshotandAssert.screenandasserttitle(driver, "没有进入策略修改也", "修改投放策略");
			}
	}
	
	
	public static void openstatus(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.linkText(asinfo.getAlorplname())).click();
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		WebElement strstatus = driver.findElement(By.className("btn-oval-close"));
	    if(strstatus.isDisplayed()){
			  strstatus.click();
		  }
	}
	
	public static void openedit(WebDriver driver,AlgorithmStrategyInfo asinfo){		
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.xpath("//*[@title='修改策略']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$('#algorithmCode').get(0).selectedIndex=3;", driver.findElement(By.id("algorithmCode")));
		driver.findElement(By.name("submitForm")).click();
	}
	public static void  secondcheck(WebDriver driver){
		driver.findElement(By.xpath("//*[@title='修改策略']")).click();
		boolean flag = Check.isenbled(driver, By.id("algorithmCode"), 5);
		if(flag){
			ScreenshotandAssert.screenandassert(driver, "算法没有关闭", flag, false);
		}else{
			System.out.println("状态开启后算法已不能修改");
		}
	}
	
	public static void copystr(WebDriver driver,AlgorithmStrategyInfo asinfo) throws InterruptedException{
		driver.findElement(By.linkText(asinfo.getAlorplname())).click();
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[5]/div[2]/a")).click();
		boolean flag = Check.elementexist(driver, By.linkText("批量复制策略"),5, "批量复制策略");
		if(flag){
			driver.findElement(By.linkText("批量复制策略")).click();
			PubHandle.switchframe(driver, By.className("myiframe"), "src", "campaignlist?id");
			boolean frameflag = Check.elementexist(driver, By.xpath("//*[@id='campaignlist']/table/thead/tr/th[2]"), 10, "计划名称");
			if(frameflag){
				driver.findElement(By.id("select_all")).click();
				driver.findElement(By.id("confirm")).click();
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "没有进入复制frame", "计划名称", By.xpath("//*[@id='campaignlist']/table/thead/tr/th[2]"));
			}
			driver.switchTo().defaultContent();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有找到批量复制策略", "批量复制策略",  By.linkText("批量复制策略"));
		}
		boolean copyflag = Check.elementexist(driver, By.linkText(asinfo.getCopystrategyname()), 5, asinfo.getCopystrategyname());
		if(!copyflag){
			ScreenshotandAssert.screenandasserttext(driver, asinfo.getAlgostrategyname()+"复制失败", asinfo.getCopystrategyname(), By.linkText(asinfo.getCopystrategyname()));
		}else{
			System.out.println(asinfo.getAlgostrategyname()+"复制success");
		}
	}
	
	public static void  editcopy(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getCopystrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.xpath("//*[@title='修改策略']")).click();
		boolean flag = Check.usualexist(driver, "修改投放策略", 5);
		if(flag){
			PubHandle.select(driver, By.id("algorithmCode"), asinfo.getCopyalgorithm());
			driver.findElement(By.name("submitForm")).click();
			System.out.println("同计划内复制策略修改成功");
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入策略修改也", "修改投放策略");
		}
	}
	
	public static void editcopycheck(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getCopystrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.xpath("//*[@title='修改策略']")).click();
		boolean flag = Check.usualexist(driver, "修改投放策略", 5);
		if(flag){
			PubHandle.selcheck(driver, By.id("algorithmCode"), asinfo.getCopyalgorithm());
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入策略修改也", "修改投放策略");
		}
	}
	
	public static void othereditcopystr(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.linkText(asinfo.getOrname())).click();
		pubseatch(driver, asinfo.getPlname());
		driver.findElement(By.linkText(asinfo.getPlname())).click();
		pubseatch(driver, asinfo.getCopystrategyname());
		driver.findElement(By.xpath("//*[@title='修改策略']")).click();
		boolean flag = Check.usualexist(driver, "修改投放策略", 5);
		if(flag){
			PubHandle.select(driver, By.id("algorithmCode"), asinfo.getOthercopyalgorithm());
			driver.findElement(By.name("submitForm")).click();
			System.out.println("外计划复制策略修改成功");
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入策略修改也", "修改投放策略");
		}
	}
	public static void othereditcopycheck(WebDriver driver,AlgorithmStrategyInfo asinfo){
		pubseatch(driver, asinfo.getCopystrategyname());
		driver.findElement(By.xpath("//*[@title='修改策略']")).click();
		boolean flag = Check.usualexist(driver, "修改投放策略", 5);
		if(flag){
			PubHandle.selcheck(driver, By.id("algorithmCode"), asinfo.getOthercopyalgorithm());
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入策略修改也", "修改投放策略");
		}
	}
	
	
	public static void pubcheck(WebDriver driver,By by,String checkelemet){
		String text = driver.findElement(by).getText();
		if(!text.equals(checkelemet)){
			ScreenshotandAssert.screenandasserttext(driver, checkelemet+"检查失败", checkelemet, by);
		}else{
			System.out.println(checkelemet+"检查pass");
		}
	}
	public static void pubseatch(WebDriver driver,String searchname){
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(searchname);
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
	}
}
