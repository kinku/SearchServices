package com.example.app.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.app.services.Activity.CreateAnnounce;
import com.example.app.services.Activity.Login;
import com.example.app.services.Activity.Register;
import com.example.app.services.Activity.RetrieveAnnounceByServices;

public class MainActivity extends AppCompatActivity {

    private Button createAnnounce, searchAnnounce, myAnnounce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createAnnounce = (Button) findViewById(R.id.creareanunt);
        //myAnnounce = (Button) findViewById(R.id.anuntulmeu);
        searchAnnounce = (Button) findViewById(R.id.cautaanunt);

        createAnnounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAnnounce.class);
                startActivity(intent);
            }
        });
        searchAnnounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RetrieveAnnounceByServices.class));
            }
        });
        /*myAnnounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RetrieveMyAnnounce.class);
                startActivity(intent);
            }
        });*/

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.login_menu:
                startActivity(new Intent(this, Login.class));
                return true;
            case R.id.register_menu:
                startActivity(new Intent(this, Register.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
