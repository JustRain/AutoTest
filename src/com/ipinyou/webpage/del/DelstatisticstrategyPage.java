package com.ipinyou.webpage.del;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.StatisticsStrategyInfo;
import com.ipinyou.entity.StrategyInfo;
import com.ipinyou.pub.PubHandle;

public class DelstatisticstrategyPage {
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

}
