package io.kimo.savings.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class Injection {

    public static OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public static Picasso picasso(@NonNull Context context) {
        return Picasso.with(context);
    }
}
