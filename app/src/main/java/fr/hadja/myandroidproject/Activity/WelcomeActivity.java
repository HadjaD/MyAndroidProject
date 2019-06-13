package fr.hadja.myandroidproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fr.hadja.myandroidproject.R;

public class WelcomeActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.button = findViewById(R.id.start_button);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continentChoiceActivity = new Intent(getApplicationContext(),ShowContinentsActivity.class);
                getApplicationContext().startActivity(continentChoiceActivity);
                //finish();
            }
        });
    }
}
