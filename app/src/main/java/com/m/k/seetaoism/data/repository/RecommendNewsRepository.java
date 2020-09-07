package com.m.k.seetaoism.data.repository;

import com.m.k.mvp.data.BaseRepository;
import com.m.k.mvp.data.request.MvpRequest;
import com.m.k.mvp.data.request.RequestType;
import com.m.k.mvp.manager.MvpManager;
import com.m.k.mvp.utils.Logger;
import com.m.k.mvp.utils.MvpDataFileCacheUtils;
import com.m.k.seetaoism.Constrant;
import com.m.k.mvp.base.IBaseCallBack;
import com.m.k.seetaoism.data.entity.News;
import com.m.k.seetaoism.data.entity.RecommendData;
import com.m.k.mvp.data.response.MvpResponse;
import com.m.k.mvp.data.response.ResponseType;
import com.trello.rxlifecycle4.LifecycleProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecommendNewsRepository extends BaseRepository {

    private volatile static RecommendNewsRepository mInstance;

    private static final String CACHE_FILE_NAME_PREFIX = "cache_";
    private RecommendNewsRepository(){

    }
    // 内存缓存， key 对应的栏目Id, value : 该栏目对应的数据
   private HashMap<String, RecommendData> mMemoryCache = new HashMap<>();


    public static  RecommendNewsRepository getInstance(){
        if(mInstance == null)
            synchronized (RecommendNewsRepository.class){
                if(mInstance == null){
                    mInstance = new RecommendNewsRepository();
                }
            }

        return mInstance;
    }






    @Override
    public <T> void doRequest(LifecycleProvider lifecycleProvider, MvpRequest<T> request, IBaseCallBack<T> callBack) {
       // super.doRequest(lifecycleProvider, request, callBack);

        String columnId = request.getParams().get(Constrant.RequestKey.KEY_COLUMN_ID).toString();

        switch (request.getRequestType()){

            case FIRST:{ // 第一次
                // step1  先从内存里面查找，有直接回调到P
                MvpResponse response = getFromMemory(columnId);
                if(response != null){
                    callBack.onResult(response); // 指的是viewpager 切换时发生的请求
                    return;
                }


                // step2 如果内存没有，从sdcard 查找 然后回调到P

               Observable.create((ObservableOnSubscribe<MvpResponse>) emitter -> {
                   MvpResponse<RecommendData> cacheData = readFromSdcard(columnId);
                   if(cacheData != null){
                       saveToMemory(cacheData,RequestType.FIRST,columnId);
                       emitter.onNext(cacheData);
                   }else{
                       emitter.onError(new NullPointerException("no cache data from sdcard"));
                   }

               }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(mvpResponse -> callBack.onResult(mvpResponse), new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        // step3 ,如果sdcard 没有，就从服务器取
                       doRequest(lifecycleProvider,request,new CacheTask<>(request),callBack);
                    }
                });


                break;
            }

            default:{ // 刷新和加载更多
                // 请求服务器
                doRequest(lifecycleProvider,request,new CacheTask<>(request),callBack);
                break;
            }

        }

    }


    private  class  CacheTask<T> implements Consumer<MvpResponse<T>>{

        private MvpRequest request;

        CacheTask(MvpRequest request) {
            this.request = request;
        }
        @SuppressWarnings("ALL")
        @Override
        public void accept(MvpResponse<T> mvpResponse) throws Throwable {

            // 接收到上游发送的数据，做缓存

            News news = new News();
            news.setType(7);
            News.Ad ad = new News.Ad();
            ad.setLayout(5);
            ad.setId("1232143");
            ad.setTitle("这个一个我们自己插入的大图广告");
            ad.setAd_url("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3830946526,2769361828&fm=26&gp=0.jpg");
            ad.setTarget_href("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3830946526,2769361828&fm=26&gp=0.jpg");
            news.setAd(ad);
            RecommendData recommendData = (RecommendData) mvpResponse.getData();
          //  recommendData.getNewsList().add(0,news);



            news = new News();
            news.setType(7);
             ad = new News.Ad();
            ad.setLayout(4);
            ad.setId("1232144");
            ad.setAd_url("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2428034512,2003179618&fm=26&gp=0.jpg");
            ad.setTarget_href("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1599046487679&di=0f28f382115333e2e94f89689616bc88&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fddd6368f875f0434e979aa256832cfc48dc490e6113af-tMIEBd_fw658");
            news.setAd(ad);
            recommendData = (RecommendData) mvpResponse.getData();
           // recommendData.getNewsList().add(3,news);



            news = new News();
            news.setType(7);
            ad = new News.Ad();
            ad.setLayout(6);
            ad.setId("1232145");
            ad.setAd_url("https://res.exexm.com/cw_145225549855002");
            ad.setTarget_href("https://res.exexm.com/cw_145225549855002");
            ad.setTitle("我是自己插入的视频广告");
            news.setAd(ad);
            recommendData = (RecommendData) mvpResponse.getData();
            //recommendData.getNewsList().add(8,news);


            news = new News();
            news.setType(7);
            ad = new News.Ad();
            ad.setLayout(7);
            ad.setId("1232146");
            ad.setAd_url("http://7xjmzj.com1.z0.glb.clouddn.com/20171026175005_JObCxCE2.mp4");
            ad.setTarget_href("http://7xjmzj.com1.z0.glb.clouddn.com/20171026175005_JObCxCE2.mp4");
            news.setAd(ad);
            recommendData = (RecommendData) mvpResponse.getData();
            //recommendData.getNewsList().add(0,news);

            String key =  request.getParams().get(Constrant.RequestKey.KEY_COLUMN_ID).toString();

            saveToMemory((MvpResponse<RecommendData>) mvpResponse,request.getRequestType(),key);

            saveToSdcard((MvpResponse<RecommendData>) mvpResponse,key);
        }
    }


    private void saveToMemory(MvpResponse<RecommendData> response, RequestType type,String key){

        if(type != RequestType.LOAD_MORE){ // 不是加载更多的情况下，清空，替换
            mMemoryCache.remove(key); // 清空内存


            RecommendData recommendData = new RecommendData();
            RecommendData serverData = response.getData();
            recommendData.setAlbumId(serverData.getAlbumId());
            recommendData.setAlbumNews(serverData.getAlbumNews());
            recommendData.setAlbumTitle(serverData.getAlbumTitle());
            recommendData.setBannerList(serverData.getBannerList());
            recommendData.setFlashId(serverData.getFlashId());
            recommendData.setFlashNews(serverData.getFlashNews());
            recommendData.setMore(serverData.getMore());
            recommendData.setPointTime(serverData.getPointTime());
            recommendData.setStart(serverData.getStart());


            recommendData.setNewsList(new ArrayList<>(serverData.getNewsList()));
            mMemoryCache.put(key,recommendData);

            Logger.d(" list code = %s",response.getData().getNewsList().hashCode());
        }else{

            // 加载更多，追加
            RecommendData cacheData = mMemoryCache.get(key);

            RecommendData serverData = response.getData();
            Logger.d("  load more list code = %s",cacheData.getNewsList().hashCode());

            if(cacheData != null && serverData != null){
                cacheData.setStart(serverData.getStart());
                cacheData.setNumber(serverData.getNumber());
                cacheData.setPointTime(serverData.getPointTime());
                cacheData.setMore(serverData.getMore());
                cacheData.getNewsList().addAll(serverData.getNewsList());
            }


        }
    }


    private  void saveToSdcard(MvpResponse<RecommendData> response,String key){

        MvpDataFileCacheUtils.saveEncryptedDataToFile(getCacheDataSdcardFile(key),response.getData());
    }

    private MvpResponse getFromMemory(String key){
        RecommendData data = mMemoryCache.get(key);
        if(data != null){
            MvpResponse<RecommendData> response = new MvpResponse<>();
            return response.setData(data).setCode(1).requestType(RequestType.FIRST).responseType(ResponseType.MEMORY);
        }
        return null;
    }

    private MvpResponse<RecommendData> readFromSdcard(String key){
      RecommendData data =  MvpDataFileCacheUtils.getencryptedDataFromFile(getCacheDataSdcardFile(key),RecommendData.class);

      if( data != null){
          MvpResponse<RecommendData> response = new MvpResponse<>();
          return response.setData(data).setCode(1).requestType(RequestType.FIRST).responseType(ResponseType.SDCARD);
      }
      return null;
    }

    private File getCacheDataSdcardFile(String columnId){
        return  new File(MvpManager.getExternalCacheDir(),CACHE_FILE_NAME_PREFIX + columnId);
    }

    public static void destroy(){
        mInstance = null;
    }
}
