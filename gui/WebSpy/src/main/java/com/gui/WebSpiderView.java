package com.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.gui.parser.CodeParser;
import com.gui.parser.ParserFactory;
import com.gui.util.HttpRequest;
import com.gui.util.IOUtils;

public class WebSpiderView {
	private JFrame frame;
	private JTextField urlTextField;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	JComboBox comboBox;
	private JTextArea resultTextArea;
	private JScrollPane scrollPane_1;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	
	// data
	private String[] demos = {"RE=(<a.*?>)", "TAG=textarea", "name=code", "class=cnblogs_code"};
	private String html;
	private List<String> matches;

	public WebSpiderView() {
		frame = new JFrame("Simple Web Spider");
		frame.setSize(500, 600);
		frame.setLocation(550, 150);

		JButton btnNewButton = new JButton("GO");
		btnNewButton.setBounds(367, 30, 63, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = urlTextField.getText();
				System.out.printf("input:[%s]\n", url);
				html = HttpRequest.sendGet(url);
				textArea.setText(html);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewButton);

		urlTextField = new JTextField("www.helloworld.com");
		urlTextField.setBounds(45, 30, 306, 25);
		frame.getContentPane().add(urlTextField);
		urlTextField.setColumns(50);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 70, 385, 204);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setRows(10);
		textArea.setColumns(10);

		comboBox = new JComboBox(demos);
		comboBox.setEditable(true);
		ComboBoxEditor editor = comboBox.getEditor();
		comboBox.configureEditor(editor, demos[0]);
		comboBox.setBounds(45, 290, 306, 25);
		frame.getContentPane().add(comboBox);

		JButton btnMatch = new JButton("Match");
		btnMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (html == null) {
					html = textArea.getText();
				}
				if (html == null) {
					JOptionPane.showMessageDialog(frame, "The html is empty.", null, JOptionPane.WARNING_MESSAGE);
					return;
				}
				String reg = (String) comboBox.getSelectedItem();
				resultTextArea.setText(String.format("Pattern[%s]\n", reg));
				matches = ParserFactory.parse(html, reg);
				int n = matches.size();
				String text = null;
				for (int i = 0; i < n; i++) {
					text = String.format("%sresult %d:\n%s\n", resultTextArea.getText(), i + 1, matches.get(i));
					resultTextArea.setText(text);
				}
				text = String.format("%sMatching %d result(s)\n", resultTextArea.getText(), n);
				resultTextArea.setText(text);
			}
		});
		btnMatch.setBounds(361, 291, 69, 23);
		frame.getContentPane().add(btnMatch);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(45, 330, 385, 221);
		frame.getContentPane().add(scrollPane_1);

		resultTextArea = new JTextArea();
		resultTextArea.setRows(11);
		resultTextArea.setColumns(10);
		scrollPane_1.setViewportView(resultTextArea);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 494, 20);
		frame.getContentPane().add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSaveText = new JMenuItem("Save Code");
		mntmSaveText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = (String) JOptionPane.showInputDialog("Please enter file path:", "code.html");
				System.out.println(path);
				if (path == null || path.trim().equals("")) {
					JOptionPane.showMessageDialog(frame, "File path is empty!", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String msg = IOUtils.writeToFile(html, path);
				JOptionPane.showMessageDialog(frame, msg, null, JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnFile.add(mntmSaveText);

		JMenuItem mntmSaveResult = new JMenuItem("Save Result");
		mntmSaveResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("----------------");
				String path = (String) JOptionPane.showInputDialog("Please enter file path:", "code.html");
				if (path == null || path.trim().equals("")) {
					JOptionPane.showMessageDialog(frame, "File path is empty!", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				String msg = IOUtils.writeToFile(matches, path);
				JOptionPane.showMessageDialog(frame, msg, null, JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnFile.add(mntmSaveResult);
		
		JMenuItem mntmParseResult = new JMenuItem("Parse Result");
		mntmParseResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keys = (String) JOptionPane.showInputDialog("Please enter keys(Comma Separated):", "</div>,</form>");
				if (keys == null) {
					return;
				}
				String[] key = keys.split(",");
				CodeParser.saveParseResult(matches, key);
				JOptionPane.showMessageDialog(frame, "Save Parse Result done.", null, JOptionPane.PLAIN_MESSAGE);
			}
		});
		mnFile.add(mntmParseResult);
		
		JMenuItem mntmSaveByItem = new JMenuItem("Save by Item");
//		mntmSaveByItem.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		
		mntmSaveByItem.addActionListener(event -> {
			String dir = CodeParser.saveParseResultByItem(matches);
			JOptionPane.showMessageDialog(frame, "Save code in " + dir, null, JOptionPane.PLAIN_MESSAGE);
		});
		mnFile.add(mntmSaveByItem);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				int option = JOptionPane.showConfirmDialog(frame, "确实要退出吗", "退出确认", JOptionPane.YES_NO_CANCEL_OPTION);
//				System.out.println(option);
			
				JOptionPane.showMessageDialog(frame, "This is a simple Web Spider!!!",
						  "About Me", JOptionPane.INFORMATION_MESSAGE);
				
//				String result = (String) JOptionPane.showInputDialog(frame, 
//						"请选择一项运动项目", "这是运动项目选择对话框",
//						JOptionPane.QUESTION_MESSAGE, null, 
//						new Object[] { "踢足球", "打篮球", "跑步", "跳绳" }, "跑步");
//				System.out.println(result);
			}
		});
		mnHelp.add(mntmAbout);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public List<String> getMatches() {
		return matches;
	}

	public static void main(String[] args) {
		new WebSpiderView();
	}
}
