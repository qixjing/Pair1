import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arithmetic extends Operation{                                           // 四则混合运算
	

	
	public Arithmetic(String sPart) {
		setAnswerNumber(Integer.valueOf(arithmeticOne(sPart)));
	}
		
	public static  String arithmeticOne(String sPart) {                                     // 混合运算一个题目   
		
		// (本方法的目标)转换字符串，把多个字符变成有意义的数字 
		// 并返回当前题目的答案给找 括号类(FindBracket)
				
		final Matcher findNumber = Pattern.compile("(\\d+)").matcher(sPart);               // 正则查找字符串中的数字
		final Matcher findSymbol = Pattern.compile("[\\+\\-\\*\\/]").matcher(sPart);     //  正则查找字符串中的符号
			
		LinkedList<String> numberSet = new LinkedList<String>();                   // 存数字的集合
		LinkedList<String> symbolSet = new LinkedList<String>();					 // 存符号的集合
	
		while(findNumber.find()) {													// 开始找字符串中的数字
			numberSet.add(findNumber.group());  									// 把找到的数字存在数字集合里
		}
																							
		while(findSymbol.find()) {    													// 开始找字符串中的符号
			symbolSet.add(findSymbol.group());										// 把找到的符号存在符号集合里
		}
																								
		multiplicationAndDivision(numberSet,symbolSet);							// 先乘除后加减！！！
		addAndSubtract(numberSet,symbolSet);									// 调用加减运算 
				
		 System.out.println(" 运算：" + answerNumber);
		return numberSet.get(0);                									// 得到了当前题目的最终答案
				
	}
	
	public static void multiplicationAndDivision(LinkedList<String> numberSet, LinkedList<String> symbolSet) {
		
		for (int i = 0 ; i < symbolSet.size(); i++) {
		//(目的) 遍历符号集合（本次只遍历*或/符号，因为先乘除后加减）
		// 匹配完一个符号，算完结果，把结果加入数字集合，然后从符号集合中删除此符号

			if (symbolSet.get(i).equals("*") || symbolSet.get(i).equals("/")) { 				// 当遍历出*或/时

				firstNumber = Integer.valueOf(numberSet.get(i)); 						// 找数字集合中当前位置的数
				secondNumber = Integer.valueOf(numberSet.get(i + 1)); 					// 再找到第二个数

				if (symbolSet.get(i).equals("*")) { 											// 判断当前符号集合的符号，算乘法
					answerNumber = firstNumber * secondNumber; 						// 算结果，保存于answerNumber中
																																						
				} else if (symbolSet.get(i).equals("/")) { 									// 算除法
					answerNumber = firstNumber / secondNumber;
					
				}
				
				numberSet.set(i, String.valueOf(answerNumber)); 							// 向数字集合的第i个位置，添加答案，会覆盖掉firstNumber
				numberSet.remove(i + 1); 													// 删掉数字集合中的secondNUmber
				symbolSet.remove(i); 														// 当前符号已经运算完成，从符号集合中删去此符号，减少后期遍历集合次数
				i--; 																			// 因为当前位置的符号已经删除了，集合会自动向前补齐，所以要找下一个符号，就需要把位置-1
			}
		}
	}

	public static void addAndSubtract(LinkedList<String> numberSet, LinkedList<String> symbolSet) {
		
		for (int i = 0; i < symbolSet.size(); i++) { 										// 运算加减，同理

			if (symbolSet.get(i).equals("+") || symbolSet.get(i).equals("-")) {
				
				firstNumber = Integer.valueOf(numberSet.get(i)); 					// 找数字集合的数
				secondNumber = Integer.valueOf(numberSet.get(i + 1));

				if (symbolSet.get(i).equals("+")) { 										// 算加法
					answerNumber = firstNumber + secondNumber;
																																	
				} else if (symbolSet.get(i).equals("-")) { 								// 算减法
					answerNumber = firstNumber - secondNumber;
																																											
				}
				
				numberSet.set(i, String.valueOf(answerNumber));
				numberSet.remove(i + 1); 												// 删掉后一个数
				symbolSet.remove(i);
				i--;
			}
		}
	}
	
}
