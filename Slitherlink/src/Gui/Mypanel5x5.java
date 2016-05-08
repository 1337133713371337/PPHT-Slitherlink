package Gui;

import Map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by bach on 4/25/2016.
 */
public class Mypanel5x5 extends JPanel implements ActionListener {
    private static final int DoRongMenu= 200;
    private static final int DoRongBtn=110;
    private static final int DoDaibtn=30;
    private JButton btnStart,btnExit,btnBackmenu;
    private MainPanel mainPanel;
    private Map map;
    public Mypanel5x5(MainPanel mainPanel){
        this.mainPanel=mainPanel;
        setLayout(null);
        map= new Map();
        map.readMap();
        init();
    }

    private void init() {
        this.setSize(MyFrame.W_SIZE-DoRongMenu,MyFrame.W_SIZE);
        this.setBackground(Color.WHITE);

        btnBackmenu= new JButton("Back");
        btnBackmenu.setBounds(MyFrame.W_SIZE-DoRongMenu-80,420,DoRongBtn*2,30);
        btnExit= new JButton("Exit");
        btnExit.setBounds(MyFrame.W_SIZE-DoRongMenu-80,460,DoRongBtn*2,30);
        add(btnBackmenu);
        add(btnExit);

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setPaint(Color.BLACK);
        graphics2D.drawLine(860-320,0,860-320,600);
        graphics2D.drawLine(860-340,310,860-340,510);
        graphics2D.drawLine(860-340,510,860-440,510);
        graphics2D.drawLine(860-440,510,860-440,415);
        graphics2D.drawLine(860-440,415,860-650,415);
        graphics2D.drawLine(860-650,415,860-650,510);
        graphics2D.drawLine(860-650,510,860-750,510);
        graphics2D.drawLine(860-650,510,860-755,510);
        graphics2D.drawLine(860-755,510,860-755,415);
        graphics2D.drawLine(860-755,415,0,415);
        graphics2D.drawLine(0,415,0,310);
        graphics2D.drawLine(0,310,860-650,310);
        graphics2D.drawLine(860-650,310,860-650,210);
        graphics2D.drawLine(860-650,210,860-755,210);
        graphics2D.drawLine(860-755,210,860-755,105);
        graphics2D.drawLine(860-755,105,0,105);
        graphics2D.drawLine(0,105,0,0);
        graphics2D.drawLine(0,0,860-650,0);
        graphics2D.drawLine(860-650,0,860-650,105);
        graphics2D.drawLine(860-650,105,860-550,105);
        graphics2D.drawLine(860-550,105,860-550,310);
        graphics2D.drawLine(860-550,310,860-340,310);
        map.draw(graphics2D);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnBackmenu)){
            mainPanel.showCard(MainPanel.CANCEL);
        }
        if(e.getSource().equals(btnExit)){
            JOptionPane.showConfirmDialog(null,"Ban co muon thoat ko?","EXIT",JOptionPane.YES_NO_OPTION);
            int t=JOptionPane.YES_NO_OPTION;
            if(t==JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
    }
}
