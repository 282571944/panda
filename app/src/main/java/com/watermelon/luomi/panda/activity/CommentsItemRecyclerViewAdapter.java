package com.watermelon.luomi.panda.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.watermelon.luomi.panda.BR;
import com.watermelon.luomi.panda.R;
import com.watermelon.luomi.panda.activity.CommentsActivity.ReplyToUser;
import com.watermelon.luomi.panda.activity.fragments.NewsFragment;
import com.watermelon.luomi.panda.net.CommentResponse;
import com.watermelon.luomi.panda.net.CommentResponse.CommentsBean;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ReplyToUser} and makes a call to the
 * specified {@link NewsFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class CommentsItemRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final ReplyToUser mListener;
    List<CommentsBean> commentsBeens;

    enum ViewType {
        REPLY_USER, REPLY_LANDLORD
    }

    public CommentsItemRecyclerViewAdapter(List<CommentsBean> commentsBeens, ReplyToUser listener) {
        this.commentsBeens = commentsBeens;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (ViewType.REPLY_USER.ordinal() == viewType) {
            ViewDataBinding bindingReplyUser = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_to_user_comments, parent, false);
            ReplyUserViewHolder replyUserViewHolder = new ReplyUserViewHolder(bindingReplyUser.getRoot());
            replyUserViewHolder.setBinding(bindingReplyUser);
            return replyUserViewHolder;
        }
        else if(ViewType.REPLY_LANDLORD.ordinal() == viewType){
            ViewDataBinding bindingReplyLandlord = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_to_landlord_comments, parent, false);
            ReplyLandlordViewHolder replyLandlordViewHolder = new ReplyLandlordViewHolder(bindingReplyLandlord.getRoot());
            replyLandlordViewHolder.setBinding(bindingReplyLandlord);
            return replyLandlordViewHolder;
        }
        else{
            return null;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ReplyUserViewHolder) {
            ((ReplyUserViewHolder) holder).getBinding().setVariable(BR.floor, String.valueOf(position+1)+"楼");
            ((ReplyUserViewHolder) holder).getBinding().setVariable(BR.comment, commentsBeens.get(position));
            ((ReplyUserViewHolder) holder).getBinding().executePendingBindings();
            ((ReplyUserViewHolder) holder).mView.setOnClickListener(v -> mListener.reply(position + ""));
            ((ReplyUserViewHolder) holder).mView.setOnClickListener(v -> mListener.reply(position + ""));
        }
        else if (holder instanceof ReplyLandlordViewHolder) {
            ((ReplyLandlordViewHolder) holder).getBinding().setVariable(BR.floor, String.valueOf(position+1)+"楼");
            ((ReplyLandlordViewHolder) holder).getBinding().setVariable(BR.comment, commentsBeens.get(position));
            ((ReplyLandlordViewHolder) holder).getBinding().executePendingBindings();
            ((ReplyLandlordViewHolder) holder).mView.setOnClickListener(v -> mListener.reply(position + ""));
            ((ReplyLandlordViewHolder) holder).mView.setOnClickListener(v -> mListener.reply(position + ""));
        }
        else{

        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ViewType.REPLY_USER.ordinal() :ViewType.REPLY_LANDLORD.ordinal() ;
    }

    @Override
    public int getItemCount() {
        return commentsBeens.size();
    }

    public class ReplyUserViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private ViewDataBinding binding;

        public ReplyUserViewHolder(View view) {
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

    public class ReplyLandlordViewHolder extends RecyclerView.ViewHolder {
        public final View mReply;
        public final View mView;
        private ViewDataBinding binding;

        public ReplyLandlordViewHolder(View view) {
            super(view);
            mView = view;
            mReply = view.findViewById(R.id.reply);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

    public void addComments(List<CommentResponse.CommentsBean> commentsBeens){
        this.commentsBeens.addAll(commentsBeens);
        CommentsItemRecyclerViewAdapter.this.notifyDataSetChanged();
    }
}
