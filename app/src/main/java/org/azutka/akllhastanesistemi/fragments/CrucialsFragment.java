package org.azutka.akllhastanesistemi.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.azutka.akllhastanesistemi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrucialsFragment extends Fragment {


    public CrucialsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crucials, container, false);
    }

}
