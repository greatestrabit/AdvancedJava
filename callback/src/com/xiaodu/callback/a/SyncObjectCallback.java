package com.xiaodu.callback.a;

class A {
	public void ask(final B b, final String question) {
		b.answer(this, question);
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

public class SyncObjectCallback {
	public static void main(final String[] args) {
		B b = new B();
		A a = new A();

		a.ask(b, "What is the answer to life, the universe and everything?");
	}
}