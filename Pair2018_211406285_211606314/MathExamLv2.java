package com.java285.second;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MathExamLv2 {
	private Object[] stack;
	private int size;// 元素个数;

	public MathExamLv2() {// 默认长度为10;
		this(10);
	}

	public MathExamLv2(int len) {// 也可以自己设置长度,即容量;
		stack = new Object[len];
	}

	public int size() {// 返回元素个数;
		return size;
	}

	public int capacity() {// 返回数组长度,即容量;
		return stack.length;
	}

	public void push(Object o) {// 入栈;
		size++;
		stack[size - 1] = o;
	}

	public boolean isEmpty() {// 判空;
		return size == 0;
	}

	public Object pop() {// 出栈;
		if (isEmpty()) {// 首先要判空;
			throw new ArrayIndexOutOfBoundsException("不能为空");
		}
		Object o = stack[--size];
		stack[size] = null;
		return o;
	}

	private static void gradeOne(int n) throws IOException, FileNotFoundException {
		int[] sum = new int[n + 1];// 创建sum数组用来保存答案
		char[] signSet = { '+', '-' };// 创建sianSet数组用来保存字符组
		Random random = new Random();

		for (int i = 1; i <= n; i++) {
			String str = "(" + i + ")";
			int a = random.nextInt(100) + 1;
			int b = random.nextInt(100) + 1;
			int t = random.nextInt(signSet.length);
			char sign = signSet[t];

			switch (sign) {
			case '+':
				sum[i] = a + b;
				break;
			case '-':
				sum[i] = a - b;
				if (sum[i] < 0) {
					i = i - 1;
					continue;
				}
				break;
			default:
				;
			}
			str = str + " " + a + " " + sign + " " + b + " " + "=";
			out(str);
			if (i == n) {
				out("----------------标准答案----------------");
			}
		} // 这个for循环是用来确立要写入到out.txt中的str的值，也就是要出的题目

		BufferedReader br = in();

		for (int i = 1; i <= n; i++) {
			String str = br.readLine() + " " + sum[i];
			out(str);
			if (i == n) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm");// 设置日期格式
				out("211406285 林志松" + " " + df.format(new Date()));
			}
		} // 这个for循环是用来确立要写入到out.txt中的str的值，也就是要出的题目的答案
	}

	private static void gradeTwo(int n) throws IOException, FileNotFoundException {
		String[] sum = new String[n + 1];// 创建sum数组用来保存答案
		char[] signSet = { '*', '/' };// 创建sianSet数组用来保存字符组
		Random random = new Random();

		for (int i = 1; i <= n; i++) {
			String str = "(" + i + ")";
			int a = random.nextInt(100) + 1;
			int b = random.nextInt(100) + 1;
			int t = random.nextInt(signSet.length);
			char sign = signSet[t];

			switch (sign) {
			case '*':
				sum[i] = String.valueOf(a * b);
				sign = '×';
				break;
			case '/':
				if (b == 0 && a > b) {
					i = i - 1;
					continue;
				}
				sum[i] = String.valueOf(a / b);
				if (a % b != 0) {
					sum[i] = sum[i] + "..." + String.valueOf(a % b);
				}
				sign = '÷';
				break;
			default:
				;
			}
			str = str + " " + a + " " + sign + " " + b + " " + "=";
			out(str);
			if (i == n) {
				out("----------------标准答案----------------");
			}
		} // 这个for循环是用来确立要写入到out.txt中的str的值，也就是要出的题目

		BufferedReader br = in();

		for (int i = 1; i <= n; i++) {
			String str = br.readLine() + " " + sum[i];
			out(str);
			if (i == n) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm");// 设置日期格式
				out("211406285 林志松" + " " + df.format(new Date()));
			}
		} // 这个for循环是用来确立要写入到out.txt中的str的值，也就是要出的题目的答案
	}

	private static void gradeThree(int n) throws IOException, FileNotFoundException {
		String[] sum = new String[n + 1];// 创建sum数组用来保存答案
		char[] signSet = { '*', '/' };// 创建sianSet数组用来保存字符组
		Random random = new Random();

		for (int i = 1; i <= n; i++) {
			String str = "(" + i + ")";
			int a = random.nextInt(100) + 1;
			int b = random.nextInt(100) + 1;
			int t = random.nextInt(signSet.length);
			char sign = signSet[t];

			switch (sign) {
			case '*':
				sum[i] = String.valueOf(a * b);
				sign = '×';
				break;
			case '/':
				if (b == 0 && a > b) {
					i = i - 1;
					continue;
				}
				sum[i] = String.valueOf(a / b);
				if (a % b != 0) {
					sum[i] = sum[i] + "..." + String.valueOf(a % b);
				}
				sign = '÷';
				break;
			default:
				;
			}
			str = str + " " + a + " " + sign + " " + b + " " + "=";
			out(str);
			if (i == n) {
				out("----------------标准答案----------------");
			}
		} // 这个for循环是用来确立要写入到out.txt中的str的值，也就是要出的题目

		BufferedReader br = in();

		for (int i = 1; i <= n; i++) {
			String str = br.readLine() + " " + sum[i];
			out(str);
			if (i == n) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm");// 设置日期格式
				out("211406285 林志松" + " " + df.format(new Date()));
			}
		} // 这个for循环是用来确立要写入到out.txt中的str的值，也就是要出的题目的答案
	}

	private static BufferedReader in() throws FileNotFoundException {
		File file = new File("out.txt");
		InputStreamReader in = new InputStreamReader(new FileInputStream(file));// 建立一个输入流的对象
		BufferedReader br = new BufferedReader(in);// 建立一个缓冲对象，把文件内容转换成计算机能识别的对象
		return br;
	}

	private static void out(String str) throws IOException {
		File file = new File("out.txt");// 定义一个文件对象并令其指向一个文件位置
		file.createNewFile();// 在指定位置创建一个文件
		BufferedWriter br = new BufferedWriter(new FileWriter(file, true));// 建立一个缓冲对象，把文件内容转换成计算机能识别的对象
		br.write(str + "\r\n");
		br.flush();
		br.close();
	}

	private static void wrongJudgement(String[] args, int n, int grade) throws IOException, FileNotFoundException {
		if (args.length < 4) {
			System.out.println("请按照 -n n -grade grade 或者 -grade grade -n n的方式输入4个参数！");
			return;
		}

		switch (args[0]) {
		case "-n": {
			if (args[1].length() > 8) {
				System.out.println("输入的题目数量太多了！");
			}
			n = Integer.parseInt(args[1]);
			break;
		}
		case "-grade": {
			grade = Integer.parseInt(args[1]);
			if (grade < 1 || grade > 3) {
				System.out.println("grade只能取1~3！");
			}
			break;
		}
		default:
			System.out.println("请按照 -n n -grade grade 或者 -grade grade -n n的方式输入4个参数！");
			break;
		}

		switch (args[2]) {
		case "-n": {
			if (args[1].length() > 8) {
				System.out.println("输入的题目数量太多了！");
			}
			n = Integer.parseInt(args[3]);
			break;
		}
		case "-grade": {
			grade = Integer.parseInt(args[3]);
			if (grade < 1 || grade > 3) {
				System.out.println("grade只能取1~3！");
			}
			break;
		}
		default:
			System.out.println("请按照 -n n -grade grade 或者 -grade grade -n n的方式输入4个参数！");
			break;
		}
		if (n != -1 && grade != -1) {
			if (grade == 1) {
				gradeOne(n);
			}
			if (grade == 2) {
				gradeTwo(n);
			}
			if (grade == 3) {
				gradeThree(n);
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		int n = -1, grade = -1;
		// 通过判断args的数组长度来判断参数的个数是否输入错误
		wrongJudgement(args, n, grade);
	}
}
