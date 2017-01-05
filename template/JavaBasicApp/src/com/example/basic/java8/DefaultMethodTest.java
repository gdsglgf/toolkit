package com.example.basic.java8;

// java 8 interface default method
interface IService {
	public default void service(String msg) {
		System.out.println("showing ..." + msg);
	}
}

public class DefaultMethodTest {

	public static void main(String[] args) {
		new IService() {}.service("hello world");
	}

}
