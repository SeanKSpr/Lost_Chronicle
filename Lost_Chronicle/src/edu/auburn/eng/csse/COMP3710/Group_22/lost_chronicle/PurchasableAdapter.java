package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class PurchasableAdapter extends BaseAdapter {
	private Fragment mFragment;
	private ArrayList<Purchasable> purchasableArray;
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
		Purchasable purchase = purchasableArray.get(position);
		if (purchase instanceof Companion && !purchase.hasBeenPurchased()) {
			return Purchasable.UNKNOWN_KANOJO;
		}
		return purchasableArray.get(position).getThumbnailResource();
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ImageView iview;
		if (view == null) {
			Resources r = Resources.getSystem();
			float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, r.getDisplayMetrics());
			
			iview = new ImageView(mFragment.getActivity());
			iview.setLayoutParams(new GridView.LayoutParams((int) px, (int) px));
			iview.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iview.setPadding(2, 2, 2, 2);
		} 
		else {
			iview = (ImageView) view;	
		}
		iview.setImageResource((int) getItemId(position));
		return iview;
	}

}
