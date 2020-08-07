package com.m.k.seetaoism.auth.Login.code;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.m.k.seetaoism.R;
import com.m.k.seetaoism.auth.Login.pwd.PasswordLoginFragment;
import com.m.k.seetaoism.base.v.BaseSmartFragment1;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.net.response.MvpResponse;
import com.m.k.seetaoism.manager.MvpFragmentManager;

public class CodeLoginFragment extends BaseSmartFragment1<User> {

    private TextView mTvLicense;
    private TextView mTvPasswordLogin;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_code_login;
    }

    @Override
    protected void initView() {

        mTvLicense = findViewById(R.id.auth_code_login_tv_license);
        mTvPasswordLogin = findViewById(R.id.auth_code_login_tv_go_password_login);


        mTvPasswordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MvpFragmentManager.addOrShowFragment(getFragmentManager(), PasswordLoginFragment.class,CodeLoginFragment.this,R.id.auth_fragment_container);
            }
        });

        setLicense();





    }

    @Override
    public void onResult1(MvpResponse<User> response) {

    }



    private void setLicense(){

        SpannableString spannableString = new SpannableString(getString(R.string.text_user_license));


        ClickableSpan licenseSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                showToast("用户协议点击");
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);
                ds.setUnderlineText(false);// 去掉下划线
            }
        };


        ClickableSpan privacySpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                showToast("隐私政策点击");

            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);
                ds.setUnderlineText(false);// 去掉下划线
            }
        };




        // setSpan 方法的最后一 flag 个参数，是没有任何任意，统一都 start 字符包含，end 不包含，这个 flag 只是在 insert 出入新的字符时有用
        // 比如我设置的flag = SPAN_INCLUSIVE_INCLUSIVE,
        // 那么在 start 插入一个字符，被插入的这个字符就会使用 设置的这个span 的效果。
        // 在end  插入一个字符。被插入的字符就不会使用设置的这个span 的效果


        spannableString.setSpan(licenseSpan,9,13, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(privacySpan,14,spannableString.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        mTvLicense.setMovementMethod(LinkMovementMethod.getInstance());

        mTvLicense.setHighlightColor(Color.TRANSPARENT);
        mTvLicense.setText(spannableString);
    }


    @Override
    public boolean isNeedAddToBackStack() {
        return false;
    }


    @Override
    public int getEnterAnimation() {
        return 0;
    }


}
