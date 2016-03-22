package com.zhixin.richard.s01e03.activity.member.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.base.ListBaseAdapter;
import com.zhixin.richard.s01e03.model.member.Member;

/**
 * Created by Administrator on 2016-03-22.
 */
public class MemberListAdapter extends ListBaseAdapter {
    @SuppressLint("InflateParams")
    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.list_cell_member, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        //TODO 修改测试
        Member member = (Member) _data.get(position);
        vh.name.setText(member.name);
        vh.phone.setText(member.phone);
        vh.point.setText(member.currentPoint + "");
        vh.money.setText(member.leftMoney + "");
//

//        News news = (News) _data.get(position);
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
        public ImageView photo;
        public TextView name, phone, point, money;

        public ViewHolder(View view) {
            photo = (ImageView) view.findViewById(R.id.iv_cell_member_photo);
            name = (TextView) view.findViewById(R.id.tv_cell_member_name);
            phone = (TextView) view.findViewById(R.id.tv_cell_member_phone);
            point = (TextView) view.findViewById(R.id.tv_cell_member_point);
            money = (TextView) view.findViewById(R.id.tv_cell_member_money);
        }
    }

}
