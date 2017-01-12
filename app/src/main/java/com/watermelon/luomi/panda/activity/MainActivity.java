package com.watermelon.luomi.panda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationBar.OnTabSelectedListener;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.watermelon.luomi.panda.R;
import com.watermelon.luomi.panda.activity.fragments.ForumFragment;
import com.watermelon.luomi.panda.activity.fragments.MyFragment;
import com.watermelon.luomi.panda.activity.fragments.NewsFragment;
import com.watermelon.luomi.panda.net.NewsResponse.StoriesBean;
import com.watermelon.luomi.panda.utils.CommonUtils;

import java.util.ArrayList;

/**
 * main for navigation
 * 1.news
 * 2.forum
 * 3.mine for base information
 */
public class MainActivity extends BaseActivity implements OnTabSelectedListener, NewsFragment.OnListFragmentInteractionListener, ForumFragment.OnListFragmentInteractionListener, MyFragment.OnFragmentInteractionListener {
    Toolbar toolbar;
    TextView title;
    BottomNavigationBar bottomNavigationBar;
    ArrayList<Fragment> fragments;
    String[] names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 1.set the tile
         * 2.set the bottom navigation
         * 3.init list fragment
         */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.title);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        names = new String[]{getResources().getString(R.string.tab1_name), getResources().getString(R.string.tab2_name), getResources().getString(R.string.tab3_name)};
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        if (bottomNavigationBar != null) {
            bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
            bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
            bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.news_down, names[0]).setInactiveIcon(ResourcesCompat.getDrawable(getResources(), R.mipmap.news, null)).setActiveColor(R.color.colorTabActive))
                    .addItem(new BottomNavigationItem(R.mipmap.talk_down, names[1]).setInactiveIcon(ResourcesCompat.getDrawable(getResources(), R.mipmap.talk, null)).setActiveColor(R.color.colorTabActive))
                    .addItem(new BottomNavigationItem(R.mipmap.my_down, names[2]).setInactiveIcon(ResourcesCompat.getDrawable(getResources(), R.mipmap.my, null)).setActiveColor(R.color.colorTabActive))
                    .setFirstSelectedPosition(0)
                    .initialise();

            fragments = getFragments();
            setDefaultFragment();
            bottomNavigationBar.setTabSelectedListener(this);
        }
    }

    /**
     * the base fragment of main
     * @return list of fragments
     */
    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(NewsFragment.newInstance(0));
        fragments.add(ForumFragment.newInstance(0));
        fragments.add(MyFragment.newInstance("", ""));
        return fragments;
    }

    /**
     * set the default fragment
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, fragments.get(0));
        transaction.commit();
    }

    /**
     * which bottom navigation is selected
     * @param position
     */
    @Override
    public void onTabSelected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                if (fragment.isAdded()) {
                    ft.replace(R.id.layFrame, fragment);
                } else {
                    ft.add(R.id.layFrame, fragment);
                }
                ft.commitAllowingStateLoss();
                title.setText(names[position]);
            }
        }
    }

    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == 0) {
            CommonUtils.checkToken(MainActivity.this, o -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * NewsFragment
     *
     * @param item news item's click event
     */
    @Override
    public void onListFragmentInteraction(StoriesBean item) {
        Intent intent = new Intent(MainActivity.this, NewsDetailActivity.class);
        intent.putExtra("newsId", item.getId() + "");
        startActivity(intent);
    }

    /**
     * ForumFragment
     *
     * @param item forum item's click event
     * @param s    sign
     */
    @Override
    public void onListFragmentInteraction(StoriesBean item, String s) {
        Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
        intent.putExtra("newsId", item.getId() + "");
        startActivity(intent);
    }

    /**
     * MyFragment
     *
     * @param position my item's click event
     */
    @Override
    public void onFragmentInteraction(int position) {

    }
}
