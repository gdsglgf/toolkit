package com.hadoop.examples.point;

import java.io.IOException;
import java.net.URI;

import javax.xml.soap.Text;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * desc:Custom Data Types <code>TestPoint3DInputFormat</code>
 * 
 * 
 */
public class TestPoint3DInputFormat {
	/**
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		Job job = Job.getInstance();
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(args[1]), conf);
		fs.delete(new Path(args[1]), true);
		job.setJobName("测试MyInputFormat程序。。。。。");
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		job.setInputFormatClass(Point3DinputFormat.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Point3D.class);
		job.setMapperClass(Point3DMapper.class);
		job.setNumReduceTasks(0);
		job.waitForCompletion(false);
	}
}