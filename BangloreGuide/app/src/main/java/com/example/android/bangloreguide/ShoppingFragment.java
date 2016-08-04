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
public class ShoppingFragment extends Fragment {


    public ShoppingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.place_list, container, false);
        final ArrayList<Guide> places = new ArrayList<Guide>();
        places.add(new Guide(R.string.shopping_one, R.string.shopping_address_one));
        places.add(new Guide(R.string.shopping_two, R.string.shopping_address_two));
        places.add(new Guide(R.string.shopping_three, R.string.shopping_address_three));
        places.add(new Guide(R.string.shopping_four, R.string.shopping_address_four));
        places.add(new Guide(R.string.shopping_five, R.string.shopping_address_five));
        places.add(new Guide(R.string.shopping_six, R.string.shopping_address_six));
        places.add(new Guide(R.string.shopping_seven, R.string.shopping_address_seven));
        places.add(new Guide(R.string.shopping_eight, R.string.shopping_address_eight));
        places.add(new Guide(R.string.shopping_nine, R.string.shopping_address_nine));
        places.add(new Guide(R.string.shopping_ten, R.string.shopping_address_ten));

        GuideAdapter adapter = new GuideAdapter(getActivity(), places, R.color.category_shopping);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        return rootView;
    }

}
