package com.ipinyou.pub;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class readtxt {
    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     * @return 
     */
    public static StringBuilder readTxtFile(String filePath){
    	StringBuilder a = new StringBuilder();
    	//String line = null;
        try {
                String encoding="UTF-8";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                       a.append(lineTxt);                       
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
		return a;
    }
    public static String script(String filepath) throws UnsupportedEncodingException{
    	String script = null;
    	script = URLEncoder.encode(readTxtFile(filepath).toString(),"UTF-8");
    	System.out.println(script);
    	return script;
    }
    
     
    public static void main(String argv[]) throws UnsupportedEncodingException{
        String filePath = "F:\\200-200.ftl";
        
//        readtxt.readTxtFile(filePath);
//        StringBuilder a = readtxt.readTxtFile(filePath);
        String a = script(filePath);
        System.out.println(a);
        		 
        
        
    }
     
     
 
}