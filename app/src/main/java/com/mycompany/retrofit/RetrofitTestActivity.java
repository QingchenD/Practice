package com.mycompany.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.service.media.MediaBrowserService;
import android.support.annotation.Nullable;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

import io.reactivex.Observable;

public class RetrofitTestActivity extends Activity {

    private String TAG = "RetrofitTestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        //initPostPerson();

        initRetrofitRxJava();

        initRetrofitRxJavaWithConvert();

        //initRetrofitRxJavaWithJsonConvert(); // 有内存泄漏 !!!!!!!
    }

    private void init() {
//        Gson gson = new GsonBuilder()
//                //配置你的Gson
//                .setDateFormat("yyyy-MM-dd hh:mm:ss")
//                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.101:6789/get_data.json/")
                .callbackExecutor(Executors.newSingleThreadExecutor())
                //.addConverterFactory(GsonConverterFactory.create(gson))
                .build();

/*
        BlogService service = retrofit.create(BlogService.class);
        Call<ResponseBody> call = service.getBlog(2);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i(TAG, response.body().toString());
                } catch (Exception e) {
                    Log.i(TAG, " Error:");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
*/
        DefaultService defaultService = retrofit.create(DefaultService.class);
        Call<ResponseBody> defaultCall = defaultService.getDefault();
        defaultCall.enqueue(new Callback<ResponseBody> () {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.i(TAG, " Default:" +  response.body().string());
                } catch (Exception e) {
                    Log.i(TAG, " Error:");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public interface BlogService {
        @GET("blog/{id}")
        Call<ResponseBody> getBlog(@Path("id") int id);
    }

    public interface DefaultService {
        @GET("/")
        Call<ResponseBody> getDefault();
    }

    public interface HttpService {
        /**
         * method 表示请求的方法，区分大小写
         * path表示路径
         * hasBody表示是否有请求体
         */
        @HTTP(method = "GET", path = "blog/{id}", hasBody = false)
        Call<ResponseBody> getBlog(@Path("id") int id);

        /**
         * {@link FormUrlEncoded} 表明是一个表单格式的请求（Content-Type:application/x-www-form-urlencoded）
         * <code>Field("username")</code> 表示将后面的 <code>String name</code> 中name的取值作为 username 的值
         */
        @POST("/form")
        @FormUrlEncoded
        Call<ResponseBody> testFormUrlEncoded1(@Field("username") String name, @Field("age") int age);


        /**
         * Map的key作为表单的键
         */
        @POST("/form")
        @FormUrlEncoded
        Call<ResponseBody> testFormUrlEncoded2(@FieldMap Map<String, Object> map);

        /**
         * {@link Part} 后面支持三种类型，{@link RequestBody}、{@link okhttp3.MultipartBody.Part} 、任意类型
         * 除 {@link okhttp3.MultipartBody.Part} 以外，其它类型都必须带上表单字段({@link okhttp3.MultipartBody.Part} 中已经包含了表单字段的信息)，
         */
        @POST("/form")
        @Multipart
        Call<ResponseBody> testFileUpload1(@Part("name") RequestBody name, @Part("age") RequestBody age, @Part MultipartBody.Part file);

        /**
         * PartMap 注解支持一个Map作为参数，支持 {@link RequestBody } 类型，
         * 如果有其它的类型，会被{@link retrofit2.Converter}转换，如后面会介绍的 使用{@link com.google.gson.Gson} 的 {@link retrofit2.converter.gson.GsonRequestBodyConverter}
         * 所以{@link MultipartBody.Part} 就不适用了,所以文件只能用<b> @Part MultipartBody.Part </b>
         */
        @POST("/form")
        @Multipart
        Call<ResponseBody> testFileUpload2(@PartMap Map<String, RequestBody> args, @Part MultipartBody.Part file);

        @POST("/form")
        @Multipart
        Call<ResponseBody> testFileUpload3(@PartMap Map<String, RequestBody> args);


        @GET("/headers?showAll=true")
        @Headers({"CustomHeader1: customHeaderValue1", "CustomHeader2: customHeaderValue2"})
        Call<ResponseBody> testHeader(@Header("CustomHeader3") String customHeaderValue3);


        /**
         * 当GET、POST...HTTP等方法中没有设置Url时，则必须使用 {@link Url}提供
         * 对于Query和QueryMap，如果不是String（或Map的第二个泛型参数不是String）时
         * 会被默认会调用toString转换成String类型
         * Url支持的类型有 okhttp3.HttpUrl, String, java.net.URI, android.net.Uri
         * {@link retrofit2.http.QueryMap} 用法和{@link retrofit2.http.FieldMap} 用法一样，不再说明
         */
        @GET //当有URL注解时，这里的URL就省略了
        Call<ResponseBody> testUrlAndQuery(@Url String url, @Query("showAll") boolean showAll);

    }

    private void initPostPerson() {
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.101:6789/get_data.json/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PostService service = retrofit.create(PostService.class);
        Person person = new Person();
        person.id = "新建的Blog";
        person.version = "测试";
        person.name = "怪盗kidou";
        Call<Person> call = service.createBlog(person);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                // 已经转换为想要的类型了
                Person result = response.body();
                System.out.println(result);
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void initRetrofitRxJava() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.101:6789/get_data.json/")
                .addConverterFactory(GsonConverterFactory.create())
                // 针对rxjava2.x
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RxJavaService service = retrofit.create(RxJavaService.class);
        service.getData()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError");
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(ResponseBody msg) {
                        try {
                            Log.i(TAG, " RetroFitRxJava:" + msg.string());
                        } catch (Exception e) {
                            Log.i(TAG, " Error:");
                            e.printStackTrace();
                        }
                    }
                });

    }


    private void initRetrofitRxJavaWithConvert() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.101:6789/get_data.json/")
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 针对rxjava2.x
                .build();

        RxJavaServiceConvert service = retrofit.create(RxJavaServiceConvert.class);
        service.getData()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError");
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(String msg) {
                        try {
                            Log.i(TAG, " Convert:" + msg);
                        } catch (Exception e) {
                            Log.i(TAG, " Error:");
                            e.printStackTrace();
                        }
                    }
                });

    }


    private void initRetrofitRxJavaWithJsonConvert() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.101:6789/get_data.json/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 针对rxjava2.x
                .build();

        GsonService service = retrofit.create(GsonService.class);
        service.getData()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Gson>() {
                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError");
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(Gson msg) {
                        try {
                            Log.i(TAG, " GSON:" + msg.toString());
                        } catch (Exception e) {
                            Log.i(TAG, " Error:");
                            e.printStackTrace();
                        }
                    }
                });

    }




    public interface RxJavaService {
        @GET("/")
        Observable<ResponseBody> getData();
    }

    public interface GsonService {
        @GET("/")
        Observable<Gson> getData();
    }

    public interface RxJavaServiceConvert {
        @GET("/")
        Observable<String> getData();
    }

    public interface PostService {
        @POST("/")
        Call<Person> createBlog(@Body Person person);
    }


    class Person {
        public String id;
        public String version;
        public String name;
    }


    /**
     * 自定义Converter实现RequestBody到String的转换
     */
    public static class StringConverter implements Converter<ResponseBody, String> {

        public static final StringConverter INSTANCE = new StringConverter();

        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }

    /**
     * 用于向Retrofit提供StringConverter
     */
    public static class StringConverterFactory extends Converter.Factory {

        public static final StringConverterFactory INSTANCE = new StringConverterFactory();

        public static StringConverterFactory create() {
            return INSTANCE;
        }

        // 我们只关实现从ResponseBody 到 String 的转换，所以其它方法可不覆盖
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            if (type == String.class) {
                return StringConverter.INSTANCE;
            }
            //其它类型我们不处理，返回null就行
            return null;
        }
    }



}


