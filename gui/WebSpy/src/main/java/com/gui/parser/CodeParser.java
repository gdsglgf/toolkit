package com.gui.parser;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gui.WebSpiderView;
import com.gui.util.IOUtils;

public class CodeParser {
	public static final String PUBLIC_NAME_REGEX = "public\\s+(?:class|interface)\\s+(\\w+)";
	public static final Pattern PUBLIC_NAME_PATTERN = Pattern.compile(PUBLIC_NAME_REGEX);
	
	public static String parsePublicName(String text) {
		Matcher m = PUBLIC_NAME_PATTERN.matcher(text);
		if (m.find()) {
			return m.group(1);
		}
		return null;
	}
	
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
	
	public static String saveParseResultByItem(List<String> matches) {
		String tempdir = IOUtils.mkdir();
		int n = matches.size();
		for (int i = 0; i < n; i++) {
			String text = matches.get(i);
			String filename = parsePublicName(text);
			if (filename == null) {
				filename = String.format("code%d.txt", i);
			} else {
				filename = filename + ".java";
			}
			filename = tempdir + "/" + filename;
			IOUtils.writeToFile(text, filename);
		}
		return tempdir;
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
