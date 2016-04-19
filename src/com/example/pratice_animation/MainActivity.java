package com.example.pratice_animation;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {

	
	Button btn1;
	Button btn2;
	
	Animation ani1;
	Animation ani2;
	Animation ani3;
	Animation ani4;
	
	ImageView img1;
	ImageView img2;
	ImageView img3;
	float startX = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ani1 = AnimationUtils.loadAnimation(this, R.animator.out_to_left);
		ani2 = AnimationUtils.loadAnimation(this, R.animator.in_from_right);
		ani3 = AnimationUtils.loadAnimation(this, R.animator.out_to_right);
		ani4 = AnimationUtils.loadAnimation(this, R.animator.in_from_left);
				
		ani1.setStartTime(Animation.START_ON_FIRST_FRAME);
		ani2.setStartTime(Animation.START_ON_FIRST_FRAME);
		ani3.setStartTime(Animation.START_ON_FIRST_FRAME);
		ani4.setStartTime(Animation.START_ON_FIRST_FRAME);
		
		btn1 = (Button) this.findViewById(R.id.button1);
		btn2 = (Button) this.findViewById(R.id.button2);
		btn2.setVisibility(View.INVISIBLE);
		
		img1 = (ImageView) this.findViewById(R.id.imageView1);
		img2 = (ImageView) this.findViewById(R.id.imageView2);
		img3 = (ImageView) this.findViewById(R.id.imageView3);
		img2.setVisibility(View.INVISIBLE);
		img3.setVisibility(View.INVISIBLE);
		
		img1.setOnTouchListener( new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				final float X = event.getRawX();
				
				switch (event.getAction() & MotionEvent.ACTION_MASK) 
				{
					case MotionEvent.ACTION_DOWN:
						if (startX == -1)
							startX = X;
						break;
					case MotionEvent.ACTION_UP:
						startX = -1;
						break;
					case MotionEvent.ACTION_POINTER_DOWN:
						break;
					case MotionEvent.ACTION_POINTER_UP:
						break;
					case MotionEvent.ACTION_MOVE:
						
						if (startX != -1)
						{
							if (X - startX > 50)
							{
								startX = -1;
								img3.setVisibility(View.VISIBLE);
								img1.setAnimation(ani3);
								img3.setAnimation(ani4);
								img1.getAnimation().start();
								img3.getAnimation().start();
								img1.setVisibility(View.INVISIBLE);
							}
							else if (X - startX < -50)
							{
								startX = -1;
								img2.setVisibility(View.VISIBLE);
								img1.setAnimation(ani1);
								img2.setAnimation(ani2);
								img1.getAnimation().start();
								img2.getAnimation().start();
								img1.setVisibility(View.INVISIBLE);
							}
						}
						break;
				}
				
				return true;
			}
		});
		
		img2.setOnTouchListener( new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				final float X = event.getRawX();
				
				switch (event.getAction() & MotionEvent.ACTION_MASK) 
				{
					case MotionEvent.ACTION_DOWN:
						if (startX == -1)
							startX = X;
						break;
					case MotionEvent.ACTION_UP:
						startX = -1;
						break;
					case MotionEvent.ACTION_POINTER_DOWN:
						break;
					case MotionEvent.ACTION_POINTER_UP:
						break;
					case MotionEvent.ACTION_MOVE:
						
						if (startX != -1)
						{
							if (X - startX > 50)
							{
								startX = -1;
								img1.setVisibility(View.VISIBLE);
								img2.setAnimation(ani3);
								img1.setAnimation(ani4);
								img2.getAnimation().start();
								img1.getAnimation().start();
								img2.setVisibility(View.INVISIBLE);
							}
							else if (X - startX < -50)
							{
								startX = -1;
								img3.setVisibility(View.VISIBLE);
								img2.setAnimation(ani1);
								img3.setAnimation(ani2);
								img2.getAnimation().start();
								img3.getAnimation().start();
								img2.setVisibility(View.INVISIBLE);
							}
						}
						break;
				}
				
				return true;
			}
		});
		
		img3.setOnTouchListener( new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				final float X = event.getRawX();
				
				switch (event.getAction() & MotionEvent.ACTION_MASK) 
				{
					case MotionEvent.ACTION_DOWN:
						if (startX == -1)
							startX = X;
						break;
					case MotionEvent.ACTION_UP:
						startX = -1;
						break;
					case MotionEvent.ACTION_POINTER_DOWN:
						break;
					case MotionEvent.ACTION_POINTER_UP:
						break;
					case MotionEvent.ACTION_MOVE:
						
						if (startX != -1)
						{
							if (X - startX > 50)
							{
								startX = -1;
								img2.setVisibility(View.VISIBLE);
								img3.setAnimation(ani3);
								img2.setAnimation(ani4);
								img3.getAnimation().start();
								img2.getAnimation().start();
								img3.setVisibility(View.INVISIBLE);
							}
							else if (X - startX < -50)
							{
								startX = -1;
								img1.setVisibility(View.VISIBLE);
								img3.setAnimation(ani1);
								img1.setAnimation(ani2);
								img3.getAnimation().start();
								img1.getAnimation().start();
								img3.setVisibility(View.INVISIBLE);
							}
						}
						break;
				}
				
				return true;
			}
		});
	}
	
	public void onClick1(View v) {

		btn2.setVisibility(View.VISIBLE);
		btn1.setAnimation(ani1);
		btn2.setAnimation(ani2);
		btn1.getAnimation().start();
		btn2.getAnimation().start();
		btn1.setVisibility(View.INVISIBLE);
	}
	
	public void onClick2(View v) {

		btn1.setVisibility(View.VISIBLE);
		btn2.setAnimation(ani1);
		btn1.setAnimation(ani2);
		btn1.getAnimation().start();
		btn2.getAnimation().start();
		btn2.setVisibility(View.INVISIBLE);
		
		Intent intent = new Intent(MainActivity.this, SecondActivity.class);
		
		startActivity(intent);
		
		MainActivity.this.overridePendingTransition(R.animator.in_from_right, R.animator.out_to_left);
	}
	
}
