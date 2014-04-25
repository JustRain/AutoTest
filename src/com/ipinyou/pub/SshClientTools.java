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
import com.sshtools.j2ssh.connection.Channel;
import com.sshtools.j2ssh.session.PseudoTerminal;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.sftp.SftpFile;
import com.sshtools.j2ssh.transport.ConsoleKnownHostsKeyVerification;

public class SshClientTools {
	
	private String hostip = null;
	private String username = null;
	private String password = null;
	private SshClient ssh = null;
	private SessionChannelClient session = null;
	public String getHostip() {
		return hostip;
	}
	public void setHostip(String hostip) {
		this.hostip = hostip;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public SshClientTools(String host,String username,String password) throws Exception{
		if(host==null || host.length()==0)
			throw new Exception("lose the host");
		if(username==null || username.length()==0)
			throw new Exception("lose the loginname");
		if(password==null || password.length()==0)
			throw new Exception("lose the password");
		this.setHostip(host);
		this.setUsername(username);
		this.setPassword(password);
	}
	
	
	private boolean connect() throws Exception{
//		if(ssh == null){
//			throw new Exception("ssclient is null");
//		}	
		PasswordAuthenticationClient authentication = new PasswordAuthenticationClient();
		authentication.setUsername(this.username);
		authentication.setPassword(this.password);
		ConsoleKnownHostsKeyVerification console;
		try{
			console = new ConsoleKnownHostsKeyVerification();
			ssh.connect(this.hostip, 22, console);
			if(ssh.authenticate(authentication) ==  AuthenticationProtocolState.COMPLETE){
				return true;
			}else{
				return false;
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public String executeCommand(String command) throws Exception{
		String result = null;
		ArrayList<String> array_result = new ArrayList<String>();
//		if(ssh == null){
//			throw new Exception("ssclient is null");
//		}	
//		if(!ssh.isConnected()){
//			this.connect();
//		}
		session = ssh.openSessionChannel();
		if(session.requestPseudoTerminal("dump",PseudoTerminal.ECHO, 0, 0, 0, null)){
			if(session.startShell()){
				OutputStream writer = session.getOutputStream();
				writer.write(command.getBytes());
				writer.write("\n".getBytes());
				writer.flush();
				writer.write("exit\n".getBytes());
				writer.flush();
				BufferedReader in = new BufferedReader(new InputStreamReader(session.getInputStream()));
				BufferedReader err = new BufferedReader(new InputStreamReader(session.getStderrInputStream()));
				String line = null;
				while((line=in.readLine())!=null){
					System.out.println(line);
					array_result.add(line);
				}
			if(array_result.size()!=0){
				return result = array_result.toString();
			}

			}
		}
	     
		
		return result;
	}
	
	public static void main(String args[]) throws Exception{		
		SshClientTools ss= new SshClientTools("10.1.1.3", "chong.xu", "chong.xuchong.xu");
		System.out.println(ss.executeCommand("ll"));
	}
	
}
