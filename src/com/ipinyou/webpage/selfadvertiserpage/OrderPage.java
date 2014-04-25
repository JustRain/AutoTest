package com.ipinyou.webpage.selfadvertiserpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.selfadvertiser.OrderInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.ScreenshotandAssert;

public class OrderPage {
	public static By by = By.xpath("/html/body/div[3]/div/div/div[4]/div[1]/a/span[2]");
	
	public static boolean checkpage(WebDriver driver,String element,String reminder){
		try {
			boolean flag = Check.elementexist(driver,by,2, element);
			if(flag){
				return true;
			}else{
				ScreenshotandAssert.screenandasserttext(driver, reminder, element,by);
				return false;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void createorder(WebDriver driver,OrderInfo oinfo){
		driver.findElement(By.className("ico-add")).click();
		driver.findElement(By.id("name")).sendKeys(oinfo.getOrname());
		driver.findElement(By.id("totalBudget")).sendKeys(oinfo.getOrderbudget());
		driver.findElement(By.id("contractNo")).sendKeys(oinfo.getContractNo());
//		PubHandle.select(driver, By.id("kpiAttrkpiType1"), "点击单价");
//		driver.findElement(By.id("kpiAttr.price1")).clear();
//		driver.findElement(By.id("kpiAttr.price1")).sendKeys(oinfo.getKPI1());
//		PubHandle.select(driver, By.id("kpiAttrkpiType2"), "到达单价");
//		driver.findElement(By.id("kpiAttr.price2")).clear();
//		driver.findElement(By.id("kpiAttr.price2")).sendKeys(oinfo.getKPI2());
//		driver.findElement(By.id("convertPoint")).sendKeys(oinfo.getConverpoint());
		
		driver.findElement(By.name("submitForm")).click();
	}
	
	public static void check(WebDriver driver,OrderInfo oinfo) throws InterruptedException{
		
		driver.findElement(By.linkText(oinfo.getAdname())).click();
		driver.findElement(By.className("alter-icon")).click();
		boolean flag =  Check.elementexist(driver,By.xpath("//*[@id='order_form']/div[1]/table/tbody/tr[1]/td[1]/span[2]"), 5, "订单名称：");
		if(flag){
			boolean flag1 = Check.elementvalue(driver, By.id("name"), 5, oinfo.getOrname(), "value");
			if(!flag1){
				ScreenshotandAssert.screenandassertvalue(driver, "订单名称不对", oinfo.getOrname(), By.id("name"), "value");
			}
			boolean flag2 = Check.elementvalue(driver, By.id("totalBudget"), 5, oinfo.getOrderbudget()+".00", "value");
			if(!flag2){
				ScreenshotandAssert.screenandassertvalue(driver, "订单预算不对", oinfo.getOrderbudget()+".00", By.id("totalBudget"), "value");
			}
			boolean flag3 = Check.elementvalue(driver, By.id("contractNo"), 5, oinfo.getContractNo(), "value");
			if(!flag3){
				ScreenshotandAssert.screenandassertvalue(driver, "合同编号不对", oinfo.getContractNo(),  By.id("contractNo"), "value");
			}
			driver.findElement(By.name("submitForm")).click();
			Thread.sleep(1000);
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有跳到修改订单页", "订单名称：", By.xpath("//*[@id='order_form']/div[1]/table/tbody/tr[1]/td[1]/span[2]"));
		}
	}
//	public static int getuser(WebDriver driver){
//		String username = driver.findElement(By.className("account-name")).getText();
//		if(username.equals("selfautotest")){
//			return 1;
//		}else{
//			return 0;
//		}
//	}
}
