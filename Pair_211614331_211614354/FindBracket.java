import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindBracket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ts();
	}

	public static void ts() {
		//
		String s1="(A+B)-(C+D)*(B+A)";  
		// ((A+B)/(C+D-B))*A
		//((A+B)*(C+D)-A)/B
		//(A+B)-(C+D)*(B+A)
		
		ArrayList<String> a1=new ArrayList<>();// 初始题目（用来运算括号以后方便删除内容）
		
		LinkedList<String> l1=new LinkedList<>();
		for(int i=0;i<s1.length();i++) {
			l1.add(String.valueOf(s1.charAt(i)));
		}
		
		/*
		 LinkedList<Integer> left=new LinkedList<>();
		ArrayDeque<Integer> right=new ArrayDeque<>();
		 */
		
		
		Matcher m=Pattern.compile("\\(").matcher(s1);
		Matcher m2=Pattern.compile("\\)").matcher(s1);
	
		int right=0; //右括号的位置
		int left=0; //左括号的位置
		
		for(int i=0;i<l1.size();i++) {
			if( l1.get(i).equals(")")) {
				right=i;
				for(int j=right-1;j>=0;j--) {
					if(l1.get(j).equals("(")) {
						l1.set(j, "!");
						left=j;
						break;
					}
				}
				
				for(int z=left;z<=right;z++) {
					System.out.print(l1.get(z));
					//l1.remove(z);						
				}
				System.out.println();
					
			}
		}
		
		
		
		/*
		while(m2.find()) {     //先找右括号，定位后，从当前位置往左扫描，找到第一个左括号
			
			                                                   //right.addLast(m2.start());
			right=m2.start();          //括号的位置
			for(int i=right-1;i>=0;i--) {
				if( (String.valueOf(s1.charAt(i))).equals("(") ) {
					left=i;      // 最后一个小于右括号位置的 左括号的位置
					break;
				}
			}
		
				System.out.println(s1.substring(left, right+1));
						
		}                                  //右括号
		*/
		
		
	}
}
