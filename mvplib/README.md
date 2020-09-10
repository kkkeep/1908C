MVP  框架使用手册：



1. 把整个 mvplib 拷贝到自己工程里面里面

2. 在 settings.gradle 文件里面 增加 `include ':mvplib'`

3. 在工程的根目录下的 build.gradle 文件里面增加 

   1. ```groovy
      
      allprojects {
          repositories {
              google()
              jcenter()
              maven { url 'https://jitpack.io' }
          }
      }
      ```

      

4. 在自己的app 目录下的 proguard-rules.pro 文件里面增加：

   1. ```properties
      -keep class com.m.k.anotaion.**{*;}
      
      -keep @com.m.k.anotaion.ApiService class * {*;}
      
      -keepclassmembers class * {
         @com.m.k.anotaion.BaseUrl <fields>;
      }
      -keep @com.m.k.anotaion.GsonConverter class * {*;}
      -keep @com.m.k.anotaion.MvpEntity class * {*;}
      -keep @com.m.k.anotaion.OkInterceptor class * {*;}
      
      
      -keep @com.m.k.anotaion.Umeng class * {*;}
      
      -keepclassmembers class * {
        @com.m.k.anotaion.Umeng *;
      }
      ```

5. 在自己 app 目录下的 build.gradle 文件里面添加依赖：

   1. ```groovy
       implementation project(path: ':mvplib')
       annotationProcessor project(path: ':mvplib:libMvpAnotation:compiler')
      ```

6. 配置服务器请求的 BaseUrl

   1. 在任意的一个类里面定义一个 BASE_URL  的常量,常量的名字任意,在其上添加 ` @BaseUrl`

      1. ```java
          @BaseUrl
          String BASE_URL = "https://www.seetao.com";
         ```

7. 配置实体类

   1. 自己定义一个实体类 并且实现 IMvpEntity 接口,重写其中的方法，并且添加上`@MvpEntity` 注解

      ```java
      @MvpEntity
      public  class HttpResult<T> implements IMvpEntity<T> 
        
        
       
      public interface IMvpEntity<T> {
      
          boolean isOk(); // 表示是否请求成功
          String getMessage();// 服务器返回的提示信息
          T getData(); // 服务器返回的我们真正要的 json 对应的对象
          int getCode(); // 服务器返回的colde
      }
      
      ```

8. 配置自己的 Retrofit  service 接口，比如你想定义一个自己的 ApiService **(非必须)**:

   定义一个接口，添加`@ApiService` 注解

   ```java
   @ApiService
   public interface AppApiService {
     	@POST("url")
       @FormUrlEncoded
       Observable<Bean<data>> getUser( @FieldMap HashMap<String,Object> map);
   }
   ```

9. 配置自定义 Retrofit 的  Json 串的转换器，这种情况一般是对 自己公司返回的json 串有特殊处理**（非必须）**

   1. 定义一个类 继承 MvpGsonConverterFactory 并为其添加 `@GsonConverter` 注解，例如：

      1. ```java
         @GsonConverter
         public class JDGsonConverterFactory extends MvpGsonConverterFactory {
           @Override
             public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                 TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
                 return new JDGsonResponseBodyConverter<>(gson, adapter,type);
         
         
             }
         }
           
         ```

      

   2. 定义一个类继承MvpGsonResponseBodyConverter  继承 MvpGsonResponseBodyConverter,重写`handJson` 方法 例如，如下是因为服务器返回失败时，会给 data 这个key 对应的 value 返回一个 空窜，我们Gosn 在把 json 串转对象时 会报一个 json 转换异常，是一位一个空的字符串是不能够转成一个 非 String 的对象。所以自己重新了 handJson 方法，在 把json 交给 Gson 转换之前把 data 为空串的时把data 移除。

      1. ```java
         public class JDGsonResponseBodyConverter<T> extends MvpGsonResponseBodyConverter<T> {
         
             public JDGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter, Type type) {
                 super(gson, adapter, type);
             }
         
             @Override
             public String handJson(String json) {
               
                 try {
                     JSONObject jsonObject = new JSONObject(json);
         
                     if(!jsonObject.isNull("code")){
                         int code = jsonObject.getInt("code");
                         if(code != 1){ // 失败
                             if(!jsonObject.isNull("data")){
                                 String data = jsonObject.getString("data");
                                // 如果 data 是一个空字符串
                                 if(TextUtils.isEmpty(data)){
                                     jsonObject.remove("data");
                                     return jsonObject.toString();
                                 }
                             }
                         }
                     }
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
                 return json;
         
             }
         }
         ```

10. 自定义Okhttp 拦截器，在自己的拦截器上加上`@OkInterceptor(1)`  注解，这个注解需要一个参数，参数代表了拦截的顺序，主要是针对有多个自己定义拦截器的时候有用**（非必须）**,例如自定义两个拦截器：

    ```java
    @OkInterceptor(1)
    public class Intercepter1 implements Interceptor{}
      
    @OkInterceptor(2)
    public class Intercepter2 implements Interceptor{}
    
    ```

    

    

    

11.  公共参数配置，如果你项目里面需要传递公共参数例如**(非必须)**：

    ```java
    //1. 在Applicaiton 的 onCreate 方法里面，调用 MvpManger.setParamsGetter 方法 传一个 ParamsGetter 的对象
      
     MvpManager.setParamsGetter(new ParamsGetter() {
                @Override
                public HashMap<String, Object> getParams() {
                    return ParamsUtils.getCommonParams();
                }
                
            });
      
    public interface ParamsGetter {
    
        HashMap<String,Object> getParams(); // 返回公共参数
    
        default HashMap<String,Object> getHeaders(){ // 返回公共请求头，如果不重新就是没有
            return new HashMap<>();
        }
    }
     
       
    ```

    