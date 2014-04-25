package com.ipinyou.webpage.batch.mobile;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ipinyou.entity.batch.mobile.BatchCopyStrategyMobInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchCopyStrategyMobPage {
	public static void search(WebDriver driver,BatchCopyStrategyMobInfo bcinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bcinfo.getAdname());
		list.add(bcinfo.getOrname());
		list.add(bcinfo.getPlname());
		PubHandle.search(driver, list, PubHandle.titlelist());
		driver.findElement(By.id("appendedInputButton")).clear();
		if(!"".equals(bcinfo.getMobiletype())&&"app".equals(bcinfo.getMobiletype())){
			driver.findElement(By.id("appendedInputButton")).sendKeys(bcinfo.getAppstrname());
		}else if(!"".equals(bcinfo.getMobiletype())&&"web".equals(bcinfo.getMobiletype())){
			driver.findElement(By.id("appendedInputButton")).sendKeys(bcinfo.getWebstrname());
		}
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		
	}
	public static void batchcopy(WebDriver driver,BatchCopyStrategyMobInfo bcinfo) throws InterruptedException{
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
				driver.switchTo().defaultContent();
				boolean copydoneflag = Check.elementexist(driver, By.xpath("/html/body/div[3]/div/div/div[2]/div[1]/table/tbody/tr/td[1]/span"), 10, bcinfo.getPlname()+"：");
				if(!copydoneflag){
					ScreenshotandAssert.screenandasserttext(driver, "复制策略后跳转失败",  bcinfo.getPlname()+"：", By.xpath("/html/body/div[3]/div/div/div[2]/div[1]/table/tbody/tr/td[1]/span"));
				}
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "没有进入复制frame", "计划名称", By.xpath("//*[@id='campaignlist']/table/thead/tr/th[2]"));
			}
			
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有找到批量复制策略", "批量复制策略",  By.linkText("批量复制策略"));
		}
	}
	
	public static void check(WebDriver driver,BatchCopyStrategyMobInfo bcinfo) throws InterruptedException{
		if(!"".equals(bcinfo.getMobiletype())&&"app".equals(bcinfo.getMobiletype())){
			editstrpage(driver, bcinfo.getCopystr1());
			commoncheck(driver, bcinfo);
			datacheck(driver, By.xpath("//*[@id='mobileset-selected']/table/tbody/tr/td[2]"), bcinfo.getMobiledevice(),5);
			datacheck(driver, By.id("mobileAttr.idfas"), bcinfo.getBatchdirected(),5);
			datacheck(driver, By.id("count-mobileAttrappCategories"), bcinfo.getApptype(),5);
			datacheck(driver, By.id("blackWhiteAppblackAppIds"), bcinfo.getBatchappblacklistold()+"\n"+bcinfo.getBatchappblacklist(),5);
			datacheck(driver, By.id("blackWhiteAppwhiteAppIds"), bcinfo.getBatchappwhitelist()+"\n"+bcinfo.getBatchappwhitelistold(),5);
		}else if(!"".equals(bcinfo.getMobiletype())&&"web".equals(bcinfo.getMobiletype())){
			editstrpage(driver, bcinfo.getCopystr2());
			commoncheck(driver, bcinfo);
			datacheck(driver, By.id("count-blackWhiteUrlblackUrlIds"), bcinfo.getMediumblack(),5);
			datacheck(driver, By.id("count-blackWhiteUrlwhiteUrlIds"), bcinfo.getMediumwhite(),5);
		}
	}
	
	
	public static void pubcheck(WebDriver driver,String reminder,boolean flag){
		if(!flag){
			ScreenshotandAssert.screenandassert(driver, reminder, flag, true);
		}
	}
	
	public static void datacheck(WebDriver driver,By by,String text,int findtime) throws InterruptedException{
		boolean flag = Check.elementexist(driver, by, findtime, text);
		if(!flag){
			ScreenshotandAssert.screenandasserttext(driver, text+"检查失败", text, by);
		}else{
			System.out.println(text+" 检查通过");
		}
	}
	
	public static void editstrpage(WebDriver driver,String str){
		driver.findElement(By.id("appendedInputButton")).sendKeys(str);
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		boolean flag = Check.exist(driver, By.linkText(str), 5);
        if(flag){
			driver.findElement(By.className("alter-icon")).click();
			boolean strflag = Check.usualexist(driver, "修改投放策略", 5);
			if(!strflag){
				ScreenshotandAssert.screenandasserttitle(driver, "没有进入策略修改也", "修改投放策略");
			}else{
				driver.findElement(By.id("toggleAdvancedSetting")).click();
			}
        }else{
			pubcheck(driver, "没有找到"+str,flag);
        }
	}
	
	public static void commoncheck(WebDriver driver,BatchCopyStrategyMobInfo bcinfo) throws InterruptedException{
		datacheck(driver, By.xpath("//*[@id='audience-selected']/table/tbody/tr/td[2]"), bcinfo.getPeopleproperty(),5);
		datacheck(driver, By.xpath("//*[@id='audience-exclude-selected']/table/tbody/tr/td[2]"), bcinfo.getDaatexclude(),5);
		datacheck(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[2]/td[2]"), bcinfo.getClassfytext(),5);
		datacheck(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"), bcinfo.getContext(),5);
		datacheck(driver, By.xpath("//*[@id='exclude-selected']/table/tbody/tr[1]/td[2]"), bcinfo.getClassifyexclude(),5);
		datacheck(driver, By.xpath("//*[@id='exclude-selected']/table/tbody/tr[2]/td[2]"), bcinfo.getConexclude(),5);
		datacheck(driver, By.id("clickCookie-selected"), "当前已选点击找回类型："+"\n"+bcinfo.getClicksele(),5);
		datacheck(driver, By.className("geo-all"), bcinfo.getAreatext(),5);
		datacheck(driver, By.xpath("//*[@id='mobileset-selected']/table/tbody/tr/td[2]"), bcinfo.getMobiledevice(),5);
		PubHandle.selcheck(driver, By.id("dayPacing"), bcinfo.getEditblanceputin());
//		PubHandle.checkvalue(driver, By.id("preferDeal"), bcinfo.getEditpreferdeal(), "value");
		PubHandle.checkvalue(driver, By.id("cpmBidPrice"), bcinfo.getEdithighprice(), "value");
		PubHandle.selcheck(driver, By.id("algorithmCode"), bcinfo.getEditalgorithmCode());
		PubHandle.selcheck(driver, By.id("expectedGoal"), bcinfo.getEditpricegoal());
		PubHandle.checkvalue(driver, By.id("expectedParams"), bcinfo.getEditreachprice(), "value");
		PubHandle.selcheck(driver, By.id("effectGoal"), bcinfo.getEditeffectgoal());
		PubHandle.checkvalue(driver, By.id("effectGoalRate"), bcinfo.getEditrate(), "value");
		datacheck(driver, By.id("scriptCode"), bcinfo.getEditscriptcode(),5);
		datacheck(driver, By.id("remark"), bcinfo.getEditremark(),5);
		
	}

	public static void outcheck(WebDriver driver,BatchCopyStrategyMobInfo bcinfo) throws InterruptedException{
		driver.findElement(By.linkText(bcinfo.getOrname())).click();
		Thread.sleep(500);
		driver.findElement(By.id("appendedInputButton")).sendKeys(bcinfo.getPlname1());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.linkText(bcinfo.getPlname1())).click();
		boolean flag = Check.elementexist(driver, By.xpath("/html/body/div[3]/div/div/div[2]/div[1]/table/tbody/tr/td[1]/span"), 5, bcinfo.getPlname1()+"：");
		if(flag){
			if(!"".equals(bcinfo.getMobiletype())&&"app".equals(bcinfo.getMobiletype())){
				editstrpage(driver, bcinfo.getCopystr1());
				commoncheck(driver, bcinfo);
				datacheck(driver, By.id("mobileAttr.idfas"), bcinfo.getBatchdirected(),5);
				datacheck(driver, By.id("count-mobileAttrappCategories"), bcinfo.getApptype(),5);
				datacheck(driver, By.id("blackWhiteAppblackAppIds"), bcinfo.getBatchappblacklistold()+"\n"+bcinfo.getBatchappblacklist(),5);
				datacheck(driver, By.id("blackWhiteAppwhiteAppIds"), bcinfo.getBatchappwhitelist()+"\n"+bcinfo.getBatchappwhitelistold(),5);
			}else if(!"".equals(bcinfo.getMobiletype())&&"web".equals(bcinfo.getMobiletype())){
				editstrpage(driver, bcinfo.getCopystr2());
				commoncheck(driver, bcinfo);
				datacheck(driver, By.id("count-blackWhiteUrlblackUrlIds"), bcinfo.getMediumblack(),5);
				datacheck(driver, By.id("count-blackWhiteUrlwhiteUrlIds"), bcinfo.getMediumwhite(),5);
			}
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入"+bcinfo.getPlname1(), bcinfo.getPlname1()+"：",By.xpath("/html/body/div[3]/div/div/div[2]/div[1]/table/tbody/tr/td[1]/span"));
		}
	}
}
