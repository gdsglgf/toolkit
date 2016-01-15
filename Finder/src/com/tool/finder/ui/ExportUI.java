package com.tool.finder.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.tool.finder.util.FileUtil;

public class ExportUI extends BaseUI {
	public ExportUI() {
		super();
		this.setTitle("Export");
		init();
		setDefaultValue();
		addListener();
	}
	
	public ExportUI(String path, String key, String content, String result) {
		this();
		contentTextArea.append("path:" + path);
		contentTextArea.append(FileUtil.CRLF);
		contentTextArea.append("key:" + key);
		contentTextArea.append(FileUtil.CRLF);
		contentTextArea.append("------result------");
		contentTextArea.append(FileUtil.CRLF);
		contentTextArea.append(result);
		contentTextArea.append(FileUtil.CRLF);
		contentTextArea.append("------content------");
		contentTextArea.append(FileUtil.CRLF);
		contentTextArea.append(content);
	}
	
	private void setDefaultValue() {
		pathTextField.setText(System.getProperty("user.dir"));
		filenameTextField.setText(FileUtil.randomFilename());
	}

	/**
	 * 初始化各组件位置
	 */
	private void init() {
		setLayout(null);

		// Add directory row
		JLabel pathLabel = new JLabel("Directory Path");
		pathLabel.setBounds(50, 20, 100, 30);
		add(pathLabel);

		pathTextField.setBounds(150, 20, 400, 30);
		add(pathTextField);

		openButton.setBounds(600, 20, 100, 30);
		add(openButton);

		// add key row
		JLabel keyLabel = new JLabel("File Name");
		keyLabel.setBounds(50, 60, 100, 30);
		add(keyLabel);

		filenameTextField.setBounds(150, 60, 400, 30);
		add(filenameTextField);

		saveButton.setBounds(600, 60, 100, 30);
		add(saveButton);

		// add result row
		contentTextArea.setLineWrap(true);
		JScrollPane contentScroll = new JScrollPane(contentTextArea);
		contentScroll.setBounds(50, 110, 650, 420);
		add(contentScroll);
	}

	/**
	 * 添加事件监听器
	 */
	private void addListener() {
		addOpenListener();
		addSaveListener();
	}

	/**
	 * 添加选择文件路径监听器
	 */
	private void addOpenListener() {
		openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Open");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					pathTextField.setText(chooser.getSelectedFile()
							.getAbsolutePath());
				} else {
					System.out.println("No Selection ");
				}
			}
		});
	}
	
	/**
	 * 提示输入有误
	 * @param tf 文本输入框
	 * @param message 提示信息
	 */
	private void showMessage(JTextField tf, String message) {
		JOptionPane.showMessageDialog(null, message, "WARNING_MESSAGE",
				JOptionPane.WARNING_MESSAGE);
		tf.requestFocus();
		tf.selectAll();
	}

	private void addSaveListener() {
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String path = pathTextField.getText();
				if (!FileUtil.exists(path)) {
					showMessage(pathTextField, "Path invalid!!!");
					return;
				}
				String filename = filenameTextField.getText();
				if (filename == null || filename.trim().equals("")) {
					showMessage(filenameTextField, "Please enter keywords!");
					return;
				}
				
				filename = String.format("%s/%s", path, filename);
				if (FileUtil.exists(filename)) {
					showMessage(filenameTextField, "The file exists. Please enter other filename!");
					return;
				}
				
				FileUtil.save(filename, contentTextArea.getText());
				JOptionPane.showMessageDialog(null, "Saved " + filename, "MESSAGE",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	private static final long serialVersionUID = -6391942549323477950L;

	private JTextField pathTextField = new JTextField();
	private JButton openButton = new JButton("Open");
	private JTextField filenameTextField = new JTextField();
	private JButton saveButton = new JButton("Save");
	private JTextArea contentTextArea = new JTextArea();
}
