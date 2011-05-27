/**
 * 
 */
package q.jsp.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Change birthday to star sign.
 * 
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 11, 2011
 * 
 */
public class StarSignTag extends SimpleTagSupport {
	private int month;

	private int day;

	public void setMonth(int month) {
		this.month = month;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public void doTag() throws JspException, IOException {
		String starSign = "星座不详";
		if (month == 1 && day >= 21 || month == 2 && day <= 19) {
			starSign = "水瓶座";
		} else if (month == 2 && day >= 20 || month == 3 && day <= 20) {
			starSign = "双鱼座";
		} else if (month == 3 && day >= 21 || month == 4 && day <= 20) {
			starSign = "白羊座";
		} else if (month == 4 && day >= 21 || month == 5 && day <= 21) {
			starSign = "金牛座";
		} else if (month == 5 && day >= 22 || month == 6 && day <= 21) {
			starSign = "双子座";
		} else if (month == 6 && day >= 22 || month == 7 && day <= 22) {
			starSign = "巨蟹座";
		} else if (month == 7 && day >= 23 || month == 8 && day <= 23) {
			starSign = "狮子座";
		} else if (month == 8 && day >= 24 || month == 9 && day <= 23) {
			starSign = "处女座";
		} else if (month == 9 && day >= 24 || month == 10 && day <= 23) {
			starSign = "天秤座";
		} else if (month == 10 && day >= 24 || month == 11 && day <= 22) {
			starSign = "天蝎座";
		} else if (month == 11 && day >= 23 || month == 12 && day <= 21) {
			starSign = "射手座";
		} else if (month == 12 && day >= 22 || month == 1 && day <= 20) {
			starSign = "魔羯座";
		}
		this.getJspContext().getOut().write(starSign);
	}
}
