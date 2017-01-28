package com.example.mow.childrenvaccine;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddVaccine extends ActionBarActivity implements View.OnClickListener {
    private EditText editTextDiseaseName;
    private EditText editTextVaccineName;
    private EditText editTextDetails;



    private String diseaseName;
    private String vaccineName;
    private String details;


    private Button btnSave;
    public Vaccine addVaccine;
    private VaccineDBHandler dbHandler;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine);

        initialize_vaccine();
        btnSave.setOnClickListener(this);

    }

    private void initialize_vaccine() {
        addVaccine = new Vaccine();
        dbHandler = new VaccineDBHandler(AddVaccine.this);

        editTextDiseaseName = (EditText) findViewById(R.id.etDisease);
        editTextVaccineName = (EditText) findViewById( R.id.etVaccineName);
        editTextDetails = (EditText) findViewById(R.id.etDetails);

        btnSave = (Button) findViewById(R.id.buttonSave);
    }

    private void getValue() {
        diseaseName = editTextDiseaseName.getText().toString();
        vaccineName = editTextVaccineName.getText().toString();
        details = editTextDetails.getText().toString();


        addVaccine.setDiseaseName(diseaseName);
        addVaccine.setVaccineName(vaccineName);
        addVaccine.setDetails(details);


        dbHandler.addChildVaccine(addVaccine);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_vaccine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSave:
                getValue();

                showMessage("Saved Successfully");
                finish();
                break;
            default:
                break;
        }
    }
    private void showMessage(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}

