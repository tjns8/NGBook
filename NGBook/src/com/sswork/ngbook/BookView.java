package com.sswork.ngbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import fi.harism.curl.CurlPage;
import fi.harism.curl.CurlView;

public class BookView extends Activity {
	CurlView curlView = null;
	File file = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_view);
		initFile();
		initView();
	}

	private void initView() {
		int index = 0;
		if (getLastNonConfigurationInstance() != null) {
			index = (Integer) getLastNonConfigurationInstance();
		}
		curlView = (CurlView) findViewById(R.id.curl);
		curlView.setPageProvider(new PageProvider());
		curlView.setSizeChangedObserver(new SizeChangedObserver());
		curlView.setCurrentIndex(index);
		curlView.setBackgroundColor(0xFF202830);
	}

	private void initFile() {
		String path = getIntent().getStringExtra("path");
		String record = getIntent().getStringExtra("record");
		file = new File(path);
	}

	@Override
	public void onPause() {
		super.onPause();
		curlView.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		curlView.onResume();
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		return curlView.getCurrentIndex();
	}

	/**
	 * Bitmap provider.
	 */
	private class PageProvider implements CurlView.PageProvider {

		@Override
		public int getPageCount() {
			return 5;
		}

		private Bitmap createBitmapFromFile(int width, int height, int index) throws IOException {
			Bitmap b = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			Canvas c = new Canvas(b);
			Paint p = new Paint();
			p.setColor(Color.BLACK);
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[20];
			for (int i = 0; i < 20; i++) {
				fis.read(buf);
				c.drawText(new String(buf), 0, 10 * i, p);
			}
			return b;
		}

		private Bitmap loadPage(int width, int height, int index) {
			
			Bitmap bff =null;
			try {
				bff= createBitmapFromFile(width, height, index);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Bitmap b = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			b.eraseColor(0xFFFFFFFF);
			Canvas c = new Canvas(b);

			int margin = 7;
			int border = 3;
			Rect r = new Rect(margin, margin, width - margin, height - margin);

			Paint p = new Paint();
			p.setColor(0xFFC0C0C0);
			c.drawRect(r, p);
			r.left += border;
			r.right -= border;
			r.top += border;
			r.bottom -= border;
			return bff;
		}

		@Override
		public void updatePage(CurlPage page, int width, int height, int index) {
			Bitmap front = loadPage(width, height, index);
			page.setTexture(front, CurlPage.SIDE_BOTH);
			page.setColor(Color.argb(127, 255, 255, 255), CurlPage.SIDE_BACK);
		}

	}

	/**
	 * CurlView size changed observer.
	 */
	private class SizeChangedObserver implements CurlView.SizeChangedObserver {
		@Override
		public void onSizeChanged(int w, int h) {
			if (w > h) {
				curlView.setViewMode(CurlView.SHOW_TWO_PAGES);
				curlView.setMargins(.1f, .05f, .1f, .05f);
			} else {
				curlView.setViewMode(CurlView.SHOW_ONE_PAGE);
				curlView.setMargins(.1f, .1f, .1f, .1f);
			}
		}
	}

}
