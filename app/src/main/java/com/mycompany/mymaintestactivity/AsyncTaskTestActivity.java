package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AsyncTaskTestActivity extends Activity {

    ProgressDialog dialog ;
    MyAsyncTaskDialog dialogTask;
    AsyncTaskTextView tvTask1;
    AsyncTaskTextView tvTask2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task_layout);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Test");
        dialog.setMessage("This is a test dialog");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        Button button = (Button) findViewById(R.id.start_async_task_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTask1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,R.id.text_1);
            }
        });

        Button btn2 = (Button) findViewById(R.id.start_async_task_2);
        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                tvTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,R.id.text_2);
            }
        });


        Button btn3 = (Button) findViewById(R.id.btn_show_dialog);
        btn3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AsyncTaskTestActivity.this);
                builder.setTitle("Dialog");
                builder.setMessage("Haha");
                builder.create().show();
            }
        });


        Button btn4 = (Button) findViewById(R.id.btn_share);
        btn4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });

        //dialogTask = new MyAsyncTaskDialog();
        dialogTask.execute();

        tvTask1 = new AsyncTaskTextView(R.id.text_1);
        //tvTask1.execute();

        tvTask2 = new AsyncTaskTextView(R.id.text_2);
        //tvTask2.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("AsyncTaskTestActivity", "mydebug onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        cancelTask();

        Log.i("AsyncTaskTestActivity", "mydebug onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("AsyncTaskTestActivity", "mydebug onStop");
    }



    private void cancelTask() {
        if (dialogTask != null && !dialogTask.isCancelled()) {
            dialogTask.cancel(true);
        }

        if (!tvTask1.isCancelled()){
            tvTask1.cancel(true);
        }

        if (!tvTask2.isCancelled()) {
            tvTask2.cancel(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("AsyncTaskTestActivity", "mydebug onDestroy");
    }

    class MyAsyncTaskDialog extends AsyncTask<String, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected Integer doInBackground(String... strings) {
            Integer progress = Integer.valueOf(0);

            while (true) {
                try {
                    Thread.sleep(1*1000);
                } catch (Exception e) {

                }

                progress ++;
                Log.i("AsyncTaskTestActivity", " progress: " + progress + " thread:" + Thread.currentThread());

                if (progress > 100  || isCancelled()) {
                    Log.i("AsyncTaskTestActivity", " progress: " + progress + " iscancel:" + isCancelled());
                    break;
                }

                publishProgress(progress);
            }

            return progress;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            if(dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            dialog.setProgress(values[0]);
        }
    }

    class AsyncTaskTextView extends AsyncTask<Integer,String, String> {

        int resId ;
        TextView tv;

        public AsyncTaskTextView(int resId) {
            this.resId = resId;
            tv = (TextView) findViewById(this.resId);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            Integer progress = Integer.valueOf(0);

            while (true) {
                try {
                    Thread.sleep(1*1000);
                } catch (Exception e) {

                }

                progress ++;
                Log.i("AsyncTaskTestActivity", " progress: " + progress + " thread:" + Thread.currentThread());

                if (progress > 20  || isCancelled()) {
                    Log.i("AsyncTaskTestActivity", " progress: " + progress + " iscancel:" + isCancelled());
                    break;
                }

                publishProgress(progress.toString());
            }

            return progress.toString();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            if (tv != null) {
                tv.setText(values[0]);
            }
        }
    }


}
