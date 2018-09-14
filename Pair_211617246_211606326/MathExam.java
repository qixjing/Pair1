
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MathExam {
    
    final static int GRADE1_MAX = 20;
    final static int GRADE2_MAX = 90;
    final static String FILENAME = "out.txt";
    static int num;
    static int grade;
    static String errMessage;
    static String[] operator = {"+", "-", "*", "/"};// 三年级符号
    static List<String> strArrayQ, strArrayA;// 存放题目和答案用于输出到文件
    
    
    public static void main(String[] args) throws IOException {
        if (checkInput(args)) {
            switch (grade) {
            case 1:
                grade1(num);
                break;
            case 2:
                grade2(num);
                break;
            case 3:
                grade3(num);
                break;
            default:
                break;
            }
            createTxt();
        } else {
            System.out.println(errMessage);
        }
    }
    
    /* 检查命令行输入 */
    private static boolean checkInput(String[] args) {
        /*
         * 1.检查参数个数，要求为4个
         * 2.检查第1，3个参数是否为"-n"或者"-grade"
         * 3.对应其参数检查其输入合理性，-n要求为正整数且不能太大，-grade要求为1、2、3
         */
        if (args.length == 4) {
            if (args[0].equals("-n")) {
                if (args[2].equals("-grade")) {
                    // 参数顺序-n -grade
                    try {
                        num = Integer.parseInt(args[1]);
                        if (num < 0) {
                            errMessage = "题目数量为负！请重新运行！";
                            return false;
                        } else if (num == 0 || num > 50) {
                            errMessage = "请输入合适的题目数量！比如1-50";
                            return false;
                        } else {
                            try {
                                grade = Integer.parseInt(args[3]);
                                if (grade == 1 || grade == 2 || grade == 3) {
                                    return true;
                                } else {
                                    errMessage = "年级选择超出范围，请选择一二三年级。";
                                    return false;
                                }
                            } catch (NumberFormatException e) {
                                errMessage = "年级选择选项非整数！请重新运行！";
                                return false;
                            }
                        }
                    } catch (NumberFormatException e) {
                        errMessage = "题目数量选项非整数！请重新运行！";
                        return false;
                    }
                }
            } else if (args[0].equals("-grade")) {
                if (args[2].equals("-n")) {
                    // 参数顺序-grade -n
                    try {
                        grade = Integer.parseInt(args[1]);
                        if (grade == 1 || grade == 2 || grade == 3) {
                            try {
                                num = Integer.parseInt(args[3]);
                                if (num < 0) {
                                    errMessage = "题目数量为负！请重新运行！";
                                    return false;
                                } else if (num == 0 || num > 50) {
                                    errMessage = "请输入合适的题目数量！比如1-50";
                                    return false;
                                } else {
                                    return true;
                                }
                            } catch (NumberFormatException e) {
                                errMessage = "题目数量选项非整数！请重新运行！";
                                return false;
                            }
                        } else {
                            errMessage = "年级选择超出范围，请选择一二三年级。";
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        errMessage = "年级选择选项非整数！请重新运行！";
                        return false;
                    }
                }
            }
        } else {
            errMessage = "参数个数有误！请重新运行！";
        }
        return false;
    }
    
    /* 将中缀表达式转换成后缀表达式 */
    private static String libolan(String shizi) {
        return shizi;
        
    }
    
    /* 计算后缀表达式的值 */
    private static int calc(String shizi) {
        return 0;
        
    }
    
    /* 生成一年级题目 */
    private static void grade1(int num) {
        /*
         * 1.随机生成运算符，加法或者减法
         * 2.随机生成第一个运算数，加法：第二个无要求；减法：第二个要求小于等于第一个
         * 3.判断题目是否重复
         * 4.存入题目数组并计算结果存入答案数组
         */
        int number1, number2 = 0;
        int fuhao = 0;
        int result = 0;
        // 检查重复
        List<String> check1 = new ArrayList<String>();
        List<String> check2 = new ArrayList<String>();
        String checkRepeat = null;
        
        strArrayQ = new ArrayList<String>();
        strArrayA = new ArrayList<String>();
        
        for (int i = 1; i <= num; i++) {
            fuhao = (int)(Math.random()*2);
            if (fuhao == 0) {
                do {
                    // 保证没有重复的式子生成
                    number1 = (int)(Math.random()*(GRADE1_MAX)+1);
                    number2 = (int)(Math.random()*(GRADE1_MAX)+1);
                    checkRepeat = number1 + "+" + number2;
                } while (check1.contains(checkRepeat) || check2.contains(checkRepeat));
                check1.add(number1 + "+" + number2);
                check2.add(number2 + "+" + number1);
                result = number1 + number2;
                // 记录题目和答案
                strArrayQ.add("(" + i + ") " + number1 + " + " + number2 + " =");
                strArrayA.add("(" + i + ") " + number1 + " + " + number2 + " = " + result);
            } else if (fuhao ==1) {
                do {
                    // 保证结果不为负数以及没有重复的式子生成
                    number1 = (int)(Math.random()*(GRADE1_MAX)+1);
                    number2 = (int)(Math.random()*(GRADE1_MAX)+1);
                    checkRepeat = number1 + "-" + number2;
                } while (number2 > number1 || check1.contains(checkRepeat));
                check1.add(number1 + "-" + number2);
                result = number1 - number2;
                // 记录题目和答案
                strArrayQ.add("(" + i + ") " + number1 + " - " + number2 + " =");
                strArrayA.add("(" + i + ") " + number1 + " - " + number2 + " = " + result);
            }
        }
    }
    
    /* 生成二年级题目 */
    private static void grade2(int num) {
        /*
         * 1.随机生成运算符，乘法或者除法
         * 2.随机生成第一个运算数，乘法：第二个无要求；除法：余数为0-9
         * 3.判断题目是否重复
         * 4.存入题目数组并计算结果存入答案数组
         */
        int number1, number2 = 0;
        int fuhao = 0;
        int result = 0;
        int yu = 0;
        // 检查重复
        List<String> check1 = new ArrayList<String>();
        List<String> check2 = new ArrayList<String>();
        String checkRepeat = null;
        
        strArrayQ = new ArrayList<String>();
        strArrayA = new ArrayList<String>();
        for (int i = 1; i <= num; i++) {
            fuhao = (int)(Math.random()*2);
            if (fuhao == 0) {
                do {
                    // 保证没有重复的式子生成
                    number1 = (int)(Math.random()*10);
                    number2 = (int)(Math.random()*10);
                    checkRepeat = number1 + "×" + number2;
                } while (check1.contains(checkRepeat) || check2.contains(checkRepeat));
                check1.add(number1 + "×" + number2);
                check2.add(number2 + "×" + number1);
                
                result = number1 * number2;
                // 记录题目和答案
                strArrayQ.add("(" + i + ") " + number1 + " × " + number2 + " =");
                strArrayA.add("(" + i + ") " + number1 + " × " + number2 + " = " + result);
            } else if (fuhao == 1) {
                do {
                    // 保证除数不为0以及没有重复的式子生成
                    number1 = (int)(Math.random()*GRADE2_MAX);
                    number2 = (int)(Math.random()*10);
                    checkRepeat = number1 + "÷" + number2;
                } while (number2 <= (number1 / 10) || number2 == 0 || check1.contains(checkRepeat));
                check1.add(number1 + "÷" + number2);
                result = number1 / number2;
                yu = number1 % number2;
                // 记录题目和答案
                strArrayQ.add("(" + i + ") " + number1 + " ÷ " + number2 + " =");
                if (yu == 0) {
                    strArrayA.add("(" + i + ") " + number1 + " ÷ " + number2 + " = " + result);
                } else {
                    strArrayA.add("(" + i + ") " + number1 + " ÷ " + number2 + " = " + result + "..." + yu);
                }
            }
        }
    }
    
    /* 生成三年级题目 */
    private static void grade3(int num) {
        /*
         * 1.随机生成符号数量:2-4
         * 2.根据符号数量随机生成符号
         * 3.根据加减符号数量生成括号数量：0-2
         * 4.插入括号到合适的位置
         * 5....待续
         * 
         */
    }
    
    /* 输出到out.txt文件 */
    private static void createTxt() throws IOException {
        /*
         * 1.创建指定文件名的文件
         * 2.依次向文件写入内容
         */
        File file = new File(FILENAME);
        file.createNewFile();
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            // 具体写入内容
            for (String s : strArrayQ) {
                fw.write(s);
                fw.write("\r\n");
            }
            fw.write("\r\n");
            for (String s : strArrayA) {
                fw.write(s);
                fw.write("\r\n");
            }
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
