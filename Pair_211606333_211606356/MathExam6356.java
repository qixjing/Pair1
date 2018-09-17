import java.io.File;
import java.util.*;
import java.text.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

import com.sun.org.apache.xml.internal.security.Init;

public class MathExam6356 {
	static int n;
	static int grade;
	static int[] a;
	static int[] b;
	static int[] c;
	static Random fuhao = new Random();
	static Random num1 = new Random();
	static Random num2 = new Random();// 种子相同的Random对象，生成的随机数序列是一样的。

	public static void main(String[] args) throws FileNotFoundException {
		init(args);

	}

	private static void init(String[] input) throws FileNotFoundException {
		if (input.length == 0) {
			// 默认参数
			n = 10;
			grade = 1;
		} else if (input.length == 4) {
			if (input[0].equals("-n") && input[2].equals("-grade")) {
				try {
					n = Integer.parseInt(input[1]);
					grade = Integer.parseInt(input[3]);
				} catch (NumberFormatException e) {
					System.out.println("请输入正整数！");
				}

			} else if (input[2].equals("-n") && input[0].equals("-grade")) {
				try {
					n = Integer.parseInt(input[3]);
					grade = Integer.parseInt(input[1]);
				} catch (NumberFormatException e) {
					System.out.println("请输入正整数！");
				}

			}
		} else {
			System.out.println("您输入的参数有误，请重新运行！");
			System.exit(0);
		}
		if (grade == 1) {
			gradeOne();
		} else if(grade==2) {
			gradeTwo();
		}else if (grade==3) {
			System.out.println("制作中");
			System.exit(0);
		}else {
			System.out.println("目前只支持1-3年级，请重新输入");
		}
		File file = new File("out.txt");
		PrintStream ps = new PrintStream(file);
		System.setOut(ps);// 把创建的打印输出流赋给系统。
		outPut();
	}

	private static void gradeTwo() {
		a = new int[n];
		b = new int[n];
		c = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = num1.nextInt(9); // 0-100的随机数
			b[i] = num2.nextInt(9);
			c[i] = fuhao.nextInt(2);// 0是乘法1是除法
			if (c[i] == 1 || b[i] == 0) {// 除数是0重新随机
				b[i] = num2.nextInt(8) + 1;
			}
			if (c[i] == 0 || (a[i] == 0 && b[i] == 0)) {
				a[i] = num2.nextInt(8) + 1;
				b[i] = num2.nextInt(8) + 1;
			}
		}

	}

	public static void outPut() {
		for (int i = 0; i < n; i++) {
			if (grade == 1) {
				if (c[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "+" + b[i] + " =");
				} else {
					System.out.println("(" + (i + 1) + ") " + a[i] + "-" + b[i] + " =");
				}
			} else {
				if (c[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "*" + b[i] + " =");
				} else {
					System.out.println("(" + (i + 1) + ") " + a[i] + "/" + b[i] + " =");
				}
			}
		}
		System.out.println("--------------标准答案----------------");
		for (int i = 0; i < n; i++) {
			if (grade == 1) {
				if (c[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "+" + b[i] + " =" + " " + (a[i] + b[i]));
				} else {
					System.out.println("(" + (i + 1) + ") " + a[i] + "-" + b[i] + " =" + " " + (a[i] - b[i]));
				}
			} else {
				if (c[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "*" + b[i] + " =" + " " + (a[i] * b[i]));
				} else if (a[i] % b[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "/" + b[i] + " =" + " " + (a[i] / b[i]));
				} else {
					System.out.println("(" + (i + 1) + ") " + a[i] + "/" + b[i] + " =" + " " + (a[i] / b[i]) + "..."
							+ (a[i] % b[i]));

				}
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		String time = sdf.format(new Date());
		System.out.println("211606356 陈宇 " + time);// 输出学号姓名时间
	}

	public static void gradeOne() {
		a = new int[n];
		b = new int[n];
		c = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = num1.nextInt(100);// 0-100的随机数
			b[i] = num2.nextInt(100);
			c[i] = fuhao.nextInt(2);
		}
		for (int i = 0; i < n; i++) {
			if (c[i] == 0) {// 随机加法
				while (a[i] + b[i] > 100) {
					// 随机减法
					a[i] = num1.nextInt(100);
					b[i] = num2.nextInt(100);
				}

			} else {// 随机减法
				while (a[i] - b[i] < 0) {
					// 如果差小于0重新随机
					a[i] = num1.nextInt(100);
					b[i] = num2.nextInt(100);
				}
			}

		}
	}
}
