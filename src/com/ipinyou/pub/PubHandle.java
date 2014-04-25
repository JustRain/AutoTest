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
		 usualcheck(driver, login.loginname+"��¼ʧ��", "��ҳ");
	}
	
	public void loginswitch(LoginInfo login,WebDriver driver){
		 driver.findElement(By.name("username")).sendKeys(login.loginname);
		 driver.findElement(By.name("password")).sendKeys(login.password);		
		 driver.findElement(By.cssSelector("input.btn")).click();
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 usualcheck(driver, login.loginname+"��¼ʧ��", "��ҳ");
		 driver.findElement(By.id("adAdminMenu")).click();
		 usualcheck(driver, "��ת��Ͷ�Ź���ʧ��", "������б�");
	}
	public void loginswitcho(LoginInfo login,WebDriver driver){
		driver.findElement(By.name("username")).sendKeys(login.loginname);
		 driver.findElement(By.name("password")).sendKeys(login.password);		
		 driver.findElement(By.cssSelector("input.btn")).click();
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 usualcheck(driver, login.loginname+"��¼ʧ��", "��ҳ");
		 driver.findElement(By.id("adAdminMenu")).click();
		 usualcheck(driver, "��ת��Ͷ�Ź���ʧ��", "�����б�");
	}
	public void logout(WebDriver driver) {
		driver.findElement(By.linkText("�˳�")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		usualcheck(driver, "�˳�ʧ��", "Ʒ���ųۣ�רΪ�߶��û������DSPƽ̨");
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
		  usualcheck(driver, "Optimusϵͳû�б���", "Ʒ���ųۣ�רΪ�߶��û������DSPƽ̨");
		  return driver;
	}
	
	//����frame��ת
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
				System.out.println(checkelement+" ���ͨ��������");
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
	
	//���Ƶ�ճ����
	public static void setClipboardData(String string) {
		StringSelection stsel = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);
	}
	//��������
	
	public static void dosearch(WebDriver driver,String info,String title) throws InterruptedException{
		driver.findElement(By.id("appendedInputButton")).sendKeys(info);
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		boolean flag = Check.usualexist(driver, title,2);
		if(flag){
			driver.findElement(By.linkText(info)).click();
			Thread.sleep(500);
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û����ת��"+title,title);
		}
	}
	public static void search(WebDriver driver,List<String> list,List<String> list1) throws InterruptedException{
		
		for(int i = 0;i<list.size();i++){
			dosearch(driver,list.get(i),list1.get(i));
		}
	}
	
	//�ж�ĳһԪ�ز�����
	public static boolean doesWebElementExist(WebDriver driver,By by){
		try{
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e){
			return false;
		}
		
	}
	
	//���������ֵ
	public static void selcheck(WebDriver driver,By by,String seltext){
       Select select = new Select(driver.findElement(by));
       String text = select.getFirstSelectedOption().getText();
       if(!text.equals(seltext)){
    	   ScreenshotandAssert.screenandassertseletext(driver, "��ѡ���"+seltext, seltext, text);
       }else{
    	   System.out.println(seltext+"���ͨ��");
       }
	}
	//���input valueֵ
	public static void checkvalue(WebDriver driver,By by,String element,String value) throws InterruptedException{
		boolean flag = Check.elementvalue(driver, by, 5, element, value);
		if(!flag){
			ScreenshotandAssert.screenandassertvalue(driver, element+"���ʧ��", element, by, value);
		}else{
			System.out.println(element+"���ͨ��");
		}
	}
	
	//titlelist������ҳ��title��
	public static ArrayList<String> titlelist(){
		List<String> titlelist = new ArrayList<String>();
		titlelist.add("������б�");
		titlelist.add("�����б�");
		titlelist.add("�ƻ��б�");
		titlelist.add("�����б�");
		titlelist.add("�����б�");
		return (ArrayList<String>) titlelist;
	}
	public static ArrayList<String> titlelists(){
		List<String> titlelist = new ArrayList<String>();
		titlelist.add("�����б�");
		titlelist.add("�ƻ��б�");
		titlelist.add("�����б�");
		titlelist.add("�����б�");
		return (ArrayList<String>) titlelist;
	}
	
	public static String getValue(WebDriver driver,By by,String value){
		return driver.findElement(by).getAttribute(value);
	}
	
	
	//��ȡ����IP��ַ
	public static String getHostIp() throws UnknownHostException{
		
		String hostip =  InetAddress.getLocalHost().getHostAddress().toString();
		//System.out.println(hostip);
		return hostip;
	}
	
	//��ȡPYID
	public static String getPyid(WebDriver driver){
		return driver.manage().getCookieNamed("PYID").getValue().toString();
	}
	
	
	public static void main(String args[]) throws UnknownHostException{
		getHostIp();
	}
}
