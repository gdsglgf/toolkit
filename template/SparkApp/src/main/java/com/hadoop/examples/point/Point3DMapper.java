package com.hadoop.examples.point;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Point3DMapper extends Mapper<Text, Point3D, Text, Point3D> {
	protected void map(Text key, Point3D value, Context context) throws IOException, InterruptedException {
		System.out.printf("In mapper:[%s]->[%s]%n", key, value);
		context.write(key, value);
	}
}
