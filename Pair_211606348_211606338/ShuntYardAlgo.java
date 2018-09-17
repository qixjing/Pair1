package com.it666.zuoye;

import java.util.HashMap;
import java.util.Stack;
class Node {

	double val;
	char opt;

	Node(Double val, Character opt) {
		this.val = val;
		this.opt = opt;
	}
}

public class body {
	
	public static void main(String[] args) throws Exception {
		body body = new body();
		body.Init();

		String str = "5*10/2+7";
		char[] charArray = str.toCharArray();
		System.out.println(body.ShuntYardAlgo(charArray));

	}
	Node[] node = new Node[10005];
	Character[] str = new Character[10005];
	HashMap<Character, Integer> mp = new HashMap<>();

	void Init() {
		mp.put('(', 0);
		mp.put('-', 1);
		mp.put('+', 1);
		mp.put('*', 2);
		mp.put('/', 2);
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
				case '*':
					s.push(b * a);
					break;
				case '/':
					if (Math.abs(a) < 1e-9)
						throw new Exception();
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
				case '*':
				case '/':
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
