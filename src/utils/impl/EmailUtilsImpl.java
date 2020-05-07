package utils.impl;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtilsImpl {
	//发件人账号
		private static String fromEmail = "2667902230@qq.com";
		//发件人密码
		private static String fromEmailPw = "mbhwvwjlsiiweaie";
		//发件邮箱服务器
		private static String myEmailSMTPHost = "smtp.qq.com";
		//用于参数配置
		private static Properties props;
		//用于创造会话对象
		private static Session session;
		private static String vCode;

		private PrintWriter out;  // 
	public static EmailUtilsImpl instance = new EmailUtilsImpl();
	
	/*
	 * 
	 */
	public String getVCode() {
		return vCode;
	}
	
	/*
	 * 配置属性
	 */
	private EmailUtilsImpl() {
		props = new Properties();
		
		
		props.setProperty("mail.transport.protocol", "smtp");  
        props.setProperty("mail.smtp.host", myEmailSMTPHost);  
        props.setProperty("mail.smtp.auth", "true");  
        session = Session.getInstance(props);
        // session.setDebug(true);  
	}
	
	/*
	 * 邮件信息
	 */
	public MimeMessage createMailContent(String toEmail) throws Exception, MessagingException {
		MimeMessage message = new MimeMessage(session);
		//
		message.setFrom(new InternetAddress(fromEmail, "诈骗中心の验证码发送系统", "UTF-8"));
		//
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
		//邮件主题
		message.setSubject("验证码", "UTF-8");
		//正文部分 
		vCode = VCodeUtilsImpl.verifyCode(6);
		message.setContent("你好，这里是诈骗中心，诈骗验证码是:"+vCode+"。", "text/html;charset=UTF-8");
		//发件时间：现在马上
		message.setSentDate(new Date());
		//保存设置
		message.saveChanges();
		return message;
	}
	/*
	 * 发件
	 */
	public void sendEmail(String toEmail) throws Exception {
		Transport transport = session.getTransport();
		transport.connect(fromEmail, fromEmailPw);
		MimeMessage message = createMailContent(toEmail);
		try {
			transport.sendMessage(message, message.getAllRecipients());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transport.close();
	}
}

