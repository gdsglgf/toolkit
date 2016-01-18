import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class HistogramFrame extends JFrame {
    public HistogramFrame() {
        this.setTitle("Histogram");
        this.setSize(800, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
    }
    
    public void init() {
        this.setLayout(null);
        update.setBounds(340, 520, 100, 30);
        add(update);
        final HistogramPanel p = new HistogramPanel();
        p.setBounds(0, 0, 800, 510);
        add(p);
        
        // 添加更新直方图监听事件
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.createRandomNumber();
                p.repaint();
            }
        });
    }
    
    private static final long serialVersionUID = -3735793842718090297L;

    public static void main(String[] args) {
        new HistogramFrame();
    }

    private JButton update = new JButton("Update");
}
