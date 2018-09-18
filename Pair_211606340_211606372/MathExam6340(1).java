

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class MathExam6340 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        int grade = input.nextInt();
        if(grade==1 || grade==2){
            try {
                generateExam(number,grade);
                write();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(grade==3){
            creatFourOperations(number,grade);
        }else {
            System.out.println("对不起，其他年级暂无数据！");
        }
    }

    /**
     *  随机生成四则运算式
     */
    static ScriptEngine js = new ScriptEngineManager().getEngineByName("JavaScript");
    public static void creatFourOperations(int number,int grade){
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i=0;i<number;i++){
            //运算符在2～4个,运算数3～5个
            Random random = new Random();
            int num = random.nextInt(3)+3;

            String arr = "";
            for (int j=0;j<num;j++){
                int number1 = getNumber();
                String operator = String.valueOf(getOperator());
                arr += number1 + operator;
            }
            arr =arr.substring(0,arr.length()-1);
            //将arr转化为数组
            char[] chars = arr.toCharArray();
            //存储运算符
            ArrayList<String> arrayList1 = new ArrayList<>();
            //存储运算数
            ArrayList<String> arrayList2 = new ArrayList<>();
            //存储运算式
            ArrayList<String> arrayList3 = new ArrayList<>();
            //存储运算符
            for (int j=0;j<chars.length;j++){
                if(chars[j]=='+'||chars[j]=='-'||chars[j]=='*'||chars[j]=='/'){
                    String arrString=String.valueOf(chars[j]);
                    chars[j]=',';
                    arrayList1.add(arrString);
                }
            }
            //存储运算数
            String str = new String(chars);
            String[] str1 = str.split(",");
            for (int k=0 ;k<str1.length;k++){
                arrayList2.add(str1[k]);
            }

            //生成运算式
            String[] array1 = arrayList1.toArray(new String[arrayList1.size()]);
            String[] array2 = arrayList2.toArray(new String[arrayList2.size()]);

            for (int m=0; m<array1.length;m++){
                arrayList3.add(array2[m]);
                arrayList3.add(array1[m]);
            }
            arrayList3.add(array2[array2.length-1]);

            //运算式
            String[] array = arrayList3.toArray(new String[arrayList2.size()]);
            arr="";//初始化为空
            checkArith(array);
            for (int n=0; n<array.length; n++){
                arr+=array[n];
            }
            try {
                checkArithQuestion(array,arr);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            arr="";
            for(int t=0;t<array.length;t++) {
                arr+=array[t];
            }
            arrayList.add(arr);
        }

        System.out.println("java MathExam -n "+number+" -grade "+grade);
        int i =0,j=0;
        for (String string : arrayList) {
            i++;
            System.out.println("( "+i+" ) "+string);
        }
        System.out.println("===========答案是：=============");
        for (String string : arrayList) {
           j++;
            try {
                System.out.println("( "+j+" ) "+string + "=" + js.eval(string));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
        try {
            filewriter(arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //产生随机数
    public static int getNumber(){
        Random random = new Random();
        int num  = random.nextInt(1001);
        return num;
    }

    //随机产生运算符号
    public static char getOperator(){
        char operator = 0;
        Random random = new Random();
        int i = random.nextInt(4);
        switch(i){
            case 0:
                operator = '+';
                break;
            case 1:
                operator = '-';
                break;
            case 2:
                operator = '*';
                break;
            case 3:
                operator = '/';
                break;
        }
        return operator;
    }

    //判断是否产生负数与小数
    public static void  checkArith(String arr[]){
        for(int i=0;i<arr.length;i++){
            //判断是否产生负数
            if(arr[i].contains("-")){
                if(Integer.valueOf(arr[i-1])<Integer.valueOf(arr[i+1])){
                    arr[i]="+";
                }
            }
            //判断是否产生小数
            if(arr[i].contains("/")){
                int a = Integer.valueOf(arr[i-1]);
                int b = Integer.valueOf(arr[i+1]);
                int x = a%b;
                if(x!=0){
                    arr[i]="+";
                }
            }
        }
    }

    //判断结果是否产生小数
    public static void  checkArithQuestion(String arr[],String arrString) throws ScriptException {
        for(int i=0;i<arr.length;i++){
            String str = js.eval(arrString).toString();
            if(Integer.valueOf(str)<0) {
                if(arr[i].contains("-")){
                    arr[i]="+";
                }
            }
        }
    }

    public static void filewriter(ArrayList<String> arithList) throws IOException {
        //封装存储位置
        BufferedWriter BufferedWriterbw = new BufferedWriter(new FileWriter("out.txt"));
        BufferedWriterbw.newLine();
        for(String s : arithList){
            //写数据
            BufferedWriterbw.write(s);
            BufferedWriterbw.newLine();
            BufferedWriterbw.flush();
        }
        BufferedWriterbw.write("================== 答案=================");
        BufferedWriterbw.newLine();
        BufferedWriterbw.flush();
        for(String s : arithList){
            //写数据
            try {
                BufferedWriterbw.write(s+"="+js.eval(s));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            BufferedWriterbw.newLine();
            BufferedWriterbw.flush();
        }
    }


    static StringBuffer questions = new StringBuffer();
    static StringBuffer answers = new StringBuffer();

    public static void generateExam(int number , int grade){
        int[] num1 = new int[number];
        int[] num2 = new int[number];
        int[] sum = new int[number];
        String[] so = new String[number] ;
        Random random = new Random();
        String[] ch = {"+", "-", "*", "/"};

        int remainder = 0;
        for (int i=0;i<number;i++){
            num1[i] = random.nextInt(100);
            num2[i] = random.nextInt(100);
            int subScript = (1 == grade) ?  (random.nextInt(2)) : (random.nextInt(4));
            String symbol = ch[subScript];
            so[i] = symbol;
            switch (symbol){
                case "+":
                    sum[i]=num1[i]+num2[i];
                    break;
                case "-":
                    if(num1[i]<num2[i]){
                        int temp = num1[i];
                        num1[i]=num2[i];
                        num2[i]=temp;
                    }
                    sum[i]=num1[i]-num2[i];
                    break;
                case "*":
                    sum[i]=num1[i]*num2[i];
                    break;
                case "/":
                    while (num2[i] == 0) {
                        num2[i] = (int) (Math.random() * 100);
                    }
                    sum[i] = num1[i] / num2[i];
                    if (num1[i] % num2[i] == 0) {
                        break;
                    } else {
                        if (num1[i] < num2[i]) {
                            remainder = num1[i];
                        } else if (num1[i] >  num2[i]) {
                            remainder = num1[i] %  num2[i];
                        }
                    }
                    break;
                default:
            }
            questions.append("("+(i+1)+")"+" "+num1[i]+" "+symbol+" "+num2[i]+ "\r\n");
            System.out.println("("+(i+1)+")"+" "+num1[i]+" "+symbol+" "+num2[i]);

        }
        questions.append("========输出所有答案========");
        System.out.println("========输出所有答案========");
        for (int i=0;i<number;i++) {
            if (num2[i] != 0 && so[i]=="/" && remainder != 0) {
                answers.append("(" + (i + 1) + ")" + " " + num1[i] + " " + so[i] + " " + num2[i] + " = " + sum[i]
                        + "......" + remainder + "\r\n");
                System.out.println("(" + (i + 1) + ")" + " " + num1[i] + " " + so[i] + " " + num2[i] + " = " + sum[i]
                        + "......" + remainder);
            } else {
                answers.append("(" + (i + 1) + ")" + " " + num1[i] + " " + so[i] + " " + num2[i] + " = " + sum[i] + "\r\n");
                System.out.println("(" + (i + 1) + ")" + " " + num1[i] + " " + so[i] + " " + num2[i] + " = " + sum[i]);
            }
        }
    }

    public static void write() throws IOException {
        File file= new File("out.txt");
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            file.createNewFile();
        }
        OutputStream out = new FileOutputStream(file);
        byte[] question=questions.toString().getBytes();
        byte[] answer=answers.toString().getBytes();
        out.write(question);
        out.write(System.lineSeparator().getBytes());
        out.write(answer);
        out.close();
    }



}
