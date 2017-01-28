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


public class EditActivity extends ActionBarActivity implements View.OnClickListener {
    private EditText etDiseaseName;
    private EditText etVaccineName;
    private EditText etDetails;


    private String diseaseName;
    private String vaccineName;
    private String details;

    private Button btnUpdateProfile;
    private Vaccine addVaccine;
    private VaccineDBHandler dbHandler;
    private Intent intent;
    private static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initialize();
        Bundle b = getIntent().getExtras();
        addVaccine = b.getParcelable("vaccine");
        setValue(addVaccine);
        btnUpdateProfile.setOnClickListener(this);
    }
    private void initialize() {
        dbHandler = new VaccineDBHandler(EditActivity.this);

        etDiseaseName=(EditText)findViewById(R.id.etDiseaseName);
        etVaccineName =(EditText)findViewById(R.id.etVaccineName);
        etDetails = (EditText)findViewById(R.id.etDetails);

        btnUpdateProfile = (Button) findViewById(R.id.btnUpdate);
    }

    private void setValue(Vaccine vaccine) {
        id = vaccine.getVaccineID();
        showMessage(String.valueOf(id));
        etDiseaseName.setText(vaccine.getDiseaseName());
        showMessage(vaccine.getDiseaseName());
        etVaccineName.setText(vaccine.getVaccineName());
        etDetails.setText(vaccine.getDetails());

    }

    private void getValue() {
        Vaccine upVaccine = new Vaccine();
        diseaseName = etDiseaseName.getText().toString();
        vaccineName = etVaccineName.getText().toString();
        details = etDetails.getText().toString();

        upVaccine.setVaccineID(id);
        upVaccine.setDiseaseName(diseaseName);
        upVaccine.setVaccineName(vaccineName);
        upVaccine.setDetails(details);

        dbHandler.updateProfile(upVaccine);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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
            case R.id.btnUpdate:
                getValue();
                showMessage("Updated Successfully");
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


