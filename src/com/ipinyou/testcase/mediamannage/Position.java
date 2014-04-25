package com.ipinyou.testcase.mediamannage;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;

public class Position {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	@Parameters({"loginname","password"})
	@Test
  public void login(String loginname,String password) {
		LoginInfo login = new LoginInfo(loginname,password);
		p.login(login, driver);
  }
	@Parameters({"channelname","websuit","partname"})
    @Test
    public void switchmedia(String channelname,String websuit,String partname){
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	WebElement media = driver.findElement(By.id("mediaMenu"));
    	media.click();
    	WebElement channellink = driver.findElement(By.linkText(channelname));
    	channellink.click();
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	WebElement weblink = driver.findElement(By.linkText(websuit));
    	weblink.click();
    	WebElement partlink = driver.findElement(By.linkText(partname));
    	partlink.click();
    }
	
	@Parameters({"positionname","possize","position","defaultprice"})
	@Test
	public void position1(String positionname,String possize,String position,String defaultprice){
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	     WebElement createposition = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/div[1]/a/span[2]")); 
	     createposition.click();
	     WebElement name = driver.findElement(By.id("name"));
	     name.sendKeys(positionname);
	     Select size = new Select(driver.findElement(By.id("adSizeId")));
	     size.selectByVisibleText(possize);
	     WebElement advtype = driver.findElement(By.id("notVideoRadio"));
	     advtype.click();
	     //֧�ִ�������
	     driver.findElement(By.id("supportedCreativeTypes0")).click();
	     driver.findElement(By.id("supportedCreativeTypes1")).click();
	     driver.findElement(By.id("supportedCreativeTypes2")).click();
	     driver.findElement(By.id("supportedCreativeTypes3")).click();
	     driver.findElement(By.id("supportedCreativeTypes4")).click();

	     //ѡ��λ��
	     PubHandle.select(driver, By.id("adLocation"), position);
	     //չʾ����
	     WebElement showtype = driver.findElement(By.id("displayType0"));
	     showtype.click();
	     //����ҳ��
	     WebElement location = driver.findElement(By.id("displayPage2"));
	     location.click();
	     //��׹��
	     WebElement hiddenadv = driver.findElement(By.id("openDefaultAdOpen"));
	     hiddenadv.click();
	     //״̬
	     WebElement status = driver.findElement(By.id("activeOpen"));
	     status.click();
	     //CTR�Ż�Ŀ��
	     driver.findElement(By.id("optimizeGoal2")).click();
	     
	     //���۷�ʽ
	     driver.findElement(By.id("valuationType0")).click();
	     //�������򶨼�
	     driver.findElement(By.id("openAreaPrice")).click();
	     driver.findElement(By.id("defaultPrice")).sendKeys(defaultprice);
	     
	     WebElement submit = driver.findElement(By.name("submitForm"));
	     submit.click();
	}
	
	@Parameters({"positionname1","Width","Height","videotype","defaultprice"})
	@Test
	public void position2(String positionname1,String Width,String Height,String videotype,String defaultprice){
		 driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	     WebElement createposition = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/div[1]/a/span[2]")); 
	     createposition.click();
	     WebElement name = driver.findElement(By.id("name"));
	     name.sendKeys(positionname1);
	     WebElement custom =  driver.findElement(By.id("btn-adSizeDefine"));
	     custom.click();
	     WebElement width = driver.findElement(By.id("adSizeDefineWidth"));
	     width.sendKeys(Width);
	     WebElement height = driver.findElement(By.id("adSizeDefineHeight"));
	     height.sendKeys(Height);
	     WebElement confirm = driver.findElement(By.id("btn-adSizeDefineConfirm"));
	     confirm.click();
	     
	     //���λ����
	     WebElement advtype = driver.findElement(By.id("isVideoRadio"));
	     advtype.click();
	     //֧�ִ�������
	     driver.findElement(By.id("supportedCreativeTypes0")).click();
	     driver.findElement(By.id("supportedCreativeTypes1")).click();
	     driver.findElement(By.id("supportedCreativeTypes2")).click();
	     driver.findElement(By.id("supportedCreativeTypes3")).click();
	     driver.findElement(By.id("supportedCreativeTypes4")).click();
	     //��Ƶ��վ����
	     PubHandle.select(driver, By.id("videoSite"), videotype);
	  
	     WebElement hiddenadv = driver.findElement(By.id("openDefaultAdOpen"));
	     hiddenadv.click();
	     WebElement status = driver.findElement(By.id("activeClose"));
	     status.click();
	   //CTR�Ż�Ŀ��
	     driver.findElement(By.id("optimizeGoal2")).click();
	     
	     //���۷�ʽ
	     driver.findElement(By.id("valuationType0")).click();
	     //�������򶨼�
	     driver.findElement(By.id("openAreaPrice")).click();
	     driver.findElement(By.id("defaultPrice")).sendKeys(defaultprice);
	     
	     WebElement submit = driver.findElement(By.name("submitForm"));
	     submit.click();
	}
	
	@Parameters({"positionname","positionname1"})
	@Test
	public void check(String positionname,String positionname1) throws InterruptedException{
		assertEquals("���λ����ʧ��",driver.getTitle(),"���λ�б�");
		WebElement search = driver.findElement(By.id("appendedInputButton"));
		search.sendKeys(positionname);
		WebElement dosearch = driver.findElement(By.id("searchInputButton"));
		dosearch.click();
		WebElement pos1 = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[4]//table/tbody/tr/td[3]"));
		System.out.println(pos1.getText());
		if(!pos1.getText().equals("autoadposition")){
			s.taskScreenShot(driver);
			assertEquals("ľ���ҵ�",pos1.getText(),"autoadposition");	
		}
		
		Thread.sleep(1000);
		WebElement search1 = driver.findElement(By.id("appendedInputButton"));
		search1.clear();
		search1.sendKeys(positionname1);
		WebElement dosearch1 = driver.findElement(By.id("searchInputButton"));
		dosearch1.click();
		WebElement pos2 = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[4]//table/tbody/tr/td[3]"));
		System.out.println(pos2.getText());
		if(!pos2.getText().equals("batchposition")){
			s.taskScreenShot(driver);
			assertEquals("ľ���ҵ�",pos2.getText(),"batchposition");
		}
		

	}
	
	public  boolean isElementExsit(WebDriver driver,By locator){
		boolean flag = false;
		try{
			driver.findElement(locator);
			flag=true;
		}catch(org.openqa.selenium.NoSuchElementException e){
			flag=false;		
		}
		return flag;
	}
	@Parameters({"browser"})
  @BeforeTest
  public void beforeTest(String browser) {
		driver = p.before(browser, driver);
  }

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }
}
