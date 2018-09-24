import java.util.Stack;
import java.util.EmptyStackException;
// Shunting Yard Algorithm(���ȳ��㷨)
public class ShuntingYard {
	
	 // ����(��׺)���ʽ
	 public String Calc(String Expression) {
		 // ����ջ
         Stack<Integer> Numbers = new Stack<Integer>();
         // ����(�����)ջ
         Stack<Character> Operators = new Stack<Character>();
         // ȥ�����ʽ�пո�
         String TempExpression = Expression.replace(" ", "");
         // �����λ����(������,���ܳ���0)
         int HighNumber = 0;
         // �ַ������ʽת��Ϊ�ַ�����
         char[] CharArray = TempExpression.toCharArray();
         // �����ַ�����
         for (int i = 0; i < CharArray.length; i++) {
        	// �жϵ�ǰchar�Ƿ�Ϊ����
            if (Character.isDigit(CharArray[i])) {
            	HighNumber = 10 * HighNumber + Integer.parseInt(String.valueOf(CharArray[i])); //��ʱ�������10������
            } else {
                if (HighNumber != 0) {
                	// ������ջ
                	Numbers.push(HighNumber);
                	// ����
                	HighNumber = 0;
                 }
                // ���Ŵ���
                if (CharArray[i] == '(') {
                	// ��������ջ
                	Operators.push(CharArray[i]);
                } else if (CharArray[i] == ')') {
                	// ��������������(�鿴��ջ�����Ķ��󣬵����Ӷ�ջ���Ƴ�)
                    while (Operators.peek() != '(') { 
                    	// ���ֳ�ջ�����ֳ�ջ�����ų�ջ������
                        int TempResult = TempCalc(Numbers.pop(), Numbers.pop(),Operators.pop());
                        // ��������ջ
                        Numbers.push(TempResult);
                    }
                    // �����ų�ջ
                    Operators.pop();
                } else if (GetPriority(CharArray[i]) > 0) {
                	// ջ�Ƿ�Ϊ��
                    if (Operators.isEmpty()) {
                    	// ������ջ
                    	Operators.push(CharArray[i]);
                    } else {
                        // ��ջ��Ԫ�����ȼ����ڻ����Ҫ��ջ��Ԫ��,��ջ��Ԫ�ص���������,Ȼ����ջ
                        if (GetPriority(Operators.peek()) >= GetPriority(CharArray[i])) {
                        	// ���ֳ�ջ�����ֳ�ջ�����ų�ջ������
                            int TempResult = TempCalc(Numbers.pop(), Numbers.pop(),Operators.pop());
                            if(TempResult < 0) {
                            	return "-1";
                            }
                            // ��������ջ
                            Numbers.push(TempResult);
                        }
                        // ������ջ
                        Operators.push(CharArray[i]);
                    }
                }
            }
        }
        // ���һ���ַ���������,δ��ջ
        if (HighNumber != 0) {
        	Numbers.push(HighNumber);
        }
        // ˳�����
        /*
    	int TempOne=0;
    	int TempTwo=0;
    	char TempOp=0;
    	*/
        while (!Operators.isEmpty()) {

        	/*
        	try {
        		TempOne = Numbers.pop();
        	}
        	catch(EmptyStackException e){
        		System.out.println("Error:Number1");
        		break;
        	}
        	try {
        		TempTwo = Numbers.pop();
        	}
        	catch(EmptyStackException e){
        		Numbers.push(TempOne);
        		System.out.println("Error:Number2");
        		break;
        	}
        	try {
        		TempOp = Operators.pop();
        	}
        	catch(EmptyStackException e){
        		System.out.println("Error:Operator");
        		break;
        	}
        	int TempResult = TempCalc(TempOne, TempTwo,TempOp);
        	Numbers.push(TempResult);
        	*/
        	int TempResult = TempCalc(Numbers.pop(), Numbers.pop(),Operators.pop());//������������
            if(TempResult < 0) {
            	return "-1";
            }
        	Numbers.push(TempResult);

            
            
        }
        return String.valueOf(Numbers.pop());
    }

    // ��������������ȼ�,���ֺ��������迼��
    private static int GetPriority(char Operator) {
        if (Operator == '+' || Operator == '-') {
            return 1;
        } else if (Operator == '��' || Operator == '��') {
            return 2;
        } else {
            return 0;
        }
    }

    // ��������Ƿ���,����ջ��ջ�����й�
    private static int TempCalc(int two, int one, char Operator) {
        int Result = -1;
        if (Operator == '+') {
        	Result = one + two;
        } else if (Operator == '-') {
        	Result = one - two;
        } else if (Operator == '��') {
        	Result = one * two;
        } else if (Operator == '��') {
      	  if(one % two != 0) {
      		  return -1;
      	  }
      	Result = one / two;
        }
        return Result;
    }
}