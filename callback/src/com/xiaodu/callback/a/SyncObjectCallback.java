package com.xiaodu.callback.a;

class A {
	/**
	 * 提出问题
	 * @author xiaodu.email@gmail.com
	 * @param b
	 * @param question
	 */
	public void ask(final B b, final String question) {
		b.answer(this, question);
	}

	/**
	 * 处理结果
	 * @author xiaodu.email@gmail.com
	 * @param answer
	 */
	public void processResult(final String answer) {
		System.out.println(answer);
	}
}

class B {
	/**
	 * 计算结果
	 * @author xiaodu.email@gmail.com
	 * @param a
	 * @param question
	 */
	public void answer(final A a, final String question) {
		if (question.equals("What is the answer to life, the universe and everything?")) {
			a.processResult("42");
		}
	}
}

/**
 * 相互调用
 * @author xiaodu.email@gmail.com
 *
 */
public class SyncObjectCallback {
	public static void main(final String[] args) {
		B b = new B();
		A a = new A();

		a.ask(b, "What is the answer to life, the universe and everything?");
	}
}