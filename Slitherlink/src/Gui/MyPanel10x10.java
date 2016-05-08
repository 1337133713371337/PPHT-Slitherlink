package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by bach on 4/25/2016.
 */
public class MyPanel10x10 extends JPanel implements ActionListener {
    private static final int DoRongMenu= 200;
    private static final int DoRongBtn=110;
    private static final int DoDaibtn=30;
    private Mypanel5x5 mypanel5x5= new Mypanel5x5(null);
    //    private static final int DoRongMenu= 200;
    private JButton btnStart,btnExit,btnBackmenu;
    private MainPanel mainPanel;
    public MyPanel10x10(MainPanel mainPanel){
        this.mainPanel=mainPanel;
        setLayout(null);
        init();
    }

    private void init() {
        this.setLayout(null);
        this.setSize(MyFrame.W_SIZE-DoRongMenu,MyFrame.W_SIZE);
        this.setBackground(Color.WHITE);

        btnStart= new JButton("Start");
        btnStart.setBounds(MyFrame.W_SIZE-DoRongMenu-20,380,DoRongBtn,30);
        btnBackmenu= new JButton("Back");
        btnBackmenu.setBounds(MyFrame.W_SIZE-DoRongMenu-20,420,DoRongBtn,30);
        btnExit= new JButton("Exit");
        btnExit.setBounds(MyFrame.W_SIZE-DoRongMenu-20,460,DoRongBtn,30);
        add(btnStart);
        add(btnBackmenu);
        add(btnExit);

    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawLine(860-320,0,860-320,600);
        graphics2D.drawLine(0,0,0,55);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnBackmenu)){
            mainPanel.showCard("cancel");
        }

    }
}

