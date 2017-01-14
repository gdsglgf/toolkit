package com.hadoop.examples.temperature;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MaxTemperature {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("mapred.textoutputformat.separator", ";");
		Job job = Job.getInstance(conf);
		job.setJobName("Max temperature");
		job.setJarByClass(MaxTemperature.class);
		FileInputFormat.addInputPath(job, new Path("data/weather.txt"));
		FileOutputFormat.setOutputPath(job, new Path("output6"));
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		job.setMapperClass(MaxTemperatureMapper.class);
		job.setCombinerClass(MaxTemperatureReducer.class);
		job.setReducerClass(MaxTemperatureReducer.class);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}