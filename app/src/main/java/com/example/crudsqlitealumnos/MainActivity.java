package com.example.crudsqlitealumnos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.example.crudsqlitealumnos.adaptadores.ListaAlumnosAdapter;
import com.example.crudsqlitealumnos.db.DbAlumnos;
import com.example.crudsqlitealumnos.db.DbHelper;
import com.example.crudsqlitealumnos.entidades.Alumnos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   /* Button btnCrear;*/
    RecyclerView listaAlumnos;
    ArrayList<Alumnos> listaArrayAlumnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // btnCrear = findViewById(R.id.btnCrear);
        listaAlumnos = findViewById(R.id.listarAlumnos);
        listaAlumnos.setLayoutManager(new LinearLayoutManager(this));
        DbAlumnos dbAlumnos = new DbAlumnos(MainActivity.this);
        listaArrayAlumnos = new ArrayList<>();

        ListaAlumnosAdapter adapter = new ListaAlumnosAdapter(dbAlumnos.mostrarAlumnos());
        listaAlumnos.setAdapter(adapter);
       /* btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db != null) {
                    Toast.makeText(MainActivity.this,"BASE DE DATOS CREADA" ,Toast.LENGTH_LONG).show();
                    db.close();
                }else{
                    Toast.makeText(MainActivity.this,"ERROR AL CREAR LA BASE DE DATOS" ,Toast.LENGTH_LONG).show();
                }
            }
        }); */
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void nuevoRegistro(){
        Intent intent = new Intent(this , NuevoActivity.class);
        startActivity(intent);
    }
}