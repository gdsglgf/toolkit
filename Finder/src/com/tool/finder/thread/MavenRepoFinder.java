package com.tool.finder.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JTextArea;

import com.tool.finder.util.FileUtil;

public class MavenRepoFinder implements Runnable {
	public MavenRepoFinder(JTextArea contentTextArea) {
		this.contentTextArea = contentTextArea;
	}

	@Override
	public void run() {
		repos = new ArrayList<String>();
		String home = System.getProperty("user.home");
		String mavenHome = home + "/.m2/repository/";
		int length = mavenHome.length();
		File file = new File(mavenHome);
		Queue<File> fileQueue = new LinkedBlockingQueue<File>();
		fileQueue.add(file);
		while (fileQueue.size() > 0) {
			File f = fileQueue.poll();
			if (f.isDirectory()) {
				File[] files = f.listFiles();
				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						fileQueue.add(files[i]);
					}
				}
			} else {
				String filename = f.getName();
				if (filename.endsWith(".jar") && !filename.endsWith("sources.jar")) {
					String fullPath = f.getAbsolutePath();
					String jar = fullPath.substring(length);
					repos.add(jar);
					contentTextArea.append(jar);
					contentTextArea.append(FileUtil.CRLF);
					System.out.println(jar);
				}
			}
		}
	}

	private JTextArea contentTextArea;
	public static List<String> repos;
}
