package com.html;

public class LinkParser {

	public static void main(String[] args) throws Exception {
		String url = "http://www.test.com/";
		if (args.length > 0) {
			url = args[0];
		}
		// Document doc = Jsoup.parse(new URL(url), 1000); // 403 error
		WebParser.showLink(url);
	}

}
