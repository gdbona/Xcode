package com.example.giovanni.xcodeiam.View.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.giovanni.xcodeiam.CustomList;
import com.example.giovanni.xcodeiam.R;

public class ACT_ListaSeparacao extends Activity {

    ListView LST_ListaSeparacao;
    String[] NrLista = {
            "1",
            "3",
            "5",
            "2",
            "8",
            "1",
            "3",
            "5",
            "2",
            "1",
            "3",
            "5",
            "2",
            "1",
            "3",
            "5",
            "2",
            "1",
            "3",
            "5",
            "2",
            "1",
            "3",
            "5",
            "2"
    };

    String[] ClienteLista = {
            "Vapza",
            "Toray",
            "Fersa",
            "Yazaki",
            "Fersa",
            "Vapza",
            "Toray",
            "Fersa",
            "Yazaki",
            "Vapza",
            "Toray",
            "Fersa",
            "Yazaki",
            "Vapza",
            "Toray",
            "Fersa",
            "Yazaki",
            "Vapza",
            "Toray",
            "Fersa",
            "Yazaki",
            "Vapza",
            "Toray",
            "Fersa",
            "Yazaki"
    };

    Integer[] TotalLista = {
            32,
            12,
            72,
            120,
            2135,
            32,
            12,
            72,
            120,
            32,
            12,
            72,
            120,
            32,
            12,
            72,
            120,
            32,
            12,
            72,
            120,
            32,
            12,
            72,
            120
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lista_separacao);
        CustomList listAdapter = new
                CustomList(ACT_ListaSeparacao.this, NrLista, ClienteLista, TotalLista);
        LST_ListaSeparacao = (ListView) findViewById(R.id.LST_ListaSeparacao);
        LST_ListaSeparacao.setAdapter(listAdapter);
        LST_ListaSeparacao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ACT_ListaSeparacao.this, "You Clicked at " + NrLista[+position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
