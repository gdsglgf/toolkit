package com.example.log4j;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {
	private static final Logger log = LogManager.getLogger(Test.class);

	public void log() {
		for (int i = 0; i < 10; i++) {
			log.trace(i + "--->" + new Date());
			log.debug("Debug info.");
			log.warn("Warn info");
			log.info("Info info");
			log.error("Error info");
			log.fatal("Fatal info");
			try {
				Thread.sleep(30 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Test test = new Test();
		test.log();
	}
}