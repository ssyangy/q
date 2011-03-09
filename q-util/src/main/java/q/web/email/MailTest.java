package q.web.email;
public class MailTest {
	public static void main(String[] args) {
	      MailSenderInfo mailInfo = new MailSenderInfo();
	      mailInfo.setMailServerHost("smtp.163.com");
	      mailInfo.setMailServerPort("25");
	      mailInfo.setValidate(true);
	      mailInfo.setUserName("iceball1@163.com");
	      mailInfo.setPassword("123456");
	      mailInfo.setFromAddress("iceball1@163.com");
	      mailInfo.setToAddress("iceball1@163.com");
	      mailInfo.setSubject("测试");
	      mailInfo.setContent("来自iceball1@163.com的邮件,密码为123456<img id='authimg'  src='http://www.baidu.com/img/baidu_sylogo1.gif' />");
	      SimpleMailSender sms = new SimpleMailSender();
	       //   sms.sendTextMail(mailInfo);
	          sms.sendHtmlMail(mailInfo);
	}
}
