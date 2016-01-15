package com.tool.finder.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	public static String CRLF = "\r\n";
	
	public static String randomFilename() {
		return String.format("history-%s.txt", DateUtil.time14());
	}

	/**
	 * @param path
	 *            文件路径
	 * @return 文件是否存在
	 */
	public static boolean exists(String path) {
		if (path == null) {
			return false;
		}
		File file = new File(path);
		return file.exists();
	}

	public static String export(String path, String key, String content,
			String result) {
		String filename = randomFilename();
		try {
			FileWriter fw = new FileWriter(filename);
			fw.append("path:" + path);
			fw.append(CRLF);
			fw.append("key:" + key);
			fw.append(CRLF);
			fw.append("------result------");
			fw.append(CRLF);
			fw.append(result);
			fw.append(CRLF);
			fw.append("------content------");
			fw.append(CRLF);
			fw.append(content);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return filename;
	}

	public static void save(String filename, String content) {
		try {
			FileWriter fw = new FileWriter(filename);
			fw.append(content);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
