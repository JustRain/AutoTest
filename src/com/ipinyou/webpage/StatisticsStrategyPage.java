package com.ipinyou.webpage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.StatisticsStrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ReadLiunx;
import com.ipinyou.pub.ScreenshotandAssert;

public class StatisticsStrategyPage {
	
	ReadLiunx rl = new ReadLiunx();
	
	public static void search(WebDriver driver,StatisticsStrategyInfo ssinfo){
		List<String> list = new ArrayList<String>(); 
		list.add(ssinfo.getAdname());
		list.add(ssinfo.getOrname());
		list.add(ssinfo.getPlname());
		try {
			PubHandle.search(driver, list,PubHandle.titlelist());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//创建第三方统计策略
	public static void createstatistic(WebDriver driver,StatisticsStrategyInfo ssinfo) throws InterruptedException{
		driver.findElement(By.id("appendedInputButton")).sendKeys(ssinfo.getStrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.id("select_all")).click();
		
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[5]/div[4]/a")).click();
		
		boolean flag = Check.usualexist(driver, "第三方统计策略列表", 5);
		if(flag){
			driver.findElement(By.className("ico-add")).click();
			boolean cflag= Check.usualexist(driver, "创建第三方监测策略", 5);
			if(cflag){
				driver.findElement(By.id("name")).sendKeys(ssinfo.getStatisticstrategy());
				driver.findElement(By.id("exchanges1")).click();
				driver.findElement(By.id("exchanges2")).click();
				driver.findElement(By.id("exchanges3")).click();
				driver.findElement(By.id("exchanges4")).click();
				driver.findElement(By.id("exchanges5")).click();
				driver.findElement(By.id("exchanges6")).click();
				driver.findElement(By.id("exchanges7")).click();
				driver.findElement(By.id("exchanges8")).click();
				driver.findElement(By.id("exchanges9")).click();
				driver.findElement(By.id("exchanges10")).click();
				//4.0新增盛大
//				driver.findElement(By.id("exchanges11")).click();
				driver.findElement(By.id("exchanges0")).click();
				driver.findElement(By.id("cpmBidPrice")).clear();
				driver.findElement(By.id("cpmBidPrice")).sendKeys(ssinfo.getStatisticprice());
				driver.findElement(By.id("remark")).sendKeys(ssinfo.getStatisticremark());
				driver.findElement(By.name("submitForm")).click();
				boolean crflag = Check.usualexist(driver, "添加第三方统计创意", 5);
				if(crflag){
					driver.findElement(By.xpath("/html/body/div[3]/form/div/div[2]/div/div/a/span")).click();
					boolean ccflag = Check.elementexist(driver, By.linkText("新增创意"), 5, "新增创意");
					if(ccflag){
						Thread.sleep (1000);
						driver.findElement(By.xpath("/html/body/div[3]/form/div/div[2]/div/div/ul/li/a")).click();
						driver.findElement(By.id("statsCreativeList0.theme")).sendKeys(ssinfo.getStatisticcreativetheme());
						driver.findElement(By.name("statsCreativeList[0].width")).sendKeys(ssinfo.getStawidth());
						driver.findElement(By.name("statsCreativeList[0].height")).sendKeys(ssinfo.getStaheight());
						driver.findElement(By.name("statsCreativeList[0].clickUrl")).sendKeys(ssinfo.getStaclickurl());
						driver.findElement(By.name("statsCreativeList[0].trackingUrls")).sendKeys(ssinfo.getStaexposeurl());
						driver.findElement(By.name("submitForm")).click();
						boolean check = Check.elementexist(driver, By.xpath("/html/body/div[3]/div/div/div[2]/div[4]/table/tbody/tr/td[3]/span"), 5, ssinfo.getStatisticcreativetheme());
						if(!check){
							ScreenshotandAssert.screenandasserttext(driver, "第三方创意创建失败",  ssinfo.getStatisticcreativetheme(), By.xpath("/html/body/div[3]/div/div/div[2]/div[4]/table/tbody/tr/td[3]/span"));					
							}else{
								System.out.println(ssinfo.getStatisticcreativetheme()+"检查通过");
							}
					}else{
						ScreenshotandAssert.screenandasserttext(driver, "没有找到新增创意项", "新增创意", By.linkText("新增创意"));
					}
					
				}else{
					ScreenshotandAssert.screenandasserttitle(driver, "没有进入添加第三方创意页", "添加第三方统计创意");
				}
			}else{
				ScreenshotandAssert.screenandasserttitle(driver, "没有进入新建第三方检测策略页", "创建第三方监测策略");
			}
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入第三方统计策略页",  "第三方统计策略列表");
		}
	}
	
	public static void intoStatistic(WebDriver driver,StatisticsStrategyInfo ssinfo) throws InterruptedException{
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[5]/div[4]/a")).click();
		Thread.sleep(500);
       	driver.findElement(By.linkText(ssinfo.getStatisticstrategy())).click();
	}
	
	public static HashMap<String, String> getClickUri(WebDriver driver){
		Map<String,String> click_map = new HashMap<String, String>();
       	String clickuri = PubHandle.getValue(driver, By.xpath("/html/body/div[3]/div/div/div[2]/div[4]/table/tbody/tr/td[6]/div/span[2]"), "title");
       	int ca = clickuri.lastIndexOf("/stats");
       	String argumentclickuri = clickuri.substring(ca);
       	click_map.put("clickuri", clickuri);
       	click_map.put("argument", argumentclickuri);
       	System.out.println("clickuri========================"+clickuri);
       	return (HashMap<String, String>) click_map;
	}
	public static HashMap<String,String> getImpressUri(WebDriver driver){
		Map<String,String> impree_map = new HashMap<String,String>();
		String impressuri = PubHandle.getValue(driver, By.xpath("/html/body/div[3]/div/div/div[2]/div[4]/table/tbody/tr/td[6]/div[2]/span[2]"), "title");
		int ia = impressuri.lastIndexOf("/stats");
		String argumentimpressuri = impressuri.substring(ia);
		impree_map.put("impressuri", impressuri);
		impree_map.put("argument", argumentimpressuri);
		return (HashMap<String, String>) impree_map;
	}
	
	public static String getArgumentClick(WebDriver driver){
		Map<String,String> amap = getClickUri(driver);
		System.out.println("ssss=================sssss====="+amap.get("argument").toString());
		return amap.get("argument").toString();
	}
	public static String getArgumentImpress(WebDriver driver){
		return getImpressUri(driver).get("argument");
	}
	
	public static String openClick(WebDriver driver){
		driver.navigate().to(getClickUri(driver).get("clickuri"));
		String actualclickuri = driver.getCurrentUrl();
		System.out.println("actualclickuri==============="+actualclickuri);
		return actualclickuri;
	}
	
	public static String openImpress(WebDriver driver){
		driver.navigate().to(getImpressUri(driver).get("impressuri"));
		String actualimpressuri = driver.getCurrentUrl();
		System.out.println("actualimpressuri================="+actualimpressuri);
		return actualimpressuri;
	}
	
	public static void clickuriCheck(WebDriver driver,StatisticsStrategyInfo ssinfo) throws IOException{
		System.out.println("ssinfo.getStaclickurl()============"+ssinfo.getStaclickurl());
		if(!openClick(driver).equals(ssinfo.getStaclickurl())){
			driver.navigate().back();
			ScreenshotandAssert.screenandasserttext(driver, "点击地址与预设地址不匹配", ssinfo.getStaclickurl(), openClick(driver));
		}else{
			driver.navigate().back();
			StatisticsStrategyPage ss  = new StatisticsStrategyPage();
			ss.clicklogCheck(driver, ssinfo);
			System.out.println(ssinfo.getStaclickurl()+"  正确！！！！");
		}
		
		System.out.println("aaaasssss========="+getArgumentClick(driver));
		
	}
	
	public static void impressuriCheck(WebDriver driver,StatisticsStrategyInfo ssinfo) throws IOException{
		if(!openImpress(driver).equals(ssinfo.getStaexposeurl())){
			ScreenshotandAssert.screenandasserttext(driver, "曝光地址与预设地址不匹配", ssinfo.getStaexposeurl(), openImpress(driver));
			driver.navigate().back();
		}else{
			driver.navigate().back();
			StatisticsStrategyPage ss  = new StatisticsStrategyPage();
			ss.impresslogCheck(driver, ssinfo);
			System.out.println(ssinfo.getStaexposeurl()+"  正确！！！！");
		}
		
        System.out.println("bbbbbbbsssssssss============"+getArgumentImpress(driver));
        
	}
	
	public  HashMap<String,String>  getClickLog(String log_type) throws IOException{
		Map<String,String> click_log = new HashMap<String,String>();
		click_log.put("log_ip", rl.getHostIp(log_type));
		click_log.put("log_pyid", rl.getPyid(log_type));
		click_log.put("log_uri", rl.getUri(log_type));
		return (HashMap<String, String>) click_log;
	}
	
	public HashMap<String,String> getImpressLog(String log_type) throws IOException{
		Map<String,String> impress_log = new HashMap<String,String>();
		impress_log.put("log_ip", rl.getHostIp(log_type));
		impress_log.put("log_pyid", rl.getPyid(log_type));
		impress_log.put("log_uri", rl.getUri(log_type));
		return (HashMap<String, String>) impress_log;
	}
	
	
	public  void clicklogCheck(WebDriver driver,StatisticsStrategyInfo ssinfo) throws IOException{
		Map<String,String> getclick_log = getClickLog(ssinfo.getClicklog());
		
		if(!getArgumentClick(driver).equals(getclick_log.get("log_uri"))){
			ScreenshotandAssert.onlyAssert("点击日志地址记录有误", getArgumentClick(driver), getclick_log.get("log_uri"));
		}else{
			System.out.println("lastclcik==================="+getclick_log.get("log_uri"));
		}
		if(!PubHandle.getHostIp().equals(getclick_log.get("log_ip"))){
			ScreenshotandAssert.onlyAssert("点击日志IP地址记录有误", PubHandle.getHostIp(), getclick_log.get("log_ip"));
		}else{
			System.out.println(getclick_log.get("log_ip")+"===============点击IP测试通过~~~~！！！！！！！！！！！");
		}
		if(!PubHandle.getPyid(driver).equals(getclick_log.get("log_pyid"))){  
			ScreenshotandAssert.onlyAssert("点击日志PYID记录有误", PubHandle.getPyid(driver), getclick_log.get("log_pyid"));
		}else{
			System.out.println("点击PYID ============="+getclick_log.get("log_pyid"));
		}
	}
	
	public void impresslogCheck(WebDriver driver,StatisticsStrategyInfo ssinfo) throws IOException{
		Map<String,String> getimpress_log = getImpressLog(ssinfo.getImpresslog());
		
		if(!getArgumentImpress(driver).equals(getimpress_log.get("log_uri"))){
			ScreenshotandAssert.onlyAssert("曝光日志地址记录有误", getArgumentImpress(driver),getimpress_log.get("log_uri"));
		}else{
			System.out.println("lastimpress================="+getimpress_log.get("log_uri"));
		}
		if(!PubHandle.getHostIp().equals(getimpress_log.get("log_ip"))){
			ScreenshotandAssert.onlyAssert("曝光日志IP地址记录有误", PubHandle.getHostIp(), getimpress_log.get("log_ip"));
		}else{
			System.out.println(getimpress_log.get("log_ip")+"===============曝光IP测试通过！！！！~~~~");
		}
		if(!PubHandle.getPyid(driver).equals(getimpress_log.get("log_pyid"))){
			ScreenshotandAssert.onlyAssert("曝光日志PYID记录有误", PubHandle.getPyid(driver),getimpress_log.get("log_pyid"));
		}else{
			System.out.println("曝光PYID ============="+getimpress_log.get("log_pyid"));
		}
	}

}








