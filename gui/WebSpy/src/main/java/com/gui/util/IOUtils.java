package com.gui.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class IOUtils {
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
