
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathExam{
	private Bean bean = null;
	private static int grade = 0; // 年级
	private static int numberOfTopics = 0; // 出题数
	private Random random = new Random();
	static int mod = 0;

	public static void main(String[] args) {
		checkInput(args);
		new MathExam().start();
	}

	private void start() {
		StringBuffer topic1 = new StringBuffer("");
		StringBuffer topic2 = new StringBuffer("");
		StringBuffer topic3 = new StringBuffer("");
		this.bean = new Bean();
		if (grade == 1 || grade == 2) {
			for (int i = 0; i < numberOfTopics; i++) {
				String s=null;
				if (grade == 1) {
					String[] symbol = { "+", "-" };
					s = symbol[random.nextInt(2)];
				} else {
					String[] symbol = { "*", "/" };
					s = symbol[random.nextInt(2)];
				}
				bean.setSymbol(s);
				Topic ran = TopicFactory.createRan(bean.getSymbol());
				String topic = ran.createTopic();
				PolishNotation po = new PolishNotation(new StringBuffer(topic));
				String result = (po.calculate());
				topic2.append("(" + (i + 1) + ") " + topic + "\n");
				topic3.append("(" + (i + 1) + ") " + topic + " = " + result);
				if (mod != 0) {
					topic3.append("..." + mod);
					mod = 0;
				}
				topic3.append("\n");
				//( 360 - 71 ) / 17 - 17 = 0        待解决
			}
		} else if (grade == 3) {
			for (int i = 0; i < numberOfTopics; i++) {
				TopicGradeThree topic = new TopicGradeThree();
				topic1 = topic.createTopic();
				PolishNotation po = new PolishNotation(topic1);
				String result = (po.calculate());
				topic2.append("(" + (i + 1) + ") " + topic1 + "\n");
				topic3.append("(" + (i + 1) + ") " + topic1 + " = " + result + "\n");
			}
		}
		String txt = String.valueOf(topic2) + "\n" + String.valueOf(topic3);
		
		txt=txt.replaceAll("\\*","×");
		txt=txt.replaceAll("\\/","÷");
//		System.out.println(txt);
		createMathExamTxt(txt);
	}

	// 检查输入的参数是否符合规范
	private static void checkInput(String[] args) {
		// System.out.println(args.length);//test
		if (args.length != 4) {
			exception();
		}
		String input = args[0] + " " + args[1] + " " + args[2] + " " + args[3];
		// System.out.println(input);//test
		// -grade和-n的顺序可以颠倒
		input.matches("(\\-grade [123] \\-n [1-9]\\d*)|(\\-n [1-9]\\d* \\-grade [123])");
		Matcher m = Pattern.compile("\\-grade [123]").matcher(input);

		if (m.find()) {
			String n = m.group();
			// System.out.println(n); //test
			m = Pattern.compile("[123]").matcher(n);
			if (m.find()) {
				grade = Integer.valueOf(m.group());
			}

		} else {
			exception();
		}
		m = Pattern.compile("\\-n [1-9]\\d*").matcher(input);
		if (m.find()) {
			// 找到数字，设置出题数
			String n = m.group();
			// System.out.println(n); //test
			m = Pattern.compile("[1-9]\\d*").matcher(n);
			String digit = null;
			if (m.find()) {
				digit = m.group();
				if (digit.length() >= 5) {
					exception();
				}
			}
			numberOfTopics = Integer.valueOf(digit);
		} else {
			exception();
		}

	}

	private static void exception() {
		System.out.println("输入有误,程序结束。");
		System.exit(0);
	}

	// 产生txt文本
	private void createMathExamTxt(String txt) {
		try {
			FileOutputStream fos = new FileOutputStream("out.txt");
			PrintStream ps = new PrintStream(fos);
			ps.println(txt);
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
