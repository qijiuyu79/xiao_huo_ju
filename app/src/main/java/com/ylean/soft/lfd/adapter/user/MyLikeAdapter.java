package com.ylean.soft.lfd.adapter.user;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ylean.soft.lfd.R;
import com.zxdc.utils.library.view.OvalImageViews;

public class MyLikeAdapter extends RecyclerView.Adapter<MyLikeAdapter.MyHolder> {

    private Activity activity;
    public MyLikeAdapter(Activity activity) {
        this.activity = activity;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_project, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        MyHolder holder =myHolder;

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
       OvalImageViews imgHead;
        TextView tvTitle,tvNum,tvSize;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgHead=itemView.findViewById(R.id.img_head);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvNum=itemView.findViewById(R.id.tv_num);
            tvSize=itemView.findViewById(R.id.tv_size);
        }
    }

}
