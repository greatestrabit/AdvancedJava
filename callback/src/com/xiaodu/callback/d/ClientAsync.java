package com.xiaodu.callback.d;

public class ClientAsync implements IClient {
	private final Server server;

	public ClientAsync(final Server server) {
		this.server = server;
	}

	public void ask(final String question) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				server.answer(ClientAsync.this, question);
			}
		}).start();
	}

	@Override
	public void recvAnswer(final String answer) {
		System.out.println(answer);
	}

}
