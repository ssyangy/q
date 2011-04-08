/**
 * 
 */
package q.commons.pinyin;

import java.util.HashSet;
import java.util.Set;

import q.util.StringKit;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author seanlinwang at gmail dot com
 * @date Apr 8, 2011
 * 
 */
public class Pinyin {
	// 汉语拼音格式输出类
	private static final HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();

	static {
		// 输出设置，大小写，音标方式等
		hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
	}

	/**
	 * 获取拼音集合 FIXME need performance tunning
	 * 
	 * @author seanlinwang
	 * @param src
	 * @return Set<String>
	 */
	public static Set<String> getPinyin(String src) {
		if (StringKit.isEmpty(src)) {
			return null;
		}
		char[] srcChar = src.toCharArray();

		String[][] temp = new String[src.length()][];
		for (int i = 0; i < srcChar.length; i++) {
			char c = srcChar[i];
			// 是中文或者a-z或者A-Z转换拼音(我的需求，是保留中文或者a-z或者A-Z)
			if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
				try {
					temp[i] = PinyinHelper.toHanyuPinyinStringArray(srcChar[i], hanYuPinOutputFormat);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else if (((int) c >= 65 && (int) c <= 90) || ((int) c >= 97 && (int) c <= 122)) {
				temp[i] = new String[] { String.valueOf(srcChar[i]) };
			} else {
				temp[i] = new String[] { "" };
			}
		}
		String[] pingyinArray = Exchange(temp);
		Set<String> pinyinSet = new HashSet<String>();
		for (int i = 0; i < pingyinArray.length; i++) {
			pinyinSet.add(pingyinArray[i]);
		}
		return pinyinSet;
	}

	/**
	 * 递归
	 * 
	 * @author wyh
	 * @param strJaggedArray
	 * @return
	 */
	private static String[] Exchange(String[][] strJaggedArray) {
		String[][] temp = DoExchange(strJaggedArray);
		return temp[0];
	}

	/**
	 * 递归
	 * 
	 * @author wyh
	 * @param strJaggedArray
	 * @return
	 */
	private static String[][] DoExchange(String[][] strJaggedArray) {
		int len = strJaggedArray.length;
		if (len >= 2) {
			int len1 = strJaggedArray[0].length;
			int len2 = strJaggedArray[1].length;
			int newlen = len1 * len2;
			String[] temp = new String[newlen];
			int Index = 0;
			for (int i = 0; i < len1; i++) {
				for (int j = 0; j < len2; j++) {
					temp[Index] = strJaggedArray[0][i] + strJaggedArray[1][j];
					Index++;
				}
			}
			String[][] newArray = new String[len - 1][];
			for (int i = 2; i < len; i++) {
				newArray[i - 1] = strJaggedArray[i];
			}
			newArray[0] = temp;
			return DoExchange(newArray);
		} else {
			return strJaggedArray;
		}
	}

}
