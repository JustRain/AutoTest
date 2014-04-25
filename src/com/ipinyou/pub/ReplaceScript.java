package com.ipinyou.pub;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReplaceScript {
	
	
	public boolean replace(String filepath,String content){
        File f = new File(filepath);
        FileInputStream fs = null;
        InputStreamReader in = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        String textinLine;
        boolean flag = false;
        try {
                fs = new FileInputStream(f);
                in = new InputStreamReader(fs);
                br = new BufferedReader(in);
                while (true) {
                        textinLine = br.readLine();
                        if (textinLine == null)
                                break;
                        sb.append(textinLine);
                }
                int cnt2 = sb.indexOf("<body>")+6;
                if(sb.indexOf("script")==-1){
                    sb.insert(cnt2, content);
                }else{
                	System.out.println("已有代码，无需重复添加");
                }
               
                fs.close();
                in.close();
                br.close();
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        try {
                FileWriter fstream = new FileWriter(f);
                BufferedWriter outobj = new BufferedWriter(fstream);
                outobj.write(sb.toString());
                outobj.close();
                System.out.println("Done");
                return flag = true;
        } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
        }
		return flag;
	}

        public static void main(String[] args) {
        	ReplaceScript r = new ReplaceScript();
        	String filepath = "D:\\website\\web1.html";
        	String content = "<script src='http://file.ipinyou.com.cn/j/adunit/fixed/imp.js' data-i='41' data-w='450' data-h='550'></script>";
        	r.replace(filepath, content);
        	

}
}