import java.io.File;
import java.util.*;
import java.text.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

//import com.sun.org.apache.xml.internal.security.Init;

public class MathExam6356 {
	static int n;
	static int grade;
	static int[] a;
	static int[] b;
	static int[] c;
	static String str[];
	static String AS[];
	static Random fuhao = new Random();
	static Random num1 = new Random();
	static Random num2 = new Random();// 种子相同的Random对象，生成的随机数序列是一样的。
	private static int i;

	public static void main(String[] args) throws FileNotFoundException {
		init(args);

	}

	private static void init(String[] input) throws FileNotFoundException {
		if (input.length == 0) {
			// 默认参数
			n = 10;
			grade = 3;
		} else if (input.length == 4) {
			if (input[0].equals("-n") && input[2].equals("-grade")) {
				try {
					n = Integer.parseInt(input[1]);
					grade = Integer.parseInt(input[3]);
				} catch (NumberFormatException e) {
					System.out.println("请输入正整数！");
				}

			} else if (input[2].equals("-n") && input[0].equals("-grade")) {
				try {
					n = Integer.parseInt(input[3]);
					grade = Integer.parseInt(input[1]);
				} catch (NumberFormatException e) {
					System.out.println("请输入正整数！");
				}

			}
		} else {
			System.out.println("您输入的参数有误，请重新运行！");
			System.exit(0);
		}
		if (grade == 1) {
			gradeOne();
		} else if(grade==2) {
			gradeTwo();
		}else if (grade==3) {
			gradeThree();
		}else {
			System.out.println("目前只支持1-3年级，请重新输入");
		}
		File file = new File("out.txt");
		PrintStream ps = new PrintStream(file);
		System.setOut(ps);// 把创建的打印输出流赋给系统。
		outPut();
	}

	private static void gradeThree() {

		str = new String[n];
		AS=new String[n];
		for(int i=0;i<n;i++) {
		int n1 =num1.nextInt(2)+3;// 随机符号的个数
		a = new int[n1];// 运算符个数
		b=new int [n1+1];// 数字的个数
		String fuhao[]= {"+","-","×","÷"};
		String fuhao1[]=new String[4];	//判断符号
		for(int j=0; j<n1; j++){
			a[j]=num1.nextInt(4);
			fuhao1[j]=fuhao[a[j]];// 随机生成n1个运算符
			}
		
		for(int k =0;k<n1+1;k++) {
			b[k]=num2.nextInt(100);// 根据运算符个数随机生成n+1个数字
		}
		
		String ss=Integer.toString(b[0]);//用来保存一个算式，让它先为一个数字
		
		for(int x=0;x<n1;x++) {
			//String ss=Integer.toString(b[0]);//用来保存一个算式，让它先为一个数字
			if(fuhao1[x].equals("+")||fuhao1[x].equals("-")) {
				int n2=(int)(0+Math.random()*(2-1+1));//随机生成0或1，用来随机生成加减法的括号
				//if(n2==0) {
					//ss="("+ss+fuhao1[x]+b[x+1]+")";
				//}
				//if(n2==1) {
					ss=ss+fuhao1[x]+b[x+1];	
				//}	
			}
			else {
				ss=ss+fuhao1[x]+b[x+1];
			}	
			
		}
		str[i]=ss;
		
	}	List<String> zx=null;
		for (int i = 0; i < str.length; i++) {
		zx= toInfixExpression(str[i]);
		 List<String> rpn=parseSuffixExpression(zx);
				AS[i]=str[i]+"="+reckon(rpn); 
	}
		
			
		
	}

