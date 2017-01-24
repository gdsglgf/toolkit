package com.pojo;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class POJOCreatorView {
	private JFrame frame;
	private JEditorPane editorPane;
	private JTextArea textArea;

	public POJOCreatorView() {
		frame = new JFrame("POJO generator");
		frame.setSize(500, 600);
		frame.setLocation(550, 150);
		frame.setVisible(true);
//		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 27, 472, 155);
		frame.getContentPane().add(scrollPane);
		
		editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 273, 474, 267);
		frame.getContentPane().add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setTabSize(2);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> lines = Arrays.asList(editorPane.getText().split(POJOCreator.LINE_SEPARATOR));
				String output = POJOCreator.toPOJOString(POJOCreator.load(lines));
				textArea.setText(output);
			}
		});
		btnNewButton.setBounds(190, 213, 93, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	public static void main(String[] args) {
		Runnable r = new Runnable() {
            @Override
            public void run() {
            	new POJOCreatorView();
            }
        };

        EventQueue.invokeLater(r);
	}
}