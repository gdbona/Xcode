package com.example.giovanni.xcodeiam.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.giovanni.xcodeiam.R;

import java.util.ArrayList;
import java.util.List;

public class ACT_Configuracao extends Activity {

    private ArrayList<String> series = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act__configuracao);

        // Get ListView object from xml
        ListView lstConfiguracao  = (ListView) findViewById(R.id.lst_Configuracao);

        // Defined Array values to show in ListView
        String[] values = new String[] { "Nome",
                "E-mail",
                "Senha"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        lstConfiguracao.setAdapter(adapter);

        // ListView Item Click Listener
        lstConfiguracao.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value

                //String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                //Toast.makeText(getApplicationContext(),
                //        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                //        .show();

            }

        });
    }
}
