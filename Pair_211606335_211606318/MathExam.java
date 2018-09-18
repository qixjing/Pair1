
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.io.OutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.CORBA.PUBLIC_MEMBER;


public class MathExam {
	/*
 * 	在原有的V2.0.0版本上加入三年级四则混合运算题
 *     1.实现了三年级题目和答案添加序列号
 *     ps：还未解决除数为0和差值为负数的bug，
 * 		coding ： GBK
 * 		MathExam_V2.0.7
 */	
	int firstNumber, secondNumber;		
	int symbol;	//运算符号判断
	static int grade;
	static int count;		
	int result;		
	String operator_Add;
	
	//调度场和逆波兰函数定义
	Stack<String> operators = new Stack<String>();	//存储操作符
	Stack<String>  operand= new Stack<String>();	//存储操作数
	private static StringBuilder postfixExpression=new StringBuilder(); //展示表达式
	Stack<Integer> postfixNumber = new Stack<Integer>();	//计算后缀表达式时存储数字
	
	String[] str_ArithmeticProblem = new String[10000];	//存放算术题
	String[] str_MathAnswer = new String[10000];	//存放题目和标准答案
	
	public MathExam(String[] args){
		inPut(args[0], args[1],args[2],args[3]);
		mathProblem(count);
		outPut();
		
	}

	

	private void inPut(String str0,String str1, String str2, String str3) {
		// TODO Auto-generated method stub
		boolean flag1 = true;		//判断题目年级参数
		boolean flag2 = true;
		
		Scanner in = new Scanner(System.in);
		String regex1 = "0*[1-9]{1}\\d{0,3}";		//正则表达式判断输入参数为非零正整数
		String regex2 = "0*[1-3]{1}{0}";
		Pattern pattern1 = Pattern.compile(regex1);		//定义两组对正则表达式的编译对象
		Pattern pattern2 = Pattern.compile(regex2);
		Matcher matcher1,matcher2;		//定义两组判断参数与正则表达式的操作引擎
		
		while (true) {		
			try {
				if(str0.equals("-n") && str2.equals("-grade")){		//首先匹配输入的参数类型，args[0] 和 args[2] 的内容
					matcher1 = pattern1.matcher(str1);		
					matcher2 = pattern2.matcher(str3);
					flag1 = matcher1.matches();
					flag2 = matcher2.matches();
					if(!flag1 || !flag2){
						throw new NumberFormatException();
					} else {
						count = Integer.valueOf(str1);
						grade = Integer.valueOf(str3);
					}
				}else if(str0.equals("-grade") && str2.equals("-n")){		//regex1和3的表达式都匹配成功
					matcher1 = pattern1.matcher(str3);
					matcher2 = pattern2.matcher(str1);
					flag1 = matcher1.matches();		//题数匹配规范性
					flag2 = matcher2.matches();		//年级匹配规范性
					if(!flag1 || !flag2){		//两个参数不符合正则表达式规范就进入异常
						throw new NumberFormatException();		
					} else {	
						count = Integer.valueOf(str3);
						grade = Integer.valueOf(str1);
					}
				} else {
					System.out.println("输入参数类型有误，即将退出程序");
					System.exit(0);
				}				
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("输入参数不符合规范，即将退出程序");
				System.exit(0);
			}			
			in.close();
			break;
		}
	}
	
	private void outPut() {
		// TODO Auto-generated method stub
		File file = new File("out.txt");
		
		if(!file.exists()){	//判断文件是否存在
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("文件创建时出现错误!");
			}
		}
		
		try {
			String str = "\r\n";
			byte[] bytes = str.getBytes();		//string类型转换为能被机器识别的二进制码
			
			FileOutputStream fos = new FileOutputStream(file);	//文件写入流
			for (int i = 0; i < count; i++) {
				byte[] b_ArithmeticProblem = str_ArithmeticProblem[i].getBytes();
				fos.write(b_ArithmeticProblem);
				fos.write(bytes);
			}
			fos.write(bytes);
			fos.flush();	//刷新内存
			for (int i = 0; i < count; i++) {
				byte[] b_MathAnswer = str_MathAnswer[i].getBytes();
				fos.write(b_MathAnswer);
				fos.write(bytes);
			}
			fos.flush();
			fos.close();	//关闭文件输出流
		
			System.out.print("-------  本次共生成" + count + "道小学"+ grade + "年级算数题，请打开out.txt文件查看详情    -------"); 	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("未找到指定文件!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("文件写入有误!");
		} 	
	}
	
