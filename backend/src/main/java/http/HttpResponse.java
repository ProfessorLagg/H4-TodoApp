package http;

import org.json.JSONObject;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
		private final String HTTP_VERSION = "HTTP/1.1";

		private final Map<String, String> addtionalHeaders = new HashMap<>();
		/**
		 * Defaults to 200 ok
		 */
		public HttpResponseCode statusCode;

		/**
		 * defaults to text/plain
		 */
		public String contentType;

		public byte[] body;

		public void setStringBody(String str) {
				this.body = str.getBytes(StandardCharsets.UTF_8);
				this.contentType = "text/plain";
		}

		public void setJsonBody(JSONObject jsonObject) {
				String jsonString = jsonObject.toString();
				this.setStringBody(jsonString);
				this.contentType = "application/json";
		}

		public HttpResponse() {
				this.statusCode = HttpResponseCode.OK;
				this.contentType = "text/plain";
		}

		private String getHeaderSection() {
				StringBuilder sb = new StringBuilder();

				sb.append(HTTP_VERSION);
				sb.append(" ");
				sb.append(this.statusCode.code);
				sb.append(" ");
				sb.append(this.statusCode.name);
				sb.append("\r\n");

//				sb.append("Server: LaggHttpServer\n");

				sb.append("Date: ");
				sb.append(DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now(ZoneOffset.UTC)));
				sb.append("\r\n");

				sb.append("Content-Length: ");
				sb.append(this.body.length);
				sb.append("\r\n");

				sb.append("Content-Type: ");
				sb.append(this.contentType);
				if (this.contentType.startsWith("text/")) { sb.append("; charset=utf-8"); }
				sb.append("\r\n");

				sb.append("Connection: close");
				sb.append("\r\n");
				// TODO Additional Headers

				sb.append("\r\n"); // the empty line before the body
				return sb.toString();
		}

		public void write(OutputStream outputStream) {
		}

		public byte[] getBytes() {
				byte[] headerbytes = this.getHeaderSection().getBytes();
				byte[] responsBytes = HttpUtils.concatArray(headerbytes, this.body);
				return responsBytes;
		}
}
