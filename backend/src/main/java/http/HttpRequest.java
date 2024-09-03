package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
		public final HttpRoute route;
		public final Map<String, String> headers;
		public final Map<String, String> query;
		public final byte[] body;

		public String getBodyString() { return this.getBodyString(StandardCharsets.UTF_8); }

		public String getBodyString(Charset charset) { return new String(this.body, charset); }

		private HttpRequest(final HttpRoute route, final byte[] body, final Map<String, String> headers, final Map<String, String> queryStrings) {
				this.route = route;
				this.body = body;
				this.headers = headers;
				this.query = queryStrings;
		}


		public static HttpRequest Parse(InputStream inputStream) throws IOException {
				HttpRoute route;
				HashMap<String, String> headers = new HashMap<>();
				HashMap<String, String> query = new HashMap<>();
				byte[] body = new byte[0];
				try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream)) {
						try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
								String line = bufferedReader.readLine();
								// Parse Method
								int spaceIndex = line.indexOf(' ');
								String methodString = line.substring(0, spaceIndex).trim();
								HttpMethod method = HttpMethod.valueOf(methodString);
								line = line.substring(spaceIndex).trim();

								spaceIndex = line.indexOf(' ');
								String routeString = line.substring(0, spaceIndex);
								int queryIndex = routeString.indexOf('?');
								if (queryIndex > 0) {
										String queryString = routeString.substring(queryIndex + 1);
										String[] queryFields = queryString.split("&");
										for (String queryField : queryFields) {
												int sepIdx = queryField.indexOf('=');
												String k = queryField.substring(0, sepIdx);
												String v = queryField.substring(sepIdx + 1);
												query.put(k, v);
										}

										routeString = routeString.substring(0, queryIndex).trim();
										if (routeString.charAt(routeString.length() - 1) == '/') {
												routeString = routeString.substring(0, routeString.length() - 1);
										}

								}

								route = new HttpRoute(routeString, method);
								// Parse headers
								line = bufferedReader.readLine();
								while (!HttpUtils.IsNullOrWhiteSpace(line)) {
										int sepIndex = line.indexOf(':');
										String k = line.substring(0, sepIndex).trim();
										String v = line.substring(sepIndex + 1).trim();
										headers.put(k, v);
										line = bufferedReader.readLine();
								}
								// Read body
								body = inputStream.readNBytes(Math.max(0, inputStream.available()));
						}
				}

				return new HttpRequest(route, body, headers, query);
		}

		@Override
		public String toString() {
				StringBuilder sb = new StringBuilder();
				String routeString = this.route.toString();
				sb.append(this.route.toString());
				if (!this.query.isEmpty()) {
						char prependChar = '?';
						for (String k : this.query.keySet()) {
								sb.append(prependChar);
								if (prependChar == '?') { prependChar = '&'; }
								sb.append(k);
								sb.append('=');
								sb.append(this.query.get(k));
						}
				}
				sb.append(" HTTP/1.1\n");
				for (String k : headers.keySet()) {
						sb.append(k);
						sb.append(": ");
						sb.append(headers.get(k));
						sb.append("\n");
				}
				sb.append("\n");
				sb.append(new String(this.body));
				return sb.toString();
		}
}
