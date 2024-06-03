package com.example.mobdev.home.events;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.adapter.EventsAdapter;
import com.example.mobdev.jdbc.EventDAO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PastEvents#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastEvents extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PastEvents() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pastEvents.
     */
    // TODO: Rename and change types and number of parameters
    public static PastEvents newInstance(String param1, String param2) {
        PastEvents fragment = new PastEvents();
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
        View view = inflater.inflate(R.layout.fragment_past_events, container, false);

        RecyclerView viewUserPassedEvents = view.findViewById(R.id.viewUserPassedEvents);
        viewUserPassedEvents.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        viewUserPassedEvents.setAdapter(new EventsAdapter(Storage.passedUserEvents, EventsAdapter.Orientation.VERTICAL));

        return view;
    }
}