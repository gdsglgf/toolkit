package com.examples;

import java.util.Date;

import org.apache.log4j.Logger;

public class Log4jTest {
	private static Logger log = Logger.getLogger(Log4jTest.class);

	public static void log() {
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
		log();
	}
}