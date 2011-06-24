/**
 *
 */
package q.commons.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * JDK实现的UrlFetch
 * 
 * 在恶劣情况下，如果请求过于频繁（如每秒数百次）且单次请求耗时较长， 有可能导致客户端连接来不及释放，耗尽计算机端口资源。在这种环境 下，推荐使用 <code>com.taobao.api.HttpClientUrlFetch</code>
 * 
 * 
 */

public class JdkHttpClient {
	private static final String requestMethod = "POST";

	private static final String CRLF = "\r\n";
	private static final String PREF = "--";
	private static final int UPLOAD_BUFFER_SIZE = 10240;

	/**
	 * 获得HttpUrlConnection链接
	 * 
	 * @return
	 * @throws IOException
	 */
	public static HttpURLConnection getHttpConnection(URL url, int readTimeOut, int connectTimeOut) throws IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setReadTimeout(readTimeOut);
		con.setConnectTimeout(connectTimeOut);
		return con;
	}

	public static BufferedReader getSearch(HttpURLConnection connection) {
		BufferedReader br;
		try {
			connection.connect();
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (Exception e) {
			return null;
		}
		return br;
	}

	/**
	 * 释放HttpUrlConnection链接
	 * 
	 * @param connection
	 * @throws IOException
	 */

	public static void releaseUrlConnection(HttpURLConnection connection) throws IOException {
		connection.getInputStream().close();
		connection.disconnect();
		connection = null;
	}

	public static String post(HttpURLConnection connection, Map<String, CharSequence> params) throws IOException {
		String buffer = FetchUtil.paramsToBuffer(params.entrySet(), "&", "=");

		if (buffer == null) {
			buffer = "";
		}

		connection.setRequestMethod(requestMethod);
		connection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.getOutputStream().write(buffer.getBytes());
		String body = FetchUtil.inputStreamToString(connection.getInputStream());
		return body;
	}

	public static String postString(HttpURLConnection connection, String data) throws IOException {
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.addRequestProperty("Content-Type", "text/xml");
		connection.addRequestProperty("charset", "UTF-8");
		OutputStream out = null;
		try {
			out = connection.getOutputStream();
			out.write(data.getBytes());
			out.flush();
		} finally {
			if (out != null) {
				out.close();
			}

		}
		String body = FetchUtil.inputStreamToString(connection.getInputStream());
		return body;

	}

	public static InputStream getMultipart(HttpURLConnection connection) throws IOException {
		InputStream data = connection.getInputStream();
		return data;
	}

	public static String postMultipart(HttpURLConnection connection, Map<String, CharSequence> payload, InputStream bufin, String filename, long length, String fileContentType) throws IOException {
		String boundary = Long.toString(System.currentTimeMillis(), 16);
		byte[] data = null;
		byte[] endData = null;
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, CharSequence> entry : payload.entrySet()) {
			sb.append(PREF + boundary + CRLF);
			sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"");
			sb.append(CRLF);
			sb.append("Content-Type: text/plain; charset=utf-8");
			sb.append(CRLF);
			sb.append(CRLF);
			sb.append(entry.getValue().toString());
			sb.append(CRLF);
		}
		sb.append(PREF + boundary + CRLF);
		sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + filename + "\"" + CRLF);
		sb.append("Content-Type: ");
		sb.append(fileContentType);
		sb.append(CRLF);
		sb.append("Content-Transfer-Encoding: binary");
		sb.append(CRLF);
		sb.append(CRLF);
		endData = (CRLF + PREF + boundary + PREF + CRLF).getBytes("utf-8");
		data = sb.toString().getBytes("utf-8");
		connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		connection.setRequestProperty("Content-Length", String.valueOf(data.length + length + endData.length));
		byte b[] = new byte[UPLOAD_BUFFER_SIZE];
		OutputStream output = null;
		try {
			output = connection.getOutputStream();
			output.write(data);
			for (int len = 0; (len = bufin.read(b)) > 0;) {
				output.write(b, 0, len);
			}
			output.write(endData);
			output.flush();
		} finally {
			bufin.close();
			output.close();
		}
		String body = FetchUtil.inputStreamToString(connection.getInputStream());
		return body;

	}

	public static boolean exists(String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			return false;
		}
	}
	/*
	 * public static void main(String args[]) throws IOException{ String filePath="/home/zhao/下载/xx.jpg"; File a=new File(filePath); Map<String, CharSequence> payload = new HashMap(); payload.put("imgdir", "a/"); URL temp = new URL("http://upload.qimg.net/upload.php"); HttpURLConnection con = JdkHttpClient.getHttpConnection(temp, 100000, 100000); String xx=postMultipart(con,payload, a, "xx.jpg", "image/gif"); System.out.println(xx); }
	 */

}