	//判断是否操作符
    private static boolean isOperator(String operator){
        if (operator.equals("+")||operator.equals("-")||operator.equals("×")||operator.equals("÷")
                ||operator.equals("(")||operator.equals(")")) {
            return true;
        }
        return false;
    }
	
	//计算操作符的优先级
    private static int priority(String s){
        if (s.equals("+") || s.equals("-")) {
        	return 1;
        } else if (s.equals("×") || s.equals("÷")) {
        	return 2;
        } else if(s.equals("(") || s.equals(")")){
        	return 3;
        } else{
        	return 0;
        }
    }
    
    //返回一个结果
    private static int Calculation(int n,int m,String operator){
    	int result = -4567;
    	if (operator.equals("+")) {
    		result = n + m;
    	} else if (operator.equals("-")) {
    		result = n - m;
    	} else if (operator.equals("×")) {
    		result = n * m;
    	} else if (operator.equals("÷")) {
    		result = n / m;
    	}
    	return result;
    }


	//调度场算法——[中缀表达式转后缀表达式]
	private void toPostfixExpression(String str_mix){
		int len = str_mix.length();
		char c,nextChar;
		String sc;
		for (int i = 0 ; i <= len-1 ; i++) {
			c = str_mix.charAt(i); 
			sc = String.valueOf(c);
			if(isOperator(sc))	//判断是否是操作符
			{
				if(operators.isEmpty()){	//判断为空栈，入栈
					operators.push(sc);
				} else {
					if(priority(operators.peek()) < priority(sc) && !sc.equals(")")){	
						//栈顶操作符优先级小于当前操作符优先级且操作符不为右括号，入栈
						operators.push(sc);
					} else if(priority(operators.peek()) >= priority(sc) && !sc.equals(")")){
						while(!operators.empty() && !operators.peek().equals("(")	//栈不为空，当前栈顶操作符不为左括号
								&& priority(operators.peek()) >= priority(sc)){		//操作符优先级小于等于当前栈顶操作符优先级
							do {
								operator_Add = operators.pop();
								postfixExpression.append(operator_Add);
								operand.push(operator_Add);
							} while (false);	}	// 栈顶操作符是左括号时停止压栈
						operators.push(sc);		//否则直接入栈
					} else if(sc.equals(")")){	//当前扫描到的操作符为右括号(不做入栈操作)，依次压栈相匹配的左括号内容
						do {
							operator_Add = operators.pop();
							postfixExpression.append(operator_Add);
							operand.push(operator_Add);
						} while (!operators.peek().equals("("));
						operators.pop();	//弹出栈顶无用操作符左括号
					}
				}
			}else {	//非操作符
				if(!sc.equals(" ")){
					postfixExpression.append(sc);
					operand.push(sc);					
				}
			}
		}
		while(!operators.empty()){	//结束字符串扫描后操作符的栈不为空则则压栈
			operator_Add = operators.pop();
			postfixExpression.append(operator_Add);
			operand.push(operator_Add);
		}
	}
	
	
	//逆波兰函数
	private int reversePolish() {
		// TODO Auto-generated method stub
		char c;
		int len = postfixExpression.toString().length();
		for (int i = 0; i < len; i++) {
			c = postfixExpression.charAt(i);
			if(!isOperator(String.valueOf(c))){	//判断非操作符，入栈
				postfixNumber.push(Integer.parseInt(String.valueOf(c)));
			} else{
				int m = postfixNumber.pop();
				int n = postfixNumber.pop();
				String operator = String.valueOf(c);
				postfixNumber.push(Calculation(n, m, operator));	
			}
		}
		return postfixNumber.pop();
	}
	
	//生成算术题
	private void mathProblem(int count) {	
		Random rnd = new Random();
		
		for (int i = 0; i < count; i++) {
			symbol = rnd.nextInt(2);
			firstNumber = (int)(Math.random()*20+1);
			secondNumber = (int)(Math.random()*20+1);
			
			if(grade == 1){
				switch (symbol) {
				case 0:
					add(firstNumber,secondNumber,i);
					break;
					
				case 1:
					sub(firstNumber,secondNumber,i);
					break;
					
				default:
					break;
				}
			} else if(grade == 2){
				switch (symbol) {
				case 0:
					mul(firstNumber,secondNumber,i);
					break;
					
				case 1:
					div(i);
					break;
					
				default:
					break;
				}
			}else{
				fourMixed();
			}	
		}
	}
	
