/**
 *
 */
package q.commons.http;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.commons.http.UrlKit;
import q.util.Replace;

/**
 * @author lin.wangl
 *
 */
public class UrlKitTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link q.commons.http.google.code.smallcrab.utils.UrlKit#extractParameterValue(java.lang.String, java.lang.String)} .
	 */
	@Test
	public void testExtractParameterValue() {
		assertEquals("13006624", UrlKit.extractParameterValue("appkey=13006624&tracelogww=wwltygmc", "appkey"));
		assertEquals(null, UrlKit.extractParameterValue("tracelogww=wwltygmc", "appkey"));
		assertEquals("13006624", UrlKit.extractParameterValue("&tracelogww=wwltygmc&appkey=13006624", "appkey"));
	}

	@Test
	public void testGetParameterNameWitchValueStartsWith() {
		assertEquals("name", UrlKit.getParameterNameWitchValueStartsWith("abc=111&344=5666&appkey=13006624&name=http%3A%2F%2F&a=xxx", "http"));
		assertEquals("returnurl", UrlKit.getParameterNameWitchValueStartsWith("appkey=12055128&invitor=350312108&returnurl=http%3A%2F%2Fapp.taobao.jrj.com.cn%2Finvest%2Fgolden.jsp", "http"));
		assertEquals("appkey", UrlKit.getParameterNameWitchValueStartsWith("appkey=13006624&name=http%3A%2F%2F", "1300"));
	}

	@Test
	public void testExtractPath() {
		assertEquals("/", UrlKit.extractPath("http://www.taobao.com/"));
		assertEquals("/", UrlKit.extractPath("http://www.taobao.com/?"));
		assertEquals("/abc/df.jsp", UrlKit.extractPath("http://www.taobao.com/abc/df.jsp?a=1"));
		assertEquals("/topcontainer/status.taobao\"", UrlKit.extractPath("http://172.24.37.142/topcontainer/status.taobao\""));
	}

	@Test
	public void testExtractDomainAndPath() {
		assertEquals("http://www.taobao.com", UrlKit.extractDomainAndPath("http://www.taobao.com"));
		assertEquals("http://www.taobao.com/", UrlKit.extractDomainAndPath("http://www.taobao.com/?"));
		assertEquals("http://www.taobao.com/abc/df.jsp", UrlKit.extractDomainAndPath("http://www.taobao.com/abc/df.jsp?a=1"));
		assertEquals("http://172.24.37.142/topcontainer/status.taobao\"", UrlKit.extractDomainAndPath("http://172.24.37.142/topcontainer/status.taobao\""));
	}

	@Test
	public void testExtractDomain() {
		assertEquals("http://www.taobao.com", UrlKit.extractDomain("http://www.taobao.com"));
		assertEquals("http://www.taobao.com", UrlKit.extractDomain("http://www.taobao.com/?"));
		assertEquals("http://www.taobao.com", UrlKit.extractDomain("http://www.taobao.com/abc/df.jsp?a=1"));
		assertEquals("http://172.24.37.142", UrlKit.extractDomain("http://172.24.37.142/topcontainer/status.taobao\""));
	}

	@Test
	public void testExtractQuery() {
		assertEquals("appkey=12021850&tracelog=jhleftmenu", UrlKit.extractQuery("http://tt.com/t/con?appkey=12021850&tracelog=jhleftmenu"));
	}

	@Test
	public void testFindQut() {
		assertEquals(12, UrlKit.findQut(0, "http://a/\\\"b\""));
		assertEquals(0, UrlKit.findQut(0, "\""));
		assertEquals(2, UrlKit.findQut(0, "\\\"\"a"));
	}

	@Test
	public void testGetMapFromQuery() throws UnsupportedEncodingException {
		Map<String, String> map = UrlKit.getParameterMapFromQuery("sign_method=md5&sid=abc", "utf-8");
		assertEquals("md5", map.get("sign_method"));
		assertEquals("abc", map.get("sid"));
	}

	@Test
	public void testReplaceUrl() {
		Replace replace = new Replace(){

			@Override
			public String replace(String lurl) {
				return "bb";
			}
		};
		assertEquals("aabb cc", UrlKit.replaceUrl("aahttp://www.google.com cc", replace));
		assertEquals("bb cc", UrlKit.replaceUrl("http://www.google.com cc", replace));
		assertEquals("aabb", UrlKit.replaceUrl("aahttp://www.google.com", replace));
		assertEquals("aabb ddbb中文", UrlKit.replaceUrl("aahttp://www.google.com/?a=b&c=d ddhttp://www.google.com?a=中文", replace));

	}
}
