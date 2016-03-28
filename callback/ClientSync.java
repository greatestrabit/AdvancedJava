package com.dgj.test.callbackblog;

public class ClientSync implements IClient {
	private final Server server;

	public ClientSync(final Server server) {
		this.server = server;
	}

	public void ask(final String question) {
		this.server.answer(this, question);
	}

	@Override
	public void recvAnswer(final String answer) {
		System.out.println(answer);
	}

}
