package com.dgj.ara.server;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class InitHttpHandler implements HttpHandler {

	@Override
	public void handle(final HttpExchange request) throws IOException {
		String paramString = request.getRequestURI().getQuery();

		if (paramString != null) {
			Map<String, String> params = Util.queryToMap(paramString);
			if (params.containsKey("id")) {
				ClientConfig cc = Server.ccMap.get(params.get("id"));

				String response = "";
				if (cc == null) {
					response = "error";
				} else {
					response = Integer.toString(cc.getFirst());
				}

				request.sendResponseHeaders(200, response.length());
				OutputStream os = request.getResponseBody();
				os.write(response.getBytes());
				os.close();
			}
		}
	}

}
