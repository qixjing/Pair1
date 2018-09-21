import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class MathExam6330 {		
	// ����Сѧһ�꼶��ѧ�Ӽ�������
	public static void getFirstGradeMathSubject(int n,int grade){
		/* ������������ֱ����ڴ����ʽ�ʹ�
		 ע��:��������ҿ�,���鳤��Ӧ��Ϊn+1���������������Խ��
		 */
		String[] mathSubjectArray = new String[n+1];
		mathSubjectArray[0]="";
		String[] sumArray = new String[n+1];
		sumArray[0]=null;
		for(int i=1;i<=n;i++){
		    int firstNumber;
		    int secondNumber;
		    int	thirdNumber;
		    String randomSymbol=null;
		    Random randomNumber=new Random();
		    /* ȡ������[0��21)֮�������,ǰ�������������Ϊ��ʽ������,
			�������������Ϊȷ����ʽ���ŵ����ݣ��������������10ʱ,
		       ��ʽΪ�ӷ�����֮Ϊ����
		    */
		    firstNumber=randomNumber.nextInt(21);
		    secondNumber=randomNumber.nextInt(21-firstNumber);
		    thirdNumber=randomNumber.nextInt(21);
		    if(thirdNumber>10){
		    	randomSymbol="+";
		    }else{
		    	randomSymbol="-";
		    }
		    mathSubjectArray[i]="("+i+")"+" "+firstNumber+randomSymbol+secondNumber;
		    if(randomSymbol.equals("-")){
		    	if((firstNumber-secondNumber)>=0){
		    		sumArray[i]=firstNumber-secondNumber+"";
		    	}else{
		    		sumArray[i]=secondNumber-firstNumber+"";
		    		mathSubjectArray[i]="("+i+")"+" "+secondNumber+randomSymbol+firstNumber;
		    	}
		    }else{
		    	sumArray[i]=firstNumber+secondNumber+"";
		    }
		}
		saveMathAnswer(mathSubjectArray, sumArray);
		getMathAnswer();
	}
	
	// ����Сѧ���꼶�˳�������
	public static void getSecondGradeMathSuject(int n,int grade){
		/* ������������ֱ����ڴ����ʽ�ʹ�
		 ע��:��������ҿ�,���鳤��Ӧ��Ϊn+1���������������Խ��
		 */
		String[] mathSubjectArray = new String[n+1];
		mathSubjectArray[0]="";
		String[] sumArray = new String[n+1];
		sumArray[0]=null;
		for(int i=1;i<=n;i++){
		    int firstNumber;
		    int secondNumber;
		    int	thirdNumber;
		    String randomSymbol=null;
		    Random randomNumber=new Random();
		    /* ȡ������1-9֮������������Ϊ��ʽ������,
			�������������Ϊȷ����ʽ���ŵ����ݣ��������������4ʱ,
		       ��ʽΪ�˷�����֮Ϊ����
		    */
		    firstNumber=randomNumber.nextInt(9)+1;
		    secondNumber=randomNumber.nextInt(9)+1;
		    thirdNumber=randomNumber.nextInt(9);
		    if(thirdNumber>4){
		    	randomSymbol="��";
		    }else{
		    	randomSymbol="��";
		    }
		    mathSubjectArray[i]="("+i+")"+" "+firstNumber+randomSymbol+secondNumber;
		    // �����̺�����
    		String quotient=null;
    		String remainder=null;
    		// �ж���ʽ�ǳ˷����㻹�ǳ�������
		    if(randomSymbol.equals("��")){
		    	// ����ʽΪ����ʱ��������������������ʹ����������С��81
		    	firstNumber=randomNumber.nextInt(80)+1;
		    	secondNumber=randomNumber.nextInt(80)+1;
		    	// �жϱ������Ƿ���ڳ���
		    	if(firstNumber>secondNumber){
		    		// �ж��Ƿ��ܹ�����
		    		if((firstNumber%secondNumber)==0){
		    			quotient=firstNumber/secondNumber+"";
		    			sumArray[i]=quotient;
		    		}else{
		    			quotient=firstNumber/secondNumber+"";
		    			remainder=firstNumber%secondNumber+"";
		    			sumArray[i]=quotient+"..."+remainder;
		    		}
		    	// �޸�������Ҫ��Ӧ���޸���ʽ
		    	mathSubjectArray[i]="("+i+")"+" "+firstNumber+randomSymbol+secondNumber;
		    	}else{
		    		//��������С�ڳ���ʱ���������ͳ�������
		    		if((secondNumber%firstNumber)==0){
		    			quotient=secondNumber/firstNumber+"";
		    			sumArray[i]=quotient;
		    		}else{
		    			quotient=secondNumber/firstNumber+"";
		    			remainder=secondNumber%firstNumber+"";
		    			sumArray[i]=quotient+"..."+remainder;
		    		}
		    		mathSubjectArray[i]="("+i+")"+" "+secondNumber+randomSymbol+firstNumber;
		    	}
		    }else{
		    	sumArray[i]=firstNumber*secondNumber+"";
		    }
		}
		saveMathAnswer(mathSubjectArray, sumArray);
		getMathAnswer();	
	}
	// ����3�꼶��Ŀ
		public static void getThirdGradeMathSubject(int n){
			String[] equationArray=new String[2];
			String[] mathSubjectArray = new String[n+1];
			mathSubjectArray[0]="";
			String[] sumArray = new String[n+1];
			sumArray[0]=null;
			for(int i=0;i<n;i++){
				Random r=new Random();
				// �����ʾ����������������������Ϊ[2,4]
				int randomTimes=r.nextInt(3)+2;
				// ����������ĸ������ֱ���
				switch(randomTimes){
				case 2:equationArray=handleTwo().split("=");break;
				case 3:equationArray=handleThree().split("=");break;
				case 4:equationArray=handleFour().split("=");break;
				}
				mathSubjectArray[i+1]="("+(i+1)+")"+equationArray[0];
				sumArray[i+1]=equationArray[1];
			}
			saveMathAnswer(mathSubjectArray, sumArray);
			getMathAnswer();
		}

		private static String handleFour() {
			Random r=new Random();
			int isNeedBracket=2;
			int randomNumber=r.nextInt(3);
			String[] randomSignArray=null;
			String[] sortSingArray=null;
			String[] signArray={"+","-","��","��"};
			int[] randomNumberArray=null;
			// �ж��Ƿ������
			if(isNeedBracket!=randomNumber){
				// ����洢������ɷ��ŵ�����
				randomSignArray=new String[4];
				randomSignArray[0]=signArray[r.nextInt(4)];
				randomSignArray[1]=signArray[r.nextInt(4)];
				randomSignArray[2]=signArray[r.nextInt(4)];
				randomSignArray[3]=signArray[r.nextInt(4)];
				// ���������ͬ,���������������
				while(randomSignArray[2].equals(randomSignArray[0])){
					randomSignArray[2]=signArray[r.nextInt(4)];
				}
				// ����SignPrioritySort��prioritySort()����������鰴���ȼ��ߡ�����������
				sortSingArray=prioritySortWithoutBrackets(randomSignArray);
				randomNumberArray=new int[5];
				int firstNumber=r.nextInt(1001);
				// ������ռ������Ͳ�����������
				int[] returnArray=new int[2];
				randomNumberArray[0]=firstNumber;
				/* ѭ�����ü��㺯���������ش𰸺����ɲ�����
				 * ���ֵ�һλΪ���صĲ��������ڶ�λΪ��
				 * ���浽returnArray������
				 */
				for(int i=0;i<sortSingArray.length;i++){
					// Ӧע�������������±�Ӧ������ô��������Ӷ��ı䣬�������ֳ��������������
					returnArray=handleSign(sortSingArray[i],firstNumber);
					randomNumberArray[i+1]=returnArray[0];
					firstNumber=returnArray[1];
				}
				String returnString="";
				for(int i=0;i<sortSingArray.length;i++){
					returnString=returnString+randomNumberArray[i]+sortSingArray[i];
				}
				returnString=returnString+randomNumberArray[randomNumberArray.length-1]+"="+returnArray[1];		
				return returnString;		
			}else{
				// ����洢������ɷ��ŵ�����
				randomSignArray=new String[3];
				randomSignArray[0]=signArray[r.nextInt(4)];
				randomSignArray[1]=signArray[r.nextInt(4)];
				randomSignArray[2]=signArray[r.nextInt(4)];
				// ���������ͬ,���������������
				while(randomSignArray[2].equals(randomSignArray[0])){
					randomSignArray[2]=signArray[r.nextInt(4)];
				}
				/* ��������������ַ���������getIsNeedBrackets()������
				 * �ж��Ƿ�����š��������ţ��򷵻ؼ������ŵ��ַ���������ԭ��
				 * ���ء�
				 */
				String signString="";
				for(int i=0;i<randomSignArray.length;i++){
					signString=signString+randomSignArray[i];
				}
				String newSignString=getBrackets(signString);
				// �����ص����ַ�������ַ�������
				String[] newRandomSignArray=new String[newSignString.length()];
				for(int i=0;i<newSignString.length();i++){
					newRandomSignArray[i]=String.valueOf(newSignString.charAt(i));
				}
				// ����SignPrioritySort��prioritySort()����������鰴���ȼ��ߡ�����������(��������)
				sortSingArray=prioritySort(newRandomSignArray);
				randomNumberArray=new int[4];
				int firstNumber=r.nextInt(1001);
				// ������ռ������Ͳ�����������
				int[] returnArray=new int[2];
				randomNumberArray[0]=firstNumber;
				/* ѭ�����ü��㺯���������ش𰸺����ɲ�����
				 * ���ֵ�һλΪ���صĲ��������ڶ�λΪ��
				 * ���浽returnArray������
				 */
				int j=1;
				for(int i=0;i<sortSingArray.length;i++){
					if(sortSingArray[i].equals("(") || sortSingArray[i].equals(")")){
						continue;
					}else{
						// Ӧע�������������±�Ӧ������ô��������Ӷ��ı䣬�������ֳ��������������
						returnArray=handleSign(sortSingArray[i],firstNumber);            
						randomNumberArray[j]=returnArray[0];
						j++;
						firstNumber=returnArray[1];
					}
				}
				return getAnswer(sortSingArray, randomNumberArray)+"="+returnArray[1];
			}
		}

		public static String handleThree(){
			Random r=new Random();
			int isNeedBracket=2;
			int randomNumber=r.nextInt(3);
			String[] randomSignArray=null;
			String[] sortSingArray=null;
			String[] signArray={"+","-","��","��"};
			int[] randomNumberArray=null;
			// �ж��Ƿ������
			if(isNeedBracket!=randomNumber){
				// ����洢������ɷ��ŵ�����
				randomSignArray=new String[3];
				randomSignArray[0]=signArray[r.nextInt(4)];
				randomSignArray[1]=signArray[r.nextInt(4)];
				randomSignArray[2]=signArray[r.nextInt(4)];
				// ���������ͬ,���������������
				while(randomSignArray[2].equals(randomSignArray[0])){
					randomSignArray[2]=signArray[r.nextInt(4)];
				}
				// ����SignPrioritySort��prioritySort()����������鰴���ȼ��ߡ�����������
				sortSingArray=prioritySortWithoutBrackets(randomSignArray);
				randomNumberArray=new int[4];
				int firstNumber=r.nextInt(1001);
				// ������ռ������Ͳ�����������
				int[] returnArray=new int[2];
				randomNumberArray[0]=firstNumber;
				/* ѭ�����ü��㺯���������ش𰸺����ɲ�����
				 * ���ֵ�һλΪ���صĲ��������ڶ�λΪ��
				 * ���浽returnArray������
				 */
				for(int i=0;i<sortSingArray.length;i++){
					// Ӧע�������������±�Ӧ������ô��������Ӷ��ı䣬�������ֳ��������������
					returnArray=handleSign(sortSingArray[i],firstNumber);
					randomNumberArray[i+1]=returnArray[0];
					firstNumber=returnArray[1];
				}
				String returnString="";
				for(int i=0;i<sortSingArray.length;i++){
					returnString=returnString+randomNumberArray[i]+sortSingArray[i];
				}
				returnString=returnString+randomNumberArray[randomNumberArray.length-1]+"="+returnArray[1];		
				return returnString;
			}else{
				// ����洢������ɷ��ŵ�����
				randomSignArray=new String[2];
				randomSignArray[0]=signArray[r.nextInt(4)];
				randomSignArray[1]=signArray[r.nextInt(4)];
				// ���������ͬ,���������������
				while(randomSignArray[1].equals(randomSignArray[0])){
					randomSignArray[1]=signArray[r.nextInt(4)];
				}
				/* ��������������ַ���������getIsNeedBrackets()������
				 * �ж��Ƿ�����š��������ţ��򷵻ؼ������ŵ��ַ���������ԭ��
				 * ���ء�
				 */
				String signString="";
				for(int i=0;i<randomSignArray.length;i++){
					signString=signString+randomSignArray[i];
				}
				String newSignString=getBrackets(signString);
				// �����ص����ַ�������ַ�������
				String[] newRandomSignArray=new String[newSignString.length()];
				for(int i=0;i<newSignString.length();i++){
					newRandomSignArray[i]=String.valueOf(newSignString.charAt(i));
				}
				// ����SignPrioritySort��prioritySort()����������鰴���ȼ��ߡ�����������(��������)
				sortSingArray=prioritySort(newRandomSignArray);
				randomNumberArray=new int[3];
			
			int firstNumber=r.nextInt(1001);
			// ������ռ������Ͳ�����������
			int[] returnArray=new int[2];
			randomNumberArray[0]=firstNumber;
			/* ѭ�����ü��㺯���������ش𰸺����ɲ�����
			 * ���ֵ�һλΪ���صĲ��������ڶ�λΪ��
			 * ���浽returnArray������
			 */
			int j=1;
			for(int i=0;i<sortSingArray.length;i++){
				if(sortSingArray[i].equals("(") || sortSingArray[i].equals(")")){
					continue;
				}else{
					// Ӧע�������������±�Ӧ������ô��������Ӷ��ı䣬�������ֳ��������������
					returnArray=handleSign(sortSingArray[i],firstNumber);            
					randomNumberArray[j]=returnArray[0];
					j++;
					firstNumber=returnArray[1];
				}
			}
			return getAnswer(sortSingArray, randomNumberArray)+"="+returnArray[1];
			}
		}

		public static String handleTwo(){
			Random r=new Random();
			// ����洢������ɷ��ŵ�����
			String[] randomSignArray=new String[2];
			String[] signArray={"+","-","��","��"};
			randomSignArray[0]=signArray[r.nextInt(4)];
			randomSignArray[1]=signArray[r.nextInt(4)];
			// ���������ͬ,���������������
			while(randomSignArray[1].equals(randomSignArray[0])){
				randomSignArray[1]=signArray[r.nextInt(4)];
			}
			// ����SignPrioritySort��prioritySort()����������鰴���ȼ��ߡ�����������
			String[] sortSingArray=prioritySortWithoutBrackets(randomSignArray);
			// ������ɺ�������ʧ
			int firstNumber=r.nextInt(1001);
			int[] randomNumberArray=new int[3];
			// ������ռ������Ͳ�����������
			int[] returnArray=new int[2];
			randomNumberArray[0]=firstNumber;
			/* ѭ�����ü��㺯���������ش𰸺����ɲ�����
			 * ���ֵ�һλΪ���صĲ��������ڶ�λΪ��
			 * ���浽returnArray������
			 */
			for(int i=0;i<sortSingArray.length;i++){
				// Ӧע�������������±�Ӧ������ô��������Ӷ��ı䣬�������ֳ��������������
				returnArray=handleSign(sortSingArray[i],firstNumber);
				randomNumberArray[i+1]=returnArray[0];
				firstNumber=returnArray[1];
			}
			String returnString="";
			for(int i=0;i<sortSingArray.length;i++){
				returnString=returnString+randomNumberArray[i]+sortSingArray[i];
			}
			returnString=returnString+randomNumberArray[randomNumberArray.length-1]+"="+returnArray[1];		
			return returnString;
		}

		public static int[] handleSign(String sign,int number1){
			Random r=new Random();
			int[] retrunArray=new int[2];
			int returnNumber=0,result=0;
			switch(sign){
			case "+": returnNumber=1000-r.nextInt(1001);
					  result=number1+returnNumber;
					  retrunArray[0]=returnNumber;
					  retrunArray[1]=result;
					  break;
			case "-": if(number1==0){
					  	returnNumber=0;
					  	result=number1-returnNumber;
					  }else{
						returnNumber=r.nextInt(number1);
						result=number1-returnNumber;
					  }
					  retrunArray[0]=returnNumber;
					  retrunArray[1]=result;
					  break;
			case "��": if(number1!=0){
					  	returnNumber=1000/number1;
					  }else{
						returnNumber=1000/(number1+1);
					  }
					  result=number1*returnNumber;
					  retrunArray[0]=returnNumber;
					  retrunArray[1]=result;
					  break;
			case "��": returnNumber=getFactor(number1);
					  result=number1/returnNumber;
					  retrunArray[0]=returnNumber;
					  retrunArray[1]=result;
					  break;
			}
			return retrunArray;
		}
		
		// ��ȡ���������������������Ϊ����ʱ����1
		public static int getFactor(int product){
			int factor=1;
			List<Integer> factorList=new ArrayList<Integer>();
			for(int i=2;i<product-1;i++){
				if(product%i==0){
					factorList.add(i);
				}
			}
			if(factorList.size()==0){
				return factor;
			}else{
				Random r=new Random();
				int i=r.nextInt(factorList.size());
				factor=factorList.get(i);
				return factor;
			}
		}
		
		public static String getAnswer(String[] signArray,int[] numberArray){
			List<String> list=new ArrayList<String>();
			String[] strArray=signArray;
			int[] intArray=numberArray;
			String[] intToStringArray=new String[intArray.length];
			for(int i=0;i<intArray.length;i++){
				intToStringArray[i]=String.valueOf(intArray[i]);
			}
			String returnString="";
			int j=0;
			// �������Ƿ��ڵ�һλ
			if(strArray[0].equals("(")){
				for(int i=0;i<strArray.length;i++){
					if(strArray[i].equals(")")){
						list.add(strArray[i]);
					}else{
						list.add(strArray[i]);
						list.add(intToStringArray[j]);
						j++;
					}
				}
			}else{
				list.add(intToStringArray[0]);
				for(int i=0;i<strArray.length;i++){
					if((i+1)!=strArray.length && strArray[i+1].equals("(")){
						list.add(strArray[i]);
					}else if(strArray[i].equals(")")){
						list.add(strArray[i]);
					}else{
						list.add(strArray[i]);
						list.add(intToStringArray[j]);
						j++;
					}
				}
			}
			for(int i=0;i<list.size();i++){
				returnString=returnString+list.get(i);
			}
			return returnString;
		}
		
		public static String getBrackets(String isNeedBrackets){
			/* �ж��Ƿ�������ţ���i��j���,������ţ�
			 * ���򲻼�����(�����ŵĸ���
			 * Ϊ1/5)
			 */
			String returnString="";
			List<String> list=new ArrayList<String>();
			for(int i=0;i<isNeedBrackets.length();i++){
				list.add(isNeedBrackets.substring(i,i+1));
			}
			Random r=new Random();	
			// k��ʾ���������ŵ�λ��,p��ʾ���������ŵ�λ��
			int k=0,p=0;
			k=r.nextInt(isNeedBrackets.length());
			list.add(k,"(");
			List<Integer> rightSignLocation=new ArrayList<Integer>();
			int rightLoction=0;
			if(k==0){
				for(int i=2;i<list.size();i++){
					rightSignLocation.add(i);
				}
				rightLoction=r.nextInt(rightSignLocation.size());
				p=rightSignLocation.get(rightLoction);
			}else{		
				for(int i=k+2;i<=list.size();i++){
					rightSignLocation.add(i);
				}
				rightLoction=r.nextInt(rightSignLocation.size());
				p=rightSignLocation.get(rightLoction);
			}
			list.add(p,")");
			for(String s:list){
				returnString=returnString+s;
			}
			return returnString;
		}
	
	// �������ɵ���Ŀ����
	public static void saveMathAnswer(String[] mathSubjectArray, String[] sumArray){
		File file=new File("out.txt");
		if(!file.exists()){
			File parent=file.getParentFile();
			if(parent!=null  && !parent.exists()){
				file.mkdirs();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedWriter bw=null;
		String str="";
		try{
			bw=new BufferedWriter(new FileWriter(file));
			// ������ʽ
			for(int i=1;i<mathSubjectArray.length;i++){
				str=mathSubjectArray[i];
				bw.write(str);
				bw.newLine();
				bw.flush();
			}
			bw.newLine();
			bw.flush();
			// ���ɴ�
			for(int i=1;i<mathSubjectArray.length;i++){
				str=mathSubjectArray[i]+" = "+sumArray[i];
				bw.write(str);
				bw.newLine();
				bw.flush();
			}
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ������ɵ���Ŀ�ʹ�
	public static void getMathAnswer(){
		File file=new File("out.txt");
		BufferedReader br=null;
		String str="";
		try {
			br=new BufferedReader(new FileReader(file));
			try {
				while((str=br.readLine())!=null){
					System.out.println(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
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
	
	public static void main(String[] args) {	
		int n=0;
		int grade=1;
		if(args[0].equals("-n")){
			n=Integer.valueOf(args[1]);
			grade=Integer.valueOf(args[3]);
			if(n>100){
				System.out.println("��������ȷ����Ŀ��1~100");
				return ;
			}
			if(grade>3){
				System.out.println("��������ȷ���꼶1~3");
				return ;
			}
			switch(grade){
			case 1:getFirstGradeMathSubject(n, grade);break;
			case 2:getSecondGradeMathSuject(n, grade);break;
			case 3:getThirdGradeMathSubject(n);break;
			}
		}else if(args[0].equals("-grade")){
			n=Integer.valueOf(args[3]);
			grade=Integer.valueOf(args[1]);
			if(n>100){
				System.out.println("��������ȷ����Ŀ��1~100");
				return ;
			}
			if(grade>3){
				System.out.println("��������ȷ���꼶1~3");
				return ;
			}
			switch(grade){
			case 1:getFirstGradeMathSubject(n, grade);break;
			case 2:getSecondGradeMathSuject(n, grade);break;
			case 3:getThirdGradeMathSubject(n);break;
			}
		}else{
			System.out.println("�밴"+"-n ��Ŀ�� -grade �꼶"+"�ĸ�ʽ������Ҫ���ɵ���Ŀ");
		}
	}
}
