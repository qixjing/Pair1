
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MathExam {
	static Scanner input = new Scanner(System.in);
	static String filename ="out.txt";	
	static File file = new File(filename),parent = null;
	static OutputStream out = null;
	static String[] str_symbol = {"+","-","x","��"},input_args= {" "," "};
	static List<String> Calculation_Problem = new ArrayList<String>();
	static int cal_number1 = 0, cal_number2 = 0,symbol = 0, sum = 0, remainder = 0, 
			   number = -1, grade = -1, 
			   calculation_num = 0 ,ran_symbol_num = 0,ran_left_parenthesis_num = 0;			
	static Random ranNum = new Random(),ranSymbol = new Random(),ranSymbolNum = new Random(),ran_left_parenthesis = new Random();
	static String word = null,check_input_message = null;
	static Pattern pattern = Pattern.compile("[0-9]*");
	public static void main(String[] args) {
			File_Initialization(file);//�ĵ���ʼ������
			Input_Message(args);//������Ϣ���
			for (int i = 1; i <= number; i++) 
			{
				Iteration(i);//����һ���ϸ����Ŀ
				File_Write_Problem(i);//�������Ŀд���ĵ�
			}
			if(number != 0)
			{
				File_Write_Answer();//��������Ŀ�Ĵ�д���ĵ�
				System.out.println("��Ŀ�Ѿ����ɣ��뿴out.txt�ĵ�");
			}	
	}
	
	/**
		Сѧ���꼶�����������Ҫ�����£�
		�������2��4��
		���Լ�����
		��������Ľ�������и���--����Ҫ���沨������ʱ�����ж�
		���������������Ϊ0������������
		������0-1000���ڣ����˵�
		��Ȼ��Ϊһ�꼶�����꼶����Ĺ��ܻ���Ҫ����
		������ѯ�����꼶�����������������С��,���������ڵ�����ֻ������
	 * 
	 * **/
	private static void Iteration(int i) {
		if(grade==1 && symbol<=1)
		{
			symbol = ranSymbol.nextInt(2);
			cal_number1 = ranNum.nextInt(101);
			cal_number2 = ranNum.nextInt(101);
			if(str_symbol[symbol].equals("+") && cal_number1 + cal_number2 <= 100)
				sum = cal_number1 + cal_number2;
			else if(str_symbol[symbol].equals("-") && cal_number1 - cal_number2 >= 0)
				sum = cal_number1 - cal_number2;
			else
				Iteration(i);
			word = "("+Integer.toString(i)+") "+Integer.toString(cal_number1)+" "+str_symbol[symbol]+" "+Integer.toString(cal_number2);
			return;
		}	
		else if(grade==2)
		{	
			symbol = ranSymbol.nextInt(4);
			cal_number1 = ranNum.nextInt(101);
			cal_number2 = ranNum.nextInt(101);
			if(str_symbol[symbol].equals("+") && cal_number1 + cal_number2 <= 100)
				sum = cal_number1 + cal_number2;
			else if(str_symbol[symbol].equals("-")&& cal_number1 - cal_number2 >= 0)
				sum = cal_number1 - cal_number2;
			else if(str_symbol[symbol].equals("x") && cal_number1 * cal_number2 <= 100)
				sum = cal_number1 * cal_number2;
			else if(str_symbol[symbol].equals("��") && cal_number2 != 0)
			{	
				sum = cal_number1 / cal_number2;
				remainder = cal_number1 % cal_number2;
			}
			else
				Iteration(i);
			word = "("+Integer.toString(i)+") "+Integer.toString(cal_number1)+" "+str_symbol[symbol]+" "+Integer.toString(cal_number2);
			return;
		}
		else if(grade==3)
		{		
			cal_number2 = ranNum.nextInt(1001);
			ran_symbol_num = ranSymbolNum.nextInt(3)+2;
			word = Integer.toString(cal_number2);
			for(int j=1;j<=ran_symbol_num;j++)
			{
				cal_number1 = cal_number2;
				cal_number2 = ranNum.nextInt(1001);
				symbol = ranSymbol.nextInt(4);
				ran_left_parenthesis_num = ran_left_parenthesis.nextInt(2);
				if(str_symbol[symbol].equals("+") && cal_number1 + cal_number2 > 1000
				|| str_symbol[symbol].equals("-") && cal_number1 - cal_number2 < 0
				|| str_symbol[symbol].equals("x") && cal_number1 * cal_number2 > 1000
				|| str_symbol[symbol].equals("��") && j!=1 && cal_number2 == 0)
				{	
					j--;
					continue;
				}
				else if(ran_left_parenthesis_num % 2 == 1 && j <= ran_symbol_num-1)
				{	
					if(j == 1)
					{
						word = "(" + word;
						symbol = ranSymbol.nextInt(2);
						cal_number2 = ranNum.nextInt(1001);
						word = word + str_symbol[symbol] + Integer.toString(cal_number2)+")";
						symbol = ranSymbol.nextInt(2) + 2;
						cal_number2 = ranNum.nextInt(1001);
						word = word+str_symbol[symbol]+Integer.toString(cal_number2);
					}
					else if(j <= ran_symbol_num-1)
					{
						word = word + str_symbol[symbol] + "("+Integer.toString(cal_number2);
						if(symbol == 0 || symbol == 1)
							symbol = ranSymbol.nextInt(2);
						else
							symbol = ranSymbol.nextInt(4);
						cal_number2 = ranNum.nextInt(1001);
						word = word + str_symbol[symbol] + Integer.toString(cal_number2)+")";
					}
					j++;
					continue;
				}
				word = word+str_symbol[symbol]+Integer.toString(cal_number2);
			}
			Calculation calculation = new Calculation(word);
			calculation.Infix_Expression_To_Word();
			calculation.To_Suffix_Expression();
			if(calculation.Suffix_Expression_Summation())
			{				
				word = "("+i+") "+calculation.getword();
				sum = calculation.getSum();
				return;
			}
			else
				Iteration(i);
		}
		else
			Iteration(i);
	}

	private static void Input_Message(String[] args) {
		int j=1;
		if(args[0].equals("-n") && args[2].equals("-grade"))
		{	input_args[0] = args[1];input_args[1] = args[3];}
		else if(args[0].equals("-grade") && args[2].equals("-n"))
		{	input_args[0] = args[3];input_args[1] = args[1];}
		else
			System.out.print("�������󣡣���");	
		check_input_message = input_args[0];//����
		if(!pattern.matcher(check_input_message).matches())
		{	
			System.out.print("������������Ϸ�������������������");
			check_input_message = input.nextLine();
		}
		else
		{
			number = Integer.parseInt(check_input_message);
			if(number < 0)
				System.out.print("������������Ϸ�������������������");
		}
		j++;
		while(number<0)
		{
			if(j!=1)
				check_input_message = input.nextLine();
			if(!pattern.matcher(check_input_message).matches())
			{	
				System.out.print("������������Ϸ�������������������");
				continue;
			}
			else
			{
				number = Integer.parseInt(check_input_message);
				if(number < 0)
					System.out.print("������������Ϸ�������������������");	
			}	
		}
		j=1;
		check_input_message = input_args[1];//�꼶
		if(!pattern.matcher(check_input_message).matches())
		{	
			System.out.print("������꼶���Ϸ��������������꼶��");
			check_input_message = input.nextLine();
		}	
		else
		{
			grade = Integer.parseInt(check_input_message);
			if(grade<1 || grade>3)
				System.out.print("������꼶���Ϸ�!�����������꼶(1��2��3)��");			
		}
		j++;
		while(grade<1 || grade>3)
		{
			if(j!=1)
				check_input_message = input.nextLine();
			if(!pattern.matcher(check_input_message).matches())
			{
				System.out.print("������꼶���Ϸ�!�����������꼶(1��2��3)��");
				continue;
			}
			else
			{
				grade = Integer.parseInt(check_input_message);
				if(grade<1 || grade>3)
					System.out.print("������꼶���Ϸ�!�����������꼶(1��2��3)��");
			}
			
		}
	}
	
	private static void File_Write_Answer() {
		try {
			out.write("\r\n".getBytes());
			for (int i = 0; i < calculation_num; i++)
			{
				out.write(Calculation_Problem.get(i).getBytes());
			}
		} 
		catch (IOException e) {
			System.out.println("������д��������ʱ�쳣��"+e.getMessage());
		}
	}

	private static void File_Write_Problem(int i) {
		try {
			
			out.write((word+"\r\n").getBytes());
			if(remainder!=0)
			{
				Calculation_Problem.add(word+" = "+Integer.toString(sum)+"..."+Integer.toString(remainder)+"\r\n");
				calculation_num++;
				remainder = 0;
			}
			else
			{	
				Calculation_Problem.add(word+" = "+Integer.toString(sum)+"\r\n");
				calculation_num++;
			}
		} 
		catch (IOException e) 
		{
			System.out.println("������out.write()ʱ�׳��쳣"+e.getMessage());
		}
	}

	private static void File_Initialization(File file) {
		if(!file.exists()) 
		{		parent = file.getParentFile();
				if(parent !=null && !parent.exists())
				{	parent.mkdir();//����Ŀ¼
					System.out.println("����Ŀ¼��"+parent);
				}
				try {
					file.createNewFile();
				} catch (IOException e) {
					System.out.println("������file.createNewFile()ʱ�׳��쳣"+e.getMessage());
				}
				System.out.println("�������ļ���"+file.getAbsolutePath());
		}//��ȡ����·��
		try {
			out = new FileOutputStream(file);
		} 
		catch (FileNotFoundException e) {
			System.out.println("������new FileOutputStream(file)ʱ�׳��쳣"+e.getMessage());
		}
	}

}
