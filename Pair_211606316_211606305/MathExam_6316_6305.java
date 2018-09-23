package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

public class MathExam_6316_6305 {
	static Scanner s = new Scanner(System.in);
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("��������Ŀ����(С��100����0����)");
		double n = 0;
		while(true) {
			n = s.nextDouble();
			if(n<=100 && n > 0 && (int)n == n) {
				break;
			}else {
				System.out.println("������Ĳ���С��100����0��������,����������");
			}
		}
		System.out.println("������1������һ�꼶����2��������꼶����3���������꼶��");
		while(true) {
			double G = s.nextDouble();
			if(G==1) {
				Grade_one(n);
				break;
			}else if(G == 2) {
				Grade_two(n);
				break;
			}else if(G == 3) {
				Grade_three(n);
				break;
			}else {
				System.out.println("������Ĳ���1��2��3,����������");
			}
		}
	}
	
	public static void Grade_one(double n) {
		String[][] str = new String[100][4];
		for(int i = 0; i < n; i++) {
			int a = (int) (Math.random()*20);
			int b = (int) (Math.random()*20);
			int ans = 0;
			int mark = (int) (Math.random()*2+1);
			String strMark = null;
			if(mark == 1) {
				ans = a+b;
				strMark = "+";
			}else {
				ans = a-b;
				strMark = "-";
			}
			if(ans >= 20 || ans < 0) {
				i--;
				continue;
			}
				
			str[i][0] = String.valueOf(a);
			str[i][1] = strMark;
			str[i][2] = String.valueOf(b);
			str[i][3] = String.valueOf(ans);
		}
		Calculate(n, str);
	}
	
	public static void Grade_two(double n){
		String[][] str = new String[100][4];
		for(int i = 0; i < n; i++) {
			int mark = (int) (Math.random()*2+1);
			int a = (int) (Math.random()*9+1);
			int b = (int) (Math.random()*9+1);
			int ans = a*b;
			String strMark = null;
			if(mark == 1) {
				strMark = "x";
				str[i][0] = String.valueOf(a);
				str[i][1] = strMark;
				str[i][2] = String.valueOf(b);
				str[i][3] = String.valueOf(ans);
			}else {
				strMark = "��";
				str[i][0] = String.valueOf(ans);
				str[i][1] = strMark;
				str[i][2] = String.valueOf(a);
				str[i][3] = String.valueOf(b);
			}
		}
		Calculate(n, str);
	}
	
	public static void Grade_three(double n) {
		String[][] str = new String[100][15];
		String[] markStr =  {"+","-","x","��"};
		
		for(int a = 0; a < n; a++) {
			String[] markStrNumber = new String[5];
			String[] number = new String[6];
			
			String[] markStrNumberUsing = new String[5];    //����ÿһ�������ݴ���
			String[] numberUsing = new String[6];
			
			int markAmount = (int) (Math.random()*3+2);	 //��������2 - 4
			
			
			for(int i = 0; i < markAmount; i++) {    //������ŷ������������
				int markRandom = (int) (Math.random()*3+2);	
				markStrNumber[i] = markStr[markRandom-1];
				
				markStrNumberUsing[i] = markStr[markRandom-1];
			}
			
			for(int i = 0; i < markAmount + 1; i++) {   //������ֲ��Ž�������
				number[i] = String.valueOf((int) (Math.random()*20+1));	
				
				numberUsing[i] = number[i];
			}
			
			for(int i = 0; i < 4; i++) {
				System.out.println(markStrNumberUsing[i]);
			}
			System.out.println("-------------------------------");
			for(int i = 0; i < 5; i++) {
				System.out.println(numberUsing[i]);
			}
			System.out.println("-------------------------------");
			int bracketAmount = 0;
			if(markAmount == 2 || markAmount == 3){
				bracketAmount = (int) (Math.random()*2);
			}else {
				bracketAmount = 0;
			}
			
			int bracketNow = bracketAmount;
			System.out.println(bracketNow);
			System.out.println("-------------------------------");
			int markNumInBracket = -1;
			int markNo = -1;
			double finalAns = 0;
			
			int isErrorAns = 0;
			
			while(true) {
				if(bracketNow == 1) {
					bracketNow--;
					if(markAmount == 2) {
						markNumInBracket = 1;
						markNo = (int) (Math.random()*2+1);
					}else {
						markNumInBracket = (int) (Math.random()*2+1);
						markNo = (int) (Math.random()*(3-markNumInBracket+1)+1);
					}
					
					System.out.println(markNumInBracket);
					System.out.println(markNo);
					System.out.println("-------------------------------");
					String[] OpeMarkStrNumber = new String[markNumInBracket+1];
					String[] OpeNumber = new String[markNumInBracket+2];
					
					for(int j = 0; j < markNumInBracket+1; j++) {
						OpeMarkStrNumber[j] = markStrNumberUsing[j+markNo-1];
						markStrNumberUsing[j+markNo-1] = null;
						System.out.print(OpeMarkStrNumber[j]);
					}
					
					for(int j = 0; j < markNumInBracket+2; j++) {
						OpeNumber[j] = numberUsing[j+markNo-1];
						numberUsing[j+markNo-1] = null;
						System.out.println(OpeNumber[j]);
					}
					
					double ansBracket = Operation(OpeMarkStrNumber, OpeNumber);
					if(ansBracket == 0.1 || ansBracket > 1000) {
						isErrorAns = 1;
						break;
					}
					System.out.println(ansBracket);
					System.out.println("-------------------------------");
					numberUsing[markNo-1] = String.valueOf(ansBracket);
					markStrNumberUsing = RemoveNull(markStrNumberUsing);
					
					numberUsing = RemoveNull(numberUsing);	
					
				}else if(bracketNow == 0){
					double ans = Operation(markStrNumberUsing, numberUsing);
					if(ans == 0.1 || ans > 1000) {
						isErrorAns = 1;
						break;
					}
					finalAns = ans;
					System.out.println(ans);
					break;
				}
			}
			
			if(isErrorAns == 1) {
				a--;
				continue;
			}
			
			//�����ά������
			int whichUse = 0;
			int mar = 0;
			int num = 0;
			
			String[] strNow = new String[15];
			for(int i = 0; i < 20; i++){
				if(i%2 == 0 && number[num] != null) {
					strNow[i] = number[num];
					num++;
				}else if(i%2 != 0 && markStrNumber[mar] != null) {
					strNow[i] = markStrNumber[mar];
					mar++;
				}
			}
			num = 0;
			for(int i = 0; i < 15; i++){
				if(i == (markNo-1)*2) {
					str[a][i] = "(";
				}else if(i == ((markNo-1)*2 + 2*markNumInBracket + 2)){
					str[a][i] = ")";
				}else if(strNow[num] == null && i != ((markNo-1)*2 + 2*markNumInBracket + 2)) {
					str[a][i] = String.valueOf(finalAns);
					break;
				}else {
					str[a][i] = strNow[num];
					num++;
				}
			}
		}

		
		Calculate_Three(n, str);
	}

	public static double Operation(String[] OpeMarkStrNumber, String[] OpeNumber) {
		while(true) {
			int PMAmount = 0;
			
			for(int i = 0; i < OpeMarkStrNumber.length-1; i++) {
				
				if(OpeMarkStrNumber[i] == "x") {
					
					double ans = Double.parseDouble(OpeNumber[i]) * Double.parseDouble(OpeNumber[i+1]);
					
					OpeNumber[i] = String.valueOf(ans);
					OpeNumber[i+1] = null;
					OpeMarkStrNumber[i] = null;
					OpeNumber = RemoveNull(OpeNumber);
					OpeMarkStrNumber = RemoveNull(OpeMarkStrNumber);
					
					PMAmount++;
					break;
					
				}else if(OpeMarkStrNumber[i] == "��") {
					
					double ans = Double.parseDouble(OpeNumber[i]) / Double.parseDouble(OpeNumber[i+1]);
					
					if(ans != (int)ans) {
						return 0.1;
					}
					
					
					OpeNumber[i] = String.valueOf(ans);
					OpeNumber[i+1] = null;
					OpeMarkStrNumber[i] = null;
					OpeNumber = RemoveNull(OpeNumber);
					OpeMarkStrNumber = RemoveNull(OpeMarkStrNumber);
					PMAmount++;
					break;
				}
			}
			
			if(PMAmount == 0) {
				break;
			}
		}
		return PlusMinus(OpeMarkStrNumber, OpeNumber);
	}
	
	public static double PlusMinus(String[] OpeMarkStrNumber, String[] OpeNumber) {

		int PMmarkNo = 0;
		double ans = Double.parseDouble(OpeNumber[0]) ;
		while(true) {
			if(OpeMarkStrNumber[PMmarkNo] == "+") {
				ans = ans + Double.parseDouble(OpeNumber[PMmarkNo+1]);
				PMmarkNo++;
			}else if(OpeMarkStrNumber[PMmarkNo] == "-"){
				ans = ans - Double.parseDouble(OpeNumber[PMmarkNo+1]);
				if(ans < 0) {
					return 0.1;
				}
				PMmarkNo++;
			}else if(OpeMarkStrNumber[PMmarkNo] == null){
				return ans;
			}
		}
	}
	
	
	public static String[] RemoveNull(String[] target) {
		for(int i = 0; i < target.length; i++) {
			if(target[i] == null) {
				for(int j = i; j < target.length; j++) {
					if(j != target.length-1) {
						target[j] = target[j+1];
					}else {
						target[j] = null;
					}
				}
			}
		}
		return target;
	}


	
	public static void Calculate(double n, String[][] str) {
		try {
			File f=new File("F:\\java\\out.txt");
	        if(!f.exists()){
	            f.getParentFile().mkdirs();          
	        }
	        f.createNewFile();
			FileOutputStream fos = new FileOutputStream("F:\\java\\out.txt");
			for(int i = 0; i < n; i++) {
				fos.write("(".getBytes());
				fos.write(String.valueOf(i+1).getBytes());
				fos.write(")".getBytes());
				for(int j = 0; j < 3; j++) {
					fos.write(str[i][j].getBytes());
					fos.write("\b".getBytes());
				}
				fos.write("=".getBytes());
				fos.write("\r\n".getBytes());
			}
			fos.write("------------------��׼��------------------\r\n".getBytes());
			for(int i = 0; i < n; i++) {
				fos.write("(".getBytes());
				fos.write(String.valueOf(i+1).getBytes());
				fos.write(")".getBytes());
				for(int j = 0; j < 3; j++) {
					fos.write(str[i][j].getBytes());
					fos.write("\b".getBytes());
				}
				fos.write("=\b".getBytes());
				fos.write(str[i][3].getBytes());
				fos.write("\r\n".getBytes());
			}
			fos.close();
		}catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void Calculate_Three(double n, String[][] str) {
		try {
			File f=new File("F:\\java\\out.txt");
	        if(!f.exists()){
	            f.getParentFile().mkdirs();          
	        }
	        f.createNewFile();
			FileOutputStream fos = new FileOutputStream("F:\\java\\out.txt");
			for(int i = 0; i < n; i++) {
				fos.write("(".getBytes());
				fos.write(String.valueOf(i+1).getBytes());
				fos.write(")".getBytes());
				int end = 0;
				
				for(int x = 0; x < 15; x++) {
					if(str[i][x] == null)
					{
						end = x;
						break;
					}
				}
				for(int j = 0; j < end-1; j++) {
					fos.write(str[i][j].getBytes());
					fos.write("\b".getBytes());
				}
				fos.write("=".getBytes());
				fos.write("\r\n".getBytes());
			}
			fos.write("------------------��׼��------------------\r\n".getBytes());
			for(int i = 0; i < n; i++) {
				fos.write("(".getBytes());
				fos.write(String.valueOf(i+1).getBytes());
				fos.write(")".getBytes());
				
				int end = 0;
				for(int x = 0; x < 15; x++) {
					if(str[i][x] == null)
					{
						end = x;
						break;
					}
				}
				
				for(int j = 0; j < end-1; j++) {
					fos.write(str[i][j].getBytes());
					fos.write("\b".getBytes());
				}
				fos.write("=\b".getBytes());
				fos.write(str[i][end-1].getBytes());
				fos.write("\r\n".getBytes());
			}
			fos.close();
		}catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}