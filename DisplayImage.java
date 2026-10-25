import javax.swing.*;
import java.awt.*;

public class DisplayImage {
    public static void main(String[] args) {
        ImageIcon img = new ImageIcon("image.jpg");
        Image scaled = img.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);

        JFrame f = new JFrame("Display Image");
        f.add(new JLabel(new ImageIcon(scaled)));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
} 
