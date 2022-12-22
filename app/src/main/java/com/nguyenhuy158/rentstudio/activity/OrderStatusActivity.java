/*
 * Copyright (C) 12/21/22, 10:21 PM Nguyen Huy
 *
 * OrderStatusActivity.java [lastModified: 12/21/22, 10:16 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nguyenhuy158.rentstudio.R;
import com.nguyenhuy158.rentstudio.fixbug.WrapContentLinearLayoutManager;
import com.nguyenhuy158.rentstudio.model.Common;
import com.nguyenhuy158.rentstudio.model.Request;
import com.nguyenhuy158.rentstudio.myinterface.STRING;
import com.nguyenhuy158.rentstudio.viewholder.OrderViewHolder;

public class OrderStatusActivity extends AppCompatActivity {
	
	
	RecyclerView                                      orderList;
	FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_status);
		
		orderList = findViewById(R.id.orderList);
		orderList.setLayoutManager(new WrapContentLinearLayoutManager(this,
		                                                              LinearLayoutManager.VERTICAL,
		                                                              false));
		
		loadOrders();
		
	}
	
	private void loadOrders() {
		Query query = FirebaseDatabase.getInstance().getReference(
				STRING.REQUEST_TABLE).orderByChild(STRING.KEY_PHONE).equalTo(
				Common.getPhone());
		FirebaseRecyclerOptions<Request> firebaseRecyclerOptions
				= new FirebaseRecyclerOptions.Builder<Request>().setQuery(query,
				                                                          Request.class)
				                                                .build();
		
		adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
				firebaseRecyclerOptions) {
			@NonNull
			@Override
			public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
			                                          int viewType) {
				View view = LayoutInflater.from(parent.getContext()).inflate(
						R.layout.item_order, parent, false);
				
				return new OrderViewHolder(view);
			}
			
			@Override
			protected void onBindViewHolder(@NonNull OrderViewHolder holder,
			                                int position,
			                                @NonNull Request model) {
				Log.d(STRING.TAG, "onBindViewHolder:" + adapter.getItemCount());
				
				if (holder == null) {return;}
				holder.textViewOrderPrice.setText(model.getTotal() + "");
				holder.textViewOrderStudioName.setText(
						model.getStudioId() + "");
				holder.textViewOrderTime.setText(model.getBookTime() + "");
				holder.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Log.d(STRING.TAG,
						      "onClick: " + model.getStudioId());
					}
				});
			}
		};
		Log.d(STRING.TAG, "loadOrders:" + adapter.getItemCount());
		orderList.setAdapter(adapter);
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
		adapter.startListening();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		adapter.stopListening();
	}
}