package com.it666.course1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class MathExam {
	public static void main(String[] args) throws Exception {
		//检测输入参数的各种bug
		if(!check(args))return;
		int m = 0;
		int l = 1;
		String str = null;
		// 生成2个字符串用于拼接题目和答案
		StringBuffer strbuf = new StringBuffer();
		StringBuffer strbuf1 = new StringBuffer();
		StringBuffer strbuf2 = new StringBuffer();
		StringBuffer strbuf3 = new StringBuffer();
		// random用于随机生成题目中的操作数
		int random = 0;
		// int flag=5;
		// 将四种运算符放入数组
		String[] strop = { "+", "-", "x", "÷" };
		// 将随机生成的2-4个运算符存入ostr数组
		String[] ostr = new String[4];

		for (int i = 0; i <= 100; i++) {
			// 随机生成的2-3个运算符
			int operator = (int) (Math.random() * 3) + 2;
			int k = 0;
			// 随机生成运算符号（至少有2种不同运算符号）
			for (int j = 0; j < operator; j++) {
				k = (int) (Math.random() * 4);
				ostr[j] = strop[k];
				if (operator == 2 && j == 1) {
					// 控制在只有2个运算符的情况下2个运算符号一定不同
					while (ostr[0] == ostr[1]) {
						k = (int) (Math.random() * 4);
						ostr[0] = strop[k];
					}
				}
			}
			int flag1 = 1;
			int flag = 1;
			for (int j = 0; j < operator + 1; j++) {
				if (operator == 4)
					// 如果运算符有+-就自动生成括号运算符
					if (j < operator && (ostr[j].equals("+") || ostr[j].equals("-")) && flag == 1) {
						strbuf.append("( ");
						flag = 0;// 判断是否加了左括号0代表已近加了
						flag1 = 0;// 判断是否可以加右括号了
					}
				random = (int) (Math.random() * 101);
				if (j == operator) {
					// 最后一个随机数的末尾不加空格
					if (flag == 0 && flag1 == 1) {
						strbuf.append(random + " )");
						flag1 = 0;
						flag = 100;
					} else {
						strbuf.append(random);
						if (flag == 0) {
							// 在最后一个数后面加上右括号
							flag1 = 1;
						}
					}

				} else {
					if (flag == 0 && flag1 == 1) {
						strbuf.append(random + " )" + " ");
						flag1 = 0;
						flag = 100;
					} else {
						strbuf.append(random + " ");
						if (flag == 0) {
							// 在下一次循环当中可以加入右括号
							flag1 = 1;
						}
					}
				}
				if (j < operator)
					strbuf.append(ostr[j] + " ");

			}
			l = 1;
			// 重新初始化数组
			ostr = new String[4];
			MathExam mathExam = new MathExam();
			mathExam.Init();
			str = strbuf.toString();

			char[] charArray = str.toCharArray();
			double shuntYardAlgo = mathExam.ShuntYardAlgo(charArray);
			String vf = String.valueOf(shuntYardAlgo);
			boolean matches = vf.matches("\\d+.[0]?");
			if (matches) {

				// strbuf2用于拼接题目
				strbuf2.append("(" + m + ")" + " " + strbuf + "\n");
				// strbuf1用于拼接答案
				strbuf1.append("(" + m + ")" + " " + strbuf + " " + "=" + " " + (int) shuntYardAlgo + "\n");
				m++;
				l = 0;
			}
			strbuf = new StringBuffer();
			if (l != 0)
				i--;
		}
		strbuf2.append("\n");
		strbuf2.append(strbuf1);
		System.out.println(strbuf2.toString());
	}

	public static boolean check(String[] args) {
		// 判断参数的长度是否为4
		if (args.length > 4 || args.length < 4)
			return false;

		// 判断是否用-n 和-grade的标识符
		if (!(("-n").equals(args[0]) && "-grade".equals(args[2]) || ("-n").equals(args[2]) && "-grade".equals(args[0])))
			return false;

		// 排除-n参数的000001这种类似的情况
		char[] allChar = args[1].toCharArray();
		char[] allChar1 = args[3].toCharArray();
		int k = 0;
		while (allChar[k++] == '0');
		args[1] = new String(allChar, k - 1, allChar.length - k + 1);
		k = 0;
		while (allChar1[k++] == '0');
		args[3] = new String(allChar1, k - 1, allChar1.length - k + 1);

		// 检查题目都是数字
		boolean matches;
		if (args[0].equals("-n")) {
			matches = args[1].matches("[0-9]*");
		} else {
			matches = args[3].matches("[0-9]*");
		}

		boolean matches1;
		// 检测年级参数是否是1-3
		if (args[0].equals("-garde")) {
			matches1 = args[1].matches("[1-3]?");
		} else {
			matches1 = args[3].matches("[1-3]?");
		}
		if (!matches1)
			return false;

		if (matches && (args[1].length() > 4 || args[3].length() > 4)) {
			System.out.println("题目数量太多了，重新输入！！");
			return false;
		} else {
			System.out.println("请输入正整数");
			return false;
		}

	}

	/*
	 * 以下是调度场算法和逆波兰表达式的实现
	 * 
	 */
	class Node {

		double val;
		char opt;

		Node(Double val, Character opt) {
			this.val = val;
			this.opt = opt;
		}
	}

	Node[] node = new Node[10005];
	Character[] str = new Character[10005];
	HashMap<Character, Integer> mp = new HashMap<>();

	void Init() {
		mp.put('(', 0);
		mp.put('-', 1);
		mp.put('+', 1);
		mp.put('x', 2);
		mp.put('÷', 2);
		mp.put('^', 3);
	}

	// 逆波兰表达式的计算
	double CalPoland(Node node[], int n) throws Exception {
		Stack<Double> s = new Stack<>();
		for (int i = 0; i < n; i++) {
			if (node[i].opt == ' ')
				s.push(node[i].val);
			else {
				double a = s.peek();
				s.pop();
				double b = s.peek();
				s.pop();
				switch (node[i].opt) {
				case '+':
					s.push(b + a);
					break;
				case '-':
					s.push(b - a);
					break;
				case 'x':
					s.push(b * a);
					break;
				case '÷':
					// if (Math.abs(a) < 1e-9)
					// throw new Exception();
					s.push(b / a);
					break;
				case '^':
					s.push(Math.pow(b, a));
					break;
				}
			}
		}
		return s.peek();
	}

	// 调度场算法
	double ShuntYardAlgo(char str[]) throws Exception {
		Stack<Character> oper = new Stack<>();

		// inNum标记当前是否可以输入数字
		boolean inNum = true;
		// hasDot标记是否已经输入小数点
		boolean hasDot = true;

		int p = 0; // 扫描指针位置
		int cnt = 0; // 符号或者数字计数器
		int sign = 1; // 表示正负符号

		while (p < str.length) {
			if (Character.isDigit(str[p]) || str[p] == '.') {
				if (inNum) {
					double val = 0;
					double w = 1;
					if (str[p] == '.') {
						hasDot = true;
						val = 0;
					} else {
						hasDot = false;
						val = str[p] - '0';
					}
					p++;
					while (p < str.length && (Character.isDigit(str[p]) || str[p] == '.')) {
						if (str[p] == '.') {
							if (hasDot)
								throw new Exception();
							hasDot = true;
						} else {
							if (hasDot) {
								w *= 0.1;
								val += (str[p] - '0') * w;
							} else
								val = val * 10 + str[p] - '0';
						}
						p++;
					}
					p--;
					node[cnt++] = new Node(val * sign, ' ');
					sign = 1;
					inNum = false;
				} else
					throw new Exception();
			} else {
				switch (str[p]) {
				case '(':
					oper.push(str[p]);
					break;
				case ')':
					while (!oper.empty() && oper.peek() != '(') {
						node[cnt++] = new Node(0.0, oper.peek());
						oper.pop();
					}
					if (oper.empty())
						throw new Exception();
					oper.pop();
					break;
				case '+':
				case '-':
				case 'x':
				case '÷':
				case '^':
					if (inNum) {
						if (str[p] != '+' && str[p] != '-')
							while (str[p] == '+' || str[p] == '-') {
								if (str[p] == '-')
									sign *= -1;
								p++;
							}
						p--;
					} else {
						while (!oper.empty() && mp.get(str[p]) <= mp.get(oper.peek())) {
							node[cnt++] = new Node(0.0, oper.peek());
							oper.pop();
						}
						oper.push(str[p]);
						inNum = true;
					}
					break;
				}
			}
			p++;
		}
		while (!oper.empty()) {
			node[cnt++] = new Node(0.0, oper.peek());
			oper.pop();
		}
		return CalPoland(node, cnt);
	}
}
