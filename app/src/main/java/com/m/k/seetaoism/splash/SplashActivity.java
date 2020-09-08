package com.m.k.seetaoism.splash;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.m.k.mvp.data.BaseRepository;
import com.m.k.mvp.data.request.GetRequest;
import com.m.k.mvp.manager.MvpManager;
import com.m.k.mvp.manager.MvpUserManager;
import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.R;
import com.m.k.mvp.base.BaseActivity;
import com.m.k.mvp.base.NoResultCallBack;
import com.m.k.seetaoism.auth.Login.AuthActivity;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.mvp.data.response.MvpResponse;
import com.m.k.seetaoism.home.HomeActivity;
import com.m.k.seetaoism.utils.ParamsUtils;
import com.m.k.systemui.SystemBarConfig;

import io.reactivex.rxjava3.functions.Consumer;
import me.jessyan.autosize.internal.CancelAdapt;

public class SplashActivity extends BaseActivity implements CancelAdapt {

    private static final int SPLASH_TIME = 3000; //  3秒后跳转。

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(MvpManager.isShowGuidePage()) { // 第一次安装，或者 升级，或者清除数据
            AgreementPop agreementPop = new AgreementPop(this);
            agreementPop.setListener(new AgreementPop.IPopClickListener() {
                @Override
                public void onCancel() {
                    // 退出，关闭当前activity
                    MvpManager.launchFail();
                    finish();
                }

                @Override
                public void onAgree() {
                    // 显示引导页
                    agreementPop.dismiss();
                    showGuidePage();
                }

                @Override
                public void onUserAgreement() {

                }

                @Override
                public void onPrivacyPolicy() {

                }
            });

            getWindow().getDecorView().post(new Runnable() {
                @Override
                public void run() {
                    agreementPop.showCenter(getWindow().getDecorView());
                }
            });


        }else{

            // 设置为全屏
            SystemBarConfig config = new SystemBarConfig(this).enterFullScreen(SystemBarConfig.MODE_HIDE_LEAN_BACK);
            //config.apply();

            getWindow().getDecorView().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }
            },SPLASH_TIME);

            getUserInfo();


            // 获取用户信息。
            // 发送一个网络请求获取用户信息。之所以在此处获取用户信息，是为了用户进入我的页面时，用户信息以及提前准备好了。

        }

    }


    private void showGuidePage(){
        setContentView(R.layout.activity_splash);
        startActivity(new Intent(this, HomeActivity.class));

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }


    private void getUserInfo(){

      String token =   MvpUserManager.getToken();
      long expireTime = MvpUserManager.getTokenExpireTime();

      if(TextUtils.isEmpty(token)){
          return;
      }

        GetRequest<User> userRequest = new GetRequest<>(Constrant.URL.GET_USER);
        userRequest.setParams(ParamsUtils.getCommonParams());
        userRequest.getParams().put(Constrant.RequestKey.KEY_TOKEN,token);
        userRequest.setType(User.class);


        new BaseRepository().doRequest(null, userRequest, new Consumer<MvpResponse<User>>() {
            @Override
            public void accept(MvpResponse<User> response) throws Throwable {
                if (response.isOk()) {
                    // 由于后去用户信息的接口返回的User 对象里面没有token

                    User.Token tokenObj = new User.Token();

                    tokenObj.setValue(token);
                    tokenObj.setExpire_time(expireTime);

                    response.getData().setToken(tokenObj);

                    MvpUserManager.login(response.getData());
                }
            }
        }, new NoResultCallBack<>());

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
