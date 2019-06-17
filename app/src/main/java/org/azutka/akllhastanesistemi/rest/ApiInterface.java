package org.azutka.akllhastanesistemi.rest;


import com.google.gson.annotations.SerializedName;

import org.azutka.akllhastanesistemi.models.Patient;
import org.azutka.akllhastanesistemi.models.Personnel;
import org.azutka.akllhastanesistemi.models.Settings;
import org.azutka.akllhastanesistemi.models.rest.RestData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiInterface {


    @FormUrlEncoded
    @POST("veri.php")
    Call<RestData<Personnel>> login(@Field("islem") String which, @Field("tc_no") String tc, @Field("sifre") String pass);


    @FormUrlEncoded
    @POST("veri.php")
    Call<RestData<List<Patient>>> getPatients(@Field("islem") String which, @Field("tc_no") String tc);


    @FormUrlEncoded
    @POST("veri.php")
    Call<RestData<Patient>> addPatient(@Field("islem") String which, @Field("hasta_tc_no") String patientTc, @Field("tc_no") String tc, @Field("ad") String name, @Field("soyad") String surname, @Field("dogum_tarih") String birthday, @Field("tansiyon") String blood_pressure, @Field("son_nabiz") String pulse, @Field("hasta_sicaklik") String patient_temperature, @Field("seker") String sugar, @Field("beyin_aktivite") String brain_activity, @Field("oda_sicaklik") String room_temperature, @Field("oda_no") String room_no, @Field("hasta_yakin_tel_no") String relative_phone, @Field("son_guncellenme") String last_update, @Field("hasta_eklenme_tarih") String patient_creation_date, @Field("gorevli_personel") String assignee_personels, @Field("kritik_nabiz_min") String crucial_pulse_min, @Field("kritik_nabiz_max") String crucial_pulse_max, @Field("kritik_room_temp_min") String crucial_room_temp_min, @Field("kritik_room_temp_max") String crucial_room_temp_max, @Field("kritik_sicaklik_min") String crucial_temp_min, @Field("kritik_sicaklik_max") String crucial_temp_max, @Field("kritik_buyuk_tansiyon_min") String crucial_pressure_sys_min, @Field("kritik_buyuk_tansiyon_max") String crucial_pressure_sys_max, @Field("kritik_kucuk_tansiyon_min") String crucial_pressure_dia_min, @Field("kritik_kucuk_tansiyon_max") String crucial_pressure_dia_max, @Field("kritik_seker_min") String crucial_sugar_min, @Field("kritik_seker_max") String crucial_sugar_max);


    @FormUrlEncoded
    @POST("veri.php")
    Call<RestData<Patient>> deletePatient(@Field("islem") String which, @Field("hasta_tc_no") String tc, @Field("tc_no") String personel_tc);

    @FormUrlEncoded
    @POST("veri.php")
    Call<RestData<Settings>> getSettings(@Field("islem") String which);

}
