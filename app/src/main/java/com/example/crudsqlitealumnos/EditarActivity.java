package com.example.crudsqlitealumnos;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crudsqlitealumnos.db.DbAlumnos;
import com.example.crudsqlitealumnos.entidades.Alumnos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombres , txtApellidos , txtCodigo;
    Button btnGuardar;
    FloatingActionButton fabEditar , fabEliminar;
    boolean correcto = false;
    Alumnos alumno;
    int id =0;
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
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar.setVisibility(View.INVISIBLE);

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
        DbAlumnos dbAlumnos = new DbAlumnos(EditarActivity.this);
        alumno = dbAlumnos.verAlumno(id);

        if (alumno !=null){
            txtNombres.setText(alumno.getNombres());
            txtApellidos.setText(alumno.getApellidos());
            txtCodigo.setText(alumno.getCodigo());

        }
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombres.getText().toString().equals(" ") && !txtApellidos.getText().toString().equals(" ") && !txtCodigo.getText().toString().equals(" ") ){
                 correcto =  dbAlumnos.editarAlumno(id ,txtNombres.getText().toString() , txtApellidos.getText().toString() , txtCodigo.getText().toString());

                 if (correcto){
                     Toast.makeText(EditarActivity.this,"EL REGISTRO SE HA MODIFICADO" , Toast.LENGTH_LONG).show();
                     verRegistro();
                 }else {
                     Toast.makeText(EditarActivity.this,"ERROR AL MODIFICAR REGISTRO" , Toast.LENGTH_LONG).show();
                 }
                }else{
                    Toast.makeText(EditarActivity.this,"DEBE LLENAR TODO LOS CAMPOS OBLIGATORIOS" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID" ,id);
        startActivity(intent);
    }
}