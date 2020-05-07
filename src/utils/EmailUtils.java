package utils;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public interface EmailUtils {
	public MimeMessage createMailContent(String toEmail) throws UnsupportedEncodingException, MessagingException;
	public void sendEmail(String toEmail) throws MessagingException;
}
