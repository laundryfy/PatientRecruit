package com.oneworldinc.android.patientrecruit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class InclusionActivityFragment extends Fragment {
View rootView;
    Button endSession, restartSession;
    String alert;

    public InclusionActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_inclusion, container, false);
        int exclusiveContentPosition = 0;
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.inclusive_radiogroup);
        final TextView inclusionPass = (TextView) rootView.findViewById(R.id.inclusive_pass);
        final TextView inclusionContent = (TextView) rootView.findViewById(R.id.inclusive_text);
        final String[] inclusionContent_array = getResources().getStringArray(R.array.inclusion_criteria);
        final LinearLayout alertLayout = (LinearLayout) rootView.findViewById(R.id.alert_layout);
        final LinearLayout radioLayout = (LinearLayout) rootView.findViewById(R.id.radio_layout);
        final TextView exclusionAlert = (TextView) rootView.findViewById(R.id.inclusion_alert_one);
        final TextView exclusionAlertDetail = (TextView) rootView.findViewById(R.id.inclusion_alert_two);
        endSession = (Button) rootView.findViewById(R.id.button_end_session);
        restartSession = (Button) rootView.findViewById(R.id.button_restart_session);
        alert=inclusionContent_array[0];
        inclusionContent.setText(inclusionContent_array[0]);
        final int[] finalExclusiveContentPosition = {exclusiveContentPosition};
//        finalExclusiveContentPosition[0]++;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton radioButton = (RadioButton) rootView.findViewById(checkedId);
                String check = radioButton.getText().toString();
                String yes = "Yes";
                if (check.equals("Yes")) {
                    finalExclusiveContentPosition[0]++;
                    if (finalExclusiveContentPosition[0] < inclusionContent_array.length) {
                        inclusionPass.setVisibility(View.VISIBLE);
                        inclusionPass.setText("Passed " + finalExclusiveContentPosition[0] + " of 6");
                        inclusionContent.setText(inclusionContent_array[finalExclusiveContentPosition[0]]);
                        alert=inclusionContent_array[finalExclusiveContentPosition[0]];
                        radioButton.setChecked(false);
                    }
                    else{
                        Intent intent = new Intent(getActivity(), ResultActivity.class);
                        getActivity().finish();
                        startActivity(intent);
                    }

                }
                if (check.equals("No")) {


                    inclusionPass.setVisibility(View.GONE);
                    radioLayout.setVisibility(View.GONE);
                    inclusionContent.setVisibility(View.GONE);
                    alertLayout.setVisibility(View.VISIBLE);
                    exclusionAlert.setVisibility(View.VISIBLE);
                    exclusionAlertDetail.setText(alert);
                    exclusionAlertDetail.setVisibility(View.VISIBLE);
                    endSession.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(getActivity(), ExclusionActivity.class);
                            getActivity().finish();
                            System.exit(0);
                            startActivity(intent);

                        }
                    });

                    restartSession.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            getActivity().finish();
                            startActivity(intent);
                        }
                    });

                }
            }
        });



        return rootView;
    }
}
