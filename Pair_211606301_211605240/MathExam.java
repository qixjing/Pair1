
public class MathExam {
	private static void operation(int n) {
		// TODO 自动生成的方法存根
		int a=0,b=0,c=1,d=1;
		int g=0;
		int[] str= {a,b,c,d}; //abcd代表着运算等级， 0为+-，1为*/
		int[] answer = new int[n]; //存答案的数组
		String[] word = new String[n]; //存题目的数组
		for(int i=0;i<n;i++) {
			int f = 2+(int) (Math.random()*3); //随机抽取运算符的个数
			int num = 1+(int) (Math.random()*1001);
			int num1 = 1+(int) (Math.random()*1001);
			word[i]="";
			answer[i]=0;
			
			for(int j=f;j>=2;j--) {
				g = (int)(Math.random()*(str.length)); //随机选取str数组长度
				int num2 =1+(int) (Math.random()*1001);
				if(g<=2) {
					if(g==1) {
						if(j==f) {
							word[i]=num+"+"+num1;
							answer[i] =num+num1;
						}
					word[i] = word[i]+"+"+num2;
					answer[i] = answer[i]+num2;
					}
					else {
						if(j==f) {
							word[i]=num+"-"+num1;
							answer[i] =Math.abs(num-num1);
						}
					word[i] = word[i]+"-"+num2;
					answer[i] = answer[i]-num2;
					}
				}
				else {
					if(g==3) {
						if(j==f) {
							word[i]=num+"x"+num1;
							answer[i] =num*num1;
						}
						word[i] = "("+word[i]+")"+"x"+num2;
						answer[i] = answer[i]*num2;
						j--;
					}
					else {
						if(j==f) {
							word[i]=num+"÷"+num1;
							answer[i] =num/num1;
						}
						word[i] = "("+word[i]+")"+"÷"+num2;
						answer[i] = answer[i]/num2;
						j--;
					}
				}
				
			}
			
		}
		

		for(int i=0;i<n;i++) {
		System.out.println(word[i]);
		}
		System.out.println();
		System.out.println("我是答案3："+answer[2]);
		System.out.println();
		for(int i=0;i<n;i++) {
			System.out.println(answer[i]);
			}
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int n = 10;
		operation(n);
		
	}

}
