import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SignPrioritySort {
	/* �жϲ���1�Ͳ���2�����ȼ�;������1�����ȼ����ڲ���2ʱ������true��������1ѹ��ջ����
	 * ������1�����ȼ����ڻ����ʱ����false
	 */
	public static boolean getPriority(String firstSign,String secondSign){
		if(firstSign.equals("+") || firstSign.equals("-")){
			if(secondSign.equals("(")){
				return true;
			}else{
				return false;
			}
		}
		if(firstSign.equals("��") || firstSign.equals("��")){
			if(secondSign.equals("+") || secondSign.equals("-") || secondSign.equals("(")){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	// �������������ַ����������򣬼�����������ߡ�������
	public static String[] prioritySort(String[] signString){
		String currentElement="";
		String[] newString=null;
		List<String> stringList=new ArrayList<String>();
		// ����������ջ
		Stack<String> strStack1=new Stack<String>();
		Stack<String> strStack2=new Stack<String>();
		Stack<String> strStack3=new Stack<String>();
		for(int i=0;i<signString.length;i++){
			currentElement=signString[i];
			// �����ַ�������ջΪ��ʱ��ֱ�ӽ��ַ�ѹ��ջ1
			if(strStack1.empty() || currentElement.equals("(")){
				strStack1.push(currentElement);
			}else{
				if(currentElement.equals(")")){
					strStack3.push(currentElement);
					while(!strStack1.peek().equals("(")){
						strStack3.push(strStack1.pop());	
					}
					// ��"("������ѹ��ջ2
					strStack3.push(strStack1.pop());
				}else {
					/* �ж��ַ����ȼ�������ǰ�ַ������ȼ�����ջ1
					 * ջ���ַ������ȼ����򽫵�ǰ�ַ�ѹ��ջ1ջ����
					 * ��֮����ջ1ջ��Ԫ�س�ջ��ѹ��ջ2ջ����
					 * ��ѭ���жϵ�ǰ�ַ���ջ��Ԫ�ص����ȼ���
					 */
					if(getPriority(currentElement,strStack1.peek())){
						strStack1.push(currentElement);
					}else{
						// ע��ѭ��ʱӦ�����ж�ջ�Ƿ�գ����������Խ������
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
	
	// �������������ַ����������򣬼�����������ߡ������У�����������
	public static String[] prioritySortWithoutBrackets(String[] signString){
		String currentElement="";
		String[] newString=null;
		List<String> stringList=new ArrayList<String>();
		// ����������ջ
		Stack<String> strStack1=new Stack<String>();
		Stack<String> strStack2=new Stack<String>();
		Stack<String> strStack3=new Stack<String>();
		for(int i=0;i<signString.length;i++){
			currentElement=signString[i];
			// �����ַ�������ջΪ��ʱ��ֱ�ӽ��ַ�ѹ��ջ1
			if(strStack1.empty() || currentElement.equals("(")){
				strStack1.push(currentElement);
			}else{
				if(currentElement.equals(")")){
					while(!strStack1.peek().equals("(")){
						strStack3.push(strStack1.pop());	
					}
					// ��"("����
					strStack1.pop();
				}else {
					/* �ж��ַ����ȼ�������ǰ�ַ������ȼ�����ջ1
					 * ջ���ַ������ȼ����򽫵�ǰ�ַ�ѹ��ջ1ջ����
					 * ��֮����ջ1ջ��Ԫ�س�ջ��ѹ��ջ2ջ����
					 * ��ѭ���жϵ�ǰ�ַ���ջ��Ԫ�ص����ȼ���
					 */
					if(getPriority(currentElement,strStack1.peek())){
						strStack1.push(currentElement);
					}else{
						// ע��ѭ��ʱӦ�����ж�ջ�Ƿ�գ����������Խ������
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
