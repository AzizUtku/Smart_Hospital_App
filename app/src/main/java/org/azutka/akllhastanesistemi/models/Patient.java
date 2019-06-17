package org.azutka.akllhastanesistemi.models;

import com.google.gson.annotations.SerializedName;

import org.azutka.akllhastanesistemi.core.App;

public class Patient {

    @SerializedName("tc_no")
    public String tc;

    @SerializedName("ad")
    public String name;

    @SerializedName("soyad")
    public String surname;

    @SerializedName("dogumtarih")
    public String birthday;

    @SerializedName("tansiyon")
    public String blood_pressure;

    @SerializedName("son_nabiz")
    public String pulse;

    @SerializedName("hasta_sicaklik")
    public String patient_temperature;

    @SerializedName("seker")
    public String sugar;

    @SerializedName("beyin_aktivite")
    public String brain_activity;

    @SerializedName("oda_sicaklik")
    public String room_temperature;

    @SerializedName("oda_no")
    public String room_no;

    @SerializedName("hasta_yakin_tel_no")
    public String relative_phone;

    @SerializedName("son_guncellenme")
    public String last_update;

    @SerializedName("hasta_eklenme_tarih")
    public String patient_creation_date;

    @SerializedName("gorevli_personel_json")
    public String assignee_personels;

    @SerializedName("kritik_nabiz_min")
    public String crucial_pulse_min;

    @SerializedName("kritik_nabiz_max")
    public String crucial_pulse_max;

    @SerializedName("kritik_room_temp_min")
    public String crucial_room_temp_min;

    @SerializedName("kritik_room_temp_max")
    public String crucial_room_temp_max;

    @SerializedName("kritik_sicaklik_min")
    public String crucial_temp_min;

    @SerializedName("kritik_sicaklik_max")
    public String crucial_temp_max;

    @SerializedName("kritik_buyuk_tansiyon_min")
    public String crucial_pressure_sys_min;

    @SerializedName("kritik_buyuk_tansiyon_max")
    public String crucial_pressure_sys_max;

    @SerializedName("kritik_kucuk_tansiyon_min")
    public String crucial_pressure_dia_min;

    @SerializedName("kritik_kucuk_tansiyon_max")
    public String crucial_pressure_dia_max;

    @SerializedName("kritik_seker_min")
    public String crucial_sugar_min;

    @SerializedName("kritik_seker_max")
    public String crucial_sugar_max;


    public Patient(String tc, String name, String surname, String birthday, String blood_pressure, String pulse, String patient_temperature, String sugar, String brain_activity, String room_temperature, String room_no, String relative_phone, String last_update, String patient_creation_date, String assignee_personels, String crucial_pulse_min, String crucial_pulse_max, String crucial_room_temp_min, String crucial_room_temp_max, String crucial_temp_min, String crucial_temp_max, String crucial_pressure_sys_min, String crucial_pressure_sys_max, String crucial_pressure_dia_min, String crucial_pressure_dia_max, String crucial_sugar_min, String crucial_sugar_max) {
        this.tc = tc;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.blood_pressure = blood_pressure;
        this.pulse = pulse;
        this.patient_temperature = patient_temperature;
        this.sugar = sugar;
        this.brain_activity = brain_activity;
        this.room_temperature = room_temperature;
        this.room_no = room_no;
        this.relative_phone = relative_phone;
        this.last_update = last_update;
        this.patient_creation_date = patient_creation_date;
        this.assignee_personels = assignee_personels;
        this.crucial_pulse_min = crucial_pulse_min;
        this.crucial_pulse_max = crucial_pulse_max;
        this.crucial_room_temp_min = crucial_room_temp_min;
        this.crucial_room_temp_max = crucial_room_temp_max;
        this.crucial_temp_min = crucial_temp_min;
        this.crucial_temp_max = crucial_temp_max;
        this.crucial_pressure_sys_min = crucial_pressure_sys_min;
        this.crucial_pressure_sys_max = crucial_pressure_sys_max;
        this.crucial_pressure_dia_min = crucial_pressure_dia_min;
        this.crucial_pressure_dia_max = crucial_pressure_dia_max;
        this.crucial_sugar_min = crucial_sugar_min;
        this.crucial_sugar_max = crucial_sugar_max;
    }

    public Patient() {

    }

    public boolean isPulseCrucial(){

        if(getPulse() == null || getPulse().isEmpty())
            return false;

        int value = Integer.parseInt(getPulse());

        if(getCrucial_pulse_min().isEmpty()){
            setCrucial_pulse_min(App.settings.getCrucial_pulse_min());
        }

        if(getCrucial_pulse_max().isEmpty()){
            setCrucial_pulse_max(App.settings.getCrucial_pulse_max());
        }


        int crucialMin = Integer.parseInt(getCrucial_pulse_min());
        int crucialMax = Integer.parseInt(getCrucial_pulse_max());

        if( value < crucialMin  || value > crucialMax ){
            return true;
        }

        return false;
    }

