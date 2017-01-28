package com.example.mow.childrenvaccine;

import android.content.Intent;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.TextView;
        import android.widget.Toast;


public class Vaccine_details extends ActionBarActivity {
    private TextView tvDiseaseName;
    private TextView tvVaccineName;
    private TextView tvDetails;


    private String id;
    private String diseaseName;
    private String vaccineName;
    private String details;


    private Intent intent;
    private Vaccine vaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_details);
        initialize();
    }

    private void initialize() {
        tvDiseaseName = (TextView) findViewById(R.id.tvDiseaseName);
        tvVaccineName = (TextView) findViewById(R.id.tvVaccineName);
        tvDetails = (TextView) findViewById(R.id.tvDetails);


        Bundle b = getIntent().getExtras();
        vaccine = b.getParcelable("vaccine");
        setValue(vaccine);
    }


    private void setValue(Vaccine addVaccine) {
        id = String.valueOf(addVaccine.getVaccineID());
        diseaseName = addVaccine.getDiseaseName();
        vaccineName = addVaccine.getVaccineName();
        details = addVaccine.getDetails();


        tvDiseaseName.setText(diseaseName);
        tvVaccineName.setText(vaccineName);
        tvDetails.setText(details);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vaccine_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){
            case R.id.edit:
                intent = new Intent(Vaccine_details.this, EditActivity.class);
                intent.putExtra("vaccine", vaccine);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }

    private void showMessage(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}


