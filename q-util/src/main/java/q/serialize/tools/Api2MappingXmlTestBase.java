package q.serialize.tools;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * mapping文件单元测试基类
 * 
 * @author jeck218@gmail.com 2009-12-12
 */
public class Api2MappingXmlTestBase {

	protected boolean checkMappingXml(String xmlPath, String xmlName, String fileds, Class<?> beanClass) {
		return checkMappingXml(xmlPath, xmlName, fileds, beanClass, null);
	}
	
	protected boolean checkMappingXml(String xmlPath, String xmlName, String fileds, Class<?> beanClass, String apiBeanName) {
		boolean isRight = true;
		Document xmlDoc = parseDocument(this.getClass().getResourceAsStream(xmlPath + xmlName));
		Element root = xmlDoc.getDocumentElement();

		Element callElement = getChildElement(root, "methodCall");
		if (callElement == null) {
			System.out.println("Miss node: methodCall");
			isRight = false;
			return isRight;
			
		}

		NamedNodeMap callAttrs = callElement.getAttributes();
		Node nameItem = callAttrs.getNamedItem("name");
		Node apiNameItem = callAttrs.getNamedItem("apiName");

		if (nameItem == null) {
			System.out.println("Miss node : methodCall --> name");
			isRight = false;
		}

		if (apiNameItem == null) {
			System.out.println("Miss node : methodCall --> apiName");
			isRight = false;
		} else {
			if (!apiNameItem.getNodeValue().equals(
					xmlName.substring(0, xmlName.indexOf("-mapping.xml")))) {
				System.out.println("Error node: methodCall --> apiName=\"" + apiNameItem.getNodeValue() + "\"");
				isRight = false;
			}
		}

		Element rspElement = getChildElement(root, "methodResponse");
		if (rspElement == null) {
			System.out.println("Miss node : methodResponse");
			isRight = false;
			return isRight;
		}

		if (apiBeanName == null || "".equals(apiBeanName.trim()))
			apiBeanName = hump2UnderLine(beanClass.getSimpleName());
		Element beanNode = null;

		NodeList members = rspElement.getElementsByTagName("member");
		for (int i = 0; i < members.getLength(); i++) {
			Element item = (Element) members.item(i);
			if (apiBeanName.equals(item.getAttribute("apiName"))) {
				beanNode = item;
				break;
			}
		}

		NodeList arrays = rspElement.getElementsByTagName("array");
		if (beanNode == null) {
			for (int i = 0; i < arrays.getLength(); i++) {
				Element item = (Element) arrays.item(i);
				if (apiBeanName.equals(item.getAttribute("apiArrayItemName"))) {
					beanNode = item;
					break;
				}
			}
		}

		if (beanNode == null) {
			System.out.println("Miss node : methodResponse --> No response bean: " + apiBeanName);
			return isRight = false;
		}

		beanNode = getChildElement(beanNode, "type");
		beanNode = getChildElement(beanNode, "struct");
		NodeList childNodes = beanNode.getChildNodes();

		String[] split = fileds.split(",", -1);
		List<String> filedsHave = new ArrayList<String>();
		for (String s : split)
			filedsHave.add(s.trim());

		HashMap<String, String> parasMap = getBeanMap(beanClass);
		List<String> xmlHave = new ArrayList<String>();
		Map<String, String> xmlType = new HashMap<String, String>();
		List<String> printWrongAttr = new ArrayList<String>();

		for (int i = 0; i < childNodes.getLength(); i++) {
			Node e = childNodes.item(i);
			if ("member".equals(e.getNodeName())) {
				NamedNodeMap attrs = e.getAttributes();
				String wikiAttri = attrs.getNamedItem("apiName").getNodeValue();
				String domainAttri = attrs.getNamedItem("name").getNodeValue();
				Node domainType = attrs.getNamedItem("apiType");

				xmlHave.add(wikiAttri);

				if (!wikiAttri.equals(hump2UnderLine(domainAttri))) {
					isRight = false;
					printWrongAttr.add(domainAttri + " --> " + wikiAttri);
				}

				if (domainType != null) {// ignore no apiType
					xmlType.put(domainAttri, domainType.getNodeValue());
				}
			}
		}

		if (!printWrongAttr.isEmpty()) {
			System.out.println("------------- Wrong Attr -----------");
			for (String s : printWrongAttr) {
				System.out.println(s);
			}
			System.out.println("------------- Attr End -------------");
			System.out.println();
		}

		List<String> printXmlLack = new ArrayList<String>();
		for (String f : filedsHave) {
			if (!xmlHave.contains(f.trim())) {
				isRight = false;
				printXmlLack.add(f);
			}
		}

		if (!printXmlLack.isEmpty()) {
			System.out.println("------------- Xml Lack -------------");
			for (String s : printXmlLack) {
				System.out.println(s);
			}
			System.out.println("------------- Lack End -------------");
			System.out.println();
		}

		List<String> printXmlMore = new ArrayList<String>();
		for (String x : xmlHave) {
			if (!filedsHave.contains(x)) {
				isRight = false;
				printXmlMore.add(x);
			}
		}

		if (!printXmlMore.isEmpty()) {
			System.out.println("------------- Xml More -------------");
			for (String s : printXmlMore) {
				System.out.println(s);
			}
			System.out.println("------------- More End -------------");
			System.out.println();
		}

		List<String> printWrongType = new ArrayList<String>();
		if (parasMap != null) {
			Iterator<String> it = xmlType.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = xmlType.get(key);
				String parasValue = parasMap.get(key);
				if (parasValue != null && !parasValue.equals(value)) {
					isRight = false;
					printWrongType.add(key + ": " + value + " --> "
							+ parasValue);
				}
			}
		}

