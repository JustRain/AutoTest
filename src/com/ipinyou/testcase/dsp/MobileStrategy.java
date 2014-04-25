package com.ipinyou.testcase.dsp;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.MobileStrategyInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.MobileStrategyPage;

public class MobileStrategy {
	WebDriver driver;
    ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	MobileStrategyInfo minfo = new MobileStrategyInfo();
	@Parameters({"loginname","password"})
	@Test
  public void login(String loginname,String password) throws NoSuchElementException {
		  LoginInfo login = new LoginInfo(loginname,password);
	      p.loginswitch(login, driver);
  }
	@Parameters({"adname","orname","plname"})
	@Test
	public void search(String adname,String orname,String plname) throws NoSuchElementException{
		
		minfo.setAdname(adname);
		minfo.setOrname(orname);
		minfo.setPlname(plname);
		try {
			MobileStrategyPage.search(driver, minfo, "策略列表","搜索失败");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void instrategy() throws NoSuchElementException{
		MobileStrategyPage.instrategy(driver, "创建投放策略", "没有进入创建策略页");
	}
	@Parameters({"strategyname","level","tbudget","dbudget","ctclick","impdlimit","cdlimit","indlimit","indvdcllimit","imptlimit"})
	@Test
	public void mobilestrategy(String strategyname,String level,String tbudget,String dbudget,String ctclick,String impdlimit,String cdlimit,String indlimit,
			String indvdcllimit,String imptlimit){
		minfo.setStrategyname(strategyname);
		minfo.setLevel(level);
		minfo.setTbudget(tbudget);
		minfo.setDbudget(dbudget);
		minfo.setCtclick(ctclick);
		minfo.setImpdlimit(impdlimit);
		minfo.setCdlimit(cdlimit);
		minfo.setIndlimit(indlimit);
		minfo.setIndvdcllimit(indvdcllimit);
		minfo.setImptlimit(imptlimit);
		
		
		
		WebElement name = driver.findElement(By.id("name"));
		name.sendKeys(strategyname);
		WebElement type = driver.findElement(By.id("advertisingMode1"));
		type.click();
		Select sellevel = new Select(driver.findElement(By.id("priority")));
		sellevel.selectByVisibleText(level);
		WebElement totalbudget = driver.findElement(By.id("limit.totalBudget"));
		totalbudget.sendKeys(tbudget);
		WebElement dailybudget = driver.findElement(By.id("limit.dailyBudget"));
		dailybudget.sendKeys(dbudget);
		WebElement imptotallimit = driver.findElement(By.id("limit.impTotalLimit"));
		imptotallimit.sendKeys(imptlimit);
		WebElement clicktotalclick = driver.findElement(By.id("limit.clickTotalLimit"));
		clicktotalclick.sendKeys(ctclick);
		WebElement impdailylimit = driver.findElement(By.id("limit.impDailyLimit"));
		impdailylimit.sendKeys(impdlimit);
		WebElement clickdailylimit = driver.findElement(By.id("limit.clickDailyLimit"));
		clickdailylimit.sendKeys(cdlimit);
		WebElement indvdlimitimplimit = driver.findElement(By.id("indvdLimitimpLimit"));
		indvdlimitimplimit.sendKeys(indlimit);
		WebElement indvdlimitclicklimit = driver.findElement(By.id("indvdLimitclickLimit"));
		indvdlimitclicklimit.sendKeys(indvdcllimit);
		
	}
	@Parameters({"beijing"})
	@Test
	public void highset(String beijing) throws NoSuchElementException, InterruptedException{
		WebElement highset = driver.findElement(By.id("toggleAdvancedSetting"));
		highset.click();
		WebElement audiencesset = driver.findElement(By.id("btn-audiences"));
		audiencesset.click();
		WebElement auiframe = driver.findElement(By.className("myiframe"));
		driver.switchTo().frame(auiframe);
		WebElement personalattention = driver.findElement(By.id("node_content_10005"));
		personalattention.click();
		WebElement source = driver.findElement(By.id("node_val_10006"));
		WebElement target = driver.findElement(By.cssSelector(".ui-droppable"));
		Actions action = new Actions(driver);
		action.dragAndDrop(source, target).perform();
		WebElement confirm = driver.findElement(By.id("confirm"));
		confirm.click();
		driver.switchTo().defaultContent();
		
		Select audienceweight = new Select(driver.findElement(By.id("audienceWeight")));
		audienceweight.selectByValue("Unlimited");
		
		WebElement areas = driver.findElement(By.id("btn-areas"));
		areas.click();
		String a = "customize#areas";
		List<WebElement> iframes = driver.findElements(By.className("myiframe"));
		for(int i =0;i<iframes.size();i++){
			String src = iframes.get(i).getAttribute("src");
			if(src.indexOf(a)!=-1){
				driver.switchTo().frame(iframes.get(i));
				WebElement jsearch = driver.findElement(By.id("j-search"));
				jsearch.sendKeys(beijing);
				WebElement selbeijing = driver.findElement(By.id("select_1"));
				selbeijing.click();
				WebElement areaconfirm = driver.findElement(By.id("confirm"));
				areaconfirm.click();
				driver.switchTo().defaultContent();
			}
		}
		
		WebElement timeset = driver.findElement(By.id("btn-dayParting"));
		timeset.click();
		List<WebElement> areaiframes = driver.findElements(By.className("myiframe"));

		for(int i =0;i<areaiframes.size();i++){
			if(areaiframes.get(i).getAttribute("src").indexOf("customize#dayParting")!=-1){
				driver.switchTo().frame(areaiframes.get(i));
				WebElement selall = driver.findElement(By.id("selectAll"));
				selall.click();
				WebElement timeconfirm = driver.findElement(By.id("confirm"));
				timeconfirm.click();
				driver.switchTo().defaultContent();
			}
		}
		WebElement exchanges = driver.findElement(By.id("btn-exchanges"));
		exchanges.click();
		List<WebElement> timeiframes = driver.findElements(By.className("myiframe"));
		for(int i =0;i<timeiframes.size();i++){
			if(timeiframes.get(i).getAttribute("src").indexOf("exchanges;adLocations")!=-1){
				driver.switchTo().frame(timeiframes.get(i));		
				WebElement google = driver.findElement(By.id("pltGoogleAmx"));
				google.click();
				WebElement exconfirm = driver.findElement(By.id("confirm"));
				exconfirm.click();
				driver.switchTo().defaultContent();
			}
		}
		WebElement ostype = driver.findElement(By.id("btn-mobileAttrosTypes"));
		ostype.click();
		List<WebElement> osiframes = driver.findElements(By.className("myiframe"));
		for(int i =0;i<osiframes.size();i++){
			if(osiframes.get(i).getAttribute("src").indexOf("mobileAttrosVersions")!=-1){
				driver.switchTo().frame(osiframes.get(i));
				WebElement android = driver.findElement(By.id("android"));
				android.click();
				WebElement ios = driver.findElement(By.id("ios"));
				ios.click();
				WebElement osconfirm = driver.findElement(By.id("confirm"));
				osconfirm.click();
				driver.switchTo().defaultContent();
			}
		}
		WebElement mobiletype = driver.findElement(By.id("btn-mobileAttrmobileSets"));
		mobiletype.click();
		List<WebElement> mobileiframes = driver.findElements(By.className("myiframe"));
		for(int i =0;i<mobileiframes.size();i++){
			if(mobileiframes.get(i).getAttribute("src").indexOf("mobileAttrmobileSetBrands")!=-1){
				driver.switchTo().frame(mobileiframes.get(i));
				WebElement cellphone = driver.findElement(By.id("cellphone"));
				cellphone.click();
				WebElement pad = driver.findElement(By.id("pad"));
				pad.click();
				WebElement mobconfirm = driver.findElement(By.id("confirm"));
				mobconfirm.click();
				driver.switchTo().defaultContent();
			}
		}
		WebElement apptype = driver.findElement(By.id("btn-mobileAttrappCategories"));
		apptype.click();
		List<WebElement> appiframes = driver.findElements(By.className("myiframe"));
		for(int i =0;i<appiframes.size();i++){
			if(appiframes.get(i).getAttribute("src").indexOf("mobileAttrappCategories")!=-1){
				driver.switchTo().frame(appiframes.get(i));
				WebElement allapp = driver.findElement(By.id("allapp"));
				allapp.click();
				WebElement appconfirm = driver.findElement(By.id("confirm"));
				appconfirm.click();
				driver.switchTo().defaultContent();
			}
		}
		WebElement wifi = driver.findElement(By.id("mobileAttr.mobileNetworks0"));
		wifi.click();
		WebElement chinamobile = driver.findElement(By.id("mobileAttr.mobileNetworks1"));
		chinamobile.click();
		WebElement unicom = driver.findElement(By.id("mobileAttr.mobileNetworks2"));
		unicom.click();
		WebElement telecom = driver.findElement(By.id("mobileAttr.mobileNetworks3"));
		telecom.click();
		WebElement submit = driver.findElement(By.name("submitForm"));
		submit.click();
	}
	
	
  @Parameters({"browser"})
  @BeforeTest
  public void beforeTest(String browser){
	  driver = p.before(browser, driver);
  }

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }
	}
