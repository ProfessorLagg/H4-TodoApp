import data.TodoItem;
import http.*;
import logging.ConsoleLogger;
import logging.LoggingUtils;
import org.json.JSONObject;

import java.time.*;

public class Program {
		private static HttpServer server;

		public static void main(String[] args) {
				ConsoleLogger logger = new ConsoleLogger();
				try {
						server = new HttpServer(9999, logger);
						server.setRoute("/debug/get", Program::DebugHandler);
						server.setRoute("/debug/todoitem", Program::FakeTodoItemHandler);
						server.run();
				} catch (Exception e) {
						logger.panic("Startup", "Could not start server\n" + LoggingUtils.getStackTrace(e));
				}
		}

		public static HttpResponse DebugHandler(HttpRequest request) {
				HttpResponse response = new HttpResponse();
				response.setStringBody("replying to request:\n" + request.toString());
				return response;
		}

		public static HttpResponse FakeTodoItemHandler(HttpRequest request) {
				TodoItem todoItem = new TodoItem();
				todoItem.title = "Fake it until you make it";
				todoItem.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque cursus consectetur augue, at sagittis metus volutpat id. Donec erat mauris, viverra nec lobortis at, molestie ac metus. Curabitur pellentesque at magna sit amet ultricies. Phasellus ultrices diam nec risus mattis, ac pulvinar velit lacinia. Praesent commodo non mi ut suscipit. Vivamus vitae ex vehicula turpis varius commodo. Suspendisse velit turpis, finibus vitae ultrices in, posuere a tortor. Nulla ultrices neque sit amet pellentesque luctus.";
				todoItem.deadline = ZonedDateTime.now();
				todoItem.completed = false;
				todoItem.duration = Duration.ofMinutes(10);
				todoItem.hateLevel = TodoItem.TaskHateLevel.DISLIKE;
				todoItem.importance = TodoItem.TaskImportance.HIGHEST;

				JSONObject jsonObject = todoItem.toJSONObject();

				HttpResponse response = new HttpResponse();
				response.setJsonBody(jsonObject);
				return response;
		}
}
