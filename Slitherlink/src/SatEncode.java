import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Created by trong on 4/1/2016.
 */
public class SatEncode {
	private ArrayList<String> encodeText;
	private  int val[][];
	private int h;
	private int w;
	int k;
	public SatEncode(int v[][]) {
		w = Main.WIDTH;
		h = Main.HEIGHT;
		val = v;
		k = w * (h + 1);
	}
	public ArrayList<String> getEncodeText () {
		return encodeText;
	}
	public void encode () {
		encodeText = new ArrayList<String>();
		//encode here
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				switch (val[i][j]) {
					case 0 :
						encodeText.add("-" + up(i,j));
						encodeText.add("-" + down(i,j));
						encodeText.add("-" + left(i,j));
						encodeText.add("-" + right(i,j));
                        break;
					case 1 :
						encodeText.add(up(i,j) + " " + down(i,j)+ " " + left(i,j)+ " " + right(i, j));
						encodeText.add("-" + right(i,j) + " -" + up(i,j));
						encodeText.add("-" + right(i,j) + " -" + left(i,j));
						encodeText.add("-" + up(i,j) + " -" + left(i,j));
						encodeText.add("-" + up(i,j) + " -" + down(i,j));
						encodeText.add("-" + left(i,j) + " -" + up(i,j));
						encodeText.add("-" + down(i,j) + " -" + right(i,j));
						break;
					case 2 :
						encodeText.add(right(i,j)+ " " + up(i,j)+ " " +left(i,j));
						encodeText.add(right(i,j)+ " " + up(i,j)+ " " +down(i,j));
						encodeText.add(right(i,j)+ " " + left(i,j)+ " " +down(i,j));
						encodeText.add(up(i,j)+ " " + left(i,j) + " " + down(i,j));
						encodeText.add("-" + right(i,j)+ " -" + up(i,j)+ " -" +left(i,j));
						encodeText.add("-" + right(i,j)+ " -" + up(i,j)+ " -" +down(i,j));
						encodeText.add("-" + right(i,j)+ " -" + left(i,j)+ " -" +down(i,j));
						encodeText.add("-" + up(i,j)+ " -" + left(i,j) + " -" + down(i,j));
						break;
                    case 3 :
						encodeText.add("-" + up(i,j) + " -" + down(i,j)+ " -" + left(i,j)+ " -" + right(i, j));
						encodeText.add(right(i,j) + " " + up(i,j));
						encodeText.add(right(i,j) + " " + left(i,j));
						encodeText.add(up(i,j) + " " + left(i,j));
						encodeText.add(up(i,j) + " " + down(i,j));
						encodeText.add(left(i,j) + " " + down(i,j));
						encodeText.add(down(i,j) + " " + right(i,j));
						break;
				}
			}
		}
	}
	int up (int r, int c) {
		return w * r + c + 1;
	}
	int down (int r, int c) {
		return w * (r + 1) + c + 1;
	}
	int right (int r, int c) {
		return k + (w + 1) * r + c + 2;
	}
	int left (int r, int c) {
		return k+ (w + 1) * r + c + 1;
	}

}
