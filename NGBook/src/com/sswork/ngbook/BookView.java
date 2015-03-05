package com.sswork.ngbook;

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

public class BookView  extends Activity{
	CurlView curlView=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.book_view);
		int index = 0;
		if (getLastNonConfigurationInstance() != null) {
			index = (Integer) getLastNonConfigurationInstance();
		}
		curlView=(CurlView) findViewById(R.id.curl);
		curlView.setPageProvider(new PageProvider());
		curlView.setSizeChangedObserver(new SizeChangedObserver());
		curlView.setCurrentIndex(index);
		curlView.setBackgroundColor(0xFF202830);
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

		private Bitmap loadPage(int width, int height, int index) {
			Drawable d = getResources().getDrawable(R.drawable.icon);
			Bitmap b = Bitmap.createBitmap(width, height,
					Bitmap.Config.ARGB_8888);
			b.eraseColor(0xFFFFFFFF);
			Canvas c = new Canvas(b);

			int margin = 7;
			int border = 3;
			Rect r = new Rect(margin, margin, width - margin, height - margin);

			int imageWidth = r.width() - (border * 2);
			int imageHeight = imageWidth * d.getIntrinsicHeight()
					/ d.getIntrinsicWidth();
			if (imageHeight > r.height() - (border * 2)) {
				imageHeight = r.height() - (border * 2);
				imageWidth = imageHeight * d.getIntrinsicWidth()
						/ d.getIntrinsicHeight();
			}

			r.left += ((r.width() - imageWidth) / 2) - border;
			r.right = r.left + imageWidth + border + border;
			r.top += ((r.height() - imageHeight) / 2) - border;
			r.bottom = r.top + imageHeight + border + border;

			Paint p = new Paint();
			p.setColor(0xFFC0C0C0);
			c.drawRect(r, p);
			r.left += border;
			r.right -= border;
			r.top += border;
			r.bottom -= border;

			d.setBounds(r);
			d.draw(c);

			return b;
		}
		@Override
		public void updatePage(CurlPage page, int width, int height, int index) {

			switch (index) {
			// First case is image on front side, solid colored back.
			case 0: {
				Bitmap front = loadPage(width, height, 0);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK);
				break;
			}
			// Second case is image on back side, solid colored front.
			case 1: {
				Bitmap back = loadPage(width, height, 2);
				page.setTexture(back, CurlPage.SIDE_BACK);
				page.setColor(Color.rgb(127, 140, 180), CurlPage.SIDE_FRONT);
				break;
			}
			// Third case is images on both sides.
			case 2: {
				Bitmap front = loadPage(width, height, 1);
				Bitmap back = loadPage(width, height, 3);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setTexture(back, CurlPage.SIDE_BACK);
				break;
			}
			// Fourth case is images on both sides - plus they are blend against
			// separate colors.
			case 3: {
				Bitmap front = loadPage(width, height, 2);
				Bitmap back = loadPage(width, height, 1);
				page.setTexture(front, CurlPage.SIDE_FRONT);
				page.setTexture(back, CurlPage.SIDE_BACK);
				page.setColor(Color.argb(127, 170, 130, 255),
						CurlPage.SIDE_FRONT);
				page.setColor(Color.rgb(255, 190, 150), CurlPage.SIDE_BACK);
				break;
			}
			// Fifth case is same image is assigned to front and back. In this
			// scenario only one texture is used and shared for both sides.
			case 4:
				Bitmap front = loadPage(width, height, 0);
				page.setTexture(front, CurlPage.SIDE_BOTH);
				page.setColor(Color.argb(127, 255, 255, 255),
						CurlPage.SIDE_BACK);
				break;
			}
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
