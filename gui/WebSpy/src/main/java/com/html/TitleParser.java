package com.html;

import java.util.Scanner;

public class TitleParser {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		String url;
		while (cin.hasNext()) {
			url = cin.nextLine();
			url = url.trim();
			if (url.length() > 0) {
				WebParser.showTile(url);
			}
		}
		cin.close();
	}
}
