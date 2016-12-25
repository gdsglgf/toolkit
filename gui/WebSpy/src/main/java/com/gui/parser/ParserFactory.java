package com.gui.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParserFactory {
	private static String[] splitKV(String pattern) {
		if (pattern == null) {
			return new String[]{};
		}
		int index = pattern.indexOf('=');
		if (index == -1) {
			return new String[]{"RE", pattern};
		}
		String key = pattern.substring(0, index);
		String value = pattern.substring(index + 1);
		return new String[]{key, value};
	}
	
	public static List<String> parse(String html, String pattern) {
		String[] kv = splitKV(pattern);
		if (kv.length == 0) {
			return new ArrayList<String>();
		}
		
		String key = kv[0].toUpperCase();
		String value = kv[1];
		List<String> matches = null;
		switch(key) {
		case "RE":
			matches = REParser.parse(html, value);
			break;
		case "TAG":
			matches = TAGParser.parse(html, value);
			break;
		default:
			matches = KVParser.parse(html, kv[0], value);
			break;
		}
		
		return matches;
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(splitKV("12345")));
		System.out.println(Arrays.toString(splitKV("12345=")));
		System.out.println(Arrays.toString(splitKV("a=b=c=d=e")));
		System.out.println(Arrays.toString(splitKV("RE=(<meta.*?>)")));
		System.out.println(Arrays.toString(splitKV("TAG=title")));
		System.out.println(Arrays.toString(splitKV("key=value")));
	}
}
