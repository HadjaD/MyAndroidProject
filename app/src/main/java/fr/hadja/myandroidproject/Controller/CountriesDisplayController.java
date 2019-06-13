package fr.hadja.myandroidproject.Controller;

/*Controleur d'affichage des pays*/

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fr.hadja.myandroidproject.Activity.AfricanCountriesActivity;
import fr.hadja.myandroidproject.Model.Country;
import fr.hadja.myandroidproject.Model.CountryApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountriesDisplayController implements Callback<List<Country>> {
    private AppCompatActivity view;
    private SharedPreferences sharedPreferences;

    String BASE_URL = "https://restcountries.eu/rest/v2/region/";

    public CountriesDisplayController(AfricanCountriesActivity view,
                                      SharedPreferences sharedPreferences) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
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
        Call<List<Country>> call = null;
        call = countryApi.getAfricanCountries("status:open");
        String continent = view.getClass().getName();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
        if (response.isSuccessful()) {
            List<Country> countries = response.body();
            storeData(countries);
            ((AfricanCountriesActivity)view).displayCountries(countries);

        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Country>> call, Throwable t) {

    }

    private void storeData(List<Country> countries) {
        Gson gson = new Gson();
        String data = gson.toJson(countries);
        sharedPreferences
                .edit()
                .putString("cle_string", data)
                .apply();
    }

    private List<Country> getDataFromCache() {
        String data = sharedPreferences.getString("cle_string", "");
        if (data != null && !TextUtils.isEmpty(data)) {
            Type listType = new TypeToken<List<Country>>() {
            }.getType();
            List<Country> countries = new Gson().fromJson(data, listType);
            return countries;
        }
        return new ArrayList<>();
    }
}

