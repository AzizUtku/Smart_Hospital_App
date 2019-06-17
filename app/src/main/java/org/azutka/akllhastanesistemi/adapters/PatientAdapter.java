package org.azutka.akllhastanesistemi.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.transition.TransitionManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.azutka.akllhastanesistemi.MainActivity;
import org.azutka.akllhastanesistemi.R;
import org.azutka.akllhastanesistemi.SigninActivity;
import org.azutka.akllhastanesistemi.core.App;
import org.azutka.akllhastanesistemi.models.Patient;
import org.azutka.akllhastanesistemi.models.Personnel;
import org.azutka.akllhastanesistemi.models.rest.RestData;
import org.azutka.akllhastanesistemi.rest.ApiClient;
import org.azutka.akllhastanesistemi.rest.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private static final String TAG = "PatientAdapter";
    private Context mContext;
    private List<Patient> mPatients;
    private RecyclerView mRecycler;

    public PatientAdapter(Context context, List<Patient> patients, RecyclerView recyclerView) {
        this.mContext = context;
        this.mPatients = patients;
        this.mRecycler = recyclerView;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View currencyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_patient_trace, parent, false);
        PatientHolder holder = new PatientHolder(currencyView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Patient patient = App.patientList.get(position);

        final PatientHolder patientHolder = (PatientHolder) holder;


        patientHolder.cardMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(patientHolder.cardMain,patient.getName() + " " + patient.getSurname(), patient.getTc(), patient.getRelative_phone());
            }
        });

        patientHolder.txtName.setText( patient.getName() + " " + patient.getSurname());
        patientHolder.txtRoomNo.setText( "#" + patient.getRoom_no());

        if(!patient.getPulse().isEmpty()){
            patientHolder.txtPulse.setText( patient.getPulse() + " bpm");
        } else{
            patientHolder.txtPulse.setText( "Bilinmiyor");
        }

        if(!patient.getPatient_temperature().isEmpty() ){
            patientHolder.txtTemp.setText( patient.getPatient_temperature() + " °C");
        } else {
            patientHolder.txtTemp.setText( "Bilinmiyor");
        }


        if(!patient.getRoom_temperature().isEmpty() ){
            patientHolder.txtTempRoom.setText(patient.getRoom_temperature() + " °C");
        } else {
            patientHolder.txtTemp.setText( "Bilinmiyor");
        }



        if(!patient.getBlood_pressure().isEmpty()){
            patientHolder.txtPressureDIA.setText( patient.getBlood_pressure().split("/")[0] + " mmHg");
            patientHolder.txtPressureSYS.setText( patient.getBlood_pressure().split("/")[1] + " mmHg");
        } else {
            patientHolder.txtPressureDIA.setText("Bilinmiyor");
            patientHolder.txtPressureSYS.setText("Bilinmiyor");
        }

        if(!patient.getSugar().isEmpty()){
            patientHolder.txtSugar.setText( patient.getSugar() + " mg/dL");
        } else {
            patientHolder.txtSugar.setText( "Bilinmiyor");
        }

        if(!patient.getBrain_activity().isEmpty()){
            patientHolder.txtBrainActivity.setText( patient.getBrain_activity());
        } else {
            patientHolder.txtBrainActivity.setText( "Bilinmiyor");
        }


        //ColorStateList state = patientHolder.cardMain.getCardBackgroundColor();
        patientHolder.txtLeftPulse.setTextColor(Color.parseColor("#B3000000"));
        patientHolder.txtLeftPressure.setTextColor(Color.parseColor("#B3000000"));
        patientHolder.txtLeftTemp.setTextColor(Color.parseColor("#B3000000"));
        patientHolder.txtLeftTempRoom.setTextColor(Color.parseColor("#B3000000"));
        patientHolder.txtLeftSugar.setTextColor(Color.parseColor("#B3000000"));


        if(patient.isPulseCrucial() || patient.isDiaPressureCrucial() || patient.isRoomTempCrucial() || patient.isSugarCrucial() || patient.isSysPressureCrucial() || patient.isTempCrucial()){
            patientHolder.cardMain.setCardBackgroundColor(Color.parseColor("#b71c1c"));

            if(patient.isPulseCrucial()){
                patientHolder.txtLeftPulse.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                patientHolder.txtLeftPulse.setTextColor(Color.parseColor("#B3000000"));
            }

            if(patient.isDiaPressureCrucial() || patient.isSysPressureCrucial()){
                patientHolder.txtLeftPressure.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                patientHolder.txtLeftPressure.setTextColor(Color.parseColor("#B3000000"));
            }

            if(patient.isTempCrucial()){
                patientHolder.txtLeftTemp.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                patientHolder.txtLeftTemp.setTextColor(Color.parseColor("#B3000000"));
            }

            if(patient.isRoomTempCrucial()){
                patientHolder.txtLeftTempRoom.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                patientHolder.txtLeftTempRoom.setTextColor(Color.parseColor("#B3000000"));
            }

            if(patient.isSugarCrucial()){
                patientHolder.txtLeftSugar.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                patientHolder.txtLeftSugar.setTextColor(Color.parseColor("#B3000000"));
            }
        } else {

            patientHolder.cardMain.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            patientHolder.txtLeftPulse.setTextColor(Color.parseColor("#B3000000"));
            patientHolder.txtLeftPressure.setTextColor(Color.parseColor("#B3000000"));
            patientHolder.txtLeftTemp.setTextColor(Color.parseColor("#B3000000"));
            patientHolder.txtLeftTempRoom.setTextColor(Color.parseColor("#B3000000"));
            patientHolder.txtLeftSugar.setTextColor(Color.parseColor("#B3000000"));

        }

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(patientHolder.isImgOnlineVisible){
                    patientHolder.imgOnline.setVisibility(View.GONE);
                }  else {
                    patientHolder.imgOnline.setVisibility(View.VISIBLE);
                }

                patientHolder.isImgOnlineVisible = !patientHolder.isImgOnlineVisible;
                handler.postDelayed(this,1000);
            }
        };

        if(!patientHolder.startedBlink){
            handler.postDelayed(runnable,0);
            patientHolder.startedBlink = true;

        }

    }

    @Override
    public int getItemCount() {
        return mPatients.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private void showMenu(final CardView card, final String name, final String tc, final String relativeNo){

        final CharSequence items[] = {"Hasta Bilgileri", "Hasta Taşı", "Hasta Sil"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.mainContext);
        builder.setTitle(name);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                switch (position){

                    case 0:
                        showAlertDialog("Hasta Bilgileri","Adı > " + name + "\n" + "T.C. Kimlik No > " + tc + "\n" + "Hasta Yakını Telefon > " + relativeNo );
                        break;
                    case 1:
                        //TODO
                        break;
                    case 2:

                        ApiInterface apiService =
                                ApiClient.getClient().create(ApiInterface.class);

                        Call<RestData<Patient>> call = apiService.deletePatient("hastaSil", tc, App.currentPersonel.getTc());
                        call.enqueue(new Callback<RestData<Patient>>() {
                            @Override
                            public void onResponse(Call<RestData<Patient>> call, Response<RestData<Patient>> response) {
                                //TODO
                                if (response.body() != null && response.body().isSuccess()) {
                                    Log.i(TAG, "NET: deletePatient: " + response.body().toString());
                                    App.getPatients(App.currentPersonel.getTc(), mRecycler, MainActivity.mainContext);
                                    Snackbar snackbar = Snackbar.make(card,"Hasta başarıyla silindi!",Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                } else {
                                    showAlertDialog("Hata!", "Beklenmedik bir hata oluştu. Lütfen tekrar deneyin!");
                                }
                            }

                            @Override
                            public void onFailure(Call<RestData<Patient>> call, Throwable t) {
                                showAlertDialog("Hata!", "Beklenmedik bir hata oluştu. Lütfen tekrar deneyin!");
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });
        builder.show();

    }



    private void showAlertDialog(String title, String message){
        android.app.AlertDialog.Builder dialogAbout = new android.app.AlertDialog.Builder(MainActivity.mainContext);
        dialogAbout.setTitle(title);
        dialogAbout.setMessage(message);
        dialogAbout.setPositiveButton("Tamam", null );
        dialogAbout.show();
    }

    public class PatientHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.item_patient_card)
        CardView cardMain;

        @BindView(R.id.item_patient_img_online)
        ImageView imgOnline;

        @BindView(R.id.item_patient_txt_name)
        TextView txtName;

        @BindView(R.id.item_patient_txt_room_no)
        TextView txtRoomNo;

        @BindView(R.id.item_patient_txt_pulse)
        TextView txtPulse;

        @BindView(R.id.item_patient_txt_temp)
        TextView txtTemp;

        @BindView(R.id.item_patient_txt_temp_room)
        TextView txtTempRoom;

        @BindView(R.id.item_patient_txt_blood_pressure)
        TextView txtPressureSYS;

        @BindView(R.id.item_patient_txt_blood_pressure_small)
        TextView txtPressureDIA;

        @BindView(R.id.item_patient_txt_sugar)
        TextView txtSugar;

        @BindView(R.id.item_patient_txt_brain)
        TextView txtBrainActivity;

        @BindView(R.id.item_patient_txt_left_pulse)
        TextView txtLeftPulse;

        @BindView(R.id.item_patient_txt_left_temp)
        TextView txtLeftTemp;

        @BindView(R.id.item_patient_txt_left_temp_room)
        TextView txtLeftTempRoom;

        @BindView(R.id.item_patient_txt_left_blood_pressure)
        TextView txtLeftPressure;

        @BindView(R.id.item_patient_txt_left_sugar)
        TextView txtLeftSugar;

        boolean isImgOnlineVisible = true;

        boolean startedBlink = false;

        public PatientHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }

}
