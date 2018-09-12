package com.mycompany.rxjava;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import com.mycompany.mymaintestactivity.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTestActivity extends Activity {

    private final String TAG = getClass().getSimpleName();
    private Disposable mDisposable;
    private ListView listView;
    private int[] drawableRes;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava_test);
        listView = (ListView)findViewById(R.id.list_view);
        drawableRes = new int[]{R.drawable.arrow,R.drawable.bells_wavfile,R.drawable.candle,
                R.drawable.cane,R.drawable.santa,R.drawable.elf,
                R.drawable.boot,R.drawable.dot_green,R.drawable.cookie,
                R.drawable.santa};
        //rxJavaBaseUse();
        rxJavaChainUse();

    }

    //RxJava基本使用
    private void rxJavaBaseUse(){
        //被观察者
        Observable obs = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("连载1");
                emitter.onNext("连载2");
                emitter.onNext("连载3");
                emitter.onComplete();
            }
        });

        //观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
                Log.e(TAG,"onSubscribe");
            }

            @Override
            public void onNext(String value) {
                if ("2".equals(value)){
                    mDisposable.dispose();
                    return;
                }
                Log.e(TAG,"onNext:"+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,"onError="+e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e(TAG,"onComplete()");
            }
        };

        //订阅
        obs.subscribe(observer);
    }


    //RxJava链式使用
    private void rxJavaChainUse() {
        Observable.create(
                new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext(" one ");
                e.onNext(" two ");
                e.onNext("Dispose");
                e.onNext(" three ");
                e.onNext(" four ");
                //e.onError(new Throwable("This is an error!!"));
                e.onComplete();
                e.onComplete();
                e.onNext(" Five ");
                //e.onError(new Throwable("This is an other error!!"));
            }}
            )
            //.observeOn(AndroidSchedulers.mainThread())//回调在主线程
            //.subscribeOn(Schedulers.io())//执行在io线程
            .subscribe(new Observer<String>() {

                @Override
                public void onSubscribe(Disposable d) {
                    Log.i(TAG,  "onSubscribe()");
                    mDisposable = d;
                }

                @Override
                public void onNext(String value) {
                    Log.i(TAG,  "onNext() " + value);
                    if (value.equalsIgnoreCase("Dispose")) {
                        mDisposable.dispose();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG,  "onNext() " + e.getMessage());

                }

                @Override
                public void onComplete() {
                    Log.e(TAG,  "onComplete() " );
                }
            });


    }


    //定时操作
    private void timeDoSomething(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(123);
                //sleep(3000);
                emitter.onNext(456);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG,integer+"");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });

    }

    //
    private void complicatedDoSomething(final int[] drawableRes){
        Observable.create(new ObservableOnSubscribe<Drawable>() {
            @Override
            public void subscribe(ObservableEmitter<Drawable> emitter) throws Exception {
                for (int i=0;i<drawableRes.length;i++){
                    Drawable drawable=getResources().getDrawable(drawableRes[i]);
                    //第6个图片延时3秒后架子
                    if (i==5){
                        //sleep(3000);
                    }
                    //复制第7张图片到sd卡
                    if (i==6){
                        Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
                        saveBitmap(bitmap,"test.png", Bitmap.CompressFormat.PNG);
                    }
                    //上传到网络
                    if (i==7){
                        updateIcon(drawable);
                    }
                    emitter.onNext(drawable);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Drawable>() {
                    @Override
                    public void accept(Drawable drawable) throws Exception {
                        //回调后在UI界面上展示出来
                    }
                });
    }

    private void updateIcon(Drawable drawable) {
    }


    /**
     * 将Bitmap以指定格式保存到指定路径
     * @param bitmap
     * @param name
     */
    public void saveBitmap(Bitmap bitmap, String name, Bitmap.CompressFormat format) {
        // 创建一个位于SD卡上的文件
        File file = new File(Environment.getExternalStorageDirectory(),
                name);
        FileOutputStream out = null;
        try{
            // 打开指定文件输出流
            out = new FileOutputStream(file);
            // 将位图输出到指定文件
            bitmap.compress(format, 100,
                    out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
