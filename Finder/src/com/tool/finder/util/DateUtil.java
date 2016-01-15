package com.tool.finder.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static String time14() {
		return format.format(new Date());
	}
}
