package q.commons.mail;

import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
*
*/
@SuppressWarnings("restriction")
public class MailSender {
	{
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	}

	/**
	 * 
	 * @param mailInfo
	 * @throws MessagingException
	 */
	public void sendTextMail(MailInfo mailInfo) throws MessagingException {

		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {

			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}

		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		Message mailMessage = new MimeMessage(sendMailSession);

		Address from = new InternetAddress(mailInfo.getFromAddress());

		mailMessage.setFrom(from);

		Address to = new InternetAddress(mailInfo.getToAddress());
		mailMessage.setRecipient(Message.RecipientType.TO, to);

		mailMessage.setSubject(mailInfo.getSubject());

		mailMessage.setSentDate(new Date());

		String mailContent = mailInfo.getContent();
		mailMessage.setText(mailContent);

		Transport.send(mailMessage);
	}

	/**
	 * 
	 * @param mailInfo
	 * @throws MessagingException
	 */
	public static void sendHtmlMail(MailInfo mailInfo) throws MessagingException {

		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 锟斤拷锟斤拷锟揭拷锟斤拷锟斤拷证锟斤拷锟津创斤拷一锟斤拷锟斤拷锟斤拷锟斤拷证锟斤拷
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}

		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);

		Message mailMessage = new MimeMessage(sendMailSession);

		Address from = new InternetAddress(mailInfo.getFromAddress());

		mailMessage.setFrom(from);

		Address to = new InternetAddress(mailInfo.getToAddress());

		mailMessage.setRecipient(Message.RecipientType.TO, to);

		mailMessage.setSubject(mailInfo.getSubject());

		mailMessage.setSentDate(new Date());

		Multipart mainPart = new MimeMultipart();

		BodyPart html = new MimeBodyPart();

		html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
		mainPart.addBodyPart(html);

		mailMessage.setContent(mainPart);

		Transport.send(mailMessage);
	}
	
	public static void main(String[] args) throws MessagingException {
		MailInfo mailInfo = new MailInfo();
		mailInfo.setMailServerHost("smtp.gmail.com");
		mailInfo.setMailServerPort(465);
		mailInfo.setValidate(true);
		mailInfo.setSsl(true);
		mailInfo.setUserName("qcomcn.noreply@gmail.com");
		mailInfo.setPassword("qcomcnzhangjiangjifang");
		mailInfo.setFromAddress("qcomcn.noreply@gmail.com");
		mailInfo.setToAddress("xalinx@gmail.com");
		mailInfo.setSubject("测试");
		mailInfo.setContent("来自iceball1@163.com的邮件,密码为123456<img id='authimg'  src='http://www.baidu.com/img/baidu_sylogo1.gif' />");
		// sms.sendTextMail(mailInfo);
		MailSender.sendHtmlMail(mailInfo);
	}
}
