
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TS {
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("ÊäÈë²ÎÊı(-n x -grade y):");
		String str=input.nextLine();
		String[] args2=str.split(" ");
		
		new MathExam().main(args2);
	}
}
