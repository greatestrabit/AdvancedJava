package com.dgj.ara.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Client {
	private static int serialNo;
	public static Mac mac;
	private static ExecutorService service = Executors.newFixedThreadPool(5);

	private static void init() {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(Config.secretKey.getBytes(), Config.algrithm);
			mac = Mac.getInstance(Config.algrithm);
			mac.init(keySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) {
		init();

		try {
			String url = "http://localhost:8000/init?id=" + Config.cliendId;
			Document document = Jsoup.connect(url).timeout(60000).get();
			String response = document.text();

			if (response != null && !response.equals("error")) {
				serialNo = Integer.parseInt(response);

				while (true) {
					service.execute(new MsgSendTask(serialNo));

					serialNo++;

					if (serialNo % 10 == 0) {
						Thread.sleep(2000);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
