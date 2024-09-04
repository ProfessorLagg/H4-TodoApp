package com.laggserver.rpgtodo;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.Duration;

public class CreateItemActivity extends AppCompatActivity {
	private TodoItem todoItem;

	private static final int TIME_DIALOG_ID = 666;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_item);
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.create), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});
		this.setTheme(R.style.Theme_RPGTODO);

		this.todoItem = new TodoItem();
		init();
	}

	private View buttonDuration;
	private TimePickerDialog durationPickerDialog;

	void setDuration(int selectedHour, int selectedMinute) {
		this.todoItem.duration = Duration.ofHours(selectedHour).plusMinutes(selectedMinute);
		TextView durationTextHour = findViewById(R.id.durationTextHour);
		durationTextHour.setText(String.format("%dh", selectedHour));

		TextView durationTextMinute = findViewById(R.id.durationTextMinute);
		durationTextMinute.setText(String.format("%dh", selectedMinute));
	}

	TimePickerDialog.OnTimeSetListener durationOnTimeSet = new TimePickerDialog.OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker timePicker, int hour, int minute) {
			setDuration(hour, minute);
		}
	};

	private void init() {
		this.buttonDuration = findViewById(R.id.buttonDuration);
		this.durationPickerDialog = new TimePickerDialog(
				this,
				durationOnTimeSet,
				0, 10, true);
		this.buttonDuration.setOnClickListener((v) -> durationPickerDialog.show());
		this.setDuration(0, 10);

	}
}