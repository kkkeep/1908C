package com.m.k.seetaoism.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.home.recommend.RecommendFragment;


public class HomeActivity extends AppCompatActivity {


    public static final String KEY_FROM = "from_1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        showRecommendFragment();
    }


    public void showRecommendFragment(){

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();


        RecommendFragment fragment = new RecommendFragment();

        transaction.add(R.id.home_fragment_container,fragment);

        transaction.commitNow();

    }


}
