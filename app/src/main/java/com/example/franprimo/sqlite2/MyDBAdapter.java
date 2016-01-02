package com.example.franprimo.sqlite2;

/**
 * Created by FranPrimo on 4/12/15.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by FranPrimo on 4/12/15.
 */
public class MyDBAdapter {

    // Definiciones y constantes
    private static final String DATABASE_NAME = "dbcolegio.db";
    private static final String DATABASE_TABLE = "profesores";
    private static final String DATABASE_TABLE_2 = "alumnos";
    private static final int DATABASE_VERSION = 1;

    private static final String NAME = "nombre";
    private static final String NAMEST = "nom";
    private static final String AGE = "edad";
    private static final String AGEST = "old";
    private static final String CICLO = "ciclo";
    private static final String CURSO = "curso";
    private static final String NOTA = "notaMed";
    private static final String TUTOR = "tutor";
    private static final String ROOM = "room";

    private static final String DATABASE_CREATE = "CREATE TABLE " + DATABASE_TABLE + " (_id integer primary key autoincrement, nombre text, edad integer, ciclo text, tutor text, room text);";
    private static final String DATABASE_CREATE_2 = "CREATE TABLE " + DATABASE_TABLE_2 + " (_id integer primary key autoincrement, nom text, old integer, ciclo text, curso integer, notaMed text);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS " + DATABASE_TABLE + ";";
    private static final String DATABASE_DROP_2 = "DROP TABLE IF EXISTS " + DATABASE_TABLE_2 + ";";

    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBAdapter(Context c) {
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        //OJO open();
    }

    public void open() {

        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarProfesor(String nom, int edad, String ciclo, String tut, String desp) {
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NAME, nom);
        newValues.put(AGE, edad);
        newValues.put(CICLO, ciclo);
        newValues.put(TUTOR, tut);
        newValues.put(ROOM, desp);
        db.insert(DATABASE_TABLE, null, newValues);
    }

    public void insertarAlumno(String nom, int edad, String ciclo, String curso, String nota) {
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NAMEST, nom);
        newValues.put(AGEST, edad);
        newValues.put(CICLO, ciclo);
        newValues.put(CURSO, curso);
        newValues.put(NOTA, nota);
        db.insert(DATABASE_TABLE_2, null, newValues);
    }

    //Metodo que devuelve un ArrayList<String> con los alumnos que hay en la tabla alumnos
    public ArrayList<String> recuperarAlumnos(){
        ArrayList<String> alumnos = new ArrayList<String>();
        Cursor cursor = db.query(DATABASE_TABLE_2, null, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                alumnos.add(cursor.getString(0)+" "+cursor.getString(1));
            }while(cursor.moveToNext());
        }

        return alumnos;
    }

    //Metodo que devuelve un ArrayList<String> con los profesores que hay en la tabla profesores
    public ArrayList<String> recuperarProfesores(){
        ArrayList<String> profesores = new ArrayList<String>();
        Cursor cursor = db.query(DATABASE_TABLE, null, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()) {
            do{
                profesores.add(cursor.getString(0)+" "+cursor.getString(1));
            }while(cursor.moveToNext());
        }

        return profesores;
    }

    public ArrayList<String> recuperarAlumCondicion(String parametro){
        ArrayList<String> alumnos = new ArrayList<String>();
        Cursor cursor = db.query(DATABASE_TABLE_2, null, "ciclo='"+parametro+"'", null, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                alumnos.add(cursor.getString(0)+ " " +cursor.getString(1));
            }while (cursor.moveToNext());
        }

        return alumnos;
    }

    public ArrayList<String> recuperarAlumCondicion2(String curso){
        ArrayList<String> alumnos = new ArrayList<String>();
        Cursor cursor = db.query(DATABASE_TABLE_2, null, "curso='"+curso+"'", null, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            do{
                alumnos.add(cursor.getString(0)+ " " +cursor.getString(1));
            }while (cursor.moveToNext());
        }

        return alumnos;
    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE_2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP);
            db.execSQL(DATABASE_DROP_2);
            onCreate(db);
        }

    }
}
