package com.ecarinfo.weichexin.httpserver.utils;

import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
/**
 * 发送邮件任务
 * @author Administrator
 *
 */
public class MailTask{
	
	private static final Logger logger = Logger.getLogger(MailTask.class);
	private JavaMailSenderImpl sender;
	private String text;
	private String emailAddress;
	private String ccEmail;
	private String title;
	public MailTask(JavaMailSenderImpl sender,String text, String emailAddress,String ccEmail,String title) {
		this.sender = sender;
		this.text = text;
		this.emailAddress = emailAddress;
		this.ccEmail = ccEmail;
		this.title = title;
	}
	
	public void send() {
		sendReportToUser(text,emailAddress,ccEmail,title);
	}
	
	public void sendReportToUser(String text, String emailAddress,String ccEamil,String title){
		try {
			
			logger.info("=========================emailAddress"+emailAddress);
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setSubject(title);
			helper.setFrom(sender.getUsername());
			if(ccEamil != null) {
				helper.setTo(emailAddress);
				helper.setBcc(ccEamil);
			} else {
				helper.setTo(emailAddress);
			}
			
			helper.setText(text, true);
			sender.send(message);
			logger.info(title+","+emailAddress);
		} catch(SendFailedException e){
			String info = String.format("Send Email to %s error, reason is ", emailAddress,e.getMessage());
			logger.error(info);
		} catch (Exception e) {
			logger.error("Exception:"+e.getMessage());
		}
	}
}