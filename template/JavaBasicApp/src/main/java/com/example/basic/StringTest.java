package com.example.basic;

import java.util.Arrays;

public class StringTest {
	public static void main(String[] args) {
		String data = "just for  	fun";
		System.out.println(Arrays.toString(data.split(" ")));		// [just, for, , 	fun]
		System.out.println(Arrays.toString(data.split("\\s+")));	// [just, for, fun]
	}
}
