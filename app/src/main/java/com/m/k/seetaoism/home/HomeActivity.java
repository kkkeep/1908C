package com.m.k.seetaoism.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.base.BaseFragment;
import com.m.k.seetaoism.manager.MvpFragmentManager;


public class HomeActivity extends AppCompatActivity  {


    public static final String KEY_FROM = "from_1";

    private Button button1;
    private Button button2;

    private BaseFragment mCurrent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        button1 = findViewById(R.id.btn_fragment1);
        button2 = findViewById(R.id.btn_fragment2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mCurrent =  MvpFragmentManager.addOrShowFragment(getSupportFragmentManager(),Fragment1.class,mCurrent,R.id.home_fragment_container);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mCurrent = MvpFragmentManager.addOrShowFragment(getSupportFragmentManager(),Fragment2.class,mCurrent,R.id.home_fragment_container);
            }
        });
        //MvpFragmentManager.addOrShowFragment(getSupportFragmentManager(),RecommendFragment2.class,R.id.auth_fragment_container);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
