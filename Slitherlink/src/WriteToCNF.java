import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by trong on 4/1/2016.
 */
public class WriteToCNF {

	public WriteToCNF (int val[][]) {
		int w = Main.WIDTH;
		int h = Main.HEIGHT;
		SatEncode satEncode = new SatEncode(val);
		satEncode.encode();
		ArrayList<String> encodeText = new ArrayList<>();
		encodeText = satEncode.getEncodeText();
		try {
			File file = new File("input/cnf/input.cnf");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("p cnf "+(2*w*h+w+h)+" "+ encodeText.size());
			bw.newLine();

			for(int i = 0; i < encodeText.size();i++){
				bw.write(encodeText.get(i) + " 0");
				bw.newLine();
			}
			bw.close();
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
