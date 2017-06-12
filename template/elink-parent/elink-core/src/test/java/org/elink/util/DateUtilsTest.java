package org.elink.util;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import com.elink.util.DateUtils;

public class DateUtilsTest {
	@Test
	public void testParse() {
		assertEquals(null, DateUtils.parse(null));
		
		Date d1 = new Date();
		String s = DateUtils.format(d1);
		Date d2 = DateUtils.parse(s);
		System.out.printf("%d-%d%n", d1.getTime(), d2.getTime());
		assertEquals(true, d1.getTime() >= d2.getTime());
	}
	
	@Test
	public void testFormat() {
		assertEquals(null, DateUtils.format(null));
		
		String now = DateUtils.now();
		assertEquals(now, DateUtils.format(DateUtils.parse(now)));
		System.out.printf("now is %s%n", now);
	}
}
