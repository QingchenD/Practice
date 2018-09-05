package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Qingweid on 2016/5/24.
 */
public class FilterFileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String fileName = "Home8";
        File ExternalStorageDirectory = Environment.getExternalStorageDirectory();
        fileName = ExternalStorageDirectory +  "/" +fileName;
        System.out.println("[Mydebug] " + " fileName:" + fileName + "");
        File parentFile = new File(fileName) ;

        File[] files = parentFile.listFiles(fileFilter);//通过fileFileter过滤器来获取parentFile路径下的想要类型的子文件
        ArrayList<FileInfo> fileList = new ArrayList<FileInfo>();//将需要的子文件信息存入到FileInfo里面
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                FileInfo fileInfo = new FileInfo();
                fileInfo.name = file.getName();
                fileInfo.path = file.getPath();
                fileInfo.lastModified = file.lastModified();
                fileList.add(fileInfo);
                System.out.println("[Mydebug] " + " name:" + fileInfo.name + " lastModified:" + fileInfo.lastModified);
            }
        }
        Collections.sort(fileList, new FileComparator());//通过重写Comparator的实现类FileComparator来实现按文件创建时间排序。附：

    }


    public class FileComparator implements Comparator<FileInfo> {
        public int compare(FileInfo file1, FileInfo file2) {
            if(file1.lastModified < file2.lastModified)
            {
                return -1;
            }else
            {
                return 1;
            }
        }
    }
    public FileFilter fileFilter = new FileFilter() {
        public boolean accept(File file) {
            String tmp = file.getName().toLowerCase();
            if (tmp.endsWith(".mp4") || tmp.endsWith(".jpg")) {
                return true;
            }
            return false;
        }
    };


    class FileInfo {
        String name;
        String path;
        long lastModified;

    }
}
