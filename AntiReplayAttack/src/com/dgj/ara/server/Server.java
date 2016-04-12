package com.dgj.ara.server;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpServer;

public class Server {
	public static Map<String, ClientConfig> ccMap = new HashMap<String, ClientConfig>();

	static {
		ClientConfig cc = new ClientConfig("qnscAdgRlkIhAUPY44oiexBKtQbGY0orf7OV1I50", "HmacSHA1");
		ccMap.put("123456", cc);
	}

	public static void main(final String[] args) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
			server.createContext("/init", new InitHttpHandler());
			server.createContext("/msg", new MsgHttpHandler());
			server.setExecutor(null); // creates a default executor
			server.start();
			System.out.println("server started.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
