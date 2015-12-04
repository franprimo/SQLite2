package com.example.franprimo.sqlite2;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ListFragment extends Fragment {

    //Declaro un objeto ListFragmentListener que va a ser el encargado de pasar los mensajes con la
    //accion correspondiente al activity donde se encuentra el fragment.
    ListFragmentListener mCallback;

    public interface ListFragmentListener {
        public void onListSelected(int position);

    }

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //Creo un array con las opciones que tendra el menu
        String[] opcionesMenu = {"Introducir profesor", "Introducir alumno", "Consultar", "Borrar"};

        //Creo un arrayList para obtener una lista
        ArrayList<String> listaMenu = new ArrayList<String>(Arrays.asList(opcionesMenu));

        //En vez de usar el adaptador normal, uso el propio, y le paso la lista de opciones y las imagenes.
        //En este caso, como no es un activity, en el context no le puedo pasar this, por eso para poder
        //cogerlo uso el getActivity()
        MenuAdapter adapter = new MenuAdapter(getActivity(), listaMenu);
        //Por ultimo se lo pasamos a la vista que es el listView (VISTA o INTERFAZ):
        //Como aqui no estamos en un activity, sino que es un fragment, tenemos que pasarse el activity
        //desde este, y eso lo hacemos con getActivity().findViewById()
        final ListView lv = (ListView) getActivity().findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new nuestroListener());
    }

    //Listener propio para la lista.
    private class nuestroListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {

            mCallback.onListSelected(position);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (ListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
