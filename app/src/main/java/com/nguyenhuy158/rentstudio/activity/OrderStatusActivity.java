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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenhuy158.rentstudio.R;
import com.nguyenhuy158.rentstudio.fixbug.WrapContentLinearLayoutManager;
import com.nguyenhuy158.rentstudio.model.Common;
import com.nguyenhuy158.rentstudio.model.Request;
import com.nguyenhuy158.rentstudio.model.Studio;
import com.nguyenhuy158.rentstudio.myinterface.STRING;
import com.nguyenhuy158.rentstudio.viewholder.OrderViewHolder;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderStatusActivity extends AppCompatActivity {
	
	
	RecyclerView                                      orderList;
	FirebaseRecyclerAdapter<Request, OrderViewHolder> adapter;
	List<Studio>                                      studioList
			                                                        = new ArrayList<>();
	List<String>                                      keyStudioList
			                                                        = new ArrayList<>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_status);
		
		orderList = findViewById(R.id.orderList);
		orderList.setLayoutManager(new WrapContentLinearLayoutManager(this,
		                                                              LinearLayoutManager.VERTICAL,
		                                                              false));
		
		loadOrders();
		loadStudioList();
	}
	
	private void loadStudioList() {
		FirebaseDatabase.getInstance().getReference(STRING.STUDIO_TABLE)
		                .addValueEventListener(new ValueEventListener() {
			                @Override
			                public void onDataChange(
					                @NonNull DataSnapshot snapshot) {
				                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
					                Studio studio = dataSnapshot.getValue(
							                Studio.class);
					                studioList.add(studio);
					                keyStudioList.add(dataSnapshot.getKey());
				                }
			                }
			
			                @Override
			                public void onCancelled(
					                @NonNull DatabaseError error) {
				
			                }
		                });
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
				Locale locale = new Locale(STRING.language_code,
				                           STRING.country_code);
				NumberFormat numberFormat = NumberFormat.getCurrencyInstance(
						locale);
				holder.textViewOrderPrice.setText(numberFormat.format(model.getTotal()));
				holder.textViewOrderStudioName.setText(
						getStudioName(model.getStudioId()));
				holder.textViewOrderTime.setText(model.getBookTime() + " hour");
				holder.textViewOrderStartDate.setText(
						model.getStartDate() + "");
				holder.textViewOrderStatus.setText(
						getStatus(model.getStatus()));
				changeColorStatus(holder.textViewOrderStatus,
				                  model.getStatus());
				holder.textViewOrderTotalTime.setText(
						model.getTotalHour() + "");
				
				holder.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Log.d(STRING.TAG, "onClick: " + model.getStudioId());
					}
				});
			}
		};
		Log.d(STRING.TAG, "loadOrders:" + adapter.getItemCount());
		orderList.setAdapter(adapter);
	}
	
	private void changeColorStatus(TextView textViewOrderStatus, int status) {
		if (status == STRING.ORDER_WAITING) {
			textViewOrderStatus.setBackgroundResource(
					R.drawable.button_status_waitting);
		}
		if (status == STRING.ORDER_DONE) {
			textViewOrderStatus.setBackgroundResource(
					R.drawable.button_status_done);
		}
		if (status == STRING.ORDER_SUCCESS) {
			textViewOrderStatus.setBackgroundResource(
					R.drawable.button_status_success);
		}
		if (status == STRING.ORDER_CANCELLED) {
			textViewOrderStatus.setBackgroundResource(
					R.drawable.button_status_cancelled);
		}
	}
	
	private String getStatus(int status) {
		if (status == STRING.ORDER_WAITING) {return STRING.WAITING;}
		if (status == STRING.ORDER_DONE) {return STRING.DONE;}
		if (status == STRING.ORDER_SUCCESS) {return STRING.SUCCESS;}
		if (status == STRING.ORDER_CANCELLED) {return STRING.CANCELLED;}
		return STRING.WAITING;
	}
	
	private String getStudioName(String studioId) {
		for (int i = 0; i < keyStudioList.size(); i++) {
			Log.d(STRING.TAG, "getStudioName: " + keyStudioList.get(i));
			if (keyStudioList.get(i).equals(studioId)) {
				Log.d(STRING.TAG, "getStudioName: " + studioId);
				Log.d(STRING.TAG, "getStudioName: " + studioList.get(i).getName());
				
				return studioList.get(i).getName();
			}
		}
		return "";
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