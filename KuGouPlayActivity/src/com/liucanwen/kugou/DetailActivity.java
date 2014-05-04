package com.liucanwen.kugou;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.TextView;

import com.liucanwen.kugou.view.JazzyViewPager;
import com.liucanwen.kugou.view.JazzyViewPager.TransitionEffect;
import com.liucanwen.kugou.view.OutlineContainer;

public class DetailActivity extends Activity {

	private JazzyViewPager mJazzy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
		setContentView(R.layout.activity_main);
		setupJazziness(TransitionEffect.RotateDown);
		initializeListeners();
	}

	private void setupJazziness(TransitionEffect effect) {
		mJazzy = (JazzyViewPager) findViewById(R.id.jazzy_pager);
		//设置viewpager动画效果
		mJazzy.setTransitionEffect(effect);
		mJazzy.setAdapter(new MainAdapter());
		
		//默认到第2页
		mJazzy.setCurrentItem(1);
	}
	
	private void initializeListeners()
	{
		mJazzy.setOnPageChangeListener(new OnPageChangeListener()
		{
			
			@Override
			public void onPageSelected(int arg0)
			{
				//切换到最左或最右page时结束activity
				if(arg0 == 0 || arg0 == 2)
					finish();
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0)
			{
				
			}
		});
	}

	private class MainAdapter extends PagerAdapter {
		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			TextView text = new TextView(DetailActivity.this);
			
			if(position == 1)
				text.setBackgroundResource(R.drawable.kugou_detail);
			else
				text.setBackgroundColor(0x00000000);
			
			container.addView(text, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			mJazzy.setObjectForPosition(text, position);
			return text;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object obj) {
			container.removeView(mJazzy.findViewFromObject(position));
		}
		@Override
		public int getCount() {
			//总页数为3，其中只有第二个显示内容
			return 3;
		}
		@Override
		public boolean isViewFromObject(View view, Object obj) {
			if (view instanceof OutlineContainer) {
				return ((OutlineContainer) view).getChildAt(0) == obj;
			} else {
				return view == obj;
			}
		}		
	}
}
