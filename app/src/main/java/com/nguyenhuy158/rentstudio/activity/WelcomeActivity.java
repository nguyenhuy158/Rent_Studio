/*
 * Copyright (C) 12/21/22, 10:21 PM Nguyen Huy
 *
 * WelcomeActivity.java [lastModified: 12/21/22, 10:16 PM]
 *
 * Contact:
 * facebook: https://www.facebook.com/nguyenhuy158/
 * github: https://www.github.com/nguyenhuy158/
 */

package com.nguyenhuy158.rentstudio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nguyenhuy158.rentstudio.R;
import com.nguyenhuy158.rentstudio.myinterface.STRING;

public class WelcomeActivity extends AppCompatActivity
		implements View.OnClickListener {
	
	Button button;
	Button buttonSignUp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
		bindUi();
		handleEvent();
	}
	
	private void handleEvent() {
		button.setOnClickListener(this);
		buttonSignUp.setOnClickListener(this);
	}
	private void bindUi() {
		Animation alphaAnimation = new AlphaAnimation(0.4f, 0.8f);
		alphaAnimation.setDuration(100);
		alphaAnimation.setStartOffset(20);
		alphaAnimation.setRepeatMode(Animation.REVERSE);
		alphaAnimation.setRepeatCount(STRING.REPEAT_COUNT);
		TextView textViewExtraSlogan = (TextView) findViewById(
				R.id.textViewExtraSlogan);
		textViewExtraSlogan.startAnimation(alphaAnimation);
		
		button       = findViewById(R.id.buttonSignIn);
		buttonSignUp = findViewById(R.id.buttonSignUp);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.buttonSignIn:
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
				break;
			case R.id.buttonSignUp:
				Intent intentSignUp = new Intent(this, SignUpActivity.class);
				startActivity(intentSignUp);
				break;
		}
	}
}