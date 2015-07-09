package com.oneworldinc.android.patientrecruit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;


public class ResultActivity extends ActionBarActivity {
//    final String username = "thavasukkannu@gmail.com";
//    final String password = "s.thavasukkannu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button emailSent = (Button) findViewById(R.id.button_emailSent);
        final EditText emailId = (EditText) findViewById(R.id.text_email);


        emailSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                if (!email.equals("")) {
                    ApiClient.post("sendEmail?emailId="+email, null, new JsonHttpResponseHandler(){

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {


                        }
                    });


                } else {
                    Toast.makeText(ResultActivity.this, "Please enter the mail id" + "" + email, Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

//    private void sendMail(String email, String subject, String messageBody) {
//        Session session = createSessionObject();
//
//        try {
//            Message message = createMessage(email, subject, messageBody, session);
//            new SendMailTask().execute(message);
//        } catch (AddressException e) {
//            e.printStackTrace();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private Message createMessage(String email, String subject, String messageBody, Session session) throws MessagingException, UnsupportedEncodingException {
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress("tutorials@tiemenschut.com", "Tiemen Schut"));
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, email));
//        message.setSubject(subject);
//        message.setText(messageBody);
//        return message;
//    }
//
//    private Session createSessionObject() {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//
//        return Session.getInstance(properties, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//    }
//
//    private class SendMailTask extends AsyncTask<Message, Void, Void> {
//        private ProgressDialog progressDialog;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog = ProgressDialog.show(ResultActivity.this, "Please wait", "Sending mail", true, false);
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            progressDialog.dismiss();
//        }
//
//        @Override
//        protected Void doInBackground(Message... messages) {
//            try {
//                Transport.send(messages[0]);
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_result, menu);
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
