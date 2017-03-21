package part1.gui;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

import static java.lang.Math.*;

public class MainForm extends Applet {
    private double angle;

    @Override
    public void init() {
        super.init();
        angle = Math.PI / 2;
        new Timer(1000, e -> {
            angle -= Math.PI / 30;
            repaint();
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Rectangle bounds = new Rectangle(getWidth(), getHeight());
        int R = min(bounds.width, bounds.height) / 2 - 1;
        int centerX = (int) bounds.getCenterX();
        int centerY = (int) bounds.getCenterY();
        g.drawOval(centerX - R, centerY - R, 2 * R, 2 * R);
        g.drawLine(centerX, centerY, (int) (centerX + R * cos(angle)), (int) (centerY - R * sin(angle)));
    }
}
