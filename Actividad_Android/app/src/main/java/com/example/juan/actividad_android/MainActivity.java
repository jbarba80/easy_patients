package com.example.juan.actividad_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
    }

    public void oyente_btnEntrar(View view){
        Log.d("Debug_bienvenido","Sepulsó el botón Entrar");

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_layout_id));

        ImageView image = (ImageView) layout.findViewById(R.id.imagen);
        TextView text = (TextView) layout.findViewById(R.id.text);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.FILL_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);

        if(txtUsuario.getText().toString().equals("admin") && txtPassword.getText().toString().equals("123") ){

            image.setImageResource(R.drawable.correcto);
            text.setText("Éxito en la autentificación!");
            text.setTextSize(20);
            toast.show();

            Intent i = new Intent(this, ListadoPacientes.class);
            startActivity(i);
        }else{

            image.setImageResource(R.drawable.incorrecto);
            text.setText("Los datos introducidos son incorrectos. Por for favor introduzca unos datos correctos.");
            text.setTextSize(20);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }

    }
}
