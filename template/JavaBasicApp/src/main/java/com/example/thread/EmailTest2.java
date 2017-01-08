package com.example.thread;

import java.util.LinkedList;
import java.util.List;

class EmailList {
	private List<Email> list;
	private int index = 0;

	public EmailList(List<Email> list) {
		this.list = list;
	}

	synchronized public int getIndex() {
		return index++;
	}

	public Email getEmail() {
		int i = getIndex();
		if (i < list.size()) {
			return list.get(i);
		} else {
			return null;
		}
	}
}

class Run2 implements Runnable {
	private EmailList hander;

	public Run2(EmailList hander) {
		this.hander = hander;
	}

	public void run() {
		Email email = hander.getEmail();
		while (email != null) {
			System.out.println(Thread.currentThread().getName() + ":email--" + email.getUserName());
			email = hander.getEmail();
		}
	}

}

public class EmailTest2 {

	public static void main(String[] args) {
		LinkedList<Email> emailList = new LinkedList<Email>(); // 假设list中有1W条数据
		for (int i = 0; i < 10000; i++) {
			emailList.add(new Email(String.valueOf(i)));
		}

		EmailList hander = new EmailList(emailList);
		for (int i = 0; i < 4; i++) {
			Run2 run2 = new Run2(hander);

			new Thread(run2).start();
		}
	}

}
