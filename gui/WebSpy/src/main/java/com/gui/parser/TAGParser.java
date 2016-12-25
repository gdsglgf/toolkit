package com.gui.parser;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TAGParser {
	public static List<String> parse(String html, String tagName) {
		List<String> matches = new ArrayList<String>();
		Document doc = Jsoup.parse(html);
		Elements tags = doc.getElementsByTag(tagName);
		for (Element tag : tags) {
			String code = tag.text();
			matches.add(code.trim());
		}
		return matches;
	}
}
