

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathExam6360 {
	
	String QT[] = new String[1000];				//定义全局变量
	String AS[] = new String[1000];				//定义全局变量
	String SymB[] = {"+","-","×","÷"};
	String NL ="\r\n";							//定义全局变量
	byte[] question;							//定义全局变量
	byte[] newline = NL.getBytes();				//定义全局变量
	byte[] answer;								//定义全局变量
	
	
	
	 private static int ADDITION=1;
	 private static int SUBTRACTION=1;
	 private static int MULTIPLICATION=2;
	 private static int DIVISION=2;

	 public static int Level(String operation){
	     int result;
	     switch (operation){
	         case "+":
	             result=ADDITION;
	             break;
	         case "-":
	             result=SUBTRACTION;
	             break;
	         case "×":
	             result=MULTIPLICATION;
	             break;
	         case "÷":
	             result=DIVISION;
	             break;
	         default:
//	             System.out.println("不存在该运算符");
	             result=0;
	     }
	     return result;
	 }
	 
	 
	public MathExam6360(String args[]) {		//构造函数
		// TODO Auto-generated constructor stub
		int num[] = new int[5];					//定义数组num
		num [1] = Integer.parseInt(args[1]);	//将参数转化为整型
		num [0] = Integer.parseInt(args[0]);
		boolean B =judge(num[0],num[1]);		//判断参数的格式以及大小是否符合
		if(B==true) {							
			calculate(num[0], num[1]);			//根据年级随机生成式子
			TxT(num[0]);						//创建文本，并将式子写入文本当中		
		}
		else {
			System.out.println("输入错误。没机会输入了。滚！");
		}
	}
	

	private  void TxT(int count) {
		File file = new File("out6343.txt");  	//创建文本
		if(!file.exists())						//判断TXT是否存在
		{
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("out6343.txt创建完成");
		}
		try {
			FileOutputStream fos = new FileOutputStream(file);
			for(int i=0;i<count;i++) {
				question = QT[i].getBytes();
				fos.write(question);
				fos.write(newline);
				
			}
			
			
			
			for(int i=0;i<count;i++){
				answer = AS[i].getBytes();
				fos.write(newline);
				fos.write(answer);
				
			}
			
			fos.flush();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private  boolean judge(int count ,int grade) {
		String Regex="[1-9]{1}[0-9]{0,2}";   //正则表达式，将输入的参数限定在正整数范围内，同时给予最大极限。
		String Regex2="[1-3]{1}{0}";         //正则表达式，将输入的参数限定在正整数范围内，同时给予最大极限。
		
		
		Pattern p =Pattern.compile(Regex);
		Pattern p2 =Pattern.compile(Regex2);
		
		
		String str = String.valueOf(count);   //强制将int类型转换为string类型
		String str2 = String.valueOf(grade);
		
		
		Matcher M=p.matcher(str);             //进行判断
		Matcher M2=p2.matcher(str2);			
		
		
		return M.matches() && M2.matches();   //返回boolean类型的值
		
	}
	
	private  void calculate(int count,int grade){
		
		if(grade == 1){ 										
			calculate_1(count);
		}
		
		
		if(grade == 2) {
			calculate_2(count);
		}
		
		if(grade == 3) {
		
			calculate_3(count);
		
		}
	}
	
	private void  calculate_1(int count) {					//一年级计算
		for(int i=1;i<=count;) {
			int a=(int)(Math.random()*10+1);
			int b=(int)(Math.random()*10+1);
			int symbol=(int)(Math.random()*2);
		
			if(symbol==0) {
				QT[i-1]="("+ i +") " + a + " + " + b;
				AS[i-1]="("+ i +") " + a + " + " + b +" = " +(a+b) ;
			}
			else if(symbol==1) {
				if(a<b)
					{continue;}
				QT[i-1]="("+ i +") " + a + " - " + b;	
				AS[i-1]="("+ i +") " + a + " - " + b +" = " +(a-b) ;
			}
			i++;
			}
	}
	
	private void  calculate_2(int count) {					//二年级计算
		for(int i=1;i<=count;) {
			
			int a=(int)(Math.random()*100);
			int b=(int)(Math.random()*100);
			int symbol=(int)(Math.random()*4);
		
			if(symbol==0) {
				QT[i-1]="("+ i +") " + a + " + " + b;
				AS[i-1]="("+ i +") " + a + " + " + b +" = " + (a+b) ;
			}
			else if(symbol==1) {
				if(a<b)
					{continue;}
				QT[i-1]="("+ i +") " + a + " - " + b;	
				AS[i-1]="("+ i +") " + a + " - " + b +" = " +(a-b) ;
			}
			else if(symbol==2) {
				QT[i-1]="("+ i +") " + a%10 + " × " + b%10;
				AS[i-1]="("+ i +") " + a%10 + " × " + b%10 +" = " +((a%10)*(b%10)) ;
			}
			else if(symbol==3) {
				if(b%10==0) 
					{continue;}
				
				QT[i-1]="("+ i +") " + a%10 + " ÷ " + b%10;
				
				if(((a%10)%(b%10))==0) {
					
					AS[i-1]="("+ i +") " + a%10 + " ÷ " + b%10 +" = " +((a%10)/(b%10)) ;
				}
			
				else if(((a%10)%(b%10))!=0) {
					AS[i-1]="("+ i +") " + a%10 + " ÷ " + b%10 +" = " +((a%10)/(b%10)) +" ··· " + ((a%10)%(b%10));
				}
			
			}
		i++;
		}	
	}
	
	private void  calculate_3(int count) {
	     int i=0;
	    while(i<count){
	    	  	
	    	  	int symbol_number=(int)(Math.random()*5+2);
	    	  	int a=(int)(Math.random()*1000);
	  			int b=(int)(Math.random()*1000);
	  			int c=(int)(Math.random()*1000);
	  			int symbol_1=(int)(Math.random()*4);
	  			int symbol_2=(int)(Math.random()*4);
	  			
	  			
	  			if(symbol_number == 2) {
	
		  			QT[i]=a+SymB[symbol_1]+b+SymB[symbol_2]+c;
		  			
		  			}
	  			
	  			else if(symbol_number == 3) {
	  				int d=(int)(Math.random()*1000);
	  				int symbol_3=(int)(Math.random()*4);
		  			QT[i]=a+SymB[symbol_1]+b+SymB[symbol_2]+c+SymB[symbol_3]+d;
		  			}
	  			
	  			
	  			else if(symbol_number == 4) {
	  				int d=(int)(Math.random()*1000);
	  				int e=(int)(Math.random()*1000);
	  				int symbol_3=(int)(Math.random()*4);
	  				int symbol_4=(int)(Math.random()*4);
		  			QT[i]=a+SymB[symbol_1]+ b+SymB[symbol_2]+c+SymB[symbol_3]+d+SymB[symbol_4]+e;
		  			}
	  			else {continue;}
	  			 List<String> zx= toInfixExpression(QT[i]);
	  			 List<String> rpn=parseSuffixExpression(zx);
	  			 AS[i]=QT[i]+"="+reckon(rpn);
	  			 i++;

	      	}
	     
	    }
	
	
	  public  List<String> parseSuffixExpression(List<String> ls) {
	        Stack<String> s1=new Stack<String>();
	        List<String> LS = new ArrayList<String>();
	        for (String str : ls) {
	            if (str.matches("\\d+")) {
	                LS.add(str);
	            }
	            else {
	                while (s1.size() != 0 && Level(s1.peek()) >= Level(str)) {
	                    LS.add(s1.pop());
	                }
	                s1.push(str);
	            }
	        }
	        while (s1.size() != 0) {
	            LS.add(s1.pop());
	        }
	        return LS;
	    }
	  
	
	 public List<String> toInfixExpression(String s) {
	        List<String> ls = new ArrayList<String>();//存储中序表达式
	        int w = 0;
	        String str;
	        char c;
	        int sum=s.length();
	        do {
	            if ((c = s.charAt(w)) < 48 || (c = s.charAt(w)) > 57) {
	                ls.add("" + c);
	                if(w<sum) {w++;}
	                
	            } else {
	                str = "";
	                while (w < s.length() && (c = s.charAt(w)) >= 48
	                        && (c = s.charAt(w)) <= 57) {
	                    str += c;
	                    w++;
	                }
	                ls.add(str);
	            }

	        } while (w < s.length());
	        return ls;
	    }
	
	 public  int reckon(List<String> ls) {
	        Stack<String> s=new Stack<String>();
	        for (String str : ls) {
	            if (str.matches("\\d+")) {
	                s.push(str);
	            } else {
	                int b = Integer.parseInt(s.pop());
	                int a = Integer.parseInt(s.pop());
	                int result=0;
	                if (str.equals("+")) {
	                    result = a + b;
	                } else if (str.equals("-")) {
	                    result = a - b;
	                } else if (str.equals("×")) {
	                    result = a * b;
	                } else if (str.equals("÷")) {
	                    result = a / b;
	                }
	                s.push("" + result);
	            }
	        }
	        return Integer.parseInt(s.pop());
	    }
	
	public static void main(String args[]) {
		new	MathExam6360(args);
	}

}
