package com.laggserver.rpgtodo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.create), (v, insets) -> {
			Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
			return insets;
		});
		this.setTheme(R.style.Theme_RPGTODO);
		this.init();
	}

	private ConstraintLayout currentPageWrap;

	private void init() {

		initNav();
		this.currentPageWrap = findViewById(R.id.currentPageWrap);
	}

	private View navMenuWrap;
	private NavigationView navMenu;
	private View navHeader;
	private View buttonShowNav;
	private View buttonHideNav;

	private MenuItem menuItemToday;
	private MenuItem menuItemOverview;
	private MenuItem menuItemCreate;

	private void initNav() {
		this.navMenuWrap = findViewById(R.id.navMenuWrap);
		this.navMenu = findViewById(R.id.navMenu);
		this.navHeader = navMenu.getHeaderView(0);
		this.buttonShowNav = findViewById(R.id.buttonShowNav);
		this.buttonHideNav = navHeader.findViewById(R.id.buttonHideNav);


		this.menuItemToday = navMenu.getMenu().findItem(R.id.menuItemToday);
		this.menuItemOverview = navMenu.getMenu().findItem(R.id.menuItemOverview);
		this.menuItemCreate = navMenu.getMenu().findItem(R.id.menuItemCreate);

		Intent createIntent = new Intent(this, CreateItemActivity.class);
		menuItemCreate.setIntent(createIntent);
		this.buttonShowNav.setOnClickListener((View view) -> showNav());
		this.buttonHideNav.setOnClickListener((View view) -> hideNav());
		this.buttonHideNav.callOnClick();

	}

	private static ArrayList<MenuItem> getMenuItems(NavigationView navigationView) {
		ArrayList<MenuItem> menuItems = new ArrayList<>();
		int childCount = navigationView.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = navigationView.getChildAt(i);
			if (child instanceof MenuItem) {
				menuItems.add((MenuItem) child);
			}
		}
		return menuItems;
	}

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
}