package data;

import java.time.ZonedDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;

import org.json.*;

public class TodoItem implements JSONSerializeable {
		public enum TaskHateLevel {
				HATE("Hate", (short) 2),
				DISLIKE("Dislike", (short) 1),
				INDIFFERENT("Indifferent", (short) 0),
				LIKE("Like", (short) -1),
				LOVE("Love", (short) -2);

				public final String name;
				public final short value;

				private TaskHateLevel(String name, short value) {
						this.name = name;
						this.value = value;
				}
		}

		public enum TaskImportance {
				LOWEST("Very Low", (short) -2),
				LOW("Low", (short) -1),
				MEDIUM("Medium", (short) 0),
				HIGH("High", (short) 1),
				HIGHEST("Very High", (short) 2);

				public final String name;
				public final short value;

				private TaskImportance(String name, short value) {
						this.name = name;
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

		public long calculateXP() {
				return this.duration.toSeconds() * this.hateLevel.value * this.importance.value;
		}

		public int getId(){
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
