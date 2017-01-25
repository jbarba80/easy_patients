package com.example.juan.actividad_android;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Juan on 11/04/2016.
 */
public class ConectorBD {
    static final String NOMBRE_BD = "PacientesBD";
    private PacientesSQLiteHelper dbHelper;
    private SQLiteDatabase db;

    /*Constructor*/
    public ConectorBD(Context ctx) {
        dbHelper = new PacientesSQLiteHelper(ctx, NOMBRE_BD, null, 1);
    }

    /*Abre la conexión con la base de datos*/
    public ConectorBD abrir() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    /*Cierra la conexión con la base de datos*/
    public void cerrar() {
        if (db != null) db.close();
    }

    /*inserta un contacto en la BD*/
    public void insertarPaciente(String nombre, String dni, String tipo)
    {
        String consultaSQL= "INSERT INTO Pacientes VALUES('"+nombre+"','"+dni+"','"+tipo+"')";
            db.execSQL(consultaSQL);

    }

    /*devuelve todos los contactos*/
    public Cursor listarPacientes()
    {
        return db.rawQuery("SELECT * FROM Pacientes", null);
    }
}