package org.apache.hadoop.examples;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MaxTemperatureMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		System.out.printf("[%s]->[%s]%n", key, value);
		String line = value.toString();
		String year = line.substring(15, 19);

		int airTemperature;
		if (line.charAt(25) == '+') {
			airTemperature = Integer.parseInt(line.substring(26, 30));
		} else {
			airTemperature = Integer.parseInt(line.substring(25, 30));
		}
		output.collect(new Text(year), new IntWritable(airTemperature));
	}
}