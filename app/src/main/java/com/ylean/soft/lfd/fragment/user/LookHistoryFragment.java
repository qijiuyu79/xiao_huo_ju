package com.ylean.soft.lfd.fragment.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ylean.soft.lfd.R;
import com.ylean.soft.lfd.activity.user.UserActivity;
import com.ylean.soft.lfd.adapter.focus.LookHistoryAdapter;
import com.zxdc.utils.library.base.BaseFragment;
import com.zxdc.utils.library.view.MeasureListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LookHistoryFragment extends BaseFragment {


    @BindView(R.id.listView)
    MeasureListView listView;
    Unbinder unbinder;
    private LookHistoryAdapter lookHistoryAdapter;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View view;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_look, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((UserActivity)mActivity).pager.setObjectForPosition(view,2);

        lookHistoryAdapter=new LookHistoryAdapter(mActivity);
        listView.setAdapter(lookHistoryAdapter);

        return view;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}