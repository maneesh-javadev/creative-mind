package in.nic.pes.lgd.utils;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

public class CurrentDateTime {
	
	private static final Logger log = Logger.getLogger(CurrentDateTime.class);

// TODO Remove unused code found by UCDetector
// 	public static String getDateTime() {
// 	         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
// 	         Date date = new Date();
// 	         return dateFormat.format(date);
// 	     }
	public static Timestamp getCurrentTimestamp(){
		Timestamp time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		String dateString = sdf.format(Calendar.getInstance().getTime());
		//System.out.println("date :: "+dateString);
		try {
			Date date = sdf.parse(dateString);
			time = new Timestamp(date.getTime());
		} catch (ParseException e) {
			log.debug("Exception" + e);
		} 
		//System.out.println("timest : "+time);
		return time;
	}
	
	public static java.sql.Date getDate(String strDate){
		DateFormat formater = new SimpleDateFormat("yyyy-MM-DD");
		java.sql.Date sqltDate=null;
		try {
			java.util.Date parsedUtilDate = formater.parse(strDate);
			sqltDate = new java.sql.Date(parsedUtilDate.getTime());
			//System.out.println("sqltDate ::: "+sqltDate);
		} catch (ParseException e) {
			log.debug("Exception" + e);
			return sqltDate;
		}
		return sqltDate;
	}
	
	public Date getCurrentDate() {
		Calendar currentDate = Calendar.getInstance();
 
		Date dateNow = currentDate.getTime();
		return dateNow;

	}

}
