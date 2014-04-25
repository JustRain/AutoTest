package com.ipinyou.pub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.connection.ChannelInputStream;
import com.sshtools.j2ssh.connection.ChannelOutputStream;
import com.sshtools.j2ssh.session.PseudoTerminal;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.sftp.SftpFile;
import com.sshtools.j2ssh.transport.ConsoleKnownHostsKeyVerification;
import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;

public class ReadLiunx {

	SshClient ssclient = new SshClient();

	//连接liunx服务器
	public  boolean connectLisnux(){
         try{
        	 ssclient.connect("10.1.1.3", 22, new IgnoreHostKeyVerification());
    		 PasswordAuthenticationClient pass = new PasswordAuthenticationClient();
    		 pass.setUsername("chong.xu");
    		 pass.setPassword("chong.xuchong.xu");
    		 int result = ssclient.authenticate(pass);
    		 if(result == AuthenticationProtocolState.COMPLETE){
    			 return true;
    		 }else{
    			 return false;
    		 }
         }catch(IOException  e){
        	 e.printStackTrace();
         }
         return false;
	}
	
	
	//获取/data/ef-logs/ing下最新日志
	public  String getClickLogName(String getlogname) throws IOException{
		String click_log_name = null;
		
		boolean flag = connectLisnux();
		if(flag){
			 SessionChannelClient session = ssclient.openSessionChannel();
 		    if(session.startShell()){
 		    	OutputStream writer = session.getOutputStream();
// 		    	writer.write("pwd \n".getBytes());
 		    	String command = "ls -t /data/ef-logs/ing/1.*."+getlogname+".stats.log | head -1 \n";
// 		    	writer.write("ls -t /data/ef-logs/ing/1.*.click.stats.log | head -1 \n".getBytes());
 		    	writer.write(command.getBytes());
// 		    	writer.write("awk -F '\t' \'{print $21}\' /data/ef-logs/ing/1.7.click.stats.log \n".getBytes());
 		    	writer.flush();
 		    	writer.write("exite\n".getBytes());
 		    	writer.flush();
 		    	BufferedReader in = new BufferedReader(new InputStreamReader(session.getInputStream()));
 		    	BufferedReader err = new BufferedReader(new InputStreamReader(session.getStderrInputStream()));
 		    	String line = null;
 		    	while((line = in.readLine())!=null){
 		    		int a = line.lastIndexOf("/");
 		    		click_log_name = line.substring(a+1);
 		    		//click_log_name = line;
 		    		System.out.println("message============="+click_log_name);
 		    		return click_log_name;
 		    	}  
 		    	while((line = err.readLine())!=null){
 		    		System.out.println(line);
 		    	}
 		    	if(session!=null){
 		    		session.close();
 		    	}
 		    }
		}
		return click_log_name;
	}
	
	//获取曝光/点击日志中uri
	public String getUri(String log) throws IOException{
		ArrayList<String> uri_list = new ArrayList<String>();
		String log_uri = null;
		boolean flag = connectLisnux();
		if(flag){
			 SessionChannelClient session = ssclient.openSessionChannel();
 		    if(session.startShell()){
 		    	OutputStream writer = session.getOutputStream();
// 		    	writer.write("pwd \n".getBytes());
// 		    	writer.write("ls -t /data/ef-logs/ing | head -1 \n".getBytes());
 		    	String command = "awk -F '\t' \'{print $21}\' /data/ef-logs/ing/"+getClickLogName(log)+" | tail -1  \n";
 		    	System.out.println("command====================="+command);
 		    	writer.write(command.getBytes());
 		    	writer.flush();
 		    	writer.write("exite\n".getBytes());
 		    	writer.flush();
 		    	BufferedReader in = new BufferedReader(new InputStreamReader(session.getInputStream()));
 		    	BufferedReader err = new BufferedReader(new InputStreamReader(session.getStderrInputStream()));
 		    	String line = null;
 		    	while((line = in.readLine())!=null){
 		    		uri_list.add(line);
 		    		log_uri = line.toString();
 		    		System.out.println("log_uri============="+log_uri);
 		    		return log_uri;
 		    	}  
 		    	while((line = err.readLine())!=null){
 		    		System.out.println(line);
 		    	}
 		    	if(session!=null){
 		    		session.close();
 		    	}
 		    }
		}
		return log_uri;
		
	}
	
