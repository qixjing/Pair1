import java.util.regex.Pattern;

public class MathExam {
	private static String errorMessage = "输入的参数形式有误，请按照 -n 题量 -grade 数量   或者 -grade 数量   -n 题量  格式输入。";
	private static String grade;

	public static void main(String[] args) {
		if(judgmentParameter(args)) {
			System.out.println("小学" + grade + "年级数学题题目已生成，请查看out.txt文件");
		}else {
			System.out.println(errorMessage);
		}
	}

	/**
	 * 
	 * @param args 用户输入的参数
	 * @return     当符合要求时返回 true，否则返回false
	 */
	private static boolean judgmentParameter(String[] args) {
		if(args.length < 4 || args.length > 4) {
			return false;
		}else {
			// 1  判断是否是用 -n ， -grade 标识
			if(!(("-n".equals(args[0]) && "-grade".equals(args[2]) )|| ("-grade".equals(args[0]) && "-n".equals(args[2])))) {
				return false;
			}
			
			// 2 去除数字参数的前导0
			args[1] = args[1].replaceFirst("^0*", "");
			args[3] = args[3].replaceFirst("^0*", "");
			
			// 3 检验题数参数是否都是数字
			Pattern pattern = Pattern.compile("[0-9]*");
			boolean matches = pattern.matcher(args["-n".equals(args[0]) ? 1 : 3]).matches();
			
			if (matches && args["-n".equals(args[0]) ? 3 : 1].length() > 4) {
				errorMessage = "题目数量过大，请重新运行，输入参数";
				return false;
			} else if (!matches) {
				errorMessage = "题目数量不是正整数，请重新运行，输入一个正整数";
				return false;
			}
			
			// 4 检验年级参数是否是1~3
			Pattern compile = Pattern.compile("[1-3]?");
			boolean matches2 = compile.matcher(args["-n".equals(args[0]) ? 3 : 1]).matches();
			
			if (!matches2) {
				errorMessage = "目前只支持1~3年级，请重新运行，输入1~3中的一个数字";
				return false;
			}
		}
		return true;
	}
}
