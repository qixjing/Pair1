package ruanjian.com;

import java.util.Random;

public class MathExam{
		
public static void main(String[] args) {
	
	//int n = Integer.parseInt(args[0]);
	int n=10;
	int grade = 3;
	problems( n , grade );
	
	
}
	private static void problems(int n,int grade) {
		String[] fuhao = {" + "," - "," * "," / "};
		String fengge="\r\n";
		Random rand = new Random();
		String a="";
		int S_num1 = (rand.nextInt(1000)+1);
		int i=0;
		String S_wenti =S_num1 + "";
		int op = (rand.nextInt(3));
		if(grade == 3) {

			int OPNum = (rand.nextInt(3)+2);
			jixiu : for(int j=0;j<OPNum;j++) {
				int S_num2 = (rand.nextInt(1000)+1);
				
				if(op == 0) {
					if(S_num1 + S_num2 > 1000) {
						j--;
						continue jixiu;
					}	
				}
				else if(op == 1) {
					if(S_num1-S_num2 < 0) {
						j--;
						continue jixiu;
					}
				}
				else if( op == 2 ) {
					if(S_num1 == 0 || S_num2 ==0) {
						j--;
						continue jixiu;
					}
				}
				else if(op == 3) {
					if( S_num1 % S_num2 !=0 || S_num2 ==0 ) {
						j--;
						continue jixiu;
					}
				}	
				a=S_wenti + S_num1 + fuhao[op] + S_num2 ;

				S_wenti += fuhao[op] + S_num2;
			}

			
			System.out.println("(" +i +") " + S_wenti + "\n");
		}
			
	}
}
