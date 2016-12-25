package com.gui;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Demo {
	String[] fontsize = { "12", "14", "16" };
	String defaultMessage = "请选择或直接输入文字大小!";

	private JFrame frame;

	public Demo() {
		frame = new JFrame("Simple Web Spider");
		frame.setSize(500, 600);
		frame.setLocation(550, 150);
		frame.getContentPane().setLayout(null);

		JComboBox comboBox = new JComboBox(fontsize);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboBox.getSelectedItem());
			}
		});
		comboBox.setBounds(20, 10, 177, 45);
		comboBox.setBorder(BorderFactory.createTitledBorder("请选择你要的文字大小:"));
		comboBox.setEditable(true);
		ComboBoxEditor editor = comboBox.getEditor();
		comboBox.configureEditor(editor, fontsize[0]);
		frame.getContentPane().add(comboBox);
//
//		JFileChooser fileChooser = new JFileChooser();
//		fileChooser.setDialogTitle("Choose a File");
//		fileChooser.setMultiSelectionEnabled(false);
//	    fileChooser.setBounds(10, 35, 550, 500 );
//	    fileChooser.setVisible(true);
//	    frame.getContentPane().add(fileChooser);

		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Demo();
	}
}
