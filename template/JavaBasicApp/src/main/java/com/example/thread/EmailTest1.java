package com.example.thread;

import java.util.LinkedList;

class Run1 implements Runnable {
	LinkedList<Email> emailList;

	public Run1(LinkedList<Email> emailList) {
		this.emailList = emailList;
	}

	@Override
	public void run() {
		Email email = null;
		while (true) {
			synchronized (emailList) {
				if (!emailList.isEmpty())
					email = emailList.removeFirst();
				else
					break;
			}
			System.out.println(Thread.currentThread().getName() + ":email--" + email.getUserName());
		}
	}
}

public class EmailTest1 {

	public static void main(String[] args) {
		LinkedList<Email> emailList = new LinkedList<Email>();// 假设list中有1W条数据
		for (int i = 0; i < 10000; i++) {
			emailList.add(new Email(String.valueOf(i)));
		}

		for (int i = 0; i < 4; i++) {
			new Thread(new Run1(emailList)).start();
		}
	}
}