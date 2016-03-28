package com.dgj.test.callbackblog;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadpoolCallback {
	public static void main(final String[] args) {
		ExecutorService es = Executors.newCachedThreadPool();

		Server server = new Server();

		for (int i = 0; i < 100; i++) {
			ClientRunnable cr = new ClientRunnable(server, "What is the answer to life, the universe and everything?",
					i);
			es.execute(cr);
			System.out.println("client " + i + " asked !");
		}

		es.shutdown();
	}
}
