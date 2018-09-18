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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class MathExamLv2 {
	public static String answer="";
    //用于记录操作符
    private static LinkedList<String> operators=new LinkedList<>();
    //用于记录输出
    private static LinkedList<String> output=new LinkedList<>();
    //用于展示后缀表达式
    private static StringBuilder sb=new StringBuilder();
    
    //中缀表达式转为后缀表达式
    private static void transferToPostfix(LinkedList<String> list){
        Iterator<String> it=list.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (isOperator(s)) {
                if (operators.isEmpty()) {
                    operators.push(s);
                }
                else {
                    //如果读入的操作符为非")"且优先级比栈顶元素的优先级高或一样，则将操作符压入栈
                    if (priority(operators.peek())<priority(s)&&!s.equals(")")) {
                        operators.push(s);
                    }
                    else if(!s.equals(")")&&priority(operators.peek())>=priority(s)){
                        while (operators.size()!=0&&priority(operators.peek())>=priority(s)
                                &&!operators.peek().equals("(")) {
                            if (!operators.peek().equals("(")) {
                                String operator=operators.pop();
                                sb.append(operator).append(" ");
                                output.push(operator);
                            }
                        }
                        operators.push(s);
                    }
                    //如果读入的操作符是")"，则弹出从栈顶开始第一个"("及其之前的所有操作符
                    else if (s.equals(")")) {
                        while (!operators.peek().equals("(")) {
                            String operator=operators.pop();
                            sb.append(operator).append(" ");
                            output.push(operator);
                        }
                        //弹出"("
                        operators.pop();
                    }
                }
            }
            //读入的为非操作符
            else {
                sb.append(s).append(" ");
                output.push(s);
            }
        }
        if (!operators.isEmpty()) {
            Iterator<String> iterator=operators.iterator();
            while (iterator.hasNext()) {
                String operator=iterator.next();
                sb.append(operator).append(" ");
                output.push(operator);
                iterator.remove();
            }
        }
        calculate();
        //Collections.reverse(output);
    }
    //根据后缀表达式计算结果
    private static void calculate(){
        LinkedList<String> mList=new LinkedList<>();
        String[] postStr=sb.toString().split(" ");
        for (String s:postStr) {
            if (isOperator(s)){
                if (!mList.isEmpty()){
                    int num1=Integer.valueOf(mList.pop());
                    int num2=Integer.valueOf(mList.pop());
                    if (s.equals("/") && num1==0){
                    	answer="wrong";
                    	sb.setLength(0);
                        return;
                    }
                    if (s.equals("/")&& (num2%num1!=0)){
                    	answer="wrong";
                    	sb.setLength(0);
                        return;
                    }
                    if (s.equals("-")&& num2<=num1){
                    	answer="wrong";
                    	sb.setLength(0);
                        return;
                    }
                    int newNum=cal(num2,num1,s);
                    mList.push(String.valueOf(newNum));
                }
            }
            else {
                //数字则压入栈中
                mList.push(s);
            }
        }
        if (!mList.isEmpty()){
            answer=mList.pop();
        }
        sb.setLength(0);
    }
    //判断是否操作符
    private static boolean isOperator(String oper){
        if (oper.equals("+")||oper.equals("-")||oper.equals("/")||oper.equals("*")
                ||oper.equals("(")||oper.equals(")")) {
            return true;
        }
        return false;
    }
    //计算操作符的优先级
    private static int priority(String s){
        switch (s) {
            case "+":return 1;
            case "-":return 1;
            case "*":return 2;
            case "/":return 2;
            case "(":return 3;
            case ")":return 3;
            default :return 0;
        }
    }
    //进行计算
    private static int cal(int num1,int num2,String operator){
        switch (operator){
            case "+":return num1+num2;
            case "-":return num1-num2;
            case "*":return num1*num2;
            case "/":return num1/num2;
            default :return 0;
        }
    }
    //一年级的题目
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
				out("\r\n");
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
	//二年级的题目
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
				out("\r\n");
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
	//三年级的题目
	private static void gradeThree(int n) throws IOException, FileNotFoundException {
		String[] sum = new String[n];
		Random random = new Random();
		char[] signSet = { '*', '/' ,'+','-'};
		for(int i=0;i<n;i++) {
			int sumOfSign=random.nextInt(2)+2;
			int flag=0;
			if(sumOfSign>1) {
				flag=random.nextInt(2);//令括号在其他运算符大于一个的时候有二分之一的概率出现
			}
			int number[] = new int[4];
			for(int j=0;j<4;j++) {
				number[j] = random.nextInt(1000) + 1;
			}
			String str= "(" + i + ")" + " " + number[0];
			for(int k=0;k<sumOfSign;k++) {
				str=str + " " + signSet[random.nextInt(4)] + " " + number[k+1];
				if(k==sumOfSign-1) {
					str=str+" ";
				}
			}
			if((str.contains("-") || str.contains("+")) &&(str.contains("*") || str.contains("/"))) {
				if(flag==1) {
					int t=str.indexOf("-");
					if(t==-1) {
						t=str.indexOf("+");
					}
					int right=str.indexOf(" ", t+2);
					int left=str.indexOf(" ",t-5);
					if(str.charAt(left)==' ' && !Character.isDigit(str.charAt(left+1)) ) {
						left=left+1;
					}
					StringBuffer sb = new StringBuffer(str);
					sb.insert(right, " )");
					sb.insert(left+1, "( ");
					str=sb.toString();
				}
			}
			LinkedList<String> list=new LinkedList<>();
			String[] arr = str.split(" ");
	        for(int q=1;q<arr.length;q++) {
	        	if(q==1) {
	        		list.add(arr[q]);
	        		continue;
	        	}
	        	list.add(arr[q]);
	        }
			transferToPostfix(list);
			if(answer=="wrong") {
				i=i-1;
				continue;
			}
			sum[i]=answer;
			list.clear();
			arr=null;
			str=str.replace('*', '×');
			str=str.replace('/', '÷');
			out(str);
		}
		out("\r\n");
		BufferedReader br = in();

		for (int i = 0; i < n; i++) {
			String str = br.readLine() + "=" + " " + sum[i];
			out(str);
			if (i == n) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm");// 设置日期格式
				out("211406285 林志松" + " " + df.format(new Date()));
			}
		} // 这个for循环是用来确立要写入到out.txt中的str的值，也就是要出的题目的答案
	}
	//输入方法
	private static BufferedReader in() throws FileNotFoundException {
		File file = new File("out.txt");
		InputStreamReader in = new InputStreamReader(new FileInputStream(file));// 建立一个输入流的对象
		BufferedReader br = new BufferedReader(in);// 建立一个缓冲对象，把文件内容转换成计算机能识别的对象
		return br;
	}
	//输出方法
	private static void out(String str) throws IOException {
		File file = new File("out.txt");// 定义一个文件对象并令其指向一个文件位置
		file.createNewFile();// 在指定位置创建一个文件
		BufferedWriter br = new BufferedWriter(new FileWriter(file, true));// 建立一个缓冲对象，把文件内容转换成计算机能识别的对象
		br.write(str + "\r\n");
		br.flush();
		br.close();
	}
	//除错处理
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
