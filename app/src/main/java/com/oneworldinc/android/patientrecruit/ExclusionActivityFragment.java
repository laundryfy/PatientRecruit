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
public class ExclusionActivityFragment extends Fragment {
    View rootView;

    public ExclusionActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int exclusiveContentPosition = 0;
        rootView = inflater.inflate(R.layout.fragment_exclusion, container, false);
        final Button errorMsg = (Button) rootView.findViewById(R.id.button_exclusive_pass);
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.exclusive_radiogroup);
        final TextView exclusivePass = (TextView) rootView.findViewById(R.id.exclusive_pass);
        final TextView exclusiveContent = (TextView) rootView.findViewById(R.id.elclusive_text);
        final String[] exclusiveContent_array = getResources().getStringArray(R.array.exclusion_criteria);
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
                    errorMsg.setVisibility(View.VISIBLE);
                    exclusivePass.setVisibility(View.GONE);
                }
                if (check.equals("No")) {
//
                    finalExclusiveContentPosition[0]++;
                    if (finalExclusiveContentPosition[0] < exclusiveContent_array.length) {
                        errorMsg.setVisibility(View.GONE);
                        exclusivePass.setVisibility(View.VISIBLE);
                        exclusivePass.setText("Passed " + finalExclusiveContentPosition[0] + " of 9");
                        exclusiveContent.setText(exclusiveContent_array[finalExclusiveContentPosition[0]]);
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
