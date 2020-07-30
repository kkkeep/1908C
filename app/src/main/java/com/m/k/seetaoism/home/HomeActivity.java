package com.m.k.seetaoism.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.auth.Login.pwd.PasswordLoginContract;
import com.m.k.seetaoism.auth.Login.pwd.PasswordLoginFragment;
import com.m.k.seetaoism.auth.Login.pwd.PasswordLoginPresenter;
import com.m.k.seetaoism.base.BaseFragment;
import com.m.k.seetaoism.base.p.IBasePresenter;
import com.m.k.seetaoism.base.v.MvpBaseActivity;
import com.m.k.seetaoism.manager.MvpFragmentManager;
import com.m.k.seetaoism.widgets.MvpLoadingView;


public class HomeActivity extends MvpBaseActivity<PasswordLoginContract.ILoginPresenter> {


    public static final String KEY_FROM = "from_1";

    private View button1;
    private View button2;


    private BaseFragment mCurrent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        button1 = findViewById(R.id.btn_fragment1);
        button2 = findViewById(R.id.btn_fragment2);

        mCurrent =  MvpFragmentManager.addOrShowFragment(getSupportFragmentManager(),Fragment1.class,mCurrent,R.id.home_fragment_container);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  ViewGroup viewGroup = findViewById(android.R.id.content);
                loadingView = (MvpLoadingView) LayoutIn flater.from(HomeActivity.this).inflate(R.layout.layout_loading_view,viewGroup,false);
                loadingView.setParentContainer(viewGroup);
                loadingView.showLoading(MvpLoadingView.MODE_POP);*/
                showPopLoading();
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //mCurrent = MvpFragmentManager.addOrShowFragment(getSupportFragmentManager(),Fragment2.class,mCurrent,R.id.home_fragment_container);
               // closeLoading();

                onError("网络除了点问题，点击下面按钮重试", new MvpLoadingView.OnRetryCallBack() {
                    @Override
                    public void onRetry() {
                        //

                    }
                });


            }
        });
        //MvpFragmentManager.addOrShowFragment(getSupportFragmentManager(),RecommendFragment2.class,R.id.auth_fragment_container);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public PasswordLoginContract.ILoginPresenter createPresenter() {
        return new PasswordLoginPresenter();
    }

    @Override
    public Context getMvpContent() {
        return this;
    }

    @Override
    public IBasePresenter getPresenter() {
        return mPresenter;
    }
}
