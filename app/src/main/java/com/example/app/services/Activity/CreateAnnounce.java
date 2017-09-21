package com.example.app.services.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.services.MainActivity;
import com.example.app.services.R;
import com.example.app.services.userInfo.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class CreateAnnounce extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLougout, buttonSave;
    private ProgressDialog progressDialog;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private EditText description, companyname, phonecontact, namecontact;

    private Spinner service, countryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_announce);
        textViewUserEmail = (TextView) findViewById(R.id.textuserMail);
        buttonLougout = (Button) findViewById(R.id.button_logout);
        buttonSave = (Button) findViewById(R.id.button_public_announce);
        service = (Spinner) findViewById(R.id.spinnerservicies);
        countryList = (Spinner) findViewById(R.id.countryspinner);

        description = (EditText) findViewById(R.id.description);
        companyname = (EditText) findViewById(R.id.company_name);
        phonecontact = (EditText) findViewById(R.id.phone_contact);
        namecontact = (EditText) findViewById(R.id.contact_name);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);



        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail.setText(user.getEmail());


        buttonLougout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == buttonLougout){
                    firebaseAuth.signOut();
                    Intent intent = new Intent(CreateAnnounce.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(CreateAnnounce.this, "You are successful logged out", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == buttonSave){
                    saveUserInfo();
                }
            }
        });

        //firebaseAuth = FirebaseAuth.getInstance();
        TakeLocation();
    }
    private void saveUserInfo(){
        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //String mailContact = contactmail.getText().toString().trim();
        String companyName = companyname.getText().toString().trim();
        String nameContact = namecontact.getText().toString().trim();
        String phonenumber = phonecontact.getText().toString().trim();
        String descriptiones = description.getText().toString().trim();
        String service_spinner = service.getSelectedItem().toString();
        String country_spinner = countryList.getSelectedItem().toString();

        progressDialog.setMessage("Publishing announce...");
        progressDialog.show();

        FirebaseUser user = firebaseAuth.getCurrentUser();


        if(!TextUtils.isEmpty(country_spinner)){
            if(!TextUtils.isEmpty(service_spinner)){
                UserInformation userInformation = new UserInformation(country_spinner, service_spinner, companyName, nameContact, phonenumber, descriptiones);
                databaseReference.child(String.valueOf(country_spinner)).child(user.getUid()).setValue(userInformation);
                progressDialog.dismiss();
                Toast.makeText(this, "Published successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateAnnounce.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                progressDialog.dismiss();
                Toast.makeText(this, "Select activity domain", Toast.LENGTH_SHORT).show();
            }

        }else{
            progressDialog.dismiss();
            Toast.makeText(this, "Select country", Toast.LENGTH_SHORT).show();
        }

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
