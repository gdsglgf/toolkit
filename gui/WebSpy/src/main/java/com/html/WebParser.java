package com.html;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebParser {
	// 浏览器版本过低
	// Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0
	// Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.97 Safari/537.11
	
	// 设置高浏览器版本
	//  Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36
	public static Document get(String url) {
		if (!url.startsWith("http")) {
			url = "http://" + url;
		}
		Document doc = null;
		try {
			doc = Jsoup.connect(url)
					.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")
					.timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public static void showLink(String url) {
		Document doc = get(url);
		Elements links = doc.select("a[href]");
		for (Element e : links) {
			System.out.println(e.attr("href") + "   " + e.text());
		}
	}
	
	public static void showTile(String url) {
		Document doc = get(url);
//		System.out.println(doc.html());
		Elements tag = doc.getElementsByTag("title");
		String title = "";
		if (tag != null) {
			title = tag.text().trim();
		}
		System.out.println(url + "    " + title);
	}
}
