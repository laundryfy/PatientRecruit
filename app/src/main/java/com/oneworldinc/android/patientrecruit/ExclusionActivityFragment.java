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
public class ExclusionActivityFragment extends Fragment {
    View rootView;
    Button endSession, restartSession;
    String alert;
    public ExclusionActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int exclusiveContentPosition = 0;
        rootView = inflater.inflate(R.layout.fragment_exclusion, container, false);
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.exclusive_radiogroup);
        final TextView exclusivePass = (TextView) rootView.findViewById(R.id.exclusive_pass);
        final TextView exclusionContent = (TextView) rootView.findViewById(R.id.exclusion_text);
        final String[] exclusiveContent_array = getResources().getStringArray(R.array.exclusion_criteria);
        final LinearLayout alertLayout = (LinearLayout) rootView.findViewById(R.id.alert_layout);
        final LinearLayout radioLayout = (LinearLayout) rootView.findViewById(R.id.radio_layout);
        final TextView exclusionAlert = (TextView) rootView.findViewById(R.id.exclusion_alertOne);
        final TextView exclusionAlertDetail = (TextView) rootView.findViewById(R.id.exclusion_alertTwo);
        endSession = (Button) rootView.findViewById(R.id.button_EndSesson);
        restartSession = (Button) rootView.findViewById(R.id.button_restartSession);

        alert=exclusiveContent_array[0];
        exclusionContent.setText(exclusiveContent_array[0]);
        final int[] finalExclusiveContentPosition = {exclusiveContentPosition};

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton radioButton = (RadioButton) rootView.findViewById(checkedId);
                String check = radioButton.getText().toString();
                String yes = "Yes";
                if (check.equals("Yes")) {

                    exclusivePass.setVisibility(View.GONE);
                    radioLayout.setVisibility(View.GONE);
                    exclusionContent.setVisibility(View.GONE);
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
                            Intent intent = new Intent(getActivity(), TrialActivity.class);
                            getActivity().finish();
                            startActivity(intent);
                        }
                    });
                }
                if (check.equals("No")) {
//
                    finalExclusiveContentPosition[0]++;
                    if (finalExclusiveContentPosition[0] < exclusiveContent_array.length) {
                        exclusivePass.setVisibility(View.VISIBLE);
                        exclusivePass.setText("Passed " + finalExclusiveContentPosition[0] + " of 9");
                        exclusionContent.setText(exclusiveContent_array[finalExclusiveContentPosition[0]]);
                        alert=exclusiveContent_array[finalExclusiveContentPosition[0]];
                        radioButton.setChecked(false);
                    }
                    else{
                    Intent intent = new Intent(getActivity(), InclusionActivity.class);
                    getActivity().finish();
                    startActivity(intent);
                    }


                }
            }
        });

        return rootView;
    }
}
