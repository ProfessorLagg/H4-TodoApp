package http;

import logging.ILogConsumer;
import logging.LoggingUtils;
import logging.NullLogger;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HttpServer {
		private final ServerSocket serverSocket;
		private final Executor threadPool;
		private final Map<HttpRoute, HttpHandlerFunc> routesMap;
		private final ILogConsumer log;

		private final String LOG_TAG;

		private static HttpResponse getErrorRouteNotFound(HttpRequest request) {
				HttpResponse response = new HttpResponse();
				response.statusCode = HttpResponseCode.NOT_FOUND;
				response.setStringBody("Could not find route " + request.route.toString());
				return response;
		}

		private static HttpResponse getServerError() { return getServerError(Optional.empty()); }

		private static HttpResponse getServerError(Optional<Exception> exception) {
				return getServerError("The server encountered an error parsing the request", exception);
		}

		private static HttpResponse getServerError(String msg, Optional<Exception> exception) {
				HttpResponse response = new HttpResponse();
				response.statusCode = HttpResponseCode.INTERNAL_SERVER_ERROR;
				String body = msg;
				if (!exception.isEmpty()) {
						Exception e = exception.get();
						body += '\n' + LoggingUtils.getStackTrace(e);
				}
				response.setStringBody(body);
				return response;
		}

		public HttpServer(int port) throws IOException {
				this.serverSocket = new ServerSocket(port);
				this.threadPool = Executors.newFixedThreadPool(8);
				this.routesMap = new HashMap<>();
				this.log = new NullLogger();
				this.LOG_TAG = this.getClass().getName() + ':' + port;
		}

		public HttpServer(int port, ILogConsumer logConsumer) throws IOException {
				this.serverSocket = new ServerSocket(port);
				this.threadPool = Executors.newSingleThreadExecutor();
				this.routesMap = new HashMap<>();
				this.log = logConsumer;
				this.LOG_TAG = this.getClass().getName() + ':' + port;
		}

		private class RunnableSocket implements Runnable {
				private final Socket socket;

				public RunnableSocket(final Socket socket) {
						this.socket = socket;
				}

				@Override
				public void run() {
						try (OutputStream outputStream = this.socket.getOutputStream()) {
								try (InputStream inputStream = this.socket.getInputStream()) {
										handleConnection(inputStream, outputStream);
								} catch (Exception e) {
										// TODO return error page
										log.error(this.getClass().getName(), LoggingUtils.getStackTrace(e));
								}

						} catch (Exception e) {
								log.error(this.getClass().getName(), LoggingUtils.getStackTrace(e));
						}
				}
		}

		private Optional<HttpHandlerFunc> matchRoute(final HttpRoute route) {
				for (HttpRoute k : routesMap.keySet()) {
						if (k.matches(route)) {
								return Optional.of(this.routesMap.get(k));
						}
				}
				return Optional.empty();
		}

		private void handleConnection(InputStream inputStream, OutputStream outputStream) {
				try {
						log.debug(LOG_TAG, "reading request bytes");
						byte[] inputBytes = new byte[inputStream.available()];
						inputStream.read(inputBytes);
						log.debug(LOG_TAG, "\n---\n" + new String(inputBytes) + "---\n");

						HttpRequest request = HttpRequest.Parse(new ByteArrayInputStream(inputBytes));
						log.debug(LOG_TAG, "PARSED REQUEST\n---\n" + request.toString() + "---\n");
						Optional<HttpHandlerFunc> handlerFuncOptional = matchRoute(request.route);
						HttpResponse response = null;
						if (handlerFuncOptional.isEmpty()) {
								response = getErrorRouteNotFound(request);
						} else {
								HttpHandlerFunc handlerFunc = handlerFuncOptional.get();
								response = handlerFunc.Handle(request);
						}

						outputStream.write(response.getBytes());
				} catch (Exception e) {
						log.error(LOG_TAG, "Request handling error", e);
						// TODO add setting to exclude stack traces
						HttpResponse response = getServerError("Request handling error", Optional.of(e));
						try { outputStream.write(response.getBytes()); } catch (Exception ignore) { return; }
				}
		}

		/**
		 * Creates an ANY route to the specified route
		 *
		 * @param route       the route to match
		 * @param handlerFunc handler func
		 */
		public void setRoute(String route, HttpHandlerFunc handlerFunc) { this.setRoute(new HttpRoute(route, HttpMethod.ANY), handlerFunc); }

		public void setRoute(HttpRoute route, HttpHandlerFunc handlerFunc) {
				this.routesMap.put(route, handlerFunc);
				this.log.info(LOG_TAG, "Set route = " + route);
		}

		public void run() throws IOException {
				this.log.info(LOG_TAG, "starting HttpServer on " + this.serverSocket.getLocalSocketAddress());
				while (true) {
						Socket clientSocket = this.serverSocket.accept();
						this.log.info(LOG_TAG, "received request from " + clientSocket.getLocalAddress());

						InputStream in = clientSocket.getInputStream();
						OutputStream out = clientSocket.getOutputStream();
						threadPool.execute(() -> handleConnection(in, out));
//						in.close();
//						out.close();
						this.log.info(LOG_TAG, "handled request from " + clientSocket.getLocalAddress());
				}
		}
}
