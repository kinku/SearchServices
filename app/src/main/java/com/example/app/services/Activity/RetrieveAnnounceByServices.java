package com.example.app.services.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.app.services.R;
import com.example.app.services.adapter.ListForRetrieveAdapter;
import com.example.app.services.userInfo.UserInformation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class RetrieveAnnounceByServices extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private Spinner getlistbyAnnounceServices, countryList;
    private DatabaseReference databaseReference;

    private ListView listViewAnnounceByServices;
    private List<UserInformation> announceListServices;

    private Button takeannouncebyServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieving_announce_by_services);

        countryList = (Spinner) findViewById(R.id.country_spinner);
        takeannouncebyServices = (Button) findViewById(R.id.take_announce_by_country);
        listViewAnnounceByServices = (ListView) findViewById(R.id.listview_for_announce);
        progressDialog = new ProgressDialog(this);
        announceListServices = new ArrayList<>();

        takeannouncebyServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String getAnnounceByCountry = countryList.getSelectedItem().toString();
                //final String getlistbyservices = getlistbyAnnounceServices.getSelectedItem().toString();

                databaseReference = FirebaseDatabase.getInstance().getReference(String.valueOf(getAnnounceByCountry));
                //databaseReference.child(String.valueOf(getAnnounceByCountry)).child(String.valueOf(getlistbyservices));

                progressDialog.setMessage("Searching announce...");
                progressDialog.show();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        announceListServices.clear();
                        for(DataSnapshot announceSnapshot : dataSnapshot.getChildren()){
                            UserInformation announce = announceSnapshot.getValue(UserInformation.class);
                            announceListServices.add(announce);
                        }
                        ListForRetrieveAdapter adapter = new ListForRetrieveAdapter(RetrieveAnnounceByServices.this,announceListServices);
                        listViewAnnounceByServices.setAdapter(adapter);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(RetrieveAnnounceByServices.this, "Announce read failed" + databaseError.getCode(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                });
            }
        });
        TakeLocation();
    }
    public void TakeLocation(){
        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<>();
        String[] country = new String[1];
        for( Locale loc : locale){
            country[0] = loc.getDisplayCountry();
            if(country[0].length() > 0 && !countries.contains(country[0])){
                countries.add(country[0]);
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, countries );
        countryList.setAdapter(adapter);
    }
}
