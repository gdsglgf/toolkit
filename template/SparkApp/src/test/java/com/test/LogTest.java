package com.test;

import org.apache.log4j.Logger;

public class LogTest {
	public static final Logger log = Logger.getLogger(LogTest.class);

	public void log() {
			log.trace("trace info");
			log.debug("Debug info.");
			log.warn("Warn info");
			log.info("Info info");
			log.error("Error info");
			log.fatal("Fatal info");
	}

	public static void main(String[] args) {
		LogTest test = new LogTest();
		test.log();
	}
}
