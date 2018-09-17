
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MathExam {
	/*
 * 	在原有的V2.0.0版本上加入三年级四则混合运算题
 *     1.删除原有的构造函数以及构造函数的重载；
 *     2.input方法增加了对新传入的"-n"和"-grade"参数的匹配判断； 
 *     3.修改二年级题目生成，只针对乘除做训练，不包含加减运算。
 * 		coding ： GBK
 * 		MathExam_V2.0.1
 */	
	int firstNumber, secondNumber;		
	int symbol;	//运算符号判断
	static int grade;
	static int count;		
	int result;		
	
	String[] str_ArithmeticProblem = new String[10000];	//存放算术题
	String[] str_MathAnswer = new String[10000];	//存放题目和标准答案
	
	public MathExam(String args0,String args1, String args2, String args3){
		inPut(args0, args1,args2,args3);
		mathProblem(count);
		outPut();
	}

	private void inPut(String str0,String str1, String str2, String str3) {
		// TODO Auto-generated method stub
		boolean flag1 = true;		//判断题目年级参数
		boolean flag2 = true;
		
		Scanner in = new Scanner(System.in);
		String regex1 = "0*[1-9]{1}\\d{0,3}";		//正则表达式判断输入参数为非零正整数
		String regex2 = "0*[1-2]{1}{0}";
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
			}
			
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
		str_ArithmeticProblem[i] = "( " + (i+1) +" ) " + n1 + " x " + n2;
		str_MathAnswer[i] = "( " + (i+1) +" ) " + n1 + " x " + n2 + " = " + result;
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
		new MathExam(args[0], args[1],args[2],args[3]);	
	}

}
