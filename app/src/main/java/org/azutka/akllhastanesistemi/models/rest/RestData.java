package org.azutka.akllhastanesistemi.models.rest;

import com.google.gson.annotations.SerializedName;

import org.azutka.akllhastanesistemi.models.Personnel;

public class RestData<T> {

    @SerializedName("sonuc")
    public boolean success;

    @SerializedName("veri")
    public T data;

    @SerializedName("mesaj")
    public String message;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
