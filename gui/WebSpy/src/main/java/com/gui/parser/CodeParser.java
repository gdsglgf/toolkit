package com.gui.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CodeParser {
	public static void showCodes(String path, String[] keys) throws Exception {
		FileInputStream fin = new FileInputStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(fin));
		String line = null;
		while ((line = br.readLine()) != null) {
			for (String key : keys) {
				if (line.contains(key)) {
					System.out.println(line.trim());
					System.out.println();
					break;
				}
			}
		}
		br.close();
	}
	
	public static void main(String[] args) throws Exception {
		String path = "code5.html";
		String[] keys = {"sayBefore("};
		showCodes(path, keys);
	}
}
