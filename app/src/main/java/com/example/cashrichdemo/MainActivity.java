package com.example.cashrichdemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements onClickCallback {


    private RelativeLayout rlMainView,rlCircularView;

    private TextView tvSharedValue,tvFixedValue;

    private RecyclerView rvDates;


    private ApiInterface apiInterface;


    private DateRecyclerviewAdapter dateRecyclerviewAdapter;


    private LinearLayoutManager linearLayoutManager;


    private List<SipData> sipDataList;

    private PieView pieView;


    //http://demo0312305.mockable.io/testCashRich

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    private void initView(){

        apiInterface=RetrofitClient.getClient().create(ApiInterface.class);
        rlMainView=findViewById(R.id.mainView);
        tvFixedValue=findViewById(R.id.tvFixedValue);
        tvSharedValue=findViewById(R.id.tvShareValue);
        rvDates=findViewById(R.id.rvDates);
        pieView=findViewById(R.id.pie_view);
        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
        rvDates.setLayoutManager(linearLayoutManager);

        final Call<List<SipData>> sipDataCall=apiInterface.getSipData();

         sipDataCall.enqueue(new Callback<List<SipData>>() {
             @Override
             public void onResponse(Call<List<SipData>> call, Response<List<SipData>> response) {

                 sipDataList=response.body();

                 dateRecyclerviewAdapter=new DateRecyclerviewAdapter(MainActivity.this,sipDataList);
                 rvDates.setAdapter(dateRecyclerviewAdapter);

                 Log.d(TAG, "onResponse: "+sipDataList.size());


             }

             @Override
             public void onFailure(Call<List<SipData>> call, Throwable t) {

             }
         });

    }

    @Override
    public void onDateClick(SipData sipData) {
        Log.d(TAG, "onDateClick: "+sipData.toString());
        ArrayList<Integer> intList = new ArrayList<Integer>();


        tvSharedValue.setText(sipData.getEquity()+"%");
        String fixedvalue=String.valueOf(100-Integer.parseInt(sipData.getEquity()));
        tvFixedValue.setText(fixedvalue+"%");

        intList.add(Integer.parseInt(sipData.getEquity()));
        intList.add(Integer.parseInt(fixedvalue));

        if (Integer.parseInt(fixedvalue)<50)
            rlMainView.setBackgroundColor(getResources().getColor(R.color.orange));
        else{
            rlMainView.setBackgroundColor(getResources().getColor(R.color.blue));
        }

        randomSet(pieView,intList);



    }

    private void randomSet(PieView pieView,List<Integer> intList){
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();

        int totalInt = 0;
        for(int i=0; i<intList.size(); i++){
            totalInt += intList.get(i);
        }
        for(int i=0; i<intList.size(); i++){
            pieHelperArrayList.add(new PieHelper(100f*intList.get(i)/totalInt));
        }

        pieView.selectedPie(PieView.NO_SELECTED_INDEX);
        pieView.showPercentLabel(false);
        pieView.setDate(pieHelperArrayList);
    }

    private void set(PieView pieView){
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        pieHelperArrayList.add(new PieHelper(20, Color.BLACK));
        pieHelperArrayList.add(new PieHelper(6));
        pieHelperArrayList.add(new PieHelper(30));
        pieHelperArrayList.add(new PieHelper(12));
        pieHelperArrayList.add(new PieHelper(32));

    }



    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
