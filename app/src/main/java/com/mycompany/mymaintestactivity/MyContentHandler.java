package com.mycompany.mymaintestactivity;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Qingweid on 2016/5/9.
 */
public class MyContentHandler extends DefaultHandler {
    private final String TAG = getClass().getSimpleName();
    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;

    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //记录当前的节点
        nodeName = localName;
        System.out.println("startElement() " +  " uri:" + uri + " localName:" + localName + " qName:" + qName + " nodeName:" + nodeName  );
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //根据当前的节点判断将内容添加到哪一个StringBuilder对象中
        System.out.println(" characters() " + "ch:" + (new String(ch)) + " start:" + start + " length:" + length + " nodeName:" + nodeName);
        if ("id".equals(nodeName)) {
            System.out.println("********* id:" + id);
            id.append(ch, start, length);
            System.out.println("--------- id:" + id);
        } else if ("name".equals(nodeName)) {
            System.out.println("********* name:" + name);
            name.append(ch, start, length);
            System.out.println("--------- name:" + name);
        } else if ("version".equals(nodeName)) {
            System.out.println("********* version:" + version);
            version.append(ch, start, length);
            System.out.println("--------- version:" + version);
        }
        System.out.println(" characters()" + " id:" + id + " name:" + name + " version:" + version);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(" endElement() " + " uri:" + uri + " localName:" + localName + " qName:" + qName);
        if ("app".equals(localName)) {
            Log.d(TAG, " id is " + id.toString().trim() + " name is " + name.toString().trim() +
                    " version is " + version.toString().trim());
        }
        //最后要将StringBuilder清空掉
        id.setLength(0);
        name.setLength(0);
        version.setLength(0);
    }

    @Override
    public void endDocument() throws SAXException {
    }
}
