package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by Qingweid on 2015/12/18.
 */
public class XYSeriesRendererActivity extends Activity{

    private LinearLayout containerbody;
    private GraphicalView mLineChartView;
    private PopupWindow mPopupWindow;
    private PopupWindow mPopupWindow1;
    private PopupWindow popWin;
    private View mPopupView;
    private View mPopupView1;
    private TextView mPopTxt1;
    private TextView mPopTxt2;
    private TextView mPopTxt3;
    private TextView mPopTxt4;

    private XYSeries series;
    private final int CHART_MARGINS_LEFT = 20;
    private final int CHART_MARGINS_TOP = 30;
    private final int CHART_MARGINS_RIGHT = 20;
    //    private final int CHART_MARGINS_BOTTOM = 0;
    private int chart_margins_bottom;
    private final int CHART_X_LABELS = 9;
    private final int CHART_Y_LABELS = 6;
    private final int CHART_X_AXISMAX = CHART_X_LABELS + 1;
    private final int CHART_Y_AXISMAX = CHART_Y_LABELS * 10;
    private int panXLimit;
    private int lineEndX;
    private int mEventStartX;
    //    private int mEventStartY;
    private int mEventEndX;
    private int mEventEndY;
    private int mScreenOffsetX = 0;
    //    private int mScreenOffsetY = 0;
    private int POPWIN_WIDTH ;
    private int POPWIN_HEIGHT;
    private int POPWIN_WIDTH1 ;
    private int POPWIN_HEIGHT1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xyseries_test_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lineView();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

//    private void initChartView()
//    {
//        System.out.println("initChartView here !");
//        // 1, 构造显示用渲染图
//        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
////  renderer.setPointSize(5);
//        // 2,进行显示
//        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
//        // 2.1, 构建数据
//        series = new XYSeries("业绩曲线");
//        // 填充数据
//        System.out.println("initChartView here !lineEndX = " + lineEndX);
//        for (int x = 0; x < lineEndX; x++)
//        {
//            // 填x,y值
//            mDataMap = mDataMapList.get(x);
//            // X轴从1开始，所以x+1
//            series.add(x + 1, Integer.parseInt(mDataMap.get("BUSTOTAL")));
////   series.add(x + 1, (int) Math.abs(Math.random() * CHART_Y_AXISMAX));
//            System.out.println("x = " + (x + 1) + " | y = " + Integer.parseInt(mDataMap.get("BUSTOTAL")));
//        }
//        // 需要绘制的点放进dataset中
//        dataset.addSeries(series);
//
//        // 3, 对点的绘制进行设置
//        XYSeriesRenderer xyRenderer = new XYSeriesRenderer();
//        // 3.1设置K 线颜色
//        xyRenderer.setColor(Color.RED);
//        // 3.2设置点的样式
//        xyRenderer.setPointStyle(PointStyle.CIRCLE);
//        xyRenderer.setFillPoints(true);
//        //设置线的宽度
//        xyRenderer.setLineWidth(3);
//        // 3.3, 将要绘制的点添加到坐标绘制中
//        renderer.addSeriesRenderer(xyRenderer);
//
//        // 4, 设置图表属性
//        // 显示网格
//        renderer.setShowGrid(true);
//        // 设置4周边距
//        chart_margins_bottom = (int) getResources().getDimension(R.dimen.chart_margin_bottom);
//        renderer.setMargins(new int[] { CHART_MARGINS_TOP, CHART_MARGINS_LEFT, chart_margins_bottom, CHART_MARGINS_RIGHT });
//        // 边框外侧颜色
////  renderer.setMarginsColor(Color.argb(0, 0xff, 0, 0)); // 穿透背景色
//        renderer.setMarginsColor(Color.WHITE);
//        // 设置背景颜色
//        renderer.setBackgroundColor(Color.TRANSPARENT);
//        // 设置背景颜色生效
//        renderer.setApplyBackgroundColor(true);
//        // 是否支持图表移动
//        renderer.setPanEnabled(true, false);
//        // 坐标滑动上、下限
//        renderer.setPanLimits(new double[]{1, panXLimit, 0, 100});
//        // 是否支持图表缩放
//        renderer.setZoomEnabled(false, false);
//        // X轴等分，最小、最大坐标值
//        renderer.setXLabels(CHART_X_LABELS);
//        renderer.setXAxisMin(1);
//        renderer.setXAxisMax(CHART_X_AXISMAX);
//        // Y轴等分，最小、最大坐标值
//        renderer.setYLabels(CHART_Y_LABELS);
//        renderer.setYAxisMin(0);
//        renderer.setYAxisMax(CHART_Y_AXISMAX);
//
//        // 坐标轴颜色
//        renderer.setAxesColor(Color.rgb(242, 103, 16));
//        // 坐标轴单位文字颜色、字号
//        renderer.setLabelsColor(Color.rgb(25, 110, 172));
//        renderer.setLabelsTextSize(16);
//        // 坐标轴文字对齐
//        renderer.setYLabelsAlign(Paint.Align.RIGHT);
//        //设置原点大小
//        renderer.setPointSize(5);
//        // 设置图表标题文字
////  renderer.setChartTitle(getString(R.string.chart_title));
//        // 是否显示图例
//        renderer.setShowLegend(false);
//
//        // 生成图表视图
//        mLineChartView = ChartFactory.getLineChartView(ShowChartActivity.this, dataset, renderer);
//        mLineChartView.setOnTouchListener(chartViewOnTouchListener);
//        mLineChartView.setId(0);
//
//        // 添加至父容器
//        containerbody = (LinearLayout) findViewById(R.id.container_chart);
//        containerbody.addView(mLineChartView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
////  firstShowChart();
//    }

//    private View.OnTouchListener chartViewOnTouchListener = new View.OnTouchListener()
//    {
//        @Override
//        public boolean onTouch(View v, MotionEvent event)
//        {
//            // TODO Auto-generated method stub
//            dismissPopupWindow();
//
//            switch (event.getAction())
//            {
//                case MotionEvent.ACTION_DOWN:
//                    mEventStartX = (int) event.getX();
////    mEventStartY = (int) event.getY();
//                    break;
//                case MotionEvent.ACTION_UP:
//                    // *图表点击坐标
//                    mEventEndX = (int) event.getX();
//                    mEventEndY = (int) event.getY();
//                    System.out.println("1.------------------------------");
//                    // 屏幕是否位移
//                    mScreenOffsetX += mEventEndX - mEventStartX == 0 ? 0 : mEventEndX - mEventStartX;
//                    // 是否超出X轴原点（是，归0）
//                    mScreenOffsetX = mScreenOffsetX > 0 ? 0 : mScreenOffsetX;
//                    System.out.println("############ mOffsetX = " + mScreenOffsetX);
//
//                    if (event.getX() < CHART_MARGINS_LEFT || event.getX() > containerbody.getRight() - CHART_MARGINS_LEFT || event.getY() < CHART_MARGINS_TOP
//                            || event.getY() > containerbody.getBottom() - CHART_MARGINS_TOP)
//                    {
//                        // out of the chartView, do nothing.
//                    }
//                    else
//                    {
//                        // 取屏幕宽、高
//                        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
//                        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
//                        // 取图表区域宽、高
//                        int chartViewWidth = mLineChartView.getWidth();
//                        int chartViewHeight = mLineChartView.getHeight();
//                        // 求图表单元格宽、高
//                        int chartCellWidth = (chartViewWidth - CHART_MARGINS_LEFT * 2) / CHART_X_LABELS;
//                        int chartCellHeight = (chartViewHeight - CHART_MARGINS_TOP - chart_margins_bottom) / CHART_Y_LABELS;
//                        // 是否超出X轴上限（是，取上限值）
//                        mScreenOffsetX = mScreenOffsetX < -chartCellWidth * (panXLimit - CHART_X_AXISMAX) ? -chartCellWidth * (panXLimit - CHART_X_AXISMAX)
//                                : mScreenOffsetX;
//                        System.out.println("-chartCellWidth * panXLimit = " + -chartCellWidth * (panXLimit - CHART_X_AXISMAX));
//                        System.out.println("@@@@mScreenOffsetX = " + mScreenOffsetX);
//                        // *求图表像素坐标 换算成图表单位坐标
//                        int chartEventX = 1 + Math.round((mEventEndX - CHART_MARGINS_LEFT) / chartCellWidth);
//                        int chartEventY = CHART_Y_AXISMAX - Math.round((mEventEndY - CHART_MARGINS_TOP) / chartCellHeight * 10);
//                        System.out.println("chartEventX = " + chartEventX);
//                        System.out.println("chartEventY = " + chartEventY);
//                        // 求位移单元格
//                        int chartOffsetX = Math.round(mScreenOffsetX / chartCellWidth);
//                        System.out.println("############ chartOffsetX = " + chartOffsetX);
//                        System.out.println("2.================================");
//                        // 求图表像素坐标 换算成屏幕像素坐标
//                        int screenEventX = mEventEndX + (screenWidth - chartViewWidth) / 2;
//                        int screenEventY = mEventEndY + (screenHeight - chartViewHeight) / 2;
////     System.out.println("chartEventX = " + event.getX());
////     System.out.println("chartEventY = " + event.getY());
////     System.out.println("screenEventX = " + screenEventX);
////     System.out.println("screenEventY = " + screenEventY);
//
//                        boolean mType = true;
//                        isShowPopWin: for (int i = 0; i < lineEndX; i++)
//                        {
//                            int pxSeriesX = ((int) (series.getX(i)) - 1) * chartCellWidth + CHART_MARGINS_LEFT;
//                            int pxSeriesY = (CHART_Y_AXISMAX - (int) (series.getY(i))) * chartCellHeight / 10 + CHART_MARGINS_TOP;
//                            System.out.println("3.**********************************");
////      System.out.println(" mEventEndX = " + mEventEndX);
////      System.out.println(" pxSeriesX + mScreenOffsetX - chartCellWidth / 2 = " + (pxSeriesX + mScreenOffsetX - chartCellWidth / 2));
////      System.out.println(" pxSeriesX + mScreenOffsetX + chartCellWidth / 2 = " + (pxSeriesX + mScreenOffsetX + chartCellWidth / 2));
////      System.out.println(" mEventEndY = " + mEventEndY);
////      System.out.println(" pxSeriesY - chartCellHeight / 2 = " + (pxSeriesY - chartCellHeight / 2));
////      System.out.println(" pxSeriesY + chartCellHeight / 2 = " + (pxSeriesY + chartCellHeight / 2));
//
//                            if (mEventEndX > pxSeriesX + mScreenOffsetX - chartCellWidth / 2 && mEventEndX < pxSeriesX + mScreenOffsetX + chartCellWidth / 2
//                                    && mEventEndY > pxSeriesY - chartCellHeight / 2 && mEventEndY < pxSeriesY + chartCellHeight / 2)
//                            {
//                                System.out.println(" series.getX(i) = " + series.getX(i));
//                                System.out.println(" pxSeriesX = " + pxSeriesX);
//
//
//                                mDataMap = mDataMapList.get(i);
//                                String bustotal = mDataMap.get("BUSTOTAL").toString();
//                                String busunsuccess = mDataMap.get("BUSUNSUCCESS").toString();
//                                touchPoint = i;
//                                // 根据type，判断窗口绿or蓝
//                                if("0".equals(mDataMap.get("type").toString()))
//                                {
//                                    mType = false;
//                                    mPopTxt1.setText("预约" + bustotal + "条");
//                                    mPopTxt2.setText(busunsuccess + "条");
//                                }
//                                else
//                                {
//                                    mType = true;
//                                    mPopTxt4.setText("已办理" + bustotal + "条");
//                                }
//                                if (screenWidth - screenEventX < POPWIN_WIDTH)
//                                {
//                                    mPopupView.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_pop_right));
//                                    mPopupView.setPadding(0, dip2px(ShowChartActivity.this, 30), dip2px(ShowChartActivity.this, 23), 0);
//                                    mPopTxt1.setGravity(Gravity.RIGHT);
//                                    mPopTxt2.setGravity(Gravity.RIGHT);
//                                    mPopTxt3.setGravity(Gravity.RIGHT);
//
//                                    mPopupView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_pop_right));
//                                    mPopupView1.setPadding(0, dip2px(ShowChartActivity.this, 30), dip2px(ShowChartActivity.this, 23), 0);
//                                    mPopTxt4.setGravity(Gravity.RIGHT);
//
//                                    screenEventX -= POPWIN_WIDTH;
//                                }
//                                else
//                                {
//                                    mPopupView.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_pop_left));
//                                    mPopupView.setPadding(dip2px(ShowChartActivity.this, 23), dip2px(ShowChartActivity.this, 30), 0, 0);
//                                    mPopTxt1.setGravity(Gravity.LEFT);
//                                    mPopTxt2.setGravity(Gravity.LEFT);
//                                    mPopTxt3.setGravity(Gravity.LEFT);
//
//                                    mPopupView1.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_pop_left));
//                                    mPopupView1.setPadding(dip2px(ShowChartActivity.this, 20), dip2px(ShowChartActivity.this, 30), 0, 0);
//                                    mPopTxt4.setGravity(Gravity.LEFT);
//                                }
//
//                                mPopupWindow = new PopupWindow(mPopupView, POPWIN_WIDTH, POPWIN_HEIGHT);
//                                mPopupWindow1 = new PopupWindow(mPopupView1, POPWIN_WIDTH1, POPWIN_HEIGHT1);
//                                if(mType)
//                                {
//                                    if (mPopupWindow1.isShowing())
//                                    {
//                                        mPopupWindow1.update(screenEventX, screenEventY + POPWIN_HEIGHT / 3, POPWIN_WIDTH, POPWIN_HEIGHT);
//                                    }
//                                    else
//                                    {
//                                        mPopupWindow1.showAtLocation(mLineChartView, Gravity.NO_GRAVITY, screenEventX, screenEventY + POPWIN_HEIGHT / 3);
//                                    }
//                                }
//                                else
//                                {
//                                    if (mPopupWindow.isShowing())
//                                    {
//                                        mPopupWindow.update(screenEventX, screenEventY + POPWIN_HEIGHT / 3, POPWIN_WIDTH, POPWIN_HEIGHT);
//                                    }
//                                    else
//                                    {
//                                        mPopupWindow.showAtLocation(mLineChartView, Gravity.NO_GRAVITY, screenEventX, screenEventY + POPWIN_HEIGHT / 3);
//                                    }
//                                }
//
//                                break isShowPopWin;
//                            }
//                        }
//                    }
//                    break;
//                default:
//                    break;
//            }
//
//            return false;
//        }
//    };


