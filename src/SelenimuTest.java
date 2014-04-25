
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebElement;

@SuppressWarnings("unused")
public class SelenimuTest {
	static Map<String,Integer> map = new HashMap<String,Integer>();
	static Map<String,Integer> topFrame = new HashMap<String,Integer>();
	private WebElement masterelement;

	public static void main(String[] arga) throws InterruptedException{

		WebDriver driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\32.0.1700.107\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		driver.get("http://download.csdn.net/detail/zyw38121913/6959759");
		String source = driver.getPageSource();
		List<String> srcs = new ArrayList<String>();
		SelenimuTest st = new SelenimuTest();
		int x=1;
		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		for(int i=0;i<iframes.size();i++){
			topFrame.put(iframes.get(i).getAttribute("outerHTML"), 1);
		}
		st.begin(driver, srcs);
		while(srcs.size()<1){
			System.out.println("again......"+x++);
			driver.navigate().refresh();
			map.clear();
			topFrame.clear();
			iframes = driver.findElements(By.tagName("iframe"));
			for(int i=0;i<iframes.size();i++){
				topFrame.put(iframes.get(i).getAttribute("outerHTML"), 1);
			}
			st.begin(driver, srcs);
		}
		System.out.println("===============================scan over==============================");
		for(int i=0;i<srcs.size();i++){
			System.out.println("url:"+srcs.get(i).split(";")[0]);
			driver.switchTo().defaultContent();
			iframes = driver.findElements(By.tagName("iframe"));
			WebElement element = iframes.get((int) Integer.parseInt(srcs.get(i).split(";")[1]));
			System.out.println("location:"+element.getLocation());
			System.out.println("height:"+element.getAttribute("height")+"\n"+"width:"+element.getAttribute("width")+"\n");
		}
		/*
		TestHttpGet t = new TestHttpGet();
		try {
			t.executeGet("http://localhost:8080/springMVCtest/selenimutest?msg=find");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public void begin(WebDriver driver,List<String> srcList){
		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		for(int i=0;i<iframes.size();i++){
			System.out.println(i + " begin");
			map.clear();
			String fkey = checkCdFrameId(iframes.get(i).getAttribute("outerHTML"));
			map.put(fkey, 0);
			while(map.get(fkey)==0){
				traversalFrame(driver,iframes.get(i),srcList,fkey,i);
				if(map.get(fkey)>0){
					map.put(fkey, 0);
				}else{
					map.put(fkey, 1);
				}
				driver.switchTo().defaultContent();
			}
		}
		
	}
	
	public void traversalFrame(WebDriver driver,WebElement el,List<String> srcs,String parent,int parentIndex){
		String ellab = checkCdFrameId(el.getAttribute("outerHTML"));
		System.out.println("down:"+ellab);
		driver.switchTo().frame(el);
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		while(frames.size()>0){
			String outerhtml = checkCdFrameId(frames.get(0).getAttribute("outerHTML"));
			if(map.get(outerhtml)!=null){
				frames.remove(0);
			}else{
				break;
			}
		}
		
		if(frames.size()>0){
			map.put(parent, map.get(parent)+1);
			traversalFrame(driver,frames.get(0), srcs,parent,parentIndex);
		}else{
			List<WebElement> as = driver.findElements(By.tagName("*"));
			for(int i=0;i<as.size();i++){
				String href = as.get(i).getAttribute("href");
				if(href!=null){
					if(href.indexOf("ipinyou.com")>-1){
						srcs.add(href+";"+parentIndex);
					}
				}
				String src = as.get(i).getAttribute("src");
				if(src!=null){
					if(srcs.indexOf("ipinyou.com")>-1){
						srcs.add(src+";"+parentIndex);
					}
				}
			}
			if(topFrame.get(ellab)==null){
				System.out.println("put map:"+ellab);
				map.put(ellab, 0);
			}
			return;
		}
	}
	
	/**
	 * chrome和firefox处理有些区别，chrome会自动添加一个cd_frame_id的东西，需要对iframe的代码进行处理
	 * @param code
	 * @return
	 */
	private String checkCdFrameId(String code){
		if(code.indexOf("cd_frame_id")>-1){
			int index = code.indexOf("cd_frame_id");
			String s = code.substring(index-1, index+47); 
			code = code.replace(s, "");
		}
		return code;
	}
}
