package com.example.executor;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestScheduledThreadPoolExecutor {
	public static void main(String[] args) throws InterruptedException {
		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		exec.scheduleAtFixedRate(() -> {
			System.out.printf("------[%s]------%n", Thread.currentThread().getName());
		}, 1000, 5000, TimeUnit.MILLISECONDS);

		exec.scheduleAtFixedRate(() -> {
			System.out.println(Thread.currentThread().getName() + ": " + System.nanoTime());
		}, 1000, 2000, TimeUnit.MILLISECONDS);

		Thread.sleep(10000);
		exec.shutdown();
	}
}