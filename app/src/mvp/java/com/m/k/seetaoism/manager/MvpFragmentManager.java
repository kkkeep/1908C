package com.m.k.seetaoism.manager;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.m.k.seetaoism.base.BaseFragment;

import java.time.temporal.TemporalAccessor;

public class MvpFragmentManager {


    /**
     * add
     * remove
     * replace -> （add remove）
     *
     * hide
     * show
     * attach
     * detach
     */


    public static BaseFragment addOrShowFragment(FragmentManager manager, Class<? extends BaseFragment> fragmentClass,BaseFragment lastFragment,@IdRes int containerId){

        FragmentTransaction transaction = manager.beginTransaction();
        try {

            String tag = fragmentClass.getName();

            BaseFragment  baseFragment = (BaseFragment) manager.findFragmentByTag(tag);

            if(baseFragment == null){ // 当前activity 已经添加了 该fragment
                baseFragment = fragmentClass.newInstance();
                transaction.add(containerId,baseFragment, tag);
                transaction.addToBackStack(tag);
            }else{ // 当前 activity 已经添加过这个fragment
                if(!baseFragment.isAdded()){ // 如果没有添加
                    transaction.add(containerId,baseFragment, tag);
                }
                if(baseFragment.isDetached()){ // 如果从activity  detach 了
                    transaction.attach(baseFragment);
                }
                if(baseFragment.isHidden()){// 如果被隐藏了
                    transaction.show(baseFragment);
                }
            }
            if(lastFragment != null){ // 如果之前有fragment 存在。即上一个fragment
               transaction.hide(lastFragment); // 对上一个fragment 隐藏
            }

            transaction.commit();
            return baseFragment; // 把自己（即将要显示的） 返回出去。
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
