package com.sswork.ngbook;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BookInfoAdapter extends BaseAdapter {
	ArrayList<BookInfo> albis = null;
	Context context = null;
	RecordDao recordDao=null;
	static BookInfoAdapter bia=null;
	public static BookInfoAdapter getBookInfoAdapter(Context context){
		if(null==bia){
			bia=new BookInfoAdapter(context);
		}
		return bia;
	}
	private BookInfoAdapter(Context context) {
		// TODO Auto-generated constructor stub
		SQLiteOpenHelper helper=new DaoMaster.DevOpenHelper(context, "bookinfo-db", null);
		DaoMaster daoMaster=new DaoMaster(helper.getWritableDatabase());
		DaoSession daoSession=daoMaster.newSession();
		recordDao=daoSession.getRecordDao();
		albis = new ArrayList<BookInfo>();
		albis.add(new BookInfo());
		for(Record record:recordDao.loadAll()){
			albis.add(new BookInfo(record));
		}
		this.context = context;
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
		if (null == convertView) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.book_info_view, null);
			BookInfo bi = albis.get(position);
			((TextView) convertView.findViewById(R.id.book_name))
					.setText(bi.name);
			((TextView) convertView.findViewById(R.id.book_time))
					.setText(bi.lastTime);
			((TextView) convertView.findViewById(R.id.book_record))
					.setText(bi.record);
		}
		return convertView;
	}

	public void remove(Object item) {
		// TODO Auto-generated method stub
		albis.remove(item);
		notifyDataSetChanged();
	}

	public void insert(Object item, int to) {
		// TODO Auto-generated method stub
		albis.add(to, (BookInfo) item);
		notifyDataSetChanged();
	}

	public void addNewBookInfo(String path) {
		Log.i("bia", "add new bi");
		BookInfo bookInfo = new BookInfo(path);
		albis.add(bookInfo);
		Record record=new Record();
		record.setBookName(bookInfo.name);
		record.setPath(bookInfo.path);
		record.setOldTime(System.currentTimeMillis());
		record.setRecord("0");
		recordDao.insert(record);
		notifyDataSetChanged();
	}

}
