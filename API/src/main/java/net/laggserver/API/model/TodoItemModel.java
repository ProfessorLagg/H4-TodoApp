package net.laggserver.API.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.ZonedDateTime;

@Entity
@Table(name = "TodoItems")
public class TodoItemModel {
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

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;

		@Column(name = "title")
		private String title;
		@Column(name = "description")
		private String description;

		@Column(name = "deadline")
		private ZonedDateTime deadline;

		@Column(name = "completed")
		private boolean completed;

		@Column(name = "active")
		private boolean active;

		@Column(name = "duration")
		private Duration duration;

		@Column(name = "hatelevel")
		private TaskHateLevel hatelevel;

		@Column(name = "importance")
		private TaskImportance importance;

//		@Column(name = "recurrence")
//		private String recurrence;
}
