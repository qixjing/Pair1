import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MathExam {

	public static char RnadomOpes() {
		int Temp = Random(1, 4);
		switch (Temp) {
		case 1: {
			return '+';
		}
		case 2: {
			return '-';
		}
		case 3: {
			return '×';
		}
		case 4: {
			return '÷';
		}
		}
		return ' ';

	}

	public static int Random(int Min, int Max) {
		return (int) (Min + Math.random() * (Max - Min + 1));
	}

	public static int Calc(String Question) {
		Stack<Integer> nums = new Stack<Integer>(); // 栈 保存数字
		Stack<Character> opes = new Stack<Character>(); // 栈 保存操作符
		String string = Question.replace(" ", "");// 获取临时字符串
		char symbol;
		int n = 0; // 保存每一个数字
		char[] cs = string.toCharArray();// 字符串转换为char数组
		for (int i = 0; i < cs.length; i++) {// 遍历char数组
			char temp = cs[i];
			if (Character.isDigit(cs[i])) {// 判断当前char是否为数字
				n = 10 * n + Integer.parseInt(String.valueOf(temp)); // 临时保存大于10的数字
			} else {// 非数字
				if (n != 0) {
					nums.push(n);// 数字入栈
					n = 0;
				}
				if (temp == '(') {// 如果遇到左括号
					opes.push(temp);// 入栈
				} else if (temp == ')') {// 如果是遇到右括号
					while (opes.peek() != '(') { // 括号里面运算完(查看堆栈顶部的对象，但不从堆栈中移除它)
						symbol = opes.pop();
						int t = cal(nums.pop(), nums.pop(), symbol);// 计算两个数据
						if (t < 0 && symbol == '-') {
							return -1;
						}
						if (t == 0 && symbol == '÷') {
							return -1;
						}
						if (t < 0 && symbol == '÷') {
							return -1;
						}
						nums.push(t);// 计算结果入栈
					}
					opes.pop();
				} else if (isType(temp) > 0) {
					if (opes.isEmpty()) { // 栈为空直接入栈
						opes.push(temp);
					} else {
						// 若栈顶元素优先级大于或等于要入栈的元素,将栈顶元素弹出并计算,然后入栈
						if (isType(opes.peek()) >= isType(temp)) {
							symbol = opes.pop();
							int t = cal(nums.pop(), nums.pop(), symbol);// 计算两个数据
							if (t < 0 && symbol == '-') {
								return -1;
							}
							if (t == 0 && symbol == '÷') {
								return -1;
							}
							if (t < 0 && symbol == '÷') {
								return -1;
							}
							nums.push(t);
						}
						opes.push(temp);
					}
				}
			}
		}
		// 最后一个字符若是数字,未入栈
		if (n != 0) {
			nums.push(n);
		}
		while (!opes.isEmpty()) {
			symbol = opes.pop();
			int t = cal(nums.pop(), nums.pop(), symbol);// 计算两个数据
			if (t < 0 && symbol == '-') {
				return -1;
			}
			if (t == 0 && symbol == '÷') {
				return -1;
			}
			if (t < 0 && symbol == '÷') {
				return -1;
			}
			nums.push(t);
		}
		return nums.pop();
	}

	// 返回的是运算符的优先级,数字和()不需要考虑
	public static int isType(char c) {
		if (c == '+' || c == '-') {
			return 1;
		} else if (c == '×' || c == '÷') {
			return 2;
		} else {
			return 0;
		}
	}

	// 运算次序是反的,跟入栈出栈次序有关
	public static int cal(int m, int n, char c) {
		int sum = -1;
		if (c == '+') {
			sum = n + m;
		} else if (c == '-') {
			sum = n - m;
		} else if (c == '×') {
			sum = n * m;
		} else if (c == '÷') {
			if (n % m != 0) {
				return -1;
			}
			sum = n / m;
		}
		return sum;
	}

	// 判断是否为阿拉伯数字
	public static boolean isdigit(char chr) {
		if (chr < 48 || chr > 57) {
			return false;
		} else {
			return true;
		}
	}

	// 判断是否为纯数字
	public static boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

	// 取一个随机数
	public static int GetRandomNum() {
		// (数据类型)(最小值+Math.random()*(最大值-最小值+1))
		return (int) (0 + Math.random() * (100 - 0 + 1));
	}

	// 生成题目(添加年级参数)
	public static void GetQuestion(int num, int Grade) {

		switch (Grade) {

		case 1: {
			String TempQuestion = "";
			String TempAnswer = "";
			for (int i = 0; i < num; i++) {
				int Temp = GetRandomNum();
				int Temp2;
				if (GetRandomNum() > 49) {
					Temp2 = (int) (0 + Math.random() * ((100 - Temp) - 0 + 1));
					TempQuestion = TempQuestion + "(" + String.valueOf(i + 1) + ") " + String.valueOf(Temp) + " + "
							+ String.valueOf(Temp2) + " =" + "\r\n";
					TempAnswer = TempAnswer + "(" + String.valueOf(i + 1) + ") " + String.valueOf(Temp) + " + "
							+ String.valueOf(Temp2) + " = " + String.valueOf(Temp + Temp2) + "\r\n";
				} else {
					Temp2 = (int) (0 + Math.random() * (Temp - 0 + 1));
					TempQuestion = TempQuestion + "(" + String.valueOf(i + 1) + ") " + String.valueOf(Temp) + " - "
							+ String.valueOf(Temp2) + " =" + "\r\n";
					TempAnswer = TempAnswer + "(" + String.valueOf(i + 1) + ") " + String.valueOf(Temp) + " - "
							+ String.valueOf(Temp2) + " = " + String.valueOf(Temp - Temp2) + "\r\n";
				}
			}
			System.out.println(TempQuestion + "\r\n" + TempAnswer);
			OutAnswer(TempQuestion + "\r\n" + TempAnswer);
			break;
		}

		case 2: {
			String Question = "";
			String Answer = "";
			for (int j = 0; j < num; j++) {
				int Ismul = GetRandomNum();
				int mul1 = (int) (0 + Math.random() * (10 - 0 + 1));
				if (Ismul > 49) {
					int mul2 = (int) (0 + Math.random() * (10 - 0 + 1));
					Question = Question + "(" + String.valueOf(j + 1) + ") " + String.valueOf(mul1) + " × "
							+ String.valueOf(mul2) + "\r\n";
					Answer = Answer + "(" + String.valueOf(j + 1) + ") " + String.valueOf(mul1) + " × "
							+ String.valueOf(mul2) + " = " + String.valueOf(mul1 * mul2) + "\r\n";
				} else {
					int roo;
					// 数据范围需要注意
					int mul2 = (int) (1 + Math.random() * (mul1 - 1 + 1));
					roo = mul1 % mul2;
					if (roo == 0) {
						Question = Question + "(" + String.valueOf(j + 1) + ") " + String.valueOf(mul1) + " ÷ "
								+ String.valueOf(mul2) + "\r\n";
						Answer = Answer + "(" + String.valueOf(j + 1) + ") " + String.valueOf(mul1) + " ÷ "
								+ String.valueOf(mul2) + " = " + String.valueOf(mul1 / mul2) + "\r\n";
					} else {
						Question = Question + "(" + String.valueOf(j + 1) + ") " + String.valueOf(mul1) + " ÷ "
								+ String.valueOf(mul2) + "\r\n";
						Answer = Answer + "(" + String.valueOf(j + 1) + ") " + String.valueOf(mul1) + " ÷ "
								+ String.valueOf(mul2) + " = " + String.valueOf((int) mul1 / mul2) + "..."
								+ String.valueOf(roo) + "\r\n";
					}

				}
			}
			System.out.println(Question + "\r\n" + Answer);
			OutAnswer(Question + "\r\n" + Answer);
			break;

		}

		case 3: {
			String Questions = "";
			String Answers = "";
			String TempQuestion = "";
			String TempAnswer = "";
			for (int k = 0; k < num; k++) {
				int Answer = -1;
				while (Answer < 0) {
					int Num = Random(2, 4);
					if (Num == 2) {
						int TempNum1 = Random(0, 100);
						int TempNum2 = Random(0, 100);
						int TempNum3 = Random(0, 100);
						char Tempopes1 = ' ';
						char Tempopes2 = ' ';
						while (Tempopes1 == Tempopes2) {
							Tempopes1 = RnadomOpes();
							Tempopes2 = RnadomOpes();
						}
						boolean isBracket = false;
						if (Random(1, 2) == 2) {
							isBracket = true;
						}
						if (isBracket == false) {
							TempQuestion = String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " "
									+ String.valueOf(TempNum2) + " " + String.valueOf(Tempopes2) + " "
									+ String.valueOf(TempNum3);
							TempAnswer = String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " "
									+ String.valueOf(TempNum2) + " " + String.valueOf(Tempopes2) + " "
									+ String.valueOf(TempNum3) + " = " + String.valueOf(Calc(TempQuestion));
						} else {
							if (isType(Tempopes1) != isType(Tempopes2)) {
								if (isType(Tempopes1) < isType(Tempopes2)) {
									TempQuestion = "( " + String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1)
											+ " " + String.valueOf(TempNum2) + " ) " + String.valueOf(Tempopes2) + " "
											+ String.valueOf(TempNum3);
									TempAnswer = "( " + String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " "
											+ String.valueOf(TempNum2) + " ) " + String.valueOf(Tempopes2) + " "
											+ String.valueOf(TempNum3) + " = " + String.valueOf(Calc(TempQuestion));
								} else {
									TempQuestion = String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " ( "
											+ String.valueOf(TempNum2) + " " + String.valueOf(Tempopes2) + " "
											+ String.valueOf(TempNum3) + " )";
									TempAnswer = String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " ( "
											+ String.valueOf(TempNum2) + " " + String.valueOf(Tempopes2) + " "
											+ String.valueOf(TempNum3) + " )" + " = "
											+ String.valueOf(Calc(TempQuestion));
								}
							} else {
								TempQuestion = String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " "
										+ String.valueOf(TempNum2) + " " + String.valueOf(Tempopes2) + " "
										+ String.valueOf(TempNum3);
								TempAnswer = String.valueOf(TempNum1) + " " + String.valueOf(Tempopes1) + " "
										+ String.valueOf(TempNum2) + " " + String.valueOf(Tempopes2) + " "
										+ String.valueOf(TempNum3) + " = " + String.valueOf(Calc(TempQuestion));
							}
						}
						Answer = Calc(TempQuestion);
						TempQuestion = "(" + String.valueOf(k + 1) + ") " + TempQuestion + "\r\n";
						TempAnswer = "(" + String.valueOf(k + 1) + ") " + TempAnswer + "\r\n";
					} else {
						if (Num == 3) {
							char Tempopes = ' ';
							char Tempopes1 = ' ';
							char Tempopes2 = ' ';
							char Tempopes3 = ' ';
							while (Tempopes1 == Tempopes2 && Tempopes1 == Tempopes3) {
								Tempopes1 = RnadomOpes();
								Tempopes2 = RnadomOpes();
								Tempopes3 = RnadomOpes();
							}
							boolean isBracket = false;
							if (Random(1, 2) == 2) {
								isBracket = true;
							}
							TempQuestion = String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes1) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes2) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes3) + " "
									+ String.valueOf(Random(0, 100));
							TempAnswer = String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes1) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes2) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes3) + " "
									+ String.valueOf(Random(0, 100)) + " = " + String.valueOf(Calc(TempQuestion));
							Answer = Calc(TempQuestion);
							TempQuestion = "(" + String.valueOf(k + 1) + ") " + TempQuestion + "\r\n";
							TempAnswer = "(" + String.valueOf(k + 1) + ") " + TempAnswer + "\r\n";

						} else {
							char Tempopes = ' ';
							char Tempopes1 = ' ';
							char Tempopes2 = ' ';
							char Tempopes3 = ' ';
							char Tempopes4 = ' ';
							while (Tempopes1 == Tempopes2 && Tempopes1 == Tempopes3 && Tempopes1 == Tempopes4) {
								Tempopes1 = RnadomOpes();
								Tempopes2 = RnadomOpes();
								Tempopes3 = RnadomOpes();
								Tempopes4 = RnadomOpes();
							}
							// Question = "(" + String.valueOf(k+1) + ") "
							TempQuestion = String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes1) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes2) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes3) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes4) + " "
									+ String.valueOf(Random(0, 100));
							TempAnswer = String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes1) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes2) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes3) + " "
									+ String.valueOf(Random(0, 100)) + " " + String.valueOf(Tempopes4) + " "
									+ String.valueOf(Random(0, 100)) + " = " + String.valueOf(Calc(TempQuestion));
							Answer = Calc(TempQuestion);
							TempQuestion = "(" + String.valueOf(k + 1) + ") " + TempQuestion + "\r\n";
							TempAnswer = "(" + String.valueOf(k + 1) + ") " + TempAnswer + "\r\n";

						}

					}
				}
				Questions = Questions + "\r\n" + TempQuestion;
				Answers = Answers + "\r\n" + TempAnswer;
			}
			System.out.println(Questions + "\r\n" + Answers);
			OutAnswer(Questions + "\r\n" + Answers);
			break;
		}

		}

	}

	// 输出文件
	// 记事本中需要/r/n才可以换行
	public static void OutAnswer(String str) {
		try {
			PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
			writer.println(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 主函数
	public static void main(String[] args) {
		String Path;
		String Grade;
		int n = 0;
		// System.out.println("GetPath:"+args.length);
		// 判断命令行参数个数
		if (args.length != 4) {
			System.out.println("Error:001");
			return;
		}
		if(args[0].equals("-n") == false && args[0].equals("-Grade") == false) {
			System.out.println("Error:002");
			return;
		}
		if(args[2].equals("-n") == false && args[2].equals("-Grade") == false) {
			System.out.println("Error:003");
			return;
		}
		if(args[0].equals("-n")) {
			Grade = args[3];
			Path = args[1];
		}else
		{
			Grade = args[1];
			Path = args[3];
		}
		// 判断题目个数是否为纯数字
		if (!isNum(Path)) {
			System.out.println("Error:004");
			return;
		}
		// 判断年级是否正确
		if (Integer.valueOf(Grade) != 1 && Integer.valueOf(Grade) != 2 && Integer.valueOf(Grade) != 3) {
			System.out.println("Error:005");
			return;
		}
		n = Integer.valueOf(Path);
		// 判断输入参数是否不为0
		if (n == 0) {
			System.out.println("Error:006");
			return;
		}
		GetQuestion(n, Integer.valueOf(Grade));
	}

}
