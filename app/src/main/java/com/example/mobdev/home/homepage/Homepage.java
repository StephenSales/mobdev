package com.example.mobdev.home.homepage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mobdev.R;
import com.example.mobdev.Storage;
import com.example.mobdev.jdbc.EventDAO;

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

        RecyclerView viewUpcomingEventsRecyclerView = view.findViewById(R.id.viewUpcomingEvents);
        viewUpcomingEventsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        EventDAO.getAllEvents(events -> {
            Looper.prepare();
            Toast.makeText(view.getContext(), "Success: Finished fetching events data ", Toast.LENGTH_SHORT).show();

            Storage.upcomingEvents = events;
            viewUpcomingEventsRecyclerView.setAdapter(new UpcomingEventsAdapter());

        }, e -> {
            Looper.prepare();
            Toast.makeText(view.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });


        RecyclerView recyclerView = view.findViewById(R.id.viewAllEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        EventDAO.getAllEvents(events -> {
            Looper.prepare();
            Toast.makeText(view.getContext(), "Success: Finished fetching all events data ", Toast.LENGTH_SHORT).show();

            Storage.allEvents = events;
            recyclerView.setAdapter(new AllEventsAdapter());
        }, e -> {
            Looper.prepare();
            Toast.makeText(view.getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });


//        addCardToLayout(view);
//        addCardToLayout(view);


//        CardView card1 = view.findViewById(R.id.card1);
//        card1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(getActivity(), OpenEvent.class);
//                startActivity(intent1);
//            }
//        });


//        ImageButton btnBookmarkEvent1 = view.findViewById(R.id.btnBookmarkEvent1);
//        btnBookmarkEvent1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btnBookmarkEvent1.setImageResource(R.drawable.baseline_bookmark_24);
//                Toast.makeText(getActivity().getBaseContext(), "Event Added to Bookmarks", Toast.LENGTH_SHORT).show();
//            }
//        });


//        ImageButton btnNotifications = view.findViewById(R.id.btnNotifs);
//        btnNotifications.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(getActivity(), Notifications.class);
//                startActivity(intent1);
//            }
//        });

        return view;
    }

}