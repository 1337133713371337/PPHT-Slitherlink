package Gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by bach on 4/25/2016.
 */
public class MainPanel extends JPanel {
    private Mypanel5x5 myPanel5x5;
    private MyPanel10x10 myPanel10x10;
    private MyPanel myPanel;
    private CardLayout cardLayout;
    public static final String CANCEL="cancel";

    public MainPanel(){
        myPanel5x5= new Mypanel5x5(this);
        myPanel10x10= new MyPanel10x10(this);
        myPanel=new MyPanel(this);
        cardLayout= new CardLayout();
        setLayout(cardLayout);
        add(myPanel);
        add(myPanel.MATRAN5X5,myPanel5x5);
        add(myPanel.MATRAN10X10,myPanel10x10);
    }
    public void showCard(String name) {
        cardLayout.show(this,name);
    }
}
