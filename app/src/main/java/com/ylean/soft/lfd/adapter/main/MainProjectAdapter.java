package com.ylean.soft.lfd.adapter.main;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylean.soft.lfd.R;
import com.ylean.soft.lfd.activity.main.VideoPlayActivity;
import com.zxdc.utils.library.bean.Project;
import com.zxdc.utils.library.http.HttpConstant;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.CircleImageView;
import com.zxdc.utils.library.view.OvalImageViews;

import java.util.List;

public class MainProjectAdapter extends RecyclerView.Adapter<MainProjectAdapter.MyHolder> {

    private Activity activity;
    private List<Project.ListData> list;
    public MainProjectAdapter(Activity activity,List<Project.ListData> list) {
        this.activity = activity;
        this.list=list;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_main_project, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        Project.ListData listData=list.get(i);
        //图片
        String imgUrl= HttpConstant.IP+listData.getImgurl();
        holder.imgHead.setTag(R.id.imageid,imgUrl);
        if(holder.imgHead.getTag(R.id.imageid)!=null && imgUrl==holder.imgHead.getTag(R.id.imageid)){
            Glide.with(activity).load(imgUrl).into(holder.imgHead);
        }
        //用户头像
        String headUrl=HttpConstant.IP+listData.getUserImg();
        holder.imgAuthor.setTag(R.id.imageid2,headUrl);
        if(holder.imgAuthor.getTag(R.id.imageid2)!=null && headUrl==holder.imgAuthor.getTag(R.id.imageid2)){
            Glide.with(activity).load(headUrl).into(holder.imgAuthor);
        }
        holder.tvSize.setText(listData.getPlayCountDesc());
        holder.tvTitle.setText(listData.getName());
        switch (listData.getUpdateStatus()){
            case 0:
                holder.tvStatus.setText("即将开播");
                break;
            case 1:
                holder.tvStatus.setText(Html.fromHtml("更新至 <font color=\"#000000\">第" + listData.getEpisodeCount() + "集</font>"));
                break;
            case 2:
                holder.tvStatus.setText(Html.fromHtml("<font color=\"#000000\">全" + listData.getEpisodeCount() + "集</font>"));
                break;
        }


        /**
         * 进入视频详情页面
         */
        holder.imgHead.setTag(R.id.tag1,listData);
        holder.imgHead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Project.ListData listData= (Project.ListData) v.getTag(R.id.tag1);
                if(listData.getUpdateStatus()==0){
                    ToastUtil.showLong("敬请期待");
                    return;
                }
                Intent intent=new Intent(activity, VideoPlayActivity.class);
                intent.putExtra("serialId",listData.getId());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        OvalImageViews imgHead;
        CircleImageView imgAuthor;
        TextView tvTitle,tvSize,tvBlues,tvStatus;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgHead=itemView.findViewById(R.id.img_head);
            imgAuthor=itemView.findViewById(R.id.img_author);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvSize=itemView.findViewById(R.id.tv_size);
            tvBlues=itemView.findViewById(R.id.tv_blues);
            tvStatus=itemView.findViewById(R.id.tv_status);
        }
    }

}

