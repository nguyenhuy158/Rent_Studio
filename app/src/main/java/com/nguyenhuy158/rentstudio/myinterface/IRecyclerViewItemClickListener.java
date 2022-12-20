/*
 * Copyright (C) 12/20/22, 10:49 PM Nguyen Huy
 *
 * RecyclerViewItemClickListener.java [lastModified: 12/20/22, 10:49 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.myinterface;

import android.view.View;

public interface IRecyclerViewItemClickListener {
	void onClick(View view, int position, boolean isLongClick);
}
