package in.nic.pes.lgd.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomRegexValidator {

	private static CustomRegexValidator customValidator;

	public static CustomRegexValidator getInstance() {
		if (customValidator == null) {
			customValidator = new CustomRegexValidator();
		}
		return customValidator;
	}

	
	public boolean checkforAlphabetWithSpaceDotandSlash(String strName) { // check
																		// for
		// Alphabets &
		// Space
		String pattern = "^[a-zA-Z\\-\\.\\s]+$";
		if (!strName.matches(pattern)) {
			return false;
		}
		return true;

	}
	
		public boolean checkforAlphabetNumbericWithSpaceDotandSlash(String strName) { // check
				// for
		// Alphabets &
		// Space
		String pattern = "^[a-zA-Z0-9\\-\\.\\s]+$";
		if (!strName.matches(pattern)) {
		return false;
		}
		return true;
		
		}

	public boolean checkforAlphabetWithSpaceDotSlashandBrackets(String strName) { // check
		// for
		// Alphabets &
		// Space
		String pattern = "^[a-zA-Z0-9\\-\\.\\(\\)\\[\\]\\s]+$";
		if (!strName.matches(pattern)) {
			return false;
		}
		return true;

	}
	
	public boolean checkforAlphabetWithSpaceDotSlashesandBrackets(String strName) { // check
		// for
		// Alphabets &
		// Space
		String pattern = "^[a-zA-Z0-9\\-\\.\\(\\)\\/\\[\\]\\s]+$";
		if (!strName.matches(pattern)) {
			return false;
		}
		return true;

	}
	
	public boolean checkforAlphabetsandSpace(String strName) { // check for
																// Alphabets &
																// Space
		String pattern = "^[a-zA-Z\\s]+$";
		if (!strName.matches(pattern)) {
			return false;
		}
		return true;

	}

	public boolean checkforAlphaNumericandSpace(String strName) {
		String pattern = "^[a-zA-Z0-9\\s]+$";
		
		
		if (!strName.matches(pattern)) {
			return false;
		}
		return true;

	}
	
	public boolean checkforAlphaNumericand(String strName) {
		String pattern = "^[a-zA-Z0-9]+$";
		if (!strName.matches(pattern)) {
			return false;
		}
		return true;

	}

	public boolean checkforOnlyNumeric(String strName) {
		String pattern = "^[0-9]+$";
		if (!strName.matches(pattern)) {
			return false;
		}
		return true;
	}

	public boolean checkforOrderNum(String strName) {
		String pattern = "^[a-zA-Z0-9\\-\\/\\.\\s\\(\\)]+$";
		if (!strName.trim().matches(pattern)) {
			return false;
		}
		return true;
	}

	public boolean checkforDate(Date dateName) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy",
				Locale.ENGLISH);
		String date = formatter.format(dateName);
		if (date == null|| !date.matches("\\d{2}-\\d{2}-\\d{4}")) {
			return false;
		}
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		df.setLenient(false);
		try {
			df.parse(date);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
// TODO Remove unused code found by UCDetector
// 	public boolean checkforMonth()
// 	{
// 		String monthName="12";
// 		if(monthName == null || !monthName.matches("^((0[1-9])|(1[0-2]))$"))
// 		{
// 			return false;
// 		}
// 		else
// 		{
// 			return true;
// 		}
// 	}
	
// TODO Remove unused code found by UCDetector
// 	public boolean checkforYear()
// 	{
// 		String yearName="1000";
// 		if(yearName == null || !yearName.matches("^(19|20)\\d{2}$"))
// 		{
// 			return false;
// 		}
// 		else
// 		{
// 			return true;
// 		}
// 	}
	
	public boolean checkforLatiandLongi(String strName)
	{
		String pattern = "^[0-9\\.\\,]+$";		
		if (!strName.matches(pattern)) {
			return false;
		}
		return true;
		
	}
	
	/**
	 * {@code validateSpecialCharacters} method validation for special characters,
	 * If any special charater exist in string {@code method} return false, true
	 * returns for valid string.
	 * 
	 * @return boolean {true, false}
	 * @author vinay Yadav
	 */
	public boolean validateSpecialCharacters(String inputValue){
		/* Allow '-'(hyphen) in Name of the Localbody (In English) and Name of Localbody (In Local language) @Asshish Dhupia @date 09-09-2014 */
		String regexPattern = "[\\#\\/%&\\~\\!\\@\\$\\^\\*\\_\\+\\`\\=\\{\\}\\\\\\[\\\\\\]\\|\\:\\;\\'\\\\\"\\<\\>\\?\\\\]";
		
		Pattern pattern 		= Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher 		= pattern.matcher(inputValue);
		boolean hasSpecialChars = matcher.find();

		/*if (hasSpecialChars)
		    System.out.println("There is a special character in input string");
		else
			System.out.println("There is no special character in input string");*/
		
		return !hasSpecialChars;
	}
	
	public boolean validateSpecialCharactersHyphen(String inputValue){
		 /**
		  * Change remove / from regexPattern to allow '/' in local Name
		  */
		String regexPattern = "[\\#\\%&\\~\\!\\@\\$\\^\\*\\_\\+\\`\\=\\{\\}\\\\\\[\\\\\\]\\|\\:\\;\\'\\\\\"\\<\\>\\?\\\\]";
		
		Pattern pattern 		= Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher 		= pattern.matcher(inputValue);
		boolean hasSpecialChars = matcher.find();

		/*if (hasSpecialChars)
		    System.out.println("There is a special character in input string");
		else
			System.out.println("There is no special character in input string");*/
		
		return !hasSpecialChars;
	}

// TODO Remove unused code found by UCDetector
// 	public boolean checkAlphabetdigitSpacedotspace(String strName) { 
//        String pattern = "^[0-9a-zA-Z\\.\\-\\s]+$";
//           if (!strName.matches(pattern)) {
//             return false;
//      }
//      return true;
// 
//    }  
	/**
	 * Added by sushil on 02-05-2013
	 * @param strName, will check for alphanumeric with dot
	 * @return boolean
	 */
	public boolean validateAlphabetDigitSpaceDot(String strName) { 
		/* Allow '-'(hyphen) in Name of the Localbody (In English) and Name of Localbody (In Local language) @Asshish Dhupia @date 09-09-2014 */   
		String pattern = "^[0-9a-zA-Z\\.\\s\\-]+$";
	          if (!strName.matches(pattern)) {
	            return false;
	     }
	     return true;
	   }  
	
	/**
	 * Added by Arnab on 03-05-2013
	 * @param strName, will check for number with slash
	 * @return boolean
	 */
	
	public boolean checkforNumericandSlash(String strName) {
		String pattern = "^[0-9\\/]+$";
		if (!strName.matches(pattern)) {
			return false;
		}
		return true;

	}
	/**
	 * Added by sushil on 10-05-2013
	 * @param strName, will check for short name
	 * @return boolean
	 */
	public boolean checkforShortName(String strName) {
		String pattern = "^[a-zA-Z]+$";
		if (!strName.matches(pattern)) {
			return false;
		}
		return true;
	}
	
	
	public boolean checkLatitudeRange(String cordinateList)
	{
		boolean flag=false;
		String cordinates[] = cordinateList.split(",");
		for ( int i =0 ; i < cordinates.length; i++) {
			try
			{
				Float lati=Float.valueOf(cordinates[i]);
				if(lati>=6 && lati<=38){
					flag=true;
				}	
				
			}catch(Exception e)
			{
				flag=false;
			}
		}
		
		return flag;
			
	}
	
	
	public boolean checkLongitudeRange(String cordinateList)
	{
		boolean flag=false;
		String cordinates[] = cordinateList.split(",");
		for ( int i = 0; i < cordinates.length; i++) {
			try
			{
				Float longi=Float.valueOf(cordinates[i]);
				if(longi>=32 && longi<=98){
					flag=true;
				}	
				
			}catch(Exception e)
			{
				flag=false;
			}
		}
		
		return flag;
			
	}
	
	public boolean checkEmail(String mail)
	{
		String patteren="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if(!mail.matches(patteren)){
			return false;
		}else{
			return true;
		}
	}
	
	
	public boolean checkforAlphabetNumbericHypenSpace(String strName) { 
		String pattern = "^[a-zA-Z0-9\\-\\s]+$";
		if (!strName.matches(pattern)) {
			return false;
		}
		return true;

	}

 }