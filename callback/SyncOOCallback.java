package com.dgj.test.callbackblog;

class C {
	private final D d;

	public C(final D d) {
		this.d = d;
	}

	public void ask(final String question) {
		this.d.answer(this, question);
	}

	public void processResult(final String answer) {
		System.out.println(answer);
	}
}

class D {
	public void answer(final C c, final String question) {
		if (question.equals("What is the answer to life, the universe and everything?")) {
			c.processResult("42");
		}
	}
}

public class SyncOOCallback {
	public static void main(final String[] args) {
		D d = new D();
		C c = new C(d);
		c.ask("What is the answer to life, the universe and everything?");
	}
}