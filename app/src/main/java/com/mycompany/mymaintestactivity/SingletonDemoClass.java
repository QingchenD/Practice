package com.mycompany.mymaintestactivity;

/**
 * Created by Qingweid on 2015/12/10.
 */
public class SingletonDemoClass {

    private String test;
    SingletonDemoClass(){
        this.test = "default";
    }

    private static SingletonDemoClass instance;

    public static SingletonDemoClass getInstance() {
        if (instance == null) {
            instance = new SingletonDemoClass();
        }
        return  instance;
    }

    public void  setStr(String str) {
        this.test = str;
    }

    public String getStr() {
        return  this.test;
    }
}
