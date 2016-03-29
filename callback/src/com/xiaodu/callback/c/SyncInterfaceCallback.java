package com.xiaodu.callback.c;

/**
 * 面向接口的同步回调
 * @author xiaodu.email@gmail.com
 *
 */
public class SyncInterfaceCallback {
	/**
	 * 使用内部类来实现的方式
	 * @author xiaodu.email@gmail.com
	 */
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
