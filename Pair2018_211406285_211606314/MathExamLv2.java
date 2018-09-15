package com.java285.second;

public class MathExamLv2 {
	private Object[] stack;
	private int size;// 元素个数;

	public MathExamLv2() {// 默认长度为10;
		this(10);
	}

	public MathExamLv2(int len) {// 也可以自己设置长度,即容量;
		stack = new Object[len];
	}

	public int size() {// 返回元素个数;
		return size;
	}

	public int capacity() {// 返回数组长度,即容量;
		return stack.length;
	}

	public void push(Object o) {// 入栈;
		size++;
		stack[size - 1] = o;
	}

	public boolean isEmpty() {// 判空;
		return size == 0;
	}

	public Object pop() {// 出栈;
		if (isEmpty()) {// 首先要判空;
			throw new ArrayIndexOutOfBoundsException("不能为空");
		}
		Object o = stack[--size];
		stack[size] = null;
		return o;
	}

	public static void main(String[] args) {
		StackTest Test = new StackTest();
	}
}
