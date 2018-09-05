package com.mycompany.mymaintestactivity;

/**
 * Created by Qingweid on 2016/3/18.
 */
public class Msg {

    public static final int TYPE_RCEIVED = 0;
    public static final int TYPE_SENT = 1;
    private String content;
    private int type;

    public Msg( String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
