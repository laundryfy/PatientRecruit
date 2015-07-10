package com.oneworldinc.android.patientrecruit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


public class SubTrialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_trial);

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
            actionBar.setDisplayShowCustomEnabled(true);
        }

        TextView colourChangeText = (TextView) findViewById(R.id.colourChangeText);
        TextView subTrialText=(TextView)findViewById(R.id.subTrial_Text);
        String text = "Early Signs of Efficacy Study With Riociguat in Adult Homozygous Delta F508 ";
        String styledText = "<u><font color='#3c8acbff'>CF</font></u>";
        colourChangeText.setText(Html.fromHtml(text + styledText + " Patients"), TextView.BufferType.SPANNABLE);

        subTrialText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubTrialActivity.this, TrialDetailActivity.class);
                finish();
                startActivity(intent);
            }
        });

        colourChangeText.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Layout layout = ((TextView) v).getLayout();
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    if (layout != null) {
                        int line = layout.getLineForVertical(y);
                        int offset = layout.getOffsetForHorizontal(line, x);
                        Log.v("index", "" + offset);
                        if (offset == 78 || offset == 79) {

                            openAlert(v);

                        }
                    }


                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void openAlert(View view) {
        String alert = "CF:Cystic Fibrosis is an " +
                "in herited disorder that " +
                "cause severe damage to " +
                "the lungs and digestive " +
                "system";
        TextView textView = new TextView(this);
        textView.setPadding(10, 10, 10, 10);
        textView.setTextColor(Color.rgb(60, 128, 203));
        textView.setText(alert);
        textView.setGravity(Gravity.NO_GRAVITY);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SubTrialActivity.this, "Testing", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        Dialog dialog = alertDialogBuilder.setView(textView).create();

        dialog.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = 600;
        lp.dimAmount = 0.1f;

        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND | WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

}
