/**
 * 
 * 
 * 
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class MathExam {
//	存符号
	static char sign[]= {'+','-','*','/'};
//	存放题目
	static List<String> list=new ArrayList<String>();	
//	存放答案
	static List<Integer> answer=new ArrayList<Integer>();
//	出题数目
	static int N=0;
	static int M=1;
	static int O=1;
	static double sum1,sum2;
	
	static Random random=new Random();
	
	static List<String> RPN;
//	定义一个stack纪录运算符
	static Stack<String> ops = new Stack<String>();
//	首先完成一下初始化工作：
//	定义一下op的优先级
	public static int priorityInfo(String symbol) {
	    HashMap<String, Integer> precedence = new HashMap<>();
	    precedence.put("(", 2);  
	    precedence.put(")", 2);
	    precedence.put("+", 0);  
	    precedence.put("-", 0);
	    precedence.put("*", 1);
	    precedence.put("/", 1);
	    return precedence.get(symbol);
	    }
	public static boolean isNumber(String s) {
	    if (s.equals("(") || s.equals(")") || s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
	        return false;
	    return true;
	}
	 
	public static double eval(String op, String val1, String val2) {
//	    if (op.equals("+")) return Integer.parseInt(val1) + Integer.parseInt(val2);
//	    if (op.equals("-")) return Integer.parseInt(val1) - Integer.parseInt(val2);
//	    if (op.equals("/")) return Integer.parseInt(val1) / Integer.parseInt(val2);
//	    if (op.equals("*")) return Integer.parseInt(val1) * Integer.parseInt(val2);
	    if (op.equals("+")) return Double.parseDouble(val1) + Double.parseDouble(val2);
	    if (op.equals("-")) return Double.parseDouble(val1) - Double.parseDouble(val2);
	    if (op.equals("/")) return Double.parseDouble(val1) / Double.parseDouble(val2);
	    if (op.equals("*")) return Double.parseDouble(val1) * Double.parseDouble(val2);
	 
	    throw new RuntimeException("Invalid operator");
	}
//	下面将中缀表达式转化为后缀表达式
	/**
	 * 中缀表达式换成后缀表达式
	 *
	 * @param in
	 */
	
	public static void fixPosition(String[] in) {
	    for (int i = 0; i < in.length; i++) {
	        if (isNumber(in[i])) {
	            //如果是数字,直接进入数组
	            RPN.add(in[i]);
	        } else {
	 
	            if (ops.isEmpty()) {
	                ops.push(in[i]);
	            } else if (in[i].equals("(")) {
	                ops.push(in[i]);
	            } else if (in[i].equals(")")) {
	                //括号出栈
	                bracketsPop();
	            } else {
	                //如果需要添加的优先级小于或者等于栈顶的优先级 则栈顶的优先级出栈 新的元素入栈,否则的话 新的元素进栈
	                int newpriority = priorityInfo(in[i]);
	                int stackTopPriority = priorityInfo(ops.peek());
	                //需要排除栈顶元素不是括号
	                if (stackTopPriority != 2 && newpriority <= stackTopPriority) {
	                    RPN.add(ops.pop());
	                }
	                ops.push(in[i]);
	            }
	        }
	    }
	//for循环执行完毕，将op  stack剩余的op移到list中
	    while (!ops.isEmpty())
	        RPN.add(ops.pop());
	}
	private static void bracketsPop() {
	 
	    while (!ops.peek().equals("(")) {
	        RPN.add(ops.pop());
	    }
	    //最后需要把最上层的"("给去掉
	    ops.pop();
	}
	private static void calculate() {
	    for (int i = 0; i < RPN.size(); i++) {
	        if (!isNumber(RPN.get(i))) {
	            //需要讲前面两个数字进行运算,从新排序
	        	try {
					double newTmp = eval(RPN.get(i), RPN.get(i - 2), RPN.get(i - 1));
		            RPN.remove(i);
		            RPN.remove(i - 1);
		            RPN.remove(i - 2);
		            RPN.add(i - 2, newTmp + "");
		            if (RPN.size() == 1) {
//		                System.out.println(RPN.get(0));
		                sum1=Double.parseDouble(RPN.get(0));
		                return;
		            }
				} catch (Exception e) {
					// TODO: handle exception
						sum1=Double.NEGATIVE_INFINITY;
					return;
				}
	            break;
	        }
	    }
	    calculate();
	}	

	
