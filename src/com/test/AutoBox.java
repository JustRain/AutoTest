package com.test;

import java.util.ArrayList;
import java.util.List;

public class AutoBox {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		int i=3;
		list.add(i);
		
		for(Integer x : list){
			System.out.println(x);
		}

	}

}
