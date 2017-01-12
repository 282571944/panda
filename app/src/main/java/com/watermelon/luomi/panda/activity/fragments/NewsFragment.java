package com.watermelon.luomi.panda.activity.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.watermelon.luomi.panda.R;
import com.watermelon.luomi.panda.net.APIService;
import com.watermelon.luomi.panda.net.NewsResponse;
import com.watermelon.luomi.panda.net.NewsResponse.StoriesBean;
import com.watermelon.luomi.panda.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import space.sye.z.library.RefreshRecyclerView;
import space.sye.z.library.listener.OnBothRefreshListener;
import space.sye.z.library.manager.RecyclerMode;
import space.sye.z.library.manager.RecyclerViewManager;

/**
 * A fragment representing a list of Items.
 * <p>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NewsFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    int mColumnCount = 1;
    OnListFragmentInteractionListener mListener;
    NewsItemRecyclerViewAdapter myItemRecyclerViewAdapter;
    RefreshRecyclerView recyclerView;
    RollPagerView mRollViewPager;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NewsFragment newInstance(int columnCount) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        // Set the adapter
        if (view instanceof RefreshRecyclerView) {
            Context context = view.getContext();
            recyclerView = (RefreshRecyclerView) view;
            myItemRecyclerViewAdapter = new NewsItemRecyclerViewAdapter(getActivity(),recyclerView,mListener);
            View banner = LayoutInflater.from(getActivity()).inflate(R.layout.news_header, null);
            RecyclerViewManager.with(myItemRecyclerViewAdapter,new LinearLayoutManager(getActivity())).setMode(RecyclerMode.BOTH).setOnBothRefreshListener(new OnBothRefreshListener() {
                @Override
                public void onPullDown() {
                    myItemRecyclerViewAdapter.refreshData();
                }

                @Override
                public void onLoadMore() {
                    myItemRecyclerViewAdapter.loadMore();
                }
            }).addHeaderView(banner).into(recyclerView,getActivity());
            mRollViewPager = (RollPagerView)banner.findViewById(R.id.roll_view_pager);
        }
        mRollViewPager.setPlayDelay(1000);
        mRollViewPager.setAnimationDurtion(500);
        mRollViewPager.setAdapter(new BannerAdapter());
        myItemRecyclerViewAdapter.refreshData();//temporary
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(StoriesBean item);
    }

    private class BannerAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.drawable.rolling_viewpager,
                R.drawable.rolling_viewpager,
                R.drawable.rolling_viewpager,
                R.drawable.rolling_viewpager,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}
