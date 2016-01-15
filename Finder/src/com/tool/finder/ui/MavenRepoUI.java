package com.tool.finder.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.tool.finder.thread.MavenRepoFinder;
import com.tool.finder.util.FileUtil;

public class MavenRepoUI extends BaseUI {

	public MavenRepoUI() {
		super();
		this.setTitle("Maven Local Repository Finder");
		init();
		Thread t = new Thread(new MavenRepoFinder(contentTextArea));
		t.start();
		addSearchingListener();
	}

	/**
	 * 初始化各组件位置
	 */
	private void init() {
		setLayout(null);

		// Add directory row
		JLabel keyLabel = new JLabel("Keywords");
		keyLabel.setBounds(50, 20, 100, 30);
		add(keyLabel);

		keyTextField.setBounds(150, 20, 400, 30);
		add(keyTextField);

		searchButton.setBounds(600, 20, 100, 30);
		add(searchButton);

		// add result row
		contentTextArea.setLineWrap(true);
		JScrollPane contentScroll = new JScrollPane(contentTextArea);
		contentScroll.setBounds(50, 70, 650, 220);
		add(contentScroll);

		resultTextArea.setLineWrap(true);
		JScrollPane resultScroll = new JScrollPane(resultTextArea);
		resultScroll.setBounds(50, 300, 650, 220);
		add(resultScroll);
	}

	private void addSearchingListener() {
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String key = keyTextField.getText();
				resultTextArea.setText("");
				int length = MavenRepoFinder.repos.size();
				for (int i = 0; i < length; i++) {
					String jar = MavenRepoFinder.repos.get(i);
					if (jar.contains(key) || jar.contains(key.toLowerCase())) {
						resultTextArea.append(jar);
						resultTextArea.append(FileUtil.CRLF);
					}
				}
			}
		});
	}

	private static final long serialVersionUID = 7538389786784927123L;

	private JTextField keyTextField = new JTextField();
	private JButton searchButton = new JButton("Searching");
	private JTextArea contentTextArea = new JTextArea();
	private JTextArea resultTextArea = new JTextArea();
}
