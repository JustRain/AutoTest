package com.ipinyou.pub;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class DownScriptKeyBord {

	public static void keyBoardDemo() throws AWTException { 
		 Robot robot = new Robot();	
	     robot.keyPress(KeyEvent.VK_CONTROL);     
	     robot.keyPress(KeyEvent.VK_C); 
	     robot.keyRelease(KeyEvent.VK_CONTROL); 	     
	     robot.keyRelease(KeyEvent.VK_C); 
	    } 
}
