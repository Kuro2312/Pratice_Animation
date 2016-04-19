package com.example.pratice_animation;

import java.util.ArrayList;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class SecondActivity extends Activity {

	TabHost _mainTabHost;
	GridView _grid1;
    GridView _grid2;
    GridView _grid3;
    ArrayList<GridView> l;
    Animation ani1;
	Animation ani2;
	Animation ani3;
	Animation ani4;
	float _sXPoint = -1;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		_grid1 = (GridView) findViewById(R.id.gridView1);
		_grid2 = (GridView) findViewById(R.id.gridView2);
		_grid3 = (GridView) findViewById(R.id.gridView3);
        
        l = new ArrayList<GridView>();
        l.add(_grid1);
        l.add(_grid2);
        l.add(_grid3);
        
        loadTabs();
        
        ani1 = AnimationUtils.loadAnimation(this, R.animator.out_to_left);
		ani2 = AnimationUtils.loadAnimation(this, R.animator.in_from_right);
		ani3 = AnimationUtils.loadAnimation(this, R.animator.out_to_right);
		ani4 = AnimationUtils.loadAnimation(this, R.animator.in_from_left);
				
		ani1.setStartTime(Animation.START_ON_FIRST_FRAME);
		ani2.setStartTime(Animation.START_ON_FIRST_FRAME);
		ani3.setStartTime(Animation.START_ON_FIRST_FRAME);
		ani4.setStartTime(Animation.START_ON_FIRST_FRAME);
		
        _mainTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				Toast.makeText(SecondActivity.this, tabId, Toast.LENGTH_SHORT).show();
				
				View nView = l.get(ConvertIdToIndex(tabId));
				View cView = l.get(_mainTabHost.getCurrentTab());
				
				nView.setAnimation(ani3);
				cView.setAnimation(ani4);
				cView.getAnimation().start();
				nView.getAnimation().start();
			}
		});
        
        setTouchEventForTabHost();
	}

	public void loadTabs() {
        // Lấy TabHost từ Id cho trước
        _mainTabHost = (TabHost) findViewById(R.id.tabhost);
        _mainTabHost.setup();

        // Tạo cấu hình cho tab1 : tab chứa toàn bộ ảnh
        TabSpec spec = _mainTabHost.newTabSpec("tab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("All");
        _mainTabHost.addTab(spec);

        // Tạo cấu hình cho tab2 : tab chứa toàn bộ album ảnh
        spec = _mainTabHost.newTabSpec("tab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Albums");
        _mainTabHost.addTab(spec);

        // Tạo cấu hình cho tab3 : tab chứa toàn bộ ảnh yêu thích
        spec = _mainTabHost.newTabSpec("tab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Favourite");
        _mainTabHost.addTab(spec);
        
        _mainTabHost.setTag(l);
	}
	
	public int ConvertIdToIndex(String tabID)
	{
		if (tabID == "tab1")
			return 0;
		else if (tabID == "tab2")
			return 1;
		else if (tabID == "tab3")
			return 2;
		
		return -1;
	}
	
	public void setTouchEventForTabHost()
	{
		int numberOfTabs = _mainTabHost.getTabWidget().getChildCount();
        for(int i = 0; i < numberOfTabs; i++)
        {
        	GridView tab = l.get(i);
        	tab.setOnTouchListener(new  View.OnTouchListener() {
            	
    			@Override
    			public boolean onTouch(View v, MotionEvent event) {
    				// TODO Auto-generated method stub
    				
    	        	final int X = (int) event.getRawX();
    				final int Y = (int) event.getRawY();
    				
    				switch (event.getAction() & MotionEvent.ACTION_MASK) 
    				{
    					case MotionEvent.ACTION_DOWN:
    						if (_sXPoint == -1)
    							_sXPoint = X;
    						break;
    					case MotionEvent.ACTION_UP:
    						_sXPoint = -1;
    						break;
    					case MotionEvent.ACTION_POINTER_DOWN:
    						break;
    					case MotionEvent.ACTION_POINTER_UP:
    						break;
    					case MotionEvent.ACTION_MOVE:
    						
    						if (_sXPoint != -1)
    						{
	    						int currentTabID =  _mainTabHost.getCurrentTab();
	    						int nTabs = _mainTabHost.getTabWidget().getChildCount();
	    						int nextTabID = -1;
	    						
	    						if (_sXPoint - X > 50)
	    						{
	    							nextTabID = (currentTabID + 1) % nTabs;
	    							_sXPoint = -1;
		    						l.get(nextTabID).setAnimation(ani1);
		    						l.get(nextTabID).getAnimation().start();
		    						
		    						_mainTabHost.setCurrentTab(nextTabID);
	    						}
	    						else if (_sXPoint - X < -50)
	    						{
	    							if (currentTabID - 1 < 0)
	    								nextTabID = nTabs - 1;
	    							else 
	    								nextTabID = currentTabID - 1;
	    							
	    							_sXPoint = -1;
		    						l.get(nextTabID).setAnimation(ani4);
		    						l.get(nextTabID).getAnimation().start();
		    						
		    						_mainTabHost.setCurrentTab(nextTabID);
	    						}
    						}
    						break;
    				}
    							
    				return true;
    			}
    		});
        } 
	}
	
}
