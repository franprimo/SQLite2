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

public class ProfFragment extends Fragment {

    public ProfFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        final EditText name = (EditText) getActivity().findViewById(R.id.nameInput);
        final EditText edad = (EditText) getActivity().findViewById(R.id.edadInput);
        final EditText ciclo = (EditText) getActivity().findViewById(R.id.cicloInput);
        final EditText tutor = (EditText) getActivity().findViewById(R.id.tutorInput);
        final EditText despacho = (EditText) getActivity().findViewById(R.id.despachoInput);
        final Button guardarProf = (Button) getActivity().findViewById(R.id.guardarProfBtn);

        guardarProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBAdapter dBAdapter = new MyDBAdapter(getActivity());
                dBAdapter.open();

                String nombre = name.getText().toString();
                int age = Integer.parseInt(edad.getText().toString());
                String cic = ciclo.getText().toString();
                String tut = tutor.getText().toString();
                String desp = despacho.getText().toString();

                dBAdapter.insertarProfesor(nombre, age, cic, tut, desp);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prof, container, false);
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
