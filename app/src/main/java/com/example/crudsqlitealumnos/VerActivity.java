package com.example.crudsqlitealumnos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudsqlitealumnos.db.DbAlumnos;
import com.example.crudsqlitealumnos.entidades.Alumnos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtNombres , txtApellidos , txtCodigo;
    Button btnGuardar;
    FloatingActionButton fabEditar , fabEliminar;
    Alumnos alumno;

    int id =0;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtCodigo = findViewById(R.id.txtCodigo);
        btnGuardar = findViewById(R.id.btnGuardar);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            }else {
                id = extras.getInt("ID");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("ID");
        }
        DbAlumnos dbAlumnos = new DbAlumnos(VerActivity.this);
        alumno = dbAlumnos.verAlumno(id);

        if (alumno !=null){
            txtNombres.setText(alumno.getNombres());
            txtApellidos.setText(alumno.getApellidos());
            txtCodigo.setText(alumno.getCodigo());

            btnGuardar.setVisibility(View.INVISIBLE);
            txtNombres.setInputType(InputType.TYPE_NULL);
            txtApellidos.setInputType(InputType.TYPE_NULL);
            txtCodigo.setInputType(InputType.TYPE_NULL);
        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerActivity.this , EditarActivity.class);
                intent.putExtra("ID" , id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿Desea Eliminar este Contacto?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if ( dbAlumnos.eliminarAlumno(id)){
                                    lista();
                                    Toast.makeText(VerActivity.this,"SE ELIMINO REGISTRO CORRECTAMENTE" , Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }


        private void lista(){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
        }
}