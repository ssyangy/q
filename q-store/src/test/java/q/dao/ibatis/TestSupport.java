/**
 * 
 */
package q.dao.ibatis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author seanlinwang at gmail dot com
 * @date May 18, 2011
 *
 */
public class TestSupport {
	private static ApplicationContext ctx = new FileSystemXmlApplicationContext("/src/test/resources/ddl/spring-*-test.xml");
	
	public static Object getBean(String name) {
		return ctx.getBean(name);
	}
}
