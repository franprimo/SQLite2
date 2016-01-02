package com.example.franprimo.sqlite2;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ConsultasAvanzadasFragment extends Fragment {

    public ConsultasAvanzadasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final EditText cicloInput = (EditText) getActivity().findViewById(R.id.cicloInput);
        final EditText cursoInput = (EditText) getActivity().findViewById(R.id.editText);
        Button button = (Button) getActivity().findViewById(R.id.button);
        final ListView listaAvanzada = (ListView) getActivity().findViewById(R.id.listView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cicloInput.getText() != null) {
                    String param = cicloInput.getText().toString();
                    MyDBAdapter myDB = new MyDBAdapter(getActivity());
                    myDB.open();
                    ArrayList<String> listadoPorCiclo = myDB.recuperarAlumCondicion(param);
                    MenuAdapter adapter = new MenuAdapter(getActivity(), listadoPorCiclo);
                    listaAvanzada.setAdapter(adapter);
                }
                if (cursoInput.getText() != null) {
                    String curso = cursoInput.getText().toString();
                    MyDBAdapter myDB = new MyDBAdapter(getActivity());
                    myDB.open();
                    ArrayList<String> listadoPorCurso = myDB.recuperarAlumCondicion2(curso);
                    MenuAdapter adapter = new MenuAdapter(getActivity(), listadoPorCurso);
                    listaAvanzada.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consultas_avanzadas, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
