package com.example.juan.actividad_android;

/**
 * Created by Juan on 28/05/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoPacientes extends Activity{

    private EditText txtNombre;
    private EditText txtDni;
    private ImageButton btnAniadir;
    private ImageButton btnListar;
    private ListView lstPacientes;

    private ConectorBD conectorBD;

    private ArrayList<Paciente> pacientes = new ArrayList<>();
    private AdaptadorListaPacientes adaptadorLista;

    private int contactoSeleccionado;

    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pacientes);

            txtNombre = (EditText) findViewById(R.id.txtNombre);
            txtDni = (EditText) findViewById(R.id.txtDni);
            btnAniadir = (ImageButton) findViewById(R.id.btnAniadir);
            btnListar = (ImageButton) findViewById(R.id.btnListar);
            lstPacientes = (ListView) findViewById(R.id.lstPacientes);

        final Spinner spinner = (Spinner) findViewById(R.id.Tipos);
        String[] valores = {"Niño", "Adolescente", "Adulto", "Anciano"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                //Toast.makeText(adapterView.getContext(), (String) adapterView.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });

            conectorBD = new ConectorBD(this);

            btnAniadir.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    LayoutInflater inflater = getLayoutInflater();

                    View layout = inflater.inflate(R.layout.custom_toast,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));

                    ImageView image = (ImageView) layout.findViewById(R.id.imagen);
                    image.setImageResource(R.drawable.error);

                    TextView text = (TextView) layout.findViewById(R.id.text);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.FILL_HORIZONTAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);

                    try{
                        if(txtNombre.getText().toString().equals("") || txtDni.getText().toString().equals("")){
                            text.setText("Por favor rellene todos los campos");
                            text.setTextSize(20);
                            toast.show();
                        }else{
                            conectorBD.abrir();
                            conectorBD.insertarPaciente(txtNombre.getText().toString(), txtDni.getText().toString() , spinner.getSelectedItem().toString());
                            conectorBD.cerrar();
                            Toast.makeText(getBaseContext(), "Se añadió un nuevo paciente a la BD local!", Toast.LENGTH_SHORT).show();
                        }


                }catch (SQLException e){

                        text.setText("Error! El DNI ya ha sido introducido anteriormente");
                        text.setTextSize(20);
                        toast.show();
                }

                }
            });

            adaptadorLista = new AdaptadorListaPacientes(this, pacientes);
            lstPacientes.setAdapter(adaptadorLista);

            /*Listar los contactos de la base de datos en el ListView*/
            btnListar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    pacientes.removeAll(pacientes);
                    conectorBD.abrir();
                    Cursor c = conectorBD.listarPacientes();
                    if(c.moveToFirst())
                    {
                        do {
                            Paciente paciente= new Paciente(null, null,null);
                            paciente.setNombre(c.getString(0));
                            paciente.setDni(c.getString(1));
                            paciente.setTipo(c.getString(2));
                            pacientes.add(paciente);
                        } while (c.moveToNext());
                    }
                    c.close();
                    conectorBD.cerrar();
                    ((BaseAdapter) lstPacientes.getAdapter()).notifyDataSetChanged();
                }
            });


            registerForContextMenu(lstPacientes);
        }

    //Este método se ejecutará cuando se pulse el botón ”Volver"
    public void oyente_btnVolver(View view){
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pacientes, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.acercaDe:
                Log.d("LogCat", "Pulsó la opción de menú Acerca De...");
                //Se muestra una ventana de diálogo
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Acerca de...");
                builder.setMessage("Aplicación creada por Juan Barba Fernández y Fidel Bueno Jurado");
                builder.setPositiveButton("OK", null);
                builder.create();
                builder.show();
                break;
        }
        return true;
    }

    }

