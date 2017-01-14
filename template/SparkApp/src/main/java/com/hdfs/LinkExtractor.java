package com.hdfs;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

public class LinkExtractor {
	public static final Logger log = Logger.getLogger(LinkExtractor.class);

	public static class LinkMapper extends Mapper<Object, Text, Text, Text> {
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			String line = value.toString();

			int i = line.indexOf("<url>");
			if (i > -1) {
				int j = line.indexOf("</url>");
				log.info(line.substring(i + 5, j));
			}
		}
	}

	public static class LinkReducer extends Reducer<Text, Text, Text, Text> {
		public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
//			for (Text val : values) {
//
//			}
//			 context.write(key, result);	// for output
		}
	}

	public static void deleteFile(File element) {
		if (element.isDirectory()) {
			for (File sub : element.listFiles()) {
				deleteFile(sub);
			}
		}
		element.delete();
	}

	public static void main(String[] args) throws Exception {
		String output = "output";
		deleteFile(new File(output));

		long start = System.nanoTime();
		Job job = Job.getInstance();
		job.setJarByClass(FileCount.class);
		job.setJobName("File count");

		String input = "data/docs.txt";

		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
		job.setMapperClass(LinkMapper.class);

		job.waitForCompletion(true);

		long duration = System.nanoTime() - start;

		log.debug(String.format("%s, cost %d ns", input, duration));
		System.exit(0);
	}

}
