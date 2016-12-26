package com.gui.parser;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.gui.WebSpiderView;
import com.gui.util.IOUtils;

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
	
	private static boolean hasKey(String text, String[] keys) {
		for (String key : keys) {
			if (text.contains(key)) {
				return true;
			}
		}
		return false;
	}
	
	public static void saveParseResult(List<String> matches, String[] keys) {
		String hasKeyPath = "result_has_key.txt";
		String noKeyPath = "result_no_key.txt";
		String separator = "\r\n\r\n";
		for (String line : matches) {
			line = line + separator;
			if (hasKey(line, keys)) {
				IOUtils.appendToFile(line, hasKeyPath);
			} else {
				IOUtils.appendToFile(line, noKeyPath);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
//		String path = "code.html";
//		String[] keys = {"</form>"};
//		showCodes(path, keys);
		
		Runnable r = new Runnable() {
            @Override
            public void run() {
            	new WebSpiderView();
            }
        };

        EventQueue.invokeLater(r);
	}
}