		if (!printWrongType.isEmpty()) {
			System.out.println("------------- Wrong Type -----------");
			for (String s : printWrongType) {
				System.out.println(s);
			}
			System.out.println("------------- Type End -------------");
			System.out.println();
		}

		return isRight;
	}

	private Document parseDocument(InputStream in) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			return db.parse(in);
		} catch (ParserConfigurationException ex) {
			throw new RuntimeException(ex);
		} catch (SAXException ex) {
			throw new RuntimeException(ex);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	private Element getChildElement(Element element, String tag) {
		NodeList nodes = element.getChildNodes();

		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node instanceof Element) {
				Element it = (Element) node;
				if (it.getTagName().equals(tag)) {
					return it;
				}
			}
		}

		return null;
	}

	protected HashMap<String, String> getBeanMap(Class<?> beanClass) {

		try {
			HashMap<String, String> parasMap = new HashMap<String, String>();
			PropertyDescriptor[] pds = Introspector.getBeanInfo(beanClass)
			.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				String name = pd.getName();
				Class<?> type = pd.getPropertyType();
				if (type.equals(Integer.TYPE) || type.equals(Integer.class))
					parasMap.put(name, Integer.class.getName());
				else if (type.equals(Long.TYPE) || type.equals(Long.class))
					parasMap.put(name, Long.class.getName());
				else if (type.equals(Float.TYPE) || type.equals(Float.class))
					parasMap.put(name, "string");
				else if (type.equals(Double.TYPE) || type.equals(Double.class))
					parasMap.put(name, "string");
				else if (type.equals(Character.TYPE) || type.equals(Character.class))
					parasMap.put(name, Character.class.getName());
				else if (type.equals(Boolean.TYPE) || type.equals(Boolean.class))
					parasMap.put(name, Boolean.class.getName());
				else if (type.equals(String.class) || type.equals(Date.class))
					parasMap.put(name, "string");
			}

			return parasMap;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private String hump2UnderLine(String source) {
		if (source == null)
			return null;

		if (source.trim().equals("is3D"))// 特殊
			return "is_3D";

		StringBuilder builder = new StringBuilder();
		char[] chars = source.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c >= 65 && c <= 90) {
				if (i != 0)
					builder.append("_");
				builder.append((char) (c + 32));
			} else {
				builder.append(c);
			}
		}

		return builder.toString();
	}
}
