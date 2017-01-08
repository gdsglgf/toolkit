package com.example.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleThreadExecutor {
	public static void main(String[] args) {
		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newSingleThreadExecutor();
		// 将线程放入池中进行执行
		for (int i = 0; i < 5; i++) {
			pool.execute(() -> {
				System.out.println(Thread.currentThread().getName() + "正在执行。。。");
			});
		}
		// 关闭线程池
		pool.shutdown();
	}
}
