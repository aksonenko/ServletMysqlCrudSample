package com.ab.jdbcsample.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper {
	private static final String SIMPLE_DATE_PATTERN = "dd/MM/yyyy";

	public static String getSimpleDateTime(Date date) {
		return getSimpleDate(SIMPLE_DATE_PATTERN, date);
	}

	private static String getSimpleDate(String pattern, Date date) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static Date getSimpleDate(String date) {
		return getDateByPattern(SIMPLE_DATE_PATTERN, date);
	}

	private static Date getDateByPattern(String pattern, String date) {
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
