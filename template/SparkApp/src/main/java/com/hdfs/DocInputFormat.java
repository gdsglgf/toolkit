package com.hdfs;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

public class DocInputFormat extends TextInputFormat {
	private static final String RECORD_DELIMITER = "</doc>";

	@Override
	public RecordReader<LongWritable, Text> createRecordReader(InputSplit split, TaskAttemptContext tac) {
		byte[] recordDelimiterBytes = RECORD_DELIMITER.getBytes();
		return new LineRecordReader(recordDelimiterBytes);
	}

	@Override
	public boolean isSplitable(JobContext context, Path file) {
		CompressionCodec codec = new CompressionCodecFactory(context.getConfiguration()).getCodec(file);
		return codec == null;
	}
}