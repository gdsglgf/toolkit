import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class HistogramPanel extends JPanel {
    private static final long serialVersionUID = -7687400385502950167L;
    
    /**
     * 产生直方图随机数
     */
    public void createRandomNumber() {
        nums = new int[10];
        mids = new Mid[10];
        Random r = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
    }

    public HistogramPanel() {
        createRandomNumber();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(50, 50, 50, 500);
        g.drawLine(50, 500, 700, 500);
        
        // 绘制直方图
        int start = 50;
        for (int i = 0; i < nums.length; i++) {
            start += 40;
            int h = 500 - nums[i] * 4;
            g.drawLine(start, h, start, 500);
            g.drawLine(start, h, start + 20, h);
            g.drawString("" + nums[i], start + 3, h - 5);
            start += 20;
            g.drawLine(start, h, start, 500);
            if (mids[i] == null) {
                mids[i] = new Mid(start - 10, h);
            }            
        }
        
        // 绘制小圆和折线
        g.drawOval(mids[0].getX() - 2, mids[0].getY() - 2, 4, 4);
        for (int i = 1; i < mids.length; i++) {
            g.drawLine(mids[i - 1].getX(), mids[i - 1].getY(), mids[i].getX(), mids[i].getY());
            g.drawOval(mids[i].getX() - 2, mids[i].getY() - 2, 4, 4);
        }
    }
    
    private int[] nums;
    private Mid[] mids;
}
