package q.serialize.util;

import org.junit.Test;

import q.serialize.util.LogForException;


public class LogForExceptionTest {

	@Test
	public void testLogInput(){
		String method ="item.get";
		Object[] args = {"1",2,true};
		String[] types = {"String","Integer","Boolean"};
		LogForException.logInput(method,args,types);
	}
	
	@Test
	public void testLogOutPut(){
		String method ="item.get";
		Object[] args = {"1",2,true};
		String[] types = {"String","Integer","Boolean"};
		String response = "response";
		LogForException.logOutput(method,args,types,response);
	}
}
