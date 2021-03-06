package com.ylean.soft.lfd.adapter.main;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylean.soft.lfd.R;
import com.ylean.soft.lfd.activity.main.VideoPlayActivity;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.HotTop;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.http.HttpConstant;
import com.zxdc.utils.library.http.HttpMethod;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.CircleImageView;
import com.zxdc.utils.library.view.OvalImageViews;

import java.util.List;

public class MainOnlineAdapter extends RecyclerView.Adapter<MainOnlineAdapter.MyHolder> {

    private Activity activity;
    private List<HotTop.DataBean> list;
    private HotTop.DataBean playBean;
    public MainOnlineAdapter(Activity activity,List<HotTop.DataBean> list) {
        this.activity = activity;
        this.list=list;
    }

    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_main_online, viewGroup,false);
        MyHolder holder = new MyHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        HotTop.DataBean dataBean=list.get(i);
        //背景图片
        String imgUrl= HttpConstant.IP+dataBean.getImgurl();
        holder.imgHead.setTag(R.id.imageid,imgUrl);
        if(holder.imgHead.getTag(R.id.imageid)!=null && imgUrl==holder.imgHead.getTag(R.id.imageid)){
            Glide.with(activity).load(imgUrl).into(holder.imgHead);
        }
        holder.tvTitle.setText(dataBean.getName());
        //用户头像
        String headUrl=HttpConstant.IP+dataBean.getUserImg();
        holder.imgPic.setTag(R.id.imageid2,headUrl);
        if(holder.imgPic.getTag(R.id.imageid2)!=null && headUrl==holder.imgPic.getTag(R.id.imageid2)){
            Glide.with(activity).load(headUrl).into(holder.imgPic);
        }
        holder.tvName.setText(dataBean.getUserNickName());
        holder.tvTime.setText(dataBean.getStarttime().split(" ")[0]);

        if(dataBean.isAppointment()){
            holder.tvBespoke.setText("已预约");
        }else{
            holder.tvBespoke.setText("预约");
        }

        /**
         * 进入视频详情页面
         */
        holder.imgHead.setTag(R.id.tag1,dataBean);
        holder.imgHead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HotTop.DataBean dataBean= (HotTop.DataBean) v.getTag(R.id.tag1);
                if(dataBean.getEpisodeCount()==0){
                    ToastUtil.showLong("敬请期待");
                    return;
                }
                Intent intent=new Intent(activity, VideoPlayActivity.class);
                intent.putExtra("serialId",dataBean.getId());
                activity.startActivity(intent);
            }
        });


        /**
         * 预约
         */
        holder.relBespoke.setTag(dataBean);
        holder.relBespoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playBean= (HotTop.DataBean) v.getTag();
                if(playBean.isAppointment()){
                    return;
                }
                HttpMethod.bespoke(playBean.getId(),netCallBack);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
       OvalImageViews imgHead;
        CircleImageView imgPic;
        TextView tvTime,tvTitle,tvName,tvBespoke;
        RelativeLayout relBespoke;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgHead=itemView.findViewById(R.id.img_head);
            imgPic=itemView.findViewById(R.id.img_pic);
            tvTime=itemView.findViewById(R.id.tv_time);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvName=itemView.findViewById(R.id.tv_name);
            relBespoke=itemView.findViewById(R.id.rel_bespoke);
            tvBespoke=itemView.findViewById(R.id.tv_bespoke);
        }
    }


    public NetCallBack netCallBack=new NetCallBack() {
        @Override
        public void onSuccess(Object object) {
            BaseBean baseBean= (BaseBean) object;
            if(baseBean.isSussess()){
                playBean.setAppointment(true);
                notifyDataSetChanged();
                ToastUtil.showLong("预约成功");
            }else{
                ToastUtil.showLong(baseBean.getDesc());
            }
        }
    };

}

