import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class MyPanel extends JPanel implements MouseListener {
	int w, h;
	int[][] val;
	boolean[][] row;
	boolean[][] col;
	int SIZE_BOX;
	boolean[][] getRowArr(){
		return row;
	}
	boolean[][] getColArr(){
		return col;
	}

	public void setW(int w) {
		this.w = w;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setVal(int[][] val) {
		this.val = val;
	}

	public void setRow(int a,int b) {
		row = new boolean[a+1][b];
	}

	public void setCol(int a,int b) {
		col = new boolean[a][b+1];
	}

	public MyPanel(int w, int h, int[][]val) {
		this.val = val;
		this.w = w;
		this.h = h;
		row = new boolean[h+1][w];
		col = new boolean[h][w+1];

		SIZE_BOX=Main.SIZE_BOX;
		setPreferredSize(null);
		setBackground(new Color(222, 222, 222));
		addMouseListener(this);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//draw point
		for (int i = 0; i < w + 1; i++) {
			for (int j = 0; j < h + 1; j++) {
				g.fillOval(((i + 1) * SIZE_BOX) - 4, ((j + 1) * SIZE_BOX) - 4,
						8, 8);
			}
		}
		//draw number
		Font f = new Font("Arial", Font.PLAIN, SIZE_BOX*3/5);
		g.setFont(f);
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				String str;
				if (val[j][i] != 4) {
					str = val[j][i] + "";
				} else {
					str = "";
				}
				g.drawString(str, (int)((i+1.35) * SIZE_BOX ), (int)((j+1.7) * SIZE_BOX));
			}
		}
		int x1,x2,y1,y2;
		for(int i=0;i<h+1;i++){
			for(int j=0;j<w;j++){
				if(row[i][j]){
					x1=SIZE_BOX*(1+j);
					y1=SIZE_BOX*(1+i);
					x2=x1+SIZE_BOX;
					y2=y1;
					((Graphics2D) g).setStroke(new BasicStroke(3));
					g.drawLine(x1, y1, x2, y2);
				}

			}
		}
		for(int i=0;i<h;i++){
			for(int j=0;j<w+1;j++){
				if(col[i][j]){
					x1=SIZE_BOX*(1+j);
					y1=SIZE_BOX*(1+i);
					x2=x1;
					y2=y1+SIZE_BOX;
					((Graphics2D) g).setStroke(new BasicStroke(3));
					g.drawLine(x1, y1, x2, y2);
				}

			}
		}


	}

	@Override
	public void mouseClicked(MouseEvent e) {
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
