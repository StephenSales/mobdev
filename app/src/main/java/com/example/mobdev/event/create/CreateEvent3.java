package com.example.mobdev.event.create;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobdev.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateEvent3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateEvent3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    public CreateEvent3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateEvent3.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateEvent3 newInstance(String param1, String param2) {
        CreateEvent3 fragment = new CreateEvent3();
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
        View view = inflater.inflate(R.layout.fragment_create_event3, container, false);

        EditText txtPrice = view.findViewById(R.id.eventPrice);
        String eventPrice = String.valueOf(txtPrice.getText());
        EditText addInclusion = view.findViewById(R.id.addInclusion);
        ImageButton btnAdd = view.findViewById(R.id.btnAdd);
        ListView listInclusion = view.findViewById(R.id.listInclusion);
        items = new ArrayList<>();

        items.add("Entrance");

        listInclusion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, items);
        listInclusion.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = addInclusion.getText().toString();
                if (text == null || text.length() == 0) {
                    Toast.makeText(getActivity().getBaseContext(), "Enter an item", Toast.LENGTH_SHORT).show();
                } else {
                    items.add(text);
                    listInclusion.setAdapter(adapter);
                }
            }
        });

        Button submitCreate = view.findViewById(R.id.btnSubmitCreate);
        submitCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getBaseContext(), "Event Created Successfully", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
        return view;
    }
}