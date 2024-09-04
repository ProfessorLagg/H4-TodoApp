package com.laggserver.rpgtodo.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.Duration;
import java.time.ZonedDateTime;

public class TodoItem implements JSONSerializeable {
	public enum TaskHateLevel {
		LOVE((byte) 0), LIKE((byte) 1), INDIFFERENT((byte) 2), DISLIKE((byte) 3), HATE((byte) 4);
		public final byte value;

		private TaskHateLevel(byte value) {
			this.value = value;
		}
	}

	public enum TaskImportance {
		LOWEST((byte) 0), LOW((byte) 1), MEDIUM((byte) 2), HIGH((byte) 3), HIGHEST((byte) 4);
		public final byte value;

		private TaskImportance(byte value) {
			this.value = value;
		}
	}

	private long id;
	public String title, description;
	public ZonedDateTime deadline;
	public boolean completed;
	public Duration duration;
	public TaskHateLevel hatelevel;
	public TaskImportance importance;

	public long getId() {
		return id;
	}

	public TodoItem() {
		this.title = "";
		this.description = "";
		this.completed = false;
		this.duration = Duration.ofHours(0);
		this.hatelevel = TaskHateLevel.INDIFFERENT;
		this.importance = TaskImportance.LOW;
	}

	@Override
	public JSONObject toJSON() throws JSONException {
		JSONObject result = new JSONObject();
		result.put("title", this.title);
		result.put("description", this.description);
		result.put("completed", this.completed);
		result.put("duration", this.duration);
		result.put("hatelevel", this.hatelevel);
		result.put("importance", this.importance);
		return result;
	}

	public void SendUpdate() {
		try {
			JSONObject json = this.toJSON();
			String jsonString = json.toString();

		} catch (Exception e) {
			Log.e(this.getClass().getName(), e);
		}
	}

}
