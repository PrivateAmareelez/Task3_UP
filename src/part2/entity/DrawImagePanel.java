package part2.entity;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.min;

public class DrawImagePanel extends JPanel {
    private Image image, scaledImage;
    private Point pos;

    public void loadImage(Image image) {
        this.image = this.scaledImage = image;
        pos = new Point(0, 0);
    }

    public Dimension adjust(double ratio) {
        if (getHeight() < getWidth())
            scaledImage = image.getScaledInstance(-1, (int) (getHeight() / ratio), Image.SCALE_DEFAULT);
        else
            scaledImage = image.getScaledInstance((int) (getWidth() / ratio), -1, Image.SCALE_DEFAULT);
        return new Dimension(scaledImage.getWidth(null), scaledImage.getHeight(null));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Rectangle bounds = new Rectangle(getWidth(), getHeight());
        int R = min(bounds.width, bounds.height) / 2 - 1;
        int centerX = (int) bounds.getCenterX();
        int centerY = (int) bounds.getCenterY();
        g.drawOval(centerX - R, centerY - R, 2 * R, 2 * R);

        g.drawImage(scaledImage, pos.x - scaledImage.getWidth(null) / 2, pos.y - scaledImage.getHeight(null) / 2, null);
    }

    public void shiftTo(double x, double y) {
        pos = new Point((int) x, (int) y);
    }

    public Point getPos() {
        return new Point(pos);
    }
}
