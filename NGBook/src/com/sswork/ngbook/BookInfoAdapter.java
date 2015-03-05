package com.sswork.ngbook;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BookInfoAdapter extends BaseAdapter {
	ArrayList<BookInfo> albis=null;
	Context context=null;
	public BookInfoAdapter(Context context) {
		// TODO Auto-generated constructor stub
		albis=new ArrayList<BookInfo>();
		albis.add(new BookInfo());
		albis.add(new BookInfo());
		albis.add(new BookInfo());
		albis.add(new BookInfo());
		albis.add(new BookInfo());
		albis.add(new BookInfo());
		albis.add(new BookInfo());
		albis.add(new BookInfo());
		albis.add(new BookInfo());
		albis.add(new BookInfo());
		albis.add(new BookInfo());
		this.context=context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return albis.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return albis.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(null==convertView){
			convertView=LayoutInflater.from(context).inflate(R.layout.book_info_view, null);
			BookInfo bi=albis.get(position);
			((TextView)convertView.findViewById(R.id.book_name)).setText(bi.name);
			((TextView)convertView.findViewById(R.id.book_time)).setText(bi.lastTime);
			((TextView)convertView.findViewById(R.id.book_record)).setText(bi.readingRecord);
		}
		return convertView;
	}
	public void remove(Object item) {
		// TODO Auto-generated method stub
			albis.remove(item);
	}
	public void insert(Object item, int to) {
		// TODO Auto-generated method stub
		albis.add(to, (BookInfo)item);
	}

}
