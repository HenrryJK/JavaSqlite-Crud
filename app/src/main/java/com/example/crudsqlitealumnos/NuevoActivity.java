package com.example.crudsqlitealumnos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crudsqlitealumnos.db.DbAlumnos;

public class NuevoActivity extends AppCompatActivity {
    EditText txtNombres , txtApellidos , txtCodigo;
    Button btnguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtCodigo = findViewById(R.id.txtCodigo);
        btnguardar = findViewById(R.id.btnGuardar);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbAlumnos dbAlumnos = new DbAlumnos(NuevoActivity.this);
             long id =   dbAlumnos.insertarAlumno(txtNombres.getText().toString() , txtApellidos.getText().toString() , txtCodigo.getText().toString());
             if (id > 0){
                 Toast.makeText(NuevoActivity.this, "Registro Guardado" , Toast.LENGTH_LONG).show();
                 limpiar();
             }else {
                 Toast.makeText(NuevoActivity.this, "Error al  Guardar registro" , Toast.LENGTH_LONG).show();
             }
            }
        });
    }

    private void limpiar(){
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCodigo.setText("");
    }
}