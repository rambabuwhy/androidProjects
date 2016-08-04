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
public class NatureFragment extends Fragment {


    public NatureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.place_list, container, false);
        final ArrayList<Guide> places = new ArrayList<Guide>();
        places.add(new Guide(R.string.nature_one, R.string.nature_address_one));
        places.add(new Guide(R.string.nature_two, R.string.nature_address_two));
        places.add(new Guide(R.string.nature_three, R.string.nature_address_three));
        places.add(new Guide(R.string.nature_four, R.string.nature_address_four));
        places.add(new Guide(R.string.nature_five, R.string.nature_address_five));
        places.add(new Guide(R.string.nature_six, R.string.nature_address_six));
        places.add(new Guide(R.string.nature_seven, R.string.nature_address_seven));
        places.add(new Guide(R.string.nature_eight, R.string.nature_address_eight));
        places.add(new Guide(R.string.nature_nine, R.string.nature_address_nine));
        places.add(new Guide(R.string.nature_ten, R.string.nature_address_ten));

        GuideAdapter adapter = new GuideAdapter(getActivity(), places, R.color.category_nature);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;
    }

}
