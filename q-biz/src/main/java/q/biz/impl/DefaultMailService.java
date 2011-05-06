/**
 * 
 */
package q.biz.impl;

import javax.mail.MessagingException;

import q.biz.MailService;
import q.commons.mail.MailInfo;
import q.commons.mail.MailSender;
import q.log.Logger;

/**
 * @author seanlinwang at gmail dot com
 * @date May 6, 2011
 * 
 */
public class DefaultMailService implements MailService {
	private static final Logger log = Logger.getLogger();

	private static final String DEFAULT_FROM_ADDRESS = "noreply@q.com.cn";

	private String smtpHost;

	private int smtpPort;

	private String username;

	private String password;

	private String fromAddress = DEFAULT_FROM_ADDRESS;

	private boolean smtpSsl;

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public void setSmtpSsl(boolean smtpSsl) {
		this.smtpSsl = smtpSsl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.biz.MailService#sendPasswordResetEmail(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendEmail(final String toAddress, final String subject, final String content) throws MessagingException {
		new Thread(new Runnable() {

			@Override
			public void run() {
				MailInfo mailInfo = new MailInfo();
				mailInfo.setMailServerHost(smtpHost);
				mailInfo.setMailServerPort(smtpPort);
				mailInfo.setUserName(username);
				mailInfo.setPassword(password);
				mailInfo.setFromAddress(fromAddress);
				mailInfo.setSsl(smtpSsl);
				mailInfo.setValidate(true);
				mailInfo.setToAddress(toAddress);
				mailInfo.setSubject(subject);
				mailInfo.setContent(content);
				try {
					MailSender.sendHtmlMail(mailInfo);
				} catch (MessagingException e) {
					log.error("", e);
				}
			}

		}).start();
	}

}
