package org.azutka.akllhastanesistemi;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;

import org.azutka.akllhastanesistemi.core.App;
import org.azutka.akllhastanesistemi.models.Patient;
import org.azutka.akllhastanesistemi.models.Personnel;
import org.azutka.akllhastanesistemi.models.rest.RestData;
import org.azutka.akllhastanesistemi.rest.ApiClient;
import org.azutka.akllhastanesistemi.rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninActivity extends AppCompatActivity {

    private static final String TAG = "SigninActivity";

    private RotateAnimation mRotate;
    private Runnable mRunnableRotation;
    private Handler mHandler = new Handler();

    @BindView(R.id.signin_edt_email)
    EditText edtEmail;

    @BindView(R.id.signin_edt_password)
    EditText edtPass;

    @BindView(R.id.siginin_img_logo)
    ImageView imgLogo;

    //52588406132
    //batuhan1

    @OnClick(R.id.signin_btn_login)
    public void onLogin(View view) {
        login(edtEmail.getText().toString(), edtPass.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ButterKnife.bind(this);

        mRunnableRotation = new Runnable() {
            @Override
            public void run() {
                imgLogo.startAnimation(mRotate);
                mHandler.postDelayed(mRunnableRotation, 1000);
            }
        };

        mRotate  = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotate.setDuration(1000);
        mRotate.setFillAfter(true);
        mRotate.setInterpolator(new LinearInterpolator());


    }


    private void login(String tc, String pass){

        mHandler.postDelayed(mRunnableRotation, 0);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<RestData<Personnel>> call = apiService.login("loginCheck",tc, pass);
        call.enqueue(new Callback<RestData<Personnel>>() {
            @Override
            public void onResponse(Call<RestData<Personnel>> call, Response<RestData<Personnel>> response) {

                if (response.body() != null && response.body().isSuccess()) {

                    App.currentPersonel =  response.body().getData();
                    Log.i(TAG, "NET: login: " + response.body().getData().toString());

                    getPatients(App.currentPersonel.getTc());


                } else if(response.body() != null && !response.body().isSuccess()){
                    imgLogo.clearAnimation();
                    mHandler.removeCallbacksAndMessages(null);
                    showAlertDialog("Hata!", response.body().getMessage());
                } else {
                    imgLogo.clearAnimation();
                    mHandler.removeCallbacksAndMessages(null);
                    showAlertDialog("Hata!", "Beklenmedik bir hata oluştu. Lütfen tekrar deneyin!");
                }
            }

            @Override
            public void onFailure(Call<RestData<Personnel>> call, Throwable t) {
                    Log.e(TAG, "NET: login: "  + t.toString());
                    imgLogo.clearAnimation();
                    mHandler.removeCallbacksAndMessages(null);
                    showAlertDialog("Hata!", "Beklenmedik bir hata oluştu. Lütfen tekrar deneyin!");


            }
        });
    }

    private void getPatients(String tc){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<RestData<List<Patient>>> call = apiService.getPatients("hastaListeCek",tc);
        call.enqueue(new Callback<RestData<List<Patient>>>() {
            @Override
            public void onResponse(Call<RestData<List<Patient>>> call, Response<RestData<List<Patient>>> response) {

                if (response.body() != null && response.body().isSuccess()) {

                    App.patientList =  response.body().getData();
                    Log.i(TAG, "NET: getPatients: " + response.body().getData().toString());


                    Intent intentMainActivity = new Intent(SigninActivity.this, MainActivity.class);
                    intentMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentMainActivity);

                    imgLogo.clearAnimation();
                    mHandler.removeCallbacksAndMessages(null);


                } else if(response.body() != null && !response.body().isSuccess()){

                    Log.i(TAG, "NET: getPatients: ERR: " + "Not success");
                    imgLogo.clearAnimation();
                    mHandler.removeCallbacksAndMessages(null);
                    showAlertDialog("Hata!", response.body().getMessage());
                } else {
                    Log.i(TAG, "NET: getPatients: ERR: " + "Response null");
                    imgLogo.clearAnimation();
                    mHandler.removeCallbacksAndMessages(null);
                    showAlertDialog("Hata!", "Beklenmedik bir hata oluştu. Lütfen tekrar deneyin!");
                }
            }

            @Override
            public void onFailure(Call<RestData<List<Patient>>> call, Throwable t) {
                Log.e(TAG, "NET: getPatients: "  + t.toString());
                imgLogo.clearAnimation();
                mHandler.removeCallbacksAndMessages(null);
                showAlertDialog("Hata!", "Beklenmedik bir hata oluştu. Lütfen tekrar deneyin!");

            }
        });

    }

    private void showAlertDialog(String title, String message){
        AlertDialog.Builder dialogAbout = new AlertDialog.Builder(SigninActivity.this);
        dialogAbout.setTitle(title);
        dialogAbout.setMessage(message);
        dialogAbout.setPositiveButton("Tamam", null );
        dialogAbout.show();
    }

}