	//获取曝光/点击日志中Pyid
	public String getPyid(String log) throws IOException{
		ArrayList<String> pyid_list = new ArrayList<String>();
		String py_id = null;
		boolean flag = connectLisnux();
		if(flag){
			 SessionChannelClient session = ssclient.openSessionChannel();
 		    if(session.startShell()){
 		    	OutputStream writer = session.getOutputStream();
// 		    	writer.write("pwd \n".getBytes());
// 		    	writer.write("ls -t /data/ef-logs/ing | head -1 \n".getBytes());
 		    	String command = "awk -F '\t' \'{print $16}\' /data/ef-logs/ing/"+getClickLogName(log)+" | tail -1 \n";
 		    	System.out.println("command====================="+command);
 		    	writer.write(command.getBytes());
 		    	writer.flush();
 		    	writer.write("exite\n".getBytes());
 		    	writer.flush();
 		    	BufferedReader in = new BufferedReader(new InputStreamReader(session.getInputStream()));
 		    	BufferedReader err = new BufferedReader(new InputStreamReader(session.getStderrInputStream()));
 		    	String line = null;
 		    	while((line = in.readLine())!=null){
 		    		pyid_list.add(line);
 		    		py_id = line.toString();
 		    		System.out.println("py_id============="+py_id);
 		    		return py_id;
 		    	}  
 		    	while((line = err.readLine())!=null){
 		    		System.out.println(line);
 		    	}
 		    	if(session!=null){
 		    		session.close();
 		    	}
 		    }
		}
		return py_id;
		
	}
	
	//获取曝光/点击日志中te
	public String getHostIp(String log) throws IOException{
		ArrayList<String> hostip_list = new ArrayList<String>();
		String host_ip = null;
		boolean flag = connectLisnux();
		if(flag){
			 SessionChannelClient session = ssclient.openSessionChannel();
 		    if(session.startShell()){
 		    	OutputStream writer = session.getOutputStream();
// 		    	writer.write("pwd \n".getBytes());
// 		    	writer.write("ls -t /data/ef-logs/ing | head -1 \n".getBytes());
 		    	String command = "awk -F '\t' \'{print $33}\' /data/ef-logs/ing/"+getClickLogName(log)+" | tail -1 \n";
 		    	System.out.println("command====================="+command);
 		    	writer.write(command.getBytes());
 		    	writer.flush();
 		    	writer.write("exite\n".getBytes());
 		    	writer.flush();
 		    	BufferedReader in = new BufferedReader(new InputStreamReader(session.getInputStream()));
 		    	BufferedReader err = new BufferedReader(new InputStreamReader(session.getStderrInputStream()));
 		    	String line = null;
 		    	while((line = in.readLine())!=null){
 		    		hostip_list.add(line);
 		    		host_ip = line.toString();
 		    		System.out.println("host_ip============="+host_ip);
 		    		return host_ip;
 		    	}  
 		    	while((line = err.readLine())!=null){
 		    		System.out.println(line);
 		    	}
 		    	if(session!=null){
 		    		session.close();
 		    	}
 		    }
		}
		return host_ip;
		
	}
	
	
	public String  testSsh(){
		 String message=null;
		 SshClient ssclient = new SshClient();
         try{
    		 ssclient.connect("10.1.1.3", 22, new IgnoreHostKeyVerification());
    		 PasswordAuthenticationClient pass = new PasswordAuthenticationClient();
    		 pass.setUsername("chong.xu");
    		 pass.setPassword("chong.xuchong.xu");
    		 int result = ssclient.authenticate(pass);
    		 if(result == AuthenticationProtocolState.COMPLETE){
    		    SessionChannelClient session = ssclient.openSessionChannel();
    		    if(session.startShell()){
    		    	OutputStream writer = session.getOutputStream();
//    		    	writer.write("pwd \n".getBytes());
    		    	writer.write("ls -t /data/ef-logs/ing | head -1 \n".getBytes());
//    		    	writer.write("awk -F '\t' \'{print $21}\' /data/ef-logs/ing/1.7.click.stats.log \n".getBytes());
    		    	writer.flush();
    		    	writer.write("exite\n".getBytes());
    		    	writer.flush();
    		    	BufferedReader in = new BufferedReader(new InputStreamReader(session.getInputStream()));
    		    	BufferedReader err = new BufferedReader(new InputStreamReader(session.getStderrInputStream()));
    		    	String line = null;
    		    	while((line = in.readLine())!=null){
    		    		message = line;
    		    		System.out.println("message============="+message);
    		    		return message;
    		    	}  
    		    	while((line = err.readLine())!=null){
    		    		System.out.println(line);
    		    	}
    		    	if(session!=null){
    		    		session.close();
    		    	}
    		    }
    		 }
         }catch(IOException  e){
        	 e.printStackTrace();
         }
         return message;
	}

	public static  void main(String args[]) throws IOException{
		ReadLiunx rr = new ReadLiunx();
		String log = "click";
		String log1 = "impression";
		rr.getClickLogName(log1);
		rr.getUri(log1);
		rr.getHostIp(log1);
		rr.getPyid(log1);
	}
}
