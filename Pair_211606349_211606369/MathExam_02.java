package com.java6369.lesson02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MathExam_02{
	private static final String[] fuhao = {"+", "-"," * ", " / "};//可以使用的四则符号
	static StringBuffer Topic = new StringBuffer(); //在循环中进行字符串拼接，拼接题目和答案
	static StringBuffer Answer= new StringBuffer();
	private static int i;

public static void main(String[] args) throws IOException {//抛出异常
	int n = Integer.parseInt(args[0]);  //从命令行工具输入参数
	int grade = Integer.parseInt(args[1]);//定义输入的题目数量
	if (args.length == 0) {
		//传入两个参数，第一个是题数，第二个是年级，传入的参数不能是零
        throw new IllegalArgumentException("不能输入零个题数和年级");
    }
	if(args.length == 1) {
		throw new IllegalArgumentException("必须输入2个参数");
	}
    String str = args[0];//从命令输入的第1个参数
    if(str.length()>=3) {
    	throw new IllegalArgumentException("参数只能输入2个");
    }
    for(i=0;(str.charAt(i) < '0' || str.charAt(i++) > '9') && i < str.length();i++) {
    	//返回位置为i的字符，0~9表示数字，遍历
    	throw new IllegalArgumentException("不要输入非数字");
    }
    n = Integer.parseInt(args[0]);//把函数转化为int型进行数值计算
    Exam369(n,grade);
    writeTo();
    System.out.println("题目答案已生成请查看out.txt");//传入out.txt
}
    private static void Exam369(int n,int grade) {
        int result = 0; 
        int num1,num2,sub;
        int yushu=0;
		for (int i = 1; i <= n ; i++) {
			 num1 = (int) (Math.random() * 101);
			 num2 = (int) (Math.random() * 101);
			 sub = (int) (Math.random() *2);
			String symbol = fuhao[sub];
			switch (symbol) {
			case "+":
				result = num1 + num2;
				break;
			case "-":
				if (num1 <num2) {
					int m = num1;
					num1 = m;
					m = num2;
				}
				result =num1 - num2;
				break;
			default:
				System.out.println("错误");
			}
			int Choose = (grade == 1) ? 
			((int) (Math.random()*10))%2:((int) (Math.random() * 10)) % 4;
			String symbol2 = fuhao[Choose];
			
			switch (symbol2) {
			case " + ":
				result = num1 + num2;
				break;
			case " - ":
				result = num1 - num2;
				break;
			case " * ":
				result= num1 * num2;
				break;
			case " / ":
				while(num2 ==0) {
					num2 = (int) (Math.random() * 101);
				}
				result= num1 / num2;
				yushu = num1 % num2;
				break;
			default:
				break;
			}
			// 记录题目与答案
			Topic.append("("+ i +") "+ num1 + " " + symbol + " " + num2 + "\r\n");
			if (symbol.equals(" / ")) {
				Answer.append("(" + i + ") " + num1 +  " " + symbol + " " + num2 + " = " + result
						+ (yushu != 0 ? ("..." + yushu) : "") + "\r\n");
			} else {
				Answer.append("(" + i + ") " + num1 +  " " + symbol + " "  + num2 + " = " + result + "\r\n");
			}
			
		}
    }
    public static void writeTo() throws IOException {//抛出异常
    	  File file= new File("out.txt");
		if (!file.exists()) {
			//判断是否存在此路径
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()) {
				parent.mkdirs();
			}
			file.createNewFile();
		}
		OutputStream out = new FileOutputStream(file);
		byte[] question=Topic.toString().getBytes();
		byte[] answer=Answer.toString().getBytes();
		out.write(question);
		out.write(System.lineSeparator().getBytes());
		out.write(answer);
		out.close();
    }
}
