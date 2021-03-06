package com.sswork.ngbook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ipaulpro.afilechooser.utils.FileUtils;

public class Menu extends LinearLayout implements OnClickListener{
	private static final String TAG = "FileChooserExampleActivity";

	private static final int REQUEST_CODE = 6384; // onActivityResult request
													// code
	@SuppressLint("NewApi")
	public Menu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initView();
	}

	public Menu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView();
	}

	public Menu(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView();
	}

	private void initView() {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.menu, this);
		findViewById(R.id.ButtonHelp).setOnClickListener(this);
		findViewById(R.id.ButtonOpen).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ButtonHelp:
			break;
		case R.id.ButtonOpen:
			showChooser();
			break;
		}
	}

	private void showChooser() {
		// Use the GET_CONTENT intent from the utility class
		Intent target = FileUtils.createGetContentIntent();
		// Create the chooser Intent
		Intent intent = Intent.createChooser(target,
				getContext().getString(R.string.chooser_title));
		try {
			((Activity) getContext()).startActivityForResult(intent,
					REQUEST_CODE);
		} catch (ActivityNotFoundException e) {
			// The reason for the existence of aFileChooser
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST_CODE:
			// If the file selection was successful
			if (resultCode == Activity.RESULT_OK) {
				if (data != null) {
					// Get the URI of the selected file
					final Uri uri = data.getData();
					Log.i(TAG, "Uri = " + uri.toString());
					try {
						// Get the file path from the URI
						final String path = FileUtils
								.getPath(getContext(), uri);
						Toast.makeText(getContext(), "File Selected: " + path,
								Toast.LENGTH_LONG).show();
						Log.i("menu", "will add bi");
						BookInfoAdapter.getBookInfoAdapter(getContext()).addNewBookInfo(path);
					} catch (Exception e) {
						Log.e("FileSelectorTestActivity", "File select error",
								e);
					}
				}
			}
			break;
		}
	}
	
}
