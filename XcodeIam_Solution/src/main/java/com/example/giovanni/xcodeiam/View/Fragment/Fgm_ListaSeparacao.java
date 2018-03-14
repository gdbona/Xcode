package com.example.giovanni.xcodeiam.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.giovanni.xcodeiam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fgm_ListaSeparacao extends Fragment {


    public Fgm_ListaSeparacao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fgm_lista_separacao, container, false);
    }

}
