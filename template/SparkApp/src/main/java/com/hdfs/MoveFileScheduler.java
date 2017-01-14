package com.hdfs;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MoveFileScheduler {
	public static int counter = 0;
	public static boolean move(File srcFile, String destPath) {
		// Destination directory
		File dir = new File(destPath);

		// Move file to new directory
		String filename = String.format("link-%05d.txt", counter);
		counter++;
		boolean success = srcFile.renameTo(new File(dir, filename));
		System.out.printf("move file %s -> %s%n", srcFile.getName(), filename);

		return success;
	}

	public static void main(String[] args) {
		String srcFile = "logs/";
		String destPath = "links/";

		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(() -> {
			File src = new File(srcFile);
			Arrays.asList(src.listFiles()).stream().filter(f -> {
				String name = f.getName();
				return name.startsWith("info.log.");
			}).forEach((f) -> {
				move(f, destPath);
			});
		}, 0, 60, TimeUnit.MINUTES);
	}

}
