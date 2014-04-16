package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class PurchasableAdapter extends BaseAdapter {
	private Fragment mFragment;
	private Integer[] mImageIds = {R.drawable.grea_the_dragonborn_thumbnail1, R.drawable.grea_the_dragonborn_thumbnail2,
			R.drawable.grea_the_dragonborn_thumbnail3, R.drawable.grea_the_dragonborn_thumbnail4};
	public PurchasableAdapter(Fragment f) {
		mFragment = f;
	}

	@Override
	public int getCount() {
		return mImageIds.length;
	}

	@Override
	public Object getItem(int position) {
		return mImageIds[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
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
		iview.setImageResource(mImageIds[position]);
		return iview;
	}

}
