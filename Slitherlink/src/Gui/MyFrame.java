package Gui;

import javax.swing.*;

/**
 * Created by bach on 4/25/2016.
 */
public class MyFrame extends JFrame {
    public static final int W_SIZE=860;
    public static final int H_SIZE=560;
    public MainPanel mainPanel;
    public MyFrame() {
        setSize(W_SIZE, H_SIZE);
        setLocationRelativeTo(null);
        mainPanel= new MainPanel();
        add(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
