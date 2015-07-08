package com.oneworldinc.android.patientrecruit;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class InclusionActivityFragment extends Fragment {
View rootView;
    public InclusionActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_inclusion, container, false);
        int exclusiveContentPosition = 0;
        final Button errorMsg = (Button) rootView.findViewById(R.id.button_inclusive_pass);
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.inclusive_radiogroup);
        final TextView exclusivePass = (TextView) rootView.findViewById(R.id.inclusive_pass);
        final TextView exclusiveContent = (TextView) rootView.findViewById(R.id.inclusive_text);
        final String[] exclusiveContent_array = getResources().getStringArray(R.array.inclusion_criteria);
        exclusiveContent.setText(exclusiveContent_array[0]);
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
                    if (finalExclusiveContentPosition[0] < exclusiveContent_array.length) {
                        errorMsg.setVisibility(View.GONE);
                        exclusivePass.setVisibility(View.VISIBLE);
                        exclusivePass.setText("Passed " + finalExclusiveContentPosition[0] + " of 6");
                        exclusiveContent.setText(exclusiveContent_array[finalExclusiveContentPosition[0]]);
                        radioButton.setChecked(false);
                    }
                    else{
                        Intent intent = new Intent(getActivity(), ResultActivity.class);
                        getActivity().finish();
                        startActivity(intent);
                    }

                }
                if (check.equals("No")) {
//                    radioButton.clearFocus();
//                    radioButton.setFocusable(false);
//                    radioButton.isChecked();

                    errorMsg.setVisibility(View.VISIBLE);
                    exclusivePass.setVisibility(View.GONE);


                }
            }
        });


        return rootView;
    }
}
