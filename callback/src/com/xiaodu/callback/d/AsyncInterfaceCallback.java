package com.xiaodu.callback.d;

/**
 * 基于接口的异步回调,每次建立新的线程
 * @author xiaodu.email@gmail.com
 *
 */
public class AsyncInterfaceCallback {
	/**
	 * 使用内部类的实现方式,此处可见回调地狱
	 * @author xiaodu.email@gmail.com
	 */
	private static void innerMain() {
		Server server = new Server();

		new Thread(new Runnable() {
			@Override
			public void run() {
				server.answer(new IClient() {
					@Override
					public void recvAnswer(final String answer) {
						System.out.println(answer);
					}
				}, "What is the answer to life, the universe and everything?");
			}
		}).start();
		System.out.println("asked ! waiting for the answer...");
	}

	public static void main(final String[] args) {
		Server server = new Server();
		ClientAsync client = new ClientAsync(server);
		client.ask("What is the answer to life, the universe and everything?");
		System.out.println("asked ! waiting for the answer...");

		innerMain();
	}
}
