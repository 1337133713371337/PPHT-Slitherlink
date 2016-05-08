package Map;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by bach on 4/29/2016.
 */
public class Map {
    private ArrayList<Item>arrayList= new ArrayList<Item>();
    private int x,y;
    private String str;
    private int line=0;
    private Image image;
    public void readMap(){
        try {
            File file= new File("C:\\Users\\trong\\Slitherlink\\src\\Map5x5.txt");
            BufferedReader bReader= new BufferedReader(new FileReader(file));
            while ((str = bReader.readLine()) != null){

                for (int i=0; i<str.length(); i++){
                    x=i*105+5;
                    y=103*line+5;

                    char s = str.charAt(i);
                    if (s == '0'){
                        image = new ImageIcon(getClass().getResource("/Image/0.png")).getImage();
                        Item diem = new Item(image,x,y,0);
                        arrayList.add(diem);
                    }
                    if (s == '1'){
                        image = new ImageIcon(getClass().getResource("/Image/1.png")).getImage();
                        Item diem = new Item(image,x,y,1);
                        arrayList.add(diem);
                    }

                    if (s == '2'){
                        image = new ImageIcon(getClass().getResource("/Image/2.png")).getImage();
                        Item diem = new Item(image,x,y,2);
                        arrayList.add(diem);
                    }

                    if (s == '3'){
                        image = new ImageIcon(getClass().getResource("/Image/3.png")).getImage();
                        Item diem = new Item(image,x,y,3);
                        arrayList.add(diem);
                    }

                }
                line++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void draw(Graphics2D graphics2D){
        for(int i=0;i<arrayList.size();i++) {
            arrayList.get(i).draw(graphics2D);
        }
    }
}
