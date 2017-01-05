package com.example.basic.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Java8 lambda表达式10个示例
// http://www.importnew.com/16436.html
// Stream语法详解(带图展示)
// http://www.importnew.com/20331.html

public abstract class LambdaTest {
	public static void interfaceDemo() {
		// Java 8之前：
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Before Java8, too much code for too little to do");
			}
		}).start();

		// Java 8方式：
		new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();
	}

	public static void forEachDemo() {
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		// Java 8之前：
		for (String feature : features) {
			System.out.println(feature);
		}

		// Java 8之后：
		features.forEach(n -> System.out.println(n));

		// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
		// 看起来像C++的作用域解析运算符
		features.forEach(System.out::println);
	}

	public static void filter(List<String> names, Predicate<String> predicate) {
		names.stream().filter((name) -> (predicate.test(name))).forEach((name) -> {
			System.out.printf(name + " ");
		});
		System.out.println();
		// this is better
		names.stream().filter(predicate).forEach((name) -> {
			System.out.printf(name + " ");
		});
		System.out.println();
	}

	public static void filterDemo() {
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

		System.out.println("Languages which starts with J :");
		filter(languages, (s) -> s.startsWith("J"));

		System.out.println("Languages which ends with a ");
		filter(languages, (s) -> s.endsWith("a"));

		System.out.println("Print all languages :");
		filter(languages, (s) -> true);

		System.out.println("Print no language : ");
		filter(languages, (s) -> false);

		System.out.println("Print language whose length greater than 4:");
		filter(languages, (s) -> s.length() > 4);

		// 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
		// 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
		Predicate<String> startsWithJ = (n) -> n.startsWith("J");
		Predicate<String> fourLetterLong = (n) -> n.length() == 4;
		languages.stream().filter(startsWithJ.and(fourLetterLong))
				.forEach((n) -> System.out.println("nName, which starts with 'J' and four letter long is : " + n));
	}

	public static void mapReduceDemo() {
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		// 不使用lambda表达式为每个订单加上12%的税
		double total = 0;
		for (Integer cost : costBeforeTax) {
			double price = cost + .12 * cost;
			System.out.println(price);
			total += price;
		}
		System.out.println("Total : " + total);

		// 使用lambda表达式
		double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost).peek(System.out::println)
				.reduce((sum, cost) -> sum + cost).get();
		System.out.println("Total : " + bill);
	}

	public static void collectDemo() {
		List<String> strList = Arrays.asList("abc", "", "bcd", "", "defg", "jk");
		// 创建一个字符串列表，每个字符串长度大于2
		List<String> filtered = strList.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
		System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);

		// 将字符串换成大写并用逗号链接起来
		List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
		String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
		System.out.println(G7Countries);
	}

	public static void distinctDemo() {
		// 用所有不同的数字创建一个正方形列表
		List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
		List<Integer> distinct = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
		System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
	}

	public static void summaryDemo() {
		// 获取数字的个数、最小值、最大值、总和以及平均值
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("Highest prime number in List : " + stats.getMax());
		System.out.println("Lowest prime number in List : " + stats.getMin());
		System.out.println("Sum of all prime numbers : " + stats.getSum());
		System.out.println("Average of all prime numbers : " + stats.getAverage());
	}

	// http://blog.csdn.net/zhou85xin/article/details/52171190
	public static void streamDemo() {
		List<Double> list = new ArrayList<Double>();
		for (int i = 0; i < 10000000; i++) {
			double d = Math.random() * 1000;
			list.add(d);
		}
		long start = System.nanoTime();
		list = list.stream().sequential().sorted().collect(Collectors.toList());
		long end = System.nanoTime();
		list = list.stream().parallel().sorted().collect(Collectors.toList());
		long parTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - end);// 得到并行排序所用的时间
		long seqTime = TimeUnit.NANOSECONDS.toMillis(end - start);// 得到串行排序所用的时间
		System.out.println(String.format("并行时间:%dms, 串行时间:%dms", parTime, seqTime));
	}
	
	// http://blog.csdn.net/sunjin9418/article/details/53143588
	public static long parallelSum(long n){
	    return Stream.iterate(1L, i -> i +1).limit(n).parallel().reduce(0L,Long::sum);
	}

	public static void main(String[] args) {
		interfaceDemo();
		forEachDemo();
		filterDemo();
		mapReduceDemo();
		collectDemo();
		distinctDemo();
		summaryDemo();
		streamDemo();
		System.out.println(parallelSum(100));
	}

}
