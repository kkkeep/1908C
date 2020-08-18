package com.m.k.seetaoism.splash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.m.k.mvp.widgets.MvpCommonPopView;
import com.m.k.seetaoism.R;

public class AgreementPop extends MvpCommonPopView {

    private TextView mBtnAgree;
    private TextView mBtnCancel;

    private IPopClickListener mListener;

    public AgreementPop(Context context) {
        super(context);
        initView(context);
    }


    public void setListener(IPopClickListener listener) {
        this.mListener = listener;
    }

    private void initView(Context context){

        View v  = LayoutInflater.from(context).inflate(R.layout.layout_splash_pop,null);

        mBtnAgree = v.findViewById(R.id.splash_pop_btn_agree);
        mBtnCancel = v.findViewById(R.id.splash_pop_btn_stop);


        mBtnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onAgree();
                }
            }
        });



        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onCancel();
                }
            }
        });


        mBtnCancel.setFocusable(false);
        mBtnAgree.setFocusable(false);

        setContentView(v);

        setOnBackKeyDismiss(false);



    }


    public interface IPopClickListener{

        void onCancel();
        void onAgree();
        void onUserAgreement();
        void onPrivacyPolicy();

    }

}
