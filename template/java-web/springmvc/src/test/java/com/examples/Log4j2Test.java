package com.examples;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Test {
	private static final Logger log = LogManager.getLogger(Log4j2Test.class.getName());

	public void log() {
		for (int i = 0; i < 10; i++) {
			log.trace(i + "--->" + new Date());
			log.debug("Debug info.");
			log.info("Info info");
			log.warn("Warn info");
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
		Log4j2Test test = new Log4j2Test();
		test.log();
	}
}