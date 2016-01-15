package com.tool.finder;

import javax.swing.JFrame;

import com.tool.finder.ui.FinderUI;

public class Main {

	public static void main(String[] args) {
		FinderUI ui = new FinderUI();
		ui.setVisible(true);
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
