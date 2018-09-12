package com.mycompany.recycleview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mycompany.mymaintestactivity.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RecycleViewTest extends Activity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;

    int mDefaultHeight = 100;

    @Bind(R.id.et_go)
    EditText mEt;
    @Bind(R.id.btn_go)
    Button btnGo;

    @Bind(R.id.btn_add)
    Button btnAdd;

    @Bind(R.id.btn_delete)
    Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recyclerview);
        ButterKnife.bind(this);

        btnGo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(mEt.getText().toString()) - 1;
                mRecyclerView.scrollToPosition(position);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDatas.add(2, "nihao");
                notifyItemInserted(2);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mDatas.remove(2);
                notifyItemRemoved(2);
            }
        });

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        //GridLayoutManager manager = new GridLayoutManager(this,4, GridLayoutManager.VERTICAL, false);
        //MyGridLayoutManager manager = new MyGridLayoutManager(this, 4);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return 1;
//                if (position % 3 == 0) {
//                    return 4;
//                } else if(position % 5 == 0){
//                    return 2;
//                }else{
//                    return 1;
//                }
//            }
//        });

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        manager.canScrollHorizontally();

        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
        mAdapter.setOnItemClickLitener(new OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(RecycleViewTest.this, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(RecycleViewTest.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)); //new DividerGridItemDecoration(this));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置item动画


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //mRecyclerView.getHeight();
    }

    private void notifyItemInserted(int position) {
        mAdapter.notifyItemInserted(position);
    }

    private void notifyItemRemoved(int position) {
        mAdapter.notifyItemRemoved(position);
    }

    protected void initData()
    {
        mDatas = new ArrayList<String>();
//        for (int i = 'A'; i < 'z'; i++)
//        {
//            mDatas.add("" + (char) i);
//        }

        for (int i = 1; i < 200; i++)
        {
            mDatas.add(Integer.valueOf(i).toString());
        }
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
    {
        private OnItemClickLitener mOnItemClickLitener;

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View v = LayoutInflater.from(
                    RecycleViewTest.this).inflate(R.layout.item_home, parent,
                    false);
            Random random = new Random();
            int height = mDefaultHeight + random.nextInt(300);
            v.getLayoutParams().height = height;
            MyViewHolder holder = new MyViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.tv.setText(mDatas.get(position));
            final MyViewHolder myViewHolder = holder;
            if (mOnItemClickLitener != null) {
                holder.tv.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int pos = myViewHolder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(myViewHolder.itemView, pos);
                    }
                });

                holder.tv.setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {
                        int pos = myViewHolder.getLayoutPosition();
                        mOnItemClickLitener.onItemLongClick(myViewHolder.itemView, pos);
                        return false;
                    }
                });
            }

//            Random random = new Random();
//            ViewGroup.LayoutParams layoutParams = holder.tv.getLayoutParams();
//            layoutParams.height = random.nextInt(200) + 50;
//            holder.tv.setLayoutParams(layoutParams);
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        private void setOnItemClickLitener( OnItemClickLitener onItemClickLitener) {
            this.mOnItemClickLitener = onItemClickLitener;
        }

        private void removeData(int position) {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }


        class MyViewHolder extends RecyclerView.ViewHolder
        {

            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }

    class MyGridLayoutManager extends android.support.v7.widget.GridLayoutManager {
        public MyGridLayoutManager(Context context, int spanCount) {
            super(context, spanCount);
        }

        @Override
        public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
            if (getChildCount() > 0) {
                View firstChildView = recycler.getViewForPosition(0);
                measureChild(firstChildView, widthSpec, heightSpec);
                setMeasuredDimension(View.MeasureSpec.getSize(widthSpec), firstChildView.getMeasuredHeight()*getChildCount());
            } else {
                super.onMeasure(recycler, state, widthSpec, heightSpec);
            }
        }
    }

}
