package com.example.mobdev.event.create;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mobdev.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateEvent1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateEvent1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateEvent1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateEvent1.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateEvent1 newInstance(String param1, String param2) {
        CreateEvent1 fragment = new CreateEvent1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_event1, container, false);

        // on below line we are initializing our variables.
        ImageButton btnDatepicker = view.findViewById(R.id.btnDatepicker);
        EditText txtDate = view.findViewById(R.id.txtDate);
        EditText txtName = view.findViewById(R.id.eventName);
        EditText txtLoc = view.findViewById(R.id.eventLoc);
        EditText txtTime = view.findViewById(R.id.eventTime);
        RadioGroup rgTheme = view.findViewById(R.id.eventTheme);

        Button btnContinue = view.findViewById(R.id.btnContinue);
        btnContinue.setBackgroundColor(0xEFEFEF);
        btnContinue.setTextColor(Color.BLACK);

        rgTheme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbChecked = view.findViewById(checkedId);
                CreateEvent.eventTheme = rbChecked.getText().toString();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateEvent.eventName = txtName.getText().toString();
                CreateEvent.eventLoc = txtLoc.getText().toString();
                CreateEvent.eventDate = txtDate.getText().toString();
                CreateEvent.eventTime = txtTime.getText().toString();
                ((CreateEvent) requireActivity()).loadFragment(new CreateEvent2());
            }
        });

        // on below line we are adding click listener for our pick date button
        btnDatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                if (monthOfYear < 10 && dayOfMonth < 10) {
                                    txtDate.setText(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                } else if (monthOfYear < 10) {
                                    txtDate.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
                                } else if (dayOfMonth < 10) {
                                    txtDate.setText(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);
                                } else {
                                    txtDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                }
                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

        return view;
    }
}