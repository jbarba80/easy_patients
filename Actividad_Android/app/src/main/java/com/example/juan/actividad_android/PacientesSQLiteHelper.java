package com.example.juan.actividad_android;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PacientesSQLiteHelper extends SQLiteOpenHelper {
    /*Sentencia SQL para crear la tabla de Contactos*/
    String sqlCrearTabla="CREATE TABLE Pacientes(nombre TEXT, dni TEXT primary key, tipo TEXT)";

    public PacientesSQLiteHelper(Context contexto, String nombreBD, SQLiteDatabase.CursorFactory factory, int versionBD) {
        super(contexto, nombreBD, factory, versionBD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
                        /*Se ejecutala sentenciaSQL de creaciónde la tabla*/
            db.execSQL(sqlCrearTabla);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
                /*NOTA: Porsimplicidaddel ejemploaquí utilizamosdirectamentela opciónde eliminarla tablaanterior
                y crearlade nuevovacíacon el nuevoformato. Sin embargo lo normal será quehayaquemigrardatosde
                la tablaantiguaa la nueva, porlo que estemétododeberíasermáselaborado.*/
        try {
                        /*Se eliminala versiónanterior de la table*/
            db.execSQL("DROP TABLE IF EXISTS Pacientes");
                        /*Se creala nuevaversiónde la table*/
            db.execSQL(sqlCrearTabla);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}