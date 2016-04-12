package com.dgj.ara.server;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MsgHttpHandler implements HttpHandler {

	@Override
	public void handle(final HttpExchange request) throws IOException {
		String paramString = request.getRequestURI().getQuery();

		if (paramString != null) {
			String response = "";

			Map<String, String> params = Util.queryToMap(paramString);
			ClientConfig cc = Server.ccMap.get(params.get("id"));
			if (cc != null) {
				int index = paramString.indexOf("&");
				String queryString = paramString.substring(index + 1, paramString.length());

				byte[] result = cc.getMac().doFinal(queryString.getBytes());
				StringBuilder sb = new StringBuilder();
				for (byte item : result) {
					sb.append(Byte.toString(item));
				}

				if (sb.toString().equals(params.get("mac"))) {
					int serialNo = Integer.parseInt(params.get("serial"));
					boolean success = cc.checkSerialNo(serialNo);
					if (success) {
						response = "new message got : " + params.get("msg");
					} else {
						response = "error param : serial";
					}
				} else {
					response = "error param : mac";
				}
			} else {
				response = "client does not exist";
			}

			request.sendResponseHeaders(200, response.length());
			OutputStream os = request.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}

}
