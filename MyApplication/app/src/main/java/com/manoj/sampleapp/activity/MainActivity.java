package com.manoj.sampleapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.manoj.sampleapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.manoj.sampleapp.navigator.ActivityNavigator.navigateToScenarioOne;
import static com.manoj.sampleapp.navigator.ActivityNavigator.navigateToScenarioTwo;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.scenario_one)
    AppCompatButton scenarioOneButton;

    @BindView(R.id.scenario_two)
    AppCompatButton scenarioTwoButton;

    Context mContext;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        mContext = this;

    }

    @OnClick(R.id.scenario_one)
    public void scenarioOneButtonClicked(View view) {
        navigateToScenarioOne(mContext);
    }

    @OnClick(R.id.scenario_two)
    public void scenarioTwoButtonClicked(View view) {
        navigateToScenarioTwo(mContext);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
