package com.xiaodu.callback.c;

/**
 * 发出请求者,同时要处理请求结果
 * @author xiaodu.email@gmail.com
 *
 */
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
