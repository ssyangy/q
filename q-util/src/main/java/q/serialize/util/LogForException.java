package q.serialize.util;


/**
 * 出错时,将入参和出参打印出来
 * 
 * @author jiangyongyuan.tw
 */
public class LogForException {

	/**
	 * processRequest时候报错,输出日志
	 * 
	 * @param method
	 * @param args
	 * @param types
	 */
	public static String logInput(String method, Object[] args, String[] types) {
		StringBuffer bf = new StringBuffer();
		bf.append("invoke method :" + method + "\n");
		int i = 0;
		for (Object o : args) {
			if (types != null && types.length >= i) {
				bf.append("args :" + o + " value=" + o + " type=" + types[i]
						+ "\n");
			} else
				bf.append("args :" + o + " value=" + o + "\n");
		}
		return bf.toString();
	}

	/**
	 * processResponse时候报错,输出日志
	 * 
	 * @param method
	 * @param args
	 * @param types
	 * @param resp
	 */
	public static String logOutput(String method, Object[] args,
			String[] types, Object resp) {
		String str = logInput(method, args, types);
		return str;
	}
}
