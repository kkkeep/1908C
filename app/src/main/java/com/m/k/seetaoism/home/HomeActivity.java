package com.m.k.seetaoism.home;

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
import com.m.k.seetaoism.auth.Login.pwd.PasswordLoginFragment;
import com.m.k.seetaoism.base.BaseFragment;
import com.m.k.seetaoism.manager.MvpFragmentManager;
import com.m.k.seetaoism.widgets.MvpLoadingView;


public class HomeActivity extends AppCompatActivity  {


    public static final String KEY_FROM = "from_1";

    private View button1;
    private View button2;


    private BaseFragment mCurrent;
    private MvpLoadingView loadingView;

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
                ViewGroup viewGroup = findViewById(android.R.id.content);

                loadingView = (MvpLoadingView) LayoutInflater.from(HomeActivity.this).inflate(R.layout.layout_loading_view,viewGroup,false);

                loadingView.setParentContainer(viewGroup);
                loadingView.showLoading(MvpLoadingView.MODE_POP);
            }
        });



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //mCurrent = MvpFragmentManager.addOrShowFragment(getSupportFragmentManager(),Fragment2.class,mCurrent,R.id.home_fragment_container);
                loadingView.closeLoading();
            }
        });
        //MvpFragmentManager.addOrShowFragment(getSupportFragmentManager(),RecommendFragment2.class,R.id.auth_fragment_container);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



}
