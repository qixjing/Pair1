
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathExam6360 {
	
	String Date[] = new String[100];
	String QT[] = new String[1000];				//定义全局变量，存放问题
	String QT_1[] = new String[1000];
	String ORDER[] = new String[1000];			//定义存放序号的数组。
	String AS[] = new String[1000];				//定义全局变量，存放问题和答案
	String SymB[] = {"+","-","×","÷"};
	String NL ="\r\n";							//定义全局变量
	byte[] question;							//定义全局变量
	byte[] newline = NL.getBytes();				//定义全局变量
	byte[] answer;								//定义全局变量
	byte[] order;
	byte[] date;

	
	
	 public static int Level(String operation){  						//优先级判断。
	     int result;
	     switch (operation){
	         case "+":
	             result=1;
	             break;
	         case "-":
	             result=1;
	             break;
	         case "×":
	             result=2;
	             break;
	         case "÷":
	             result=2;
	             break;
	         default:
//	             System.out.println("不存在该运算符");
	             result=0;
	     }
	     return result;
	 }
	 
	 
	public MathExam6360(String args[]) {											//构造函数
		// TODO Auto-generated constructor stub
		boolean B;
		if(args[0].equals("-n") && args[2].equals("-grade"))  						//判断参数数量和年级的顺序。
		{
			B =judge(args[1],args[3]);												//判断参数的格式以及大小是否符合
			if(B==true) {	
				
				calculate(Integer.parseInt(args[1]), Integer.parseInt(args[3]));	//根据年级随机生成式子
				TxT(Integer.parseInt(args[1]));										//创建文本，并将式子写入文本当中		
			}else 
				{System.out.println("输入错误，没机会输入了。");}
			
		}
		else if(args[0].equals("-grade") && args[2].equals("-n")) {
			
			B =judge(args[3],args[1]);												//判断参数的格式以及大小是否符合
			if(B==true) {	
				
				calculate(Integer.parseInt(args[3]), Integer.parseInt(args[1]));	//根据年级随机生成式子
				TxT(Integer.parseInt(args[3]));										//创建文本，并将式子写入文本当中
						
			}else 
				{System.out.println("输入错误，没机会输入了。");}
			
		}
		else
			{System.out.println("输入错误，没机会输入了。");}
		
	}
	

	private  void TxT(int count) {
		
		DateFormat dt = DateFormat.getDateTimeInstance(); 							//获取当前时间
		Date[0] = dt.format(new Date());
		
		
		File file = new File("out6343.txt");  										//创建文本
		if(!file.exists())															//判断TXT是否存在
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
				ORDER[i]="("+(i+1)+") ";
				question = QT[i].getBytes();
				order = ORDER[i].getBytes();
				fos.write(order);
				fos.write(question);
				fos.write(newline);
				
			}
			
			
			for(int i=0;i<count;i++){
				ORDER[i]="("+(i+1)+") ";
				order = ORDER[i].getBytes();
				answer = AS[i].getBytes();
				fos.write(newline);
				fos.write(order);
				fos.write(answer);
				
			}
			date =Date[0].getBytes();
			fos.write(newline);
			fos.write(date);
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
	
	private  boolean judge(String count ,String grade) {    //判断参数格式是否正确的方法。
		String Regex="[1-9]{1}[0-9]{0,2}";   				//正则表达式，将输入的参数限定在正整数范围内，同时给予最大极限。
		String Regex2="[1-3]{1}{0}";        				//正则表达式，将输入的参数限定在正整数范围内，同时给予最大极限。
		
		
		Pattern p =Pattern.compile(Regex);
		Pattern p2 =Pattern.compile(Regex2);
		
		
		Matcher M=p.matcher(count);         				//进行判断
		Matcher M2=p2.matcher(grade);			
		
		
		return M.matches() && M2.matches();   				//返回boolean类型的值
		
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
			int a=(int)(Math.random()*10+1);				//随机第一个数字
			int b=(int)(Math.random()*10+1);				//随机第二个数字
			int symbol=(int)(Math.random()*2);				//随机符号。
		
			if(symbol==0) {
				QT[i-1]=a + " + " + b;
				AS[i-1]=a + " + " + b +" = " +(a+b) ;
			}
			else if(symbol==1) {
				if(a<b)
					{continue;}
				QT[i-1]=a + " - " + b;	
				AS[i-1]=a + " - " + b +" = " +(a-b) ;
			}
			i++;
			}
	}
	
	private void  calculate_2(int count) {					//二年级计算
		for(int i=1;i<=count;) {
			
			int a=(int)(Math.random()*100);					//随机第一个数字
			int b=(int)(Math.random()*100);					//随机第二个数字
			int symbol=(int)(Math.random()*4);				//随机符号。
		
			if(symbol==0) {
				QT[i-1]=a + " + " + b;
				AS[i-1]=a + " + " + b +" = " + (a+b) ;
			}
			else if(symbol==1) {
				if(a<b)
					{continue;}
				QT[i-1]=a + " - " + b;	
				AS[i-1]=a + " - " + b +" = " +(a-b) ;
			}
			else if(symbol==2) {
				QT[i-1]=a%10 + " × " + b%10;
				AS[i-1]=a%10 + " × " + b%10 +" = " +((a%10)*(b%10)) ;
			}
			else if(symbol==3) {
				if(b%10==0) 
					{continue;}
				
				QT[i-1]=a%10 + " ÷ " + b%10;
				
				if(((a%10)%(b%10))==0) {
					
					AS[i-1]=a%10 + " ÷ " + b%10 +" = " +((a%10)/(b%10)) ;
				}
			
				else if(((a%10)%(b%10))!=0) {
					AS[i-1]=a%10 + " ÷ " + b%10 +" = " +((a%10)/(b%10)) +" ··· " + ((a%10)%(b%10));
				}
			
			}
		i++;
		}	
	}
	
	private void  calculate_3(int count) {
	     int i=0;
	    while(i<count){
	    	  	
	    	  	int symbol_number=(int)(Math.random()*5+2);				   	//随机生成数字，用于判断符号个数
	    	  	int number_1=(int)(Math.random()*1000);		 				//随机生成第一个数字
	  			int number_2=(int)(Math.random()*1000+1);				 	//随机生成第二个数字
	  			int number_3=(int)(Math.random()*1000+1);					//随机生成第三个数字
	  			int symbol_1=(int)(Math.random()*4);						//随机生成第一个符号
	  			int symbol_2=(int)(Math.random()*4);						//随机生成第二个符号
	  			
	  			if(symbol_number == 2) {
	  				if(symbol_1==symbol_2)									//保证至少有两种运算符号。
	  					{continue;}
	  				if(Level(SymB[symbol_1])<Level(SymB[symbol_2])) {		//比较第一个符号和第二个符号的优先级，并添加括号。
			  			QT_1[i]="("+number_1+SymB[symbol_1]+number_2+")"+SymB[symbol_2]+number_3;
			  			QT[i]="( "+number_1+" "+SymB[symbol_1]+" "+ number_2 +" ) "+SymB[symbol_2] +" " +number_3;
	  				}
	  				else if(Level(SymB[symbol_1])>Level(SymB[symbol_2]))  {
			  			QT_1[i]=+number_1+SymB[symbol_1]+"(" + number_2 +SymB[symbol_2]+number_3+")";
			  			QT[i]=number_1+" "+SymB[symbol_1]+" ( "+ number_2 +" "+SymB[symbol_2]  +number_3+" ) ";
	  				}
	  				else {
	  					QT_1[i]=+number_1+SymB[symbol_1]+number_2+SymB[symbol_2]+number_3;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 +" "+SymB[symbol_2] +" "+number_3;
	  				}
		  		}
	  			//判断运算符为两个之后，开始判断符号优先级，并在式子里添加括号。
	  			
	  			
	  			else if(symbol_number == 3) {	
	  				if(symbol_1==symbol_2)		//保证式子内至少有两种运算符号
	  				{continue;}		
	  				int number_4=(int)(Math.random()*1000+1);
	  				int symbol_3=(int)(Math.random()*4);
	  				if(Level(SymB[symbol_1])<Level(SymB[symbol_2])) {
			  			QT_1[i]="("+number_1+SymB[symbol_1]+number_2+")"+SymB[symbol_2]+number_3+SymB[symbol_3]+number_4;
			  			QT[i]="( "+number_1+" "+SymB[symbol_1]+" "+ number_2 +" ) "+SymB[symbol_2] +" " +number_3+" "+SymB[symbol_3]+" "+number_4;
	  				}
	  				else if(Level(SymB[symbol_1])>Level(SymB[symbol_2]))  {
			  			QT_1[i]=+number_1+SymB[symbol_1]+"(" + number_2 +SymB[symbol_2]+number_3+")"+SymB[symbol_3]+number_4;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" ( "+ number_2 +" "+SymB[symbol_2]  +number_3+" ) "+SymB[symbol_3]+" "+number_4;
	  				}
	  				else if(Level(SymB[symbol_2])>Level(SymB[symbol_3]))  {
			  			QT_1[i]=+number_1+SymB[symbol_1]+ number_2 +SymB[symbol_2]+"("+number_3 +SymB[symbol_3]+number_4+")";
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 +" "+SymB[symbol_2] +" ( " +number_3+" "+SymB[symbol_3]+" "+number_4+" ) ";
	  				}
	  				else {
	  					QT_1[i]=+number_1+SymB[symbol_1]+number_2+SymB[symbol_2]+number_3+SymB[symbol_3]+number_4;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 +" "+SymB[symbol_2] +" "+number_3+" "+SymB[symbol_3]+" "+number_4;
	  				}
		  		}
	  		//判断运算符为三个之后，开始判断符号优先级，并在式子里添加括号。
	  			
	  			
	  			else if(symbol_number == 4) {
	  				if(symbol_1==symbol_2)								//保证式子至少有两种运算符号
	  				{continue;}
	  				int number_4=(int)(Math.random()*1000+1);
	  				int number_5=(int)(Math.random()*1000+1);
	  				int symbol_3=(int)(Math.random()*4);
	  				int symbol_4=(int)(Math.random()*4);
	  				
	  				if(Level(SymB[symbol_1])<Level(SymB[symbol_2])) {
			  			QT_1[i]="("+number_1+SymB[symbol_1]+number_2+")"+SymB[symbol_2]+number_3+SymB[symbol_3]+number_4+SymB[symbol_4]+number_5;
			  			QT[i]="( "+number_1+" "+SymB[symbol_1]+" "+ number_2 +" ) "+SymB[symbol_2] +" " +number_3+" "+SymB[symbol_3]+" "+number_4+" "+SymB[symbol_4]+" "+number_5;
	  				}
	  				
	  				
	  				else if(Level(SymB[symbol_1])>Level(SymB[symbol_2])) {
			  			QT_1[i]=number_1+SymB[symbol_1]+"("+number_2+SymB[symbol_2]+number_3+")"+SymB[symbol_3]+number_4+SymB[symbol_4]+number_5;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" ( "+ number_2 +" "+SymB[symbol_2]+" "+number_3+" ) "+SymB[symbol_3]+" "+number_4+" "+SymB[symbol_4]+" "+number_5;
	  				}
	  				
	  				
	  				else if(Level(SymB[symbol_2])>Level(SymB[symbol_3])) {
			  			QT_1[i]=number_1+SymB[symbol_1]+number_2+SymB[symbol_2]+"("+number_3+SymB[symbol_3]+number_4+")"+SymB[symbol_4]+number_5;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 + " "+SymB[symbol_2]+" ( "+number_3+" "+SymB[symbol_3]+" "+number_4+" ) "+" "+SymB[symbol_4]+" "+number_5;
	  				}
	  				
	  				
	  				else if(Level(SymB[symbol_3])>Level(SymB[symbol_4])) {
			  			QT_1[i]=number_1+SymB[symbol_1]+number_2+SymB[symbol_2]+number_3+SymB[symbol_3]+"("+number_4+SymB[symbol_4]+number_5+")";
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 + " "+SymB[symbol_2]+" "+number_3+" "+SymB[symbol_3]+" ( "+number_4+" "+SymB[symbol_4]+" "+number_5+" )";
	  				}
	  				
	  				
	  				else {
			  			QT_1[i]=number_1+SymB[symbol_1]+ number_2+SymB[symbol_2]+number_3+SymB[symbol_3]+number_4+SymB[symbol_4]+number_5;
			  			QT[i]=number_1+" "+SymB[symbol_1]+" "+ number_2 +" "+SymB[symbol_2] +" " +number_3+" "+SymB[symbol_3] +" "+number_4+" "+SymB[symbol_4]+" "+number_5;
	  				}
	  			}
	  		//判断运算符为四个之后，开始判断符号优先级，并在式子里添加括号。
	  			
	  			else {continue;}		//当symbol_number随机出不需要的数字时，结束本次循环，重新开始新的循环。主要防止出现空指针。
	  			
	  			
	  			 List<String> rec= toInfixExpression(QT_1[i]);			//调用中序表达式。
	  			 AS[i]=QT[i]+" = "+reckon(rec);
	  			 
	  			 
	  			 if(reckon(rec)<0 || reckon(rec)>10000)  //当数字的结果大小为负数或者大于10000，结束本次循环，重新生成式子。
	  			 {continue;}
	  			 i++;
	  			 
	      	}
	     
	    }
	
	
	  public  List<String> parseSuffixExpression(List<String> ls) {   //逆波兰表达式
	        Stack<String> s1=new Stack<String>();
	        List<String> LS = new ArrayList<String>();
	        for (String str : ls) {
	            if (str.matches("\\d+")) {
	                LS.add(str);
	            }
	            else if(str.equals("(")) {
	            	s1.push(str);
	            }
	            else if (str.equals(")")) {
	            	while(!s1.peek().equals("(")) {
	            		LS.add(s1.pop());
	            	}
	            	s1.pop();
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

	        do {
	            if ((c = s.charAt(w)) < 48 || (c = s.charAt(w)) > 57) {
	                ls.add("" + c);
	               w++;
	                
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
	        List<String> LS=parseSuffixExpression(ls);
	        return LS;
	    }
	
	 public  int reckon(List<String> ls) {     //用于计算线性表里的式子。
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
