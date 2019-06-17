package org.azutka.akllhastanesistemi.models;

import com.google.gson.annotations.SerializedName;

public class Settings {

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


    public Settings(String crucial_pulse_min, String crucial_pulse_max, String crucial_room_temp_min, String crucial_room_temp_max, String crucial_temp_min, String crucial_temp_max, String crucial_pressure_sys_min, String crucial_pressure_sys_max, String crucial_pressure_dia_min, String crucial_pressure_dia_max, String crucial_sugar_min, String crucial_sugar_max) {
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
}
