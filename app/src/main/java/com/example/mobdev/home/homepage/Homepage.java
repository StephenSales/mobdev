package com.example.mobdev.home.homepage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.adapter.EventsAdapter;
import com.example.mobdev.jdbc.EventDAO;
import com.example.mobdev.notifications.Notifications;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homepage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Homepage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public static final int DAYS_AHEAD = 45;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Homepage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homepage.
     */
    // TODO: Rename and change types and number of parameters
    public static Homepage newInstance(String param1, String param2) {
        Homepage fragment = new Homepage();
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
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        ImageButton btnNotifs = view.findViewById(R.id.btnNotifs);
        btnNotifs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), Notifications.class);
                startActivity(intent);
            }
        });

        RecyclerView viewUpcomingEventsRecyclerView = view.findViewById(R.id.viewUpcomingEvents);
        viewUpcomingEventsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        EventDAO.getUpcomingEvents(DAYS_AHEAD,
                events -> {
                    this.requireActivity().runOnUiThread(() -> {
                        Toast.makeText(view.getContext(), "Success: Finished fetching events data ", Toast.LENGTH_SHORT).show();
                        Storage.upcomingEvents = events;
                        viewUpcomingEventsRecyclerView.setAdapter(new EventsAdapter(Storage.upcomingEvents, EventsAdapter.Orientation.HORIZONTAL));
                    });
                },
                e -> {
                    this.requireActivity().runOnUiThread(() -> {
                        Toast.makeText(view.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                });


        RecyclerView viewAllEventsRecyclerView = view.findViewById(R.id.viewAllEvents);
        viewAllEventsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        EventDAO.getAllEvents(events -> {
            this.requireActivity().runOnUiThread(() -> {
                Toast.makeText(view.getContext(), "Success: Finished fetching all events data ", Toast.LENGTH_SHORT).show();
                Storage.allEvents = events;
                viewAllEventsRecyclerView.setAdapter(new EventsAdapter(Storage.allEvents, EventsAdapter.Orientation.VERTICAL));
            });
        }, e -> {
            this.requireActivity().runOnUiThread(() -> {
                Toast.makeText(view.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

//        EditText search = view.findViewById(R.id.search);
//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                EventsAdapter.
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        return view;
    }

}