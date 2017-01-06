package com.gui.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class IOUtils {
	public static String createUniqueDIR() {
		return String.format("temp%d", System.currentTimeMillis());
	}
	
	public static boolean mkdir(String path) {
		File f = new File(path);
		if (f.exists()) {
			return false;
		}
		return f.mkdirs();
	}
	
	public static String mkdir() {
		String dir = String.format("temp%d", System.currentTimeMillis());
		mkdir(dir);
		return dir;
	}
	
	private static void writeToFile(String text, String path, OpenOption... options) {
		Path targetPath = Paths.get(path);
		byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
		try {
			Files.write(targetPath, bytes, options);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写字符串到文件
	 * @param text  要保存的字符串
	 * @param path  文件路径名
	 */
	public static void appendToFile(String text, String path) {
		// append to an existing file, create file if it doesn't initially exist
		OpenOption[] ops = new OpenOption[] {StandardOpenOption.CREATE, StandardOpenOption.APPEND};
		writeToFile(text, path, ops);
	}
	
	public static String writeToFile(String text, String path) {
		String result = null;
		Path targetPath = Paths.get(path);
		byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
		try {
			Files.write(targetPath, bytes, StandardOpenOption.CREATE_NEW);
			result = String.format("Save file succeed: %s", path);
		} catch (Exception e) {
			result = String.format("File already exists: %s", path);
		}
		return result;
	}
	
	public static String writeToFile(List<String> lines, String path) {
		StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			sb.append(line);
			sb.append("\r\n\r\n");
		}
		return writeToFile(sb.toString(), path);
	}
	
	public static String streamToString(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[2048];
		int readBytes = 0;
		StringBuilder sb = new StringBuilder();
		while ((readBytes = inputStream.read(buffer)) > 0) {
			sb.append(new String(buffer, 0, readBytes));
		}
		inputStream.close();
		return sb.toString();
	}
}
