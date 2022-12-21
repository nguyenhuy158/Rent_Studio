/*
 * Copyright (C) 12/21/22, 2:50 PM Nguyen Huy
 *
 * OrderViewHolder.java [lastModified: 12/21/22, 2:50 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.viewholder;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenhuy158.rentstudio.R;

public class OrderViewHolder extends RecyclerView.ViewHolder
		implements View.OnClickListener {
	public TextView textViewOrderPrice;
	public TextView textViewOrderTime;
	public TextView textViewOrderStudioName;
	
	public OrderViewHolder(@NonNull View itemView) {
		super(itemView);
		
		textViewOrderPrice      = itemView.findViewById(
				R.id.textViewOrderPrice);
		textViewOrderTime       = itemView.findViewById(R.id.textViewOrderTime);
		textViewOrderStudioName = itemView.findViewById(
				R.id.textViewOrderStudioName);
		
		itemView.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
	
	}
}
