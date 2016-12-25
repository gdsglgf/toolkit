package com.gui.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class REParser {
	private static String formatRegex(String regex) {
		if (regex.contains("(") && regex.contains(")")) {
			return regex;
		}
		return "(" + regex + ")";
	}
	
	public static List<String> parse(String html, String regex) {
		regex = formatRegex(regex);
		List<String> matches = new ArrayList<String>();
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(html);
		while (m.find()) {
			try {
				matches.add(m.group(1));
			} catch (Exception e) {
				break;
			}
		}
		return matches;
	}
}
