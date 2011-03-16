/**
 * 
 */
package q.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * People education degree.
 * 
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 16, 2011
 * 
 */
public enum Degree {
	XIAOXUE(9, "小学"), CHUZHONG(10, "初中"), ZHONGZHUAN(11, "中专"), GAOZHONG(12, "高中"), DAZHUAN(13, "大专"), BENKE(14, "本科"), YANJIUSHENG(15, "研究生"), BOSHI(16, "博士");

	private String name;
	private int value;

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	private Degree(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static Degree convertValue(int value) {
		if (value == XIAOXUE.value) {
			return XIAOXUE;
		} else if (value == CHUZHONG.value) {
			return CHUZHONG;
		} else if (value == ZHONGZHUAN.value) {
			return ZHONGZHUAN;
		} else if (value == GAOZHONG.value) {
			return GAOZHONG;
		} else if (value == DAZHUAN.value) {
			return DAZHUAN;
		} else if (value == BENKE.value) {
			return BENKE;
		} else if (value == YANJIUSHENG.value) {
			return YANJIUSHENG;
		} else if (value == BOSHI.value) {
			return BOSHI;
		} else {
			return null;
		}
	}

	/**
	 * @param value
	 * @return
	 */
	public static boolean valid(int value) {
		return null == convertValue(value) ? false : true;
	}

	private final static List<Degree> degrees = new ArrayList<Degree>();
	static {
		degrees.add(XIAOXUE);
		degrees.add(CHUZHONG);
		degrees.add(ZHONGZHUAN);
		degrees.add(GAOZHONG);
		degrees.add(DAZHUAN);
		degrees.add(BENKE);
		degrees.add(YANJIUSHENG);
		degrees.add(BOSHI);
	}

	/**
	 * @return
	 */
	public static List<Degree> getAll() {
		return degrees;
	}

}
