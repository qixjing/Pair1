package first;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class MathExam6321 {
 
public static void main(String[] args) throws FileNotFoundException  {
	File file = new File("out.txt");
	DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
	// TODO Auto-generated method stub	
	Scanner s=new Scanner(System.in);
	System.out.println("请输入年级：");
	int grade = s.nextInt();
	switch( grade ){
		case 1 : grade_one();break;
		case 2:  grade_two();break;
		case 3:  grade_Three();break;
		}

//	if(grade == 2) { 
//		grade_two();
//	}else { 
//		grade_one();
//	}
	}
public static void grade_one() {
	int p=0; int q=0;
 Scanner input =new Scanner(System.in);
 System.out.println("请输入你想要做几道题目"); 
  int number=input.nextInt();
  int[] d=new int[number];
  int[] e=new int[number];
  int[] f=new int[number];
  
   for (int i = 0; i < number; i++) {
       int a=(int)(0+Math.random()*20);
       int b=(int)(0+Math.random()*20);
       int c=(int)(1+Math.random()*2); 
       d[i] =a;
       e[i]=b;
       f[i]=c;
       if(d[i]<e[i]) {
    	   q=d[i];d[i]=e[i];e[i]=q;
       }
       if(a<b) {
    	   p=a;a=b;b=p;
       }
       
     
        if(c==1) {
           System.out.println("("+(i+1)+") "+a+" + "+b+"= " );
   }else {
     System.out.println("("+(i+1)+") "+a+" - "+b+"= " );
   }   
  }
 
  System.out.println("                                      ");
 
  for (int i = 0; i < number; i++) {
  
       if(f[i]==1) {
      System.out.println("("+(i+1)+") "+d[i]+" + "+e[i]+"= "+(d[i]+e[i]));  
     }else {
      System.out.println("("+(i+1)+") "+d[i]+" - "+e[i]+"= "+(d[i]-e[i])); 
     } 
     
  } 
}


public static void grade_two() {
	System.out.println("请输入出题的数目：");
	Scanner s=new Scanner(System.in);
	int m = s.nextInt();
	int ai[]=new int[m]; 
	int aj[]=new int[m];
	int ax[]=new int[m];
	int m1=1;
	for(int a=0; a<m;a++)
	{
		int i=(int) Math.floor(Math.random()*10+1);
		int j=(int) Math.floor(Math.random()*10+1);
		int x=(int) Math.floor(Math.random()*2+1);
		ai[a]=i;
		aj[a]=j;
		ax[a]=x;
		if(x%2==1) {
		System.out.println("(" + m1 + ") "+ i +" * " + j + "=");
		}else {
		System.out.println("(" + m1 + ") "+ i + " / " + j + "= ");
		}
		m1++;
	}	
	System.out.println("                  ");		
	int m2=1;
	for(int a1=0;a1<m;a1++)
	{
		int B[]=new int[m];  
	    B[m-1]=(int) (ai[a1]%aj[a1]); 
	if(ax[a1]%2==1) 
	{
	System.out.println("("+ m2 +") "+ ai[a1] +" * "+ aj[a1] +" = "+(ai[a1]*aj[a1]));
	}else {
		if(ai[a1]%aj[a1]==0)
		{
			System.out.println("("+ m2 +") "+ ai[a1] +" / "+ aj[a1] +" = "+(ai[a1]/aj[a1]));
		}else {
			System.out.println("("+ m2 +") "+ ai[a1] +" / "+ aj[a1] +" = "+(((ai[a1]-(ai[a1]%aj[a1])))/aj[a1])+"..."+B[m-1]);
	}
		}
	m2++;
	}
}


public static void grade_Three() {
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
		System.out.println("                                 ");		
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

			
				

	



private static String floor(double d) {
	// TODO Auto-generated method stub
	return null;
}}





 
