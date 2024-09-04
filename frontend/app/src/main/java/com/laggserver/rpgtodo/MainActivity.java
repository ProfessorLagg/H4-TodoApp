package com.laggserver.rpgtodo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});
		this.init();
	}

	private void init() {
		initNav();
		
	}

	private View navMenuWrap;
	private NavigationView navMenu;
	private View navHeader;
	private View buttonShowNav;
	private View buttonHideNav;

	private void showNav() {
		try {
			navMenuWrap.setVisibility(View.VISIBLE);
			buttonShowNav.setVisibility(View.GONE);
			Log.d(this.getClass().getName(), "showNav()");
		} catch (Exception e) {
			Log.e(this.getClass().getName(), "Could not show nav menu", e);
		}
	}

	private void hideNav() {
		try {
			navMenuWrap.setVisibility(View.GONE);
			buttonShowNav.setVisibility(View.VISIBLE);
			Log.d(this.getClass().getName(), "hideNav()");
		} catch (Exception e) {
			Log.e(this.getClass().getName(), "Could not hide nav menu", e);
		}
	}

	private void initNav() {
		navMenuWrap = findViewById(R.id.navMenuWrap);
		navMenu = findViewById(R.id.navMenu);
		navHeader = navMenu.getHeaderView(0);
		buttonShowNav = findViewById(R.id.buttonShowNav);
		buttonHideNav = navHeader.findViewById(R.id.buttonHideNav);


		buttonShowNav.setOnClickListener((View view) -> showNav());
		buttonHideNav.setOnClickListener((View view) -> hideNav());
		hideNav();
	}


}