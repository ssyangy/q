package q.serialize.util;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 * 
 * @version 2009-08-24
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class Utils {

	/**
	 * ================================XML输出所用的常量===============================
	 * .
	 */

/** 常量 LEFT_ANGLE_BRACKET: <code>'<'</code>. */
	public static final Character LEFT_ANGLE_BRACKET = '<';

	/** 常量 RIGHT_ANGLE_BRACKET: <code>'>'</code>. */
	public static final Character RIGHT_ANGLE_BRACKET = '>';

	/** 常量 EQUAL: <code>'='</code>. */
	public static final Character EQUAL = '=';

	/** 常量 BACK_SLASH: <code>'/'</code>. */
	public static final Character BACK_SLASH = '/';

	/** 常量 DOUBLE_QUOTE: <code>"\""</code>. */
	public static final String DOUBLE_QUOTE = "\"";

	/** 常量 SINGEL_QUOTE: <code>"\'"</code>. */
	public static final String SINGEL_QUOTE = "\'";

	/** 常量 SPACE: <code>' '</code>. */
	public static final Character SPACE = ' ';

	/** 常量 LIST: <code>"list=\"true\""</code>. */
	public static final String LIST = "list=\"true\"";

	/** 常量 Character: <code>'</code>. */
	public static final Character APOS = new Character('\'');

	/** 常量 Character: <code>!</code>. */
	public static final Character BANG = new Character('!');

	/** 常量 Character: <code>?</code>. */
	public static final Character QUEST = new Character('?');

	/** 常量 Character: <code>"</code>. */
	public static final Character QUOT = new Character('"');

	/** 常量 Character: <code>&</code>. */
	public static final Character AMP = new Character('&');

	/** =====================================配置输出常量============================. */

	/** 常量 MEMBER: <code></code>. */
	public static final String MEMBER = "member";

	/** 常量 TYPE: <code></code>. */
	public static final String TYPE = "type";

	/** 常量 METHOD: <code></code>. */
	public static final String METHOD = "method";

	/** 常量 PARAM: <code></code>. */
	public static final String PARAM = "param";

	/** 常量 PARAMS: <code></code>. */
	public static final String PARAMS = "params";

	/** 常量 STRUCT: <code></code>. */
	public static final String STRUCT = "struct";

	/** 常量 NAME: <code></code>. */
	public static final String NAME = "name=\"";

	/** 常量 API_NAME: <code></code>. */
	public static final String API_NAME = "apiName=\"";

	/** 常量 ARRAYITEMNAME: <code></code>. */
	public static final String ARRAYITEMNAME = "apiArrayItemName=\"";

	/** 常量 API_TYPE: <code></code>. */
	public static final String API_TYPE = "apiType=\"";

	/** 常量 ARRAY: <code></code>. */
	public static final String ARRAY = "array";

	/** 常量 TYPE_ATTRIBUTE: <code></code>. */
	public static final String TYPE_ATTRIBUTE = "class";

	/** 常量 METHOD_CALL: <code></code>. */
	public static final String METHOD_CALL = "methodCall";

	/** 常量 METHOD_RESPONSE: <code></code>. */
	public static final String METHOD_RESPONSE = "methodResponse";

	/** 常量 SWITCH: <code></code>. */
	public static final String SWITCH = "switch";

	/** 常量 CASE: <code></code>. */
	public static final String CASE = "case";

	/** 常量 DEFAULT: <code></code>. */
	public static final String DEFAULT = "default";

	/** 常量 VALUE: <code></code>. */
	public static final String VALUE = "value=\"";

	/** 常量 API_VALUE: <code></code>. */
	public static final String API_VALUE = "apiValue=\"";

	/** 常量 XMLNS: <code></code>. */
	public static final String XMLNS = "xmlns=\"http://apimapping.taobao.com\"";

	/** 常量 XMLNS_XSI: <code></code>. */
	public static final String XMLNS_XSI = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";

	/** 常量 SCHEMA_LOCATION: <code></code>. */
	public static final String SCHEMA_LOCATION = "xsi:schemaLocation=\"http://apimapping.taobao.com http://10.1.5.104/top/apimapping.xsd\"";

	/** 常量 INTERFACE_NAME: <code></code>. */
	public static final String INTERFACE_NAME = "interfaceName=\"";

	/** 常量 INTERFACE_VERSION: <code></code>. */
	public static final String INTERFACE_VERSION = "interfaceVersion=\"";

	/** 常量 EXCEPTION: <code></code>. */
	public static final String EXCEPTION = "exceptionExpress=\"";
	
	/** ======================================转义字符============================. */

	/** 常量 entity存储转义字符的map */
	public static final java.util.HashMap entity;

	static {
		entity = new java.util.HashMap(8);
		entity.put(AMP, "&amp;");
		entity.put(APOS, "&apos;");
		entity.put(RIGHT_ANGLE_BRACKET, "&gt;");
		entity.put(LEFT_ANGLE_BRACKET, "&lt;");
		entity.put(QUOT, "&quot;");
	}

	/**
	 * =========================================JSON常量==========================
	 * =.
	 */

	/** 常量 LB(left brace):<code>{</code> */
	public static final char LB = '{';

	/** 常量 RB(Right Brace):<code>}</code>. */
	public static final char RB = '}';

	/** 常量 COLON:<code>:</code>. */
	public static final char COLON = ':';

	/** 常量 LSB(Left Square Bracket):<code>[</code>. */
	public static final char LSB = '[';

	/** 常量 RSB(Rigth Square Bracket):<code>]</code>. */
	public static final char RSB = ']';

	/** 常量 COMMA:<code>,</code>. */
	public static final char COMMA = ',';

	/**
	 * ========================================常量==============================
	 * ==.
	 */

	/** 常量 XML_FORMAT: <code>xml</code>. */
	public static final String XML_FORMAT = "xml";

	/** 常量 JSON_FORMAT: <code>json</code>. */
	public static final String JSON_FORMAT = "json";

	/** 常量 Camel_Casing: <code>1</code>. */
	public static final int CAMEL_CASING = 1;

	/** 常量 UNDERLINE_STYLE: <code>2</code>. */
	public static final int UNDERLINE_STYLE = 2;
}
