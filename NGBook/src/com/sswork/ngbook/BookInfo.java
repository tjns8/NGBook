package com.sswork.ngbook;

import java.util.Date;

import android.text.format.Time;

public class BookInfo {
public String name=null;
public String lastTime=null;
public String record=null;
public String path=null;
public BookInfo() {
	// TODO Auto-generated constructor stub
	name="name";
	lastTime="2015";
	record="11/22";
	path="path";
}
public BookInfo(String path){
	this.path=new String(path);
	lastTime=Time.getCurrentTimezone();
	record="0";
	String ps[]=path.split("\\");
	name=ps[ps.length-1];
}
public BookInfo(Record record){
	name=record.getBookName();
	Date date=new Date(record.getOldTime());
	lastTime=date.toString();
	path=record.getPath();
	this.record=record.getRecord();
}
}
