package com.example.giovanni.xcodeiam;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vinicius on 24/11/2018.
 */

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] NrLista;
    private final String[] ClienteLista;
    private final Integer[] TotalLista;

    public CustomList(Activity context, String[] NrLista, String[] ClienteLista, Integer[] TotalLista) {
        super(context, R.layout.list_single, NrLista);
        this.context = context;
        this.NrLista = NrLista;
        this.ClienteLista = ClienteLista;
        this.TotalLista = TotalLista;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.list_single, null, true);

        TextView TXT_NrLista = (TextView) rowView.findViewById(R.id.NrLista);

        TextView TXT_ClienteLista = (TextView) rowView.findViewById(R.id.ClienteLista);

        TextView TXT_TotalLista = (TextView) rowView.findViewById(R.id.TotalLista);

        TXT_NrLista.setText(NrLista[position]);
        TXT_ClienteLista.setText(ClienteLista[position]);
        TXT_TotalLista.setText(String.valueOf(TotalLista[position]));

        return rowView;
    }
}
