package com.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FileChooserEx {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                new FileChooserEx().createUI();
            }
        };

        EventQueue.invokeLater(r);
    }

    private void createUI() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        JButton saveBtn = new JButton("Save");
        JButton openBtn = new JButton("Open");

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser saveFile = new JFileChooser(".");
                saveFile.setDialogTitle("Choose a File");
                saveFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int response = saveFile.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                   File file = saveFile.getSelectedFile();
                   System.out.printf("Path:[%s]\n", file.getAbsolutePath());
                }
            }
        });

        openBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser openFile = new JFileChooser(".");
                openFile.setDialogTitle("Choose a File");
                openFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int response = openFile.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                   File file = openFile.getSelectedFile();
                   System.out.printf("Path:[%s]\n", file.getAbsolutePath());
                }
            }
        });

        frame.add(new JLabel("File Chooser"), BorderLayout.NORTH);
        frame.add(saveBtn, BorderLayout.CENTER);
        frame.add(openBtn, BorderLayout.SOUTH);
        frame.setTitle("File Chooser");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}