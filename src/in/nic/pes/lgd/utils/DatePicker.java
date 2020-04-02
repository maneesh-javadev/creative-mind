package in.nic.pes.lgd.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatePicker {
	public static java.sql.Date getDate(String strDate){
		
		GregorianCalendar date = new GregorianCalendar();
		 
	      int day = date.get(Calendar.DAY_OF_MONTH);
	      int month = date.get(Calendar.MONTH);
	      int year = date.get(Calendar.YEAR);
	 
	      //int second = date.get(Calendar.SECOND);
	      //int minute = date.get(Calendar.MINUTE);
	      //int  hour = date.get(Calendar.HOUR);
	 
	     strDate =  year+"-"+(month+1)+"-"+day;
	  

		

		DateFormat formater = new SimpleDateFormat("yyyy-MM-DD");

		java.sql.Date sqltDate=	null;

		try {

		java.util.Date parsedUtilDate = formater.parse(strDate);

		sqltDate =

		new java.sql.Date(parsedUtilDate.getTime());

		

		}

		catch (ParseException e) {

		return sqltDate;

		}

		return sqltDate;

		}

}
