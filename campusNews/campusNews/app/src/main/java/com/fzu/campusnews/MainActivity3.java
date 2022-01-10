package com.fzu.campusnews;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends  ActivityGroup implements RadioGroup.OnCheckedChangeListener{

    private ViewPager mViewPager;
    private RadioGroup mTabRadioGroup;
    private RadioButton tab1_news;
    private RadioButton tab2_friends;
    private RadioButton tab3_takephotos;
    private RadioButton tab4_homepages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiogroup_layout);

        mTabRadioGroup = (RadioGroup) findViewById(R.id.rg_tabs);
        tab1_news = (RadioButton) findViewById(R.id.rb_news);
        tab2_friends = (RadioButton) findViewById(R.id.rb_friends);
        tab3_takephotos = (RadioButton) findViewById(R.id.rb_takephotos);
        tab4_homepages = (RadioButton) findViewById(R.id.rb_homepages);

        initViewPager();
        mTabRadioGroup.setOnCheckedChangeListener(this);
    }

    //对ViewPager初始化
    public  void initViewPager()
    {
        List<View> viewList = new ArrayList<View>();
        mViewPager= (ViewPager)findViewById(R.id.vp_viewpager);
        //添加RecyclerView
        viewList.add(this.getLocalActivityManager().startActivity("viewpage1_layout", new Intent(MainActivity3.this, ViewPager1.class)).getDecorView());
        viewList.add(View.inflate(MainActivity3.this, R.layout.viewpage2_layout, null));
        viewList.add(View.inflate(MainActivity3.this, R.layout.viewpage3_layout, null));
        viewList.add(View.inflate(MainActivity3.this, R.layout.viewpage4_layout, null));

        //设置mViewPager适配器
        mViewPager.setAdapter(new MyViewPagerAdapter(viewList));
        //设置页面切换监听器
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                restartButton();
                switch(position)
                {
                    case 0:
                        tab1_news.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.news,0,0);
                        mTabRadioGroup.check(R.id.rb_news);
                        break;
                    case 1:
                        tab2_friends.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.friends,0,0);
                        mTabRadioGroup.check(R.id.rb_friends);
                        break;
                    case 2:
                        tab3_takephotos.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.takephotos,0,0);
                        mTabRadioGroup.check(R.id.rb_takephotos);
                        break;
                    case 3:
                        tab4_homepages.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.homepage,0,0);
                        mTabRadioGroup.check(R.id.rb_homepages);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    //RadioGroup事件监听器
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        restartButton();
        switch(i)
        {
            case R.id.rb_news:
                tab1_news.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.news,0,0);
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rb_friends:
                tab2_friends.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.friends,0,0);
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rb_takephotos:
                tab3_takephotos.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.takephotos,0,0);
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rb_homepages:
                tab4_homepages.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.homepage,0,0);
                mViewPager.setCurrentItem(3);
                break;
        }
    }

    //ViewPager适配器
    class MyViewPagerAdapter extends PagerAdapter
    {
        private List<View> viewList;
        public MyViewPagerAdapter(List<View> view) {
            viewList=view;
        }
        //页面数量
        @Override
        public int getCount() {
            return viewList.size();
        }

        //告知框架view的id是不是object
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }
        //页面销毁
        @Override
        public void destroyItem(ViewGroup container,int position,Object object)
        {
            container.removeView(viewList.get(position));
        }
        //页面实例化
        @Override
        public Object instantiateItem(ViewGroup container,int position)
        {
            View view = viewList.get(position);
            container.addView(view);
            return view;
        }
    }

    //初始化按钮不选择下的样式，用于切换按钮样式
    public void restartButton()
    {
        tab1_news.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.news2,0,0);
        tab2_friends.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.friends2,0,0);
        tab3_takephotos.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.takephotos2,0,0);
        tab4_homepages.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.homepage2,0,0);
    }


}