package com.mycompany.aidlTest;


import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
    private String mName;
    private int mAge;

    public Person(String name, int age) {
        mName = name;
        mAge = age;
    }

    protected Person(Parcel in) {
        mName = in.readString();
        mAge = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mAge);
    }

    @Override
    public String toString() {
        return "Person{" +
                "mName='" + mName + '\'' +
                "mAge=" + mAge +
                '}';
    }

}