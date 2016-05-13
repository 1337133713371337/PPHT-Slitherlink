import jdk.internal.dynalink.beans.StaticClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.ImageObserver;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

	JFrame myFrame;
	static JLabel myLabel1;
	static JLabel myLabel2;
	static JLabel message;
	static JLabel cnf;
	static JLabel myLabel3;
	static JLabel myLabel4;

	MyPanel myCanvas;
	ArrayList<JButton> buttons;
	final static int NUM_OF_BUTTON = 4;
	int CANVAS_HEIGHT;
	int CANVAS_WIDTH;
	final static int SIZE_BOX = 40;
	JPanel tab1, tab2, panelCanvas,panelCanvas2;
	JTabbedPane tabbedPane;
	//main
	public static void main(String args[]) {
		try {
			new Main();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int WIDTH;
	public static int HEIGHT;
	static  int [][]val;
	public static String input = "";
	public static void readInput (String input) {
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
	Main() throws IOException {
		input = "input/problem/15x15.txt";
		readInput(input);
		CANVAS_HEIGHT = (HEIGHT + 2) * SIZE_BOX;
		CANVAS_WIDTH = (WIDTH + 2) * SIZE_BOX;
		tab1 = new JPanel();
		tab2 = new JPanel();
		panelCanvas = new JPanel();
		panelCanvas2 = new JPanel();
		tabbedPane = new JTabbedPane();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLayout(null);
		myFrame.setSize(CANVAS_WIDTH + 300, CANVAS_HEIGHT + 50);
		// myFrame.add(myCanvas);
		myFrame.setLocationRelativeTo(null);
		myFrame.setVisible(true);


		tabbedPane.add("Game", tab1);
		tabbedPane.add("CNF", tab2);

		myLabel1 = new JLabel("Duration : --:--");
		myLabel1.setBounds(CANVAS_WIDTH + 50, 250, 200, 70);
		myLabel2 = new JLabel("Number of SAT calls : 0");
		myLabel2.setBounds(CANVAS_WIDTH + 50, 300, 200, 70);
		message= new JLabel("Click SOLVE to solve!");
		message.setBounds(CANVAS_WIDTH + 50, 350, 200, 70);
		cnf= new JLabel("");

		myLabel3 = new JLabel("Number of variable : 0                 ");
		myLabel3.setBounds(CANVAS_WIDTH + 50, 400, 200, 70);
		myLabel4 = new JLabel("Number of expression : 0               ");
		buttons = new ArrayList<JButton>();
		for (int i = 0; i < NUM_OF_BUTTON; i++) {
			buttons.add(new JButton());
		}
		buttons.get(0).setText("Solve");
		buttons.get(1).setText("Clear");
		buttons.get(0).setBounds(CANVAS_WIDTH + 50, 100, 100, 40);
		buttons.get(1).setBounds(CANVAS_WIDTH + 50, 150, 100, 40);


		JComboBox<String> comboLanguage = new JComboBox<String>();
		comboLanguage.addItem("3x3.txt");
		comboLanguage.addItem("4x4.txt");
		comboLanguage.addItem("5x5.txt");
		comboLanguage.addItem("7x7.txt");
		comboLanguage.addItem("10x10.txt");
		comboLanguage.addItem("10x10_1.txt");
		comboLanguage.addItem("10x10_2.txt");
		comboLanguage.addItem("15x15.txt");
		comboLanguage.addItem("20x20.txt");
		comboLanguage.addItem("20x20_2.txt");
		comboLanguage.setSelectedIndex(7);
		comboLanguage.setBounds(CANVAS_WIDTH + 50, 50, 120, 30);

		tab1.add(buttons.get(0));
		tab1.add(buttons.get(1));
		tab1.add(myLabel1);
		tab1.add(myLabel2);
		tab1.add(message);
		tab1.add(comboLanguage);
		tab2.add(myLabel3);
		tab2.add(myLabel4);
		myFrame.add(tabbedPane);
		tabbedPane.setBounds(0, 0, myFrame.getWidth() - 15,myFrame.getHeight() - 38);

		tab1.setLayout(null);
		tab1.add(panelCanvas);
		panelCanvas.setLayout(new BorderLayout());
		panelCanvas.setBounds(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT - 15);
		myCanvas = new MyPanel(WIDTH,HEIGHT,val);
		myCanvas.setPreferredSize(new Dimension( 1000,1000));
		JScrollPane scrollFrame = new JScrollPane(myCanvas);
		myCanvas.setAutoscrolls(true);
		panelCanvas.add(scrollFrame);

		tab2.setLayout(null);
		tab2.add(panelCanvas2);
		panelCanvas2.setLayout(new BorderLayout());
		panelCanvas2.setBounds(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT - 15);
		JPanel myCanvas2 = new JPanel();
		myCanvas2.setLayout(new FlowLayout(FlowLayout.LEFT));
		myCanvas2.add(cnf);

		myCanvas2.setPreferredSize(new Dimension( 0,1000000));
		JScrollPane scrollFrame2 = new JScrollPane(myCanvas2);
		myCanvas2.setAutoscrolls(true);
		panelCanvas2.add(scrollFrame2);

		myFrame.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				Component c = (Component) arg0.getSource();
				tabbedPane.setBounds(0, 0, c.getWidth() - 15,
						c.getHeight() - 38);
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}
		});
		comboLanguage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<String> combo = (JComboBox<String>) event.getSource();
				String selectedBook = (String) combo.getSelectedItem();
				input = "input/problem/"+selectedBook;
				readInput(input);
				CANVAS_HEIGHT = (HEIGHT + 2) * SIZE_BOX;
				CANVAS_WIDTH = (WIDTH + 2) * SIZE_BOX;
				myCanvas.setH(HEIGHT);
				myCanvas.setW(WIDTH);
				myCanvas.setVal(val);
				myCanvas.setCol(HEIGHT,WIDTH);
				myCanvas.setRow(HEIGHT,WIDTH);
				myCanvas.repaint();
				System.out.println(input);
			}
		});
		//solve
		buttons.get(0).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int countReload = 1;
				String result = null;
				WriteToCNF writeToCNF = new WriteToCNF(val);
				long start = System.currentTimeMillis();
				result = SatSolver.solve("input/cnf/input.cnf");
				while (!checkResult()) {
					try {
						result = Reload.reload();
						countReload++;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if (result == "Cannot solve this problem !") {
						message.setText("Cannot solve this problem !");
						break;
					}
					if (countReload >= 500) {
						message.setText("Reload too many times!!!");
						break;
					}
				}
				if (result != "Cannot solve this problem !" && countReload < 500) {
					message.setText("Successful !!!");
				}
				long end = System.currentTimeMillis();
				long time = end - start;
				cnf.setText(readCnf());
				myLabel1.setText("Duration : " + time +" ms");
				myLabel2.setText("Number of SAT calls : " + countReload);
				myLabel3.setText("Number of varaiable  : "+ numOfVaraialbe);
				myLabel4.setText("Number of expression : " + numOfExpression);

				String[] a = result.split(" ");
				ArrayList<Integer> b = new ArrayList<Integer>();
				for (int i = 0; i < a.length - 1; i++) {
					b.add(Integer.parseInt(a[i]));
				}
				for (int i = 0; i < b.size(); i++) {
					int temp = b.get(i);
					if (temp > 0) {
						if (e(temp)[2] == 0) {
							myCanvas.getRowArr()[e(temp)[0]][e(temp)[1]] = true;
						}
						if (e(temp)[2] == 1) {
							myCanvas.getColArr()[e(temp)[0]][e(temp)[1]] = true;
						}

					}
				}
				myCanvas.repaint();

			}
		});
		//clear
		buttons.get(1).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < HEIGHT + 1; i++) {
					for (int j = 0; j < WIDTH; j++) {
						myCanvas.getRowArr()[i][j] = false;
					}
				}
				for (int i = 0; i < HEIGHT; i++) {
					for (int j = 0; j < WIDTH + 1; j++) {
						myCanvas.getColArr()[i][j] = false;
					}
				}
				myCanvas.repaint();
			}
		});
	}
	int[] e(int a) {
		int[] e = new int[3];
		int b;
		int d = WIDTH * (HEIGHT + 1);
		if (a <= d) {
			b = a % WIDTH;
			if (b == 0) {
				e[0] = a/WIDTH-1;
				e[1] = WIDTH-1;
			}
			else {
				e[0] = a/WIDTH;
				e[1] = a%WIDTH-1;
			}
			e[2] = 0;
		} else {
			int k = a - WIDTH*(HEIGHT+1);
			b = k % (WIDTH +1);
			if (b == 0) {
				e[0] = k / (WIDTH +1)-1;
				e[1] = WIDTH;
			}
			else {
				e[0] = k/(WIDTH+1);
				e[1] = k%(WIDTH+1)-1;
			}
			e[2] = 1;
		}
		return e;
	}
	static int numOfVaraialbe = 0;
	static  int numOfExpression = 0;
	public String readCnf () {
		String a = "<html>";
		Scanner sc = null;
		try {
			sc = new Scanner(new File("input/cnf/input.cnf"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		java.util.List<String> lines = new ArrayList<String>();
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		for (int i = 0 ; i < lines.size(); i++) {
			if (lines.get(i).length() < 110)
				a+= lines.get(i) +"<br>";
			else {
				String b = lines.get(i);
				while (b.length() >=110) {
					a += b.substring(0, 109) +"<br>";
					b = b.substring(109);
				}
				a+= b +"<br>";
			}
		}
		a+="</html>";

		String[] arr = lines.toArray(new String[0]);
		String newFirstLine[] = arr[0].split(" ");
		numOfVaraialbe = Integer.parseInt(newFirstLine[2]);
		numOfExpression = Integer.parseInt(newFirstLine[3]);
		return a;
	}
}
