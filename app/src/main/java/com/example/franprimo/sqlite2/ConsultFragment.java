package com.example.franprimo.sqlite2;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ConsultFragment extends Fragment {

    Toast t;

    public ConsultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        RadioGroup rGroup = (RadioGroup) getActivity().findViewById(R.id.radioGroup);
        final RadioButton rAlumno = (RadioButton) getActivity().findViewById(R.id.alumRadio);
        RadioButton rProf = (RadioButton) getActivity().findViewById(R.id.profRadio);
        final ListView listado = (ListView) getActivity().findViewById(R.id.listado);

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.alumRadio) {
                    //Tiene que llamar al metodo de MyDBAdapter recuperarAlumnos() que devuelve
                    // un ArrayList de alumnos y lo cargara en la lista.
                    MyDBAdapter myDB = new MyDBAdapter(getActivity());
                    myDB.open();
                    ArrayList<String> listaAlumnos = myDB.recuperarAlumnos();
                    MenuAdapter adapter = new MenuAdapter(getActivity(), listaAlumnos);
                    listado.setAdapter(adapter);

                    t = Toast.makeText(getActivity(), "Has seleccionado alumnos", Toast.LENGTH_LONG);
                    t.show();
                }
                if (checkedId == R.id.profRadio) {
                    //Tiene que llamar al metodo de MyDBAdapter recuperarProfesores() que devuelve
                    //un ArrayList de profesores y lo cargara en la lista.
                    MyDBAdapter myDB = new MyDBAdapter(getActivity());
                    myDB.open();
                    ArrayList<String> listaProf = myDB.recuperarProfesores();
                    MenuAdapter adapter = new MenuAdapter(getActivity(), listaProf);
                    listado.setAdapter(adapter);

                    t = Toast.makeText(getActivity(), "Has seleccionado profesores", Toast.LENGTH_LONG);
                    t.show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consult, container, false);
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
