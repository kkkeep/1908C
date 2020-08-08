package com.m.k.seetaoism.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.m.k.banner.Banner;
import com.m.k.mvp.manager.MvpManager;
import com.m.k.mvp.manager.MvpUserManager;
import com.m.k.seetaoism.Constrant;
import com.m.k.seetaoism.R;
import com.m.k.seetaoism.auth.Login.AuthActivity;
import com.m.k.seetaoism.base.BaseActivity;
import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.data.entity.NewsBanner;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.net.request.GetRequest;
import com.m.k.seetaoism.data.net.response.MvpResponse;
import com.m.k.seetaoism.data.repository.BaseRepository;
import com.m.k.seetaoism.utils.ParamsUtils;
import com.m.k.systemui.SystemBarConfig;

import java.util.ArrayList;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class SplashActivity extends BaseActivity {

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
            config.apply();
           // startActivity(new Intent(this, AuthActivity.class));
           // getUserInfo();


            setContentView(R.layout.item_home_recomment_banner);

            Banner banner = findViewById(R.id.banner);

            ArrayList<NewsBanner> arrayList = new ArrayList<>();

            arrayList.add(new NewsBanner("我是banner Tittle 1"));
            arrayList.add(new NewsBanner("我是banner Tittle 2"));
            arrayList.add(new NewsBanner("我是banner Tittle 3"));
            arrayList.add(new NewsBanner("我是banner Tittle 4"));

            banner.setData(arrayList);


            // 获取用户信息。
            // 发送一个网络请求获取用户信息。之所以在此处获取用户信息，是为了用户进入我的页面时，用户信息以及提前准备好了。


        }

    }


    private void showGuidePage(){
        setContentView(R.layout.activity_splash);
        startActivity(new Intent(this, AuthActivity.class));

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }



    private Disposable disposable;

    private void getUserInfo(){

      String token =   MvpUserManager.getToken();

     /* if(TextUtils.isEmpty(token)){
          return;
      }*/



        GetRequest<User> userRequest = new GetRequest<>(Constrant.URL.GET_USER);
        userRequest.setParams(ParamsUtils.getCommonParams());
        userRequest.getParams().put(Constrant.RequestKey.KEY_TOKEN,token);
        userRequest.setType(User.class);


        new BaseRepository().doRequest(null, userRequest, new Consumer<MvpResponse<User>>() {
            @Override
            public void accept(MvpResponse<User> response) throws Throwable {

                if (response.isOk()) {
                    MvpUserManager.login(response.getData());
                }
            }
        }, new IBaseCallBack<User>() {
            @Override
            public void onStart(Disposable disposable) {

            }

            @Override
            public void onResult(MvpResponse<User> response) {

            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
       // disposable.dispose();
    }
}
