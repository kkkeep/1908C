
https://developer.umeng.com/docs/66632/detail/66639#h3--

1. 自己去友盟，微信，qq，微博等平台注册账号，拿到各种key，appId

   > 注意： 注册账号的时候，包名（applicationId）和签名信息一定不要搞错了。签名信息一定要用你自己准备用来打包的那个签名文件里面的签名信息(MD5)

2. 默认已经集成了 QQ,微信，微博。如果需要增加新的分享或者第三方登录的平台，只需要按照集成文档粘贴过去即可

4. 在ShareUtils.java 这个类中 把自己的ID 替换掉即可

4. 在你自己app 代码里面 任意地方（类，字段，方法）加一个 @Umeng("your.applicationId")

5. 对于 QQ ,微博等平台，需要在你 分享或者登录的 Activity 中重写，否则收不到回调 ：

   ```java
   
       @Override
       protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
           super.onActivityResult(requestCode, resultCode, data);
   
           UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
       }
   ```

   

6. 如果需要升级友盟分享：

   1. 替换Libs 下面的jar 和 aar 包。
   2. 替换 res 目录下的所有资源文件
   3. 替换 consumer-rules.pro 下面的混淆规则
   4. 更具最新版的改动在在相应的地放修改（主要是AndroidManifest 文件）

> 注意：
>
>  // wx 的 有效期是1个月。过了1个月就必须重新授权登录。没有过期则不用跳转到授权页面，直接返回用户相关信息。
>
>   // qq 的 access_token_有效期是三个月。过了三个月就必须重新授权登录。没有过期则不用跳转到授权页面，直接返回用户相关信息。
>
> qq 不支持纯文本分享
>
> qq 和 微信 不支持图文同时分享，只会把图片分享过去。
>
> [微信分享成功后，停留在微信页面，是没有回调的](https://developer.umeng.com/docs/66632/detail/66791?um_channel=sdk)

[友盟问题汇总](https://developer.umeng.com/docs/128606/cate/129514)




