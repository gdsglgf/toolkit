package com.hadoop.examples.point;

import java.io.IOException;

import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.LineReader;

public class Point3DinputFormat extends FileInputFormat<Text, Point3D> {

	@Override
	protected boolean isSplitable(JobContext context, Path filename) {
		return false;
	}

	@Override
	public RecordReader<Text, Point3D> createRecordReader(InputSplit inputsplit, TaskAttemptContext context)
			throws IOException, InterruptedException {
		return new objPosRecordReader();
	}

	public static class objPosRecordReader extends RecordReader<Text, Point3D> {

		public LineReader in;
		public Text lineKey;
		public Point3D lineValue;
		public StringTokenizer token = null;

		public Text line;

		@Override
		public void close() throws IOException {
			
		}

		@Override
		public Text getCurrentKey() throws IOException, InterruptedException {
			// lineKey.set(token.nextToken());
			return lineKey;
		}

		@Override
		public Point3D getCurrentValue() throws IOException, InterruptedException {
			return lineValue;
		}

		@Override
		public float getProgress() throws IOException, InterruptedException {
			return 0;
		}

		@Override
		public void initialize(InputSplit input, TaskAttemptContext context) throws IOException, InterruptedException {
			FileSplit split = (FileSplit) input;
			Configuration job = context.getConfiguration();
			Path file = split.getPath();
			FileSystem fs = file.getFileSystem(job);

			FSDataInputStream filein = fs.open(file);
			in = new LineReader(filein, job);

			line = new Text();
			lineKey = new Text();
			lineValue = new Point3D();
		}

		@Override
		public boolean nextKeyValue() throws IOException, InterruptedException {
			int linesize = in.readLine(line);
			if (linesize == 0)
				return false;

			String[] pieces = line.toString().split(",");
			if (pieces.length != 4) {
				throw new IOException("Invalid record received");
			}

			// try to parse floating point components of value
			float fx, fy, fz;
			try {
				fx = Float.parseFloat(pieces[1].trim());
				fy = Float.parseFloat(pieces[2].trim());
				fz = Float.parseFloat(pieces[3].trim());
			} catch (NumberFormatException nfe) {
				throw new IOException("Error parsing floating poing value in record");
			}
			lineKey.set(pieces[0]);

			lineValue.set(fx, fy, fz);
			
			System.out.printf("In InputFormat:[%s]->[%s]%n", lineKey, lineValue);
			
			return true;
		}
	}
}