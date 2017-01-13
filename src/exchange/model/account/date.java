package exchange.model.account;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class date {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		DateFormat df = DateFormat.getDateInstance();
//		Date date = df.parse("2009-1-1");
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		System.out.println(sdf.format(calendar.getTime()));
		Date d=new Date();
	    SimpleDateFormat sdf=new SimpleDateFormat();
	    System.out.println(sdf.toPattern());  //輸出 yyyy/M/d a h:mm
	    System.out.println(sdf.format(d));    //輸出 2014/4/1 下午 1:44
	    sdf.applyPattern("yyyy-MM-dd");  //套用新格式
	    System.out.println(sdf.format(d));  //輸出 2014-04-01 13:44:36
	}

}
