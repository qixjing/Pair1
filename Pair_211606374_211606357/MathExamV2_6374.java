


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.SynchronousQueue;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class MathExamV2_6374 {

	public static void main(String[] args) throws IOException {
		int i,a,b,fuhao,d,e,f;
		Scanner input=new Scanner(System.in);
		int flag=0,flag2=0;
		int []c =new int [args.length] ;    // 创建一个数组存入参数
	
		String[] timu=new String[c[0]+1];	// 创建数组存入题目
		String[] daan=new String[c[0]+1];
		String huanhang="\r\n";    // windows的换行，在cmd运行方便观看
		File file = new File("out.txt");
        FileOutputStream u = new FileOutputStream("out.txt");
		if(!file.exists()) {
			file.createNewFile();
		}    // 创建out.txt文本
		if(flag2==1) {
		for(;;) {
			if(c[0]>400&&c[1]==1) {
			System.out.println("请输入1-400之间的题目数，否则题目重复，请重新输入，直接输入题目数和年级即可");
			 c[0]=input.nextInt();
			 c[1]=input.nextInt();
			continue;
			}		
			if(c[1]<1) {
			System.out.println("输入的题目数请大于1，请重新输入，直接输入题目数和年级即可");
			 c[0]=input.nextInt();
			 c[1]=input.nextInt();
			continue;
			}
			if(c[0]>81&&c[1]==2) {
				System.out.println("请输入1-81之间的题目数，否则题目重复，请重新输入，直接输入题目数和年级即可");
				 c[0]=input.nextInt();
				 c[1]=input.nextInt();
				continue;
			}
			if(c[1]>3) {
				System.out.println("本程序仅支持1年级和2年级和3年级，请重新输入，直接输入题目数和年级即可");
				c[0]=input.nextInt();
				 c[1]=input.nextInt();
				 continue;
			}
			else {
			flag=1;
			if(flag==1)
				break;
		}
		}
		}
			if(flag==2) {
					for(;;) {
						if(c[0]>400&&c[1]==1) {
						System.out.println("请输入1-400之间的题目数，否则题目重复，请重新输入，直接输入年级和题目数即可");
						 c[0]=input.nextInt();
						 c[1]=input.nextInt();
						continue;
						}		
						if(c[0]<1) {
						System.out.println("输入的题目数请大于1，请重新输入，直接输入年级和题目数即可");
						 c[0]=input.nextInt();
						 c[1]=input.nextInt();
						continue;
						}
						if(c[0]>81&&c[1]==2) {
							System.out.println("请输入1-81之间的题目数，否则题目重复，请重新输入，直接输入年级和题目数即可");
							 c[0]=input.nextInt();
							 c[1]=input.nextInt();
							continue;
						}
						if(c[1]>3) {
							System.out.println("本程序仅支持1年级和2年级和3年级，请重新输入，直接输入年级和题目数即可");
							 c[0]=input.nextInt();
							 c[0]=input.nextInt();
							 continue;
						}
						else {
						flag=1;
						if(flag==1)
							break;
					}
			 }
		}
		if(c[1]==1) {
		for(i=1;i<=c[0];i++) {
			fuhao=(int)(Math.random()*2);
			if(fuhao==0) {
			 a=(int)(Math.random()*21);   
	         b=(int)(Math.random()*21);
	         int answer=a+b;
	         System.out.println("( "+i+ ") "+a+" + "+b);
	         timu[i-1]="( "+ i+" ) "+a+" + "+b;
	         daan[i-1]="( "+i+" ) "+a+" + "+b+" = "+answer;
	         byte[] ti =timu[i-1].getBytes();	// 将字符串转换为字节流为了写入txt文件
	         byte[] da =daan[i-1].getBytes();
	         byte[] h=huanhang.getBytes();
	         u.write(ti);	// 将转换好的字节流写入txt
	         u.write(h);
		 				}
	        else {
	         a=(int)(Math.random()*21);   
	         b=(int)(Math.random()*(a));
	         int answer=a-b;
	         System.out.println("( "+i+" ) "+a+" - "+b);
	         timu[i-1]="( "+i+" ) "+a+" - "+b;
	         daan[i-1]="( "+i+" ) "+a+" - "+b+" = "+answer;
	         byte[] ti =timu[i-1].getBytes();
	         byte[] da =daan[i-1].getBytes();
	         byte[] h=huanhang.getBytes();
	         u.write(ti);
	         u.write(h);
	         }	
		}
		System.out.println("\n");
		String xieru="题目与答案已经写入out.txt";
		byte[] xr=xieru.getBytes();
		 byte[] h=huanhang.getBytes();
		 u.write(h);u.write(h);
		for(i=1;i<=c[0];i++) {
			System.out.println( daan[i-1]);
	         byte[] da =daan[i-1].getBytes();
	         u.write(da);
	         u.write(h);
		}
			 u.write(xr);
			 System.out.println("题目与答案写入out.txt");
		}	
		else if(c[1]==2) {
			for(i=1;i<=c[0];i++) {
			fuhao=(int)(Math.random()*2);
			if(fuhao==0) {
			 a=(int)(Math.random()*10);   
	         b=(int)(Math.random()*10);
	         int answer=a*b;
	         System.out.println("( "+i+" ) "+a+" × "+b);
	         timu[i-1]="( "+i+" ) "+a+" × "+b;
	         daan[i-1]="( "+i+" ) "+a+" × "+b+" = "+answer;
	         byte[] ti =timu[i-1].getBytes();
	         byte[] da =daan[i-1].getBytes();
	         byte[] h=huanhang.getBytes();
	         u.write(ti);
	         u.write(h);
		 				}
	        else {
	         a=(int)(Math.random()*10);   
	         b=(int)(Math.random()*(a)+1);
	         int answer=a/b;
	         int yushu=a%b;
	         System.out.println("( "+i+" ) "+a+" ÷ "+b);
	         timu[i-1]="( "+i+" ) "+a+" ÷ "+b;
	         if(a%b==0) {
	         daan[i-1]="( "+i+" ) "+a+" ÷ "+b+" = "+answer;
	         }
	         else if(a%b!=0) {
	         daan[i-1]="( "+i+" ) "+a+" ÷ "+b+" = "+answer+" ..... "+yushu;
	         }
	         byte[] ti =timu[i-1].getBytes();
	         byte[] da =daan[i-1].getBytes();
	         byte[] h=huanhang.getBytes();
	         u.write(ti);
	         u.write(h);
	         }	
		}
			System.out.println("\n");
			String xieru="题目与答案已经写入out.txt";
			byte[] xr=xieru.getBytes();
			 byte[] h=huanhang.getBytes();
			 u.write(h);
			for(i=1;i<=c[0];i++) {
				System.out.println( daan[i-1]);
		         byte[] da =daan[i-1].getBytes();
		         u.write(da);
		         u.write(h);
			}
			u.write(xr);
			System.out.println("题目与答案已经写入out.txt");
			}	
		else if(c[1]==3) {
		 for(i=1;i<=c[0];i++) {
			 fuhao=(int)(Math.random()*8);
				if(fuhao==0) {
					a = (int)(Math.random()*100+1);
					b = (int)(Math.random()*100+1);
					d = (int)(Math.random()*100+1);
		         int answer=a*b+d;
		         System.out.println("( "+i+" )"+a+" × "+b+" + "+d);
		         timu[i-1]="( "+i+" )"+a+" × "+b+" + "+d;
		         daan[i-1]="( "+i+" )"+a+" × "+b+" + "+d+" = "+answer;
		         byte[] ti =timu[i-1].getBytes();
		         byte[] da =daan[i-1].getBytes();
		         byte[] h=huanhang.getBytes();
		         u.write(ti);
		         u.write(h);
				}
				if(fuhao==1) {
					 a = (int)(Math.random()*100+1);
					 b = (int)(Math.random()*100+1);
					 d = (int)(Math.random()*100+1);
					 e = (int)(Math.random()*100+1);
					int answer=a*b+d-e;
			         System.out.println("( "+i+" ) "+a+" × "+b+" + "+d+" - "+e);
			         timu[i-1]="( "+i+" ) "+a+" × "+b+" + "+d+" - "+e;
			         daan[i-1]="( "+i+" ) "+a+" × "+b+" + "+d+" - "+e+" = "+answer;
			         byte[] ti =timu[i-1].getBytes();
			         byte[] da =daan[i-1].getBytes();
			         byte[] h=huanhang.getBytes();
			         u.write(ti);
			         u.write(h);
				}
				if(fuhao==2) {
					 b = (int)(Math.random()*100+1);
					 a = 3*b;
					 d = (int)(Math.random()*100+1);
					 e = (int)(Math.random()*100+1);
					int answer=a/b+d-e;
			         System.out.println("( "+i+" ) "+a+" ÷ "+b+" + "+d+" - "+e);
			         timu[i-1]="( "+i+" ) "+a+" ÷ "+b+" + "+d+" - "+e;
			         daan[i-1]="( "+i+" ) "+a+" ÷ "+b+" + "+d+" - "+e+" = "+answer;
			         byte[] ti =timu[i-1].getBytes();
			         byte[] da =daan[i-1].getBytes();
			         byte[] h=huanhang.getBytes();
			         u.write(ti);
			         u.write(h);
				}
				if(fuhao==3) {
					 b = (int)(Math.random()*100+1);
					 a = 6*b;
					 d = (int)(Math.random()*100+1);
					 e = (int)(Math.random()*100+1);
					 f = (int)(Math.random()*100+1);
					int answer=a/b+d-e+f;
			         System.out.println("( "+i+" ) "+a+" ÷ "+b+" + "+d+" - "+e+" + "+f);
			         timu[i-1]="( "+i+" ) "+a+" ÷ "+b+" + "+d+" - "+e+" + "+f;
			         daan[i-1]="( "+i+" ) "+a+" ÷ "+b+" + "+d+" - "+e+" + "+f+" = "+answer;
			         byte[] ti =timu[i-1].getBytes();
			         byte[] da =daan[i-1].getBytes();
			         byte[] h=huanhang.getBytes();
			         u.write(ti);
			         u.write(h);				
				}
				if(fuhao==4) {
					 b = (int)(Math.random()*100+1);
					 a = 2*b;
					 d = (int)(Math.random()*100+1);
					 e = (int)(Math.random()*100+1);
					 f = (int)(Math.random()*100+1);
					int answer=a/b*d-e+f;
			         System.out.println("( "+i+" ) "+a+" ÷ "+b+" × "+d+" - "+e+" + "+f);
			         timu[i-1]="( "+i+" ) "+a+" ÷ "+b+" × "+d+" - "+e+" + "+f;
			         daan[i-1]="( "+i+" ) "+a+" ÷ "+b+" × "+d+" - "+e+" + "+f+" = "+answer;
			         byte[] ti =timu[i-1].getBytes();
			         byte[] da =daan[i-1].getBytes();
			         byte[] h=huanhang.getBytes();
			         u.write(ti);
			         u.write(h);				
				} 
				if(fuhao==5) {
					 b = (int)(Math.random()*100+1);
					 a = 3*b;
					 d = (int)(Math.random()*100+1);
					 e = (int)(Math.random()*100+1);
					 int l=d-e;
					 int answer=l+a/b;
			         System.out.println("( "+i+" ) "+a+" ÷ "+b+" + "+"( "+d+" - "+e+" )");
			         timu[i-1]="( "+i+" ) "+a+" ÷ "+b+" + "+"( "+d+" - "+e+" )";
			         daan[i-1]="( "+i+" ) "+a+" ÷ "+b+" + "+"( "+d+" - "+e+" )"+" = "+answer;
			         byte[] ti =timu[i-1].getBytes();
			         byte[] da =daan[i-1].getBytes();
			         byte[] h=huanhang.getBytes();
			         u.write(ti);
			         u.write(h);
				}
				if(fuhao==6) {
					 a = (int)(Math.random()*100+1);
					 b = (int)(Math.random()*100+1);
					 d = (int)(Math.random()*100+1);
					 e = (int)(Math.random()*100+1);
					 int l=+b+d+e;
					int answer=a*l;
			         System.out.println("( "+i+" ) "+a+" × "+"( "+b+" + "+d+" + "+e+" )");
			         timu[i-1]="( "+i+" ) "+a+" × "+"( "+b+" + "+d+" + "+e+" )";
			         daan[i-1]="( "+i+" ) "+a+" × "+"( "+b+" + "+d+" + "+e+" )"+" = "+answer;
			         byte[] ti =timu[i-1].getBytes();
			         byte[] da =daan[i-1].getBytes();
			         byte[] h=huanhang.getBytes();
			         u.write(ti);
			         u.write(h);
				}if(fuhao==7) {
					 a = (int)(Math.random()*100+1);
					 b = (int)(Math.random()*100+1);
					 d = (int)(Math.random()*100+1);
					 e = (int)(Math.random()*100+1);
					 int l=a+b;
					 int z=d+e;
					int answer=z*l;
			         System.out.println("( "+i+" ) "+"( "+a+" + "+b+" )"+" * "+"( "+d+" + "+e+" )");
			         timu[i-1]="( "+i+" ) "+"( "+a+" + "+b+" )"+" * "+"( "+d+" + "+e+" )";
			         daan[i-1]="( "+i+" ) "+"( "+a+" + "+b+" )"+" * "+"( "+d+" + "+e+" )"+" = "+answer;
			         byte[] ti =timu[i-1].getBytes();
			         byte[] da =daan[i-1].getBytes();
			         byte[] h=huanhang.getBytes();
			         u.write(ti);
			         u.write(h);
				}
				
	}
	System.out.println("\n");
	String xieru="题目与答案已经写入out.txt";
	byte[] xr=xieru.getBytes();
	 byte[] h=huanhang.getBytes();
	 u.write(h);
	for(i=1;i<=c[0];i++) {
		System.out.println( daan[i-1]);
         byte[] da =daan[i-1].getBytes();
         u.write(da);
         u.write(h);
	}
	u.write(xr);
	System.out.println("题目与答案已经写入out.txt");
	}	
}
}