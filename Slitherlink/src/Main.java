import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	public static void main(String[] args) throws IOException {
		String result = Main.solve("input/problem/5x5.txt");
		System.out.print(result);
	}
}
