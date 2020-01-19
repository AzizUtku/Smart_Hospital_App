package org.azutka.akllhastanesistemi.core;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.azutka.akllhastanesistemi.MainActivity;
import org.azutka.akllhastanesistemi.SigninActivity;
import org.azutka.akllhastanesistemi.adapters.PatientAdapter;
import org.azutka.akllhastanesistemi.fragments.PatientsFragment;
import org.azutka.akllhastanesistemi.models.Patient;
import org.azutka.akllhastanesistemi.models.Personnel;
import org.azutka.akllhastanesistemi.models.Settings;
import org.azutka.akllhastanesistemi.models.rest.RestData;
import org.azutka.akllhastanesistemi.rest.ApiClient;
import org.azutka.akllhastanesistemi.rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class App extends Application {

    private static final String TAG = "App";

    public static Personnel currentPersonel = new Personnel();
    public static List<Patient> patientList = new ArrayList<>();
    public static Settings settings;

    public static boolean startedToGetDataPerSecond = false;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    public static void getPatients(String tc, final RecyclerView recyclerView, final Context context){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<RestData<List<Patient>>> call = apiService.getPatients("hastaListeCek",tc);
        call.enqueue(new Callback<RestData<List<Patient>>>() {
            @Override
            public void onResponse(Call<RestData<List<Patient>>> call, Response<RestData<List<Patient>>> response) {

                if (response.body() != null && response.body().isSuccess()) {

                    App.patientList =  response.body().getData();
                    recyclerView.getAdapter().notifyDataSetChanged();
                    synchronized (recyclerView) {
                        recyclerView.notifyAll();
                    }


                } else if(response.body() != null && !response.body().isSuccess()){
                    Log.i(TAG, "NET: getPatients: ERR: " + "Not success");
                } else {
                    Log.i(TAG, "NET: getPatients: ERR: " + "Response null");
                }
            }

            @Override
            public void onFailure(Call<RestData<List<Patient>>> call, Throwable t) {
                Log.e(TAG, "NET: getPatients: "  + t.toString());

            }
        });

    }


}