	private static void gradeTwo() {
		a = new int[n];
		b = new int[n];
		c = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = num1.nextInt(9); // 0-100的随机数
			b[i] = num2.nextInt(9);
			c[i] = fuhao.nextInt(2);// 0是乘法1是除法
			if (c[i] == 1 || b[i] == 0) {// 除数是0重新随机
				b[i] = num2.nextInt(8) + 1;
			}
			if (c[i] == 0 || (a[i] == 0 && b[i] == 0)) {
				a[i] = num2.nextInt(8) + 1;
				b[i] = num2.nextInt(8) + 1;
			}
		}

	}

	public static void outPut() {
		for (int i = 0; i < n; i++) {
			if (grade == 1) {
				if (c[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "+" + b[i] + " =");
				} else {
					System.out.println("(" + (i + 1) + ") " + a[i] + "-" + b[i] + " =");
				}
			} else if(grade==2) {
				if (c[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "*" + b[i] + " =");
				} else {
					System.out.println("(" + (i + 1) + ") " + a[i] + "/" + b[i] + " =");
				}
			}else {
				
				System.out.println("(" + (i + 1) + ") " +str[i]);
			
				
			}
		}
		System.out.println("--------------标准答案----------------");
		for (int i = 0; i < n; i++) {
			if (grade == 1) {
				if (c[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "+" + b[i] + " =" + " " + (a[i] + b[i]));
				} else {
					System.out.println("(" + (i + 1) + ") " + a[i] + "-" + b[i] + " =" + " " + (a[i] - b[i]));
				}
			} else if(grade==2){
				if (c[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "*" + b[i] + " =" + " " + (a[i] * b[i]));
				} else if (a[i] % b[i] == 0) {
					System.out.println("(" + (i + 1) + ") " + a[i] + "/" + b[i] + " =" + " " + (a[i] / b[i]));
				} else {
					System.out.println("(" + (i + 1) + ") " + a[i] + "/" + b[i] + " =" + " " + (a[i] / b[i]) + "..."
							+ (a[i] % b[i]));

				}
			}else {
				System.out.println("(" + (i + 1) + ") " +AS[i]);
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		String time = sdf.format(new Date());
		System.out.println("211606356 陈宇 211606333温志铭 " + time);// 输出学号姓名时间
	}

	public static void gradeOne() {
		a = new int[n];
		b = new int[n];
		c = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = num1.nextInt(100);// 0-100的随机数
			b[i] = num2.nextInt(100);
			c[i] = fuhao.nextInt(2);
		}
		for (int i = 0; i < n; i++) {
			if (c[i] == 0) {// 随机加法
				while (a[i] + b[i] > 100) {
					// 随机减法
					a[i] = num1.nextInt(100);
					b[i] = num2.nextInt(100);
				}

			} else {// 随机减法
				while (a[i] - b[i] < 0) {
					// 如果差小于0重新随机
					a[i] = num1.nextInt(100);
					b[i] = num2.nextInt(100);
				}
			}

		}
	}
	  public static  List<String> parseSuffixExpression(List<String> ls) {
	        Stack<String> s1=new Stack<String>();
	        List<String> LS = new ArrayList<String>();
	        for (String ssr : ls) {
	            if (ssr.matches("\\d+")) {
	                LS.add(ssr);
	            }
	            else {Operation a=new Operation();
	                while (s1.size() != 0 && a.getValue(s1.peek()) >= a.getValue(ssr)) {
	                    LS.add(s1.pop());
	                }
	                s1.push(ssr);
	            }
	        }
	        while (s1.size() != 0) {
	            LS.add(s1.pop());
	        }
	        return LS;
	    }
	  
	
	 public static List<String> toInfixExpression(String s) {
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
	        return ls;
	    }
	
	 public static  int reckon(List<String> ls) {
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
	                } else if (str.equals("*")) {
	                    result = a * b;
	                } else if (str.equals("/")) {
	                    result = a / b;
	                }
	                s.push("" + result);
	            }
	        }
	        return Integer.parseInt(s.pop());
	    }
	 public static class Operation { 
		 private int ADDITION=1;
		    private int SUBTRACTION=1;
		    private int MULTIPLICATION=2;
		    private int DIVISION=2;
		 Operation() {
			
		 }
		    

		    public int getValue(String operation){
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
//		                System.out.println("不存在该运算符");
		                result=0;
		        }
		        return result;
		    }
		}

}
