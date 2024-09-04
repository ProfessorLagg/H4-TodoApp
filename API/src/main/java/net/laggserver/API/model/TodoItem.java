package net.laggserver.API.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.ZonedDateTime;

@Entity
@Table(name = "TODOITEMS")
public class TodoItem {
		public enum TaskHateLevel {
				LOVE((byte) 0), LIKE((byte) 1), INDIFFERENT((byte) 2), DISLIKE((byte) 3), HATE((byte) 4);
				public final byte value;

				private TaskHateLevel(byte value) { this.value = value; }
		}

		public enum TaskImportance {
				LOWEST((byte) 0), LOW((byte) 1), MEDIUM((byte) 2), HIGH((byte) 3), HIGHEST((byte) 4);
				public final byte value;

				private TaskImportance(byte value) { this.value = value; }
		}

		public Long CalculateXp() {
				double result = this.duration.toSeconds();
				result *= Math.log(3.0 + ((double) this.hatelevel.value));
				result *= Math.log(3.0 + ((double) this.hatelevel.value));
				return Math.round(result);
		}

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;

		@Column(name = "title")
		public String title;

		@Column(name = "description")
		public String description;

		@Column(name = "deadline")
		public ZonedDateTime deadline;

		@Column(name = "completed")
		public boolean completed;

		@Column(name = "duration")
		public Duration duration;

		@Column(name = "hatelevel")
		public TaskHateLevel hatelevel;

		@Column(name = "importance")
		public TaskImportance importance;

		public long getId() { return id; }

		public void cloneFrom(TodoItem item) {
				this.title = item.title;
				this.description = item.description;
				this.deadline = item.deadline;
				this.completed = item.completed;
				this.duration = item.duration;
				this.hatelevel = item.hatelevel;
				this.importance = item.importance;
		}
}
