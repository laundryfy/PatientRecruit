package com.oneworldinc.android.patientrecruit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = getLayoutInflater().inflate(R.layout.action_bar_view, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView titleView = (TextView) view.findViewById(R.id.title_text);
        titleView.setText("Welcome to the Bayer Clinical Trial Recruitment Center");

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setElevation(0);
            actionBar.setCustomView(view);
            actionBar.setDisplayShowCustomEnabled(true);
        }



        next=(Button)findViewById(R.id.next);

        Spinner specialty= (Spinner)findViewById(R.id.specialty);
        Spinner state=(Spinner)findViewById(R.id.state);
        String[] specialtyItem = new String[]{"Specialty","Cardiologist", "Hematologist", "Rheumatologist","Pulmonologist","Infectious Disease","Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, specialtyItem);
        specialty.setAdapter(adapter);
        String[] stateDetail=new String[]{"State","AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI",
                "ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH",
                "NJ","NM","NY","NC","ND","OH","OK","OR","PA","PR","PC","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"};
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stateDetail);
        state.setAdapter(stateAdapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,TrialActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.profile) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
