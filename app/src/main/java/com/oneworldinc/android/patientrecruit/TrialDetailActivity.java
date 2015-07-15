package com.oneworldinc.android.patientrecruit;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class TrialDetailActivity extends AppCompatActivity {
Button back,next;
TextView trialDetailContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial_detail);

        View view = getLayoutInflater().inflate(R.layout.action_bar_view, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView titleView = (TextView) view.findViewById(R.id.title_text);
        titleView.setText("Clinical Trial Recruitment Center");

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setElevation(0);
            actionBar.setCustomView(view);
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
            actionBar.setDisplayShowCustomEnabled(true);
        }



        back=(Button)findViewById(R.id.button_Return);
        next=(Button)findViewById(R.id.button_Continue);
        trialDetailContent=(TextView)findViewById(R.id.trialDetail);


        String content="The MOTION study is a prospective, Phase IV study, enrolling 61 patients " +
                "with Pulmonary Arterial Hypertension (PAH). The study designed to further " +
                "explore patient-reported outcomes in PAH subjects who are not on active " +
                "treatment and living in the United States. In addition, the study will be " +
                "exploring the use of new telemetric technology (Accelerator band) to " +
                "evaluate if this technology correlates with improvements in 6 Minute " +
                "Walking Distance 6MWD in patients with PAH ";
        String styledText = "<u><font color='#2E9AFE'>Full Trial Information</font></u>";
        trialDetailContent.setText(Html.fromHtml(content + styledText), TextView.BufferType.SPANNABLE);


        trialDetailContent.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Layout layout = ((TextView) v).getLayout();
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    if (layout != null) {
                        int line = layout.getLineForVertical(y);
                        int offset = layout.getOffsetForHorizontal(line, x);
                        Log.v("index", "" + offset);
                        if (offset>=469&&offset<=489) {
                            Intent intent=new Intent(TrialDetailActivity.this,TrialInfoActivity.class);
                            finish();
                            startActivity(intent);

                        }
                    }

                }
                return true;
            }
        });
        

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
