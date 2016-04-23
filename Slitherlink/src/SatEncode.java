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
//	int edgeUp (int r, int c) {
//		if (r == 0) return 0;
//		if (c != 0) return right(r-1, c-1);
//		return left(r-1, c);
//	}
	int edgeUp (int r, int c) {
		return right(r-1, c-1);
	}
	int edgeRight(int r, int c) {
		return up(r, c);
	}
	int edgeDown (int r, int c) {
		return left(r, c);
	}
	int edgeLeft (int r, int c) {
		return down(r-1, c-1);
	}
//	int edgeRight(int r, int c) {
//		if (c == w) return  0;
//		if (r != h) return up(r, c);
//		return down(r-1, c);
//	}
//	int edgeDown (int r, int c) {
//		if (r == h) return 0;
//		if (c != w) return left(r, c);
//		return right(r, c-1);
//	}
//	int edgeLeft (int r, int c) {
//		if (c == 0) return  0;
//		if (r != 0) return down(r-1, c-1);
//		return up(r, c-1);
//	}
	public ArrayList<String> getEncodeText () {
		return encodeText;
	}
	public void encode () {
		encodeText = new ArrayList<String>();

		//encode law 1

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
						encodeText.add("-" + left(i,j) + " -" + down(i,j));
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
		for (int i = 1; i < w; i++) {
			for (int j = 1; j < h; j++) {

					encodeText.add("-" + edgeRight(i,j) + " "+edgeUp(i,j) +" " + edgeLeft(i,j) + " " + edgeDown(i,j));
					encodeText.add("-" + edgeUp(i,j)  + " " + edgeRight(i,j) + " " + edgeLeft(i,j) + " " + edgeDown(i,j));
					encodeText.add("-" + edgeLeft(i,j) + " "+edgeRight(i,j) +" " + edgeUp(i,j) + " " + edgeDown(i,j));
					encodeText.add("-" + edgeDown(i,j) + " " + edgeRight(i,j) + " " + edgeUp(i,j) + " " + edgeLeft(i,j));
					encodeText.add("-" + edgeRight(i,j) + " -" + edgeUp(i,j) + " -" + edgeLeft(i,j) + " -" + edgeDown(i,j));
					encodeText.add(" -" + edgeRight(i,j) + " -" + edgeUp(i,j) + " -" + edgeLeft(i,j));
					encodeText.add(" -" + edgeRight(i,j) + " -" + edgeUp(i,j) + " -" + edgeDown(i,j));
					encodeText.add("-" + edgeRight(i,j) + " -" + edgeLeft(i,j) + " -" + edgeDown(i,j) );
					encodeText.add("-" + edgeUp(i,j) + " -" + edgeLeft(i,j) + " -" + edgeDown(i,j) );
			}
		}
		encodeText.add(edgeRight(0,0) + " -" + edgeDown(0,0));
		encodeText.add("-" + edgeRight(0,0) + edgeDown(0,0));

		encodeText.add(edgeLeft(0,w) + " -" + edgeDown(0,w));
		encodeText.add("-" + edgeLeft(0,w) + edgeDown(0,0));

		encodeText.add(edgeRight(h,0) + " -" + edgeUp(h,0));
		encodeText.add("-" + edgeRight(h,0) + edgeUp(h,0));

		encodeText.add(edgeUp(h,w) + " -" + edgeLeft(h,w));
		encodeText.add(" -" + edgeUp(h,w) + edgeLeft(h,w));

		for (int j = 1; j < w; j++) {
			//0,j right left down
			encodeText.add("-" + edgeRight(0,j) + " " + edgeLeft(0,j) + " " + edgeDown(0,j));
			encodeText.add("-" + edgeRight(0,j) + " -" + edgeLeft(0,j) + " -"+ edgeDown(0,j));
			encodeText.add(edgeRight(0,j) + " -" + edgeLeft(0,j) + " "+ edgeDown(0,j));
			encodeText.add(edgeRight(0,j) + " " + edgeLeft(0,j) + " -"+ edgeDown(0,j));
			//h,j right left up
			encodeText.add("-" + edgeRight(h,j) + " " + edgeUp(h,j) + " " + edgeLeft(h,j));
			encodeText.add("-" + edgeRight(h,j) + " -" + edgeUp(h,j) + " -" + edgeLeft(h,j));
			encodeText.add(edgeRight(h,j) + " -" + edgeUp(h,j) + " " + edgeLeft(h,j));
			encodeText.add(edgeRight(h,j) + " " + edgeUp(h,j) + " -" + edgeLeft(h,j));
		}
		for (int i = 1; i < h; i++) {
			//i,0
			encodeText.add("-" + edgeRight(i,0) + " " + edgeUp(i,0) + " " + edgeDown(i,0));
			encodeText.add("-" + edgeRight(i,0) + " -" + edgeUp(i,0) + " -"+ edgeDown(i,0));
			encodeText.add(edgeRight(i,0) + " -" + edgeUp(i,0) + " "+ edgeDown(i,0));
			encodeText.add(edgeRight(i,0) + " " + edgeUp(i,0) + " -"+ edgeDown(i,0));

			//i,w
			encodeText.add("-" + edgeUp(i,w) + " " + edgeLeft(i,w) + " " + edgeDown(i,w));
			encodeText.add("-" + edgeUp(i,w) + " -" + edgeLeft(i,w) + " -"+ edgeDown(i,w));
			encodeText.add(edgeUp(i,w) + " -" + edgeLeft(i,w) + " "+ edgeDown(i,w));
			encodeText.add(edgeUp(i,w) + " " + edgeLeft(i,w) + " -"+ edgeDown(i,w));

		}

		//encode law 2
//		for (int i = 0; i < w + 1; i++) {
//			for (int j = 0; j < h + 1; j++) {
//				if (edgeRight(i,j) != 0 && edgeUp(i,j) != 0 && edgeLeft(i,j) != 0 && edgeDown(i,j) != 0) {
//					encodeText.add("-" + edgeRight(i,j) + " "+edgeUp(i,j) +" " + edgeLeft(i,j) + " " + edgeDown(i,j));
//					encodeText.add("-" + edgeUp(i,j)  + " " + edgeRight(i,j) + " " + edgeLeft(i,j) + " " + edgeDown(i,j));
//					encodeText.add("-" + edgeLeft(i,j) + " "+edgeRight(i,j) +" " + edgeUp(i,j) + " " + edgeDown(i,j));
//					encodeText.add("-" + edgeDown(i,j) + " " + edgeRight(i,j) + " " + edgeUp(i,j) + " " + edgeLeft(i,j));
//					encodeText.add("-" + edgeRight(i,j) + " -" + edgeUp(i,j) + " -" + edgeLeft(i,j) + " -" + edgeDown(i,j));
//
//				}
//				if (edgeRight(i,j) != 0 && edgeUp(i,j) != 0 && edgeLeft(i,j) != 0) {
//					encodeText.add(" -" + edgeRight(i,j) + " -" + edgeUp(i,j) + " -" + edgeLeft(i,j));
//
//				}
//				if (edgeRight(i,j) != 0 && edgeUp(i,j) != 0 && edgeDown(i,j) != 0) {
//					encodeText.add(" -" + edgeRight(i,j) + " -" + edgeUp(i,j) + " -" + edgeDown(i,j));
//
//				}
//
//				if (edgeRight(i,j) != 0 && edgeLeft(i,j) != 0 && edgeDown(i,j) != 0) {
//					encodeText.add("-" + edgeRight(i,j) + " -" + edgeLeft(i,j) + " -" + edgeDown(i,j) );
//
//				}
//				if (edgeUp(i,j) != 0 && edgeLeft(i,j) != 0 && edgeDown(i,j) != 0) {
//					encodeText.add("-" + edgeUp(i,j) + " -" + edgeLeft(i,j) + " -" + edgeDown(i,j) );
//
//				}
//
//			}
//		}
	}

}
