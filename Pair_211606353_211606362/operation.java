package operationBag;

import java.util.*;
import java.io.*;


public class operation {
	
	
	public static void main(String[] arg)throws FileNotFoundException {
		Scanner sc = new Scanner(System.in); 
		
		int grade,n;//grade:年级，n:题数
		System.out.print("Please enter the grade and n: ");
		grade=sc.nextInt();
		n=sc.nextInt();
//		System.out.println("grade: " + grade);
//		System.out.println("n: " + n);
		function(n);
		
		
	}
	
	public static void function(int n)throws FileNotFoundException {
		System.setOut(new PrintStream(new BufferedOutputStream(
				new FileOutputStream("d:/operation/print.txt")),true));
		//加一个true相当于flush
		
		Random random = new Random();//随机数种子
		
		int[] a = new int[n];
		int[] b = new int[n];
		int[] c = new int[n];
		
		int[] symbol1 = new int[n];
		int[] symbol2 = new int[n];
		
		int result;
		
		System.out.println("题目：");
		for(int i=0; i<n; i++) {
			a[i]=random.nextInt(1000)+1;
			b[i]=random.nextInt(1000)+1;
			c[i]=random.nextInt(1000)+1;
			symbol1[i]=random.nextInt(4);
			symbol2[i]=random.nextInt(4);
			
			if(symbol1[i] == 3) {
				if(a[i]%b[i]!=0) {
					a[i]=a[i]/b[i]*b[i];
				}
				if(symbol2[i] == 3) {
					while(a[i]/b[i]%c[i]!=0) {
						c[i]=random.nextInt(a[i]/b[i])+1;
					}
				}
			}
			else if(symbol2[i] == 3) {
				if(b[i]%c[i]!=0) {
					b[i]=b[i]/c[i]*c[i];
				}
			}
			//++超过1000
			if(symbol1[i] ==0 && symbol2[i] ==0) {
				while((a[i]+b[i]+c[i])>1000) {
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
					c[i]=random.nextInt(1000)+1;
				}
			}
			//--为负数
			if(symbol1[i] ==1 && symbol2[i] ==1) {
				while((a[i]-b[i]-c[i])<0) {
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
					c[i]=random.nextInt(1000)+1;
				}
			}
			//+-
			if(symbol1[i] ==0 && symbol2[i] ==1) {
				while((a[i]+b[i])>1000) {
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
				}
				while((a[i]+b[i]-c[i] < 0))
					c[i]=random.nextInt(a[i]+b[i]-1)+1;
			}
			//-+
			if(symbol1[i] ==1 && symbol2[i] ==0) {
				while((a[i]-b[i])<0) {
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
				}
				while((a[i]-b[i]+c[i]>1000)) {
					c[i]=random.nextInt(1000)+1;
				}
			}
			//+*
			if(symbol1[i] ==0 && symbol2[i] ==2) {
				while((a[i]+b[i]*c[i])>1000) {
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
					c[i]=random.nextInt(1000)+1;
				}
			}
			//*+
			if(symbol1[i] ==2 && symbol2[i] ==0) {
				while((a[i]*b[i]+c[i])>1000) {
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
					c[i]=random.nextInt(1000)+1;
				}
			}
			//-*
			if(symbol1[i] ==1 && symbol2[i] ==2) {
				while((a[i]-b[i]*c[i])<0) {
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
					c[i]=random.nextInt(1000)+1;
				}
			}
			//**
			if(symbol1[i] ==2 && symbol2[i] ==2) {
				while((a[i]*b[i]*c[i])>1000) {
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
					c[i]=random.nextInt(1000)+1;
				}
			}
			///*
			if(symbol1[i] ==3 && symbol2[i] ==2) {
				while((a[i]/b[i]*c[i])>1000 ) {
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
					c[i]=random.nextInt(1000)+1;
				}
			}
			//+/
			if(symbol1[i] ==0 && symbol2[i] ==3) {
				while((a[i]+b[i]/c[i])>1000 ) {
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
					c[i]=random.nextInt(1000)+1;
				}
			}
			//-/
			if(symbol1[i] ==1 && symbol2[i] ==3) {
			while((a[i]-b[i]/c[i])<0 ) {
				a[i]=random.nextInt(1000)+1;
				b[i]=random.nextInt(1000)+1;
				c[i]=random.nextInt(1000)+1;
			}
			//*-
			if(symbol1[i] ==2 && symbol2[i] ==1) {
				while((a[i]*b[i]-c[i])>1000 || (a[i]*b[i]-c[i])<0){
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
					c[i]=random.nextInt(1000)+1;
				}
			}
			//*/
			if(symbol1[i] ==2 && symbol2[i] ==3) {
				while((a[i]*b[i]/c[i])>1000 ) {
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
					c[i]=random.nextInt(1000)+1;
				}
			}
			///+
			if(symbol1[i] ==3 && symbol2[i] ==0) {
				while((a[i]/b[i]+c[i])>1000 ) {
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
					c[i]=random.nextInt(1000)+1;
				}
			}
			///-
			if(symbol1[i] ==3 && symbol2[i] ==1) {
				while((a[i]/b[i]-c[i])<0 ) {
					a[i]=random.nextInt(1000)+1;
					b[i]=random.nextInt(1000)+1;
					c[i]=random.nextInt(1000)+1;
				}
			}
		}
			System.out.print(a[i]);
			if(symbol1[i] == 0)System.out.print(" + ");
			if(symbol1[i] == 1)System.out.print(" - ");
			if(symbol1[i] == 2)System.out.print(" * ");
			if(symbol1[i] == 3)System.out.print(" / ");
			System.out.print(b[i]);
			if(symbol2[i] == 0)System.out.print(" + ");
			if(symbol2[i] == 1)System.out.print(" - ");
			if(symbol2[i] == 2)System.out.print(" * ");
			if(symbol2[i] == 3)System.out.print(" / ");
			System.out.println(c[i]);
		}
		
		System.out.println();
		System.out.println("答案：");
		for(int i=0; i<n; i++) {
			
			System.out.print(a[i]);
			if(symbol1[i] == 0)System.out.print(" + ");
			if(symbol1[i] == 1)System.out.print(" - ");
			if(symbol1[i] == 2)System.out.print(" * ");
			if(symbol1[i] == 3)System.out.print(" / ");
			System.out.print(b[i]);
			if(symbol2[i] == 0)System.out.print(" + ");
			if(symbol2[i] == 1)System.out.print(" - ");
			if(symbol2[i] == 2)System.out.print(" * ");
			if(symbol2[i] == 3)System.out.print(" / ");
			System.out.print(c[i]);
			System.out.print(" = ");
			//++
			if(symbol1[i]==0 && symbol2[i]==0) {
				result = a[i]+b[i]+c[i];
				System.out.println(result);
			}
			//+-
			if(symbol1[i]==0 && symbol2[i]==1) {
				result = a[i]+b[i]-c[i];
				System.out.println(result);
			}
			//+*
			if(symbol1[i]==0 && symbol2[i]==2) {
				result = a[i]+b[i]*c[i];
				System.out.println(result);
			}
			//+/
			if(symbol1[i]==0 && symbol2[i]==3) {
				result = a[i]+b[i]/c[i];
				System.out.println(result);
			}
			
			//-+
			if(symbol1[i]==1 && symbol2[i]==0) {
				result = a[i]-b[i]+c[i];
				System.out.println(result);
			}
			//--
			if(symbol1[i]==1 && symbol2[i]==1) {
				result = a[i]-b[i]-c[i];
				System.out.println(result);
			}
			//-*
			if(symbol1[i]==1 && symbol2[i]==2) {
				result = a[i]-b[i]*c[i];
				System.out.println(result);
			}
			//-/
			if(symbol1[i]==1 && symbol2[i]==3) {
				result = a[i]-b[i]/c[i];
				System.out.println(result);
			}
			
			//*+
			if(symbol1[i]==2 && symbol2[i]==0) {
				result = a[i]*b[i]+c[i];
				System.out.println(result);
			}
			//*-
			if(symbol1[i]==2 && symbol2[i]==1) {
				result = a[i]*b[i]-c[i];
				System.out.println(result);
			}
			//**
			if(symbol1[i]==2 && symbol2[i]==2) {
				result = a[i]*b[i]*c[i];
				System.out.println(result);
			}
			//*/
			if(symbol1[i]==2 && symbol2[i]==3) {
				result = a[i]*b[i]/c[i];
				System.out.println(result);
			}
			
			///+
			if(symbol1[i]==3 && symbol2[i]==0) {
				result = a[i]/b[i]+c[i];
				System.out.println(result);
			}
			///-
			if(symbol1[i]==3 && symbol2[i]==1) {
				result = a[i]/b[i]-c[i];
				System.out.println(result);
			}
			///*
			if(symbol1[i]==3 && symbol2[i]==2) {
				result = a[i]/b[i]*c[i];
				System.out.println(result);
			}
			////
			if(symbol1[i]==3 && symbol2[i]==3) {
				result = a[i]/b[i]/c[i];
				System.out.println(result);
			}
		}
	}
}