//	生成并返回随机数
	public static int createNumber(int S) {
//		用Random的nextInt方法来生成随机数
//		S表示生成随机数的范围
		return random.nextInt(S);
	}
//	生成题目
	public static void createProblem() {
//		调用pro_num生成题目和符合，并存入字符串集合list里面
//		再通过逆波兰算法计算题目的结果，存入answer里面
//		通过N来控制出题数目
		String str;
		for(int i=0;i<N;i++) {
			while(true) {
				if(O>2 && createNumber(5)==0) {
					str=brackets();
				}else {
					str=nubrackets();
				}
				RPNcalculate(str);
				sum2=(int)sum1;
				if(sum1==sum2 && sum1>=0.0) {
					break;
				}
			}
			list.add(str);
			answer.add((int)sum1);
//			System.out.println(str+"= "+(int)sum1);
		}
		createFile("out");
		waterFile("out");
	}
	public static String brackets() {
		int i;
		int size=random.nextInt(1)+2;
		int start=random.nextInt(3);
		String str="";
		for(i=0;i<start+size+1;i++) {
			if(i==start) {
				str+="( ";
			}
			str=str+random.nextInt(100)+" ";
			if(i==start+size) {
				str+=") ";
			}
			if(i<=start || i>=start+size) {
				str=str+sign[random.nextInt(1)+2]+" ";
			}else {
				str=str+sign[random.nextInt(4)]+" ";
			}
			
		}
		str=str+random.nextInt(100)+" ";
		if(i==start+size) {
			str+=") ";
		}
		
//		System.out.println(str);
		return str;
	}
	public static String nubrackets() {
		int T=random.nextInt(3)+2;
		if(O<3) {
			T=1;
		}
		String str="";
		for(int i=0;i<T;i++) {
			str=str+random.nextInt(100)+" ";
			str=str+sign[random.nextInt(M)]+" ";
		}
		str=str+random.nextInt(100)+" ";
		
//		System.out.println(str);
		return str;
	}
	
	
//	创建文件
	public static void createFile(String name) {
		String filename=name+".txt";
		File file=new File(filename);
		if(!file.exists()) {
			System.out.println("文件创建成功");
		}else {
			System.out.println("文件已存在");
		}
	}
//	写入文件
	public static void waterFile(String name) {
		/* 向文件写入计算题 */
		PrintStream pS=null;
		try {
			pS = new PrintStream(new FileOutputStream(name+".txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("加载文件失败");
			e.printStackTrace();
		}
		/* 题目 */
		for (int i = 0; i < N; i++) {
			pS.println("("+(i+1)+") "+list.get(i));
		}
		pS.println("");
		System.out.println("");
		/* 标准答案 */
		for (int i = 0; i < N; i++) {
			pS.println("("+(i+1)+") "+list.get(i)+" = "+answer.get(i));
		}
		pS.close();
	}
	
	
//	运用逆波兰算法计算结果
	public static void RPNcalculate(String str) {
		String[] RPNStr= str.split(" ");
		fixPosition(RPNStr);
		calculate();
	}
	public static void main(String[] args) {
		String num="0";
		String grade="1";
		for(int i=0;i<args.length;i++) {
			if(args[i].equals("-n")) {
				num=args[i+1];
			}
			if(args[i].equals("-grade")) {
				grade=args[i+1];
			}
		}
		if(!num.equals("0") && num.matches("0*[0-9]{0,3}") && grade.matches("[1-3]")) {
			N=Integer.parseInt(num);
			O=Integer.parseInt(grade);
			if(O==1) {
				M=2;
			}else {
				M=4;
			}
			createProblem();
		}else {
			System.out.println("请输入符合要求的四则运算题目数量");
		}
	}
}
