/*
 * Copyright (C) 12/21/22, 12:11 AM Nguyen Huy
 *
 * StudioList.java [lastModified: 12/21/22, 12:11 AM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio;

import static android.content.ContentValues.TAG;

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
import com.nguyenhuy158.rentstudio.model.Studio;
import com.nguyenhuy158.rentstudio.viewholder.StudioViewHolder;

public class StudioList extends AppCompatActivity {
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
			CategoryId = getIntent().getStringExtra(
					getResources().getString(R.string.KEY_CATEGORY_ID));
		}
		Log.i(TAG, "onCreate: " + CategoryId);
		recyclerView = findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
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
				getResources().getString(R.string.STUDIO_TABLE));
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
						R.layout.studio_item, parent, false);
				
				return new StudioViewHolder(view);
			}
			
			@Override
			protected void onBindViewHolder(@NonNull StudioViewHolder holder,
			                                int position,
			                                @NonNull Studio model) {
				holder.textViewStudioName.setText(model.getName());
				holder.textViewStudioPrice.setText(model.getPrice() + "");
				holder.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
					
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