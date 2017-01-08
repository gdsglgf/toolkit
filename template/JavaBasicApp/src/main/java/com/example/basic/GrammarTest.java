package com.example.basic;

public class GrammarTest {
	public static void testContinueBreak() {
		for (int i = 0; i < 10; i++) {
			if (i == 3) {
				continue;
			} else if (i == 7) {
				break;
			}
			System.out.printf("%d ", i);	// 0 1 2 4 5 6 
		}
	}
	public static void main(String[] args) {
		testContinueBreak();
	}
}
