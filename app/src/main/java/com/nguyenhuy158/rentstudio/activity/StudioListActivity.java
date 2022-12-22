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
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.nguyenhuy158.rentstudio.R;
import com.nguyenhuy158.rentstudio.fixbug.WrapContentLinearLayoutManager;
import com.nguyenhuy158.rentstudio.model.Studio;
import com.nguyenhuy158.rentstudio.myinterface.STRING;
import com.nguyenhuy158.rentstudio.viewholder.StudioViewHolder;

import java.util.ArrayList;
import java.util.List;

public class StudioListActivity extends AppCompatActivity {
	String                                            CategoryId  = "";
	RecyclerView                                      recyclerView;
	FirebaseRecyclerAdapter<Studio, StudioViewHolder> adapter;
	FirebaseRecyclerAdapter<Studio, StudioViewHolder> searchAdapter;
	List<String>                                      suggestList
	                                                              = new ArrayList<>();
	MaterialSearchBar                                 materialSearchBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_studio_list);
		
		bindUI();
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
	
	private void bindUI() {
		materialSearchBar = findViewById(R.id.searchBar);
		loadSuggest();
		materialSearchBar.setCardViewElevation(10);
		materialSearchBar.setHint(STRING.hint_search);
		materialSearchBar.addTextChangeListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
			                              int after) {
				
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
			                          int count) {
				List<String> suggest = new ArrayList<>();
				for (String search : suggestList) {
					if (search.toLowerCase().contains(
							materialSearchBar.getText().toLowerCase())) {
						suggest.add(search);
					}
				}
				
				materialSearchBar.setLastSuggestions(suggest);
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		materialSearchBar.setOnSearchActionListener(
				new MaterialSearchBar.OnSearchActionListener() {
					@Override
					public void onSearchStateChanged(boolean enabled) {
						if (!enabled) {
							recyclerView.setAdapter(adapter);
						}
					}
					
					@Override
					public void onSearchConfirmed(CharSequence text) {
						Log.d(TAG, "onSearchConfirmed: ");
						Log.d(TAG,
						      "onSearchConfirmed: text " + text.toString());
						Query query = FirebaseDatabase.getInstance()
						                              .getReference(
								                              STRING.STUDIO_TABLE)
						                              .orderByChild(
								                              STRING.KEY_NAME)
						                              .startAt(text.toString())
						                              .endAt(text.toString() + "\uf8ff")
								;
						FirebaseRecyclerOptions<Studio> firebaseRecyclerOptions
								= new FirebaseRecyclerOptions.Builder<Studio>()
								.setQuery(query, Studio.class).build();
						
						searchAdapter
								= new FirebaseRecyclerAdapter<Studio, StudioViewHolder>(
								firebaseRecyclerOptions) {
							@NonNull
							@Override
							public StudioViewHolder onCreateViewHolder(
									@NonNull ViewGroup parent, int viewType) {
								View view = LayoutInflater.from(
										parent.getContext()).inflate(
										R.layout.item_studio, parent, false);
								
								return new StudioViewHolder(view);
							}
							
							@Override
							protected void onBindViewHolder(
									@NonNull StudioViewHolder holder,
									int position, @NonNull Studio model) {
								if (holder == null) {return;}
								Log.d(TAG,
								      "onBindViewHolder: search model " + model.getName());
								holder.textViewStudioName.setText(
										model.getName());
								holder.textViewStudioPrice.setText(
										model.getPrice() + "");
								holder.itemView.setOnClickListener(
										new View.OnClickListener() {
											@Override
											public void onClick(View v) {
												Intent intent = new Intent(
														StudioListActivity.this,
														DetailStudioActivity.class);
												intent.putExtra(
														STRING.KEY_STUDIO_ID,
														adapter
																.getRef(holder.getAdapterPosition())
																.getKey());
												startActivity(intent);
											}
										});
							}
						};
						searchAdapter.startListening();
						Log.i(TAG,
						      "loadListStudio: searchAdapter " + searchAdapter.getItemCount());
						recyclerView.setAdapter(searchAdapter);
					}
					
					@Override
					public void onButtonClicked(int buttonCode) {
						
					}
				});
		
	}
	
	private void loadSuggest() {
		FirebaseDatabase.getInstance().getReference(STRING.STUDIO_TABLE)
		                .orderByChild(STRING.KEY_CATEGORY_ID).equalTo("")
		                .addValueEventListener(new ValueEventListener() {
			                @Override
			                public void onDataChange(
					                @NonNull DataSnapshot snapshot) {
				                Log.d(TAG,
				                      "onDataChange: count++ " + snapshot.getChildrenCount());
				
			                }
			
			                @Override
			                public void onCancelled(
					                @NonNull DatabaseError error) {
				
			                }
		                });
		
		FirebaseDatabase.getInstance().getReference(STRING.STUDIO_TABLE)
		                .orderByChild(STRING.KEY_CATEGORY_ID)
		                .addValueEventListener(new ValueEventListener() {
			                @Override
			                public void onDataChange(
					                @NonNull DataSnapshot snapshot) {
				                Log.d(TAG,
				                      "onDataChange: category id" + CategoryId);
				                Log.d(TAG,
				                      "onDataChange: child " + snapshot.getChildrenCount());
				                Studio value = snapshot.getValue(Studio.class);
				                Log.d(TAG,
				                      "onDataChange: category id " + value.getCategoryId());
				                Log.d(TAG, "onDataChange: " + value.getName());
				
				                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
					                Studio studio = dataSnapshot.getValue(
							                Studio.class);
					                suggestList.add(studio.getName());
					                Log.d(TAG,
					                      "onDataChange: category id " + studio.getCategoryId());
					                Log.d(TAG,
					                      "onDataChange: " + studio.getName());
				                }
				                Log.d(TAG,
				                      "onDataChange: size" + suggestList.size());
				                materialSearchBar.setLastSuggestions(
						                suggestList);
			                }
			
			                @Override
			                public void onCancelled(
					                @NonNull DatabaseError error) {
				                Log.d(TAG, "onCancelled: Error");
			                }
		                });
	}
	
	private void loadListStudio() {
		Query query = FirebaseDatabase.getInstance().getReference(
				                              STRING.STUDIO_TABLE).orderByChild(STRING.KEY_CATEGORY_ID)
		                              .equalTo(CategoryId);
		// Query query = FirebaseDatabase.getInstance().getReference(
		// 		STRING.STUDIO_TABLE);
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
		if (adapter != null) {
			adapter.startListening();
		}
		if (searchAdapter != null) {
			searchAdapter.startListening();
		}
	}
	
	@Override
	public void onStop() {
		super.onStop();
		adapter.stopListening();
		searchAdapter.stopListening();
	}
}