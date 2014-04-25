package com.ipinyou.testcase.dsp;

import org.testng.annotations.Test;

import com.ipinyou.pub.SendMail;

public class SendMailTest {
  @Test
  public void sendmail() {
	  SendMail sendmail = new SendMail();
	  try{
		  Thread.sleep(5000);
		  sendmail.send();
		}catch(Exception e){
			e.printStackTrace();
		}
  }
}
