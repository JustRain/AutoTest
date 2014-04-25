package com.ipinyou.pub;

import static org.testng.AssertJUnit.assertEquals;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.ipinyou.entity.LoginInfo;


public class PubHandle {
	static Check checkexit = new Check();

	public void login(LoginInfo login,WebDriver driver){
		 driver.findElement(By.name("username")).sendKeys(login.loginname);
		 driver.findElement(By.name("password")).sendKeys(login.password);
		 driver.findElement(By.cssSelector("input.btn")).click();
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 usualcheck(driver, login.loginname+"登录失败", "首页");
	}
	
	public void loginswitch(LoginInfo login,WebDriver driver){
		 driver.findElement(By.name("username")).sendKeys(login.loginname);
		 driver.findElement(By.name("password")).sendKeys(login.password);		
		 driver.findElement(By.cssSelector("input.btn")).click();
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 usualcheck(driver, login.loginname+"登录失败", "首页");
		 driver.findElement(By.id("adAdminMenu")).click();
		 usualcheck(driver, "跳转到投放管理失败", "广告主列表");
	}
	public void loginswitcho(LoginInfo login,WebDriver driver){
		driver.findElement(By.name("username")).sendKeys(login.loginname);
		 driver.findElement(By.name("password")).sendKeys(login.password);		
		 driver.findElement(By.cssSelector("input.btn")).click();
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 usualcheck(driver, login.loginname+"登录失败", "首页");
		 driver.findElement(By.id("adAdminMenu")).click();
		 usualcheck(driver, "跳转到投放管理失败", "订单列表");
	}
	public void logout(WebDriver driver) {
		driver.findElement(By.linkText("退出")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		usualcheck(driver, "退出失败", "品友优驰，专为高端用户服务的DSP平台");
	}
	
	public  WebDriver before(String browser,WebDriver driver){
		 if(browser.equals("firefox")){
			  System.setProperty("webdriver.firefox.bin",Globalsetting.firefoxdriverpath);
			  driver = new FirefoxDriver();  
		  }else if(browser.equals("chrome")){
			  System.setProperty("webdriver.chrome.driver", Globalsetting.chromedriverpath);
			  driver = new ChromeDriver();
		  }
		  driver.get(Globalsetting.openurl);
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		  usualcheck(driver, "Optimus系统没有被打开", "品友优驰，专为高端用户服务的DSP平台");
		  return driver;
	}
	
	//公共frame跳转
	public static void switchframe(WebDriver driver,By by,String attribute,String element) throws InterruptedException{
		List<WebElement> frame = driver.findElements(by);
		for(int i=0;i<frame.size();i++){
			if(frame.get(i).getAttribute(attribute).indexOf(element)!=-1){
				driver.switchTo().frame(frame.get(i));
				Thread.sleep(500);
			}
		}
	}
	
	public static void drag(WebDriver driver,By by1,By by2){
	   WebElement source = driver.findElement(by1);
	   WebElement target = driver.findElement(by2);
	   Actions action = new Actions(driver);
	   action.dragAndDrop(source, target).perform();
	}
	
	public static void select(WebDriver driver,By by,String text){
		Select select = new Select(driver.findElement(by));
		select.selectByVisibleText(text);
	}
	
	public static  boolean usualcheck(WebDriver driver,String reminder,String checkelement){
    	boolean flag = Check.usualexist(driver, checkelement, 2);
    	if(flag){
    		return true;
    	}else{
    		ScreenShot.taskScreenShot(driver);
    		Reporter.log(reminder);
    		assertEquals(reminder,driver.getTitle(),checkelement);
    		return false;
    	}
    }
	public static boolean usualelementcheck(WebDriver dr,By by,int findtime,String reminder,String checkelement) throws InterruptedException{
		boolean flag = Check.elementexist(dr, by, findtime, checkelement);
		if(!flag){
			ScreenshotandAssert.screenandasserttext(dr, reminder, checkelement, by);
			return false;		
			}else{
				System.out.println(checkelement+" 检查通过！！！");
			}
		return true;
	} 
	public static void isdisplay(WebDriver driver,By by,int findtime,String reminder,boolean expected){
		boolean flag = Check.exist(driver, by, findtime);
		if(!flag){
			ScreenshotandAssert.screenandassert(driver, reminder, flag, expected);
		}
	}
	public static void isenbled(WebDriver driver,By by,String reminder,boolean expected){
		boolean flag = Check.isenbled(driver, by, 2);
		if(!flag){
			ScreenshotandAssert.screenandassertisenbled(driver, reminder, expected, by);
		}
	}
	
	//复制到粘贴板
	public static void setClipboardData(String string) {
		StringSelection stsel = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
	}
	//公共搜索
	
	public static void dosearch(WebDriver driver,String info,String title) throws InterruptedException{
		driver.findElement(By.id("appendedInputButton")).sendKeys(info);
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		boolean flag = Check.usualexist(driver, title,2);
		if(flag){
			driver.findElement(By.linkText(info)).click();
			Thread.sleep(500);
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有跳转到"+title,title);
		}
	}
	public static void search(WebDriver driver,List<String> list,List<String> list1) throws InterruptedException{
		
		for(int i = 0;i<list.size();i++){
			dosearch(driver,list.get(i),list1.get(i));
		}
	}
	
	//判断某一元素不存在
	public static boolean doesWebElementExist(WebDriver driver,By by){
		try{
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e){
			return false;
		}
		
	}
	
	//检查下拉框值
	public static void selcheck(WebDriver driver,By by,String seltext){
       Select select = new Select(driver.findElement(by));
       String text = select.getFirstSelectedOption().getText();
       if(!text.equals(seltext)){
    	   ScreenshotandAssert.screenandassertseletext(driver, "首选项不是"+seltext, seltext, text);
       }else{
    	   System.out.println(seltext+"检查通过");
       }
	}
	//检查input value值
	public static void checkvalue(WebDriver driver,By by,String element,String value) throws InterruptedException{
		boolean flag = Check.elementvalue(driver, by, 5, element, value);
		if(!flag){
			ScreenshotandAssert.screenandassertvalue(driver, element+"检查失败", element, by, value);
		}else{
			System.out.println(element+"检查通过");
		}
	}
	
	//titlelist（管理页面title）
	public static ArrayList<String> titlelist(){
		List<String> titlelist = new ArrayList<String>();
		titlelist.add("广告主列表");
		titlelist.add("订单列表");
		titlelist.add("计划列表");
		titlelist.add("策略列表");
		titlelist.add("创意列表");
		return (ArrayList<String>) titlelist;
	}
	public static ArrayList<String> titlelists(){
		List<String> titlelist = new ArrayList<String>();
		titlelist.add("订单列表");
		titlelist.add("计划列表");
		titlelist.add("策略列表");
		titlelist.add("创意列表");
		return (ArrayList<String>) titlelist;
	}
	
	public static String getValue(WebDriver driver,By by,String value){
		return driver.findElement(by).getAttribute(value);
	}
	
	
	//获取本地IP地址
	public static String getHostIp() throws UnknownHostException{
		
		String hostip =  InetAddress.getLocalHost().getHostAddress().toString();
		//System.out.println(hostip);
		return hostip;
	}
	
	//获取PYID
	public static String getPyid(WebDriver driver){
		return driver.manage().getCookieNamed("PYID").getValue().toString();
	}
	
	
	public static void main(String args[]) throws UnknownHostException{
		getHostIp();
	}
}
