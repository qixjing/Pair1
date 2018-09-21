import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetThirdSubject{
	public static String[] getMathSubject(int n){
		String[] equationArray=new String[n];
		for(int i=0;i<n;i++){
			Random r=new Random();
			// 定义表示运算符个数的随机数，区间为[2,4]
			int randomTimes=r.nextInt(3)+2;
			// 根据运算符的个数，分别处理
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
		String[] signArray={"+","-","×","÷"};
		int[] randomNumberArray=null;
		// 判断是否加括号
		if(isNeedBracket!=randomNumber){
			// 定义存储随机生成符号的数组
			randomSignArray=new String[4];
			randomSignArray[0]=signArray[r.nextInt(4)];
			randomSignArray[1]=signArray[r.nextInt(4)];
			randomSignArray[2]=signArray[r.nextInt(4)];
			randomSignArray[3]=signArray[r.nextInt(4)];
			// 若运算符相同,则重新生成运算符
			while(randomSignArray[2].equals(randomSignArray[0])){
				randomSignArray[2]=signArray[r.nextInt(4)];
			}
			// 调用SignPrioritySort的prioritySort()将运算符数组按优先级高→低重新排序
			sortSingArray=SignPrioritySort.prioritySortWithoutBrackets(randomSignArray);
			randomNumberArray=new int[5];
			int firstNumber=r.nextInt(1001);
			// 定义接收计算结果和操作数的数组
			int[] returnArray=new int[2];
			randomNumberArray[0]=firstNumber;
			/* 循环调用计算函数，并返回答案和生成操作数
			 * 数字第一位为返回的操作数，第二位为答案
			 * 保存到returnArray数组中
			 */
			for(int i=0;i<sortSingArray.length;i++){
				// 应注意两个参数的下标应该随调用次数的增加而改变，否则会出现除法除不尽的情况
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
			// 定义存储随机生成符号的数组
			randomSignArray=new String[3];
			randomSignArray[0]=signArray[r.nextInt(4)];
			randomSignArray[1]=signArray[r.nextInt(4)];
			randomSignArray[2]=signArray[r.nextInt(4)];
			// 若运算符相同,则重新生成运算符
			while(randomSignArray[2].equals(randomSignArray[0])){
				randomSignArray[2]=signArray[r.nextInt(4)];
			}
			/* 将运算符数组变成字符串，调用getIsNeedBrackets()方法，
			 * 判断是否加括号。若加括号，则返回加完括号的字符串，否则原样
			 * 返回。
			 */
			String signString="";
			for(int i=0;i<randomSignArray.length;i++){
				signString=signString+randomSignArray[i];
			}
			String newSignString=getBrackets(signString);
			// 将返回的新字符串变成字符串数组
			String[] newRandomSignArray=new String[newSignString.length()];
			for(int i=0;i<newSignString.length();i++){
				newRandomSignArray[i]=String.valueOf(newSignString.charAt(i));
			}
			// 调用SignPrioritySort的prioritySort()将运算符数组按优先级高→低重新排序(包含括号)
			sortSingArray=SignPrioritySort.prioritySort(newRandomSignArray);
			randomNumberArray=new int[4];
			int firstNumber=r.nextInt(1001);
			// 定义接收计算结果和操作数的数组
			int[] returnArray=new int[2];
			randomNumberArray[0]=firstNumber;
			/* 循环调用计算函数，并返回答案和生成操作数
			 * 数字第一位为返回的操作数，第二位为答案
			 * 保存到returnArray数组中
			 */
			int j=1;
			for(int i=0;i<sortSingArray.length;i++){
				if(sortSingArray[i].equals("(") || sortSingArray[i].equals(")")){
					continue;
				}else{
					// 应注意两个参数的下标应该随调用次数的增加而改变，否则会出现除法除不尽的情况
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
		String[] signArray={"+","-","×","÷"};
		int[] randomNumberArray=null;
		// 判断是否加括号
		if(isNeedBracket!=randomNumber){
			// 定义存储随机生成符号的数组
			randomSignArray=new String[3];
			randomSignArray[0]=signArray[r.nextInt(4)];
			randomSignArray[1]=signArray[r.nextInt(4)];
			randomSignArray[2]=signArray[r.nextInt(4)];
			// 若运算符相同,则重新生成运算符
			while(randomSignArray[2].equals(randomSignArray[0])){
				randomSignArray[2]=signArray[r.nextInt(4)];
			}
			// 调用SignPrioritySort的prioritySort()将运算符数组按优先级高→低重新排序
			sortSingArray=SignPrioritySort.prioritySortWithoutBrackets(randomSignArray);
			randomNumberArray=new int[4];
			int firstNumber=r.nextInt(1001);
			// 定义接收计算结果和操作数的数组
			int[] returnArray=new int[2];
			randomNumberArray[0]=firstNumber;
			/* 循环调用计算函数，并返回答案和生成操作数
			 * 数字第一位为返回的操作数，第二位为答案
			 * 保存到returnArray数组中
			 */
			for(int i=0;i<sortSingArray.length;i++){
				// 应注意两个参数的下标应该随调用次数的增加而改变，否则会出现除法除不尽的情况
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
			// 定义存储随机生成符号的数组
			randomSignArray=new String[2];
			randomSignArray[0]=signArray[r.nextInt(4)];
			randomSignArray[1]=signArray[r.nextInt(4)];
			// 若运算符相同,则重新生成运算符
			while(randomSignArray[1].equals(randomSignArray[0])){
				randomSignArray[1]=signArray[r.nextInt(4)];
			}
			/* 将运算符数组变成字符串，调用getIsNeedBrackets()方法，
			 * 判断是否加括号。若加括号，则返回加完括号的字符串，否则原样
			 * 返回。
			 */
			String signString="";
			for(int i=0;i<randomSignArray.length;i++){
				signString=signString+randomSignArray[i];
			}
			String newSignString=getBrackets(signString);
			// 将返回的新字符串变成字符串数组
			String[] newRandomSignArray=new String[newSignString.length()];
			for(int i=0;i<newSignString.length();i++){
				newRandomSignArray[i]=String.valueOf(newSignString.charAt(i));
			}
			// 调用SignPrioritySort的prioritySort()将运算符数组按优先级高→低重新排序(包含括号)
			sortSingArray=SignPrioritySort.prioritySort(newRandomSignArray);
			randomNumberArray=new int[3];
		
		int firstNumber=r.nextInt(1001);
		// 定义接收计算结果和操作数的数组
		int[] returnArray=new int[2];
		randomNumberArray[0]=firstNumber;
		/* 循环调用计算函数，并返回答案和生成操作数
		 * 数字第一位为返回的操作数，第二位为答案
		 * 保存到returnArray数组中
		 */
		int j=1;
		for(int i=0;i<sortSingArray.length;i++){
			if(sortSingArray[i].equals("(") || sortSingArray[i].equals(")")){
				continue;
			}else{
				// 应注意两个参数的下标应该随调用次数的增加而改变，否则会出现除法除不尽的情况
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
		// 定义存储随机生成符号的数组
		String[] randomSignArray=new String[2];
		String[] signArray={"+","-","×","÷"};
		randomSignArray[0]=signArray[r.nextInt(4)];
		randomSignArray[1]=signArray[r.nextInt(4)];
		// 若运算符相同,则重新生成运算符
		while(randomSignArray[1].equals(randomSignArray[0])){
			randomSignArray[1]=signArray[r.nextInt(4)];
		}
		// 调用SignPrioritySort的prioritySort()将运算符数组按优先级高→低重新排序
		String[] sortSingArray=SignPrioritySort.prioritySortWithoutBrackets(randomSignArray);
		// 排序完成后括号消失
		int firstNumber=r.nextInt(1001);
		int[] randomNumberArray=new int[3];
		// 定义接收计算结果和操作数的数组
		int[] returnArray=new int[2];
		randomNumberArray[0]=firstNumber;
		/* 循环调用计算函数，并返回答案和生成操作数
		 * 数字第一位为返回的操作数，第二位为答案
		 * 保存到returnArray数组中
		 */
		for(int i=0;i<sortSingArray.length;i++){
			// 应注意两个参数的下标应该随调用次数的增加而改变，否则会出现除法除不尽的情况
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
		case "×": if(number1!=0){
				  	returnNumber=1000/number1;
				  }else{
					returnNumber=1000/(number1+1);
				  }
				  result=number1*returnNumber;
				  retrunArray[0]=returnNumber;
				  retrunArray[1]=result;
				  break;
		case "÷": returnNumber=getFactor(number1);
				  result=number1/returnNumber;
				  retrunArray[0]=returnNumber;
				  retrunArray[1]=result;
				  break;
		}
		return retrunArray;
	}
	
	// 获取所传入参数的因数，当其为质数时返回1
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
		// 左括号是否在第一位
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
		/* 判断是否产生括号，若i与j相等,则加括号；
		 * 否则不加括号(加括号的概率
		 * 为1/5)
		 */
		String returnString="";
		List<String> list=new ArrayList<String>();
		for(int i=0;i<isNeedBrackets.length();i++){
			list.add(isNeedBrackets.substring(i,i+1));
		}
		Random r=new Random();	
		// k表示插入左括号的位置,p表示插入右括号的位置
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
