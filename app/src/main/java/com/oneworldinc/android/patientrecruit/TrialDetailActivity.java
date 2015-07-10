package com.oneworldinc.android.patientrecruit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TrialDetailActivity extends AppCompatActivity {
Button back,next;
TextView trialDetailContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial_detail);
        back=(Button)findViewById(R.id.button_Return);
        next=(Button)findViewById(R.id.button_Continue);
        trialDetailContent=(TextView)findViewById(R.id.trialDetail);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.hide();
        }

        String content="The MOTION study is a prospective, Phase IV study, enrolling 61 patients" +
                "with Pulmonary Arterial Hypertension (PAH). The study designed to further" +
                "explore patient-reported outcomes in PAH subjects who are not on active" +
                "treatment and living in the United States. In addition, the study will be" +
                "exploring the use of new telemetric technology (Accelerator band) to" +
                "evaluate if this technology correlates with improvements in 6 Minute" +
                "Walking Distance 6MWD in patients with PAH ";
        String styledText = "<u><font color='#90EE90'>Full Trial Information</font></u>";
        trialDetailContent.setText(Html.fromHtml(content + styledText), TextView.BufferType.SPANNABLE);
        

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TrialDetailActivity.this,SubTrialActivity.class);
                finish();
                startActivity(intent);

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(TrialDetailActivity.this,ExclusionActivity.class);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
