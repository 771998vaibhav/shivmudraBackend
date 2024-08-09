package com.shivmudra.utils;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtils {


	/**
	 * yyyy-MM-dd HH:mm:ss format
	 */
	public static final String YyyyMmDdHmsFormat = "yyyy-MM-dd HH:mm:ss";
	public static final String DdmmyyyyHHmmssFormat = "dd/MM/yyyy HH:mm:ss";
	public static final String DddMMMyyyyHHmmssFormat = "dd-MMM-yyyy HH:mm:ss";
    public static final String HHmmddMMMyyyyFormat = " HH:mm dd-MMM-yyyy";
	public static final String DdmmyyyyFormat = "dd/MM/yyyy";
	public static final String MmddyyyyFormat = "MM/dd/yyyy";
	public static final String YyyyMmDd = "yyyy-MM-dd";
	public static final String YYYYMmDd = "yyyy/MM/dd";
	public static final String ddMMYYYY = "dd.MM.yyyy";
	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String yyyyMMddSlashed = "yyyy/MM/dd";
	public static final String dd_mm_yyyy = "dd-MM-yyyy";
	public static final String DdMonthYearFormat = "dd MMMM,yyyy";
	public static final String DdTrimmedMonthYearFormat = "dd MMM yyyy";
	public static final String DdMMMYYYYFormat = "dd MMM, yyyy";
	public static final String DdMonthYearHHmmFormat = "dd MMMM yyyy hh:mm a";
	public static final String Day_DdMonthYearFormat = "EEE, dd MMM yyyy";
	public static final String Day_DdMonthFormat = "EEE, dd MMM";
	public static final String HOUR_MINUTE_SEC = "HH:mm:ss";
	public static final String[] MONTHS_ARRAY = {"January","February","March","April","May","June","July","August","September","October","November","December"};
	public static final String YYYYMMDDPlain = "YYYYMMdd";
	public static final String YYYYMMDD24HHmmssPlain = "yyyyMMddHHmmss";
	public static final String dd_MMM_yy = "dd-MMM-yy";
	public static final String MMM_dd_yyyy = "MMM-dd-yyyy";
	public static final String DdMonthFormat = "dd MMM yyyy";
    public static final String hhmm_aFormate = "hh:mm a";

	/**
	 * Converts date to string
	 * @param date
	 * @param format
	 */
	public static String convertDateToString(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	
	/**
	 * Converts string to date
	 * @param date
	 * @param format
	 * @throws java.text.ParseException
	 */
	public static Date convertStringToDate(String date, String format) throws ParseException {
        if(date == null || date.equals("") || date.isEmpty()){
            return null;
        }
		SimpleDateFormat formatter = new SimpleDateFormat(format);
        try{
            return formatter.parse(date);
        }catch (Exception e){
            return null;
        }
	}
	
	/**
	 * Converts string to timestamp
	 * @param timestamp
	 * @param format
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Timestamp convertStringToTimestamp(String timestamp, String format) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return new Timestamp(formatter.parse(timestamp).getTime());
	}

	/**
	 * Get current mysql date
	 * @return
	 */
	public static String getCurMySQLDate() {
		Date date = new Date(System.currentTimeMillis());
		return getMySQLDate(date);
	}
	
	/**
	 * Get mysql date for the specified java date
	 * @param date
	 */
	public static String getMySQLDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(DateUtils.YyyyMmDd);
		return formatter.format(date);
	}
	
	/**
	 * Get current mysql timestamp
	 */
	public static String getCurMySQLDateWithTime() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatter = new SimpleDateFormat(DateUtils.YyyyMmDdHmsFormat);
		return formatter.format(date);
	}
	
	/**
	 * Get Date in yyyyMMdd format
	 * @param date
	 */
	public static String getDateInyyyyMMddFormat(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		return new StringBuilder().append(year).append(month).append(day).toString();
	}
	
	/**
	 * Return string representation of date in dd/MM/yyyy format
	 * @param date
	 */
	public static String getDateInddMMyyyyFormat(Date date){
	    SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/YYYY");
	    return outputDateFormat.format(date);
	}
	public static String getDateInMMDDyyyyFormat(Date date){
	    SimpleDateFormat outputDateFormat = new SimpleDateFormat("MM/dd/YYYY");
	    return outputDateFormat.format(date);
	}
	
	/**
	 * Get time in AM/PM format
	 */
	public static String getAmPmTime(int hourOfDay){
		if(hourOfDay <= 12){
			return hourOfDay + " AM";
		}
		hourOfDay = hourOfDay - 12;
		return hourOfDay + " PM";
	}
	
	/**
	 * Adds specified number of days in the date
	 * @param date
	 * @param days
	 * @return modified date
	 */
	public static Date addDays(Date date,int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}
	
	/**
	 * Adds specified number of years in the date
	 * @param date
	 * @param numberOfYears
	 * @return modified date
	 */
	public static Date addYears(Date date,int numberOfYears){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, numberOfYears);
		return calendar.getTime();
	}
	
	/**
	 * Adds specified number of months in the date
	 * @param date
	 * @param numberOfMonths
	 * @return modified date
	 */
	public static Date addMonths(Date date,int numberOfMonths){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, numberOfMonths);
		return calendar.getTime();
	}
	
	/**
	 * Add minutes to time
	 */
	public static Date addMinutes(Date date,int minutes){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}
	
	/**
	 * Get Day Start
	 * @param date
	 * @return modified date
	 */
	public static Date dayStart(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	/**
	 * Get Day Start
	 * @return modified date
	 */
	public static Date currentDayStart(){
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	/**
	 * Get current day end
	 */
	public static Date currentDayEnd(){
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND,59);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	/**
	 * Get Day End
	 * @param date
	 * @return modified date
	 */
	public static Date dayEnd(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND,59);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	/**
	 * Get Month End
	 * @return modified date
	 */
	public static Date dayMonthEnd(Integer month, Integer year){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);  
	    calendar.add(Calendar.DATE, -1);  
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND,59);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	
	/**
	 * Get Month Start
	 * @return modified date
	 */
	public static Date dayMonthStart(Integer month, Integer year){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_MONTH, 1);  
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	

	/**
	 * Get Month Start
	 */
	public static Date dayMonthStart(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);  
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	public static Date dayMonthEnd(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);  
	    calendar.add(Calendar.DATE, -1);  
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND,59);
		calendar.set(Calendar.MILLISECOND,0);
		return calendar.getTime();
	}
	/**
	 * Range of date from to both are inclusive.
	 * @param from
	 * @param to
	 */
	public static List<Date> getDateRange(Date from, Date to) {
		from = DateUtils.dayStart(from);
		to = DateUtils.dayStart(to);
		List<Date> dates = new ArrayList<Date>();
		if ( from.after(to)) return null;
		if ( from.equals(to)) {
			dates.add(from);
			return dates;
		}else {
			Date _temp = from;
			dates.add(from);
			while (!_temp.equals(to)) {
				_temp = addDays(_temp, 1);
				dates.add(_temp);
			}
		}
		return dates;
	}
	
	/**
	 * Utility method to calculate age
	 */
	public static int getAge(Date dateOfBirth){
        if(dateOfBirth == null){
            return 0;
        }
		Calendar dob = Calendar.getInstance();
		dob.setTime(dateOfBirth);
		Calendar today = Calendar.getInstance();  
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
		if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
		  age--;  
		} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
		    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
		  age--;  
		}
		return age;
	}
	
	/**
	 * Get current year
	 */
	public static String getCurYear(){
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return new StringBuilder().append(year).toString();
	}
	
	/**
	 * Get Year of the provided date
	 */
	public static Integer getYear(Date inputDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inputDate);
		return calendar.get(Calendar.YEAR);
	}
	/**
	 * Get month of the provided date
	 */
	public static Integer getMonth(Date inputDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inputDate);
		return calendar.get(Calendar.MONTH);
	}
	/**
	 * Get day of the provided date
	 */
	public static Integer getDay(Date inputDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inputDate);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Create Date from inputs
	 * For Month -> If 1 is passed then we will reduce it by -1 (because 0 is January for Calendar)
	 */
	public static Date createDate(Integer day,Integer month, Integer year){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month + 1);
		calendar.set(Calendar.YEAR, year);
		return calendar.getTime();
	}
	
	/**
	 * Get current month
	 */
	public static String getCurMonth(){
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		return new StringBuilder().append(month).toString();
	}
	
	/**
	 * Get current date
	 */
	public static String getCurDate(){
		Date date = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		return new StringBuilder().append(day).toString();
	}
	
	/**
	 * Remove time from the date
	 * @param date
	 */
	public static Date removeTime(Date date) {    
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);  
        return cal.getTime(); 
    }

	/**
	 * Set 12 PM time
	 * @param date
	 */
	public static Date set12PMTime(Date date){
		Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.set(Calendar.HOUR_OF_DAY, 12);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);  
        return cal.getTime();
	}
	
	/**
	 * Get no of days between dates
	 * @param fromDate
	 * @param toDate
	 */
	public static Long daysBetweenDates(Date fromDate, Date toDate){
        return ( (toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
	}

    public static int yearsBetweenDates(Date startDate, Date endDate){
        Calendar start = getCalendar(startDate);
        Calendar end = getCalendar(endDate);
        int diff = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
        if (start.get(Calendar.MONTH) > end.get(Calendar.MONTH) ||
                (start.get(Calendar.MONTH) == end.get(Calendar.MONTH) && start.get(Calendar.DATE) > end.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

    public static Long minsBetweenDates(Date fromDate, Date toDate){
        Long diff = ( (toDate.getTime() - fromDate.getTime()));
        return TimeUnit.MILLISECONDS.toMinutes(diff);
    }

	/**
	 * Check whether two dates are equal or not
	 * @param d1
	 * @param d2
	 */
	public static boolean equalDates(Date d1, Date d2) {
		if(d1 == null || d2 == null){
			return false;
		}
		return DateUtils.removeTime(d1).compareTo(DateUtils.removeTime(d2)) == 0;
	}
	
	public static Date getYearStartDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, getYear(date));
		cal.set(Calendar.DAY_OF_YEAR, 1);    
		return dayStart(cal.getTime());
	}
	
	public static Date getYearEndDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, getYear(date));
		cal.set(Calendar.MONTH, 11); // 11 = december
		cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
		return dayEnd(cal.getTime());
	}
	
	public static Date getYearStartDateByYear(int year){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.DAY_OF_YEAR, 1);    
		return dayStart(cal.getTime());
	}
	
	public static Date getYearEndDateByYear(int year){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, 11); // 11 = december
		cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
		return dayEnd(cal.getTime());
	}
	
	public static Date getFirstDayOfPreviousWeek() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int d = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		c.add(Calendar.DATE, -d - 7);
		return c.getTime();
	}
	@SuppressWarnings("deprecation")
	public static Date getFirstDayOfThisWeek() {
		Calendar cal = Calendar.getInstance(); 
        Date d = Calendar.getInstance().getTime();
        int currDay  =  d.getDay(); // getting the current day
        int startDay =  (currDay - cal.getFirstDayOfWeek()) + 1; // calculate the difference of number days to the current date from the first day of week
        d.setDate(d.getDate() - startDay); // setting the date accordingly.
        System.out.println(d);
        return d;
	}
	public static Date getFirstDayOfThisMonth() {
	  Calendar c = Calendar.getInstance();   // this takes current date
	  c.set(Calendar.DAY_OF_MONTH, 1);
	  return c.getTime();   
	}

    public static Date localDateToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate DateToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return  Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }


}
