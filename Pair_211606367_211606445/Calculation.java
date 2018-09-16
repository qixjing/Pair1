import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculation {
	int sum = 0;
	String word = "";;
	String[] cal_str = {"+","-","x","÷"};
	List<String> inffix_expression = new ArrayList<String>();
	List<String> suffix_expression = new ArrayList<String>();
	Stack<String> cal_symbol = new Stack<String>();
	Stack<String> cal_sum = new Stack<String>();

	public Calculation(String cal_problem) {
		super();
		String str_char_merge = "";
		for (int i = 0; i < cal_problem.length(); i++) {
			if(cal_problem.charAt(i) >= '0' && cal_problem.charAt(i) <= '9')
			{
				while(i < cal_problem.length() && cal_problem.charAt(i) >= '0' && cal_problem.charAt(i) <= '9')
				{
					str_char_merge += cal_problem.charAt(i);
					i++;
				}
				inffix_expression.add(str_char_merge);
				str_char_merge = "";
				i--;
			}
			else
			{	
				inffix_expression.add(cal_problem.substring(i, i+1));
			}
		}
	}

	/**
	 * 中缀表达式转后缀表达式
	 *
	 */
	public void To_Suffix_Expression()
	{
		String str_char="";
		for(int i = 0;i < inffix_expression.size();i++) {	
			str_char = inffix_expression.get(i);
			if(str_char.matches("[0-9]+"))
				suffix_expression.add(str_char);	
			else if(str_char.equals("("))
				cal_symbol.push(str_char);
			else if(str_char.equals(")"))
			{
				while(!(cal_symbol.peek().equals("(")))
				{
					suffix_expression.add(cal_symbol.pop());
				}
				cal_symbol.pop();
			}
			else
			{
				while(cal_symbol.size()!=0 && Oper(cal_symbol.peek(),str_char))
				{
					suffix_expression.add(cal_symbol.pop());
				}
				cal_symbol.push(str_char);
			}	
		}
		while(cal_symbol.size()!=0)
		{
			suffix_expression.add(cal_symbol.pop());
		}
	}
	
	private boolean Oper(String peek, String str) {
		int i = 0,j = 0;
		if(peek.equals("(") || peek == null)
			return false;
		while(i < cal_str.length && cal_str[i] != peek)
		{
			i++;
		}
		while(j < cal_str.length  && cal_str[j] != str)
		{
			j++;
		}
		if(i/2 >= j/2)
			return true;
		else
			return false;
	}

	/**
	 * 后缀表达式（即逆波兰表达式）求和
	 *
	 */
	public boolean Suffix_Expression_Summation()
	{
		for(int i = 0;i < suffix_expression.size();i++) {
			if(suffix_expression.get(i).matches("\\d+"))
			{
				cal_sum.push(suffix_expression.get(i));
			}
			else
			{
				int num1 = Integer.parseInt(cal_sum.pop());
				int num2 = Integer.parseInt(cal_sum.pop());
				if(suffix_expression.get(i).equals("+"))
				{					
					sum = num1+num2;
				}
				else if(suffix_expression.get(i).equals("-"))
				{
					sum = num2-num1;
				}
				else if(suffix_expression.get(i).equals("x"))
				{
					sum = num1*num2;
				}
				else if(suffix_expression.get(i).equals("÷") && num1 == 0)
				{
					return false;
				}
				else if(suffix_expression.get(i).equals("÷"))
				{
					sum = num2/num1;
					if(num2%num1 != 0)
						return false;
				}
				cal_sum.push(sum+"");
			}	
		}
		sum = Integer.parseInt(cal_sum.pop());
		if(sum < 0 || sum > 10000)
			return false;
		else
			return true;
	}
	
	/**
	 * 中缀表达式（生成合格的中缀表达式）word
	 *
	 */
	public void Infix_Expression_To_Word()
	{
		for (int i = 0; i < inffix_expression.size(); i++) {
			if(i == inffix_expression.size()-1)
			{
				word += inffix_expression.get(i);
				return;
			}
			else
			{
				word += inffix_expression.get(i)+" ";
			}
		}
	}
	
	/**
	 * @return word
	 */
	public String getword() {
		return word;
	}
	/**
	 * 获取后缀表达式求和值
	 * @return sum
	 */
	public int getSum() {
		return sum;
	}
	
}
