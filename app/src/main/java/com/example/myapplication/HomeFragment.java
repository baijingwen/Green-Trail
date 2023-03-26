package com.example.myapplication;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    private ImageButton profileBtn, searchBtn;
    private ImageView weather;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        profileBtn = view.findViewById(R.id.profile);
        profileBtn.setOnClickListener(this);
        searchBtn = view.findViewById(R.id.button_search);
        searchBtn.setOnClickListener(this);

        weather = view.findViewById(R.id.weather_little_picture);
        weather.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.profile:
                startActivity(new Intent(getActivity().getApplication(), ProfileActivity.class));
                break;
            case R.id.button_search:
                startActivity(new Intent(getActivity().getApplication(), search_plant_Activity.class));
                break;
            case R.id.weather_little_picture:
                startActivity(new Intent(getActivity().getApplication(), WeatherActivity.class));
                break;
        }
    }
}