
public class MathExam {
	//加法
	private static void add(int i,StringBuffer word,int[] answer) {
		// TODO 自动生成的方法存根
		int num1 = (int) (Math.random()*999);
		System.out.println("我是加法1："+num1);
		word.insert(word.length()," + "+num1);
		answer[i] = answer[i]+num1;
	}
	//减法
	private static void sub(int i,StringBuffer word, int[] answer) {
		// TODO 自动生成的方法存根
		int num1 = (int) (Math.random()*(answer[i]-1));
		System.out.println("我是减法1："+num1);
		word.insert(word.length(),"-"+num1);
		answer[i] = answer[i]-num1;
	}
	//乘法
	private static void mul(int i,StringBuffer word, int[] answer) {
		// TODO 自动生成的方法存根
		int num1 = (int) (Math.random()*98);
		System.out.println("我是乘法1："+num1);
			word.insert(word.length(), "x"+num1);
			answer[i]= answer[i]*num1;
	}
	//除法
	private static void div(int i,int j,int f, StringBuffer word, int[] answer) {
		// TODO 自动生成的方法存
		int test = (int)(Math.random());
		if((test==1)||(answer[i]==0)) {
			int num1 = 1+(int)(Math.random()*98);  //除数
			System.out.println("我是除法1："+num1);
			int rem=answer[i]%num1;
			if(rem>0) {
				word.insert(word.length(), "+"+rem);
				word.insert(word.length(),"÷"+num1);
				answer[i]=(answer[i]+rem)/num1;
			}else {
			word.insert(word.length(),"÷"+num1);
			answer[i]=answer[i]/num1;}
		}
		else if(test==0) {
			int num1 = (int)(Math.random()*(1000/answer[i]));  //被除数
			System.out.println("我是除法2："+num1*answer[i]);
			word.insert(0,(num1*answer[i])+"÷");
			answer[i] = num1;
		}
		
	}
	private static void operation(int n) {
		// TODO 自动生成的方法存根
		//str数组储存了运算符的等级和符号；answer数组包含式子答案；word数组储存题目；f是随机运算符的个数;num是随机的第一个数;g是选择第g个运算符;i是第i道题；j是当前符号数量
		int level=1;//设置等级
		int[] str= {0,0,1,1}; // 0为+-，1为*/
		int[] answer = new int[n]; //存答案的数组
		//存题目的数组
		for(int i=0;i<n;i++) {//出n道题目的循环
			int f = 2+(int) (Math.random()*3); //f:随机抽取运算符的个数
			int g = (int)(Math.random()*(str.length-1));//第一个运算符
			if(g==2) {
				answer[i]= (int) (Math.random()*98);
			}else {
				answer[i] = (int) (Math.random()*998);
			}//第一个随机数
			StringBuffer word= new StringBuffer(answer[i]+"");
			for(int j=0;j<f;j++) {
				if(level<str[g] && j>f-2)break;//在需要加括号的情况下运算符不够用
				if(level<str[g]) {
					//加括号
					word.insert(0, "(");
					word.insert(word.length(), ")");
					j++;
				}
				level=str[g];
				 //随机选择符号
				if(g==0) {	
					add(i,word, answer);
				}
				else if(g==1) {
				
					sub(i,word, answer);
					}
				else if(g==2) {
					
					div(i,j,f,word, answer);
					
				}
				else {
					mul(i,word, answer);
				}
				g = (int)(Math.random()*(str.length));
				if(answer[i]>=100) {
				g = (int)(Math.random()*(str.length-1));
				}
					
				
				
			}
			System.out.println(word);
			System.out.println(answer[i]);
		}
	}
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int n = 1;
		operation(n);
		
	}

}
