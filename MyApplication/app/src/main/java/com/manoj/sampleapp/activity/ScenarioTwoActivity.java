package com.manoj.sampleapp.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.manoj.sampleapp.R;
import com.manoj.sampleapp.model.Sample;
import com.manoj.sampleapp.restApi.RestClient;
import com.manoj.sampleapp.restApi.RestService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScenarioTwoActivity extends AppCompatActivity {

    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, ScenarioTwoActivity.class);
        return callingIntent;
    }

    @BindView(R.id.sample_items_spinner)
    Spinner sampleListSpinner;

    @BindView(R.id.car_details)
    AppCompatTextView carDetails;

    @BindView(R.id.train_details)
    AppCompatTextView trainDetails;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    RestService restService;
    ArrayAdapter<String> adapter;
    List<Sample> sampleList;
    Sample currentSample = null;
    Context mContext;
    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_two);
        mContext = this;

        unbinder = ButterKnife.bind(this);

        setSpinner();
        setSpinnerItemSelectedListner();
        fetchSampleListFromServer();
    }

    public void setSpinnerItemSelectedListner() {
        sampleListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (sampleList != null) {
                    ShowSampleItemDetails(i);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void ShowSampleItemDetails(int pos) {
        currentSample = sampleList.get(pos);
        if (currentSample != null && currentSample.fromCentral != null) {

            if (currentSample.fromCentral.car != null) {
                carDetails.setText("Car - " + currentSample.fromCentral.car);
            } else {
                carDetails.setText("");
            }
            if (currentSample.fromCentral.train != null) {
                trainDetails.setText("Train - " + currentSample.fromCentral.train);
            } else {
                trainDetails.setText("");
            }

        }
    }

    public void setSpinner() {
        String[] array = {"select"};
        ArrayList<String> lst = new ArrayList<String>(Arrays.asList(array));
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, lst);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sampleListSpinner.setAdapter(adapter);

    }

    @OnClick(R.id.navigate_button)
    public void onNavigateButtonClick() {
        if (currentSample != null && currentSample.location != null) {
            //String uri = String.format(Locale.ENGLISH, "geo:" + currentSample.location.latitude + "," + currentSample.location.longitude);

            String uriBegin = "geo:" + currentSample.location.latitude + "," + currentSample.location.longitude;
            String query = currentSample.location.latitude + "," + currentSample.location.longitude + "(" + currentSample.name + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
            Uri uri = Uri.parse(uriString);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(intent);
        }
    }

    public void fetchSampleListFromServer() {
        progressBar.setVisibility(View.VISIBLE);
        restService = RestClient.getClient().create(RestService.class);

        Call call = restService.getSampleList();
        call.enqueue(new Callback<List<Sample>>() {


            @Override
            public void onResponse(Call<List<Sample>> call, Response<List<Sample>> response) {
                Log.d("ScenarioTwo", response.code() + "");
                sampleList = response.body();
                Log.d("ScenarioTwo", new Gson().toJson(sampleList));
                adapter.clear();
                adapter.notifyDataSetChanged();

                if (sampleList != null) {
                    for (Sample s : sampleList) {
                        adapter.add(s.name);
                    }
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
                ShowSampleItemDetails(0);

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                call.cancel();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