	//四则运算：括号算操作符，总共2-4个操作符
	private void fourMixed() {
		// TODO Auto-generated method stub
		int whereBrackets = (int)(Math.random());	//控制左右括号为位置
		int howManyNum = (int)(Math.random()*3+3);
		StringBuilder bf_sequence;
		for (int j = 0; j < count; j++) {
			int n1 = (int)(Math.random()*10);
			int n2 = (int)(Math.random()*10);
			int n3 = (int)(Math.random()*10);
			int n4 = (int)(Math.random()*10);
			int n5 = (int)(Math.random()*10);
			int c1 = (int)(Math.random()*4);
			int c2 = (int)(Math.random()*4);
			int c3 = (int)(Math.random()*4);
			int c4 = (int)(Math.random()*4);
			String[] cs = {"+","-","×","÷"};
			if(howManyNum==3) {
			str_ArithmeticProblem[j] = n1 + " " + cs[c1] + " " + n2 + " " + cs[c2] + " " + n3;
			toPostfixExpression(str_ArithmeticProblem[j]);
			str_MathAnswer[j] ="( " + (j+1) + " ) " + n1 + " " + cs[c1] + " " + n2 + " " + cs[c2] + " " + n3 + " = " + reversePolish();
			} else if(howManyNum==4) {
				str_ArithmeticProblem[j] = n1 + " " + cs[c1] + " " + n2 + " " + cs[c2] + " " + n3 + " " + cs[c3] + " " + n4;
				toPostfixExpression(str_ArithmeticProblem[j]);
				str_MathAnswer[j] ="( " + (j+1) + " ) " + n1 + " " + cs[c1] + " " + n2 + " " + cs[c2] + " " + n3 + " " + cs[c3] + " " + n4 + " = " + reversePolish();
			}else if(howManyNum==5) {
				str_ArithmeticProblem[j] = n1 + " " + cs[c1] + " " + n2 + " " + cs[c2] + " " + n3 + " " + cs[c3] + " " + n4 + " " + cs[c4] + " " + n5;
				toPostfixExpression(str_ArithmeticProblem[j]);
				str_MathAnswer[j] ="( " + (j+1) + " ) " + n1 + " " + cs[c1] + " " + n2 + " " + cs[c2] + " " + n3 + " " + cs[c3] + " " + n4 + " " + cs[c4] + " " + n5 + " = " + reversePolish();
			}
			bf_sequence = new StringBuilder("( " + (j+1) + " ) ") ;	//给题目添加序列号
			str_ArithmeticProblem[j] = bf_sequence.append(str_ArithmeticProblem[j]).toString();
		}
	}
	
/*
	 * 加法：
	 *  1.一二年级的加法的两个加数在20以内。
	 * 
*/
	private void add(int n1, int n2,int i) {
		// TODO Auto-generated method stub
		result = n1 + n2;
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " + " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " + " + n2 + " = " + result;
	}
	
	/*
	 * 减法：
	 * 
	 * 1.一二年级两数之差应该在大于0；
	 * 2.被减数和减数在20以内。
*/
	private void sub(int n1, int n2,int i) {
		// TODO Auto-generated method stub
		if (n1 < n2) {		//差为负数，交换数值
			int num;
			num = n1;
			n1 = n2;
			n2 = num;
		}
		result = n1 - n2;
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " - " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " - " + n2 + " = " + result;
	}
	
	/*
	 * 乘法：
	 * 
	 * 1.一二年级的乘法运算应该在0-9以内【九九乘法表】；
	 * 
*/
	private void mul(int n1, int n2,int i) {
		// TODO Auto-generated method stub
		if (n1 > 9) {
			n1 = (int)(Math.random()*10);
		} 
		if (n2 > 9) {
			n2 = (int)(Math.random()*10);
		}
		result = n1 * n2;
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " × " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " × " + n2 + " = " + result;
	}
	
	/*
	 * 除法：
	 * 
	 * 1.一二年级的除法运算应该在”九九乘法表“范围以内；
	 * 2.分母不能为”0“。
	 * 
*/
	private void div(int i) {
		// TODO Auto-generated method stub
		while(true){
			int n1 = (int)(Math.random()*82);
			int n2 = (int)(Math.random()*81+1);
			if(n1 % n2 == 0){
				result = n1 / n2;
				str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " ÷ " + n2;
				str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " ÷ " + n2 + " = " + result;
			}else if(n1 % n2 != 0){
				result = n1 / n2;
				str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " ÷ " + n2;
				str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " ÷ " + n2 + " = " + result + "..." + (n1 % n2);
			}
			break;
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MathExam(args);	
	}

}
