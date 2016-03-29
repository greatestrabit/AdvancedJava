package com.xiaodu.callback.e;

public class ClientRunnable implements IClient, Runnable {
	private final Server server;
	private final String question;
	private final int id;

	public ClientRunnable(final Server server, final String question, final int id) {
		this.server = server;
		this.question = question;
		this.id = id;
	}

	@Override
	public void recvAnswer(final String answer) {
		System.out.println("clinet " + this.id + " got answer: " + answer);
	}

	@Override
	public void run() {
		server.answer(ClientRunnable.this, this.question);
	}

}
