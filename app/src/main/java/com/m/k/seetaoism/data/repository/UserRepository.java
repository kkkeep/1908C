package com.m.k.seetaoism.data.repository;

import com.m.k.seetaoism.auth.Login.pwd.PasswordLoginContract;
import com.m.k.seetaoism.base.IBaseCallBack;
import com.m.k.seetaoism.data.BaseRepository;
import com.m.k.seetaoism.data.entity.HttpResult;
import com.m.k.seetaoism.data.entity.User;
import com.m.k.seetaoism.data.net.ok.DataService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

public class UserRepository extends BaseRepository implements PasswordLoginContract.ILoginMode {



    @Override
    public void login(HashMap<String, String> params, IBaseCallBack<User> callBack) {
    }
}
