package com.zhixin.richard.s01e03.activity.member.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zhixin.richard.s01e03.AppContext;
import com.zhixin.richard.s01e03.R;
import com.zhixin.richard.s01e03.base.BaseFragment;
import com.zhixin.richard.s01e03.model.member.Member;

/**
 * Created by Administrator on 2016-03-22.
 */
public class MemberFragment extends BaseFragment {
    private Member mMember;
    private TextView mTVName, mTVId, mTVDegrade, mTVPoint, mTVLeftMoney, mTVStatus, mTVConsumeMoney;
    private ImageButton mIBCall, mIBMessage, mIBConsume, mIBExchenge, mIBCharge, mIBHistory, mIBDelete, mIBEdit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_detail, container, false);
        Intent intent = getActivity().getIntent();

        //将member传递到这里
        mMember = (Member) intent.getSerializableExtra("member");
        if (mMember == null) {
            AppContext.showToast("程序错误");
            getActivity().finish();
        } else {
            initView(view);
        }
        return view;
    }


    private void initView(View view) {
        mTVName = (TextView) view.findViewById(R.id.tv_member_detail_name);
        mTVId = (TextView) view.findViewById(R.id.tv_member_detail_id);
        mTVDegrade = (TextView) view.findViewById(R.id.tv_member_detail_degrade);
        mTVPoint = (TextView) view.findViewById(R.id.tv_member_detail_point);
        mTVStatus = (TextView) view.findViewById(R.id.tv_member_detail_status);
        mTVConsumeMoney = (TextView) view.findViewById(R.id.tv_member_detail_consumemoney);

        mIBCall = (ImageButton) view.findViewById(R.id.ib_member_detail_call);
        mIBMessage = (ImageButton) view.findViewById(R.id.ib_member_detail_message);
        mIBConsume = (ImageButton) view.findViewById(R.id.ib_member_detail_consume);
        mIBExchenge = (ImageButton) view.findViewById(R.id.ib_member_detail_exchange);
        mIBCharge = (ImageButton) view.findViewById(R.id.ib_member_detail_charge);
        mIBHistory = (ImageButton) view.findViewById(R.id.ib_member_detail_history);
        mIBDelete = (ImageButton) view.findViewById(R.id.ib_member_detail_delete);
        mIBEdit = (ImageButton) view.findViewById(R.id.ib_member_detail_edit);

        mIBCall.setOnClickListener(this);
        mIBMessage.setOnClickListener(this);
        mIBConsume.setOnClickListener(this);
        mIBExchenge.setOnClickListener(this);
        mIBCharge.setOnClickListener(this);
        mIBHistory.setOnClickListener(this);
        mIBDelete.setOnClickListener(this);
        mIBEdit.setOnClickListener(this);


        if (mMember != null) {
            mTVId.setText(mMember.getId());
            mTVName.setText(mMember.getName());
            mTVDegrade.setText(mMember.getDegrade());
            mTVPoint.setText(mMember.getCurrentPoint() + "");
            mTVStatus.setText(mMember.status);
            mTVConsumeMoney.setText(mMember.consumeMoney + "元");

        }
    }

    @Override
    public void onClick(View view) {
       switch(view.getId()){
           case R.id.ib_member_detail_call:
               break;
           case R.id.ib_member_detail_message:
               break;
           case R.id.ib_member_detail_consume:
               break;
           case R.id.ib_member_detail_exchange:
               break;
           case R.id.ib_member_detail_charge:
               break;
           case R.id.ib_member_detail_history:
               break;
           case R.id.ib_member_detail_delete:
               break;
           case R.id.ib_member_detail_edit:
               break;
           default:
               break;

       }
    }
}
