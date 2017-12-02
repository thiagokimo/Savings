package io.kimo.savings.data;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.kimo.savings.domain.model.Saving;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

public class SavingsService {

    private static final String ENDPOINT = "http://qapital-ios-testtask.herokuapp.com/";
    private static final String SAVINGS_PATH = "savingsgoals";

    private OkHttpClient mOkHttpClient;

    public SavingsService(@NonNull OkHttpClient okHttpClient) {
        mOkHttpClient = okHttpClient;
    }

    public Observable<Saving> getSavings() {
        return getServerResponse()
                .concatMap(this::convertResponseToJson)
                .concatMap(this::parseSavings);
    }

    private Observable<Saving> parseSavings(JSONObject json) {
        return Observable.create(subscriber -> {
            try {
                JSONArray savings = json.getJSONArray("savingsGoals");

                for (int i = 0; i < savings.length(); i++) {
                    JSONObject saving = savings.getJSONObject(i);
                    Saving savingModel = new Saving();

                    if (saving.has("goalImageURL")) {
                        savingModel.setImageUrl(saving.getString("goalImageURL"));
                    }

                    if (saving.has("currentBalance")) {
                        savingModel.setCurrentAmount(saving.getString("currentBalance"));
                    }

                    if (saving.has("targetAmount")) {
                        savingModel.setTargetAmount(saving.getString("targetAmount"));
                    }

                    if (saving.has("name")) {
                        savingModel.setName(saving.getString("name"));
                    }

                    subscriber.onNext(savingModel);
                }
                subscriber.onCompleted();
            } catch(JSONException e) {
                subscriber.onError(e);
            }
        });
    }

    private Observable<JSONObject> convertResponseToJson(Response response) {
        return Observable.create(subscriber -> {
            try {
                JSONObject jsonObject = new JSONObject(response.body().string());
                subscriber.onNext(jsonObject);
                subscriber.onCompleted();
            } catch (NullPointerException|IOException|JSONException e) {
                subscriber.onError(e);
            }
        });
    }

    private Observable<Response> getServerResponse() {
        return Observable.create(subscriber -> {
            try {
                Request request = new Request.Builder()
                        .url(ENDPOINT + SAVINGS_PATH)
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64)")
                        .build();

                subscriber.onNext(mOkHttpClient.newCall(request).execute());
                subscriber.onCompleted();
            } catch (IOException e) {
                subscriber.onError(e);
            }
        });
    }
}
