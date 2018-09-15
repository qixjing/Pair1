import java.util.LinkedList;

public class FindBracket extends Operation{                            // 找呀找呀找括号！！！

	static int sRight = 0; 											// 记录右括号的位置
	static int sLeft = 0; 											// 记录左括号的位置
	static String sPart = ""; 										// 一部分题目，即左右括号之间的题目
	static LinkedList<String> sInitialProblemSet;   					// 初始题目集合（用来运算括号，以后方便删除内容）
	static String sProblem; 												// 用来保存最初的题目的字符串

	

	public  void findOne(String sProblem) {                              // 找到一个小括号！！！
			
		LinkedList<String> sInitialProblemSet = new LinkedList<String>(); 		// 初始题目（用来运算括号以后方便删除内容）
				
		for(int i = 0; i < sProblem.length(); i++) {                    				// 把接受到的题目转存到初始题目的集合中
			sInitialProblemSet.add(String.valueOf(sProblem.charAt(i)));
		}
		
		for(int i = 0; i < sInitialProblemSet.size(); i++) { 						// 遍历集合，找到一个右括号
			
			
			if( sInitialProblemSet.get(i).equals(")")) {    					// 当前位置是右括号时
					sRight = i;   												// 记录右括号的位置
				
				for(int j = sRight-1; j >= 0; j--) {         						// 从当前右括号往前找第一个左括号
					
					if(sInitialProblemSet.get(j).equals("(")) {
						sInitialProblemSet.set(j, "!");						// 改变标志左括号
						sLeft = j;             									// 记录左括号位置
						break;
					}
				}
				
				for( int z = sLeft + 1; z < sRight; z++)							// 循环遍历，找出左右括号之间的数
				{   						
					sPart = sPart + sInitialProblemSet.get(z);					// 把找出来的数拼接起来，作为括号内题目的一部分
					
				}
				
				@SuppressWarnings("unused")
				Arithmetic ar = new Arithmetic(sPart);						// 把sPart作为参数，传入四则运算类
																			
				sInitialProblemSet.set( sLeft, String.valueOf(getAnswerNumber()) );
																				
				for(int m = 1; m <= sRight-sLeft ; m++){
																
					sInitialProblemSet.remove(sLeft + 1);
					
				}
																				
				i = i - ( sRight - sLeft ); 								// 删除了sRight-sLeft个元素，所以i的位置也有重新移动
						
										// 删除后修正i的位置
				
			}//当前位置是右括号时					
			
			sPart="";															// 因为是拼接字符串，所以每找到一个括号算完后，就有要把字符串给清空
		}// 找到右括号
			
		for(int i = 0; i < sInitialProblemSet.size(); i++) {			// 把最后无括号的式子传入四则运算符号
			sPart = sPart + sInitialProblemSet.get(i);
		}
		@SuppressWarnings("unused")
		Arithmetic arS = new Arithmetic(sPart);
																	
	}//findOne
	
	
}
