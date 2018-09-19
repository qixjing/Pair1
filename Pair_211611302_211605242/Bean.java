
import java.util.Random;

public class Bean {
	private int count = 1; // 题号，初始为1
	private String symbol = null; // 题目的运算符号
	private int numberA = 0;
	private int numberB = 0;
	private int result = 0; // 运算结果
	private int mod = 0; // 除法运算中的余数

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getNumberA() {
		return numberA;
	}

	public void setNumberA(int numberA) {
		this.numberA = numberA;
	}

	public int getNumberB() {
		return numberB;
	}

	public void setNumberB(int numberB) {
		this.numberB = numberB;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}


}
