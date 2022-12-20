/*
 * Copyright (C) 12/20/22, 10:57 AM Nguyen Huy
 *
 * BottomNavigationBehavior.java [lastModified: 12/20/22, 10:57 AM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationBehavior
		extends CoordinatorLayout.Behavior<BottomNavigationView> {
	public BottomNavigationBehavior() {
	}
	
	public BottomNavigationBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean layoutDependsOn(@NonNull CoordinatorLayout parent,
	                               @NonNull BottomNavigationView child,
	                               @NonNull View dependency) {
		boolean dependsOn = dependency instanceof FrameLayout;
		return dependsOn;
	}
	
	@Override
	public boolean onStartNestedScroll(
			@NonNull CoordinatorLayout coordinatorLayout,
			@NonNull BottomNavigationView child,
			@NonNull View directTargetChild, @NonNull View target, int axes,
			int type) {
		return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
	}
	
	@Override
	public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
	                              @NonNull BottomNavigationView child,
	                              @NonNull View target, int dx, int dy,
	                              @NonNull int[] consumed, int type) {
		if (dy < 0) {
			showBottomNavigationView(child);
		} else if (dy > 0) {
			hideBottomNavigationView(child);
		}
	}
	
	private void hideBottomNavigationView(BottomNavigationView view) {
		view.animate().translationY(view.getHeight());
	}
	
	private void showBottomNavigationView(BottomNavigationView view) {
		view.animate().translationY(0);
	}
}
