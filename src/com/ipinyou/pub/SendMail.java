package com.ipinyou.pub;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	private String host = "smtp.qiye.163.com";
	private String username = "chong.xu@ipinyou.com";
	private String password = "xc1989";
	private String mail_to = "chong.xu@ipinyou.com";
	private String mail_from = "chong.xu@ipinyou.com";
	private String mail_body = "邮件的内容"; 
	private String personalName = "自动化测试";  
	private String mail_head_name = "邮件标题";  
	private String mail_head_value = "邮件标题";
	private String mail_subject = "邮件标题";  
	private String filename="E:\\Eclipse workspace\\IpinyouSele\\test-output\\emailable-report.html"; 
	
	
	public SendMail(){
		
	}
	  public MimeBodyPart createAttachment(String fileName) throws Exception {  
	        MimeBodyPart attachmentPart = new MimeBodyPart();  
	        FileDataSource fds = new FileDataSource(fileName);  
	        attachmentPart.setDataHandler(new DataHandler(fds));  
	        attachmentPart.setFileName(fds.getName());  
	        return attachmentPart;  
	    } 
	
	public void send (){
		try{
			Properties props = new Properties();//获取系统配置
			Authenticator auth = new Email_Autherticator();//进行邮件服务器用户认证
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			Multipart multipart = new MimeMultipart();  
			//设置session，和邮件服务器进行通讯
			Session session = Session.getDefaultInstance(props, auth);
			MimeMessage message = new MimeMessage(session); 
			message.setSubject(mail_subject); // 设置邮件主题
			message.setText(mail_body);
			
			//添加邮件附件
			MimeMultipart allPart = new MimeMultipart("mixed"); 
			MimeBodyPart attachment01 = createAttachment(filename);  
			allPart.addBodyPart(attachment01); 
			message.setContent(allPart);  
			message.saveChanges();  
			
			message.setHeader(mail_head_name, mail_head_value); // 设置邮件标题  
			Address address = new InternetAddress(mail_from,personalName);
			message.setFrom(address);
			Address toaddress = new InternetAddress(mail_to);
			message.addRecipient(Message.RecipientType.TO, toaddress);
			Transport.send(message);
			System.out.println("发送成功");
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	public class Email_Autherticator extends Authenticator{
		public Email_Autherticator(){
			super();
		}
		public Email_Autherticator(String user,String pwd){
			super();
			username = user;
			password = pwd;
		}
		public PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(username,password);
			
		}
	}
	
	public static void main(String args[]){
		SendMail sendmail = new SendMail();
		try{
			sendmail.send();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
