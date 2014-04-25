package com.test;

import org.testng.annotations.Test;

public class MultiThreadSample {
  @Test(invocationCount = 5,threadPoolSize = 10) 
  public void f() {
	  System.out.println("Ïß³ÌºÅÎª£º"+Thread.currentThread().getId());
  }
}
