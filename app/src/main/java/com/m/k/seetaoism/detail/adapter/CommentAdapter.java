package com.m.k.seetaoism.detail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m.k.GlideApp;
import com.m.k.mvp.widgets.CommentsView;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.data.entity.Comment;
import com.m.k.seetaoism.databinding.ItemDetialCommentBinding;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {

    private ArrayList<Comment> mComments;


    public void setComments(ArrayList<Comment> comments) {
        this.mComments = comments;

        notifyItemRangeInserted(0,comments.size());

    }



    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detial_comment,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
            holder.bindData(mComments.get(position));
    }

    @Override
    public int getItemCount() {
        return mComments == null  ? 0 : mComments.size();
    }


    public class CommentHolder extends RecyclerView.ViewHolder{

        ItemDetialCommentBinding binding;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);

            binding = ItemDetialCommentBinding.bind(itemView);
        }


        public void bindData(Comment comment){
            GlideApp.with(itemView).load(comment.getHeadUrl()).circleCrop().into(binding.detailComcomtItemUserHeadPic);

            binding.detailCommentItemTvUsername.setText(comment.getUserName());
            binding.detailCommentItemTvTime.setText(comment.getTimeDescribe());
            binding.detailCommentItemTvContent.setText(comment.getContent());
            binding.detailCommentItemCbLick.setText(String.valueOf(comment.getPraiseCountDescribe()));
            if(comment.getReplyMore() == 1){

                binding.detailCommentItemShowMore.setVisibility(View.VISIBLE);
            }else{
                binding.detailCommentItemShowMore.setVisibility(View.GONE);
            }

            // 把回复的集合给它
            binding.detailCommentItemReply.setList(comment.getReplyList());

            /**
             *  设置监听，监听点击用户名或者点击回复内容
             *  监听有三个参数：
             *  1. 回复列表里面被点击的item的position
             *  2. 如果直接点击的是用户名，那么这个id 就是对应用户的id，如果点击的内容那么这个id 表示 回复那个人的id。
             *
             *  3. 回复列表里面被点击的回复对象
             */

            binding.detailCommentItemReply.setOnItemClickListener((position,clickUserId, replayData) -> {

                Toast.makeText(itemView.getContext(),clickUserId + " position " + position,Toast.LENGTH_SHORT).show();
            });

            // 这行代码不要忘记了，这行代码才会去刷新回复列表，
            binding.detailCommentItemReply.notifyDataSetChanged();


        }
    }


}
