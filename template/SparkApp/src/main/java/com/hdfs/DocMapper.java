package com.hdfs;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.log4j.Logger;

public class DocMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private static Logger log = Logger.getLogger(DocMapper.class);
	private static AtomicInteger counter = new AtomicInteger(0);
	
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String filepath = ((FileSplit)context.getInputSplit()).getPath().toString();
		word.set(filepath);
//		log.debug(filepath);
		context.write(word, one);
//		log.debug(value.toString());
		System.out.printf("%s, %d%n", filepath, counter.incrementAndGet());
	}
}
