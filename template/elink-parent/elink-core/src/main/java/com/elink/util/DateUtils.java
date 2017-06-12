package com.elink.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String now() {
		return sdf.format(new Date());
	}

	public static String format(Date date) {
		if (date == null) {
			return null;
		}
		return sdf.format(date);
	}
	
	public static Date parse(String d) {
		try {
			return sdf.parse(d);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
