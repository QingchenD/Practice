package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Qingweid on 2016/7/19.
 */
public class JSONTestActivity extends Activity {

    HashMap<String, String> mHueStatusMap = new HashMap<>();

    public interface IConstants {
        String ONE = "1";
        String TWO = "2";
        String THREE = "3";
        String FOUR = "4";
        String FIVE = "5";
        String SIX = "6";
        String SEVEN = "7";
    }

    String jsonStr;


    String lightsState = "{" +
            "\"1\": { \"state\": { \"on\": true, \"bri\": 254, \"hue\": 14922, \"sat\": 144, \"xy\": [ 0.4595, 0.4105 ], \"ct\": 369, \"alert\": \"none\", \"effect\": \"none\", \"colormode\": \"ct\", \"reachable\": true }, \"type\": \"Extended color light\", \"name\": \"Light 1\", \"modelid\": \"LCT001\", \"swversion\": \"66009663\", \"pointsymbol\": { \"1\": \"none\", \"2\": \"none\", \"3\": \"none\", \"4\": \"none\", \"5\": \"none\", \"6\": \"none\", \"7\": \"none\", \"8\": \"none\" } }," +
            "\"2\": { \"state\": { \"on\": false, \"bri\": 254, \"hue\": 14922, \"sat\": 144, \"xy\": [ 0.4595, 0.4105 ], \"ct\": 369, \"alert\": \"none\", \"effect\": \"none\", \"colormode\": \"ct\", \"reachable\": true }, \"type\": \"Extended color light\", \"name\": \"Light 1\", \"modelid\": \"LCT001\", \"swversion\": \"66009663\", \"pointsymbol\": { \"1\": \"none\", \"2\": \"none\", \"3\": \"none\", \"4\": \"none\", \"5\": \"none\", \"6\": \"none\", \"7\": \"none\", \"8\": \"none\" } }" +
            "}";

    String lightState = "{ \"state\": { \"on\": true, \"bri\": 254, \"hue\": 14922, \"sat\": 144, \"xy\": [ 0.4595, 0.4105 ], \"ct\": 369, \"alert\": \"none\", \"effect\": \"none\", \"colormode\": \"ct\", \"reachable\": true }, \"type\": \"Extended color light\", \"name\": \"Light 1\", \"modelid\": \"LCT001\", \"swversion\": \"66009663\", \"pointsymbol\": { \"1\": \"none\", \"2\": \"none\", \"3\": \"none\", \"4\": \"none\", \"5\": \"none\", \"6\": \"none\", \"7\": \"none\", \"8\": \"none\" } }";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        startTest();
//        deJson();
        getHuelightState();

        mHueStatusMap.put("123","123");
        mHueStatusMap.put("234","234");

        System.out.println("value:" + mHueStatusMap.get("123") + " size:" + mHueStatusMap.size());
    }

    private void startTest() {
        Gson gson = new Gson();
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            Person p = new Person();
            p.setName("name" + i);
            p.setAge(i * 5);
            persons.add(p);
        }
        jsonStr = gson.toJson(persons);

        System.out.println("str:\n" + jsonStr);

        System.out.println("one:" + IConstants.ONE + " two:" + IConstants.TWO);
    }

    private void deJson () {
        String jsonString = "{\"age\":0,\"name\":\"name0\"}";
        Gson gson = new Gson();
        Person person = gson.fromJson(jsonString, Person.class);
        System.out.println("person:" + person.toString());

        List<Person> ps = gson.fromJson(jsonStr, new TypeToken<List<Person>>(){}.getType());
        for(int i = 0; i < ps.size() ; i++)
        {
            Person p = ps.get(i);
            System.out.println("[list]:" + p.toString());
        }
    }

    private void getHuelightState() {
        Gson gson = new Gson();
//        HueMessage hueMessage = gson.fromJson(lightState,HueMessage.class);
//        System.out.println(hueMessage.state.toString());

        Map result = new HashMap();
        try {
//            JsonParser jsonParser = new JsonParser();
            JSONObject jsonObject =  new JSONObject(lightsState);

            Iterator iterator = jsonObject.keys();
            String key = null;
            String value = null;

            while (iterator.hasNext()) {

                key = (String) iterator.next();
                value = jsonObject.getString(key);
                result.put(key, value);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int index = 0 ; index < result.size() ; index ++) {
            System.out.println("***" + result.get(String.valueOf(index+1)));
        }

//        List<HueMessage> hs = gson.fromJson(lightsState, new TypeToken<List<HueMessage>>(){}.getType());
//        for(int i = 0; i < hs.size() ; i++)
//        {
//            HueMessage h = hs.get(i);
//            System.out.println("[list]:" + h.toString());
//        }
    }


    public enum IndexEnum {
        ONE,TWO,FOUR,FIVE,SIX,SEVEN
    }

    public class HueMessage {
        IndexEnum index;
        HueState state;
        class HueState {
            State state;
            String type;
            String name;
            String modelid;
            String swversion;
            HashMap<String, String> pointsymbol;

            public class State {
                Boolean on;
                Integer bri;
                Integer hue;
                Integer sat;
                Float xy[];
                Integer ct;
                String alert;
                String effect;
                String colormode;
                Boolean reachable;

                public boolean isOn() {
                    return on;
                }

                public void setOn(boolean on) {
                    this.on = on;
                }

                public Integer getBri() {
                    return bri;
                }

                public void setBri(Integer bri) {
                    this.bri = bri;
                }

                public void setHue(Integer hue) {
                    this.hue = hue;
                }

                public void setSat(Integer sat) {
                    this.sat = sat;
                }

                public Integer getHue() {
                    return hue;
                }

                public Integer getSat() {
                    return sat;
                }

                public Boolean getReachable() {
                    return reachable;
                }

                public String toString() {
                    return( "on:" + on  + " bri:"+ bri + " hue:" + hue + " sat:" + sat);
                }
            }

            public State getState() {
                return state;
            }
        }
    }

    public class Person {

        private String name;
        private int age;

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the age
         */
        public int getAge() {
            return age;
        }

        /**
         * @param age the age to set
         */
        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString()
        {
            return name + ":" +age;
        }
    }
}


