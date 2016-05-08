import Gui.MyFrame;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
	public static int WIDTH;
	public static int HEIGHT;
	static  int [][]val;
	/*input : file matrix
	* output : string satSolver, write to file result
	* */
	public static String solve (String input) throws IOException {
		Path filePath = Paths.get(input);
		Scanner scanner = null;
		try {
			scanner = new Scanner(filePath);
			int i = 0, j = 0;
			HEIGHT = scanner.nextInt();
			WIDTH = scanner.nextInt();
			val = new int[HEIGHT][WIDTH];
			while (scanner.hasNext()) {
				if (scanner.hasNextInt()) {
					int q = scanner.nextInt();
					if (j >= WIDTH) {
						i++;
						j = 0;
					}
					val[i][j] = q;
					j++;
				} else {
					scanner.next();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		WriteToCNF writeToCNF = new WriteToCNF(val);
		String satSolver = SatSolver.solve("input/cnf/input.cnf");
		return  satSolver;
	}
	/*
	* compute the edge next to 'a' in arraylist arr
	* */
	public static int computeNextTo (int a, ArrayList<Integer> arr) {
		int x=0,y=0,t=0,b=0;
		if (a <= WIDTH*(HEIGHT+1)) {
			b = a % WIDTH;
			if (b == 0) {
				x = a/WIDTH -1;
				y = WIDTH;
			}
			else {
				x = a/WIDTH;
				y = a%WIDTH;
			}
			t = WIDTH *(HEIGHT+1) + (x - 1)*(WIDTH+1) + y;
			for (int i = 0; i < arr.size(); i++) {
				if ((b == 0 && a-arr.get(i) == 1 )||
						(b == 1 && arr.get(i) - a == 1) )
					return arr.get(i);
				if (Math.abs(arr.get(i) - a) == 1 && b != 0 && b != 1) return arr.get(i);
				if (a <= WIDTH) {
					if ( 	arr.get(i) == t+WIDTH+1 ||
							arr.get(i) == t+WIDTH+2)
						return arr.get(i);
				}
				if (a > (WIDTH*HEIGHT)) {
					if ( 	arr.get(i) == t ||
							arr.get(i) == t+1)
						return arr.get(i);
				}
				if (arr.get(i) > WIDTH*(HEIGHT+1)){
					if ( 	arr.get(i) == t ||
							arr.get(i) == t+1 ||
							arr.get(i) == t+WIDTH+1 ||
							arr.get(i) == t+WIDTH+2)
						return arr.get(i);
				}
			}
		}
		if (a > WIDTH*(HEIGHT+1)) {
			int k = a - WIDTH*(HEIGHT+1);
			b = k % (WIDTH +1);
			if (b == 0) {
				x = k / (WIDTH +1) - 1;
				y = WIDTH+1;
			}
			else {
				x = k/(WIDTH+1);
				y = k%(WIDTH+1);
			}
			t = x * WIDTH + y -1;
			for (int i = 0; i < arr.size(); i++) {
				if (( a <= WIDTH*(HEIGHT+1)+WIDTH+1 && arr.get(i)-a == WIDTH+1 )||
						( a >= 2*WIDTH*HEIGHT+HEIGHT && a-arr.get(i) == WIDTH+1 ) )
					return arr.get(i);
				if (Math.abs(arr.get(i) - a) == WIDTH+1 && a > WIDTH*(HEIGHT+1)+WIDTH+1 && a <= 2*WIDTH*HEIGHT+HEIGHT) return arr.get(i);
				if (arr.get(i) <= WIDTH*(HEIGHT+1)){
					if (b == 0 ) {
						if (arr.get(i) == t || arr.get(i)==t+WIDTH) return arr.get(i);
					}
					else if (b==1) {
						if (arr.get(i) == t+1 || arr.get(i)==t+WIDTH+1) return arr.get(i);

					}
					else
					if (arr.get(i) == t ||
						arr.get(i) == t+1 ||
						arr.get(i) == t+WIDTH ||
						arr.get(i) == t+WIDTH+1)
					return arr.get(i);
				}
			}
		}
		return 0;
	}
	/*
	* check the result of satsolver.
	* if false, reload satsolver
	* */
	public static boolean checkResult () {
		ArrayList<Integer> a = Reload.readResult();
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) <= 0) {
				a.remove(i);
				i--;
			}
		}
		ArrayList<Integer> b = new ArrayList<>();
		b.add(a.get(0));
		a.remove(0);
		while (computeNextTo(b.get(b.size()-1),a) != 0) {
			int t = computeNextTo(b.get(b.size()-1),a);
			b.add(t);
			a.remove(Integer.valueOf(t));
		}
		//return a.size();
		for (int i =0;i<b.size();i++) {
			System.out.print(b.get(i)+"  ");

		}
		System.out.print("\n");
		if (a.size() !=0) return false;
		return true;
	}
	public static void main(String[] args) throws IOException {
		int countReload = 0;
		String result = Main.solve("input/problem/20x20.txt");
		long start = System.currentTimeMillis();
		while (!checkResult()) {
			result = Reload.reload();
			countReload++;
			if (result == "Cannot solve this problem !") break;
		}
		long end = System.currentTimeMillis();
		long time = end - start;

		System.out.println("Number of reloading : " + countReload);
		System.out.println("Time : " + time);
	}

}
