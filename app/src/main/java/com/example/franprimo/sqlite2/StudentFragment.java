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

public class StudentFragment extends Fragment {

    public StudentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        final EditText name = (EditText) getActivity().findViewById(R.id.nombreStInput);
        final EditText edad = (EditText) getActivity().findViewById(R.id.edadStInput);
        final EditText ciclo = (EditText) getActivity().findViewById(R.id.cicloStInput);
        final EditText curso = (EditText) getActivity().findViewById(R.id.cursoStInput);
        final EditText nota = (EditText) getActivity().findViewById(R.id.notaStInput);
        final Button guardarAlumno = (Button) getActivity().findViewById(R.id.guadarStBtn);

        guardarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBAdapter dBAdapter = new MyDBAdapter(getActivity());
                dBAdapter.open();

                String nombre = name.getText().toString();
                int age = Integer.parseInt(edad.getText().toString());
                String cic = ciclo.getText().toString();
                String cur = curso.getText().toString();
                String notaMed = nota.getText().toString();

                dBAdapter.insertarAlumno(nombre, age, cic, cur, notaMed);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student, container, false);
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
