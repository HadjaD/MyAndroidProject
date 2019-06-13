package fr.hadja.myandroidproject.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.hadja.myandroidproject.Adapter.CountriesDisplayAdapter;
import fr.hadja.myandroidproject.Controller.CountriesDisplayController;
import fr.hadja.myandroidproject.Model.Country;
import fr.hadja.myandroidproject.R;

public class AfricanCountriesActivity extends AppCompatActivity {
    private RecyclerView countryList;
    private CountriesDisplayAdapter adapter;
    private List<Country> countries = new ArrayList<>();
    String BASE_URL = /*"https://github.com/HadjaD/Android_projet_3AS2_ESIEA/tree/master/"*/"https://restcountries.eu/rest/v2/";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_countries_recycler);
        //Debut de l'affichage des pays
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        CountriesDisplayController controller = new CountriesDisplayController(this,
                sharedPreferences);
        controller.start();

    }
    public void displayCountries(List<Country> countries){
        countryList = findViewById(R.id.country_recycler);
        countryList.setLayoutManager(new LinearLayoutManager(this));
        countryList.setHasFixedSize(true);
        countryList.setHasFixedSize(true);
        adapter = new CountriesDisplayAdapter(countries);
        countryList.setAdapter(adapter);

    }
}

