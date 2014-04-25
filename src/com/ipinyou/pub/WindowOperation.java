package com.ipinyou.pub;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import java.util.Iterator;
import java.util.Set;

public class WindowOperation {

	public boolean switchToWindow(WebDriver driver,String windowTitle){
		boolean flag = false;
		try {
			String currentHandle = driver.getWindowHandle();
			Set<String> handles = driver.getWindowHandles();
			for (String s : handles) {
				if (s.equals(currentHandle))
					continue;
				else {
					driver.switchTo().window(s);
					if (driver.getTitle().contains(windowTitle)) {
						flag = true;
						System.out.println("Switch to window: "
								+ windowTitle + " successfully!");
						break;
					} else
						continue;
				}
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Window: " + windowTitle
					+ " cound not found!");
			flag = false;
		}
		return flag;
	}

	 
}
