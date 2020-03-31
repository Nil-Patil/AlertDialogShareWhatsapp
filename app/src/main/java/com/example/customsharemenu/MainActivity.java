package com.example.customsharemenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Context context;
    private String selectedCountyCode = "+91";
    private String enteredNumber = "";
    private String enteredName = "";
    private AlertDialog countyCodeDialog;
    private AlertDialog customWhatsappDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        Button btnOpenShare = findViewById(R.id.btnOpenShare);
        btnOpenShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customWhatsappShowDialog(selectedCountyCode, enteredNumber, enteredName);
            }
        });
    }

    public void showCountyCodePicker() {
        // create an alert builder
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // set the custom layout
        @SuppressLint("InflateParams") final View customLayout = getLayoutInflater().inflate(R.layout.county_code_search_layout, null);
        builder.setView(customLayout);

        RecyclerView recyclerView = customLayout.findViewById(R.id.rvCountyCode);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final CountyRecyclerAdapter recyclerAdapter = new CountyRecyclerAdapter(
                new CountyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CountryModel item) {
                        selectedCountyCode = item.getPhoneCode();
                        customWhatsappShowDialog(selectedCountyCode, enteredNumber, enteredName);
                        countyCodeDialog.dismiss();
                    }
                });
        recyclerView.setAdapter(recyclerAdapter);


        SearchView searchView = customLayout.findViewById(R.id.searchView);
        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        // create and show the alert dialog
        countyCodeDialog = builder.create();
        countyCodeDialog.show();
    }


    public void customWhatsappShowDialog(final String countyCode, String number, String name) {
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // set the custom layout
        @SuppressLint("InflateParams")
        View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        builder.setView(customLayout);
        // add a button
        final EditText etName = customLayout.findViewById(R.id.etName);
        final EditText etNumber = customLayout.findViewById(R.id.etNumber);
        final EditText etCountyCode = customLayout.findViewById(R.id.etCountryCode);
        Button btnSend = customLayout.findViewById(R.id.btnSend);
        Button btnOpenWhatsapp = customLayout.findViewById(R.id.btnWhatsapp);

        etCountyCode.setText(countyCode);
        etNumber.setText(number);
        etName.setText(name);

        etCountyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCountyCode = etCountyCode.getText().toString();
                enteredNumber = etNumber.getText().toString();
                enteredName = etName.getText().toString();
                showCountyCodePicker();
                customWhatsappDialog.dismiss();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countryCode = etCountyCode.getText().toString();
                String number = etNumber.getText().toString();
                String name = etName.getText().toString();
                String smsText = "Hello, " + name + "\n\nTo view this catalog, follow the link above\"\n" +
                        "\"Select the product you like to send your enquiry\"\n\n" + "Regards\n";

                PackageManager packageManager = context.getPackageManager();
                Intent i = new Intent(Intent.ACTION_VIEW);
                try {
                    String url = "https://api.whatsapp.com/send?phone=" + countryCode + number + "&text=" + URLEncoder.encode(smsText, "UTF-8");
                    i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(url));
                    if (i.resolveActivity(packageManager) != null) {
                        context.startActivity(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        btnOpenWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whatsAppIntent = new Intent(Intent.ACTION_SEND);
                whatsAppIntent.setType("text/plain");
                whatsAppIntent.setPackage("com.whatsapp");
                whatsAppIntent.putExtra(Intent.EXTRA_TEXT, "Hello,To view this catalog, follow the link above\"\n" +
                        "\"Select the product you like to send your enquiry\"\n\n" + "Regards\n");
                try {
                    context.startActivity(whatsAppIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context, "Whatsapp have not been installed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // create and show the alert dialog
        customWhatsappDialog = builder.create();
        customWhatsappDialog.show();
    }
}
