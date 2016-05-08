package Map;

import java.awt.*;

/**
 * Created by bach on 4/29/2016.
 */
public class Item {
    private int x,y,bit;
    private Image image;

    public Item(Image image,int x, int y ,int bit) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.bit=bit;
    }
    public void draw(Graphics2D g2d){
        g2d.drawImage(image,x,y,null);
    }
}
