package execute;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChanCase {
	
	public static void edit(String content){
		String filepath = "E:\\Eclipse workspace\\IpinyouSele\\run.bat";
		File f = new File(filepath);
        FileInputStream fs = null;
        InputStreamReader in = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        String textinLine;
        try{
        	fs = new FileInputStream(f);
        	in = new InputStreamReader(fs);
        	br = new BufferedReader(in);
        	while(true){
        	  textinLine = br.readLine();
        	  if(null == textinLine)
        		  break;
        	  sb.append(textinLine);
        	  sb.append("\n\r");
        	}
        	int index = sb.indexOf("\"")+10;
        	int last = sb.lastIndexOf("\"");
        	sb.replace(index, last,content);
        	System.out.println("Ìæ»»Íê³É");
            fs.close();
            in.close();
            br.close();
        }catch(FileNotFoundException e){
        	e.printStackTrace();
        }catch(IOException e){
        	e.printStackTrace();
        }
        try{
        	FileWriter fstream = new FileWriter(f);
            BufferedWriter outobj = new BufferedWriter(fstream);
            outobj.write(sb.toString());
            outobj.close();
            System.out.println("Done");
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	public static void main(String args[]){
		ChanCase.edit("xuchong");
	}
}
