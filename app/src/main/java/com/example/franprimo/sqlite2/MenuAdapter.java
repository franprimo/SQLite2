package com.example.franprimo.sqlite2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by FranPrimo on 18/11/15.
 */
public class MenuAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> datos;
    private ArrayList<Integer> imagenes;

    public MenuAdapter(Context context, ArrayList<String> opcionesMenu) {
        super(context, 0, opcionesMenu);
        this.context = context;
        this.datos = opcionesMenu;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_list, parent, false);
            TextView texto = (TextView) convertView.findViewById(R.id.textView);


            texto.setText(datos.get(position));

        }
        return convertView;
    }

}
