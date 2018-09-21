import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SignPrioritySort {
	/* 判断参数1和参数2的优先级;当参数1的优先级高于参数2时，返回true，将参数1压入栈顶；
	 * 当参数1的优先级低于或等于时返回false
	 */
	public static boolean getPriority(String firstSign,String secondSign){
		if(firstSign.equals("+") || firstSign.equals("-")){
			if(secondSign.equals("(")){
				return true;
			}else{
				return false;
			}
		}
		if(firstSign.equals("×") || firstSign.equals("÷")){
			if(secondSign.equals("+") || secondSign.equals("-") || secondSign.equals("(")){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	// 将传入的运算符字符串进行排序，即令运算符按高→低排列
	public static String[] prioritySort(String[] signString){
		String currentElement="";
		String[] newString=null;
		List<String> stringList=new ArrayList<String>();
		// 定义两个空栈
		Stack<String> strStack1=new Stack<String>();
		Stack<String> strStack2=new Stack<String>();
		Stack<String> strStack3=new Stack<String>();
		for(int i=0;i<signString.length;i++){
			currentElement=signString[i];
			// 遍历字符串，当栈为空时，直接将字符压入栈1
			if(strStack1.empty() || currentElement.equals("(")){
				strStack1.push(currentElement);
			}else{
				if(currentElement.equals(")")){
					strStack3.push(currentElement);
					while(!strStack1.peek().equals("(")){
						strStack3.push(strStack1.pop());	
					}
					// 将"("弹出并压入栈2
					strStack3.push(strStack1.pop());
				}else {
					/* 判断字符优先级，若当前字符的优先级高于栈1
					 * 栈顶字符的优先级，则将当前字符压入栈1栈顶；
					 * 反之，则将栈1栈顶元素出栈，压入栈2栈顶，
					 * 并循环判断当前字符与栈顶元素的优先级；
					 */
					if(getPriority(currentElement,strStack1.peek())){
						strStack1.push(currentElement);
					}else{
						// 注意循环时应该先判断栈是否空，否则会引起越界问题
						strStack2.push(strStack1.pop());
						while(!strStack1.empty() && !strStack1.peek().equals("(") && !getPriority(currentElement,strStack1.peek())){
							strStack2.push(strStack1.pop());
						}
						strStack1.push(currentElement);
						while(!strStack2.empty()){
							strStack1.push(strStack2.pop());
						}
					}
				}
			}
		}
		while(!strStack3.empty()){
			strStack1.push(strStack3.pop());
		}
		while(!strStack1.empty()){
			if(strStack1.peek().equals("(")){
				strStack1.pop();
				stringList.add(")");
			}else if(strStack1.peek().equals(")")){
				strStack1.pop();
				stringList.add("(");
			}else{
				stringList.add(strStack1.pop());
			}
		}
		newString=new String[stringList.size()];
		for(int i=0;i<stringList.size();i++){
			newString[i]=stringList.get(i);
		}
		return newString;
		}
	
	// 将传入的运算符字符串进行排序，即令运算符按高→低排列，不返回括号
	public static String[] prioritySortWithoutBrackets(String[] signString){
		String currentElement="";
		String[] newString=null;
		List<String> stringList=new ArrayList<String>();
		// 定义两个空栈
		Stack<String> strStack1=new Stack<String>();
		Stack<String> strStack2=new Stack<String>();
		Stack<String> strStack3=new Stack<String>();
		for(int i=0;i<signString.length;i++){
			currentElement=signString[i];
			// 遍历字符串，当栈为空时，直接将字符压入栈1
			if(strStack1.empty() || currentElement.equals("(")){
				strStack1.push(currentElement);
			}else{
				if(currentElement.equals(")")){
					while(!strStack1.peek().equals("(")){
						strStack3.push(strStack1.pop());	
					}
					// 将"("弹出
					strStack1.pop();
				}else {
					/* 判断字符优先级，若当前字符的优先级高于栈1
					 * 栈顶字符的优先级，则将当前字符压入栈1栈顶；
					 * 反之，则将栈1栈顶元素出栈，压入栈2栈顶，
					 * 并循环判断当前字符与栈顶元素的优先级；
					 */
					if(getPriority(currentElement,strStack1.peek())){
						strStack1.push(currentElement);
					}else{
						// 注意循环时应该先判断栈是否空，否则会引起越界问题
						strStack2.push(strStack1.pop());
						while(!strStack1.empty() && !strStack1.peek().equals("(") && !getPriority(currentElement,strStack1.peek())){
							strStack2.push(strStack1.pop());
						}
						strStack1.push(currentElement);
						while(!strStack2.empty()){
							strStack1.push(strStack2.pop());
						}
					}
				}
			}
		}
		while(!strStack3.empty()){
			strStack1.push(strStack3.pop());
		}
		while(!strStack1.empty()){
			if(strStack1.peek().equals("(")){
				strStack1.pop();
				stringList.add(")");
			}else if(strStack1.peek().equals(")")){
				strStack1.pop();
				stringList.add("(");
			}else{
				stringList.add(strStack1.pop());
			}
		}
		newString=new String[stringList.size()];
		for(int i=0;i<stringList.size();i++){
			newString[i]=stringList.get(i);
		}
		return newString;
		}
	
	public static void main(String[] args) {
		String signString="+-(+-+)";
		String[] signArray=new String[signString.length()];
		for(int i=0;i<signString.length();i++){
			signArray[i]=String.valueOf(signString.charAt(i));
		}
		for(int i=0;i<prioritySortWithoutBrackets(signArray).length;i++){
		    System.out.println(prioritySortWithoutBrackets(signArray)[i]);
	    }
		System.out.println("----------------");
		for(int i=0;i<prioritySort(signArray).length;i++){
		    System.out.println(prioritySort(signArray)[i]);
	    }
	}
}
