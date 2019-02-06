import java.util.Arrays;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
* Stores or checks dates. Class will not be properly created if the date or time are not valid. Also handles leap years.
*
* @author Oliver Broomhall
* @version 1.0
* @release 22/02/2016
*/
public class BetterDate{
	
	private int day;
	private int month;
	private int year;
	private int minute;
	private int hour;
	
	// Days in each corresponding month
	private int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	// Month names can be accessed publicly
	public static final String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	public static final String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	/**
	* Constructor. Sets the date variables, the time varibles, or both
	*/
	public BetterDate(int day, int month, int year){
		setDate(day, month, year);
	}
	public BetterDate(int hour, int minute){
		setTime(hour, minute);
	}
	public BetterDate(int day, int month, int year, int hour, int minute){
		setDate(day, month, year);
		setTime(hour, minute);
	}
	
	/**
	* Changes the date if feasible.
	*
	* @return True if the date is feasible, false otherwise.
	*/
	public boolean setDate(int day, int month, int year){
		if (dateCheck(day, month, year)){
			this.day = day;
			this.month = month;
			this.year = year;
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	* @return Date String in the form "dd/mm/yyyy".
	*/
	public String getDateString(){
		return day + "/" + month + "/" + year;
	}
	
	/**
	* @return Date String in the form "dd[st/nd/rd/th] month yyyy".
	*/
	public String getWordedDate(){
		return day + getOrdinalIndicator() + " " + getMonthString() + " " + year;
	}
	
	/**
	* @return Full name of the month.
	*/
	public String getMonthString(){
		return monthNames[month-1];
	}
	
	/**
	* @return Short name of the month. e.g. Jan, Feb...
	*/
	public String getMonthStringShort(){
		return monthNames[month-1].substring(0,3);
	}
	
	public String getTimeLeft(){
		return getTimeBetween(getCurrentDate());
	}
	public String getTimeBetween(BetterDate current){
		int hours = hoursBetween(current);
		int minutes = minutesBetween(current);
		System.out.println(hours + "h" + minutes + "m");
		String time = "";
		if (minutes < 0){
			if (hours > 0){
				minutes += 60;
			}
			else{
				minutes = minutes * -1;
			}
		}
		else{
			if (hours < 0){
				minutes -= 60;
				minutes = minutes * -1;
			}
		}
		if (hours < 0){
			time += "-";
			hours = hours * -1;
		}
		if (hours < 10){
			time += "0";
		}
		time += hours + ":";
		if (minutes < 10 && minutes >= 0){time += 0;}
		return time + minutes;
	}
	
	/**
	* Accessors.
	*/
	public int getDay(){return day;}
	public int getMonth(){return month;}
	public int getYear(){return year;}
	public int getHour(){return hour;}
	public int getMinute(){return minute;}
	
	/**
	* @return Correct ordinal indicator for the day.
	*/
	public String getOrdinalIndicator(){
		if (day%10 == 1 && day != 11){return "st";}
		else if (day%10 == 2 && day != 12){return "nd";}
		else if (day%10 == 3 && day != 13){return "rd";}
		else{return "th";}
	}
	
	/**
	* @return BetterDate instance of todays date.
	*/
	public static BetterDate getCurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy/hh/mm/aa");
		Date date = new Date();
		String[] dateSplit = dateFormat.format(date).split("/");
		int day = Integer.parseInt(dateSplit[0]);
		int month = Integer.parseInt(dateSplit[1]);
		int year = Integer.parseInt(dateSplit[2]);
		int hour = Integer.parseInt(dateSplit[3]);
		int minute = Integer.parseInt(dateSplit[4]);
		if (dateSplit[5].equals("PM")){hour += 12;}
		
		return new BetterDate(day, month, year, hour, minute);
	}
	
	/**
	* @return True if date and time of this class is after the date and time passed as parameter.
	*/
	public boolean isAfter(BetterDate bd){
		if (isAfterDate(bd)){
			return true;
		}
		else if (equalsDate(bd) && isAfterTime(bd)){
			return true;
		}
		return false;
	}
	/**
	* @return True if date of this class is after the date passed as parameter.
	*/
	public boolean isAfterDate(BetterDate bd){
		if (daysBetween(bd) > 0){
			return true;
		}
		return false;
	}
	/**
	* @return True if time of this class is after the time passed as parameter.
	*/
	public boolean isAfterTime(BetterDate bd){
		if (totalMinutesBetween(bd) > 0){
			return true;
		}
		return false;
	}
	
	/**
	* @return True if the two BetterDate classes contain the same date and time.
	*/
	public boolean equals(BetterDate bd){
		if (equalsDate(bd) && equalsTime(bd)){
			return true;
		}
		return false;
	}
	/**
	* @return True if the two BetterDate classes contain the same date.
	*/
	public boolean equalsDate(BetterDate bd){
		if (day == bd.getDay() && month == bd.getMonth() && year == bd.getYear()){
			return true;
		}
		return false;
	}
	/**
	* @return True if the two BetterDate classes contain the same time.
	*/
	public boolean equalsTime(BetterDate bd){
		if (hour == bd.getHour() && minute == bd.getMinute()){
			return true;
		}
		return false;
	}
	
	/**
	* @return Exact number of days until the specified date. Can also count back.
	*/
	public int daysLeft(){
		return daysBetween(getCurrentDate());
	}
	public int daysBetween(BetterDate current){
		
		int years = year - current.getYear();
		int months = month - current.getMonth();
		int days = day - current.getDay();
		
		// Handles leap years
		if (year%4 == 0 && month > 2 && current.getMonth() <= 2){
			days++;
		}
		else if (year%4 == 0 && month <= 2 && current.getMonth() > 2){
			days--;
		}
		
		// Adds the correct number of days corresponding to each month
		for (int i = month-2; i > current.getMonth()-2; i--){
			days += monthDays[i];
		}
		
		// Subtracts days of months if the month is before todays month
		if (current.getMonth() > month){
			for (int i = month-1; i < current.getMonth()-1; i++){
				days -= monthDays[i];
			}
		}
		
		// Adds or subtracts full years in days. Handles leap years.
		if (current.getYear() > year){
			for (int i = year; i < current.getYear(); i++){
				if (i%4 == 0){days -= 366;}
				else{days -= 365;}
			}
		}
		else{
			for (int i = year-1; i >= current.getYear(); i--){
				if (i%4 == 0){days += 366;}
				else{days += 365;}
			}
		}
		
		return days;
	}
	
	public int totalMinutesLeft(){
		return totalMinutesBetween(getCurrentDate());
	}
	public int totalMinutesBetween(BetterDate current){
		int hours = hour - current.getHour();
		int minutes = minute - current.getMinute();
		return hours*60 + minutes;
	}
	public int minutesLeft(){
		return minutesBetween(getCurrentDate());
	}
	public int minutesBetween(BetterDate current){
		return minute - current.getMinute();
	}
	public int hoursLeft(){
		return hoursBetween(getCurrentDate());
	}
	public int hoursBetween(BetterDate current){
		int minutes = totalMinutesBetween(current);
		int hours = minutes/60;
		return hours;
	}
	
	/**
	* Finds the date after or before a specified number of days.
	*/
	public BetterDate addDays(int days){
		day += days;
		dayAddition();
		daySubtraction();
		return this;
	}
	// Adds days to the date
	private void dayAddition(){
		
		while (day > monthDays[month-1]){
			if (year%4 == 0 && month == 2){
				monthDays[1] = 29;
			}
			
			day -= monthDays[month-1];
			month++;
			if (month == 13){
				month = 1;
				year++;
			}
			
			if (monthDays[1] == 29){
				monthDays[1] = 28;
			}
		}
	}
	// Subtracts days from the date
	private void daySubtraction(){
		while (day < 1){
			if (year%4 == 0 && month == 2){
				monthDays[1] = 29;
			}
			
			if (month == 1){
				month = 12;
				year--;
				day += monthDays[11];
			}
			else{
				day += monthDays[month-2];
				month--;
			}
			
			if (monthDays[1] == 29){
				monthDays[1] = 28;
			}
		}
	}
	
	/**
	* Methods to add exact weeks, months, or years onto the current date. Useful for recurring events.
	*/
	public BetterDate addWeeks(int weeks){
		return addDays(7*weeks);
	}
	public BetterDate addMonths(int months){
		month += months;
		while (month > 12){
			year++;
			month -= 12;
		}
		return this;
	}
	public BetterDate addYears(int years){
		year += years;
		return this;
	}
	
	public int getWeekDayNumber(){
		BetterDate jan2001 = new BetterDate(01,01,2001,00,00);
		int days = daysBetween(jan2001);
		days %= 7;
		return days + 1;
	}
	public String getWeekDay(){
		int day	= getWeekDayNumber() - 1;
		return dayNames[day];
	}
	public String getWeekDayShort(){
		return getWeekDay().substring(0,3);
	}
	
	/**
	* Method dateCheck()
	*
	* @return boolean
	*	Returns true if the date entered is a real date. False otherwise.
	*/
	public boolean dateCheck(){
		return dateCheck(day, month, year);
	}
	public boolean dateCheck(int day, int month, int year){
		if (day < 1){return false;} // Day must be positive
		if (month < 1 || month > 12){return false;} // Months must be between 1 and 12 inclusive
		if (year < 1900 || year > 2200){return false;} // Year must be within a reasonable region
		
		// February can have 29 days on a leap year
		if (month == 2 && year%4 == 0 && day <= 29){
			return true;
		}
		// The number of days cannot be greater than the maximum number of days for that month
		else if (day > monthDays[month-1]){
			return false;
		}
		// The date is real
		return true;
	}
	
	/**
	* Sets the time.
	*/
	public boolean setTime(int hour, int minute){
		if (hour >= 0 && hour < 25 && minute >= 0 && minute < 60){
			this.hour = hour;
			this.minute = minute;
			return true;
		}
		return false;
	}

	public String getTime(){
		String time = "";
		if (hour < 10){time += 0;}
		time += hour + ":";
		if (minute < 10){time += 0;}
		return time + minute;
	}
	public String getTime12h(){
		String time = "";
		if (hour%12 < 10){time += 0;}
		time += hour%12 + ":";
		if (minute < 10){time += 0;}
		return time + minute;
	}
	public String getTimeSuffix(){
		if (hour < 12){return "am";}
		else{return "pm";}
	}
	
}