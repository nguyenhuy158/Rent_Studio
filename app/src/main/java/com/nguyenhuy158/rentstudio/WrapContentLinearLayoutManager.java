/*
 * Copyright (C) 12/21/22, 10:37 AM Nguyen Huy
 *
 * WrapContentLinearLayoutManager.java [lastModified: 12/21/22, 10:37 AM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WrapContentLinearLayoutManager extends LinearLayoutManager {
	private static final String TAG = "ContentValues";
	
	//... constructor
	public WrapContentLinearLayoutManager(Context context) {
		super(context);
	}
	
	public WrapContentLinearLayoutManager(Context context, int orientation,
	                                      boolean reverseLayout) {
		super(context, orientation, reverseLayout);
	}
	
	@Override
	public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
		try {
			super.onLayoutChildren(recycler, state);
		} catch (IndexOutOfBoundsException e) {
			Log.e(TAG, "onLayoutChildren: meet a IOOBE in RecyclerView" );
		}
	}
}