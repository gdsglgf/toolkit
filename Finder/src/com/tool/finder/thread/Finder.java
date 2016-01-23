package com.tool.finder.thread;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.tool.finder.util.FileUtil;

public class Finder implements Runnable {

    public Finder(JTextArea contentTextArea, JTextArea resultTextArea, String path, String key) {
        this.contentTextArea = contentTextArea;
        this.resultTextArea = resultTextArea;
        this.path = path;
        this.key = key;
    }

    @Override
    public void run() {
        File file = new File(path);
        Queue<File> fileQueue = new LinkedBlockingQueue<File>();
        fileQueue.add(file);
        while (fileQueue.size() > 0) {
            File f = fileQueue.poll();
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {
                        fileQueue.add(files[i]);
                    }
                }
            } else {
                String filename = f.getName();
                if (filename.contains(key)
                        || filename.toLowerCase().contains(key.toLowerCase())) {
                    resultTextArea.append(f.getAbsolutePath());
                    resultTextArea.append(FileUtil.CRLF);
                    count++;
                }
                contentTextArea.append(f.getAbsolutePath());
                contentTextArea.append(FileUtil.CRLF);
            }
        }
        
        String msg = String.format("Searched %d results", count);
        JOptionPane.showMessageDialog(null, msg, "MESSAGE",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private JTextArea contentTextArea;
    private JTextArea resultTextArea;
    private String path;
    private String key;
    private int count = 0;
}
