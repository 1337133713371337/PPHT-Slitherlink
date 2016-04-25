import com.sun.org.apache.regexp.internal.RE;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by trong on 4/24/2016.
 */
public class Reload {
    /*
    * input : result in result.txt
    * output : String nOldResult - negative oldresult.
    * */
	private static String negaResult () {
		ArrayList<Integer> oldResult = new ArrayList<>();
		Path filePath = Paths.get("input/result/result.txt");
		Scanner scanner = null;
		try {
			int i = 0;
			scanner = new Scanner(filePath);
			while (scanner.hasNext()) {
				if (scanner.hasNextInt()) {
					int q = scanner.nextInt();
					oldResult.add(q);
				}
				else {
					scanner.next();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String nOldResult = "";
		for (int i = 0; i < oldResult.size()-1; i++) {
			nOldResult += (oldResult.get(i)*-1) + " ";
		}
		nOldResult += "0\n";
		return nOldResult;
	}
    /*
    * write new file cnf. add negative old result to end of file.
    * */
	private static void WriteNewCNF () throws FileNotFoundException {
		Scanner sc = new Scanner(new File("input/cnf/input.cnf"));
		List<String> lines = new ArrayList<String>();
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		if (lines.size() == 0) throw new RuntimeException("run main first.");
	
		String[] arr = lines.toArray(new String[0]);
		String newFirstLine[] = arr[0].split(" ");
		int numberClause = Integer.parseInt(newFirstLine[3]) + 1;
		try {
			File file = new File("input/cnf/input.cnf");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("p cnf " + newFirstLine[2] + " " + numberClause);
			bw.newLine();
			for (int i = 1; i < lines.size(); i++) {
				bw.write(lines.get(i));
				bw.newLine();
			}
			bw.write(Reload.negaResult());
			bw.close();
		} catch (Exception e) {

		}

	}
    /*
    * solve new cnf file
    * */
	public static String reload () throws FileNotFoundException,IOException {
		Reload.WriteNewCNF();
		String satSolver = SatSolver.solve("input/cnf/input.cnf");
		return satSolver;
	}
	public static void main (String []args) throws IOException {
		System.out.print(Reload.reload());
	}
}
