package q.serialize.mapping;

import java.sql.Date;

import q.util.DateKit;

/**
 * 输出的辅助类
 * @author jiangyongyuan.tw
 */
public class OutputHelper {

	/** 如果是Date类型,修改为输出格式	 */
	public static Object changeValue(Object obj){
		/** 如果是Date类型,修改为输出格式	 */
		if(obj instanceof Date){
			return DateKit.date2Ymdhms((Date)obj);
		}
		return obj;
	}
}
