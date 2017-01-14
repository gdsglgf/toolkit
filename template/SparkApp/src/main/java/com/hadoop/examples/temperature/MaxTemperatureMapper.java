package com.hadoop.examples.temperature;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTemperatureMapper extends Mapper<Object, Text, Text, IntWritable> {
	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		System.out.printf("[%s]->[%s]%n", key, value);
		String line = value.toString();
		String year = line.substring(15, 19);

		int airTemperature;
		if (line.charAt(25) == '+') {
			airTemperature = Integer.parseInt(line.substring(26, 30));
		} else {
			airTemperature = Integer.parseInt(line.substring(25, 30));
		}
		context.write(new Text(year), new IntWritable(airTemperature));
	}
}
