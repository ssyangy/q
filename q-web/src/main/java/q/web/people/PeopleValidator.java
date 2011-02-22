package q.web.people;

import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;

public class PeopleValidator {
	  /**
	   *验证email
	  */
	  public static boolean validateEmail(String email){
		   return  GenericValidator.isEmail(email);	    	
	  }
	  /**
	   *验证圈子帐号,大小写字母开头，允许6-16字节，允许字母数字下划线
	  */
      public static boolean validateAccount(String account){
    	  Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]{5,15}$");
    	  return(p.matcher(account).matches());
    	    
      }
      /**
	   *验证密码，允许6-16字节，允许字母数字下划线,区分大小写
	  */
      public static boolean validatePassword(String password){
    	  Pattern p = Pattern.compile("^[0-9a-zA-Z_]{5,15}$");
    	  return(p.matcher(password).matches());
      }
      /**
	   *验证姓名，允许1-12字节，允许汉字字母数字下划线,区分大小写
	  */
      public static boolean validateName(String name){
    	  Pattern p = Pattern.compile("^[a-zA-Z0-9_\u4e00-\u9fa5]+{0,11}$");
    	  return(p.matcher(name).matches());
      }
}
