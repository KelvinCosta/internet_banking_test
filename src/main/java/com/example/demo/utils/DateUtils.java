package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {

	public static Long convertStringToLongMinutes(String time) {
		try {
			Long hh = Long.valueOf(time.substring(0, 2));
			Long mm = Long.valueOf(time.substring(3, 5));
			Long minutes = (hh * 60) + mm;
			return minutes;
		} catch (Exception e) {
			return 0L;
		}

	}

	public static String convertLongToString(Long tm) {
		try {
			int time = tm.intValue();
			int hh = time / 60;
			int mm = time % 60;
			return String.format("%02d:%02d", hh, mm);
		} catch (Exception e) {
			return "00:00";
		}
	}

	public static String convertIntegerToString(Integer tm) {
		try {
			Integer hours = (int) (tm / 60); // since both are ints, you get an int
			Integer minutes = (int) (tm % 60);
			String horas = (hours < 10 ? "0" + hours : hours.toString()) + ":"
					+ (minutes < 10 ? "0" + minutes : minutes.toString());
			return horas;
		} catch (Exception e) {
			return "00:00";
		}
	}

	public static Long calculateTime(List<Long> times) {
		Long totalHours = 0L;
		for (Long hours : times) {
			totalHours += hours;
		}
		return totalHours;
	}

	public static Date convertStringToDate(String dateFormat, String date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date convertStringToMondayDate(String dateFormat, String date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(convertStringToDate(dateFormat, date));
		calendar.add(Calendar.DAY_OF_WEEK, Calendar.MONDAY - calendar.get(Calendar.DAY_OF_WEEK));
		return calendar.getTime();
	}

	public static String convertDateToString(String dateFormat, Date date) {
		if (date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}

	/**
	 * Method convert Week Number to yyyy-MM-dd Date * It always use monday as the
	 * week day
	 * 
	 * @throws ParseException
	 */
	public static String convertWeekToDate(String dateFormat, Integer year, Integer week) {
		if (year == null || week == null || week < 1)
			return null;
		Calendar date = Calendar.getInstance();
		date.setFirstDayOfWeek(Calendar.MONDAY);
		date.setMinimalDaysInFirstWeek(7);
		date.setWeekDate(year, 1, Calendar.MONDAY);
		date.add(Calendar.WEEK_OF_YEAR, week - 1);
		return convertDateToString(dateFormat, date.getTime());
	}

	/**
	 * Method convert Week Number to yyyy-MM-dd Date * It always use monday as the
	 * week day
	 * 
	 * @throws ParseException
	 */
	public static String convertWeekToDate(String dateFormat, Integer year, String week) {
		Integer weekNumber = Integer.parseInt(week.replaceAll("[a-zA-Z]*", ""));
		return convertWeekToDate(dateFormat, year, weekNumber);
	}

	/**
	 * Method convert yyyy-MM-dd Date to Week Number * It always use monday as the
	 * week day
	 * 
	 * @throws ParseException
	 */
	public static Integer convertDateToWeek(String dateFormat, String date) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setMinimalDaysInFirstWeek(7);
		cal.setTime(convertStringToDate(dateFormat, date));
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * Method get Week dates and numbers between dates.
	 * 
	 * @throws ParseException
	 */
	public static HashMap<Integer, HashMap<Integer, String>> getWeekNumberAndDateBetween(String dateFormat, Date start, Date end) {
		if (start == null || end == null)
			return null;
		if (start.getTime() > end.getTime()) {
			Date tmp = start;
			start = end;
			end = tmp;
		}
		HashMap<Integer, HashMap<Integer, String>> year = new HashMap<>();
		HashMap<Integer, String> List = new HashMap<>();
		Calendar date = Calendar.getInstance();
		date.setFirstDayOfWeek(Calendar.MONDAY);
		date.setMinimalDaysInFirstWeek(7);
		date.setTime(start);
		year.put(date.getWeekYear(), List);

		while (date.getTimeInMillis() <= end.getTime()) {
			if (date.get(Calendar.WEEK_OF_YEAR) == 1) {
				List = new HashMap<>();
				year.put(date.getWeekYear(), List);
			}
			List.put(date.get(Calendar.WEEK_OF_YEAR), convertDateToString(dateFormat, date.getTime()));
			date.add(Calendar.WEEK_OF_YEAR, 1);
		}

		return year;
	}
}
