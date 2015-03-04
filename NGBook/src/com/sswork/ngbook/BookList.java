package com.sswork.ngbook;

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mobeta.android.dslv.DragSortListView;

@SuppressLint("NewApi")
public class BookList extends ListFragment {
	static void printf(String string) {
		Log.i("booklist", string);
	}

	private ListView listview = null;

	private BookInfoAdapter adapter;

	private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
		@Override
		public void drop(int from, int to) {
			printf("drop");
			// String item = adapter.getItem(from);
			//
			// adapter.notifyDataSetChanged();
			// adapter.remove(item);
			// adapter.insert(item, to);
		}
	};

	private DragSortListView.RemoveListener onRemove = new DragSortListView.RemoveListener() {
		@Override
		public void remove(int which) {
			printf("remove");
			// adapter.remove(adapter.getItem(which));
		}
	};

	private DragSortListView.DragScrollProfile ssProfile = new DragSortListView.DragScrollProfile() {
		@Override
		public float getSpeed(float w, long t) {
			printf("getspeed");
			if (w > 0.8f) {
				// Traverse all views in a millisecond
				return ((float) adapter.getCount()) / 0.001f;
			} else {
				return 10.0f * w;
			}
		}
	};

	public BookList() {
		// TODO Auto-generated constructor stub
		super();
		printf("new");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		printf("on create");
		adapter = new BookInfoAdapter(getActivity());
		setListAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		printf("on create view");
		View v= (DragSortListView) inflater.inflate(R.layout.book_list, container, false);
		return v;
	}

	public void initView() {
		printf("initview");
		ListView lv = getListView();
		if (lv != null) {
			printf("lv is not null");
			DragSortListView dslv = (DragSortListView) getListView();
			dslv.setDropListener(onDrop);
			dslv.setRemoveListener(onRemove);
			dslv.setDragScrollProfile(ssProfile);
		}else{
			printf("lv is null");
		}
	}

}