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
public class AgeTag extends SimpleTagSupport {
	private int year;
	
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public void doTag() throws JspException, IOException {
		String age = null;
		if (year < 1940 ) {
			age = "40前";
		} else if (year < 1950) {
			age = "40后";
		} else if (year < 1960) {
			age = "50后";
		} else if (year < 1970) {
			age = "60后";
		} else if (year < 1980) {
			age = "70后";
		} else if (year < 1985) {
			age = "80后";
		} else if (year < 1990 ) {
			age = "85后";
		} else if (year < 1995) {
			age = "90后";
		} else if (year < 2000) {
			age = "95后";
		} else {
			age = "00后";
		} 
		this.getJspContext().getOut().write(age);
	}
}
