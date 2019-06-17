package org.azutka.akllhastanesistemi;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.azutka.akllhastanesistemi.core.App;
import org.azutka.akllhastanesistemi.fragments.PatientsFragment;
import org.azutka.akllhastanesistemi.models.Patient;
import org.azutka.akllhastanesistemi.models.Settings;
import org.azutka.akllhastanesistemi.models.rest.RestData;
import org.azutka.akllhastanesistemi.rest.ApiClient;
import org.azutka.akllhastanesistemi.rest.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPatientActivity extends AppCompatActivity {

    private static final String TAG = "AddPatientActivity";

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView txtToolbarTitle;

    @BindView(R.id.add_patient_birthday)
    EditText edtBirthday;

    @BindView(R.id.add_patient_crucial_dia)
    EditText edtCrucialDia;

    @BindView(R.id.add_patient_crucial_pulse)
    EditText edtCrucialPulse;

    @BindView(R.id.add_patient_crucial_room_temperature)
    EditText edtCrucialRoomTemp;

    @BindView(R.id.add_patient_crucial_sugar)
    EditText edtCrucialSugar;

    @BindView(R.id.add_patient_crucial_sys)
    EditText edtCrucialSys;

    @BindView(R.id.add_patient_crucial_temperature)
    EditText edtCrucialTemp;

    @BindView(R.id.add_patient_name)
    EditText edtName;

    @BindView(R.id.add_patient_relative_phone)
    EditText edtRelativePhone;

    @BindView(R.id.add_patient_room_no)
    EditText edtRoomNo;

    @BindView(R.id.add_patient_surname)
    EditText edtSurname;

    @BindView(R.id.add_patient_tc)
    EditText edtTC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        ButterKnife.bind(this);

        //Set toolbar
        setSupportActionBar(toolbar);
        txtToolbarTitle.setText("Yeni hasta kaydı");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Settings settings = App.settings;

        edtCrucialDia.setText(settings.crucial_pressure_dia_min + "-" + settings.crucial_pressure_dia_max );
        edtCrucialSugar.setText(settings.crucial_sugar_min + "-" + settings.crucial_sugar_max );
        edtCrucialSys.setText(settings.crucial_pressure_sys_min + "-" + settings.crucial_pressure_sys_max );
        edtCrucialTemp.setText(settings.crucial_temp_min + "-" + settings.crucial_temp_max );
        edtCrucialRoomTemp.setText(settings.crucial_room_temp_min + "-" + settings.crucial_room_temp_max );
        edtCrucialPulse.setText(settings.crucial_pulse_min + "-" + settings.crucial_pulse_max );
    }

    public void addPatient(){

        try {

            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<RestData<Patient>> call = apiService.addPatient("hastaKayit",
                    edtTC.getText().toString(),
                    App.currentPersonel.getTc(),
                    edtName.getText().toString(),
                    edtSurname.getText().toString(),
                    edtBirthday.getText().toString(),
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    edtRoomNo.getText().toString(),
                    edtRelativePhone.getText().toString(),
                    String.valueOf(System.currentTimeMillis()),
                    String.valueOf(System.currentTimeMillis()),
                    "[\"" + App.currentPersonel.getTc() + "\"]",
                    edtCrucialPulse.getText().toString().split("-")[0],
                    edtCrucialPulse.getText().toString().split("-")[1],
                    edtCrucialRoomTemp.getText().toString().split("-")[0],
                    edtCrucialRoomTemp.getText().toString().split("-")[1],
                    edtCrucialTemp.getText().toString().split("-")[0],
                    edtCrucialTemp.getText().toString().split("-")[1],
                    edtCrucialSys.getText().toString().split("-")[0],
                    edtCrucialSys.getText().toString().split("-")[1],
                    edtCrucialDia.getText().toString().split("-")[0],
                    edtCrucialDia.getText().toString().split("-")[1],
                    edtCrucialSugar.getText().toString().split("-")[0],
                    edtCrucialSugar.getText().toString().split("-")[1]
            );

            call.enqueue(new Callback<RestData<Patient>>() {
                @Override
                public void onResponse(Call<RestData<Patient>> call, Response<RestData<Patient>> response) {

                    if (response.body() != null && response.body().isSuccess()) {

                        Log.i(TAG, "NET: addPatient: " + response.body().toString());
                        Snackbar snackbar = Snackbar.make( PatientsFragment.recyclerList ,response.body().getMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                        finish();
                        App.getPatients(App.currentPersonel.getTc(), PatientsFragment.recyclerList, getApplicationContext());

                    } else if(response.body() != null && !response.body().isSuccess()){
                        showAlertDialog("Hata!", response.body().getMessage());
                    } else {
                        showAlertDialog("Hata!", "Beklenmedik bir hata oluştu. Lütfen tekrar deneyin!");
                    }

                }

                @Override
                public void onFailure(Call<RestData<Patient>> call, Throwable t) {
                    Log.e(TAG, "NET: addPatient: "  + t.toString());
                    showAlertDialog("Hata!", "Beklenmedik bir hata oluştu. Lütfen tekrar deneyin!");

                }
            });
        }

        catch(Exception ex) {
            Log.e(TAG, "addPatient: NET: Error:" + ex.toString());
            showAlertDialog("Hata!", "Eksik ya da yanlış bilgi girildi!");
        }



    }

    private void showAlertDialog(String title, String message){
        AlertDialog.Builder dialogAbout = new AlertDialog.Builder(AddPatientActivity.this);
        dialogAbout.setTitle(title);
        dialogAbout.setMessage(message);
        dialogAbout.setPositiveButton("Tamam", null );
        dialogAbout.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.okay_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.okay_menu_item_okay:

                addPatient();

                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
