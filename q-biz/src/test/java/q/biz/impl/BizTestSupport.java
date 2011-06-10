/**
 * 
 */
package q.biz.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author seanlinwang at gmail dot com
 * @date Jun 2, 2011
 *
 */
public class BizTestSupport {
private static ApplicationContext ctx = new FileSystemXmlApplicationContext("/src/test/resources/spring-*-test.xml");
	
	public static Object getBean(String name) {
		return ctx.getBean(name);
	}
}
