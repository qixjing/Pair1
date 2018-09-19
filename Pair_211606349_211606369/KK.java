package com.java6369.lesson02;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class KK{
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
		int re1 = 0, left=0,right=0;
		int re2 = 0;
		int re3 = 0;
		String c[] ={"+","-","×","÷"};
		byte contentInBytes[];
		
		Random rand=new Random();
		for (int i=0;i<=n;i++) {
			int a=rand.nextInt(c.length);
			String resultChar=c[a];//运算符号存入resultChar
			
			if (grade==1) {
				for (int i1=0;i1<=n;i1++) {
					left=rand.nextInt(100);
					right=rand.nextInt(100);//左右百内随机数
					String b= left+resultChar+right+"=";//题目
					//又一套，存题目
					
					if(resultChar=="+"){ 
						re3=left+right;
			            String d=left+resultChar+right+"="+re3;
			          
			        }else if(resultChar=="-"){
			        	re3=left-right;
			            String e=left+resultChar+right+"="+re3;
			           
			        }
				}
			}
			if (grade==2) {
				for (int i2=0;i2<=n;i2++) {
					if(resultChar=="+"){
						left=rand.nextInt(10000);
						right=rand.nextInt(10000);
						String b= left+resultChar+right+"=";
						
						if((left%100==0 || left%1000==0 || left<=100)&&(right%100==0 || right%1000==0 || right<=100)) {
							re3=left+right;
							String e=left+resultChar+right+"="+re3;
					       
						}
						if(left%100==0 && left>=100 && (right%100==0 || right%10==0)) {
							re3=left+right;
							String f=left+resultChar+right+"="+re3;
							
						}
			        }else if(resultChar=="-"){
			        	left=rand.nextInt(10000);
						right=rand.nextInt(10000);
						String b= left+resultChar+right+"=";
						
						if((left%100==0 || left%1000==0 || left<=100)&&(right%100==0 || right%1000==0 || right<=100)) {
							re3=left-right;
							String g=left+resultChar+right+"="+re3;
							
						}
						if(left%100==0 && left>=100 && (right%100==0 || right%10==0)) {
							re3=left-right;
							String h=left+resultChar+right+"="+re3;
							
						}
			        }else if(resultChar=="×"){
			        	left=rand.nextInt(10)+1;
						right=rand.nextInt(10)+1;
						String b= left+resultChar+right+"=";
						re3=left-right;
						String j=left+resultChar+right+"="+re3;
						
			        }else if(resultChar=="÷"){
			        	left=rand.nextInt(10)+1;
						right=rand.nextInt(10)+1;
						String b= left+resultChar+right+"=";
						
			            re2=left/right;
			            re1=left%right;
			            String k=left+resultChar+right+"=";
						
			            if(re1==0) { 
			            	re3=left/right;
			            	String l=left+resultChar+right+"="+re3;
			            }
			            else {
			            	String m=left+resultChar+right+"="+(re1+"..."+re2);
			            	
			            }   
			        }
				}
			}
			if (grade==3) {
				for (int i3=0;i3<=n;i3++) {
					if(resultChar=="+"){
						left=rand.nextInt(10000);
						right=rand.nextInt(10000);
						re3=left+right;
						String o= left+resultChar+right+"="+re3;
						
					}
					if(resultChar=="-") {
						left=rand.nextInt(10000);
						right=rand.nextInt(10000);
						re3=left-right;
						String o= left+resultChar+right+"="+re3;
						
					}
					if(resultChar=="×") {
						left=rand.nextInt(1000)+100;
						right=rand.nextInt(100)+10;
						re3=left*right;
						String o= left+resultChar+right+"="+re3;
						
					}
					if(resultChar=="÷") {
						left=rand.nextInt(1000)+100;
						right=rand.nextInt(100)+10;
						re3=left/right;
						String o= left+resultChar+right+"="+re3;
						
						re2=left/right;
				        re1=left%right;
						if(re1==0) { 
			            	String p=left+resultChar+right+"="+re3;
			            }
			            else {
			            	String p=left+resultChar+right+"="+(re1+"..."+re2);
			            	
			            }	
					}
				}
			}
		// 记录题目与答案
			Topic.append("("+ i +") "+ left + " " + resultChar + " " + right + "\r\n");
			if (resultChar.equals(" / ")) {
				Answer.append("(" + i + ") " + left +  " " + resultChar + " " + right + " = " + re3
						+ (re1 != 0 ? ("..." + re1) : "") + "\r\n");
			} else {
				Answer.append("(" + i + ") " + left +  " " + resultChar + " "  + right + " = " + re3 + "\r\n");
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

