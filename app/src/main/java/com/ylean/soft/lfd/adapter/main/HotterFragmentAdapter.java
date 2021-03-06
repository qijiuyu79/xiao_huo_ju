package com.ylean.soft.lfd.adapter.main;

import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylean.soft.lfd.R;
import com.ylean.soft.lfd.activity.main.VideoPlayActivity;
import com.zxdc.utils.library.bean.HotTop;
import com.zxdc.utils.library.eventbus.EventBusType;
import com.zxdc.utils.library.eventbus.EventStatus;
import com.zxdc.utils.library.http.HttpConstant;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.view.CircleImageView;
import com.zxdc.utils.library.view.OvalImageViews;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotterFragmentAdapter extends BaseAdapter {

    private Activity activity;
    private List<HotTop.DataBean> list;

    public HotterFragmentAdapter(Activity activity,List<HotTop.DataBean> list) {
        super();
        this.activity = activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list==null ? 0 : list.size()-1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder holder = null;
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.item_more_hotter, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        HotTop.DataBean dataBean=list.get(position+1);
        switch (position){
            case 0:
                 holder.imgNum.setImageResource(R.mipmap.dier);
                holder.imgNum.setVisibility(View.VISIBLE);
                 break;
            case 1:
                holder.imgNum.setImageResource(R.mipmap.disan);
                holder.imgNum.setVisibility(View.VISIBLE);
                break;
            case 2:
                holder.imgNum.setImageResource(R.mipmap.disi);
                holder.imgNum.setVisibility(View.VISIBLE);
                break;
            default:
                holder.imgNum.setVisibility(View.INVISIBLE);
                break;
        }
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
        if(dataBean.isFollowUser()){
            holder.tvFocus.setText("已关注");
        }else{
            holder.tvFocus.setText("未关注");
        }
        holder.tvSize.setText(dataBean.getPlayCountDesc());
        holder.tvType.setText(dataBean.getChannelName());
        holder.tvDes.setText(dataBean.getIntroduction());
        switch (dataBean.getUpdateStatus()){
            case 0:
                holder.tvStatus.setText("即将开播");
                break;
            case 1:
                holder.tvStatus.setText(Html.fromHtml("更新至 <font color=\"#000000\">第" + dataBean.getEpisodeCount() + "集</font>"));
                break;
            case 2:
                holder.tvStatus.setText(Html.fromHtml("<font color=\"#000000\">全" + dataBean.getEpisodeCount() + "集</font>"));
                break;
        }


        /**
         * 关注，或者取消关注
         */
        holder.tvFocus.setTag(dataBean);
        holder.tvFocus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HotTop.DataBean dataBean= (HotTop.DataBean) v.getTag();
                if(dataBean.getUpdateStatus()==0){
                    ToastUtil.showLong("敬请期待");
                    return;
                }
                EventBus.getDefault().post(new EventBusType(EventStatus.HOT_PLAY_FOCUS,dataBean));
            }
        });

        /**
         * 进入视频详情页面
         */
        holder.imgHead.setTag(R.id.tag1,dataBean);
        holder.imgHead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HotTop.DataBean dataBean= (HotTop.DataBean) v.getTag(R.id.tag1);
                Intent intent=new Intent(activity, VideoPlayActivity.class);
                intent.putExtra("serialId",dataBean.getId());
                activity.startActivity(intent);
            }
        });
        return view;
    }


    static class ViewHolder {
        @BindView(R.id.img_num)
        ImageView imgNum;
        @BindView(R.id.img_head)
        OvalImageViews imgHead;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.img_pic)
        CircleImageView imgPic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_size)
        TextView tvSize;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_type)
        TextView tvType;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_focus)
        TextView tvFocus;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

