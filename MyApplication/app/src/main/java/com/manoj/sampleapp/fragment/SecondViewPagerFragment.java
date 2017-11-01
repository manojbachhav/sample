package com.manoj.sampleapp.fragment;

/**
 * Created by manoj on 1/11/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.manoj.sampleapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SecondViewPagerFragment extends Fragment {

    @BindView(R.id.fragment_label)
    AppCompatTextView fragmentLabel;

    static int fragmentNumber;
    private Unbinder unbinder;

    public SecondViewPagerFragment() {
    }


    public static SecondViewPagerFragment getNewInstance(int number) {
        SecondViewPagerFragment fragment = new SecondViewPagerFragment();
        fragmentNumber = number;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        fragmentLabel.setText("Fragment " + fragmentNumber);
        return rootView;
    }

    @OnClick(R.id.fragment_main_layout)
    public void fragmentOnClick(View view) {
        Toast.makeText(getActivity(), "Fragment " + fragmentNumber, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}