package fr.hadja.myandroidproject.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CountryApi {
    @GET("africa")
    Call<List<Country>> getAfricanCountries(@Query("q") String status);

    @GET("name/{name}")
    Call<Country> getCountry(@Path("name") String name );
}