    public boolean isTempCrucial(){

        if(getPatient_temperature() == null || getPatient_temperature().isEmpty())
            return false;

        int value = Integer.parseInt(getPatient_temperature());

        if(getCrucial_temp_min().isEmpty()){
            setCrucial_temp_min(App.settings.getCrucial_temp_min());
        }

        if(getCrucial_temp_max().isEmpty()){
            setCrucial_temp_max(App.settings.getCrucial_temp_max());
        }

        int crucialMin = Integer.parseInt(getCrucial_temp_min());
        int crucialMax = Integer.parseInt(getCrucial_temp_max());

        if( value < crucialMin  || value > crucialMax ){
            return true;
        }

        return false;
    }

    public boolean isRoomTempCrucial(){

        if(getRoom_temperature() == null || getRoom_temperature().isEmpty())
            return false;

        int roomTemp = Integer.parseInt(getRoom_temperature());

        if(getCrucial_room_temp_min().isEmpty()){
            setCrucial_room_temp_min(App.settings.getCrucial_room_temp_min());
        }

        if(getCrucial_room_temp_max().isEmpty()){
            setCrucial_room_temp_max(App.settings.getCrucial_room_temp_max());
        }

        int crucialRoomTempMin = Integer.parseInt(getCrucial_room_temp_min());
        int crucialRoomTempMax = Integer.parseInt(getCrucial_room_temp_max());

        if( roomTemp < crucialRoomTempMin  || roomTemp > crucialRoomTempMax ){
            return true;
        }
        return false;
    }

    public boolean isSysPressureCrucial(){

        if(getBlood_pressure() == null || getBlood_pressure().isEmpty() || getBlood_pressure().split("/").length < 2 )
            return false;

        int value = Integer.parseInt(getBlood_pressure().split("/")[0]);

        if(getCrucial_pressure_sys_min().isEmpty()){
            setCrucial_pressure_sys_min(App.settings.getCrucial_pressure_sys_min());
        }

        if(getCrucial_pressure_sys_max().isEmpty()){
            setCrucial_pressure_sys_max(App.settings.getCrucial_pressure_sys_max());
        }


        int crucialMin = Integer.parseInt(getCrucial_pressure_sys_min());
        int crucialMax = Integer.parseInt(getCrucial_pressure_sys_max());

        if( value < crucialMin  || value > crucialMax ){
            return true;
        }

        return false;
    }

    public boolean isDiaPressureCrucial(){

        if(getBlood_pressure() == null || getBlood_pressure().isEmpty() || getBlood_pressure().split("/").length < 2 )
            return false;

        int value = Integer.parseInt(getBlood_pressure().split("/")[1]);

        if(getCrucial_pressure_dia_min().isEmpty()){
            setCrucial_pressure_dia_min(App.settings.getCrucial_pressure_dia_min());
        }

        if(getCrucial_pressure_dia_max().isEmpty()){
            setCrucial_pressure_dia_max(App.settings.getCrucial_pressure_dia_max());
        }

        int crucialMin = Integer.parseInt(getCrucial_pressure_dia_min());
        int crucialMax = Integer.parseInt(getCrucial_pressure_dia_max());

        if( value < crucialMin  || value > crucialMax ){
            return true;
        }

        return false;
    }

    public boolean isSugarCrucial(){

        if(getSugar() == null || getSugar().isEmpty() )
            return false;

        int value = Integer.parseInt(getSugar());

        if(getCrucial_sugar_min().isEmpty()){
            setCrucial_sugar_min(App.settings.getCrucial_sugar_min());
        }

        if(getCrucial_sugar_max().isEmpty()){
            setCrucial_sugar_max(App.settings.getCrucial_sugar_max());
        }

        int crucialMin = Integer.parseInt(getCrucial_sugar_min());
        int crucialMax =  Integer.parseInt(getCrucial_sugar_max());

        if( value < crucialMin  || value > crucialMax ){
            return true;
        }

        return false;
    }


