package com.example.cashrichdemo;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DateRecyclerviewAdapter extends RecyclerView.Adapter<DateRecyclerviewAdapter.MyViewHolder> {


    private List<SipData> sipDataList;

    private Context context;

    private onClickCallback onClickCallback;

    private LongSparseArray<TextView> clickSparseArray;



       DateRecyclerviewAdapter(Context context,List<SipData> sipDataList){

           this.sipDataList=sipDataList;
           this.context=context;
           clickSparseArray=new LongSparseArray<>();
           onClickCallback=(com.example.cashrichdemo.onClickCallback)context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_date,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

           final SipData sipData=sipDataList.get(i);

           myViewHolder.tvDate.setText(sipData.getDate());


           if (clickSparseArray.size()!=0&&clickSparseArray.get(i)!=null){
               clickSparseArray.get(i).getBackground().setColorFilter(context.getResources().getColor(R.color.bluelight), PorterDuff.Mode.SRC_ATOP);
               clickSparseArray.get(i).setTextColor(context.getResources().getColor(R.color.white));
           }
           else {
               myViewHolder.tvDate.getBackground().setColorFilter(context.getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
               myViewHolder.tvDate.setTextColor(context.getResources().getColor(R.color.black));
           }

           myViewHolder.tvDate.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onClickCallback.onDateClick(sipData);
                   if (clickSparseArray.get(myViewHolder.getAdapterPosition())==null){
                       clickSparseArray.clear();
                       clickSparseArray.put(myViewHolder.getAdapterPosition(),myViewHolder.tvDate);
                       myViewHolder.tvDate.getBackground().setColorFilter(context.getResources().getColor(R.color.bluelight), PorterDuff.Mode.SRC_ATOP);
                       myViewHolder.tvDate.setTextColor(context.getResources().getColor(R.color.white));
                       notifyDataSetChanged();
                   }

               }
           });
    }

    @Override
    public int getItemCount() {
        return sipDataList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

           TextView tvDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate=itemView.findViewById(R.id.tvDate);
        }
    }
}
