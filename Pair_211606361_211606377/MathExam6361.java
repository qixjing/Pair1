package first;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class MathExam6361 {
	
public static void main(String[] args) throws FileNotFoundException   {
    Scanner input =new Scanner(System.in);
    System.out.println("请输入你想要的的年级  ");
    int grade=input.nextInt();
     if(grade==1) {
    	grade1();
    }else if(grade==2) {
    	grade2();   
    }else if(grade==3) {
    	grade3();
   }
   
    }


    
  
	public static void grade3() {
 	// TODO Auto-generated method stub
	  System.out.println("请输入你想要做几道题目");
	  Scanner input =new Scanner(System.in);
	  int number2=input.nextInt();
	  String[] f=new String[4];
	  f[0]="+";
	  f[1]="-";
	  f[2]="*";
	  f[3]="/";
	  int[] b=new int[number2];
	  int[] b1=new int[number2];
	  int[] b2=new int[number2];
	  int[] b3=new int[number2];
	  int[] b4=new int[number2];
	  int[] b5=new int[number2];
	  int[] b6=new int[number2];
	  int[] b7=new int[number2];
	  int[] b8=new int[number2];
	  int[] b9=new int[number2];
	  int[] b10=new int[number2];
	 
	 
	 
	  for (int i = 0; i < number2; i++) {
		  int a=(int)(0+Math.random()*100);
		  int a1=(int)(0+Math.random()*100);
		  int a2=(int)(0+Math.random()*100);
		  int a3=(int)(0+Math.random()*100);
		  int a4=(int)(0+Math.random()*100);
		  int e=(int)(1+Math.random()*3);
		  int h=(int)(Math.random()*f.length);
		  int h1=(int)(Math.random()*f.length);
		  int h2=(int)(Math.random()*f.length);
		  int h3=(int)(Math.random()*f.length);
		  b1[i]=a;
		  b2[i]=a1;
		  b3[i]=a2;
		  b4[i]=a3;
		  b5[i]=a4;
		  b6[i]=e;
		  b7[i]=h;
		  b8[i]=h1;
		  b9[i]=h2;
		  b10[i]=h3;
		  
		 
	    
	   if(e==1) {
		   
			  System.out.println("("+(i+1)+") "+a+" "+f[h]+" "+a1+" "+f[h1]+" "+a2+"= ");
		  }else if(e==2){
			  System.out.println("("+(i+1)+") "+a+" "+f[h]+" "+a1+" "+f[h1]+" "+a2+" "+f[h2]+" "+a3+"= ");
		  }else {
			  System.out.println("("+(i+1)+") "+a+" "+f[h]+" "+a1+" "+f[h1]+" "+a2+" "+f[h2]+" "+a3+" "+f[h3]+" "+a4+"= ");			  
		  }
	   
		  }
	   System.out.println("==============标准答案===============");
	   
	    for (int i = 0; i < number2; i++) {
	      if(b6[i]==1) {
	    	 System.out.println("("+(i+1)+") "+b1[i]+" "+f[b[7]]+" "+b2[i]+" "+f[b8[i]]+" "+b3[i]+"= ");
		  }else if(b6[i]==2){
			  System.out.println("("+(i+1)+") "+b1[i]+" "+f[b[7]]+" "+b2[i]+" "+f[b8[i]]+" "+b3[i]+" "+f[b9[i]]+" "+b4[i]+"= ");
		  }else {
			  System.out.println("("+(i+1)+") "+b1[i]+" "+f[b[7]]+" "+b2[i]+" "+f[b8[i]]+" "+b3[i]+" "+f[b9[i]]+" "+b4[i]+" "+f[b10[i]]+" "+b5[i]+"= ");			  
		  }
			
		}
	    
		  
	  }		
	


	public static void grade2() {
	// TODO Auto-generated method stub
		 int t=0; int s=0;
 		 System.out.println("请输入你想要做几道题目");	
		 Scanner input =new Scanner(System.in);
		 int number=input.nextInt();
		 int[] d=new int[number];
		 int[] e=new int[number];
		 int[] f=new int[number];
		 int[] g=new int[number];
		 
		 	 
	   for (int i = 0; i < number; i++) {
	       int a=(int)(0+Math.random()*20);
	       int b=(int)(0+Math.random()*20);
	       int c=(int)(1+Math.random()*4); 
	       d[i] =a;
	       e[i]=b;
	       f[i]=c;
	       g[i]=d[i]%e[i];
	       if(a<b) {
	    	   s=a;a=b;b=s;	    	   
	       }
	       if(d[i]<e[i]) {
	    	  t=d[i]; d[i]=e[i];e[i]=t; 
	       }	      
	     
	        if(c==1) {
	           System.out.println("("+(i+1)+") "+a+" + "+b+"= " );
		  }else if(c==2){
			   System.out.println("("+(i+1)+") "+a+" - "+b+"= " );	
		  }else if(c==3) {
			   System.out.println("("+(i+1)+") "+a+" * "+b+"= " );		
		  }else {
			  System.out.println("("+(i+1)+") "+a+" / "+b+"= " );	
		 }
	   }
		 System.out.println("================标准答案=====================");
		
		 for (int i = 0; i < number; i++) {	
	   	   if(f[i]==1) {
	   		 System.out.println("("+(i+1)+") "+d[i]+" + "+e[i]+"= "+(d[i]+e[i]));	 
	   	    }else if(f[i]==2) {
	   		 System.out.println("("+(i+1)+") "+d[i]+" - "+e[i]+"= "+(d[i]-e[i]));	
	   	    }else if(f[i]==3) {
	   		 System.out.println("("+(i+1)+") "+d[i]+" * "+e[i]+"= "+(d[i]*e[i]));
		    }else {
			  System.out.println("("+(i+1)+") "+d[i]+" / "+e[i]+"= "+(d[i]/e[i])   +"=="+      "=="  +g[i]);	
		  }
	   	
}
		
	 }

	public static void grade1() {
	// TODO Auto-generated method stub
		int t=0; int s=0;
	    Scanner input =new Scanner(System.in);
		System.out.println("请输入你想要做几道题目");		 	
		 int number1=input.nextInt();
		 int[] d=new int[number1];
		 int[] e=new int[number1];
		 int[] f=new int[number1];
		 
	   for (int i = 0; i < number1; i++) {
	       int a=(int)(0+Math.random()*20);
	       int b=(int)(0+Math.random()*20);
	       int c=(int)(1+Math.random()*2); 
	       d[i] =a;
	       e[i]=b;
	       f[i]=c;
	       if(a<b) {
	    	   s=a;a=b;b=s;	    	   
	       }
	       if(d[i]<e[i]) {
	    	  t=d[i]; d[i]=e[i];e[i]=t; 
	       }
	     
	        if(c==1) {
	           System.out.println("("+(i+1)+") "+a+" + "+b+"= " );
		  }else{
			   System.out.println("("+(i+1)+") "+a+" - "+b+"= " );		
	   }
	   }
		 System.out.println("================标准答案=====================");
		
		 for (int i = 0; i < number1; i++) {	
	   	   if(f[i]==1) {
	   		 System.out.println("("+(i+1)+") "+d[i]+" + "+e[i]+"= "+(d[i]+e[i]));	 
	   	    }else {
	   		 System.out.println("("+(i+1)+") "+d[i]+" - "+e[i]+"= "+(d[i]-e[i]));	
	   	    }
	    } 
	} 
	}





