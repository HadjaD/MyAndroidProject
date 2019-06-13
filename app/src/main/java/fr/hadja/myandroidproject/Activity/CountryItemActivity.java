package fr.hadja.myandroidproject.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.hadja.myandroidproject.Controller.CountryItemController;
import fr.hadja.myandroidproject.Model.Country;
import fr.hadja.myandroidproject.R;

public class CountryItemActivity extends AppCompatActivity {
    public ImageView flag;
    public TextView name;
    public TextView capital;
    public TextView subregion;
    public TextView population;
    public TextView demonym;
    public TextView area;
    public TextView numericCode;
    public TextView alpha2code;
    public TextView callingCode;
    public Country country;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_item);
        //LoadData via CountryItemController
        Intent activ = getIntent();
        String donnee = activ.getStringExtra("cName");
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        CountryItemController controller = new CountryItemController(this,sharedPreferences,donnee);
        controller.start();

    }

    public void displayCountryInformations(Country country) {
        flag = findViewById(R.id.flag);
        name = findViewById(R.id.name);
        capital = findViewById(R.id.capital);
        subregion = findViewById(R.id.subregion);
        demonym = findViewById(R.id.demonym);
        area = findViewById(R.id.area);
        numericCode = findViewById(R.id.numericCode);
        alpha2code = findViewById(R.id.alpha2code);
        callingCode = findViewById(R.id.callingCode);
        population = findViewById(R.id.population);

        Picasso.with(this).load(country.getFlag()).into(flag);
        name.setText("Nom: "+country.getName());
        capital.setText("Capital: " + country.getCapital());
        subregion.setText("Subregion: " + country.getSubregion());
        population.setText("Population: " + country.getPopulation());
        demonym.setText("demonym: " + country.getDemonym());
        area.setText("Area: " + country.getArea());
        numericCode.setText("Numeric Code: " + country.getNumericCode());
        alpha2code.setText("Alpha2code: " + country.getAlpha2Code());
        callingCode.setText("Calling Code: " + country.getCallingCodes().get(0));

    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
