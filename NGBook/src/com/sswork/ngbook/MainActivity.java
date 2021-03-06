package com.sswork.ngbook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends Activity {
	static void printf(String msg) {
		Log.i("mainActivity", msg);
	}

	private SlidingMenu menu = null;
	private Menu menu_view = null;
	private BookList book_list = null;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		printf("oncreate");
		setContentView(R.layout.main);
		initList();
		initMenu();
	}
 @Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	printf("on resume");
	book_list.initView();
}
	@SuppressLint("NewApi")
	private void initList() {
		printf("initlist");
		book_list = new BookList();
		getFragmentManager().beginTransaction()
				.add(R.id.frame_layout_main, book_list).commit();
	}

	private void initMenu() {
		printf("initMenu");
		menu = new SlidingMenu(this);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu_view = new Menu(this);
		menu.setMenu(menu_view);
	}

	@Override
	public void onBackPressed() {
		printf("onbackpressed");
		if (menu.isMenuShowing()) {
			menu.showContent();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		printf("onactivityresult");
		menu_view.onActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}
}
