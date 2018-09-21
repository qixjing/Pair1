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
	// 生成小学一年级数学加减计算题
	public static void getFirstGradeMathSubject(int n,int grade){
		/* 定义两个数组分别用于存放算式和答案
		 注意:数组左闭右开,数组长度应当为n+1，否则会引起数组越界
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
		    /* 取三个在[0，21)之间的整数,前两个随机整数作为算式的因子,
			第三个随机数作为确定算式符号的依据，当该随机数大于10时,
		       算式为加法，反之为减法
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
	
	// 生成小学二年级乘除计算题
	public static void getSecondGradeMathSuject(int n,int grade){
		/* 定义两个数组分别用于存放算式和答案
		 注意:数组左闭右开,数组长度应当为n+1，否则会引起数组越界
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
		    /* 取两个在1-9之间的随机整数作为算式的因子,
			第三个随机数作为确定算式符号的依据，当该随机数大于4时,
		       算式为乘法，反之为除法
		    */
		    firstNumber=randomNumber.nextInt(9)+1;
		    secondNumber=randomNumber.nextInt(9)+1;
		    thirdNumber=randomNumber.nextInt(9);
		    if(thirdNumber>4){
		    	randomSymbol="×";
		    }else{
		    	randomSymbol="÷";
		    }
		    mathSubjectArray[i]="("+i+")"+" "+firstNumber+randomSymbol+secondNumber;
		    // 定义商和余数
    		String quotient=null;
    		String remainder=null;
    		// 判断算式是乘法运算还是除法运算
		    if(randomSymbol.equals("÷")){
		    	// 当算式为除法时，重新生成两个因数，使得两个因数小于81
		    	firstNumber=randomNumber.nextInt(80)+1;
		    	secondNumber=randomNumber.nextInt(80)+1;
		    	// 判断被除数是否大于除数
		    	if(firstNumber>secondNumber){
		    		// 判断是否能够整除
		    		if((firstNumber%secondNumber)==0){
		    			quotient=firstNumber/secondNumber+"";
		    			sumArray[i]=quotient;
		    		}else{
		    			quotient=firstNumber/secondNumber+"";
		    			remainder=firstNumber%secondNumber+"";
		    			sumArray[i]=quotient+"..."+remainder;
		    		}
		    	// 修改因数后要相应的修改算式
		    	mathSubjectArray[i]="("+i+")"+" "+firstNumber+randomSymbol+secondNumber;
		    	}else{
		    		//当被除数小于除数时将被除数和除数互换
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
	// 生成3年级题目
		public static void getThirdGradeMathSubject(int n){
			String[] equationArray=new String[2];
			String[] mathSubjectArray = new String[n+1];
			mathSubjectArray[0]="";
			String[] sumArray = new String[n+1];
			sumArray[0]=null;
			for(int i=0;i<n;i++){
				Random r=new Random();
				// 定义表示运算符个数的随机数，区间为[2,4]
				int randomTimes=r.nextInt(3)+2;
				// 根据运算符的个数，分别处理
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
				sortSingArray=prioritySortWithoutBrackets(randomSignArray);
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
				sortSingArray=prioritySort(newRandomSignArray);
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
				sortSingArray=prioritySortWithoutBrackets(randomSignArray);
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
				sortSingArray=prioritySort(newRandomSignArray);
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
			String[] sortSingArray=prioritySortWithoutBrackets(randomSignArray);
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
	
	// 保存生成的题目及答案
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
			// 保存算式
			for(int i=1;i<mathSubjectArray.length;i++){
				str=mathSubjectArray[i];
				bw.write(str);
				bw.newLine();
				bw.flush();
			}
			bw.newLine();
			bw.flush();
			// 生成答案
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
	
	// 输出生成的题目和答案
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
	
	public static void main(String[] args) {	
		int n=0;
		int grade=1;
		if(args[0].equals("-n")){
			n=Integer.valueOf(args[1]);
			grade=Integer.valueOf(args[3]);
			if(n>100){
				System.out.println("请输入正确的题目数1~100");
				return ;
			}
			if(grade>3){
				System.out.println("请输入正确的年级1~3");
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
				System.out.println("请输入正确的题目数1~100");
				return ;
			}
			if(grade>3){
				System.out.println("请输入正确的年级1~3");
				return ;
			}
			switch(grade){
			case 1:getFirstGradeMathSubject(n, grade);break;
			case 2:getSecondGradeMathSuject(n, grade);break;
			case 3:getThirdGradeMathSubject(n);break;
			}
		}else{
			System.out.println("请按"+"-n 题目数 -grade 年级"+"的格式输入需要生成的题目");
		}
	}
}
