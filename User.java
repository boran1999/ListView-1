package com.example.customadapterdemo;

import android.util.Log;

import androidx.annotation.NonNull;

public class User {
    String name, phoneNumber, sex;
    Gender gend;

    public User(String name, String phoneNumber, String sex) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.sex=sex;
        if(sex.equals("MAN"))
            this.gend = Gender.MAN;
        if(sex.equals("WOMAN"))
            this.gend = Gender.WOMAN;
        if(sex.equals("UNKNOWN"))
            this.gend = Gender.UNKNOWN;
    }
}
