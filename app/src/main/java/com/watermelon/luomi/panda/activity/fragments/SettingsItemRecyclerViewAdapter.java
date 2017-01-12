package com.watermelon.luomi.panda.activity.fragments;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.watermelon.luomi.panda.BR;
import com.watermelon.luomi.panda.R;
import com.watermelon.luomi.panda.activity.fragments.MyFragment.OnFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link String int} and makes a call to the
 * specified {@link NewsFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class SettingsItemRecyclerViewAdapter extends RecyclerView.Adapter<SettingsItemRecyclerViewAdapter.ViewHolder> {

    private final OnFragmentInteractionListener mListener;
    int[] drawableIds = new int[]{R.mipmap.password, R.mipmap.password, R.mipmap.name, R.mipmap.fadeback, R.mipmap.about, R.mipmap.clean};
    String[] names = new String[]{"我的消息", "密码修改", "个人资料修改", "用户反馈", "关于我们", "清除缓存"};

    public SettingsItemRecyclerViewAdapter(OnFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fragment_my, parent, false);
        ViewHolder holder = new ViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final int drawableId = drawableIds[position];
        final String name = names[position];
        holder.getBinding().setVariable(BR.name, name);
        holder.getBinding().setVariable(BR.drawable, holder.mView.getContext().getResources().getDrawable(drawableId));
        holder.getBinding().executePendingBindings();
        holder.mView.setOnClickListener(v -> mListener.onFragmentInteraction(position));
    }

    @Override
    public int getItemCount() {
        return drawableIds.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private ViewDataBinding binding;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }
}
