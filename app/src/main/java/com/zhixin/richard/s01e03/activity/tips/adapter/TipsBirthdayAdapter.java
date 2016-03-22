package com.zhixin.richard.s01e03.activity.tips.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.base.ListBaseAdapter;
import com.zhixin.richard.s01e03.model.tips.TipBirtyday;

/**
 * Created by Administrator on 2016-03-15.
 */
public class TipsBirthdayAdapter extends ListBaseAdapter {
    @SuppressLint("InflateParams")
    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.list_tips_birtyday_cell, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//TODO 修改测试
        TipBirtyday tipBirtyday =(TipBirtyday)_data.get(position);
//
//        vh.title.setText(news.getTitle());
//        vh.source.setText(news.getAuthor());
//        vh.time.setText(DateUtil.getFormatTime(news.getPubDate()));
//        //StringUtils.friendly_time(news.getPubDate())
//        if(StringUtils.isToday(news.getPubDate())){
//            vh.tip.setVisibility(View.VISIBLE);
//            vh.tip.setImageResource(R.drawable.ic_today);
//        } else {
//            vh.tip.setVisibility(View.GONE);
//        }
//        vh.comment_count.setText(parent.getResources().getString(R.string.comment_count, news.getCommentCount()))getCommentCount;
        //Drawable d = parent.getContext().getResources().getDrawable(R.drawable.ic_comment_count);
        //vh.commentCount.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
        return convertView;
    }

    static class ViewHolder {
        public TextView title, source, time, commentCount;
        public ImageView tip;

        public ViewHolder(View view) {
            //TODO 修改
            title = (TextView) view.findViewById(R.id.tv_tip_birtyday_title);
            source = (TextView) view.findViewById(R.id.tv_tip_birtyday_source);
            time = (TextView) view.findViewById(R.id.tv_tip_birtyday_time);
            commentCount = (TextView) view.findViewById(R.id.tv_tip_birtyday_count);
            tip = (ImageView) view.findViewById(R.id.iv_tip_birtyday);
        }
    }
}
