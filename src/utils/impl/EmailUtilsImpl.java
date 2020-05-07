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
	//�������˺�
		private static String fromEmail = "2667902230@qq.com";
		//����������
		private static String fromEmailPw = "mbhwvwjlsiiweaie";
		//�������������
		private static String myEmailSMTPHost = "smtp.qq.com";
		//���ڲ�������
		private static Properties props;
		//���ڴ���Ự����
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
	 * ��������
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
	 * �ʼ���Ϣ
	 */
	public MimeMessage createMailContent(String toEmail) throws Exception, MessagingException {
		MimeMessage message = new MimeMessage(session);
		//
		message.setFrom(new InternetAddress(fromEmail, "թƭ���Ĥ���֤�뷢��ϵͳ", "UTF-8"));
		//
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toEmail));
		//�ʼ����⢘
		message.setSubject("��֤��", "UTF-8");
		//���Ĳ��� 
		vCode = VCodeUtilsImpl.verifyCode(6);
		message.setContent("��ã�������թƭ���ģ�թƭ��֤����:"+vCode+"��", "text/html;charset=UTF-8");
		//����ʱ�䣺��������
		message.setSentDate(new Date());
		//��������
		message.saveChanges();
		return message;
	}
	/*
	 * ����
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

