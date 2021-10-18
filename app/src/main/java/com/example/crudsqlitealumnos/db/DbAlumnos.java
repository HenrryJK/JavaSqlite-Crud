package com.example.crudsqlitealumnos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.crudsqlitealumnos.entidades.Alumnos;

import java.util.ArrayList;

public class DbAlumnos extends DbHelper {
    Context context;
    public DbAlumnos(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    public long insertarAlumno ( String nombres , String apellidos , String codigo){
       long id = 0;
        try {
           DbHelper dbHelper = new DbHelper(context);
           SQLiteDatabase db = dbHelper.getWritableDatabase();

           ContentValues values = new ContentValues();
           values.put("nombres", nombres);
           values.put("apellidos", apellidos);
           values.put("codigo", codigo);
            id = db.insert(TABLE_ALUMNOS, null, values);

       }catch (Exception e) {
           e.toString();
       }
       return  id;
    }

    public ArrayList<Alumnos> mostrarAlumnos(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Alumnos> listaAlumnos = new ArrayList<>();
        Alumnos alumno = null;
        Cursor cursorAlumnos = null;

        cursorAlumnos = db.rawQuery("SELECT * FROM " + TABLE_ALUMNOS , null);
        if (cursorAlumnos.moveToFirst() ) {
            do {
             alumno = new Alumnos();
             alumno.setId(cursorAlumnos.getInt(0));
                alumno.setNombres(cursorAlumnos.getString(1));
                alumno.setApellidos(cursorAlumnos.getString(2));
                alumno.setCodigo(cursorAlumnos.getString(3));
                listaAlumnos.add(alumno);
            }while (cursorAlumnos.moveToNext());
        }
        cursorAlumnos.close();
        return listaAlumnos;
    }



    public Alumnos verAlumno(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Alumnos alumno = null;
        Cursor cursorAlumnos;

        cursorAlumnos = db.rawQuery("SELECT * FROM " + TABLE_ALUMNOS + " WHERE id = " + id + " LIMIT 1 " , null);

        if (cursorAlumnos.moveToFirst() ) {
                alumno = new Alumnos();
                alumno.setId(cursorAlumnos.getInt(0));
                alumno.setNombres(cursorAlumnos.getString(1));
                alumno.setApellidos(cursorAlumnos.getString(2));
                alumno.setCodigo(cursorAlumnos.getString(3));

        }
        cursorAlumnos.close();
        return alumno;
    }

    public boolean editarAlumno ( int id,String nombres , String apellidos , String codigo){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL(" UPDATE " + TABLE_ALUMNOS + " SET nombres = '" + nombres +"' ,  apellidos = '" + apellidos +"' ,  codigo = '" + codigo +"' WHERE id ='"+ id +"'" );
            correcto = true;
        }catch (Exception e) {
            e.toString();
            correcto = false;
        }finally {
           db.close();
        }
        return  correcto;
    }

    public boolean eliminarAlumno ( int id){
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL(" DELETE FROM " + TABLE_ALUMNOS + " WHERE id= '"+ id +"'");
            correcto = true;
        }catch (Exception e) {
            e.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return  correcto;
    }

}
