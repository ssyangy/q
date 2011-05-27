/**
 * 
 */
package q.jsp.tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Omit string too long.
 * 
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 11, 2011
 * 
 */
public class OmitTag extends SimpleTagSupport {
	private int maxLength;

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	private String omitPostfix = "...";
	
	public void setOmitPostfix(String omitPostfix) {
		this.omitPostfix = omitPostfix;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspFragment body = getJspBody();
		if (body == null) {
			return;
		}
		StringWriter writer = new StringWriter();
		body.invoke(writer);
		String target = writer.toString();
		if (target != null && target.length() > maxLength) {
			target = target.substring(0, maxLength);
			target += omitPostfix;
		}
		this.getJspContext().getOut().write(target);
	}
}
