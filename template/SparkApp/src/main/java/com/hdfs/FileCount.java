package com.hdfs;

import java.util.concurrent.TimeUnit;

import org.apache.hadoop.examples.WordCount.IntSumReducer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FileCount {

	public static void main(String[] args) throws Exception {
		long start = System.nanoTime();
		Job job = Job.getInstance();
		job.setJarByClass(FileCount.class);
		job.setJobName("File count");
		
		String input = "data/docs.txt";
		String output = "output";
		FileInputFormat.addInputPath(job, new Path(input));
		FileOutputFormat.setOutputPath(job, new Path(output));
		job.setInputFormatClass(DocInputFormat.class);
		job.setMapperClass(DocMapper.class);
		job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(IntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		boolean status = job.waitForCompletion(true);
		
		long duration = System.nanoTime() - start;
		
		System.out.printf("Cost %d ms", TimeUnit.NANOSECONDS.toMillis(duration));
		System.exit(status ? 0 : 1);
	}

}