    public void lineView() {
        //同样是需要数据dataset和视图渲染器renderer
        XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
        XYSeries series = new XYSeries("第一条线");
        series.add(1, 6);
        series.add(2, 5);
        series.add(3, 7);
        series.add(4, 4);
        mDataset.addSeries(series);
        XYSeries seriesTwo = new XYSeries("第二条线");
        seriesTwo.add(1, 4);
        seriesTwo.add(2, 6);
        seriesTwo.add(3, 3);
        seriesTwo.add(4, 7);
        mDataset.addSeries(seriesTwo);


        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        //设置图表的X轴的当前方向
        mRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        mRenderer.setXTitle("日期");//设置为X轴的标题
        mRenderer.setYTitle("价格");//设置y轴的标题
        mRenderer.setAxisTitleTextSize(20);//设置轴标题文本大小
        mRenderer.setChartTitle("价格走势图");//设置图表标题
        mRenderer.setChartTitleTextSize(30);//设置图表标题文字的大小
        mRenderer.setLabelsTextSize(50);//设置标签的文字大小
        mRenderer.setLegendTextSize(20);//设置图例文本大小
        mRenderer.setPointSize(10f);//设置点的大小
        mRenderer.setYAxisMin(0);//设置y轴最小值是0
        mRenderer.setYAxisMax(15);
        mRenderer.setYLabels(10);//设置Y轴刻度个数（貌似不太准确）
        mRenderer.setXAxisMax(5);
        mRenderer.setXAxisMin(1);
        mRenderer.setShowGrid(true);//显示网格
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        //将x标签栏目显示如：1,2,3,4替换为显示1月，2月，3月，4月
//        mRenderer.addXTextLabel(1, "1月");
        mRenderer.addXTextLabel(2, "2月");
        mRenderer.addXTextLabel(3, "3月");
        mRenderer.addXTextLabel(4, "4月");
        mRenderer.addXTextLabel(5, "5月");
        mRenderer.setXLabels(0);//设置只显示如1月，2月等替换后的东西，不显示1,2,3等
        mRenderer.setMargins(new int[]{20, 30, 15, 20});//设置视图位置

        XYSeriesRenderer r = new XYSeriesRenderer();//(类似于一条线对象)
        r.setColor(Color.BLUE);//设置颜色
        r.setPointStyle(PointStyle.CIRCLE);//设置点的样式
        r.setFillPoints(true);//填充点（显示的点是空心还是实心）
        r.setDisplayChartValues(true);//将点的值显示出来
        r.setChartValuesSpacing(10);//显示的点的值与图的距离
        r.setChartValuesTextSize(25);//点的值的文字大小

        //  r.setFillBelowLine(true);//是否填充折线图的下方
        //  r.setFillBelowLineColor(Color.GREEN);//填充的颜色，如果不设置就默认与线的颜色一致
        r.setLineWidth(3);//设置线宽
        mRenderer.addSeriesRenderer(r);


        XYSeriesRenderer rTwo = new XYSeriesRenderer();//(类似于一条线对象)
        rTwo.setColor(Color.GRAY);//设置颜色
        rTwo.setPointStyle(PointStyle.CIRCLE);//设置点的样式
        rTwo.setFillPoints(true);//填充点（显示的点是空心还是实心）
        rTwo.setDisplayChartValues(true);//将点的值显示出来
        rTwo.setChartValuesSpacing(10);//显示的点的值与图的距离
        rTwo.setChartValuesTextSize(25);//点的值的文字大小

        //  rTwo.setFillBelowLine(true);//是否填充折线图的下方
        //  rTwo.setFillBelowLineColor(Color.GREEN);//填充的颜色，如果不设置就默认与线的颜色一致
        rTwo.setLineWidth(3);//设置线宽

        mRenderer.addSeriesRenderer(rTwo);


        GraphicalView view = ChartFactory.getLineChartView(this, mDataset, mRenderer);
        view.setBackgroundColor(Color.BLACK);
        setContentView(view);
    }

    private void dismissPopupWindow() {
        mPopupWindow.dismiss();
        mPopupWindow1.dismiss();
    }
}
