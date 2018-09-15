import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arithmetic {        //四则混合运算

	
	public static void test() {   
		//转换字符串，把多个字符变成有意义的数字
		String str="1*9-9+695/865";
		//56/8-6*8+6
		//35-69+10*5/8
		//8+9*8*8/6
		//1-2+3+4
		Matcher m=Pattern.compile("(\\d+)").matcher(str); //找数字
		Matcher m2=Pattern.compile("[\\+\\-\\*\\/]").matcher(str); // 找符号
		
		int firstNumber=0;
		int secondNumber=0;
		int answerNumber=0;
		
		LinkedList<String> li=new LinkedList<String>(); // 存数字
		LinkedList<String> li2=new LinkedList<String>();//存符号


		int s;
		
		while(m.find()) {			
			li.add(m.group());  //存数字或者存符号在集合里
		}
		System.out.println(li);
	
		while(m2.find()) {    //存符号
			li2.add(m2.group());				
		}
		System.out.println(li2);
		
		for(int i=0;i<li2.size();i++) {
			
			if(li2.get(i).equals("*") || li2.get(i).equals("/")) {
				
				firstNumber=Integer.valueOf(li.get(i));               //找数字集合的数
				secondNumber=Integer.valueOf(li.get(i+1)); 
				
				if(li2.get(i).equals("*")) {           //算乘法
					answerNumber=firstNumber*secondNumber;
					
				}
				else if(li2.get(i).equals("/")) {      //算除法
					answerNumber=firstNumber/secondNumber;
					
				}
				li.set(i, String.valueOf(answerNumber));
				li.remove(i+1);//删掉后一个数
				li2.remove(i);
				i--;
			}
		}
		
		for(int i=0;i<li2.size();i++) {
			
			if(li2.get(i).equals("+") || li2.get(i).equals("-")) {
				
				firstNumber=Integer.valueOf(li.get(i));               //找数字集合的数
				secondNumber=Integer.valueOf(li.get(i+1)); 
				
				if(li2.get(i).equals("+")) {           //算加法
					answerNumber=firstNumber+secondNumber;
					
				}
				else if(li2.get(i).equals("-")) {      //算减法
					answerNumber=firstNumber-secondNumber;
					
				}
				li.set(i, String.valueOf(answerNumber));
				li.remove(i+1);//删掉后一个数
				li2.remove(i);
				i--;
			}
		}
		System.out.println(li);
		System.out.println(li2);
		
		
	}
}
