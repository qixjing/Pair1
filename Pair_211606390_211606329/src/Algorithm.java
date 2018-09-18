import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Algorithm {
	private int num1;
    private int num2;
    private int grade;
    private int number;
    private int Symbol;
    private int[] flag;
    private int fg=0;
    private String str="";
    private BufferedWriter bw;
    private LinearTable L=new LinearTable();
    private SopStacks Sop=new SopStacks();
    private SopStacks scale=new SopStacks();
    private List<String> list=new ArrayList<String>();
    private List<String> problem=new ArrayList<String>();
    private List<String> answer=new ArrayList<String>();
    private int sum;
    Random random=new Random();
    public Algorithm(String args0,String args1,String args2,String args3) {
    	if(args0.equals("-n"))
    	{
    		grade=Integer.parseInt(args3);
    		number=Integer.parseInt(args1);
    	}
    	else if(args0.equals("-grade"))
    	{
    		number=Integer.parseInt(args3);
    		grade=Integer.parseInt(args1);
    	}
    	//使输入的参数顺序可以互换
    	if(grade==3) {
    		CalculationThree();
    	}
    }
    private void CalculationThree()
    {
    	File file=new File("out.txt");
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//创建out文件
		try {
			bw=new BufferedWriter(new FileWriter("out.txt"));
		} catch (IOException e1) {
			e1.printStackTrace();//处理异常的方法
		}
    	for(int i=0;i<=number-1;i++)
    	{
    		Input();
    	}
    	for(int i=0;i<=problem.size();i++)
    	{
    		try {
				bw.write("("+(i+1)+")"+problem.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		System.out.println("("+(i+1)+")"+problem.get(i));
    	}
    	try {
			bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	for(int i=0;i<=problem.size();i++)
    	{
    		try {
				bw.write("("+(i+1)+")"+problem.get(i)+"="+answer.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		System.out.println("("+(i+1)+")"+problem.get(i)+"="+answer.get(i));
    	}
    }
    private void Input()
    {
    	getProblem();
		for(int j=0;j<=L.size();j++)
		{
			str=str+L.get(j);
			list.add(L.get(j));
		}
		problem.add(str);
		L.remove();
		Transformation();
		answer.add(count());
    }
    //三年级的计算算法
    private void getProblem()
    {
    	Symbol=random.nextInt(3)+2;
    	flag=new int[Symbol];
		for(int i=0;i<=Symbol;i++) {
			if(fg>1)
			{
			if(L.get(fg-1).equals(")")) 
			{
				flag[i]=random.nextInt(4)+1;
			}
			}
			else {
				flag[i]=random.nextInt(5);
			}
			if(flag[i]==0)
			{
				L.append("(");
				fg++;
			}
			L.append(random.nextInt(100)+1+"");
			fg++;
			if(flag[i]==0) 
			{
				flag[i]=random.nextInt(4)+1;
				fg++;
				L.append(fg+1,")");
			}
			if(flag[i]==1) 
			{
				 L.append("+");
				 fg++;
 			}
 			else if(flag[i]==2) 
 			{
 				L.append("-");
 				fg++;
 			}
 			else if(flag[i]==3)
 			{
 				L.append("*");
 				fg++;
 			}
 			else if(flag[i]==4)
 			{
 				L.append("÷");
 				fg++;
 			}
		}  
    }
    //生成一道题目（三年级）
    public static boolean isDigit(String args) 
    {
		Pattern pattern=Pattern.compile("[0-9]{1,}");
		Matcher matcher=pattern.matcher((CharSequence) args);
		return matcher.matches();
	}
  //判断字符串是否为一个数字
    private void Transformation()
    {
    	for(String s:list) {
    		if(isDigit(s)) 
    		{
    			L.append(s);
    		}
    		//根据逆波兰算法：操作数进入线性表
    		else if(s.equals("(")||s.equals(")"))
    		{
    			if(s.equals("("))
    			{
    				Sop.push(s);
    			}
    			else if(s.equals(")"))
    			{
    				while(!Sop.isEmpty()&&!Sop.pop().equals("(")) 
    				{
    					L.append(Sop.pop());	
    				}
    				if(!Sop.isEmpty())  {
    					Sop.pop();
    				}
    			}
    		}//根据逆波兰算法：分界符处理
    		else
    		{	Operator e=new Operator(s);
    			Operator e1=new Operator(Sop.peek());
    			while(!Sop.isEmpty()&&Sop.peek().equals("+")||Sop.peek().equals("-")||Sop.peek().equals("*")||Sop.peek().equals("÷")&&e.compareTo(e1)<=0) 
    			{
    				L.append(Sop.pop());
    			}
    			Sop.push(s);
    		}
    	}
    
    while(!Sop.isEmpty()) 
    {
    	L.append(Sop.pop());
    }
 }
    //把中缀变为后缀
    private String count()
    {
    	for(int i=0;i<=L.size();i++) {
    		if(isDigit(L.get(i))) {
    			scale.push(L.get(i));
    		}
    		else 
    		{
    			num1=Integer.parseInt(scale.pop());
    			num2=Integer.parseInt(scale.pop());
    			if(L.get(i).equals("-"))
    			{
    				if(num2<num1)
    				{
    					Input();
    				}
    				sum=num2-num1;
    			}
    			else if(L.get(i).equals("÷"))
    			{
    				if(num2%num1!=0) {
    					Input();
    				}
    				sum=num2/num1;
    			}
    			else if(L.get(i).equals("+")) 
    			{
    				sum=num2+num1;
    			}
    			else if(L.get(i).equals("*"))
    			{
    				sum=num2*num1;
    			}
    			scale.push(sum+"");
    		}
    		
    	}
    	return scale.pop();
    }
    //计算后缀表达式
 
}
