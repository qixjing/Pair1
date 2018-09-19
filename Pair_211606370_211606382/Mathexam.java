import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Stack;

public class Mathexam {

	static StringBuffer wenti = new StringBuffer();
	static StringBuffer daan = new StringBuffer();
	static int result;
	static int n;
	static int grade;

	public static boolean test(String A, String B) {
		grade = Integer.parseInt(B);
		n = Integer.parseInt(A);
		if (n > 100) {
			System.out.println("输入的题数过多，请输入100以下的数字");
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		 problems(10, 3);
		 File file = new File("out.txt");
		 try {
		 System.setOut(new PrintStream(new BufferedOutputStream(new
		 FileOutputStream("out.txt")), true));
		 } catch (FileNotFoundException e) {
		 e.printStackTrace();
		 }
		 problems(20, 3);
	}

	private static void problems(int n, int grade) {
		String[] fuhao = { " + ", " - ", " * ", " / " };
		String fengge = "\r\n";
		Random rand = new Random();
		String a = "";
		int S_num1 = (rand.nextInt(101));
		int i = 0;
		String S_wenti = S_num1 + "";

		if (grade == 1) {
			for (i = 1; i <= n; i++) {
				int operator = (int) (Math.random() * 2);
				if (operator == 0) {
					int num1 = (int) (Math.random() * 10);
					int num2 = (int) (Math.random() * 10);
					result = num1 + num2;

					wenti.append("(" + i + ") " + num1 + " + " + num2 + "\r\n");
					daan.append("(" + i + ") " + num1 + " + " + num2 + " = " + result + "\r\n");
				} else if (operator == 1) {
					int x = (int) (Math.random() * 10);
					int y = (int) (Math.random() * 10);
					result = x - y;

					wenti.append("(" + i + ") " + x + " - " + y + "\r\n");
					daan.append("(" + i + ") " + x + " - " + y + " = " + result + "\r\n");
				}
			}
			System.out.println(wenti);
			System.out.println(daan);

		}

		else if (grade == 2) {
			for (i = 1; i <= n; i++) {
				int operator = (int) (Math.random() * 2);
				if (operator == 0) {
					int num1 = (int) (Math.random() * 10);
					int num2 = (int) (Math.random() * 10);
					result = num1 * num2;

					wenti.append("(" + (i + 1) + ") " + num1 + " * " + num2 + "\r\n");
					daan.append("(" + (i + 1) + ") " + num1 + " * " + num2 + " = " + result + "\r\n");
				} else if (operator == 1) {
					int x = (int) (Math.random() * 10);
					int y = (int) (Math.random() * 10);
					if (y == 0) {
						System.out.println("除數不能为零");
					}
					if (x % y == 0) {
						result = x / y;
						wenti.append("(" + i + ") " + x + " / " + y + "\r\n");
						daan.append("(" + i + ") " + x + " / " + y + " = " + result + "\r\n");

					}
					if (x % y != 0) {
						result = x / y;
						wenti.append("(" + i + ") " + x + " / " + y + "\r\n");
						daan.append("(" + i + ") " + x + " / " + y + " = " + result + "..." + (x % y) + "\r\n");
					}
				}
			}
			System.out.println(wenti);
			System.out.println(daan);
		}

		if (grade == 3) {
			outLoop:for (i = 1; i <= n; i++) {
				int OPNum = (rand.nextInt(3) + 2);
				int S_num2 = (rand.nextInt(100) + 1);
				S_wenti = S_num2 + "";
				for (int j = 1; j <= OPNum; j++) {
					int S_num = S_num2;
					S_num2 = (rand.nextInt(1000) + 1);
					int op = (rand.nextInt(4));
					if (op == 0) {
						if (S_num + S_num2 > 1000) {
							j--;
							continue;
						}
					} else if (op == 1) {
						if (S_num - S_num2 < 0) {
							j--;
							continue;
						}
				} else if (op == 2) {
						if (S_num1 * S_num2 > 10000) {
							j--;
							continue;
						}
					} else if (op == 3) {
						if (S_num1 % S_num2 != 0 || S_num2 == 0) {
							j--;
							continue;
						}
					}
					a = S_wenti + S_num1 + fuhao[op] + S_num2;
					S_wenti += fuhao[op] + S_num2;
					if (Integer.parseInt(answers(S_wenti))<0 || Integer.parseInt(answers(S_wenti))>100000) {
						i--;
						continue outLoop;
					}
				}
				
				wenti.append("(" + i + ") " + S_wenti + "\r\n");
				
				daan.append("(" + i + ") " + S_wenti + " = " + answers(S_wenti) + "\r\n");
				//S_num1 = (rand.nextInt(1001));
				//S_wenti = S_num1 + "";
			}
			System.out.println(wenti);
			System.out.println(daan);
		}

	}

	public static String answers(String S_wenti) {
		Stack<String> number1 = new Stack<String>();
		Stack<String> op_Stack = new Stack<String>();
		String stc = S_wenti.replace(" ", "");
		String number = "";
		for (int i = 0; i < stc.length(); i++) {
			char c = stc.charAt(i);
			if (c >= '0' && c <= '9') {
				number += c + "";
				if (i + 1 >= stc.length()) {
					number1.push(number);
					number = "";
				}
			} 
			else {
				if (!number.isEmpty()) {
					number1.push(number);
					number = "";
				}
				
				if (!op_Stack.empty() && comparePriority(c + "", op_Stack.peek()) < 1 ) {
					while (!op_Stack.empty() && comparePriority(c + "", op_Stack.peek()) < 1) {

						int a = Integer.parseInt(number1.pop());
						int b = Integer.parseInt(number1.pop());
						int d;
						String stackTop = op_Stack.peek();
						if (stackTop.equals("+")) {
							d = b + a;
						} else if (stackTop.equals("-")) {
							d = b - a;
						} else if (stackTop.equals("*")) {
							d = b * a;
						} else {
							d = b / a;
						}
						number1.push(d + "");
						stackTop = op_Stack.pop();
					}

					op_Stack.push(c + "");
				}
				else
					op_Stack.push(c+"");
			}

		}

		while (!op_Stack.empty()) {
			String stackTop = op_Stack.pop();
			int a = Integer.parseInt(number1.pop());
			int b = Integer.parseInt(number1.pop());
			int d;
			if (stackTop.equals("+")) {
				d = b + a;
			} else if (stackTop.equals("-")) {
				d = b - a;
			} else if (stackTop.equals("*")) {
				d = b * a;
			} else {
				d = b / a;
			}
			number1.push(d + "");
		}

		return number1.peek();
	}

	private static int comparePriority(String a, String b) {
		if (a.equals(b)) {
			return 0;
		} else if (adv(a) > adv(b)) {
			return 1;
		} else if (adv(a) < adv(b)) {
			return -1;
		} else {
			return 0;
		}
	}

	private static int adv(String op) {
		if (op.equals("*") || op.equals("/")) {
			return 2;
		} else if (op.equals("+") || op.equals("-")) {
			return 1;

		}
		// if(op.equals("(")){
		// return 0;
		// }
		return 0;

	}

	private static void out(String filename) throws IOException {
		File file = new File(filename);
		File parentFile = file.getParentFile();
		if (parentFile != null && !parentFile.exists()) {
			parentFile.mkdirs();
			System.out.println("创建目录：" + parentFile);
		}
		file.createNewFile();
		OutputStream a = new FileOutputStream(file);
		a.write(wenti.toString().getBytes());
		a.write(System.lineSeparator().getBytes());
		a.write(daan.toString().getBytes());
		a.close();
	}

}
