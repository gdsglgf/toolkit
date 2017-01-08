package com.example.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {

	public static void main(String[] args) throws InterruptedException {
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(() -> {
			System.out.println("run service...");
		}, 0, 600, TimeUnit.MILLISECONDS);

		Thread.sleep(5000);
		executorService.shutdown();
	}

}
