/**
 * 
 */
package q.serialize.mock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import q.serialize.mapping.OperationCodeException;

/**
 * @author xalinx at gmail dot com
 * @date Dec 8, 2009
 */
public class ApiMappingExceptionTest extends ApiMappingXmlTestBase {

	@Test
	public void testMRSuccess() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-exception.xml");
		ResultWrap wrap = new ResultWrap();
		wrap.setResult("hello");
		wrap.setSuccess(true);
		Object test = testDefaultResponse(file, wrap);
		assertEquals("{\"result_api\":\"hello\"}", test);
	}

	@Test
	public void testMRSuccessButNullResult() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-exception.xml");
		ResultWrap wrap = new ResultWrap();
		wrap.setResult(null);
		wrap.setSuccess(true);
		Object test = testDefaultResponse(file, wrap);
		assertEquals("{}", test);
	}

	@Test
	public void testMRException() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-exception.xml");
		ResultWrap wrap = new ResultWrap();
		wrap.setResult("hello");
		wrap.setSuccess(false);
		wrap.setErrorCode("400");
		wrap.setErrorMsg("error_msg");
		Object test = testDefaultResponse(file, wrap);
		OperationCodeException e = (OperationCodeException) test;
		// assertEquals(wrap.getErrorCode(), e.getCode());
		// assertEquals(wrap.getErrorMsg(), e.getMsg());
	}

	@Test
	public void testMREmptyResultExceptionSuccess() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-exception-emptyresult.xml");
		ResultWrap wrap = new ResultWrap();
		wrap.setResult(null);
		wrap.setSuccess(true);
		Object test = testDefaultResponse(file, wrap);
		assertEquals("{}", test);
	}

	private class ResultWrap {
		private boolean success = false;

		private String errorCode;

		private String errorMsg;

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorMsg() {
			return errorMsg;
		}

		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}

		private String result;

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}

	}
}
