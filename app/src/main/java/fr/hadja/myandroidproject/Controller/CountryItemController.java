package fr.hadja.myandroidproject.Controller;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import fr.hadja.myandroidproject.Activity.CountryItemActivity;
import fr.hadja.myandroidproject.Model.Country;
import fr.hadja.myandroidproject.Model.CountryApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryItemController implements Callback<Country> {

    private CountryItemActivity view;
    private SharedPreferences sharedPreferences;
    private String countryName;
    String BASE_URL = "https://restcountries.eu/rest/v2/";

    public CountryItemController(CountryItemActivity view,
                                 SharedPreferences sharedPreferences,
                                 String countryName) {
        this.sharedPreferences = sharedPreferences;
        this.countryName = countryName;
        this.view = view;
    }
    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        CountryApi countryApi = retrofit.create(CountryApi.class);
        Call<Country> call = countryApi.getCountry(countryName);
        call.enqueue(this);

    }


    private void storeData(Country country) {
        Gson gson = new Gson();
        String data = gson.toJson(country);
        sharedPreferences
                .edit()
                .putString("cle_string" + countryName, data)
                .apply();
    }

    private Country getDataFromCache() {
        String country = sharedPreferences.getString("cle_string"  + countryName, "");
        if(country != null && !TextUtils.isEmpty(country)){
            Type listType = new TypeToken<Country>(){}.getType();
            Country pays = new Gson().fromJson(country, listType);
            return pays;
        }
        return new Country();
    }

    @Override
    public void onResponse(Call<Country> call, Response<Country> response) {
        Country country = response.body();
        storeData(country);
        view.displayCountryInformations(country);
    }

    @Override
    public void onFailure(Call<Country> call, Throwable t) {

    }

}

