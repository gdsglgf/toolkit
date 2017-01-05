package com.example.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
http://www.mkyong.com/java8/java-8-stream-read-a-file-line-by-line/
*/
public class IOUtils {
	/**
	 * 读文件到字符串
	 * @param path 文件路径名
	 * @return 文件内容字符串
	 */
	public static String fileToString(String path) {
		try {
			return new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void appendToString(String path, String text) {
		try {
			boolean append = true;
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, append), "utf-8"));
			out.write(text);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String path = "data.txt";
//		appendToString(path, "<^\"U\"^>\n");
//		appendToString(path, "<^\\\"U\\\"^>\n");
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String line = null;
			while (null != (line = in.readLine())) {
				System.out.println(line);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
