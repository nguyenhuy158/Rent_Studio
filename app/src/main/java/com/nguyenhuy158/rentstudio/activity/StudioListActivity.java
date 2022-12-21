/*
 * Copyright (C) 12/21/22, 10:21 PM Nguyen Huy
 *
 * StudioListActivity.java [lastModified: 12/21/22, 10:16 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nguyenhuy158.rentstudio.R;
import com.nguyenhuy158.rentstudio.fixbug.WrapContentLinearLayoutManager;
import com.nguyenhuy158.rentstudio.model.Studio;
import com.nguyenhuy158.rentstudio.myinterface.STRING;
import com.nguyenhuy158.rentstudio.viewholder.StudioViewHolder;

public class StudioListActivity extends AppCompatActivity {
	String                                            CategoryId       = "";
	RecyclerView                                      recyclerView;
	FirebaseRecyclerAdapter<Studio, StudioViewHolder> adapter;
	FirebaseDatabase                                  firebaseDatabase
	                                                                   = FirebaseDatabase.getInstance();
	DatabaseReference                                 databaseReference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_studio_list);
		
		if (getIntent() != null) {
			CategoryId = getIntent().getStringExtra(STRING.KEY_CATEGORY_ID);
		}
		Log.i(TAG, "onCreate: " + CategoryId);
		recyclerView = findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(
				new WrapContentLinearLayoutManager(getApplicationContext(),
				                                   LinearLayoutManager.VERTICAL,
				                                   false));
		loadListStudio();
		// if (!CategoryId.isEmpty() || CategoryId != null) {
		// 	Log.i(TAG, "onCreate: load list");
		// }
	}
	
	private void loadListStudio() {
		// Query query = FirebaseDatabase.getInstance().getReference(
		// 		getResources().getString(R.string.STUDIO_TABLE)).equalTo(
		// 		CategoryId).limitToLast(50);
		Query query = FirebaseDatabase.getInstance().getReference(
				STRING.STUDIO_TABLE);
		FirebaseRecyclerOptions<Studio> firebaseRecyclerOptions
				= new FirebaseRecyclerOptions.Builder<Studio>().setQuery(query,
				                                                         Studio.class)
				                                               .build();
		
		adapter = new FirebaseRecyclerAdapter<Studio, StudioViewHolder>(
				firebaseRecyclerOptions) {
			@NonNull
			@Override
			public StudioViewHolder onCreateViewHolder(
					@NonNull ViewGroup parent, int viewType) {
				View view = LayoutInflater.from(parent.getContext()).inflate(
						R.layout.item_studio, parent, false);
				
				return new StudioViewHolder(view);
			}
			
			@Override
			protected void onBindViewHolder(@NonNull StudioViewHolder holder,
			                                int position,
			                                @NonNull Studio model) {
				if (holder == null) {return;}
				holder.textViewStudioName.setText(model.getName());
				holder.textViewStudioPrice.setText(model.getPrice() + "");
				holder.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(StudioListActivity.this,
						                           DetailStudioActivity.class);
						intent.putExtra(STRING.KEY_STUDIO_ID, adapter
								.getRef(holder.getAdapterPosition()).getKey());
						startActivity(intent);
					}
				});
			}
		};
		Log.i(TAG, "loadListStudio: " + adapter.getItemCount());
		
		recyclerView.setAdapter(adapter);
		
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