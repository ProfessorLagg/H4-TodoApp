package com.laggserver.rpgtodo;


import java.time.ZonedDateTime;
import java.time.Duration;

public class TodoItem {
	public enum TaskHateLevel {
		HATE("Hate", (short) -2),
		DISLIKE("Dislike", (short) -1),
		INDIFFERENT("Indifferent", (short) 0),
		LIKE("Like", (short) 1),
		LOVE("Love", (short) 2);

		private TaskHateLevel(String name, short value) {
		}
	}

	public enum TaskImportance {
		LOWEST("Very Low", (short) -2),
		LOW("Low", (short) -1),
		MEDIUM("Medium", (short) 0),
		HIGH("High", (short) 1),
		HIGHEST("Very High", (short) 2);

		private TaskImportance(String name, short value) {
		}
	}

	public String name;
	public String description;
	public ZonedDateTime timestamp;
	public boolean hasTime;
	public Duration duration;
	public TaskHateLevel hateLevel;
	public TaskImportance importance;
}

