package com.test;

import java.io.File;
import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class WordCount {

	public static void main(String[] args) {
		String filename = args[0];
		SparkConf conf = new SparkConf().setAppName("Simple Application");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> textFile = sc.textFile(filename).cache();
		long lineCounts = textFile.count();
		long wordCounts = textFile.map(line -> line.split("\\s+").length).reduce((a, b) -> a + b);
		System.out.println(String.format("lineCounts:%d, wordCounts:%d", lineCounts, wordCounts));

		JavaRDD<String> lines = sc.textFile("data.txt");
		JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
		JavaPairRDD<String, Integer> counts = words.mapToPair(w -> new Tuple2<String, Integer>(w, 1))
				.reduceByKey((x, y) -> x + y).sortByKey();
		
		String path = new File(".").getAbsolutePath();
		counts.coalesce(1, true).saveAsTextFile(path + "/counts");
		
		counts.foreach(data -> {
	        System.out.println(data._1()+"-"+data._2());
	    });

		sc.stop();
		sc.close();
	}

}
