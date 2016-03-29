package com.xiaodu.callback.b;

class A {
	private final B b;

	public A(final B b) {
		this.b = b;
	}

	public void ask(final String question) {
		this.b.answer(this, question);
	}

	public void processResult(final String answer) {
		System.out.println(answer);
	}
}

class B {
	public void answer(final A a, final String question) {
		if (question.equals("What is the answer to life, the universe and everything?")) {
			a.processResult("42");
		}
	}
}

/**
 * 面向对象的相互调用
 * @author xiaodu.email@gmail.com
 *
 */
public class SyncOOCallback {
	public static void main(final String[] args) {
		B b = new B();
		A a = new A(b);
		a.ask("What is the answer to life, the universe and everything?");
	}
}