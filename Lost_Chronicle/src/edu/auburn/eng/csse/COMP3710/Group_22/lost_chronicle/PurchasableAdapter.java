package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;

import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class PurchasableAdapter extends BaseAdapter {
	private Fragment mFragment;
	private ArrayList<Purchasable> purchasableArray;
	//Needs to be generated from database. Companion_Activity will create an ArrayList of Companions and pass them to this class.
	//The Adapter will assign the list of companions to it's list of Purchasables and then populate the gridview.
	private void setList(ArrayList<Purchasable> list) {
		purchasableArray = list;
	}
	public PurchasableAdapter(Fragment f, ArrayList<Purchasable> list) {
		mFragment = f;
		setList(list);
	}

	@Override
	public int getCount() {
		return purchasableArray.size();
	}

	@Override
	public Object getItem(int position) {
		return purchasableArray.get(position);
	}

	@Override
	public long getItemId(int position) {
		return purchasableArray.get(position).getThumbnailResource();
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ImageView iview;
		if (view == null) {
			iview = new ImageView(mFragment.getActivity());
			iview.setLayoutParams(new GridView.LayoutParams(150,150));
			iview.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iview.setPadding(2, 2, 2, 2);
		} else {
			iview = (ImageView) view;	
		}
		iview.setImageResource((int) getItemId(position));
		return iview;
	}

}
