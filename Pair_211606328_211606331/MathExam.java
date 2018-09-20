package com.MathExam6331.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class MathExam {
	
	public static void main(String[] args) throws IOException{
		Scanner in=new Scanner(System.in);
		System.out.println("请输入数字和年级:");
		String str1=in.nextLine();
		String str2=in.nextLine();
		int n=0,m=0;
		if(choose(str1, str2)) {
			n=Integer.parseInt(str1);
			m=Integer.parseInt(str2);
		}
		if(m==1||m==2) grade12(n, m);
		if(m==3) grade3(n);
	}
	public static boolean choose(String str1,String str2) {
		int a=0,b=0;
		if(isNumber(str1)) {
			a=1;
		}/*else System.out.println("请输入正确的题数！");*/
		if(isNumber(str2)) {
			if(Integer.parseInt(str2)==1||Integer.parseInt(str2)==2||Integer.parseInt(str2)==3) {b=1;}
		}
		//else System.out.println("请输入正确的年级！");
		if(a==1&&b==1) return true;
		else return false;
	}
	
	public static void grade12(int n,int m){
		String[] str1=new String[n];
		String[] str2=new String[n];
		for (int i = 0; i < str1.length; i++) {
			str1[i]=out1(m);
		}
		int q=0;
		for (int i = 0; i < str1.length; i++) {
			q=str1[i].indexOf("=");
			str2[i]=str1[i].substring(0, q);
		}
		output(str1, str2);
	}
	
	public static void grade3(int n) {
		String[] str1=input(n);
		String[] str2=new String[n];
		int q=0;
		for (int i = 0; i < str1.length; i++) {
			while(true) {
				if(str1[i].equals("1")) {
					int x=(int)(Math.random()*3)+2;
					if(x==2) {
						str1[i]=out2();
					}
					if(x==3) {
						str1[i]=out3();
					}
					if(x==4) {
						str1[i]=out4();
					}
				}else {
					break;
				}
			}
			for (int j = 0; j < str1[i].length(); j++) {
				if(str1[i].charAt(j)=='=') {
					 q=j;break;
				}
			}
			if(q<str1[i].length())
			str2[i]=str1[i].substring(0, q);
		}
		output(str1, str2);
	}
		
	public static String[] input(int n) {
		String str[]=new String[n];
		for (int i = 0; i < n; i++) {
			int x=(int)(Math.random()*3)+2;
			if(x==2) {
				str[i]=out2();
			}
			if(x==3) {
				str[i]=out3();
			}
			if(x==4) {
				str[i]=out4();
			}
		}
		return str;
	}
	
	public static String out1(int m) {
		String sign[]= {"+","-","×","÷"};
		String str;
		int number1=0,number2=0;
		int answer,a;
		if(m==1) {
			a=(int)(Math.random()*2);
		}else {
			a=(int)(Math.random()*2)+2;
		}
		String s=sign[a];
		number1=(int)(Math.random()*100);
		if(s.equals("+")) {
			 number2=(int)(Math.random()*(100-number1));
		}
		if(s.equals("-")) {
			 number2=(int)(Math.random()*(number1));
		}
		if(s.equals("×")) {
			if(number1!=0) {
			number2=(int)(Math.random()*(100/number1));
			}else {
				number2=(int)(Math.random()*(100));}
		}
		if(s.equals("÷")) {
			if(number1==0) number2=(int)(Math.random()*100)+1;
			else{int[] yin=yinshu(number1);
			number2=yin[(int)(Math.random()*(yin.length))];
			}
		}
		answer=calculate(number1, number2, s);
		str=String.valueOf(number1)+" "+s+" "+String.valueOf(number2)+" "+"="+" "+String.valueOf(answer);
		return str;
	}
	
	public static String out2() {
		String str;
		String sign1[]= {"+","-"};
		String sign2[]= {"×","÷"};
		int b1,b2,c1,c2;
		int number1,number2,number3;
		String s1,s2;
		while(true){
			b1=(int)(Math.random()*2); 
			b2=(int)(Math.random()*2);
			c1=(int)(Math.random()*2); 
			c2=(int)(Math.random()*2);
			if(!(b1==b2&&c1==c2))break;
		}
		if(b1==0) s1=sign1[c1];
		else s1=sign2[c1];
		if(b2==0) s2=sign1[c2];
		else s2=sign2[c2];
		int answer;
		if(b1>=b2) {
			number1=(int)(Math.random()*100);
			number2=sc1(s1,number1);
			answer=calculate(number1, number2, s1);
			number3=sc1(s2, answer);
			answer=calculate(answer, number3, s2);
		}
		else {
			number2=(int)(Math.random()*100);
			number3=sc1(s2,number2);
			answer=calculate(number2, number3, s2);
			number1=sc2(s1,answer);
			answer=calculate(number1, answer, s1);
		}
		str=String.valueOf(number1)+" "+s1+" "+String.valueOf(number2)+" "+s2+" "+String.valueOf(number3)+" "+"="+" "+String.valueOf(answer);
		return str;
	}
	
	public static String out3() {
		String str;
		String sign1[]= {"+","-"};
		String sign2[]= {"×","÷"};
		int a=(int)(Math.random()*2);
		if(a==0) {
			int x=(int)(Math.random()*2);
			int b1,b2,c1,c2;
			int number1,number2,number3;
			while(true) {
				b1=(int)(Math.random()*2); 
				b2=(int)(Math.random()*2);
				c1=(int)(Math.random()*2); 
				c2=(int)(Math.random()*2);
				if(!(b1==b2&&c1==c2)) break;
			} 
			String s1,s2;
			if(b1==0) s1=sign1[c1];
			else s1=sign2[c1];
			if(b2==0) s2=sign1[c2];
			else s2=sign2[c2];
			int answer;
			if(x==0) {
				number1=(int)(Math.random()*100);
				number2=sc1(s1,number1);
				answer=calculate(number1, number2, s1);
				number3=sc1(s2, answer);
				answer=calculate(answer, number3, s2);
				str="( "+String.valueOf(number1)+" "+s1+" "+String.valueOf(number2)+" ) "+s2+" "+String.valueOf(number3)+" = "+String.valueOf(answer);
			}
			else {
				number2=(int)(Math.random()*100);
				number3=sc1(s2,number2);
				answer=calculate(number2, number3, s2);
				number1=sc2(s1,answer);
				answer=calculate(number1, answer, s1);
				str=String.valueOf(number1)+" "+s1+" ( "+String.valueOf(number2)+" "+s2+" "+String.valueOf(number3)+" ) = "+String.valueOf(answer);
			}
		}else {
			String[] s=sc3();
			String number1=s[0],number2=s[1],number3=s[2],number4=s[3],answer=s[4];
			String s1=s[5],s2=s[6],s3=s[7];
			str=String.valueOf(number1)+" "+s1+" "+String.valueOf(number2)+" "+s2+" "+String.valueOf(number3)+" "+s3+" "+String.valueOf(number4)+" = "+String.valueOf(answer);
		}
		return str;
	}
	
	public static String out4() {
		String str="1";
		String sign1[]= {"+","-"};
		String sign2[]= {"×","÷"};
		int a=(int)(Math.random()*2);
		if(a==0) {
			String[] s=sc3();
			String number1=s[0],number2=s[1],number3=s[2],number4=s[3],answer=s[4];
			String s1=s[5],s2=s[6],s3=s[7],s4;
			int b3=Integer.parseInt(s[8]);
			int b4=(int)(Math.random()*2);
			int c4=(int)(Math.random()*2);
			int number5;
			if(b4==0) s4=sign1[c4];
			else s4=sign2[c4];
			int an=Integer.parseInt(answer);
			if(b3>=b4) {
				number5=sc1(s4, an);
				an=calculate(an, number5, s4);
				str=number1+" "+s1+" "+number2+" "+s2+" "+number3+" "+s3+" "+number4+" "+s4+" "+String.valueOf(number5)+" = "+String.valueOf(an);
			}else {
				out4();
			}
		}else {
			int b1,b2,b3,c1,c2,c3;
			int number1,number2,number3,number4;
			while(true) {
				b1=(int)(Math.random()*2); 
				b2=(int)(Math.random()*2);
				b3=(int)(Math.random()*2);
				c1=(int)(Math.random()*2); 
				c2=(int)(Math.random()*2);
				c3=(int)(Math.random()*2);
				if(!(b1==b2&&c1==c2&&b1==b3&&c1==c3&&b2==b3&&c2==c3)) {break;}
				}
			String s1,s2,s3;
			if(b1==0) s1=sign1[c1];
			else s1=sign2[c1];
			if(b2==0) s2=sign1[c2];
			else s2=sign2[c2];
			if(b1==0) s3=sign1[c3];
			else s3=sign2[c3];
			int answer;
			int x=(int)(Math.random()*5);
			if(x==0) {
				number1=(int)(Math.random()*100)+1;
				number2=sc1(s1, number1);
				answer=calculate(number1, number2, s1);
				if(b2>=b3) {
					number3=sc1(s2, answer);
					answer=calculate(answer, number3, s2);
					number4=sc1(s3, answer);
					answer=calculate(answer, number4, s3);
				}else {
					int p=sc1(s2, answer);
					if(s3.equals("×")) {
						if(p==0) {
							number3=0;number4=(int)(Math.random()*100)+1;
						}else {
						number3=sc1("÷", p);
						number4=p/number3;
						}
					}else{
						if(p==0) {
							number3=0;number4=(int)(Math.random()*100)+1;
						}else {
							number4=(int)(Math.random()*(100/p));
							number4++;
							number3=number4*p;
						}
					}
					answer=calculate(answer, p, s2);
				}
				str="( "+String.valueOf(number1)+" "+s1+" "+String.valueOf(number2)+" ) "+s2+" "+String.valueOf(number3)+" "+s3+" "+String.valueOf(number4)+" = "+String.valueOf(answer);	
			}
			if(x==1) {
				if(b1>=b2) {
					number1=(int)(Math.random()*100)+1;
					number2=sc1(s1, number1);
					answer=calculate(number1, number2, s1);
					number3=sc1(s2, answer);
					answer=calculate(answer, number3, s2);
				}else {
					number2=(int)(Math.random()*100)+1;
					number3=sc1(s2, number2);
					answer=calculate(number2, number3, s2);
					number1=sc2(s1, answer);
					answer=calculate(number1, answer, s1);
				}
				number4=sc1(s3, answer);
				answer=calculate(answer, number4, s3);
				str="( "+String.valueOf(number1)+" "+s1+" "+String.valueOf(number2)+" "+s2+" "+String.valueOf(number3)+" ) "+s3+" "+String.valueOf(number4)+" = "+String.valueOf(answer);
			}
			if(x==2) {
				number2=(int)(Math.random()*100)+1;
				number3=sc1(s2, number2);
				answer=calculate(number2, number3, s2);
				if(b1>=b3) {
					number1=sc2(s1, answer);
					answer=calculate(number1, answer, s1);
					number4=sc1(s3, answer);
					answer=calculate(answer, number4, s3);
				}else {
					number4=sc1(s3, answer);
					answer=calculate(answer, number4, s3);
					number1=sc2(s1, answer);
					answer=calculate(number1, answer, s1);
				}
				str=String.valueOf(number1)+" "+s1+" ( "+String.valueOf(number2)+" "+s2+" "+String.valueOf(number3)+" ) "+s3+" "+String.valueOf(number4)+" = "+String.valueOf(answer);
			}
			if(x==3) {
				if(b2>=b3) {
					number2=(int)(Math.random()*100)+1;
					number3=sc1(s2, number2);
					answer=calculate(number2, number3, s2);
					number4=sc1(s3, answer);
					answer=calculate(answer, number4, s3);
				}else {
					number3=(int)(Math.random()*100)+1;
					number4=sc1(s3, number3);
					answer=calculate(number3, number4, s3);
					number2=sc2(s2, answer);
					answer=calculate(number2, answer, s2);
				}
				number1=sc2(s1, answer);
				answer=calculate(number1, answer, s1);
				str=String.valueOf(number1)+" "+s1+" ( "+String.valueOf(number2)+" "+s2+" "+String.valueOf(number3)+"  "+s3+" "+String.valueOf(number4)+" ) = "+String.valueOf(answer);
			}
			if(x==4) {
				if(b1>=b2) {
					int p;
					number1=(int)(Math.random()*100)+1;
					number2=sc1(s1, number1);
					answer=calculate(number1, number2, s1);
					p=sc1(s2, answer);
					if(s3.equals("+")) {
						number3=(int)(Math.random()*p)+1;
						number4=p-number3;
					}else if(s3.equals("-")) {
						number4=(int)(Math.random()*(p/2))+1;
						number3=p-number4;
					}else if(s3.equals("×")) {
						if(p==0) {number3=0;number4=(int)(Math.random()*100)+1;}
						else {
						number3=sc1("÷", p);
						number4=p/number3;
						}
					}else {
						if(p==0) {
							number3=0;number4=(int)(Math.random()*100)+1;
						}else {
							number4=(int)(Math.random()*(100/p));
							number4++;
							number3=number4*p;
						}
					}
					answer=calculate(answer, p, s2);
				}else {
					number3=(int)(Math.random()*100)+1;
					number4=sc1(s3, number3);
					answer=calculate(number3, number4, s3);
					number2=sc2(s2, answer);
					answer=calculate(number2, answer, s2);
					number1=sc2(s1, answer);
					answer=calculate(number1, answer, s1);
				}
				str=String.valueOf(number1)+" "+s1+" "+String.valueOf(number2)+" "+s2+" ( "+String.valueOf(number3)+"  "+s3+" "+String.valueOf(number4)+" ) = "+String.valueOf(answer);
			}
		}
		return str;
	}
	public static String[] sc3() {
		String a[]=new String[9];
		String sign1[]= {"+","-"};
		String sign2[]= {"×","÷"};
		int b1,b2,b3,c1,c2,c3;
		int number1=0,number2=0,number3=0,number4=0;
		while(true) {
			b1=(int)(Math.random()*2); 
			b2=(int)(Math.random()*2);
			b3=(int)(Math.random()*2);
			c1=(int)(Math.random()*2); 
			c2=(int)(Math.random()*2);
			c3=(int)(Math.random()*2);
			if(!(b1==b2&&c1==c2&&b1==b3&&c1==c3&&b2==b3&&c2==c3)) {break;}
			}
		String s1,s2,s3;
		if(b1==0) s1=sign1[c1];
		else s1=sign2[c1];
		if(b2==0) s2=sign1[c2];
		else s2=sign2[c2];
		if(b1==0) s3=sign1[c3];
		else s3=sign2[c3];
		int answer=0;
		if((b1==b2&&b2==b3)||(b1==1&&b2==0&&b3==0)||(b1==1&&b2==1&&b3==0)) {
			number1=(int)(Math.random()*100);
			number2=sc1(s1, number1);
			answer=calculate(number1, number2, s1);
			number3=sc1(s2, answer);
			answer=calculate(answer, number3, s2);
			number4=sc1(s3, answer);
			answer=calculate(answer, number4, s3);
		}
		if(b1==0&&b2==0&&b3==1) {
			number3=(int)(Math.random()*1000);
			number4=sc1(s3, number3);
			answer=calculate(number3, number4, s3);
			number2=sc2(s2, answer);
			if(s1.equals("-")) {
				number1=(int)(Math.random()*1000)+number2+answer;
				answer=calculate(calculate(number1, number2, s1), answer, s2);
			}else {
				answer=calculate(number2, answer, s2);
				number1=(int)(Math.random()*(3000-answer));
				answer=calculate(number1, answer, s1);
			}
		}
		if(b1==0&&b2==1&&b3==0) {
			number2=(int)(Math.random()*1000);
			number3=sc1(s2, number2);
			answer=calculate(number2,number3, s2);
			number1=sc2(s1, answer);
			answer=calculate(number1, answer, s1);
			number4=sc1(s3, answer);
			answer=calculate(answer, number4, s3);
		}
		if(b1==0&&b2==1&&b3==1) {
			number2=(int)(Math.random()*1000);
			number3=sc1(s2, number2);
			answer=calculate(number2,number3, s2);
			number4=sc1(s3, answer);
			answer=calculate(answer,number4, s3);
			number1=sc2(s1, answer);
			answer=calculate(number1, answer, s1);
		}
		if(b1==1&&b2==0&&b3==1) {
			number1=(int)(Math.random()*1000);
			number2=sc1(s1, number1);
			number3=(int)(Math.random()*1000);
			number4=sc1(s3, number3);
			int answer1=calculate(number1, number2, s1);
			int answer2=calculate(number3, number4, s3);
			int t;
			String r;
			if(calculate(answer1, answer2, s2)<0) {
				t=number1;number1=number3;number3=t;
				t=number2;number2=number4;number4=t;
				r=s1;s1=s3;s3=r;
				answer1=calculate(number1, number2, s1);
				answer2=calculate(number3, number4, s3);
			}
			answer=calculate(answer1, answer2, s2);
		}
		a[0]=String.valueOf(number1);a[1]=String.valueOf(number2);a[2]=String.valueOf(number3);
		a[3]=String.valueOf(number4);a[4]=String.valueOf(answer);a[5]=s1;a[6]=s2;a[7]=s3;a[8]=String.valueOf(b3);
		return a;
	}
	
	public static int sc2(String s1, int answer) {
		int number;
		if(s1.equals("+")) {
			 number=(int)(Math.random()*1000);
			 return number;
		}
		if(s1.equals("-")) {
			 number=(int)(Math.random()*1000)+answer+1;
			 return number;
		}
		if(s1.equals("×")) {
			number=(int)(Math.random()*(2000/answer))+1;
			return number;
		}
		if(s1.equals("÷")) {
			if (answer!=0) {
			number=(int)(Math.random()*(3000/answer)+1)*answer;
			return number;}
			else {
				input();
			}
		}
		return 0;
	}
	
	public static int sc1(String s,int answer) {
		int number ;
		if(s.equals("+")) {
			 number=(int)(Math.random()*1000);
			 return number;
		}
		if(s.equals("-")) {
			 number=(int)(Math.random()*(answer));
			 return number;
		}
		if(s.equals("×")) {
			if(answer!=0) {
			number=(int)(Math.random()*(1000/answer))+1;
			return number;}
			else {
				number=(int)(Math.random()*(100));}
		}
		if(s.equals("÷")) {
			if(answer==0) number=(int)(Math.random()*1000)+1;
			else{int[] yin=yinshu(answer);
			number=yin[(int)(Math.random()*(yin.length))];
			return number;}
		}
		return 0;
	}
	
	public static int[] yinshu(int number) {
		int y=0;
		for (int i =1; i <=number; i++) {
			if (number%i==0) {
				y++;
			}
		}
		int[] yin=new int[y];
		for (int i=1,z=0;i<=number;i++) {
			if (number%i==0) {
				yin[z]=i;
				z++;
			}
		}
		return yin;
	}
		
	public static int calculate(int a,int b,String s) {
		if(s.equals("+")) return (a+b);
		if(s.equals("-")) return (a-b);
		if(s.equals("×")) return (a*b);
		if(s.equals("÷")&&b!=0) return (a/b);
		else input(1);;
		return 0;
	}
	public static boolean isNumber(String str) {
		String regex="\\d*";
		if(str.charAt(0)=='0'&&str.length()>1) return false;
		return str.matches(regex);
	}
	
	public static void file(File file) {
		if(!file.exists()) {
			File parent = file.getParentFile();
			if(parent!=null&&!parent.exists()) {
				parent.mkdirs();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void output(String[] str1,String[] str2){
		String filename="d:\\out.txt";
		File file=new File(filename);
		file(file);
		String str="\r\n";
		try {
			OutputStream out=new FileOutputStream(file);
			for (int i = 0; i < str2.length; i++) {
				try {
					str2[i]="( "+String.valueOf(i+1)+" ) "+str2[i]+"\r\n";
					out.write(str2[i].getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				out.write(str.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < str1.length; i++) {
				try {
					str1[i]="( "+String.valueOf(i+1)+" ) "+str1[i]+"\r\n";
					out.write(str1[i].getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
