package com.tool.finder.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.tool.finder.thread.Finder;
import com.tool.finder.util.FileUtil;

public class FinderUI extends BaseUI {

	public FinderUI() {
		super();
		this.setTitle("Searching");
		addMenu();
		init();
		addListener();
	}

	/**
	 * 添加菜单栏
	 */
	private void addMenu() {
		JMenuBar menubar = new JMenuBar(); // 创建菜单栏
		JMenu menu = new JMenu("File"); // 创建“文件”菜单
		JMenuItem mavenItem = new JMenuItem("Maven Repo Parser");
		JMenuItem exportItem = new JMenuItem("Export"); // 创建“导出"菜单项
		JMenuItem closeItem = new JMenuItem("Exit"); // 创建“退出"菜单项
		menu.add(mavenItem);
		menu.add(exportItem);
		menu.add(closeItem);
		menubar.add(menu); // 将文件添加到菜单栏上
		menubar.add(new JMenu("Help"));
		this.setJMenuBar(menubar); // 设置菜单栏

		// 退出菜单项监听
		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// 导出菜单项监听
		exportItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				String filename = FileUtil.export(pathTextField.getText(), keyTextField.getText(), 
//						contentTextArea.getText(), resultTextArea.getText());
//				JOptionPane.showMessageDialog(null, "Saved " + filename, "MESSAGE",
//						JOptionPane.INFORMATION_MESSAGE);
				
				ExportUI export = new ExportUI(pathTextField.getText(), keyTextField.getText(), 
						contentTextArea.getText(), resultTextArea.getText());
				export.setVisible(true);
				export.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	// 关闭当前窗口, 不影响其他父窗口
			}
		});
		
		// Maven本地仓库监听
		mavenItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MavenRepoUI repo = new MavenRepoUI();
				repo.setVisible(true);
				repo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
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
		JLabel keyLabel = new JLabel("Keywords");
		keyLabel.setBounds(50, 60, 100, 30);
		add(keyLabel);

		keyTextField.setBounds(150, 60, 400, 30);
		add(keyTextField);

		searchButton.setBounds(600, 60, 100, 30);
		add(searchButton);

		// add result row
		contentTextArea.setLineWrap(true);
		JScrollPane contentScroll = new JScrollPane(contentTextArea);
		contentScroll.setBounds(50, 110, 300, 420);
		add(contentScroll);

		resultTextArea.setLineWrap(true);
		JScrollPane resultScroll = new JScrollPane(resultTextArea);
		resultScroll.setBounds(400, 110, 300, 420);
		add(resultScroll);
	}

	/**
	 * 添加事件监听器
	 */
	private void addListener() {
		addOpenListener();
		addSearchListener();
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

	/**
	 * 添加搜索监听器
	 */
	private void addSearchListener() {
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String path = pathTextField.getText();
				if (!FileUtil.exists(path)) {
					showMessage(pathTextField, "Path invalid!!!");
					return;
				}
				String key = keyTextField.getText();
				if (key == null || key.trim().equals("")) {
					showMessage(keyTextField, "Please enter keywords!");
					return;
				}
				contentTextArea.setText("");
				resultTextArea.setText("");
				Thread t = new Thread(new Finder(contentTextArea, resultTextArea, path, key));
				t.start();
			}
		});
	}

	private static final long serialVersionUID = -7716053189657055365L;

	private JTextField pathTextField = new JTextField();
	private JButton openButton = new JButton("Open");
	private JTextField keyTextField = new JTextField();
	private JButton searchButton = new JButton("Searching");
	private JTextArea contentTextArea = new JTextArea();
	private JTextArea resultTextArea = new JTextArea();
}
