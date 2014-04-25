package execute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteBat {
	
	public static void main(String args[]) throws IOException{
		 ChooseCase.getid();
		 Process process = Runtime.getRuntime().exec("E:/Eclipse workspace/IpinyouSele/run.bat ");    
		 BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream())); 
         String str = null; 
         while ((str = read.readLine()) != null){ 
             System.out.println(str);
	}
	}
}