    //Getters and Setters
    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(String blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getPatient_temperature() {
        return patient_temperature;
    }

    public void setPatient_temperature(String patient_temperature) {
        this.patient_temperature = patient_temperature;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getBrain_activity() {
        return brain_activity;
    }

    public void setBrain_activity(String brain_activity) {
        this.brain_activity = brain_activity;
    }

    public String getRoom_temperature() {
        return room_temperature;
    }

    public void setRoom_temperature(String room_temperature) {
        this.room_temperature = room_temperature;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getRelative_phone() {
        return relative_phone;
    }

    public void setRelative_phone(String relative_phone) {
        this.relative_phone = relative_phone;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getPatient_creation_date() {
        return patient_creation_date;
    }

    public void setPatient_creation_date(String patient_creation_date) {
        this.patient_creation_date = patient_creation_date;
    }

    public String getAssignee_personels() {
        return assignee_personels;
    }

    public void setAssignee_personels(String assignee_personels) {
        this.assignee_personels = assignee_personels;
    }

    public String getCrucial_pulse_min() {
        return crucial_pulse_min;
    }

    public void setCrucial_pulse_min(String crucial_pulse_min) {
        this.crucial_pulse_min = crucial_pulse_min;
    }

    public String getCrucial_pulse_max() {
        return crucial_pulse_max;
    }

    public void setCrucial_pulse_max(String crucial_pulse_max) {
        this.crucial_pulse_max = crucial_pulse_max;
    }

    public String getCrucial_room_temp_min() {
        return crucial_room_temp_min;
    }

    public void setCrucial_room_temp_min(String crucial_room_temp_min) {
        this.crucial_room_temp_min = crucial_room_temp_min;
    }

    public String getCrucial_room_temp_max() {
        return crucial_room_temp_max;
    }

    public void setCrucial_room_temp_max(String crucial_room_temp_max) {
        this.crucial_room_temp_max = crucial_room_temp_max;
    }

    public String getCrucial_temp_min() {
        return crucial_temp_min;
    }

    public void setCrucial_temp_min(String crucial_temp_min) {
        this.crucial_temp_min = crucial_temp_min;
    }

    public String getCrucial_temp_max() {
        return crucial_temp_max;
    }

    public void setCrucial_temp_max(String crucial_temp_max) {
        this.crucial_temp_max = crucial_temp_max;
    }

    public String getCrucial_pressure_sys_min() {
        return crucial_pressure_sys_min;
    }

    public void setCrucial_pressure_sys_min(String crucial_pressure_sys_min) {
        this.crucial_pressure_sys_min = crucial_pressure_sys_min;
    }

    public String getCrucial_pressure_sys_max() {
        return crucial_pressure_sys_max;
    }

    public void setCrucial_pressure_sys_max(String crucial_pressure_sys_max) {
        this.crucial_pressure_sys_max = crucial_pressure_sys_max;
    }

    public String getCrucial_pressure_dia_min() {
        return crucial_pressure_dia_min;
    }

    public void setCrucial_pressure_dia_min(String crucial_pressure_dia_min) {
        this.crucial_pressure_dia_min = crucial_pressure_dia_min;
    }

    public String getCrucial_pressure_dia_max() {
        return crucial_pressure_dia_max;
    }

    public void setCrucial_pressure_dia_max(String crucial_pressure_dia_max) {
        this.crucial_pressure_dia_max = crucial_pressure_dia_max;
    }

    public String getCrucial_sugar_min() {
        return crucial_sugar_min;
    }

    public void setCrucial_sugar_min(String crucial_sugar_min) {
        this.crucial_sugar_min = crucial_sugar_min;
    }

    public String getCrucial_sugar_max() {
        return crucial_sugar_max;
    }

    public void setCrucial_sugar_max(String crucial_sugar_max) {
        this.crucial_sugar_max = crucial_sugar_max;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "tc='" + tc + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", blood_pressure='" + blood_pressure + '\'' +
                ", pulse='" + pulse + '\'' +
                ", patient_temperature='" + patient_temperature + '\'' +
                ", sugar='" + sugar + '\'' +
                ", brain_activity='" + brain_activity + '\'' +
                ", room_temperature='" + room_temperature + '\'' +
                ", room_no='" + room_no + '\'' +
                ", relative_phone='" + relative_phone + '\'' +
                ", last_update='" + last_update + '\'' +
                ", patient_creation_date='" + patient_creation_date + '\'' +
                ", assignee_personels='" + assignee_personels + '\'' +
                ", crucial_pulse_min='" + crucial_pulse_min + '\'' +
                ", crucial_pulse_max='" + crucial_pulse_max + '\'' +
                ", crucial_room_temp_min='" + crucial_room_temp_min + '\'' +
                ", crucial_room_temp_max='" + crucial_room_temp_max + '\'' +
                ", crucial_temp_min='" + crucial_temp_min + '\'' +
                ", crucial_temp_max='" + crucial_temp_max + '\'' +
                ", crucial_pressure_sys_min='" + crucial_pressure_sys_min + '\'' +
                ", crucial_pressure_sys_max='" + crucial_pressure_sys_max + '\'' +
                ", crucial_pressure_dia_min='" + crucial_pressure_dia_min + '\'' +
                ", crucial_pressure_dia_max='" + crucial_pressure_dia_max + '\'' +
                ", crucial_sugar_min='" + crucial_sugar_min + '\'' +
                ", crucial_sugar_max='" + crucial_sugar_max + '\'' +
                '}';
    }
}
