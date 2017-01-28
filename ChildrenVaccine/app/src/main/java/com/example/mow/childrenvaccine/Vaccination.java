package com.example.mow.childrenvaccine;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ListAdapter;

import java.util.List;


public class Vaccination extends ActionBarActivity {

    private Intent intent;
    private ListView lvChild;
    private VaccineDBHandler dbHandler;
    private List<Vaccine> vaccineList;
    private CustomVaccineAdapter adapter;
    private Vaccine vaccine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);
        initialize();


    }

    private void initialize() {
        lvChild = (ListView) findViewById(R.id.lvVaccine);
        dbHandler = new VaccineDBHandler(Vaccination.this);
        updateDatabase();

        lvChild.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vaccine = (Vaccine) parent.getItemAtPosition(position);
               intent = new Intent(Vaccination.this, Vaccine_details.class);
               intent.putExtra("vaccine", vaccine);
                startActivity(intent);

            }
        });

    }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            switch(id) {
                case R.id.new_profile:
                    intent = new Intent(Vaccination.this, AddVaccine.class);
                    startActivity(intent);
                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }

        }




    private void updateDatabase() {
        vaccineList = dbHandler.getAllProfile();
        if (!vaccineList.isEmpty()) {
            adapter = new CustomVaccineAdapter(this, vaccineList);
            lvChild.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDatabase();
    }


}


