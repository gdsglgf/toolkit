package com.gui.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class KVParser {
	public static List<String> parse(String html, String key, String value) {
		List<String> codes = new ArrayList<String>();
		Document doc = Jsoup.parse(html);
		Elements tags = doc.getElementsByAttributeValue(key, value);
		for (Element tag : tags) {
			String code = tag.text();
			codes.add(code.trim());
		}
		return codes;
	}
}
