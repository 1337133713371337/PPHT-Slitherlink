package Gui;

import Map.Map;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by bach on 4/25/2016.
 */
public class MyPanel extends JPanel implements ActionListener{
    public static final String MATRAN5X5= "5x5";
    public static final String MATRAN10X10= "10x10";
    public static final String EXIT= "exit";
    private JButton btn5x5,btn10x10,btnexit;
    private MainPanel mainPanel;
    public MyPanel(MainPanel mainPanel){
        this.mainPanel=mainPanel;
        setLayout(null);
        init();


    }

    private void init() {
        btn5x5= new JButton();
        btn5x5.setText("5x5");
        btn10x10= new JButton();
        btn10x10.setText("10x10");
        btnexit= new JButton();
        btnexit.setText("exit");
        btn5x5.setBounds(MyFrame.W_SIZE/2-100,MyFrame.H_SIZE/2-140,200,50);
        btn10x10.setBounds(MyFrame.W_SIZE/2-100,MyFrame.H_SIZE/2-70,200,50);
        btnexit.setBounds(MyFrame.W_SIZE/2-100,MyFrame.H_SIZE/2,200,50);
        add(btn5x5);
        add(btn10x10);
        add(btnexit);
        btn5x5.addActionListener(this);
        btn10x10.addActionListener(this);
        btnexit.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btn5x5)){
            mainPanel.showCard(MATRAN5X5);
        }
        if(e.getSource().equals(btn10x10)){
            mainPanel.showCard(MATRAN10X10);
        }
        if(e.getSource().equals(btnexit)){
            JOptionPane.showConfirmDialog(null,"Ban co muon thoat ko?",EXIT,JOptionPane.YES_NO_OPTION);
            int t=JOptionPane.YES_NO_OPTION;
            if(t==JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
    }
}

