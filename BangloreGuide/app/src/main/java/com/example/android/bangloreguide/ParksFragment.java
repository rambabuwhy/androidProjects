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
public class ParksFragment extends Fragment {


    public ParksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_list, container, false);
        final ArrayList<Guide> places = new ArrayList<Guide>();
        places.add(new Guide(R.string.parks_one, R.string.parks_address_one));
        places.add(new Guide(R.string.parks_two, R.string.parks_address_two));
        places.add(new Guide(R.string.parks_three, R.string.parks_address_three));
        places.add(new Guide(R.string.parks_four, R.string.parks_address_four));
        places.add(new Guide(R.string.parks_five, R.string.parks_address_five));
        places.add(new Guide(R.string.parks_six, R.string.parks_address_six));
        places.add(new Guide(R.string.parks_seven, R.string.parks_address_seven));
        places.add(new Guide(R.string.parks_eight, R.string.parks_address_eight));
        places.add(new Guide(R.string.parks_nine, R.string.parks_address_nine));
        places.add(new Guide(R.string.parks_ten, R.string.parks_address_ten));

        GuideAdapter adapter = new GuideAdapter(getActivity(), places, R.color.category_parks);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;
    }

}
