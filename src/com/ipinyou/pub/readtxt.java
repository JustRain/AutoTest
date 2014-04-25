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
     * ���ܣ�Java��ȡtxt�ļ�������
     * ���裺1���Ȼ���ļ����
     * 2������ļ��������������һ���ֽ���������Ҫ��������������ж�ȡ
     * 3����ȡ������������Ҫ��ȡ�����ֽ���
     * 4��һ��һ�е������readline()��
     * ��ע����Ҫ���ǵ����쳣���
     * @param filePath
     * @return 
     */
    public static StringBuilder readTxtFile(String filePath){
    	StringBuilder a = new StringBuilder();
    	//String line = null;
        try {
                String encoding="UTF-8";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ����
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//���ǵ������ʽ
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                       a.append(lineTxt);                       
                    }
                    read.close();
        }else{
            System.out.println("�Ҳ���ָ�����ļ�");
        }
        } catch (Exception e) {
            System.out.println("��ȡ�ļ����ݳ���");
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