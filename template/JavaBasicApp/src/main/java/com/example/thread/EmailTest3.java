package com.example.thread;

import java.util.ArrayList;
import java.util.List;

public class EmailTest3 {

	/**
	 * 多线程处理list
	 * 
	 * @param data  数据list
	 * @param threadNum  线程数
	 */
	public void handleList(List<String> data, int threadNum) {
		for (int i = 0; i < threadNum; i++) {
			HandleThread thread = new HandleThread("线程[" + (i + 1) + "]", data, i, threadNum);
			thread.start();
		}
	}

	class HandleThread extends Thread {
		private String threadName;
		private List<String> data;
		private int start;
		private int threadNum;

		public HandleThread(String threadName, List<String> data, int start, int threadNum) {
			this.threadName = threadName;
			this.data = data;
			this.start = start;
			this.threadNum = threadNum;
		}

		public void run() {
			int length = data.size();
			for (int i = start; i < length; i += threadNum) {
				System.out.printf("%s: %d --> %s%n", threadName, i, data.get(i));
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					break;
				}
			}
		}

	}

	public static void main(String[] args) {
		EmailTest3 test = new EmailTest3();
		// 准备数据
		List<String> data = new ArrayList<String>();
		for (int i = 0; i < 5000; i++) {
			data.add("item" + i);
		}
		test.handleList(data, 10);
	}
}
