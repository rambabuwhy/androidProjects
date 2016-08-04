package com.example.android.bangloreguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MuseumsFragment extends Fragment {


    public MuseumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_list, container, false);
        final ArrayList<Guide> places = new ArrayList<Guide>();
        places.add(new Guide(R.string.museum_one, R.string.museum_address_one,
                R.drawable.museum_one));
        places.add(new Guide(R.string.museum_two, R.string.museum_address_two,
                R.drawable.museum_two));
        places.add(new Guide(R.string.museum_three, R.string.museum_address_three,
                R.drawable.museum_three));
        places.add(new Guide(R.string.museum_four, R.string.museum_address_four,
                R.drawable.museum_four));
        places.add(new Guide(R.string.museum_five, R.string.museum_address_five,
                R.drawable.museum_five));
        places.add(new Guide(R.string.museum_six, R.string.museum_address_six,
                R.drawable.museum_six));
        places.add(new Guide(R.string.museum_seven, R.string.museum_address_seven,
                R.drawable.museum_seven));
        places.add(new Guide(R.string.museum_eight, R.string.museum_address_eight,
                R.drawable.museum_eight));
        places.add(new Guide(R.string.museum_nine, R.string.museum_address_nine,
                R.drawable.museum_nine));
        places.add(new Guide(R.string.museum_ten, R.string.museum_address_ten,
                R.drawable.museum_ten));


        GuideAdapter adapter = new GuideAdapter(getActivity(), places, R.color.category_museum);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;


    }




}
