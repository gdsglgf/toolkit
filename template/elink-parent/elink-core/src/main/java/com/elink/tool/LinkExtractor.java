package com.elink.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.elink.util.IOUtils;

public class LinkExtractor {
	private static final Logger log = LogManager.getLogger(LinkExtractor.class);
	
	public static boolean isNotValid(String output) {
		File f = new File(output);
		if (f.exists() || f.isDirectory()) {
			return true;
		}
		f.mkdirs();
		return false;
	}

	public static void main(String[] args) throws Exception {
		// LinkExtractor <input> <output>
		if (args.length != 4) {
			System.out.println("Usage:LinkExtractor <input> <output> <start> <end>");
			System.exit(4);
		}
		
		String input = args[0];
		String output = args[1];
		
//		if (isNotValid(output)) {
//			System.err.println("output exists:" + output);
//			System.exit(1);
//		}
		
		int start = Integer.parseInt(args[2]);
		int end = Integer.parseInt(args[3]);
		if (start < 0 || end < 0 || start >= end ) {
			System.err.println("range[start, end) error!");
			System.exit(1);
		}
		
		log.debug(String.format("input:%s, output:%s, start:%d, end:%d", input, output, start, end));
		
		String line = null;
		for (int i = start; i < end; i++) {
			String filename = String.format("%s/part-m-%05d.bz2", input, i);
			String outFile = String.format("%s/link-%05d.txt", output, i);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile, true), "utf-8"));
			long startTime = System.nanoTime();
			try (BufferedReader reader = IOUtils.getBufferedReaderForCompressedFile(filename)) {
				while ((line = reader.readLine()) != null) {
					int j = line.indexOf("<url>");
					if (j > -1) {
						int k = line.indexOf("</url>");
						out.write(String.format("%s%n", line.substring(j + 5, k)));
					}
				}
			} catch (Exception e) {
				log.debug(String.format("Error: %s, %s", filename, e.getMessage()));
			}
			out.flush();
			out.close();
			long duration = System.nanoTime() - startTime;
			log.debug(String.format("%s, cost %d ns", filename, duration));
		}
	}

}
