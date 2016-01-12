package com.tool.finder;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class BaseUI extends JFrame {
	public BaseUI() {
		this.setSize(800, 600);
		this.setResizable(false);	//窗体不能最大化
		centerDisplay();		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * @return 屏幕大小
	 */
	public Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	/**
	 * 中央显示窗口
	 */
	public void centerDisplay() {
		Dimension screenSize = getScreenSize();
		setLocation((screenSize.width - this.getWidth()) / 2,
				(screenSize.height - this.getHeight()) / 2);
	}
	
	private static final long serialVersionUID = -6810901839808671046L;

}
