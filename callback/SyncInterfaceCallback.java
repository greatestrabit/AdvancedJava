package com.dgj.test.callbackblog;

public class SyncInterfaceCallback {
	private static void innerMain() {
		Server server = new Server();
		server.answer(new IClient() {
			@Override
			public void recvAnswer(final String answer) {
				System.out.println(answer);
			}
		}, "What is the answer to life, the universe and everything?");
	}

	public static void main(final String[] args) {
		Server server = new Server();
		ClientSync client = new ClientSync(server);
		client.ask("What is the answer to life, the universe and everything?");

		innerMain();
	}
}
