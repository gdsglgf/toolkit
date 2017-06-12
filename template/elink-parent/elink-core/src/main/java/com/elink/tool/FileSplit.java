package com.elink.tool;

import com.elink.util.IConsumer;
import com.elink.util.IOUtils;

public class FileSplit implements IConsumer {
	@Override
	public void consume(String html, int rank) {
		String filename = String.format("E:/sogout/page/file-%d.html", rank);
		IOUtils.appendToFile(html, filename);
	}

	public static void main(String[] args) {
		IOUtils.benchmark("H:/sogout_data.3.comp/part-m-00000.bz2", new FileSplit());
	}

}
