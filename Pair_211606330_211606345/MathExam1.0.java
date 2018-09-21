import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetThirdSubject{
	public static String[] getMathSubject(int n){
		String[] equationArray=new String[n];
		for(int i=0;i<n;i++){
			Random r=new Random();
			// �����ʾ����������������������Ϊ[2,4]
			int randomTimes=r.nextInt(3)+2;
			// ����������ĸ������ֱ���
			switch(randomTimes){
			case 2:equationArray[i]=handleTwo();break;
			case 3:equationArray[i]=handleThree();break;
			case 4:equationArray[i]=handleFour();break;
			}
		}
		return equationArray;
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
			sortSingArray=SignPrioritySort.prioritySortWithoutBrackets(randomSignArray);
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
			sortSingArray=SignPrioritySort.prioritySort(newRandomSignArray);
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
			sortSingArray=SignPrioritySort.prioritySortWithoutBrackets(randomSignArray);
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
			sortSingArray=SignPrioritySort.prioritySort(newRandomSignArray);
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
		String[] sortSingArray=SignPrioritySort.prioritySortWithoutBrackets(randomSignArray);
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

	public static void main(String[] args){
		for(int i=0;i<getMathSubject(30).length;i++){
			System.out.println(getMathSubject(30)[i]);
		}
	}

}
