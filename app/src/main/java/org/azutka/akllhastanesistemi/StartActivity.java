package org.azutka.akllhastanesistemi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.azutka.akllhastanesistemi.core.App;
import org.azutka.akllhastanesistemi.models.Personnel;
import org.azutka.akllhastanesistemi.models.Settings;
import org.azutka.akllhastanesistemi.models.rest.RestData;
import org.azutka.akllhastanesistemi.rest.ApiClient;
import org.azutka.akllhastanesistemi.rest.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity {

    private static final String TAG = "StartActivity";

    private Context mContext;

    @BindView(R.id.start_img_logo)
    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2);

        ButterKnife.bind(this);

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_heartbeat);
        imgLogo.startAnimation(anim);

        this.mContext = getApplicationContext();


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        App.settings = new Settings(
                "60",
                "100",
               "21",
                "25",
                "36",
                "38",
                "90",
                "160",
               "60",
                "100",
               "70",
                "99"
        );

        final Call<RestData<Settings>> call = apiService.getSettings("getAyarlar");

        Runnable runnableRotation = new Runnable() {
            @Override
            public void run() {
                call.enqueue(new Callback<RestData<Settings>>() {
                    @Override
                    public void onResponse(Call<RestData<Settings>> call, Response<RestData<Settings>> response) {

                        if (response.body() != null && response.body().isSuccess()) {

                            Log.i(TAG, "NET: settings: " + response.body().toString());

                            App.settings = response.body().getData();

                            Intent sigininActivity = new Intent(StartActivity.this,SigninActivity.class);
                            sigininActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(sigininActivity);


                        } else if(response.body() != null && !response.body().isSuccess()){
                            // showAlertDialog(mContext.getString(R.string.error), response.body().getMessage());

                            Intent sigininActivity = new Intent(StartActivity.this,SigninActivity.class);
                            sigininActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(sigininActivity);
                        } else {
                            Intent sigininActivity = new Intent(StartActivity.this,SigninActivity.class);
                            sigininActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(sigininActivity);
                            // showAlertDialog(mContext.getString(R.string.error), mContext.getString(R.string.error_msg));
                        }
                    }

                    @Override
                    public void onFailure(Call<RestData<Settings>> call, Throwable t) {
                        Log.e(TAG, "NET: login: "  + t.toString());
                        //showAlertDialog(mContext.getString(R.string.error), mContext.getString(R.string.error_msg));
                        Intent sigininActivity = new Intent(StartActivity.this,SigninActivity.class);
                        sigininActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(sigininActivity);

                    }
                });
            }
        };

        new Handler().postDelayed(runnableRotation, 1000);


    }

    private void showAlertDialog(String title, String message){
        AlertDialog.Builder dialogAbout = new AlertDialog.Builder(StartActivity.this);
        dialogAbout.setTitle(title);
        dialogAbout.setMessage(message);
        dialogAbout.setPositiveButton(mContext.getString(R.string.okay), null );
        dialogAbout.show();
    }
}
