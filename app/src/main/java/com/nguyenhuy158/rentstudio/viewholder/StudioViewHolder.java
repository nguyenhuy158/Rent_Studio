/*
 * Copyright (C) 12/20/22, 10:53 PM Nguyen Huy
 *
 * CategoryViewHoler.java [lastModified: 12/20/22, 10:53 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhuy158.rentstudio.R;
import com.nguyenhuy158.rentstudio.myinterface.IRecyclerViewItemClickListener;

public class StudioViewHolder extends RecyclerView.ViewHolder
		implements View.OnClickListener {
	public TextView  textViewStudioName;
	public TextView textViewStudioPrice;
	IRecyclerViewItemClickListener iRecyclerViewItemClickListener;
	
	public StudioViewHolder(@NonNull View itemView) {
		super(itemView);
		textViewStudioName       = itemView.findViewById(
				R.id.textViewStudioName);
		textViewStudioPrice       = itemView.findViewById(
				R.id.textViewStudioPrice);
		
		itemView.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		iRecyclerViewItemClickListener.onClick(v, getAbsoluteAdapterPosition(),
		                                       false);
	}
}
