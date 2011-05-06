/**
 * 
 */
package q.biz;

import javax.mail.MessagingException;

/**
 * @author seanlinwang at gmail dot com
 * @date May 6, 2011
 * 
 */
public interface MailService {

	void sendEmail(String toAddress, String subject, String content) throws MessagingException;

}
