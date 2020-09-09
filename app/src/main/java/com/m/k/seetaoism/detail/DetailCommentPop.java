package com.m.k.seetaoism.detail;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import com.m.k.mvp.utils.KeyboardUtils;
import com.m.k.mvp.utils.Logger;
import com.m.k.mvp.widgets.MvpCommonPopView;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.databinding.LayoutDetailCommentPopBinding;
import com.umeng.socialize.utils.UmengText;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class DetailCommentPop extends MvpCommonPopView {

    public static final int TYPE_COMMENT = 0X100;
    public static final int TYPE_REPLAY = 0X101;


    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @IntDef({TYPE_COMMENT,TYPE_REPLAY})
    public @interface ShowType{ }

    private LayoutDetailCommentPopBinding binding;


    private OnSendCallBack mOnSendCallBack;



    public DetailCommentPop(Context context,@NonNull  OnSendCallBack callBack) {
        super(context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.mOnSendCallBack = callBack;
    }


    @Override
    protected void setView(Context context) {

        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_detail_comment_pop,null,false);
        binding = LayoutDetailCommentPopBinding.bind(contentView);
        setContentView(contentView);

        binding.commentTvCancel.setOnClickListener(this::close);


        binding.commentTvSent.setOnClickListener(v -> {
            mOnSendCallBack.onSend(binding.commentEtContent.getText().toString());
            close(v);
        });




    }


    @Override
    public void dismiss() {
        super.dismiss();

        mOnSendCallBack.onSaveContent(binding.commentEtContent.getText().toString());
    }

    private void close(View v){
        KeyboardUtils.hide(getContentView());
        dismiss();
    }
/*
    public void showComment(View view,@ShowType int showType,String savedContent){
        showCenter(view);
        if(showType == TYPE_COMMENT){
            if(TextUtils.isEmpty(savedContent)){
                binding.commentEtContent.setHint(R.string.text_comment);
            }else{
                binding.commentEtContent.setText(savedContent);
            }
        }else if(showType == TYPE_REPLAY){
            if(TextUtils.isEmpty(savedContent)){
                binding.commentEtContent.setHint(view.getContext().getResources().getString(R.string.text_REPLAY_HINT,));
            }else{
                binding.commentEtContent.setText(savedContent);
            }
        }

    }*/

    public void showRelay(View view, String toUserName){
        showCenter(view);
        binding.commentEtContent.setHint(view.getContext().getString(R.string.text_REPLAY_HINT,toUserName));


    }

    public void showComment(View view,  String savedContent){
        showCenter(view);
        if(TextUtils.isEmpty(savedContent)){
            binding.commentEtContent.setHint(R.string.text_comment);
        }else{
            binding.commentEtContent.setText(savedContent);
        }

    }



    @Override
    public void showCenter(View view) {
        super.showCenter(view);
        // 确保 pop 已经完全显示，所以采用延迟显示软键盘
        view.postDelayed(() -> KeyboardUtils.show(binding.commentEtContent),50);

    }


    public interface  OnSendCallBack{

        void onSend(String content);
        default void onSaveContent(String content){

        };
    }
}
