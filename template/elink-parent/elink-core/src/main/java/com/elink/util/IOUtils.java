package com.elink.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

public class IOUtils {
	/**
	 * 获取压缩文件的BufferedReader
	 * @param fileIn  文件路径名
	 * @return
	 */
	public static BufferedReader getBufferedReaderForCompressedFile(String fileIn) {
		BufferedReader br2 = null;
		try {
			FileInputStream fin = new FileInputStream(fileIn);
			BufferedInputStream bis = new BufferedInputStream(fin);
			CompressorInputStream input = new CompressorStreamFactory().createCompressorInputStream(bis);
			br2 = new BufferedReader(new InputStreamReader(input));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return br2;
	}
	
	/**
	 * 
	 * @param filename  文件路径名
	 * @param consumer    文件内容消费者
	 * @return   文件的个数
	 */
	public static int split(String filename, IConsumer consumer) {
		int cnt = 0;
		String line = null;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = getBufferedReaderForCompressedFile(filename)) {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				if (line.contains("</doc>")) {
					cnt++;
					consumer.consume(sb.toString(), cnt);
					sb = new StringBuilder();
				}
			}
		} catch (IOException e) {
//			e.printStackTrace();  // when EOF java.io.IOException: bad block header
		}
		return cnt;
	}
	
	public static void benchmark(String filename, IConsumer consumer) {
		System.out.printf("%s: start benchmark, file:%s, consumer:%s%n", DateUtils.now(), filename, consumer.getClass().getName());
		long start = System.nanoTime();
		int cnt = split(filename, consumer);
		long end = System.nanoTime();
		long duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		String message = String.format("%s: %s, total: %d files, cost %d ms, %.1f files/second, %.1f ms/file", 
				DateUtils.now(), filename, cnt, duration,  cnt / (duration / 1000.0), duration * 1.0 / cnt);
		System.out.println(message);
	}
	
	public static void countFileBenchmark(String filename) {
		long start = System.nanoTime();
		
		int cnt = 0;
		String line = null;
		try (BufferedReader reader = getBufferedReaderForCompressedFile(filename)) {
			while ((line = reader.readLine()) != null) {
				if (line.contains("<doc>")) {
					cnt++;
				}
			}
		} catch (IOException e) {

		}
		
		long end = System.nanoTime();
		long duration = TimeUnit.NANOSECONDS.toMillis(end - start);
		String message = String.format("%s: %s, total: %d files, cost %d ms, %.1f files/second, %.1f ms/file", 
				DateUtils.now(), filename, cnt, duration,  cnt / (duration / 1000.0), duration * 1.0 / cnt);
		System.out.println(message);
	}
	
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
	
	/**
	 * 读文件到字符串
	 * @param file 要读取的文件
	 * @return  文件内容字符串
	 */
	public static String toString(File file) {
		return fileToString(file.getAbsolutePath());
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
	public static void writeToFile(String text, String path) {
		// StandardOpenOption.CREATE ---  Create a new file if it does not exist. If the file already exists, it will modify the file.
		// StandardOpenOption.CREATE_NEW  --- Create a new file, failing if the file already exists.
		writeToFile(text, path, StandardOpenOption.CREATE_NEW);
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
}
