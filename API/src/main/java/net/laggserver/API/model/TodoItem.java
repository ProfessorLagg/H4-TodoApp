package net.laggserver.API.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.ZonedDateTime;

@Entity
@Table(name = "TODOITEMS")
public class TodoItem {
		public enum TaskHateLevel {
				LOVE((byte) 1), LIKE((byte) 2), INDIFFERENT((byte) 3), DISLIKE((byte) 4), HATE((byte) 5);
				public final byte value;

				private TaskHateLevel(byte value) { this.value = value; }
		}

		public enum TaskImportance {
				LOWEST((byte) 1), LOW((byte) 2), MEDIUM((byte) 3), HIGH((byte) 4), HIGHEST((byte) 5);
				public final byte value;

				private TaskImportance(byte value) { this.value = value; }
		}

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
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
