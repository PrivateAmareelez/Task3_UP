package part2.action;

import part2.gui.TracePather;

import java.awt.*;

public class Run {
    public static void main(String[] args) {
        Dialog dialog = new TracePather();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
