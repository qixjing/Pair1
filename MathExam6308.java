

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;
public class MathExam6306 {

	public static void main(String[] args) throws FileNotFoundException {		
		// TODO 自动生成的方法存根
		int n,g;
		Scanner scanner = new Scanner(System.in);
		// 从键盘接收数据
		n = scanner.nextInt();
		 // 接收的数据赋值给n
		g = scanner.nextInt();
		String[] str = new String[n];
File file = new File("out6306.txt");
	PrintStream ps = new PrintStream(file);
		System.setOut(ps);
		if (g == 1) {
			zzz(n, str);
		} else if (g==2) {
			xxx(n, str);
		} 
		else if (g==3) {
			ttt(n, str);
		} 
		else {
			System.out.println("不会做");
		}
	}
	public static void zzz(int n,String[] str) {
		int x;
		int y;
		for (int i = 0; i < n; i++) {
			int z = (int)(Math.random()*(2));
			x = (int)(Math.random()*100);
			y = (int)(Math.random()*100);
			
		if(z==0) {
			System.out.println("("+(i+1)+")" + "" + x + "+" + y + "" + "=" + " " );
			str[i] = "(" + (i+1) + ")" +" " + x + "+" + y + " " + "=" + " " + (x+y);}
		else{
			if(x-y<0) {
				System.out.println("("+(i+1)+")" + "" + y + "-" + x + "" + "=" + " " );
				str[i] = "(" + (i+1) + ")" +" " + y + "-" + x + " " + "=" + " " + (y-x);
			}
			else {
System.out.println("("+(i+1)+")" + "" + x + "-" + y + "" + "=" + " " );
				str[i] = "(" + (i+1) + ")" +" " + x + "-" + y + " " + "=" + " " + (x-y);
		}
			}
		}
		System.out.println("标准答案");
		for(String s:str)
			System.out.println(s);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		System.out.println("              211606306 姓名：黄柏涛  " +sdf.format(new Date()));


		
	}
	public static void xxx(int n,String[] str) {
		int x;
		int y;
		for (int i = 0; i < n; i++) {
			int z = (int)(Math.random()*(2));
			x = (int)(Math.random()*9)+1;
			y = (int)(Math.random()*9)+1;
			
		if(z==0) {
			System.out.println("("+(i+1)+")" + "" + x + "*" + y + "" + "=" + " " );
			str[i] = "(" + (i+1) + ")" +" " + x + "*" + y + " " + "=" + " " + (x*y);}
		else{
			if(x-y<0) {
				System.out.println("("+(i+1)+")" + "" + y + "/" + x + "" + "=" + " " );
				str[i] = "(" + (i+1) + ")" +" " + y + "/" + x + " " + "=" + " " + (y/x)+"..."+(y%x);
			}

else {
				System.out.println("("+(i+1)+")" + "" + x + "/" + y + "" + "=" + " " );
				str[i] = "(" + (i+1) + ")" +" " + x + "/" + y + " " + "=" + " " + (x/y)+"..."+(x%y);
		}
			}
		}
		System.out.println("标准答案");
		for(String s:str)
			System.out.println(s);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		System.out.println("              211606308 姓名：江东  " +sdf.format(new Date()));

		
		
		
	}
private static  void ttt( int n ,String[] str) {
		// TODO 自动生成的方法存根
		str = new String[n];
		
		for (int i = 0; i < n; i++) {
			int x = (int)(Math.random()*10);
			int y = (int)(Math.random()*10);
			int z = (int)(Math.random()*10);
			int f = (int)(Math.random()*2);;
			//int 转化为 String
			String x1 = String.valueOf(x);
			String y1 = String.valueOf(y);
			String z1 = String.valueOf(z);
			String fh1;
			String fh2;
			if (f == 0) {
				fh1= "+";
				fh2= "-";
			}
			 else{
				fh2= "+";
				fh1= "-";
			}
			
			String [] strArr = { x1,y1,z1,fh1,fh2 };
			System.out.println("(" + (i+1) + ")" +" " + z1 + " " + fh1 + " " + y1 + " " + fh2 + " " + x1 + " = " );
			str[i] = "(" + (i+1) + ")" +" " + z1 +" " + fh1  +" " + y1 +" " + fh2 +" " + x1 + " = "  + rem(strArr);
		}
		System.out.println("--------标准答案---------");
		//输出答案
		for(String a:str)
			System.out.println(a);
	}
private static String rem (String[] strArr){
        String str = "+-*/";
        Stack<String> stack = new Stack<String>();
        //遍历数组中的每一个元素
        for(String s : strArr){
        	//如果是数字,放入栈中
            if(!str.contains(s)){
                stack.push(s);
            }
            else{
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                switch(s){
                case "+" :
                	stack.push(String.valueOf(a+b));
                    break;
                case "-" :
                	stack.push(String.valueOf(a-b));
                    break ;
                case "*" :
                    stack.push(String.valueOf(a*b));
                    break;
                case "/" :
                    stack.push(String.valueOf(a/b));
                    break ;
                }
            }
        }
        return stack.pop();
    }
		
}