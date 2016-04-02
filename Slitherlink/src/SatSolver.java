import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.reader.Reader;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by trong on 2/29/2016.
 */
public class SatSolver {
	public static String solve (String url){
        String resultDecode ="";
        ISolver solver = SolverFactory.newDefault();
		solver.setTimeout(3600); // 1 hour timeout
		Reader reader = new DimacsReader(solver);
		try {
			IProblem problem = reader.parseInstance(url);
			if (problem.isSatisfiable()) {
				System.out.println("Satisfiable !");
				resultDecode = reader.decode(problem.model());

//                System.out.println(reader.decode(problem.model()));

			} else {
				System.out.println("Unsatisfiable !");
            }
		} catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
		} catch (ParseFormatException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} catch (ContradictionException e) {
			System.out.println("Unsatisfiable (trivial)!");
		} catch (TimeoutException e) {
			System.out.println("Timeout, sorry!");
		}
		return resultDecode;
	}
}
