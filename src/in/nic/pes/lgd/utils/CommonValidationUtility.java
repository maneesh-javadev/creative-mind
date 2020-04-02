package in.nic.pes.lgd.utils;
//By Abhishek Kumar Singh dated 19-12-16
public class CommonValidationUtility {
	String validMsg="";
	public String isValidInteger(String arg){
		validMsg="";
		if(!(isValidIntegerType(arg))){
			validMsg="Invalid";
		}
		return validMsg;
	}
	public String isValidLetter(String arg){
		if(!(isValidLetterType(arg))){
			if(arg.length() > 0 && !(arg.matches("[a-zA-Z0-9]"))){
				validMsg="Invalid";
			}
		}
		return validMsg;
	}
	public static boolean isValidIntegerType(String codeValue){
		for (int i=0; i < codeValue.length(); i++) {
	        if (!Character.isDigit(codeValue.charAt(i))) {
	            return false;
	        }
	    }
		return true;
	}
	public static boolean isValidLetterType(String codeValue){
		for (int i=0; i < codeValue.length(); i++) {
	        if (!Character.isLetter(codeValue.charAt(i)) && codeValue.charAt(i) != ' ') {
	            return false;
	        }
	    }
		return true;
	}
}
