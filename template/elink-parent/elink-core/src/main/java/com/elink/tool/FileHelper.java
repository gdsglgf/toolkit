package com.elink.tool;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileHelper {
	private static final Logger log = LogManager.getLogger(FileHelper.class);
	private static AtomicInteger index = new AtomicInteger(0);
	private static int end = 4096;
	
	public static void setIndex(int newValue) {
		index.set(newValue);
	}
	
	public static void setEnd(int newEnd) {
		end = newEnd;
	}
	
	public static String nextFile(String path) {
		return String.format("%s/part-m-%05d.bz2", path, index.getAndIncrement());
	}
	
	public static int getIndex() {
		return index.get();
	}
	
	public static void test() {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while(FileHelper.getIndex() < end) {
					System.out.printf("%s:%s%n", Thread.currentThread().getName(), FileHelper.nextFile("test"));
				}
			}).start();
		}
	}
	
	public static void main(String[] args) {
		if(args.length != 4) {
			System.err.println("Usage: FileHelper <data-path> <startIndex> <endIndex> <nthreads>");
			System.err.println("eg. FileHelper input 0 4096 4");
			System.exit(2);
		}
		
		String path = args[0];
		int start = Integer.parseInt(args[1]);
		int end = Integer.parseInt(args[2]);
		if (start < 0 || end < 0 || start > end ) {
			System.err.println("range(start, end) error!");
			System.exit(1);
		}
		int nthreads = Integer.parseInt(args[3]);
		if (nthreads <= 0) {
			System.err.println("nthreads must greater than zero!");
			System.exit(1);
		}
		
		FileHelper.setIndex(start);
		FileHelper.setEnd(end);
		
		
		for (int i = 0; i < nthreads; i++) {
			new Thread(() -> {
				log.debug("start counter...");
				while(FileHelper.getIndex() < end) {
					String filename = FileHelper.nextFile(path);
					Counter.countOne(filename);
				}
				log.debug("end counter...");
			}).start();
		}
	}
}
