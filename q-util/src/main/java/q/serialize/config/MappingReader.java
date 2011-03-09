package q.serialize.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import q.serialize.util.Utils;
import q.util.StringKit;


/**
 * MappingReader Class用于存储ClassReader和ConfigReader中一些公用的变量和函数.
 * 
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class MappingReader {


	/** The Constant log. */
	protected static transient final Log log = LogFactory
			.getLog(MappingConfigReader.class);

	/** The Constant DEFAULT_CONFIG_ENCODE. */
	protected static final String DEFAULT_CONFIG_ENCODE = "UTF-8";


	/** The Member call. */
	public static int memberCall = 1;

	/** The Object call. */
	public static int objectCall = 2;

	/** The style. */
	protected int style;

	/**
	 * 构造一个新的MappingReader 默认没有风格，即按所读出的命名进行命名，不做转换.
	 */
	public MappingReader() {
	}

	/**
	 * 构造一个新的MappingClassReader 指定命名风格，现在支持驼峰风格和下划线风格，
	 * 分别为Utils.CAMEL_CASING,Utils.UNDERLINE_STYLE
	 * 
	 * @param style
	 *            命名风格
	 */
	public MappingReader(int style) {
		this.style = style;
	}

	/**
	 * 根据制定的风格类型（style）来生成对应的配置.
	 * 
	 * @param string
	 *            the string
	 * 
	 * @return the string
	 */
	protected String getString(String string) {
		if (this.style == Utils.CAMEL_CASING) {
			return StringKit.trim(StringKit.toCamelCase(string));
		} else if (this.style == Utils.UNDERLINE_STYLE) {
			return StringKit.trim(StringKit
					.toLowerCaseWithUnderscores(string));
		} else {
			return StringKit.trim(string);
		}
	}
}
