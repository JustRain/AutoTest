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
	private String mail_body = "�ʼ�������"; 
	private String personalName = "�Զ�������";  
	private String mail_head_name = "�ʼ�����";  
	private String mail_head_value = "�ʼ�����";
	private String mail_subject = "�ʼ�����";  
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
			Properties props = new Properties();//��ȡϵͳ����
			Authenticator auth = new Email_Autherticator();//�����ʼ��������û���֤
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			Multipart multipart = new MimeMultipart();  
			//����session�����ʼ�����������ͨѶ
			Session session = Session.getDefaultInstance(props, auth);
			MimeMessage message = new MimeMessage(session); 
			message.setSubject(mail_subject); // �����ʼ�����
			message.setText(mail_body);
			
			//����ʼ�����
			MimeMultipart allPart = new MimeMultipart("mixed"); 
			MimeBodyPart attachment01 = createAttachment(filename);  
			allPart.addBodyPart(attachment01); 
			message.setContent(allPart);  
			message.saveChanges();  
			
			message.setHeader(mail_head_name, mail_head_value); // �����ʼ�����  
			Address address = new InternetAddress(mail_from,personalName);
			message.setFrom(address);
			Address toaddress = new InternetAddress(mail_to);
			message.addRecipient(Message.RecipientType.TO, toaddress);
			Transport.send(message);
			System.out.println("���ͳɹ�");
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
