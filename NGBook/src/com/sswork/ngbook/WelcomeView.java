package com.sswork.ngbook;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WelcomeView extends FragmentPagerAdapter {
	private ArrayList<Fragment> alfs = null;
	private final int[] imgs = { R.drawable.turkey };

	public WelcomeView(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
		alfs = new ArrayList<Fragment>();
		for (int imgid : imgs) {
			alfs.add(new ImageFragment(imgid));
		}
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return alfs.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return alfs.size();
	}

	@SuppressLint("ValidFragment")
	class ImageFragment extends Fragment {
		int id;
		public ImageFragment(int imgid) {
			// TODO Auto-generated constructor stub
			id=imgid;
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			View v=new View(getActivity());
			v.setBackgroundResource(id);
			return v;
		}
	}

}
