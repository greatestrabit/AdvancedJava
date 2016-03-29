package com.xiaodu.callback.c;

import java.util.Random;

/**
 * 相应请求者,即提供服务者
 * @author xiaodu.email@gmail.com
 *
 */
public class Server {
	public void answer(final IClient client, final String question) {
		if (question.equals("What is the answer to life, the universe and everything?")) {
			calclating();
			client.recvAnswer("42");
		}
	}

	private void calclating() {
		try {
			Thread.sleep(new Random().nextInt(5000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
