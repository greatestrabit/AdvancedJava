package com.dgj.ara.client;

import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MsgSendTask implements Runnable {
	private final int serialNo;

	public MsgSendTask(final int serialNo) {
		this.serialNo = serialNo;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(1000));

			String query = "msg=thisisamessage&serial=" + this.serialNo + "&id=" + Config.cliendId;
			byte[] result = Client.mac.doFinal(query.getBytes());

			StringBuilder sb = new StringBuilder();
			for (byte item : result) {
				sb.append(Byte.toString(item));
			}

			String url = "http://localhost:8000/msg?" + "mac=" + sb.toString() + "&" + query;
			Document document = Jsoup.connect(url).timeout(60000).get();
			String response = document.text();

			System.out.println(this.serialNo + "---" + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
