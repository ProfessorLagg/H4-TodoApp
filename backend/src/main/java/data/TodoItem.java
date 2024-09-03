package data;

import java.time.ZonedDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;

import org.json.*;

public class TodoItem implements JSONSerializeable {
		public enum TaskHateLevel {
				LOVE((byte) 1),
				LIKE((byte) 2),
				INDIFFERENT((byte) 3),
				DISLIKE((byte) 4),
				HATE((byte) 5);


				public final byte value;

				private TaskHateLevel(byte value) {
						this.value = value;
				}
		}

		public enum TaskImportance {
				LOWEST((byte) 1),
				LOW((byte) 2),
				MEDIUM((byte) 3),
				HIGH((byte) 4),
				HIGHEST((byte) 5);

				public final byte value;

				private TaskImportance(byte value) {
						this.value = value;
				}
		}

		public String title;
		public String description;
		public ZonedDateTime deadline;
		public boolean completed;
		public Duration duration;
		public TaskHateLevel hateLevel;
		public TaskImportance importance;
		public Duration recurrence;

		public long calculateXP() {
				return this.duration.toSeconds() * this.hateLevel.value * this.importance.value;
		}

		public int getId() {
				// TODO database stuff
				return -1;
		}


		@Override
		public JSONObject toJSONObject() {
				JSONObject result = new JSONObject();

				result.put("title", this.title == null ? "" : this.title);
				result.put("description", this.description == null ? "" : this.description);
				result.put("deadline", this.deadline.format(DateTimeFormatter.ISO_DATE_TIME));
				result.put("completed", this.completed);
				result.put("duration", this.duration.toSeconds());
				result.put("hateLevel", this.hateLevel.value);
				result.put("importance", this.importance.value);

				result.put("xp", this.calculateXP());
				return result;
		}
}
