/*
 * Copyright (C) 12/24/22, 12:53 PM Nguyen Huy
 *
 * HomeFragment.java [lastModified: 12/24/22, 12:53 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.nguyenhuy158.rentstudio.R;
import com.nguyenhuy158.rentstudio.activity.StudioListActivity;
import com.nguyenhuy158.rentstudio.fixbug.WrapContentLinearLayoutManager;
import com.nguyenhuy158.rentstudio.model.Category;
import com.nguyenhuy158.rentstudio.myinterface.STRING;
import com.nguyenhuy158.rentstudio.viewholder.CategoryViewHolder;
import com.squareup.picasso.Picasso;


public class HomeFragment extends Fragment {
	
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";
	RecyclerView                                          recyclerView;
	FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;
	FirebaseDatabase                                      firebaseDatabase
			= FirebaseDatabase.getInstance();
	DatabaseReference                                     databaseReference;
	// TODO: Rename and change types of parameters
	private String mParam1;
	private String mParam2;
	
	public HomeFragment() {
	}
	
	public static HomeFragment newInstance(String param1, String param2) {
		HomeFragment fragment = new HomeFragment();
		Bundle       args     = new Bundle();
		args.putString(ARG_PARAM1, param1);
		args.putString(ARG_PARAM2, param2);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getString(ARG_PARAM1);
			mParam2 = getArguments().getString(ARG_PARAM2);
		}
		
		databaseReference = firebaseDatabase.getReference(
				STRING.CATEGORY_TABLE);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_home, container,
		                             false);
		recyclerView = view.findViewById(R.id.recyclerView);
		// recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(
				new WrapContentLinearLayoutManager(getContext(),
				                                   LinearLayoutManager.HORIZONTAL,
				                                   false));
		getCategory();
		
		return view;
	}
	
	private void getCategory() {
		Query query = databaseReference.limitToLast(50);
		FirebaseRecyclerOptions<Category> firebaseRecyclerOptions
				= new FirebaseRecyclerOptions.Builder<Category>().setQuery(
				query, Category.class).build();
		
		adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(
				firebaseRecyclerOptions) {
			@NonNull
			@Override
			public CategoryViewHolder onCreateViewHolder(
					@NonNull ViewGroup parent, int viewType) {
				View view = LayoutInflater.from(parent.getContext()).inflate(
						R.layout.item_category, parent, false);
				
				return new CategoryViewHolder(view);
			}
			
			@Override
			protected void onBindViewHolder(@NonNull CategoryViewHolder holder,
			                                int position,
			                                @NonNull Category model) {
				
				Picasso.get().load(model.getThumbnailUrl()).into(
						holder.imageViewStudioThumbnail);
				holder.textViewCategoryName.setText(model.getName());
				holder.itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Log.d(TAG, "onClick: " + model.getName());
						
						// sent category id to new activity
						Intent intent = new Intent(getContext(),
						                           StudioListActivity.class);
						intent.putExtra(STRING.KEY_CATEGORY_ID, adapter
								.getRef(holder.getAbsoluteAdapterPosition())
								.getKey());
						startActivity(intent);
						
					}
				});
			}
		};
		Log.i(TAG, "getCategory: " + adapter.getItemCount());
		
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