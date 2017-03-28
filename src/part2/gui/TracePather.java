package part2.gui;

import part2.entity.DrawImagePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.*;

public class TracePather extends JDialog {
    private JPanel contentPane;
    private JComboBox comboBoxDirection;
    private JPanel drawImagePanel;
    private JSlider sliderSpeed;
    private double alpha = Math.PI / 2, R = 0;

    public TracePather() {
        setContentPane(contentPane);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        File f = new File("src/part2/twitter.jpg");
        try {
            Image image = ImageIO.read(f);
            DrawImagePanel imagePanel = (DrawImagePanel) this.drawImagePanel;
            imagePanel.loadImage(image);

            int delay = 200;
            new Timer(delay, e -> {
                Dimension dim = imagePanel.adjust(5);
                R = min(imagePanel.getWidth(), imagePanel.getHeight()) / 2 - max(dim.getWidth(), dim.getHeight());

                if (comboBoxDirection.getSelectedItem().equals("Clockwise")) {
                    alpha -= sliderSpeed.getValue() / R * 0.2;
                } else if (comboBoxDirection.getSelectedItem().equals("Anti-clockwise"))
                    alpha += sliderSpeed.getValue() / R * 0.2;

                Rectangle bounds = new Rectangle(imagePanel.getWidth(), imagePanel.getHeight());

                imagePanel.shiftTo(bounds.getCenterX() + R * cos(alpha), bounds.getCenterY() - R * sin(alpha));
                repaint();
            }).start();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File not found", "Missing file", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createUIComponents() {
        drawImagePanel = new DrawImagePanel();
    }
}
