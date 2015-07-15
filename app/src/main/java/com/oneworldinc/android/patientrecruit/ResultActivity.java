package com.oneworldinc.android.patientrecruit;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ResultActivity extends ActionBarActivity {
    String firstName, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        View view = getLayoutInflater().inflate(R.layout.action_bar_view, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        TextView titleView = (TextView) view.findViewById(R.id.title_text);
        titleView.setText("");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
            actionBar.setCustomView(view);
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
            actionBar.setDisplayShowCustomEnabled(true);
        }


        Button emailSent = (Button) findViewById(R.id.button_emailSent);
        final EditText emailId = (EditText) findViewById(R.id.text_email);
        final EditText firstNameView = (EditText) findViewById(R.id.firstName);
        final EditText lastNameView = (EditText) findViewById(R.id.lastName);


        emailSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                boolean cancel = false;
                View focusView = null;
                // Check for a valid email address.
                if (TextUtils.isEmpty(email)) {
                    emailId.setError("Required");
                    focusView = emailId;
                    cancel = true;
                } else if (!isEmailValid(email)) {
                    emailId.setError("This email address is invalid");
                    focusView = emailId;
                    cancel = true;
                }
                if (cancel) {
                    focusView.requestFocus();
                } else {
                    Intent intent = new Intent(ResultActivity.this, SendMailActivity.class);
                    firstName = firstNameView.getText().toString();
                    lastName = lastNameView.getText().toString();
                    intent.putExtra("fname", firstName);
                    intent.putExtra("lname", lastName);
                    startActivity(intent);
                    finish();
                    ApiClient.post("sendEmail?emailId=" + email, null, new JsonHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            //Toast.makeText(ResultActivity.this, "yes", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ResultActivity.this, SendMailActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                            //Toast.makeText(ResultActivity.this, "No", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {
                            //Toast.makeText(ResultActivity.this, "No", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            //Toast.makeText(ResultActivity.this, "No", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            //Toast.makeText(ResultActivity.this, "No", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    });
                }
            }
        });

    }

    private boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        return matcher.matches();
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
