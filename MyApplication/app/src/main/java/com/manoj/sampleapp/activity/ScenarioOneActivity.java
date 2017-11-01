package com.manoj.sampleapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.manoj.sampleapp.R;
import com.manoj.sampleapp.adapter.ItemAdapter;
import com.manoj.sampleapp.adapter.SectionsPagerAdapter;
import com.manoj.sampleapp.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ScenarioOneActivity extends AppCompatActivity {

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, ScenarioOneActivity.class);
        return callingIntent;
    }

    @BindView(R.id.horizontal_recycle_view)
    RecyclerView horizontalRecycleView;

    @BindView(R.id.recycler_item_name)
    AppCompatTextView recyclerViewItemName;

    @BindView(R.id.background_color_buttons_layout)
    RelativeLayout backgroundColorButtonsLayout;

    @BindView(R.id.red_button)
    AppCompatButton redButton;

    @BindView(R.id.blue_button)
    AppCompatButton blueButton;

    @BindView(R.id.green_button)
    AppCompatButton greenButton;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ItemAdapter itemAdapter;
    private List<ItemModel> movieList = new ArrayList<>();

    private ViewPager mViewPager;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_one);
        unbinder = ButterKnife.bind(this);
        setmSectionsPagerAdapterView();
        setRecyclerViewList();

    }

    private void setRecyclerViewList() {
        itemAdapter = new ItemAdapter(movieList);
        itemAdapter.setOnItemClickListener(onItemClickListener);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        horizontalRecycleView.setLayoutManager(mLayoutManager);
        horizontalRecycleView.setItemAnimator(new DefaultItemAnimator());
        horizontalRecycleView.setAdapter(itemAdapter);

        prepareMovieData();

    }

    private void setmSectionsPagerAdapterView() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    private void prepareMovieData() {

        movieList.add(new ItemModel(1, "Item 1"));
        movieList.add(new ItemModel(2, "Item 2"));
        movieList.add(new ItemModel(3, "Item 3"));
        movieList.add(new ItemModel(4, "Item 4"));
        movieList.add(new ItemModel(5, "Item 5"));

        itemAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.red_button)
    public void changeBackgroundToRed(View view) {
        backgroundColorButtonsLayout.setBackgroundColor(getResources().getColor(R.color.red));
    }

    @OnClick(R.id.blue_button)
    public void changeBackgroundToBlue(View view) {
        backgroundColorButtonsLayout.setBackgroundColor(getResources().getColor(R.color.blue));
    }

    @OnClick(R.id.green_button)
    public void changeBackgroundToGreen(View view) {
        backgroundColorButtonsLayout.setBackgroundColor(getResources().getColor(R.color.green));
    }

    private ItemAdapter.OnItemClickListener onItemClickListener =
            new ItemAdapter.OnItemClickListener() {
                @Override
                public void onUserItemClicked(ItemModel itemModel) {
                    recyclerViewItemName.setText(itemModel.getTitle());

                }
            };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
