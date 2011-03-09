package q.serialize.config;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 格式化输出工具：不考虑性能因素,将输出的string读取并输出format后的格式
 * @author jiangyongyuan.tw
 */
public class XmlFormater {
	public static String format(String str)
			throws UnsupportedEncodingException, IOException, DocumentException {

		SAXReader reader = new SAXReader();
		// System.out.println(reader);
		// 注释：创建一个串的字符输入流
		StringReader in = new StringReader(str);
		Document doc = reader.read(in);
		// System.out.println(doc.getRootElement());
		// 注释：创建输出格式
		OutputFormat formater = OutputFormat.createPrettyPrint();
		// 注释：设置xml的输出编码
		formater.setEncoding("utf-8");
		// 注释：创建输出(目标)
		StringWriter out = new StringWriter();
		// 注释：创建输出流
		XMLWriter writer = new XMLWriter(out, formater);
		// 注释：输出格式化的串到目标中，执行后。格式化后的串保存在out中。
		writer.write(doc);

		// 注释：返回我们格式化后的结果
		return out.toString();
	}
}
