package com.m.k.seetaoism.auth.Login;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.auth.Login.pwd.PasswordLoginFragment;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        showPasswordLoginFragment();
    }



    public void showPasswordLoginFragment(){

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();


        PasswordLoginFragment passwordLoginFragment = new PasswordLoginFragment();

        transaction.add(R.id.auth_fragment_container,passwordLoginFragment);

        transaction.commitNow();

    }
}
