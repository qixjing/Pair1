package Work01.Math;

import java.util.Scanner;

public class MathExam02 {
	public static void main(String[] args){
		Scanner g=new Scanner(System.in);
		System.out.println("请输入你所在的年级(1或2或3)：");
		int grade=g.nextInt();
		if(grade==1) {
			one();
		}
		if(grade==2) {
			two();
		}
		if(grade==3) {
			three();
		}
	}
	public static void three() {
		System.out.println("题目的数量：");
		Scanner n=new Scanner(System.in);
		int x=n.nextInt();
		int i1[]=new int[x];
		int i2[]=new int[x];
		int i3[]=new int[x];
		int i4[]=new int[x];
		int i5[]=new int[x];
		String i6[]=new String[x];
		String i7[]=new String[x];
		String i8[]=new String[x];
		int l=0;
		String q[]=new String[4];
		q[0]="+";
		q[1]="-";
		q[2]="X";
		q[3]="/";
		for(int m=0;m<x;m++) {
			int a=(int)(1+Math.random()*20);
			int b=(int)(1+Math.random()*20);
			int a1=(int)(1+Math.random()*20);
			int a2=(int)(1+Math.random()*20);
			int c=(int)(1+Math.random()*2);
			int d=(int)(Math.random()*q.length);
			int d1=(int)(Math.random()*q.length);
			int d2=(int)(Math.random()*q.length);
			i1[m]=a;
			i2[m]=b;
			i3[m]=a1;
			i4[m]=a2;
			i5[m]=c;
			i6[m]=q[d];
			i7[m]=q[d1];
			i8[m]=q[d2];
			l++;
			
			if(c==0) {
				System.out.println(" ("+l+") " + a + " " + q[d] +" " + b + " = ");
			}
			if(c==1){
				System.out.println(" ("+l+") " + a + " "+ q[d] + " " + b + " " +q[d1] + " "+ a1 +" = ");
			}
			if(c==2){
				System.out.println(" ("+l+") " + a + " "+ q[d] + " "+ b + " " +q[d1]+ " " + a1 +" "+ q[d2]+" " + a2 + " = ");
			}
		}
		System.out.println("                                           ");		
		int y=0;
		for(int z=0;z<x;z++) {
			y++;
			if(i5[z]==0) {
				System.out.println(" ("+y+") " + i1[z] + " " + i6[z] +" " + i2[z] + " = " );
			}
			if(i5[z]==1){
				System.out.println(" ("+y+") " + i1[z] + " "+ i6[z] + " " + i2[z] + " " +i7[z] + " "+ i3[z] +" = ");
			}
			if(i5[z]==2){
				System.out.println(" ("+y+") " + i1[z] + " "+ i6[z] + " "+ i2[z] + " " +i7[z]+ " " + i3[z] +" "+ i8[z]+" " + i4[z] + " = ");
			}
	}
		
	}
	
	public static void one() {
		System.out.println("题目的数量：");
		Scanner n=new Scanner(System.in);
		int x=n.nextInt();
		int i[]=new int[x];
		int j[]=new int[x];
		int k[]=new int[x];
		int l=0;
		for(int m=0;m<x;m++) {
			int a=(int)(0+Math.random()*20);
			int b=(int)(0+Math.random()*20);
			int c=(int)(0+Math.random()*3);
			i[m]=a;
			j[m]=b;
			k[m]=c;
			l++;
			if(c==0) {
				System.out.println(" ("+l+") " + a + " + " + b + " = ");
			}else if(a<b){
				System.out.println(" ("+l+") " + b + " - " + a+" = ");
			}else {
				System.out.println(" ("+l+") " + a + " - " + b + " = ");
			}
		}
		System.out.println("                                                ");		
		int y=0;
		for(int z=0;z<x;z++) {
			y++;
			if(k[z]==0) {
				System.out.println(" ("+y+") " + i[z] + " + " + j[z] + " = " + (i[z]+j[z]));
			}else if(i[z]<j[z]){
				System.out.println(" ("+y+") "+j[z]+" - "+i[z]+" = "+(j[z]-i[z]));
			}else {
				System.out.println(" ("+y+") "+i[z]+ " - "+j[z]+" = "+(i[z]-j[z]));
			}
	}
	}

	public static void two() {
		System.out.println("题目的数量：");
		Scanner n=new Scanner(System.in);
		int x=n.nextInt();
		int f[]=new int[x];
		int v[]=new int[x];
		int s[]=new int[x];
		int l=0;
		for(int m=0;m<x;m++) {
			int a=(int)(0+Math.random()*20);
			int b=(int)(0+Math.random()*20);
			int c=(int)(0+Math.random()*4);
			f[m]=a;
			v[m]=b;
			s[m]=c;
			l++;
			if(c==0) {
				System.out.println(" ("+l+") " + a + " + " + b + " = ");
			}
			if(c==1 ){
				System.out.println(" ("+l+") " + b + " - " + a+" = ");
			}

			
			if(c==2 || b==0) {
				System.out.println(" ("+l+") " + a + " X " + b + " = ");
			}
			if(c==3 && b!=0) {
				System.out.println(" ("+l+") " + a + " / " + b + " = ");
			}
		}
		System.out.println("                                                       ");		
	int t=0;
	for(int h=0;h<x;h++) {
		t++;
		if(s[h]==0) {
			System.out.println(" ("+t+") " + f[h] + " + " + v[h] + " = "+(f[h]+v[h]));
		}
		if(s[h]==1 ){
			System.out.println(" ("+t+") " + f[h] + " - " + v[h]+" = "+(f[h]-v[h]));
		}

		if(s[h]==2 ||v[h]==0) {
			System.out.println(" ("+t+") " + f[h] + " X " + v[h]+" = "+(f[h]*v[h]));
		}
		if(s[h]==3 && v[h]!=0) {
			if(f[h]%v[h]==0) {
			System.out.println(" ("+t+") " + f[h] + " / " + v[h]+" = "+(f[h]/v[h]));
		}else {
			System.out.println(" ("+t+") " + f[h] + " / " + v[h]+" = "+(f[h]/v[h])+"..."+(f[h]%v[h]));
		}
			}
}
}
	
	
}