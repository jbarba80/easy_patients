package com.example.juan.actividad_android;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Juan on 11/04/2016.
 */
public class AdaptadorListaPacientes extends ArrayAdapter<Paciente> {
    private Activity context;
    private ArrayList<Paciente> pacientes;

    public AdaptadorListaPacientes(Activity context, ArrayList<Paciente>pacientes) {
        super(context, R.layout.layout_item_lista, pacientes);
        this.context = context;
        this.pacientes = pacientes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_item_lista, null);
        TextView lblNombre = (TextView) v.findViewById(R.id.lblNombre);
        lblNombre.setText(pacientes.get(position).getNombre());
        TextView lblDni = (TextView) v.findViewById(R.id.lblDni);
        lblDni.setText(pacientes.get(position).getDni());

        ImageView imagPaciente = (ImageView)v.findViewById(R.id.imagePaciente);
        switch (pacientes.get(position).getTipo())
        {
            case "Niño":
                imagPaciente.setImageResource(R.drawable.ninos);
                break;
            case "Adolescente":
                imagPaciente.setImageResource(R.drawable.adolescentes);
                break;
            case "Adulto":
                imagPaciente.setImageResource(R.drawable.adultos);
                break;
            case "Anciano":
                imagPaciente.setImageResource(R.drawable.abuelos);
                break;
        }

        return v;
    }
}
