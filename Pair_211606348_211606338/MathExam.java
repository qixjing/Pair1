package com.it666.course1;

import java.util.Arrays;

public class MathExam {
	public static void main(String[] args) {
		//生成2个字符串用于拼接题目和答案
		StringBuffer strbuf = new StringBuffer();
		StringBuffer strbuf1 = new StringBuffer();
		//random用于随机生成题目中的操作数
		int random = 0;
		//将四种运算符放入数组
		String[] str = { "+", "-", "X", "÷" };
		//将随机生成的2-4个运算符存入ostr数组
		String[] ostr = new String[4];
		
		for(int i=0;i<10;i++) {
			//随机生成的2-4个运算符
			int operator = (int)(Math.random()*3)+2;
			int k = 0;
			for (int j = 0; j < operator; j++) {
				k = (int)(Math.random()*4);
				ostr[j]=str[k];
			}
			for (int j = 0; j < operator+1; j++) {
				random = (int)(Math.random()*1001);
				strbuf.append(random+" ");
				if(j<operator)
				strbuf.append(ostr[j]+" ");
			}strbuf.append("\n");
			//重新初始化数组
			ostr = new String[4];
			
		}System.out.println(strbuf.toString());
		
	}
}
