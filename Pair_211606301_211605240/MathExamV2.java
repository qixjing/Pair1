
public class MathExamV2 {
	public static void math(int n,int grade) {
		System.out.println("n:"+n);
		System.out.println("grade:"+grade);
	}
	public static int[] input(String[] args,int[] str) {
		
		String regex="\\d*";
		if(args.length!=4) {//解决输入参数为多个
			System.out.println("输入的参数应为4个");
			str[0]=0;
			return str;
		}
		if((!args[1].matches(regex))||(!args[3].matches(regex))) {//解决如果输入不为数字
			System.out.println("输入的年级数与题数应为数字");
			str[0]=0;
			return str;
			}
		if(!((args[0].equals("-n") && args[2].equals("-grade"))||(args[0].equals("-grade") && args[2].equals("-n")))) {
			System.out.println("查找不到-n或者-grade");
			str[0]=0;
			return str;
		}
		String s0=String.valueOf(args[1]);
		String s1=String.valueOf(args[3]);
		int grade=Integer.parseInt(s0);
		int n=Integer.parseInt(s1);
		
		if(args[0].equals("-n") && args[2].equals("-grade")) {
		n=Integer.parseInt(s0);
		grade=Integer.parseInt(s1);
		}
		
		
		if( grade>3 || grade<=0 ) {
			System.out.println("输入年级应在一到三年级");
			str[0]=0;
			return str;
		}
		if(n<=0||n>1000)
		{
			System.out.println("输入题目数量应该在1~1000");
			str[0]=0;
			return str;
		}
		str[0]=1;
		str[1]=n;
		str[2]=grade;
		return str;
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		int[] str= new int[3];
		input(args,str);
		if(str[0]==0){
			return ;
		}
		int n=str[1];
		int grade=str[2];
		math(n,grade);
	}

}
