package com.example.app.services.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.app.services.R;
import com.example.app.services.userInfo.UserInformation;

import java.util.List;

/**
 * Created by Claudiu on 29.08.2017.
 */

public class ListForRetrieveAdapter extends ArrayAdapter<UserInformation>{

    private TextView textViewNameCompany, textViewNameContact, textViewPhoneNumber, textViewDescription, textViewServices, textViewCountry;
    Activity context;
    List<UserInformation> listForRetrieve;

    public ListForRetrieveAdapter(Activity context, List<UserInformation> listForRetrieve){
        super(context, R.layout.list_announce, listForRetrieve);
        this.context = context;
        this.listForRetrieve = listForRetrieve;

    }


    @Nullable
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View listViewItem = inflater.inflate(R.layout.list_announce, null, true);

        //textViewcontactMail = (TextView) listViewItem.findViewById(R.id.yourContactmail);
        textViewServices = (TextView) listViewItem.findViewById(R.id.yourservices);
        textViewNameCompany = (TextView) listViewItem.findViewById(R.id.yourcompanyname);
        textViewNameContact = (TextView) listViewItem.findViewById(R.id.yourname);
        textViewPhoneNumber = (TextView) listViewItem.findViewById(R.id.yourphone);
        textViewDescription = (TextView) listViewItem.findViewById(R.id.yourdescription);
        textViewCountry = (TextView) listViewItem.findViewById(R.id.yourCountry);
        UserInformation announce = listForRetrieve.get(position);

        //textViewcontactMail.setText(announce.getMailContact());
        textViewCountry.setText(announce.getCountry());
        textViewServices.setText(announce.getServicies());
        textViewNameCompany.setText(announce.getCompanyName());
        textViewNameContact.setText(announce.getContactName());
        textViewPhoneNumber.setText(announce.getPhoneNumber());
        textViewDescription.setText(announce.getDescription());

        return listViewItem;

    }
}
